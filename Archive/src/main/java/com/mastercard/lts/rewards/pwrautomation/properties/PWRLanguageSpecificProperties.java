package com.mastercard.lts.rewards.pwrautomation.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties
public class PWRLanguageSpecificProperties {

    public Map< String, String > getPage() {
        return page;
    }

    public void setPage(Map< String, String > page) {
        this.page = page;
    }

    private Map<String, String> page = new HashMap<>();
}
