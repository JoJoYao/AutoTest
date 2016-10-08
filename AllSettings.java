package com.example.jojoyao.tutorabc;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

/**
 * Created by JoJoYao on 2016/9/27.
 */

public class AllSettings {
    private AndroidDriver driver;

    public void setUp() throws Exception {

        // 設定App路徑
        //File classpathRoot = new File(System.getProperty("user.air"));
        File app = new File("/Users/JoJoYao/Documents/TestApp/TutorMobile_1.0.33_apk-dl.com.apk");
        System.out.println("設置路徑完成");

        //設置自動化相關參數
        DesiredCapabilities cap = new DesiredCapabilities();
        //cap.setCapability("appium-version", "1.5.3");
        cap.setCapability("platformVersion", "5.1");
        cap.setCapability("platformName", "Androiad");
        cap.setCapability("deviceName", "Nexus 5X");
        System.out.println("設置測試裝置參數完成");

        //设置apk路径
        cap.setCapability("app",app.getAbsolutePath());
        //如果测试的是AndroidApp的话,需要设置app的package_name & mainActivity-------iOS可以省略
        cap.setCapability("appPackage", "com.tutorabc.tutormobile");
        //cap.setCapability("appActivity", "com.tutorabc.tutormobile_android.HomeSigninActivity");
        System.out.println("Apk Load Succes");

        //初始化 AppiumDriver
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        //設置等待秒數
        //driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        System.out.println("初始化 AppiumDriver");
    }

}
