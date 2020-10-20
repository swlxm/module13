package com.epam.java.selenium.utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private static Properties envProp;

    private PropertiesUtil() {}

    public static Properties getInstance(String env) throws IOException {
        if(null == envProp) {
            envProp = new Properties();
            envProp.load(PropertiesUtil.class.getResourceAsStream("/" + env + ".properties"));
        }
        return envProp;
    }
}
