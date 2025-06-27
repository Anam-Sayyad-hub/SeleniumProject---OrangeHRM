package Edu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class seleniumscript {
    public static void main(String[] args) {

        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();

     
        driver.get("https://opensource-demo.orangehrmlive.com/");

       
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin123");

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();

        sleep(2000);

        // Verify login
        if (driver.getCurrentUrl().contains("dashboard")) {
            System.out.println("✅ Login Successful");
        } else {
            System.out.println("❌ Login Failed");
        }

        //Navigate to PIM module with explicit wait
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pimMenu = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']")));
        pimMenu.click();
        sleep(2000);
        //  Click Add Employee
        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        sleep(2000);

        // Fill employee details and save
        driver.findElement(By.name("firstName")).sendKeys("Anam");
        driver.findElement(By.name("lastName")).sendKeys("Sayyad");
        sleep(1000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleep(3000);

        System.out.println("✅ Employee Added Successfully");

        // Go to Employee List
        driver.findElement(By.xpath("//a[text()='Employee List']")).click();
        sleep(3000);

        // Search employee
        WebElement empNameField = driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]"));
        empNameField.sendKeys("Anam Sayyad");
        sleep(2000);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        sleep(3000);

        System.out.println("🔍 Employee Search Completed");
     
        //  Logout
        driver.findElement(By.className("oxd-userdropdown-name")).click();
        sleep(2000);
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        sleep(2000);

        if (driver.getCurrentUrl().contains("auth/login")) {
            System.out.println("✅ Logout Successful");
        } else {
            System.out.println("❌ Logout Failed");
        }

        // Close browser
        driver.quit();
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}