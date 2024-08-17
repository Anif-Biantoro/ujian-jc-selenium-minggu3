package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AddProduct {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Open Browser URL");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,300)");

        //web scrapping
        delay(1);
        WebElement companyBranding = driver.findElement(By.xpath("//div[@class='login_logo']"));
        System.out.println(companyBranding.isDisplayed());

        //validate title
        delay(1);
        String tabTitle = driver.getTitle();
        String expectedTitle = "Swag Labs";
        System.out.println(tabTitle);

        //assert if for validate title
        customAssertEquals(tabTitle,expectedTitle);

        //scenario test login
        delay(2);
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        delay(2);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        delay(2);
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        System.out.println("login berhasil");

        delay(1);
        String txtDashboard = driver.findElement(By.xpath("//span[@class='title']")).getText();
        String expected    = "Products";
        System.out.println("Open Products Page");

        //assert if for validate product page / login passed!
        customAssertEquals(txtDashboard,expected);


        //scenario test add satu products
        delay(2);
        WebElement ProductAddToCartButton = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']"));
        ProductAddToCartButton.click();
        System.out.println("--Tambah 1 produk ke keranjang--");

        delay(2);
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        String cartItemCount = cartBadge.getText();
        String expectedItemCount = "1";
        customAssertEquals(cartItemCount,expectedItemCount);

        //scenario negative test products
        delay(1);
        String unexpectedItemCount = "3"; //failed jika terdapat 3 item di keranjang setelah add 1 product
        System.out.println("--Apakah terdapat 3 produk di keranjang setelah add 1 produk?--");
        customAssertEquals(cartItemCount, unexpectedItemCount);

        //close
        delay(3);
        driver.quit();
        System.out.println("Exit Browser");
    }

    //SCENARIO NEGATIVE TEST LOGIN TERDAPAT DI CLASS YANG BERBEDA YAITU CLASS negativeTestNoLogin.java

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static void customAssertEquals(String actual, String expected){
        System.out.print("Test Result: ");
        if(actual.equals(expected)){
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }
}