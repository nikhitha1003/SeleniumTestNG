package automationtesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automationtesting.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement selectCountry;
	//@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	//WebElement selectCountry;
	@FindBy(css=".action__submit")
	WebElement submitCheckout;
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryValue;
	
	By searchCountryResults = By.cssSelector(".ta-results");
	 public void selectCountry(String Country) {
		 Actions a = new Actions(driver);
		 a.sendKeys(countryValue, Country).build().perform();
		 waitForElementToAppearBy(searchCountryResults);
		 selectCountry.click();
		 
	 }
	 public ConfirmationPage submitCheckOut() {
		 submitCheckout.click();
		 ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		 return confirmationPage;
	 }
	 
}
