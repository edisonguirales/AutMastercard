package com.mastercard.lts.rewards.pwrautomation.configuration;

import com.mastercard.lts.rewards.pwrautomation.pages.MobilePageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

@Configuration
@Order(-12)
public class PerfectoConfiguration {

    @Autowired
    public PerfectoConfiguration( ConfigurableEnvironment env) {
//        String[] split = activeProfiles.split(",");
//        Arrays.stream(split).forEach(i->env.addActiveProfile(i));
    }

    @Bean
    public MobilePageFactory mobilePageFactory(){
      return new MobilePageFactory();
    }
}
