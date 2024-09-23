package org.example;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class tools {
    public static void resetApp(AppiumDriver driver,String platform,String appId, By locator) {
        // Menutup aplikasi
        if (platform.equalsIgnoreCase("android")){
            driver.executeScript("mobile: terminateApp", ImmutableMap.of("appId", appId));

            // Meluncurkan kembali aplikasi
            driver.executeScript("mobile: activateApp", ImmutableMap.of("appId", appId));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); // Ganti dengan locator yang sesuai
        }else if (platform.equalsIgnoreCase("ios")){
            // Menggunakan mobile: terminateApp untuk menutup aplikasi
            driver.executeScript("mobile: terminateApp", ImmutableMap.of("bundleId", appId));

            // Menggunakan mobile: launchApp untuk membuka kembali aplikasi
            driver.executeScript("mobile: launchApp", ImmutableMap.of("bundleId", appId));
        }

    }
    public static String getAppIdAndroid (AppiumDriver driver){
        String Result="";
            Result = driver.getCapabilities().getCapability("appPackage").toString();
        return Result;
    }
    public static Boolean scrollDown(AppiumDriver driver, By locator) {
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
        return checkElementVisibility(driver,locator);
    }
    public static Boolean checkElementVisibility(AppiumDriver driver, By locator) {
        try {
            // Check if the element is displayed on the screen
            WebElement element =driver.findElement(locator);
            if (element.isDisplayed()) {
                System.out.println("Element is visible on the screen.");
            } else {
                System.out.println("Element is not visible on the screen.");
            }
            return true;
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            return false;
        }
    }
}
