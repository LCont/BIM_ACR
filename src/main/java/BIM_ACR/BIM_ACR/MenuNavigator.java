package BIM_ACR.BIM_ACR;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;

public class MenuNavigator {

	public static void main(String[] args) {

		WebDriver driver = null;
		try {
			driver = navegador("Chrome");

			String user = "Administrator";
			String password = "FABCadm22!";
			if (System.getProperty("file.separator") == "/") {
				user = "recordings";
				password = "AvayaT3mp!";
			}
			driver = abriracr(driver, user, password);
			driver = aplicarfecha(driver, 1);
			driver = aplicarhora(driver);
			Thread.sleep(10000);
			driver.quit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

	public static WebDriver aplicarXhora(WebDriver driver) throws InterruptedException {
		String timeini = "00:00";
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		String xpath = "//div[@id='CMM_RecStartTimeField_Id']/div/input";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		WebElement navegar = driver.findElement(By.xpath(xpath));
		navegar.sendKeys(Keys.ENTER);
		navegar = driver.findElement(By.xpath(xpath));
		System.out.println("Start time despues del arrow down" + navegar.getDomProperty("value"));
		Thread.sleep(500);
		xpath = "//div[@id='CMM_RecEndTimeField_Id']/div/input";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		navegar = driver.findElement(By.xpath(xpath));
		navegar.sendKeys(Keys.ENTER);
		navegar = driver.findElement(By.xpath(xpath));
		System.out.println("Start time despues del arrow down" + navegar.getDomProperty("value"));
		Thread.sleep(100);
		for (int hour = 0; hour <= 23; hour++) {
			for (int minute = 0; minute <= 45; minute += 15) {
				timeini = String.format("%02d:%02d\n", hour, minute);
				if ((hour == 0) && (minute == 0)) {
					System.out.printf("es verdad" + timeini);
				}
				if (!((hour == 0) && (minute == 0))) {
					System.out.printf(timeini);
					xpath = "//div[@id='CMM_RecStartTimeField_Id']/div/input";
					driver.findElement(By.xpath(xpath)).click();
					navegar = driver.findElement(By.xpath(xpath));
					navegar.sendKeys(Keys.ARROW_DOWN);
					navegar = driver.findElement(By.xpath(xpath));
					navegar.sendKeys(Keys.ENTER);
					navegar = driver.findElement(By.xpath(xpath));
					System.out.println("Start time despues del arrow down" + navegar.getDomProperty("value"));
					Thread.sleep(500);
				}

				if (!((hour == 23) && (minute == 45))) {
					xpath = "//div[@id='CMM_RecEndTimeField_Id']/div/input";
					w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
					driver.findElement(By.xpath(xpath)).click();
					navegar = driver.findElement(By.xpath(xpath));
					navegar.sendKeys(Keys.ARROW_DOWN);
					navegar = driver.findElement(By.xpath(xpath));
					navegar.sendKeys(Keys.ENTER);
					navegar = driver.findElement(By.xpath(xpath));
					System.out.println("Stop time despues del arrow down" + navegar.getDomProperty("value"));
					Thread.sleep(500);
					
					
				}else {
					System.out.println("deberias resetear a cero");
					corregirhorafinal(driver);
				}
				System.out.println("llamar al filtro");
				aplicarfiltro(driver);
			}
		}
	
		return driver;
	}

	public static WebDriver aplicarhora(WebDriver driver) throws InterruptedException {
		// Preparar la hora final en 00:15
		String xpath = "//div[@id='CMM_RecEndTimeField_Id']/div/input";
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		WebElement navegar = driver.findElement(By.xpath(xpath));
		navegar.sendKeys(Keys.ARROW_DOWN);
		navegar = driver.findElement(By.xpath(xpath));
		navegar.sendKeys(Keys.ENTER);
		/// html/body/div[6]/div/div[1]
		// 1) esperar por la class GNOAMXJCOHB (dropdown)
		// 2) esperar por la clase GNOAMXJCIG
		for (int i = 0; i < 5; i++) {
			xpath = "//div[@id='CMM_RecStartTimeField_Id']/div/input";
			w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).click();
			//este sirve pero no recuerdocual es
			//xpath = "/html/body/div[2]/div[2]/div/div[3]/div/div/div[4]/div/div/div[2]/div[2]/div[2]/table/tbody/tr/td[3]/div/div/input";
			// en teoria es toda la clase del dropdown
			driver.findElements(By.className("GNOAMXJCIG"));
			navegar = driver.findElement(By.xpath(xpath));
			System.out.println("Navegar hora inicial-> " + navegar.getDomProperty("value"));
			navegar.sendKeys(Keys.ARROW_DOWN);
			System.out.println("navegar value despues del arrow down" + navegar.getDomProperty("value"));
			navegar = driver.findElement(By.xpath(xpath));
			navegar.sendKeys(Keys.ENTER);
			System.out.println("navegar value despues del enter" + navegar.getDomProperty("value"));

			xpath = "//div[@id='CMM_RecEndTimeField_Id']/div/input";
			w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
			driver.findElement(By.xpath(xpath)).click();
			navegar = driver.findElement(By.xpath(xpath));
			System.out.println("Navegar hora final-> " + navegar.getText());
			navegar.sendKeys(Keys.ARROW_DOWN);
			navegar = driver.findElement(By.xpath(xpath));
			navegar.sendKeys(Keys.ENTER);
			Thread.sleep(100);
			if (i == 94) {
				corregirhorafinal(driver);
			}
			aplicarfiltro(driver);

		}

		// <div class="GNOAMXJCIG x-view-highlightrow GNOAMXJCKG">00:00</div>
		return driver;
	}

	public static void corregirhorafinal(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		// FILTROS -Hora
		String xpath = "//div[@id='CMM_RecEndTimeField_Id']/div/input";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		WebElement navegar = driver.findElement(By.xpath(xpath));
		for (int i = 0; i < 95; i++) {
			navegar.sendKeys(Keys.ARROW_UP);
			navegar = driver.findElement(By.xpath(xpath));
		}

		navegar.sendKeys(Keys.ENTER);
		Thread.sleep(100);
	}

	public static WebDriver aplicarfecha(WebDriver driver, int j) throws InterruptedException {

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		// FECHA CON OTRO METODO
		String xpath = "//input[@id='x-widget-308-input']";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		Thread.sleep(200);
		xpath = "//*[@id=\"x-menu-el-\"]/div";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		WebElement navegar = driver.findElement(By.xpath(xpath));
		navegar = driver.findElement(By.xpath(xpath));

		for (int i = 0; i < j; i++) {
			navegar.sendKeys(Keys.ARROW_LEFT);
			Thread.sleep(20);
		}
		navegar.sendKeys(Keys.ENTER);

		xpath = "//input[@id='x-widget-310-input']";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
		// Thread.sleep(200);

		xpath = "//*[@id=\"x-menu-el-\"]/div";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		navegar = driver.findElement(By.xpath(xpath));
		navegar.sendKeys(Keys.ARROW_LEFT);
		navegar.sendKeys(Keys.ENTER);
		return driver;
	}

	public static void aplicarfiltro(WebDriver driver) throws InterruptedException {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		// FILTROS - FILTRAR
		String xpath = "//button[@id='CMM_RecButtonApplyCriteria_Id']";
		w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		WebElement navegar = driver.findElement(By.xpath(xpath));
		navegar = driver.findElement(By.xpath(xpath));
		navegar.click();
		Thread.sleep(1000);

		// marcar
		// xpath="//*[@id=\"CMM_RecGrid_Id\"]/div[1]/div/table/tbody[2]/tr/td[1]/div";
		//xpath = "/html/body/div[2]/div[2]/div/div[3]/div/div/div[6]/div/div/div/div[2]/div[1]/div/table/tbody[2]/tr/td[1]/div";
		//List<WebElement> lista = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		List<WebElement> lista = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("GNOAMXJCFID")));
		
		System.out.print("La lista de llamadas es de tamaño: " + lista.size());
		if (!lista.isEmpty()) {
			driver.findElement(By.xpath(xpath)).click();
		}
		Thread.sleep(100);

		// descargar
		// *[@id="CMM_RecHeader_Id"]/div/div/div[4]/button
		xpath = "//*[@id=\"CMM_RecHeader_Id\"]/div/div/div[4]/button";
		lista.clear();
		lista = w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
		if (!lista.isEmpty()) {
			driver.findElement(By.xpath(xpath)).click();
		}

		Thread.sleep(100);
		
		
	}

	public static WebDriver abriracr(WebDriver driver, String user, String password) throws InterruptedException {

		try {

			// LOGiN
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
			w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#firstName-input")));
			System.out.println("Title of the page is -> " + driver.getTitle());
			WebElement userinput = driver.findElement(By.id("firstName-input"));
			userinput.sendKeys(user);
			WebElement passwordinput = driver.findElement(By.id("password-input"));
			passwordinput.sendKeys(password);
			WebElement boton = driver.findElement(By.cssSelector(".gwt-Button"));
			boton.click();
			Thread.sleep(100);

			// NAVEGAR
			// menu aplicaciones
			String xpath = new String();
			Actions action = new Actions(driver);

			xpath = "//div[@id='menu_primary_applications']";
			// w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("menu_primary_applications")));
			w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
			WebElement navegar = driver.findElement(By.id("menu_primary_applications"));
			System.out.println("Navegar -> " + navegar.getText());
			action.moveToElement(navegar).perform();
			Thread.sleep(100);

			// menu aplicacion, opci[on media manager
			xpath = "//div[@id='menu_item_media_manager']/span";
			w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
			// navegar = driver.findElement(By.xpath(xpath));
			navegar = driver.findElement(By.id("menu_item_media_manager"));
			System.out.println("Navegar -> " + navegar.getText());
			// action.moveToElement(navegar).perform();
			navegar.click();
			Thread.sleep(100);

			// dropbox del media manager
			xpath = "//input[@id='Media_Mgr_Menu-input']";
			w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
			navegar = driver.findElement(By.xpath(xpath));
			navegar.sendKeys(Keys.ARROW_DOWN);
			xpath = "/html/body/div[6]/div/div[4]";
			w.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
			navegar = driver.findElement(By.xpath(xpath));
			navegar.click();
			Thread.sleep(100);
			return driver;

		} finally {
			// driver.quit();
		}

	}

	public static WebDriver navegador(String navegador) {
		WebDriver driver = null;
		System.out.println("os.name: " + System.getProperty("os.name"));
		System.setProperty("webdriver.gecko.driver", "D:\\Proyectos\\BIM_ACR\\apps\\geckodriver.exe");
		String downloadFilepath = "D:\\Proyectos\\BIM_ACR\\zipped\\";
		String url = "https://fabc.ddns.net:7070";
		if (System.getProperty("file.separator") == "/") {
			// LINUX
			System.setProperty("webdriver.gecko.driver", "/avaya/apps/geckodriver");
			downloadFilepath = "/avaya/zipped";
			url = "https://172.30.24.11:7070";
		}
		if (navegador.equalsIgnoreCase("chrome")) {
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			ChromeOptions options = new ChromeOptions();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", downloadFilepath);
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setAcceptInsecureCerts(true);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			cap.setCapability("acceptSslCerts", "True");
			driver = new ChromeDriver(options);
		} else {
			FirefoxOptions firefoxoptions = new FirefoxOptions();
			firefoxoptions.setAcceptInsecureCerts(true);
			firefoxoptions.addPreference("browser.download.dir", downloadFilepath);
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			firefoxoptions.setBinary(firefoxBinary);
			driver = new FirefoxDriver(firefoxoptions);
		}

		System.out.println("Browser position: " + driver.manage().window().getPosition());
		System.out.println("Browser size: " + driver.manage().window().getSize());
		driver.manage().window().maximize();
		System.out.println("conectando a: " + url);
		driver.get(url);
		return driver;
	}

}