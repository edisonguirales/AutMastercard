package com.mastercard.lts.rewards.pwrautomation.device.driver;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;

import com.mastercard.developer.interceptors.OkHttpOAuth1Interceptor;
import com.mastercard.lts.rewards.pwrautomation.client.ApiClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Profile("android")
@Slf4j
public class APIClientConfiguration {

    @Value("${basePath}")
    private String basePath;

    @Value("${mastercard.api.consumer.key}")
    private String consumerKey;

    @Value("${mastercard.api.key.alias}")
    private String signingKeyAlias;

    @Value("${mastercard.api.keystore.password}")
    private String signingKeyPassword;

    @Value("${mastercard.api.p12.path}")
    private String signingKeyPkcs12FileName;

    public ApiClient getHttpClientWithOAuthHeader() throws IOException {
    	ApiClient client = new ApiClient();
    	client.setBasePath(basePath);
    	client.setDebugging(false);
        InputStream readStream = null;
        try {
        	KeyStore pkcs12KeyStore = KeyStore.getInstance("PKCS12", "SunJSSE");
        	readStream = new FileInputStream(signingKeyPkcs12FileName);
            pkcs12KeyStore.load(readStream, signingKeyPassword.toCharArray());
            PrivateKey signingKey = (PrivateKey) pkcs12KeyStore.getKey(signingKeyAlias, signingKeyPassword.toCharArray());

            return client.setHttpClient(client.getHttpClient()
                    .newBuilder()
                    .addInterceptor(new OkHttpOAuth1Interceptor(consumerKey, signingKey))
                    .build()
            );
        }catch (Exception e){
            log.error(e.getMessage());
        }
        finally {
            assert readStream != null;
            readStream.close();
        }
        return client;
    }
}
