package com.shop.api.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class JdoodleBody {
    public String script;
    public String language;
    private String versionIndex;
    private String clientId;
    private String clientSecret;

}
