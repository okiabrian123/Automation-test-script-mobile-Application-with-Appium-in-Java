package org.example;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


public class TestAndroid {
    AppiumDriver driver;
    String appId;
    String platform;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platform","Android");
        caps.setCapability("appium:automationName","UiAutomator2");
        caps.setCapability("appium:platformVersion","14");
        caps.setCapability("appium:deviceName","OPPO A57");
        String projectDir = System.getProperty("user.dir");
        caps.setCapability("appium:app",projectDir+"/apps/ApiDemos-debug.apk");
        driver = new AndroidDriver((new URL("http://127.0.0.1:4723/")),caps);
        platform="android";
        appId=tools.getAppIdAndroid(driver);
    }



    @Test
    public void A001(){
         driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"App\"]")).click();
    }

    @Test
    public void A002(){
        By locator_wait = By.xpath("//android.widget.TextView[@content-desc=\"Views\"]");
        tools.resetApp(driver,platform,appId,locator_wait);
        // Tunggu sampai elemen tertentu muncul setelah aplikasi diluncurkan


        driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Views\"]")).click();
        By locator_textField_button =AppiumBy.androidUIAutomator("new UiSelector().text(\"TextFields\")");
        while (true){
            if(tools.scrollDown(driver,locator_textField_button)){
                break;
            }
        }
        driver.findElement(locator_textField_button).click();
        WebElement textField =driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"io.appium.android.apis:id/edit\")"));
        textField.sendKeys("halo");
    }




    @AfterTest
    public void close(){
        if(driver != null){
            driver.quit();
        }
    }

}