package org.example;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


public class TestiOS {
    AppiumDriver driver;
    String bundleId;
    String platform;

    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platform","iOS");
        caps.setCapability("appium:automationName","XCUITest");
        caps.setCapability("appium:platformVersion",System.getProperty("iosVersion"));
        caps.setCapability("appium:deviceName",System.getProperty("iosName"));
        String projectDir = System.getProperty("user.dir");
        String path_app=System.getProperty("iosApp");
        if(!path_app.startsWith("/")){
            path_app=projectDir+"/apps/"+path_app;
        }
        caps.setCapability("appium:app",path_app);
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/"),caps);
        platform="ios";
        bundleId="com.example.apple-samplecode.UICatalog";

    }

    @Test
    public void A001(){
         driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Buttons\"]")).click();
    }

    @Test
    public void A002(){
        tools.resetApp(driver,platform,bundleId,null);
        By locator_textField_button = By.xpath("//XCUIElementTypeStaticText[@name=\"Text Fields\"]");
        while (true){
            if(tools.scrollDown(driver,locator_textField_button)){
                break;
            }
        }
        driver.findElement(locator_textField_button).click();
        WebElement textField =driver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeTextField"));
        textField.sendKeys("halo");
    }


    @AfterTest
    public void close(){
        if(driver != null){
            driver.quit();
        }
    }

}