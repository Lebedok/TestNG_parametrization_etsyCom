package Selenium.TestNG;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Etsy {

   private WebDriver driver;
   private SoftAssert softAssert;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        softAssert = new SoftAssert();




    }

    @Test
    @Parameters({"searchCriteria"})
    public void etsySearch(String etsySearchText){
        driver.get("https://etsy.com");
        Assert.assertTrue(driver.getCurrentUrl().contains("etsy"));
        WebElement searchBar = driver.findElement(By.id("global-enhancements-search-query"));
        searchBar.sendKeys(etsySearchText);
        driver.findElement(By.xpath("//button[@value='Search']")).click();

        List<WebElement> itemLinks = driver.findElements(By.xpath("//h3[@class='text-gray text-truncate mb-xs-0 text-body']"));

        for (WebElement itemLink: itemLinks) {
            String temp = itemLink.getText();
            String [] strArr = etsySearchText.split(" ");
            String java = strArr[0];
            String programming = strArr[1];
            String mug = strArr[2];
            softAssert.assertTrue(temp.contains(java)||temp.contains(programming)||temp.contains(mug));

        }

        softAssert.assertAll();




    }
}
