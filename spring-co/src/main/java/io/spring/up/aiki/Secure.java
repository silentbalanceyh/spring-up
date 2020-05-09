package io.spring.up.aiki;

import io.spring.up.exception.web._401SegmentAuthorityException;
import io.spring.up.exception.web._401UnsupportedAuthorityException;
import io.spring.up.log.Log;
import io.vertx.core.json.JsonObject;
import io.vertx.up.fn.Fn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

class Secure {

    private static final Logger LOGGER = LoggerFactory.getLogger(Secure.class);

    private static final String ANONYMOUS = "ROLE_ANONYMOUS";

    static Optional<String> getCurrentUserLogin() {
        final SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        final UserDetails user = (UserDetails) authentication.getPrincipal();
                        return user.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }

    static String getUniqueAuthority() {
        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication authentication = context.getAuthentication();
        final List<GrantedAuthority> authorities = new ArrayList<>();
        authentication.getAuthorities().forEach(item -> authorities.add((GrantedAuthority) item));
        String reference = null;
        Ux.out(1 < authorities.size(), _401UnsupportedAuthorityException.class,
                Secure.class, Secure.getCurrentUserLogin().get(), authorities.size());
        if (0 < authorities.size()) {
            final GrantedAuthority authority = authorities.get(0);
            if (null != authority) {
                final String content = authority.getAuthority();
                reference = Fn.getJvm(() -> new String(Base64.getDecoder().decode(content), "UTF-8"), content);
            }
        }
        return reference;
    }

    static JsonObject getAuthorities() {
        final String authority = getUniqueAuthority();
        final JsonObject data = new JsonObject();
        try {
            data.mergeIn(new JsonObject(authority));
        } catch (final Throwable ex) {
            Log.jvm(LOGGER, ex);
            throw new _401SegmentAuthorityException(Secure.class, authority);
        }
        return data;
    }

    static boolean isAuthenticated() {
        final SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .map(authentication -> authentication.getAuthorities().stream()
                        .noneMatch(grantedAuthority -> isIn(grantedAuthority.getAuthority(), ANONYMOUS)))
                .orElse(false);
    }

    static boolean isInRole(final String authority) {
        final SecurityContext context = SecurityContextHolder.getContext();
        return Optional.ofNullable(context.getAuthentication())
                .map(authentication -> authentication.getAuthorities().stream()
                        .anyMatch(grantedAuthority -> isIn(grantedAuthority.getAuthority(), authority)))
                .orElse(false);
    }

    private static boolean isIn(final String authority, final String checked) {
        final String content = Fn.getJvm(() -> new String(Base64.getDecoder().decode(authority), "UTF-8"), authority);
        final JsonObject item = new JsonObject(content);
        return item.containsKey(checked);
    }
}
