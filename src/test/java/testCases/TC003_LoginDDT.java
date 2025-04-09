//SESSION 51:
package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass{
	//vì class DataProviders ở 1 package khác --> phải import vào để dùng
	//nếu ở cùng class, hoặc cùng package --> không cần vế thứ 2
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="DataDriven")  //getting data provider from different class
	public void verify_loginDDT(String email, String pwd, String exp) {
		logger.info("***** Starting TC003_LoginDDT_Test *****");
		try {
			//HomePage
			logger.info("Going to HomePage");
			HomePage hp = new HomePage(driver);
			
			logger.info("Clicking on My Account");
			hp.clickMyAccount();
			
			logger.info("Clicking on Login");
			hp.clickLogin();
			//LoginPage
			LoginPage lp = new LoginPage(driver);
			
			logger.info("Providing email");
			lp.setMailAddress(email);
			
			logger.info("Providing password");
			lp.setPassword(pwd);
			
			logger.info("Clicking on login button");
			lp.clickLogin();
			
			logger.info("Validating login");
			//MyAccountPage
			MyAccountPage myaccount = new MyAccountPage(driver);
			boolean targetPage = myaccount.isMyAccountPageExists();
			
			/**
			 * Data is valid - login success - test pass - logout
			 * 				 - login failed - test fail - not need to logout
			 * 
			 * Data is invalid - login success - test fail - logout
			 * 				   - login failed - test pass - not need to logout
			 */
			
			if(exp.equalsIgnoreCase("Valid")) {
				if(targetPage==true) {
					myaccount.clickLogout();
					Assert.assertTrue(true);
				}else {
					Assert.assertTrue(false);
				}
			}else if (exp.equalsIgnoreCase("Invalid")) {
				if(targetPage==true) {
					myaccount.clickLogout();
					//assert should be after actions
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);
				}
			}
		}catch(Exception e) {
			Assert.fail();
		}
		logger.info("***** Finished TC003_LoginĐT_Test *****");
	}
}
