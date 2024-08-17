package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

// class ini saya buat terpisah untuk mencoba negative test
// apakah bisa masuk ke products page tanpa login atau tidak
public class NegativeTestNoLogin {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/inventory.html");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Open Browser URL");
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,300)");

        //web scrapping (Negative Test)
        delay(3);
        WebElement companyBranding = driver.findElement(By.xpath("//div[@class='app_logo']"));
        System.out.println(companyBranding.isDisplayed());


        //close
        delay(3);
        driver.quit();
        System.out.println("Exit Browser");
    }

    public static void delay(long detik){
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
