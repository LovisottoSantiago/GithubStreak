package com.lovisotto.githubstreak;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Scraper {

    private WebDriver driver;

    public Scraper(String userName) throws IOException{
        try {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
            driver.get(userName);
        }
        catch (Exception e) {
            e.getMessage();
        }
    }

    public int getData(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2)); 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("td.ContributionCalendar-day")));

        List<WebElement> tdElements = driver.findElements(By.cssSelector("td.ContributionCalendar-day"));
       
        int size = tdElements.size();
        int rows = 7;
        int columns = size / rows; 

        int streak = 0; 

        for (int row = rows - 1; row >= 0; row--) {
            for (int col = columns - 1; col >= 0; col--) {
                
                int index = col * rows + row;

                if (index < size) {
                    WebElement element = tdElements.get(index);
                    String dataLevel = element.getAttribute("data-level"); // contribution check
                    String date = element.getAttribute("data-date");

                    System.out.println(date);

                }
            }
        }

        driver.quit(); 
        return streak;

    }


}
