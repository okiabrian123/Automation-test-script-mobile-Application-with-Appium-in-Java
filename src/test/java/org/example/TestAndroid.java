package org.example;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


public class TestiOS {
    AppiumDriver driver;


    @BeforeTest
    public void setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("appium:platform","iOS");
        caps.setCapability("appium:automationName","XCUITest");
        caps.setCapability("appium:platformVersion","17.4");
        caps.setCapability("appium:deviceName","iPhone SE (3rd generation)");
        caps.setCapability(  "appium:app","/Users/macmini/AutomationTest/Automation_test_script_iOS_application_with_Appium_in_Java/apps/UIKitCatalog.app");
        driver = new IOSDriver(new URL("http://127.0.0.1:4723/"),caps);
    }

    public void scrollDown() {
        Dimension size = driver.manage().window().getSize();
        int height = size.getHeight();
        int width = size.getWidth();

        // Tentukan titik awal dan akhir untuk scroll
        int startX = width / 2;
        int startY = (int) (height * 0.8);
        int endY = (int) (height * 0.2);

        // Menggunakan PointerInput dan W3C Actions API
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        // Tekan titik awal
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        // Geser ke titik akhir
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    @Test
    public void A001(){
         driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Buttons\"]")).click();
    }

    @Test
    public void A002(){
        scrollDown();
        driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Text Fields\"]")).click();
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