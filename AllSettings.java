package com.example.jojoyao.tutorabc;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.appium.java_client.android.AndroidDriver;



public class UtilityTool {
    //driver傳入就好, 不用再new一次
    public static boolean doLogin(AndroidDriver driver) {
        if (driver.findElementById(resourceid.email).isDisplayed()) {
            driver.findElement(By.id(resourceid.email)).sendKeys("ID");
            driver.pressKeyCode(4);
            driver.findElementById(resourceid.password).sendKeys("PW");
            driver.pressKeyCode(4);
            driver.findElementById(resourceid.login_btu).click();

            if (driver.findElementById(resourceid.EnterSession).isDisplayed()) {
                System.out.println("Login success");
                return true;
            } else {
                System.out.println("Login fail");
                return false;
            }
        } else if (driver.findElementById(resourceid.internet).isDisplayed()) {
            System.out.println("Plz turn on Wifi");
        } else {
            System.out.println("Login fail");
        }
        return false;
    }

    public static boolean showSchedule(AndroidDriver driver) {
        if (driver.findElementById(resourceid.Schedule).isDisplayed()) {
            System.out.println("Show schedule success");
            return true;
        }
        captureScreenShots(driver);
        return false;
    }

    public static boolean doLogout(AndroidDriver driver) {
        driver.findElementById(resourceid.Setting).click();
        if (driver.findElementById(resourceid.about).isDisplayed()) {
            scrollToExactElement(driver, "id", resourceid.logout_btu);
            driver.findElementById(resourceid.logout_btu).click();
            return true;
        }
        return false;
    }

    //==============================================================================================

    private static void captureScreenShots(AndroidDriver driver) {
        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //Date format fot screenshot file name
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create dir with given folder name
        new File(resourceid.FOLDER_FOR_CAP).mkdir();
        //Setting file name
        String file_name = df.format(new Date()) + ".png";
        //copy screenshot file into screenshot folder.
        try {
            FileUtils.copyFile(f, new File(resourceid.FOLDER_FOR_CAP + "/" + file_name));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //scroll to find Element in view
    private static void scrollToExactElement(AndroidDriver driver, String type, String str) {
        if (type == "text") {
            driver.findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView" +
                            "(new UiSelector().text(\"" + str + "\").instance(0))");
        } else if (type == "id") {
            driver.findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView" +
                            "(new UiSelector().resourceId(\"" + str + "\").instance(0))");
        }
    }

}
