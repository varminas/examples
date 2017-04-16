package lt.arminai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Vytautas Arminas
 */
@ConfigurationProperties("my")
public class MyCustomProperties {
    private String host;
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
