package automationtesting.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import automationtesting.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LoginPage loginPage;
	public WebDriver initializeDriver() throws IOException {
		Properties propreties = new Properties();
		FileInputStream fil = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\automationtesting\\resources\\GlobalData.properties");
		propreties.load(fil);
		String browsername = System.getProperty("browser")!= null ? System.getProperty("browser") : propreties.getProperty("browser");
		//String browsername = propreties.getProperty("browser");
		if(browsername.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless")) {
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			//System.setProperty(("webdriver.edge.driver", browsername)
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filepath) throws IOException {
		// reading Json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath) , StandardCharsets.UTF_8);
		//to conver sring to hash map we need to get Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		
	}
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToLoginPage();
		return loginPage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	public String getScreenshot(String testCaseName, WebDriver Driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File fl = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, fl );
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

}
