package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;

public class LoginTest extends BaseTest {
    
    @Test(priority = 1)
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        // Verify successful login by checking URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"), 
            "Login failed - not redirected to inventory page");
    }
    
    @Test(priority = 2)
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "wrong_password");
        
        // Verify error message appears
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Username and password do not match"), 
            "Error message not displayed for invalid login");
    }
    
    @Test(priority = 3)
    public void emptyUsernameTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");
        
        // Verify error message for empty username
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Username is required"), 
            "Error message not displayed for empty username");
    }
}