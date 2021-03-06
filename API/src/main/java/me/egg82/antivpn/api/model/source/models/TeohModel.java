package me.egg82.antivpn.api.model.source.models;

import flexjson.JSON;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TeohModel implements SourceModel {
    private @Nullable String message = null;
    private @Nullable String ip = null;
    private @Nullable String organization = null;
    private @Nullable String asn = null;
    private @Nullable String type = null;
    private @Nullable String risk = null;
    @JSON(name = "is_hosting")
    private int hosting = -1;
    @JSON(name = "vpn_or_proxy")
    private @Nullable String vpnOrProxy = null;

    @Nullable
    public String getMessage() { return message; }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    @Nullable
    public String getIp() { return ip; }

    public void setIp(@Nullable String ip) {
        this.ip = ip;
    }

    @Nullable
    public String getOrganization() { return organization; }

    public void setOrganization(@Nullable String organization) {
        this.organization = organization;
    }

    @Nullable
    public String getAsn() { return asn; }

    public void setAsn(@Nullable String asn) {
        this.asn = asn;
    }

    @Nullable
    public String getType() { return type; }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public String getRisk() { return risk; }

    public void setRisk(@Nullable String risk) {
        this.risk = risk;
    }

    @JSON(name = "is_hosting")
    public int getHosting() { return hosting; }

    @JSON(name = "is_hosting")
    public void setHosting(int hosting) {
        this.hosting = hosting;
    }

    @JSON(name = "vpn_or_proxy")
    @Nullable
    public String getVpnOrProxy() { return vpnOrProxy; }

    @JSON(name = "vpn_or_proxy")
    public void setVpnOrProxy(@Nullable String vpnOrProxy) {
        this.vpnOrProxy = vpnOrProxy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeohModel)) {
            return false;
        }
        TeohModel teohModel = (TeohModel) o;
        return hosting == teohModel.hosting && Objects.equals(message, teohModel.message) && Objects.equals(ip, teohModel.ip) && Objects.equals(
                organization,
                teohModel.organization
        ) && Objects.equals(asn, teohModel.asn) && Objects.equals(type, teohModel.type) && Objects.equals(risk, teohModel.risk) && Objects.equals(
                vpnOrProxy,
                teohModel.vpnOrProxy
        );
    }

    @Override
    public int hashCode() { return Objects.hash(message, ip, organization, asn, type, risk, hosting, vpnOrProxy); }

    @Override
    public String toString() {
        return "TeohModel{" +
                "message='" + message + '\'' +
                ", ip='" + ip + '\'' +
                ", organization='" + organization + '\'' +
                ", asn='" + asn + '\'' +
                ", type='" + type + '\'' +
                ", risk='" + risk + '\'' +
                ", hosting=" + hosting +
                ", vpnOrProxy='" + vpnOrProxy + '\'' +
                '}';
    }
}
