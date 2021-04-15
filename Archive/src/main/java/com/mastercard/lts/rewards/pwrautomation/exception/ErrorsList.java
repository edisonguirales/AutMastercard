package com.mastercard.lts.rewards.pwrautomation.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class ErrorsList {
	@JsonProperty("Errors")
    Errors errors;
}
