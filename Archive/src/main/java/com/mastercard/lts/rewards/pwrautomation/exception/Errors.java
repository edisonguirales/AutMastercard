package com.mastercard.lts.rewards.pwrautomation.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class Errors {
	@JsonProperty("Error")
    List<ErrorItem> error;
}
