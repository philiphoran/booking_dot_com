Project created on Windows 10 64 bit.
Using Eclipse IDE 2020-06
Java 1.8.0_211 64 bit

1. Download Selenium jar files.
https://www.selenium.dev/downloads/

2. Download browser drivers that are compatible with current versions of your browsers.
    Chrome: https://chromedriver.chromium.org/downloads
    Firefox: https://github.com/mozilla/geckodriver/releases/tag/v0.24.0 
    MS Edge: https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
    
    Save these files in the same location. 
    I created a local directory "C:\Drivers\" and saved them here.

3. Import project from Github into Eclipse

4. Right click on project in Eclipse and click on Build Path > Configure Build Path
    Click on "Add External JARs" and add all Selenium jar files.
    Click on "Add Library" and select JUnit ( default to JUnit 5 for me )
    
5. Click on Run and run them as JUnit tests.
    
