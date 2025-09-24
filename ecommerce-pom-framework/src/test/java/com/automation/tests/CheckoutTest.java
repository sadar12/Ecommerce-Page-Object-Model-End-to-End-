package com.automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductPage;
import com.automation.pages.CheckoutPage;

public class CheckoutTest extends BaseTest {
    
    // No custom constructor needed - BaseTest handles driver initialization
    
    @Test(priority = 1)
    public void successfulCheckoutTest() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        // Step 2: Add product to cart
        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        
        // Step 3: Complete checkout process
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.completeCheckout("John", "Doe", "12345");
        
        // Step 4: Verify order completion
        String confirmationMsg = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(confirmationMsg, "Thank you for your order!", 
            "Order was not completed successfully");
        
        System.out.println("Checkout test completed successfully!");
        System.out.println("✓ Login successful");
        System.out.println("✓ Product added to cart");
        System.out.println("✓ Checkout process completed");
        System.out.println("✓ Order confirmed: " + confirmationMsg);
    }
    
    @Test(priority = 2)
    public void checkoutWithMissingInformationTest() {
        // Step 1: Login and add product
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        
        // Step 2: Try checkout with missing information
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.goToCart();
        checkoutPage.clickCheckout();
        
        // Fill incomplete information (missing postal code)
        checkoutPage.fillCheckoutInformation("John", "Doe", "");
        checkoutPage.clickContinue();
        
        // Step 3: Verify error handling
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("checkout-step-one"), 
            "Should remain on checkout step one due to missing information");
        
        System.out.println("Checkout validation test completed!");
        System.out.println("✓ Missing information properly validated");
    }
    
    @Test(priority = 3)
    public void checkoutPriceVerificationTest() {
        // Step 1: Login and add product
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        ProductPage productPage = new ProductPage(driver);
        productPage.addBackpackToCart();
        
        // Step 2: Go through checkout to overview page
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.goToCart();
        checkoutPage.clickCheckout();
        checkoutPage.fillCheckoutInformation("John", "Doe", "12345");
        checkoutPage.clickContinue();
        
        // Step 3: Verify total price is displayed
        String totalPrice = checkoutPage.getTotalPrice();
        Assert.assertTrue(totalPrice.contains("Total"), 
            "Total price should be displayed on checkout overview");
        Assert.assertTrue(totalPrice.contains("$"), 
            "Price should contain currency symbol");
        
        System.out.println("Price verification test completed!");
        System.out.println("✓ Total price displayed: " + totalPrice);
    }
}