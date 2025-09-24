package com.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductPage;

public class EndtoEnd extends BaseTest {
    
    @Test
    public void completeUserJourneyTest() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        // Step 2: Verify we're on products page
        ProductPage productPage = new ProductPage(driver);
        String pageTitle = productPage.getPageTitle();
        Assert.assertEquals(pageTitle, "Products", "Failed to reach products page");
        
        // Step 3: Add product to cart
        productPage.addBackpackToCart();
        
        // Step 4: Verify cart has item
        String cartCount = productPage.getCartItemCount();
        Assert.assertEquals(cartCount, "1", "Item not added to cart");
        
        System.out.println("End-to-end test completed successfully!");
        System.out.println("✓ Login successful");
        System.out.println("✓ Products page loaded");
        System.out.println("✓ Product added to cart");
        System.out.println("✓ Cart updated with item count");
    }
}