package automationtesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;

import automationtesting.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent {
	WebDriver driver;
	@FindBy(css = "tr td:nth-child(3)")
	private List<WebElement> productsName;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public Boolean verifyOrderDisplay(String prductName) {
		Boolean match = productsName.stream().anyMatch(product -> product.getText().equalsIgnoreCase(prductName));
		return match;
	}

}
