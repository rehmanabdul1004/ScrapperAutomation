package demo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    static ChromeDriver driver;
    
        /*
         * TODO: Write your tests here with testng @Test annotation. 
         * Follow `testCase01` `testCase02`... format or what is provided in instructions
         */
    
         /*public static void select(String word){
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
        }*/
    @Test
    public void testCase01() throws InterruptedException{
        //List<String> rows= new ArrayList<>();
        List<HashMap<String,String>> data = new ArrayList<>();
        String[] headTR = {"Team Name","Year","Win %"};
        Wrappers.log("Start Test Case","Check");
        new Wrappers(driver);
        Wrappers.geturl("https://www.scrapethissite.com/pages/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        Wrappers.select("Hockey Teams: Forms, Searching and Pagination");
        List<WebElement> headings = new ArrayList<>();
        List<String> headingText = new ArrayList<>();
        headings = driver.findElements(By.xpath("//*[@id='hockey']/div/table/tbody/tr/th"));
        //System.out.println("Size of headings is "+headings.size());
        int TeamName =0;
        int Year = 0;
        int win=0;
        int index=0;
        for(WebElement a: headings){
            for(String b: headTR){
                if(a.getText().equals(b)){
                    headingText.add(a.getText());
                    if(a.getText().equals("Team Name")){
                        TeamName=index+1;
                    }
                    if(a.getText().equals("Win %")){
                        win = index+1;
                    }
                    if(a.getText().equals("Year")){
                        Year =index+1;
                    }
                }
            }
            index=index+1;  
        }
        //data.add(headingText);
        for(int count=2;count<=5;count++){
            List<WebElement> rows = new ArrayList<>();
            rows = driver.findElements(By.xpath("//*[@id='hockey']/div/table/tbody/tr"));
            for(int i=1;i<rows.size();i++){
                //List<String> rowValues = new ArrayList<>();
                HashMap<String,String> rowValues = new HashMap<>();
                Wrappers.moveTo(rows.get(i));
                LocalTime time = LocalTime.now();
                DateTimeFormatter formater = DateTimeFormatter.ofPattern("HH_mm_ss");
                String timeStamp =time.format(formater);
                rowValues.put("timeStamp", timeStamp);
                String Name = rows.get(i).findElement(By.xpath("./td["+TeamName+"]")).getText();
                rowValues.put("Team Name",Name);
                String Y =rows.get(i).findElement(By.xpath("./td["+Year+"]")).getText();
                rowValues.put("Year",Y);
                String W =rows.get(i).findElement(By.xpath("./td["+win+"]")).getText();
                rowValues.put("Win %",W);
                if(Double.parseDouble(W)<0.40){
                    
                    data.add(rowValues);
                }
            }
            WebElement pageClick = driver.findElement(By.xpath("//*[@id='hockey']/div/div[5]/div[1]/ul/li["+count+"]/a"));
            Wrappers.moveTo(pageClick);
            pageClick.click();
            wait.until(driver-> ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        }
        System.out.println(data.size());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        // Write data to JSON file
            objectMapper.writeValue(new File("hockey-team-data.json"), data);
            System.out.println("Data successfully written to hockey-team-data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Wrappers.log("TestCase1 Ended", "check");

    }
    @Test
    public void testCase02() throws InterruptedException{
        new Wrappers(driver);
        List<HashMap<String,String>> data = new ArrayList();
        Wrappers.log("TestCase 2 Started", "Check");
        Wrappers.geturl("https://www.scrapethissite.com/pages/");
        Wrappers.select("Oscar Winning Films: AJAX and Javascript");
        List<WebElement> yearElements = new ArrayList<>();
        yearElements = driver.findElements(By.xpath("//*[@id='oscars']/div/div[4]/div/a"));
       
        for(WebElement a: yearElements){
            int index=0;
            
            Wrappers.moveTo(a);
            a.click();
            Thread.sleep(8000);
            List<WebElement> movierows = new ArrayList<>();
            movierows = driver.findElements(By.xpath("//*[@id='table-body']/tr"));
            index=index+1;
            for(int i =0;i<5;i++){
                HashMap<String,String> MovieNames = new HashMap<>();
                Wrappers.moveTo(movierows.get(i));
                LocalTime time = LocalTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH_mm_ss");
                String timeStamp = time.format(formatter);
                MovieNames.put("Epoche Time Of Scrap", timeStamp);
                String Year = a.getText();
                MovieNames.put("Year", Year);
                String movieName = movierows.get(i).findElement(By.xpath("./td[1]")).getText();
                MovieNames.put("Title",movieName);
                //System.out.println("Title is -" +movieName);
                String label = movierows.get(i).findElement(By.xpath("./td[2]")).getText();
                MovieNames.put("Nominations", label);
                String wards = movierows.get(i).findElement(By.xpath("./td[3]")).getText();
                MovieNames.put("Awards", wards);
                Boolean isWinning = false;
                try{
                    if(movierows.get(i).findElement(By.xpath("./td[4]/i")).isDisplayed()){
                        isWinning=true;
                    }
                }catch(Exception e){}
                MovieNames.put("isWinner", isWinning.toString());
                data.add(MovieNames);
            }
        }
        System.out.println(data.size());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        // Write data to JSON file
            objectMapper.writeValue(new File("oscar-winner-data.json"), data);
            System.out.println("Data successfully written to oscar-winner-data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File jsonFile = new File("hockey-team-data.json");

        // Assert that the file exists
        Assert.assertTrue(jsonFile.exists(), "File should exist!");

        // Assert that the file is not empty
        Assert.assertTrue(jsonFile.length() > 0, "File should not be empty!");
        Wrappers.log("TestCase2 Ended", "check");
    }
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}