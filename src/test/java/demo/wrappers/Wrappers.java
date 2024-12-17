package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    static WebDriver driver;
        public Wrappers(WebDriver driver){
            this.driver=driver;
        }
        public static void log(String a, String b){
            LocalTime time = LocalTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = time.format(format); 
            System.out.println("Time is - "+ timeString);
            System.out.println(a+"-"+b);
        }
        public static void moveTo(WebElement a){
            Actions action = new Actions(driver);
            action.moveToElement(a).perform();
        }
        public static void select(String word){
            List<WebElement> headingLinks = new ArrayList<>();
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
            headingLinks = driver.findElements(By.xpath("//*[@id='pages']/section/div/div/div/div/h3/a"));
            for(WebElement a: headingLinks){
                Wrappers.moveTo(a);
                if(a.getText().contains(word)){
                    a.click();
                    wait.until(driver->((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
                    break;
                }
            }
        }
        public static void geturl(String url){
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(driver-> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        }

}
