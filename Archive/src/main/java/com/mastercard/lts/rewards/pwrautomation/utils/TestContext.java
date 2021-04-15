package com.mastercard.lts.rewards.pwrautomation.utils;

import com.mastercard.lts.rewards.pwrautomation.properties.PWRLanguageSpecificProperties;
import org.apache.commons.lang3.StringUtils;

public class TestContext {

    private static ThreadLocal<PWRLanguageSpecificProperties> threadLocal;


    public static void setLanguageSpecificProperties(PWRLanguageSpecificProperties pwrLanguageSpecificProperties) {
        if(threadLocal == null){
            threadLocal = new ThreadLocal<>();
        }
        threadLocal.remove();
        threadLocal.set(pwrLanguageSpecificProperties);
    }

    public static String getValue(String key){
        if(StringUtils.isNotBlank(key)){
            return threadLocal.get().getPage().get(key.trim());
        }
        return null;
    }
}
