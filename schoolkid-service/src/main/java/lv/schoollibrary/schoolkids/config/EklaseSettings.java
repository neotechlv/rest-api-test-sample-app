package lv.schoollibrary.schoolkids.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.net.URI;

import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties(prefix = "integrations.eklase")
public class EklaseSettings {

    @NotNull
    private String host;

    @NotNull
    private Integer port;

    @NotNull
    private URI searchUri;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public URI getSearchUri() {
        return searchUri;
    }

    public void setSearchUri(URI searchUri) {
        this.searchUri = searchUri;
    }
}
