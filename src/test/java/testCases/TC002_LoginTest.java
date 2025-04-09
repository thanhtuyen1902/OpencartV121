//SESSION 51:
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	//cùng để Master --> có thể dùng để chọn tất cả test case để chạy
	@Test(groups={"Sanity", "Master"})
	public void verify_login() {
		try {
			logger.info("***** Starting TC002_LoginTest *****");
			HomePage hp = new HomePage(driver);
			logger.info("Clicking on My Account");
			hp.clickMyAccount();
			logger.info("Clicking on Login");
			hp.clickLogin();
			LoginPage lp = new LoginPage(driver);
			logger.info("Providing email");
			//cắt khoảng trắng thừa
			lp.setMailAddress(p.getProperty("email").trim());
			
			logger.info("Providing password");
			System.out.println(p.getProperty("password"));
			lp.setPassword(p.getProperty("password").trim());
			
//			Thread.sleep(2000);
			
			logger.info("Clicking on login button");
			lp.clickLogin();
			MyAccountPage myaccount = new MyAccountPage(driver);
			logger.info("Validating login successfully!");
			if (myaccount.isMyAccountPageExists()) {
				Assert.assertTrue(true);
			}else {
				logger.error("Test failed");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
			//cach 2:
//			boolean targetPage = myaccount.isMyAccountPageExists();
//			Assert.assertEquals(targetPage, true, "Login failed");
			//cach 3:
			//Assert.assertTrue(targetPage);
			
		}catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC002_LoginTest *****");
	}
}
