package automationtesting.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automationtesting.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlone {

	public static void main(String[] args) throws InterruptedException  {
		// TODO Auto-generated method stub
		String productName = "IPHONE 13 PRO";
		 WebDriverManager.chromedriver().setup();
		 
		 ChromeDriver driver = new ChromeDriver();
		 
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		 driver.manage().window().maximize();
		 driver.get("https://rahulshettyacademy.com/client");
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  new LoginPage(driver);
		 driver.findElement(By.id("userEmail")).sendKeys("automationtest1@gmail.com");
		 driver.findElement(By.id("userPassword")).sendKeys("Asdf1234@");
		 driver.findElement(By.id("login")).click();
		 
		 Thread.sleep(1000);
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card")));
		 List<WebElement> products = driver.findElements(By.cssSelector(".card"));
		 WebElement prod = products.stream().filter(product-> 
		 product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		 prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		 Thread.sleep(1000);
		 driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		 
		 List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart h3"));
		 Boolean match = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equalsIgnoreCase(productName));
		 Assert.assertTrue(match);
		 driver.findElement(By.cssSelector(".totalRow button")).click();
		 
		 Actions a = new Actions(driver);
		 a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "India").build().perform();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		 
		 driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		 //driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		 driver.findElement(By.cssSelector(".action__submit")).click();
		 
		 String orderSucess = driver.findElement(By.cssSelector(".hero-primary")).getText();
		 Assert.assertTrue(orderSucess.equalsIgnoreCase("Thankyou for the order."));
		 driver.close();
		 
	}

}
