package com.devdoc.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromptRequest {
    private String prompt;
    private Integer maxTokens;
    private Double temperature;
}
