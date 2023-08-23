package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test01 {   public WebDriver driver;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }
    @Test
    public void validateTheNumber(){
        WebElement addButton = driver.findElement(By.cssSelector("div > button"));

        int numberOfElements = 5;
        for (int i = 0; i < numberOfElements; i++) {
            addButton.click();
        }
        List<WebElement> deleteBoxes = driver.findElements(By.cssSelector("#elements>button"));
        Assert.assertEquals(deleteBoxes.size(), numberOfElements);
        for (WebElement deleteBox : deleteBoxes) {
            Assert.assertTrue(deleteBox.isDisplayed());
        }
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
