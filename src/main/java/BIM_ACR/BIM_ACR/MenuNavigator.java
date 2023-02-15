package BIM_ACR.BIM_ACR;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


public class MenuNavigator {
    public static void main(String[] args) {
        abriracr();
        
    }
    
    public static void abriracr() {
    	final String dir = System.getProperty("user.dir");
		char currentChar = dir.charAt(1);
		if (currentChar == ':') {
			System.setProperty("webdriver.chrome.driver", "D:\\Proyectos\\BIM_ACR\\chromedriver.exe");
		}else {
			System.setProperty("webdriver.chrome.driver", "/avaya/app/chromedriver");
		}
    	
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://fabc.ddns.net:7070");
            //LOGiN
            WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#firstName-input")));
            System.out.println("Title of the page is -> " + driver.getTitle());
            WebElement user = driver.findElement(By.id("firstName-input"));
            user.sendKeys("Administrator");
            WebElement password = driver.findElement(By.id("password-input"));
            password.sendKeys("FABCadm22!");
            WebElement boton = driver.findElement(By.cssSelector(".gwt-Button"));
            boton.click();
            
            //NAVEGAR
            // menu aplicaciones
            String xpath = new String();
            Actions action = new Actions(driver);
            
            xpath="//div[@id='menu_primary_applications']";
            //w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("menu_primary_applications")));
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));		
            WebElement navegar = driver.findElement(By.id("menu_primary_applications"));
            System.out.println("Navegar -> " + navegar.getText());
            action.moveToElement(navegar).perform();
            
            //menu aplicacion, opci[on media manager
            xpath="//div[@id='menu_item_media_manager']/span";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            
            //navegar = driver.findElement(By.xpath(xpath));
            navegar = driver.findElement(By.id("menu_item_media_manager"));
            System.out.println("Navegar -> " + navegar.getText());
            //action.moveToElement(navegar).perform();
            navegar.click();
            
            //dropbox del media manager
            xpath="//input[@id='Media_Mgr_Menu-input']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.sendKeys(Keys.ARROW_DOWN);
            xpath="/html/body/div[6]/div/div[4]";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.click();
            
            //FILTROS

            //FILTROS -Hora
            xpath="//div[@id='CMM_RecStartTimeField_Id']/div/input";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).click();
            //navegar.click();
            int i=0;
            while(i<(8*4)) {
            	navegar = driver.findElement(By.xpath(xpath));
            	navegar.sendKeys(Keys.ARROW_DOWN);
            	i++;
            }
            navegar = driver.findElement(By.xpath(xpath));
            navegar.sendKeys(Keys.ENTER);
            
            xpath="//div[@id='CMM_RecEndTimeField_Id']/div/input";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.click();
            i=0;
            while(i<(20*4)) {
            	navegar = driver.findElement(By.xpath(xpath));
            	navegar.sendKeys(Keys.ARROW_DOWN);
            	i++;
            }
            navegar = driver.findElement(By.xpath(xpath));
            navegar.sendKeys(Keys.ENTER);
            
            
            //FILTROS -FECHA
            xpath="//input[@id='x-widget-308-input']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).click();
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("GNOAMXJCDNB")));
            navegar = driver.findElement(By.className("GNOAMXJCDNB"));
            navegar.click();
            
            xpath="//input[@id='x-widget-310-input']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).click();
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("GNOAMXJCDNB")));
            navegar = driver.findElement(By.className("GNOAMXJCDNB"));
            navegar.click();
            
            
        
            
            //FILTROS - FILTRAR
            xpath="//button[@id='CMM_RecButtonApplyCriteria_Id']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.click();
         
            
            
            
            
        } finally {
            //driver.quit();
        }
    }
}
