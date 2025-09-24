package com.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    
    @BeforeMethod
    public void setUp() {
    	
    	ChromeOptions options = new ChromeOptions();
        
        // Use incognito mode to avoid saved passwords and security checks
        options.addArguments("--incognito");
        
        // Additional security disables
        options.addArguments("--disable-features=PasswordLeakDetection");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-save-password-bubble");
        driver = new ChromeDriver(options);  // Pass options to constructor        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    public WebDriver getDriver() {
        return driver;
    }
}