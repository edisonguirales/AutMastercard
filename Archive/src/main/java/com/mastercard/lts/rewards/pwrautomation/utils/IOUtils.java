package com.mastercard.lts.rewards.pwrautomation.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOUtils {

    /*@Autowired
    protected TestRestTemplate template;*/

    public static String getSecurityToken(String path){
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        } catch (IOException e) {
            throw new IllegalArgumentException("No security token found");
        }
    }

    /*public static String getSecurityToken(String user, String password) {
        response = template.getForEntity("/api/helloWorld", String.class);
    }*/
}
