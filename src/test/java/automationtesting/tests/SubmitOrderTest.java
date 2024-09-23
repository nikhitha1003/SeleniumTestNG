package automationtesting.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import automationtesting.TestComponents.BaseTest;
import automationtesting.pageobjects.CartPage;
import automationtesting.pageobjects.CheckoutPage;
import automationtesting.pageobjects.ConfirmationPage;
import automationtesting.pageobjects.OrdersPage;
import automationtesting.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{
	//public String productName = "IPHONE 13 PRO";
	@Test(dataProvider = "getData", groups= {"Purchase"})
	public void standAloneTest(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		ProductCatalog productCatalog = loginPage.loginAppication(input.get("email"),input.get("password"));
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCart();
		Boolean match = cartPage.verifyCartItems(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitCheckOut();
		 
		String orderSucess = confirmationPage.orderStatus();
		Assert.assertTrue(orderSucess.equalsIgnoreCase("Thankyou for the order."));		
	}
	@Test(dependsOnMethods = {"standAloneTest"}, dataProvider = "getData", groups= {"Purchase"})
	public void OrderHistoryTest(HashMap<String,String> input) {
		ProductCatalog productCatalog = loginPage.loginAppication(input.get("email"),input.get("password"));
		OrdersPage Orderpage = productCatalog.goToOrders();
		Assert.assertTrue(Orderpage.verifyOrderDisplay(input.get("productName")));
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
