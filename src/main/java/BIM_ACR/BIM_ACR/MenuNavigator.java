package BIM_ACR.BIM_ACR;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


public class MenuNavigator {
    public static void main(String[] args) {
        abriracr();
        
    }
    
    public static void abriracr(){
    	System.setProperty("webdriver.chrome.driver", "D:\\Proyectos\\BIM_ACR\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://fabc.ddns.net:7070");
            //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            
            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#firstName-input")));
            System.out.println("Title of the page is -> " + driver.getTitle());
            WebElement user = driver.findElement(By.id("firstName-input"));
            user.sendKeys("Administrator");
            WebElement password = driver.findElement(By.id("password-input"));
            password.sendKeys("FABCadm22!");
            WebElement boton = driver.findElement(By.cssSelector(".gwt-Button"));
            boton.click();
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("menu_primary_applications")));
            WebElement navegar = driver.findElement(By.id("menu_primary_applications"));
            Actions action = new Actions(driver);
            action.moveToElement(navegar).perform();
            navegar = driver.findElement(By.id("x-menu-el-menu_item_media_manager"));
            navegar.click();
            		
        } finally {
            //driver.quit();
        }
    }
}
