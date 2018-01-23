package com.sapient.dsm.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

public class ServerInstance {
    @NotEmpty
    private String name;

    @NotEmpty
    private String url;

    @NotEmpty
    @Id
    private String id;

    @JsonProperty
    public String getName() {
        return this.name;
    }

    @JsonProperty
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getUrl() {
        return this.url;
    }

    @JsonProperty
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty
    public String getId() {
        return this.id;
    }

    @JsonProperty
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
