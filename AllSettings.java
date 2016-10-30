package com.example.jojoyao.tutorabc;

import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.File;
import java.util.Date;
import java.text.DateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.server.handler.Status;

import io.appium.java_client.AppiumDriver;




public class AllSettings {


    public static AppiumDriver driver;
    public static String folder_name;
    public static DateFormat df;

    public static void captureScreenShots() throws IOException {
        folder_name = "screenshot";
        File f = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Date format fot screenshot file name
        df = new  SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
        //create dir with given folder name
        new File(folder_name).mkdir();
        //Setting file name
        String file_name = df.format(new Date())+".png";
        //copy screenshot file into screenshot folder.
        FileUtils.copyFile(f, new File(folder_name + "/" + file_name));
    }

//    public static void scrollToExactElement(String type,String str) {
//        if (type == "text"){
//            driver.findElementByAndroidUIAutomator(
//                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView" +
//                            "(new UiSelector().text(\"" + str + "\").instance(0))");
//        }
//        else if (type == "id"){
//            driver.findElementByAndroidUIAutomator(
//                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView" +
//                            "(new UiSelector().resourceId(\"" + str + "\").instance(0))");
//        }
//    }



}