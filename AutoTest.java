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
        driver.manage().timeouts().implicitlyWait(600, TimeUnit.SECONDS);
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
    public void Login() throws InterruptedException{
        System.out.println("Case One on Start");
        
        driver.findElementById(resourceid.email).sendKeys("ID");
        driver.pressKeyCode(4);
        driver.findElementById(resourceid.password).sendKeys("Password");
        driver.pressKeyCode(4);
        driver.findElementById(resourceid.login_btu).click();
        if (driver.findElementById(resourceid.EnterSession).isDisplayed() == true){
            System.out.println("Login_Pass");
        }
        else {
            System.out.println("Login_Fail");
        }
           driver.findElement(By.id(resourceid.email)).sendKeys("ID");
        driver.pressKeyCode(4);
        driver.findElementById(resourceid.password).sendKeys("PW");
        driver.pressKeyCode(4);

        driver.findElementById(resourceid.login_btu).click();
        if (driver.findElementById(resourceid.EnterSession).isDisplayed() == true){
            System.out.println("Login_Pass");
        }
        else {
            System.out.println("Login_Fail");
        }
        //into Schedule Page
            driver.findElementById(resourceid.Schedule).click();

            if (driver.findElementById(resourceid.year).isDisplayed() == true) {
                System.out.println("Schedule_Pass");
            } else {
                System.out.println("Schedule_Fail");
            }
        //into Vocabulary Page
            driver.findElementById(resourceid.Vocabulary).click();

            if (driver.findElementById(resourceid.Vocabulary).isDisplayed() == true) {
                System.out.println("Vocabulary_Pass");
            } else {
                System.out.println("Vocabulary_Fail");
            }
        //into FreeSession Page
            driver.findElementById(resourceid.FreeSession).click();

            if (driver.findElementById(resourceid.FreeSession).isDisplayed() == true) {
                System.out.println("FreeSession_Pass");
            } else {
                System.out.println("FreeSession_Fail");
            }

        //Logout
            driver.findElementById(resourceid.Setting).click();
            driver.findElementById(resourceid.about).isDisplayed();
            scrollToExactElement("id", resourceid.logout_btu);
            driver.findElementById(resourceid.logout_btu).click();

    }


    //scroll to find Element in view
    public void scrollToExactElement(String type,String str) {
        if (type == "text"){
        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView" +
                        "(new UiSelector().text(\"" + str + "\").instance(0))");
    }
        else if (type == "id"){
            driver.findElementByAndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView" +
                            "(new UiSelector().resourceId(\"" + str + "\").instance(0))");
        }
}
    public void waitForScreenToLoad(AppiumDriver lDriver, WebElement element, int seconds){

        WebDriverWait wait = new WebDriverWait(lDriver, seconds);
       wait.until(ExpectedConditions.visibilityOf(element));
   }

    /*@Test
    public void TestTwo() throws InterruptedException{
        System.out.println("Case Two on Start");
    }
    */


}
