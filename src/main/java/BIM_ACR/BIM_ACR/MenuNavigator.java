package BIM_ACR.BIM_ACR;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;


public class MenuNavigator {
    public static void main(String[] args) {
    	final String dir = System.getProperty("user.dir");
		char currentChar = dir.charAt(1);
		if (currentChar == ':') {
			System.setProperty("webdriver.chrome.driver", "D:\\Proyectos\\BIM_ACR\\chromedriver.exe");
		}else {
			System.setProperty("webdriver.chrome.driver", "/avaya/app/chromedriver");
		}
    	
        
        try {
			abriracr();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public static void abriracr() throws InterruptedException {
    	
        try {
        	WebDriver driver = new ChromeDriver();
        	driver.get("https://fabc.ddns.net:7070");
        	System.out.println("posicion de la ventana "+driver.manage().window().getPosition().toString());
        	Point point = new Point(0, 0);
        	driver.manage().window().setPosition(point);
        	
        	//new Point(0,0)
        	
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
            
            
//            //FILTROS -FECHA
//            xpath="//input[@id='x-widget-308-input']";
//            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
//            driver.findElement(By.xpath(xpath)).click();
//            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("GNOAMXJCDNB")));
//            navegar = driver.findElement(By.className("GNOAMXJCDNB"));
//            navegar.click();
//            
//            xpath="//input[@id='x-widget-310-input']";
//            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
//            driver.findElement(By.xpath(xpath)).click();
//            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("GNOAMXJCDNB")));
//            navegar = driver.findElement(By.className("GNOAMXJCDNB"));
//            navegar.click();
//            
//            
            
            //FECHA CON OTRO METODO
            xpath="//input[@id='x-widget-308-input']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(200);
            xpath="//*[@id=\"x-menu-el-\"]/div";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            
            for (i = 0; i < 100; i++) {
				navegar.sendKeys(Keys.ARROW_LEFT);
				Thread.sleep(20);
				
			}
			navegar.sendKeys(Keys.ENTER);
            
            
            
            xpath="//input[@id='x-widget-310-input']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).click();
            //Thread.sleep(200);
            
            xpath="//*[@id=\"x-menu-el-\"]/div";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.sendKeys(Keys.ARROW_LEFT);
            navegar.sendKeys(Keys.ENTER);
            
            
            //FILTROS - FILTRAR
            xpath="//button[@id='CMM_RecButtonApplyCriteria_Id']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.click();
            Thread.sleep(1000);
            
            
            
            
            //marcar
            //xpath="//*[@id=\"CMM_RecGrid_Id\"]/div[1]/div/table/tbody[2]/tr/td[1]/div";
            xpath = "/html/body/div[2]/div[2]/div/div[3]/div/div/div[6]/div/div/div/div[2]/div[1]/div/table/tbody[2]/tr/td[1]/div";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).click();
            Thread.sleep(10);
            //navegar.click();
            
            //descargar
            //*[@id="CMM_RecHeader_Id"]/div/div/div[4]/button
            xpath = "//*[@id=\"CMM_RecHeader_Id\"]/div/div/div[4]/button";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            driver.findElement(By.xpath(xpath)).click();
            
            
            
        } finally {
            //driver.quit();
        }
    }
	public static void selectDate(WebDriver driver, String year, String month, String select_day) throws InterruptedException
    { 
		String xpath="//*[@id=\"x-menu-el-\"]/div";
		List<WebElement> elements = driver.findElements(By.xpath(xpath));

		for (int i=0; i<elements.size();i++)
		{
			System.out.println("Element: "+ i + " Accesible name: "+ elements.get(i).getAccessibleName()+ " Text: " + elements.get(i).getText()  );
			

			//Selecting the month
			//if(elements.get(i).getText().equals(month_year))
			//{ 

				//Selecting the date 
				//List<WebElement> days = driver.findElements(By.xpath("//div[@class='ui-datepicker-inline ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2']/div[2]/table/tbody/tr/td/a"));

				//for (WebElement d:days)
				//{ 
				//	System.out.println(d.getText());
				//	if(d.getText().equals(select_day))
				//	{
				//		d.click();
				//		Thread.sleep(10000);
				//		return;
				//	}
				//}
			//}
		}
    }
}
