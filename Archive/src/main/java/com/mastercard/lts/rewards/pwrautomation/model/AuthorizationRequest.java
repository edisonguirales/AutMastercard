
package com.mastercard.lts.rewards.pwrautomation.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mastercard.lts.rewards.pwrautomation.enums.AuthAccountIdType;
import com.mastercard.lts.rewards.pwrautomation.enums.AuthProcessingCode;
import lombok.Data;


@Data
public class AuthorizationRequest {
    @NotBlank(message = "userId cannot be null or empty")
    private String accountId;
    private AuthAccountIdType accountIdType;
    @NotNull(message = "processingCode cannot be null or empty")
    private AuthProcessingCode processingCode;
    @NotBlank(message = "cardholderBillingAmount cannot be null or empty")
    @Size(max = 12, message = "cardholderBillingAmount maximum allowed size is 12")
    private String cardholderBillingAmount;
    @NotBlank(message = "transactionDateAndTime cannot be null or empty")
    private String transactionDateAndTime;
    @NotBlank(message = "merchantCategoryCode cannot be null or empty")
    @Size(max = 4, message = "merchantCategoryCode maximum allowed size is 4")
    @Pattern(regexp = "^[0-9]*$", message = "merchantCategoryCode should be numeric")
    private String merchantCategoryCode;
    @NotBlank(message = "acquiringInstitutionIdCode cannot be null or empty")
    @Size(max = 6, message = "acquiringInstitutionIdCode maximum allowed size is 6")
    @Pattern(regexp = "^[0-9]*$", message = "acquiringInstitutionIdCode should be numeric")
    private String acquiringInstitutionIdCode;
    @NotBlank(message = "cardAcceptorIdCode cannot be null or empty")
    @Size(max = 15, message = "cardAcceptorIdCode maximum allowed size is 15")
    private String cardAcceptorIdCode;
    @NotBlank(message = "cardAcceptorNameAndLocation cannot be null or empty")
    @Size(max = 40, message = "cardAcceptorNameAndLocation maximum allowed size is 40")
    private String cardAcceptorNameAndLocation;
    @NotBlank(message = "cardholderBillingCurrencyCode cannot be null or empty")
    @Size(max = 3, message = "cardholderBillingCurrencyCode maximum allowed size is 3")
    private String cardholderBillingCurrencyCode;
    @NotBlank(message = "issuerCountryCode cannot be null or empty")
    @Size(max = 3, message = "issuerCountryCode maximum allowed size is 3")
    private String issuerCountryCode;
    @NotBlank(message = "externalId cannot be null or empty")
    @Size(max = 9, message = "externalId maximum allowed size is 9")
    private String externalId;
    private String queueTransaction;
}
