package com.example.jojoyao.tutorabc;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.AppiumDriver;

public class AutoTest {

    //初始化AppiumDriver
    private AndroidDriver driver;

    @Before
    public void setUp() throws Exception {

        // 設定App路徑
        //File classpathRoot = new File(System.getProperty("user.air"));
        File app = new File("/Users/JoJoYao/Documents/TestApp/TutorMobile_1.0.33_apk-dl.com.apk");
        System.out.println("設置路徑完成");

        //設置自動化相關參數
        DesiredCapabilities cap = new DesiredCapabilities();
        //cap.setCapability("appium-version", "1.5.3");
        cap.setCapability("platformVersion", "6.0");
        cap.setCapability("platformName", "Androiad");
        cap.setCapability("deviceName", "G5NPCX072101ETX");
        System.out.println("設置測試裝置參數完成");

        //设置apk路径
        cap.setCapability("app",app.getAbsolutePath());
        //如果测试的是AndroidApp的话,需要设置app的package_name & mainActivity-------iOS可以省略
        cap.setCapability("appPackage", "com.tutorabc.tutormobile");
        cap.setCapability("appActivity", "com.tutorabc.tutormobile_android.HomeSigninActivity");
        System.out.println("Apk Load Succes");

        //初始化 AppiumDriver
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        //設置等待秒數
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        System.out.println("初始化 AppiumDriver");
    }

    @After
    public void tearDown() throws Exception{

        //driver.wait(60000);
        driver.quit();
        System.out.println("運行结束!!!!!!!");
        System.out.println("即将執行NextSession");
    }

    @Test
    public void allTests() {
        System.out.println("Test Start");
        //設true第一個判斷式才會做
        boolean pass = true;

        if (pass) {
            pass = UtilityTool.doLogin(driver);
            System.out.println("doLogin : " + pass);
        }

        //
        if (pass) {
            pass = UtilityTool.showSchedule(driver);
            System.out.println("showSchedule : " + pass);
        }

        //前一步驟為true才會進行
        if (pass) {
            pass = UtilityTool.doLogout(driver);
            System.out.println("doLogout : " + pass);
        }

        System.out.println("Test End");
    }
}
