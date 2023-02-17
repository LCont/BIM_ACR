package BIM_ACR.BIM_ACR;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;


public class MenuNavigator {
	
    public static void main(String[] args) {
    	final String dir = System.getProperty("user.dir");
		char currentChar = dir.charAt(1);
		System.setProperty("webdriver.chrome.driver", "/avaya/apps/chromedriver");
		String downloadFilepath = "/avaya/zipped";
		String url = "https://172.30.24.11:7070";
		String user = "recordings";
		String password = "AvayaT3mp!";

		if (currentChar == ':') {
			
			System.setProperty("webdriver.chrome.driver", "D:\\Proyectos\\BIM_ACR\\apps\\chromedriver.exe");
			downloadFilepath = "D:\\\\Proyectos\\\\BIM_ACR\\\\zipped\\\\";
			url = "https://fabc.ddns.net:7070";
			user = "Administrator";
			password = "FABCadm22!";
		}
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = new DesiredCapabilities();				
		cap.setAcceptInsecureCerts(true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		cap.setCapability("acceptSslCerts", "True");
		
		WebDriver driver = new ChromeDriver();
		try {
			System.out.println("conectando a: "+url);
	    	
			driver.get(url);
		System.out.println("posicion de la ventana "+driver.manage().window().getPosition().toString());
    	Point point = new Point(0, 0);
    	driver.manage().window().setPosition(point);
    	
        
       
			abriracr(driver, user, password );
			aplicarfecha(driver, 1);
			
			String xpath="//div[@id='CMM_RecEndTimeField_Id']/div/input";
			WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
	        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
	        driver.findElement(By.xpath(xpath)).click();
	        WebElement navegar = driver.findElement(By.xpath(xpath));
	        navegar.sendKeys(Keys.ARROW_DOWN);
	        navegar = driver.findElement(By.xpath(xpath));
	        navegar.sendKeys(Keys.ENTER);
			for (int i = 0; i < 95; i++) {
				aplicarhora(driver, i);
				if (i==94) {
					corregirhorafinal(driver);
				}
				aplicarfiltro(driver);
			}
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			driver.quit();
		}
		
        
    }
    
    
    public static void aplicarfecha(WebDriver driver, int j) throws InterruptedException {
    	
    	
    	WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
    	//FECHA CON OTRO METODO
        String xpath="//input[@id='x-widget-308-input']";
        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        Thread.sleep(200);
        xpath="//*[@id=\"x-menu-el-\"]/div";
        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        WebElement navegar = driver.findElement(By.xpath(xpath));
        navegar = driver.findElement(By.xpath(xpath));
        
        for (int i = 0; i < j; i++) {
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

    }
    public static void aplicarhora(WebDriver driver, int j) throws InterruptedException {
    	WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
    	//FILTROS -Hora
        String xpath="//div[@id='CMM_RecStartTimeField_Id']/div/input";
        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        WebElement navegar = driver.findElement(By.xpath(xpath));
        navegar.sendKeys(Keys.ARROW_DOWN);
        navegar = driver.findElement(By.xpath(xpath));
        navegar.sendKeys(Keys.ENTER);
        
        xpath="//div[@id='CMM_RecEndTimeField_Id']/div/input";
        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        navegar = driver.findElement(By.xpath(xpath));
        navegar.sendKeys(Keys.ARROW_DOWN);
        navegar = driver.findElement(By.xpath(xpath));
        navegar.sendKeys(Keys.ENTER);
        Thread.sleep(100);
    }
    
    public static void aplicarfiltro(WebDriver driver) throws InterruptedException {
    	WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
        //FILTROS - FILTRAR
        String xpath="//button[@id='CMM_RecButtonApplyCriteria_Id']";
        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        WebElement navegar = driver.findElement(By.xpath(xpath));
        navegar = driver.findElement(By.xpath(xpath));
        navegar.click();
        Thread.sleep(1000);
        
        //marcar
        //xpath="//*[@id=\"CMM_RecGrid_Id\"]/div[1]/div/table/tbody[2]/tr/td[1]/div";
        xpath = "/html/body/div[2]/div[2]/div/div[3]/div/div/div[6]/div/div/div/div[2]/div[1]/div/table/tbody[2]/tr/td[1]/div";
        List<WebElement> lista = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		if (!lista.isEmpty()) {
			driver.findElement(By.xpath(xpath)).click();
        }
        Thread.sleep(100);
        
        //descargar
        //*[@id="CMM_RecHeader_Id"]/div/div/div[4]/button
        xpath = "//*[@id=\"CMM_RecHeader_Id\"]/div/div/div[4]/button";
        lista.clear();
        lista = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        if (!lista.isEmpty()) {
        	driver.findElement(By.xpath(xpath)).click();
        }
        
        Thread.sleep(100);
    }
    
    public static void abriracr(WebDriver driver, String user, String password) throws InterruptedException {
    	
        try {
        	
            //LOGiN
        	WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#firstName-input")));
            System.out.println("Title of the page is -> " + driver.getTitle());
            WebElement userinput = driver.findElement(By.id("firstName-input"));
            userinput.sendKeys(user);
            WebElement passwordinput = driver.findElement(By.id("password-input"));
            passwordinput.sendKeys(password);
            WebElement boton = driver.findElement(By.cssSelector(".gwt-Button"));
            boton.click();
            Thread.sleep(100);
            
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
            Thread.sleep(100);
            
            //menu aplicacion, opci[on media manager
            xpath="//div[@id='menu_item_media_manager']/span";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            //navegar = driver.findElement(By.xpath(xpath));
            navegar = driver.findElement(By.id("menu_item_media_manager"));
            System.out.println("Navegar -> " + navegar.getText());
            //action.moveToElement(navegar).perform();
            navegar.click();
            Thread.sleep(100);
            
            //dropbox del media manager
            xpath="//input[@id='Media_Mgr_Menu-input']";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.sendKeys(Keys.ARROW_DOWN);
            xpath="/html/body/div[6]/div/div[4]";
            w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            navegar = driver.findElement(By.xpath(xpath));
            navegar.click();
            Thread.sleep(100);
            
            
        } finally {
            //driver.quit();
        }
    }
    public static void corregirhorafinal(WebDriver driver) throws InterruptedException {
    	WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(20));
    	//FILTROS -Hora
        String xpath="//div[@id='CMM_RecEndTimeField_Id']/div/input";
        w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        driver.findElement(By.xpath(xpath)).click();
        WebElement navegar = driver.findElement(By.xpath(xpath));
        for(int i =0 ; i<95;i++) {
        	navegar.sendKeys(Keys.ARROW_UP);
            navegar = driver.findElement(By.xpath(xpath));
        }
        
        navegar.sendKeys(Keys.ENTER);
        Thread.sleep(100);
    }

}