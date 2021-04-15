package com.mastercard.lts.rewards.pwrautomation.service;

import java.io.IOException;
import java.util.List;

import com.mastercard.lts.rewards.pwrautomation.client.ApiClient;
import com.mastercard.lts.rewards.pwrautomation.device.driver.APIClientConfiguration;
import com.mastercard.lts.rewards.pwrautomation.exception.ApiException;
import com.mastercard.lts.rewards.pwrautomation.model.AuthorizationPostResponse;
import com.mastercard.lts.rewards.pwrautomation.model.AuthorizationRequest;
import com.mastercard.lts.rewards.pwrautomation.utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorizationService {

    private ApiClient apiClient;
    private APIClientConfiguration apiClientConfiguration;
    Marker verbose = MarkerFactory.getMarker("Verbose");

    public AuthorizationService(APIClientConfiguration apiClientConfiguration, ApiClient apiClient) {
        this.apiClientConfiguration = apiClientConfiguration;
        this.apiClient = apiClient;

    }

    public ApiResponse<List< AuthorizationPostResponse >> postAuthorizations(AuthorizationRequest request, String requestURI) throws ApiException, IOException {
        apiClient = apiClientConfiguration.getHttpClientWithOAuthHeader();
        Call postAuthorizationsCall = postAuthorizationsCall(request, requestURI);
        return apiClient.execute(postAuthorizationsCall, new TypeToken<List<AuthorizationPostResponse>>() {
        }.getType());
    }

    private Call postAuthorizationsCall(AuthorizationRequest request, String requestURI) throws ApiException {
        return apiClient.buildCall(requestURI, "POST", null, request);
    }

}
