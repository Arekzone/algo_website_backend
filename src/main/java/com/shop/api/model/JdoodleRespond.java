package com.shop.api.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JdoodleRespond {
    private String output;
    private String statusCode;
    private String memory;
    private String cpuTime;
}
