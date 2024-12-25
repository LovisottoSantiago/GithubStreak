package com.lovisotto.githubstreak;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
       
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int streak = 0; 

        for (@SuppressWarnings("unused") WebElement tdElement : tdElements) {
            String expectedDate = currentDate.format(formatter);
    
            for (WebElement element : tdElements) {
                String date = element.getAttribute("data-date");
    
                if (date != null && date.equals(expectedDate)) {
                    String dataLevel = element.getAttribute("data-level");
                    System.out.println("Fecha: " + date + ", Nivel: " + dataLevel);
    
                    if (dataLevel != null && !dataLevel.equals("0")) {
                        streak++;
                    } else {
                        return streak;
                    }
                    
                    break; 
                }
            }

            currentDate = currentDate.minusDays(1);
        }
    
        driver.quit();
        return streak;

    }


}
