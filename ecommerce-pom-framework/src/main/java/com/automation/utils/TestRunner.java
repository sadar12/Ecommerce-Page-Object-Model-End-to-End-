package com.automation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.automation.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRunner {
    public static void main(String[] args) {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        
        try {
            // Navigate to website
            driver.get("https://www.saucedemo.com/");
            
            // Create LoginPage object
            LoginPage loginPage = new LoginPage(driver);
            
            // Test valid login
            System.out.println("Testing valid login...");
            loginPage.login("standard_user", "secret_sauce");
            
            // Check if login successful
            if (driver.getCurrentUrl().contains("inventory")) {
                System.out.println("Login successful! POM is working correctly.");
            } else {
                System.out.println("Login failed.");
            }
            
            Thread.sleep(3000); // Wait to see the result
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}