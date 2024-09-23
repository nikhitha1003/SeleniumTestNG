package automationtesting.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationtesting.TestComponents.BaseTest;
import automationtesting.TestComponents.Retry;
import automationtesting.pageobjects.CartPage;
import automationtesting.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{
	@Test(groups = {"ErrorHandling"})
	public void loginFailErrorValidation() {
		loginPage.loginAppication("automationtest1@gmail.com", "Asdf1234");
		Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");
	}
	@Test(dataProvider = "getData", retryAnalyzer = Retry.class)
	public void productErrorValidation(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ProductCatalog productCatalog = loginPage.loginAppication("automationtest2@gmail.com","Asdf1234@");
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCart();
		Boolean match = cartPage.verifyCartItems(input.get("productName"));
		Assert.assertTrue(match);
	}
	@DataProvider
	public Object[][] getData() throws IOException {
		/*HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("email", "automationtest1@gmail.com");
		map.put("password", "Asdf1234@");
		map.put("productName", "IPHONE 13 PRO");
		
		HashMap<Object, Object> map1 = new HashMap<Object, Object>();
		map1.put("email", "automationtest1@gmail.com");
		map1.put("password", "Asdf1234@");
		map1.put("productName", "ZARA COAT 3");*/
		List<HashMap<String,String>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"\\src\\test\\java\\automationtesting\\Data\\PurchaseOrder.json");
		return new Object[][] { {data.get(0)} ,{data.get(1)} };
	}

}
