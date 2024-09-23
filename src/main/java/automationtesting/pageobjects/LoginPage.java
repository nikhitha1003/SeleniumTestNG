package automationtesting.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import automationtesting.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	/*WebElement userEmail = driver.findElement(By.id("userEmail"));
	 * WebElement password = driver.findElement(By.id("userPassword"));
	WebElement lognBtn = driver.findElement(By.id("login"));
	 */
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEamilEle;
	@FindBy(id="userPassword")
	WebElement passwordEle;
	@FindBy(id="login")
	WebElement lognBtn;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalog loginAppication(String email, String password) {
		userEamilEle.sendKeys(email);
		passwordEle.sendKeys(password);
		lognBtn.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
		
	}
	public String getErrorMessage() {
		waitForElementToAppearWebElement(errorMessage);
		return errorMessage.getText();
	}
	public void goToLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
}
