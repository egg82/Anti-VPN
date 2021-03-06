package me.egg82.antivpn.api.model.source.models;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Objects;

public class ProxyCheckModel implements SourceModel {
    private @Nullable String status = null;
    private @Nullable String message = null;
    private @Nullable ProxyCheckModel.IP ip = null;

    public ProxyCheckModel() { }

    @Nullable
    public String getStatus() { return status; }

    public void setStatus(@Nullable String status) {
        this.status = status;
    }

    @Nullable
    public String getMessage() { return message; }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    @Nullable
    public IP getIp() { return ip; }

    public void setIp(@Nullable IP ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProxyCheckModel)) {
            return false;
        }
        ProxyCheckModel that = (ProxyCheckModel) o;
        return Objects.equals(status, that.status) && Objects.equals(message, that.message) && Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() { return Objects.hash(status, message, ip); }

    @Override
    public String toString() {
        return "ProxyCheckModel{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", ip=" + ip +
                '}';
    }

    public static final class IP implements Serializable {
        private @Nullable String proxy = null;
        private @Nullable String type = null;

        @Nullable
        public String getProxy() { return proxy; }

        public void setProxy(@Nullable String proxy) {
            this.proxy = proxy;
        }

        @Nullable
        public String getType() { return type; }

        public void setType(@Nullable String type) {
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof IP)) {
                return false;
            }
            IP ip = (IP) o;
            return Objects.equals(proxy, ip.proxy) && Objects.equals(type, ip.type);
        }

        @Override
        public int hashCode() { return Objects.hash(proxy, type); }

        @Override
        public String toString() {
            return "IP{" +
                    "proxy='" + proxy + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
