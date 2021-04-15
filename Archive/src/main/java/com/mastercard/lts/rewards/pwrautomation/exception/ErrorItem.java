package com.mastercard.lts.rewards.pwrautomation.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorItem {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("ReasonCode")
    private String reasonCode;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Recoverable")
    private Boolean recoverable;
    @JsonProperty("Details")
    private String details;
}
