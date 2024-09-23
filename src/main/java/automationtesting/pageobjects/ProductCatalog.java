package automationtesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	By byProducts = By.cssSelector(".mb-3");
	By addTocart = By.cssSelector(".card-body button:last-of-type");
	By toastCintainor = By.id("toast-container");
	public List<WebElement> getProducts() {
		waitForElementToAppearBy(byProducts);
		return products;
	}
	
	 public  WebElement getProductByName(String productName) {
		 waitForElementToAppearBy(byProducts);
		 WebElement prod = products.stream().filter(product-> 
		 product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		 return prod;
	 }
	 public void addProductToCart(String productName) {
		 WebElement prod = getProductByName(productName);
		 prod.findElement(addTocart).click();
		 waitForElementToAppearBy(toastCintainor);
		 waitForElementToDisappear(spinner);
	 }
	 
	 
	 //driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	

}
