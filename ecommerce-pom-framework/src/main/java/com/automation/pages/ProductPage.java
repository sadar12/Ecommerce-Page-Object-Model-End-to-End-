package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    @FindBy(className = "title")
    private WebElement pageTitle;
    
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddToCartButton;
    
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;
    
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    public String getPageTitle() {
        wait.until(ExpectedConditions.visibilityOf(pageTitle));
        return pageTitle.getText();
    }
    
    public void addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(backpackAddToCartButton));
        backpackAddToCartButton.click();
    }
    
    public String getCartItemCount() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartBadge));
            return cartBadge.getText();
        } catch (Exception e) {
            return "0";
        }
    }
}