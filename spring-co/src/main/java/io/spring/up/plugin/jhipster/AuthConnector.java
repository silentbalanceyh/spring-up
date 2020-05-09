package io.spring.up.plugin.jhipster;

import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import org.springframework.http.*;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * JHipster的UAA连接器，因为使用了数据规范，需要改动所有Service和UAA之间的接口
 * 原始的Response改动成data -> value
 */
public class AuthConnector {

    public static SignatureVerifier getVerifier(final RestTemplate client,
                                                final String keyEndPoint)
            throws IllegalStateException {
        // 由于修改了UAA的数据接口规范，这里在认证时需要变更，从原始的value改变路径为：data -> value
        final HttpEntity<Void> request = new HttpEntity<Void>(new HttpHeaders());
        final Object body = client
                .exchange(keyEndPoint, HttpMethod.GET, request, Map.class).getBody().get("data");
        final JsonObject data = Ut.serializeJson(body);
        return new RsaVerifier(data.getString("value"));
    }

    public static ResponseEntity<OAuth2AccessToken> getToken(final RestTemplate client,
                                                             final String tokenUri,
                                                             final HttpEntity<MultiValueMap<String, String>> entity) {
        final ResponseEntity<String> responseContent = client.postForEntity(tokenUri, entity,
                String.class);
        if (responseContent.getStatusCode() == HttpStatus.OK) {

            final JsonObject tokenContent = new JsonObject(responseContent.getBody());
            final OAuth2AccessToken token = Ut.deserialize(tokenContent.getJsonObject("data"), OAuth2AccessToken.class);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
