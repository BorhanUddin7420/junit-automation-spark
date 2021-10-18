package com.qa.util;

import org.openqa.selenium.WebDriver;

public class HelperUtil {
    WebDriver driver;


    public String multiplyOfTwoStringValue(String value1, String value2) {
        float numberOfValue1 = Float.parseFloat(value1.replaceAll("[^\\d.]", ""));
        float numberOfValue2 = Float.parseFloat(value2.replaceAll("[^\\d.]", ""));
        return String.valueOf(numberOfValue1 * numberOfValue2);
    }


}
