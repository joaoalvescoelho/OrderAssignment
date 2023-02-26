package com.example.orderassignment.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSupport {
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;
}