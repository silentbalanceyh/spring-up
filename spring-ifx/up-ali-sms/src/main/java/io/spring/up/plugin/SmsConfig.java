package io.spring.up.plugin;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import io.spring.up.config.InfixData;
import io.vertx.core.json.JsonObject;
import io.vertx.up.util.Ut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SmsConfig implements InfixData<SmsConfig> {

    @Value("${tpl.sms.name}")
    private String account;

    @Value("${tpl.sms.secret}")
    private String secret;

    @Value("${tpl.sms.cmHost}")
    private String cmHost;

    @Value("${tpl.sms.cmPort}")
    private Integer cmPort;

    @Value("${tpl.sms.wsHost}")
    private String wsHost;

    @Value("${tpl.sms.wsPort}")
    private Integer wsPort;

    public String getAccount() {
        return this.account;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public String getSecret() {
        return this.secret;
    }

    public void setSecret(final String secret) {
        this.secret = secret;
    }

    public String getCmHost() {
        return this.cmHost;
    }

    public void setCmHost(final String cmHost) {
        this.cmHost = cmHost;
    }

    public Integer getCmPort() {
        return this.cmPort;
    }

    public void setCmPort(final Integer cmPort) {
        this.cmPort = cmPort;
    }

    public String getWsHost() {
        return this.wsHost;
    }

    public void setWsHost(final String wsHost) {
        this.wsHost = wsHost;
    }

    public Integer getWsPort() {
        return this.wsPort;
    }

    public void setWsPort(final Integer wsPort) {
        this.wsPort = wsPort;
    }

    @Override
    public JsonObject toJson() {
        return Ut.serializeJson(this);
    }

    @Override
    public SmsConfig fromJson(final JsonObject config) {
        return Ut.deserialize(config, SmsConfig.class);
    }
}
