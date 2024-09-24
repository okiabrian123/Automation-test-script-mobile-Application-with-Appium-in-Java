# Automation Test With Appium in Java - Browser Testing on Mobile Devices (Android & iOS)
This repository contains automated test scripts for browser testing on Android and iOS mobile devices using Appium and Selenium. The tests are designed to run on mobile devices, utilizing the Chrome browser for Android and Safari browser for iOS.

## Prerequisites
Before running the tests, ensure you have the following installed on your system:
- *Java Development Kit (JDK)* - [Download Here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- *Node.js* - [Download Here](https://nodejs.org/en/download/)
- *Appium* - [Installation Guide](http://appium.io/docs/en/about-appium/getting-started/?lang=en)
- *Android Debug Bridge (ADB)* - Required for Android devices. Install via Android Studio or stand-alone [here](https://developer.android.com/studio/command-line/adb)
- *Xcode (For iOS)* - Required for iOS devices, available from the Mac App Store.

------------------------------------------------------------------------------------
## Setup Instructions
**Clone the repository:**


```bash
git clone https://github.com/okiabrian123/Automation-test-script-mobile-Application-with-Appium-in-Java.git
cd Automation-test-script-mobile-Application-with-Appium-in-Java
```

## Install required dependencies:

### 1. Install dependencies using npm:
```bash
npm install
```

### 2. Set up WebDriverAgent (iOS Only):

For testing on iOS, you need to configure WebDriverAgent to work with Safari. Follow the instructions from the official Appium documentation for setting up WebDriverAgent on iOS, or follow my instructions [here](https://github.com/okiabrian123/Automation-Test-Mobile-Browser/blob/main/WebDriverAgent_Setup.md).

------------------------------------
## Important Note
For iOS, ensure WebDriverAgent is properly configured for testing.

--------------------------------------
## Running the Tests
### Android
1.Connect your Android device with USB debugging enabled.
2. Add Android application to folder apps<br />
example: 
```bash
Automation-test-script-mobile-Application-with-Appium-in-Java/apps/ApiDemos-debug.apk
```
3. Launch Appium

```bash
appium
```
4. Run the tests for Android<br />
Example:<br />
&nbsp;- Android version = 14<br />
&nbsp;- Device name = OPPO A57<br />
&nbsp;- Android apps = ApiDemos-debug.apk<br />
```bash
mvn -Dtest=TestAndroid test -DandroidVersion=14 -DandroidName="OPPO A57" -DandroidApp="ApiDemos-debug.apk"
```

&nbsp; Note: you can use absolute path directly, example:
```bash
mvn -Dtest=TestAndroid test -DandroidVersion=14 -DandroidName="OPPO A57" -DandroidApp="/Users/username/AutomationTest/Automation test script mobile Application/apps/ApiDemos-debug.apk"
```
### iOS
1. Connect your iOS device with Developer mode enabled.<br />
2. Add iOS application to folder apps<br />
example: 
```
Automation-test-script-mobile-Application-with-Appium-in-Java/apps/UIKITCatalog.app
```
3. Launch Appium
```bash
appium
```
4. Run the tests for iOS(simulator)<br />
Example:<br />
&nbsp; - iOS version = 14<br />
&nbsp; - Device name = iPhone SE (3rd generation)<br />
&nbsp; - iOS apps = UIKITCatalog.app<br />

```bash
 mvn -Dtest=TestiOS test -DiosVersion="17.4" -DiosName="iPhone SE (3rd generation)" -DiosApp="UIKITCatalog.app"√
```

Note: you can use absolute path directly, example:

```bash
 mvn -Dtest=TestiOS test -DiosVersion="17.4" -DiosName="iPhone SE (3rd generation)" -DiosApp="/Users/username/AutomationTest/Automation test script mobile Application/apps/UIKITCatalog.app"
```
---------------------------------
## Repository Structure
```bash
Automation-Test-Mobile-Browser/
.
|
├── drivers/                # Folder to store your Chromedriver for Android
├── src/test/java/org.example
│   ├── TestAndroid.java           # Test scripts
│   ├── TestiOS.java           # Test scripts
├── pom.xml                 # Maven project file for dependencies, plugin and profile
└── README.md               # Project documentation
```
-------------------------------
## Contributing
Feel free to submit pull requests for enhancements or bug fixes. Please ensure code changes are properly tested.

