package automationtesting.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import automationtesting.pageobjects.CartPage;
import automationtesting.pageobjects.OrdersPage;

public class AbstractComponent {
	WebDriver driver;
	//Constructor
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Webelements
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	//Methods
	public void waitForElementToAppearBy(By loactorinfo) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(loactorinfo));
		
	}
	public void waitForElementToAppearWebElement(WebElement loactorinfo) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(loactorinfo));
		
	}
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCart() throws InterruptedException {
		Thread.sleep(1000);
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrdersPage goToOrders() {
		orderHeader.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

}
