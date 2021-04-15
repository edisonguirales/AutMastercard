
package com.mastercard.lts.rewards.pwrautomation.model;

import lombok.Data;

@Data
public class AuthorizationPostResponse {
  private String programId;
  private String id;
  private String authRewardsServiceType;
  private String responseReasonId;
  private String responseReasonDesc;
  private String responseMessage;
}

