package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"Regression", "Master"})
	public void verify_account_registration() {
		logger.info("***** Starting TC001 AccountRegistrationTest ******");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("Clicked on MyAccount Link");
			hp.clickRegister();
			logger.info("Clicked on Register Link");
			AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
			logger.info("Providing customer details...");
			regpage.setFirstName(randomString(6).toUpperCase());
			regpage.setLastName(randomString(5).toUpperCase());
			regpage.setEmail(randomString(5) + "@gmail.com");	//randomly generated the email
			regpage.setTele("128364663");
			String pwd = randomPassword(6);
			regpage.setPassword(pwd);
			regpage.setCfPassword(pwd);
			logger.info("Setting privacy policy...");
			regpage.setPrivacyPolicy();
			logger.info("Clicking continue button...");
			regpage.clickContinue();
			logger.info("Validating expected message..");
			String confirm_mgs = regpage.getConfirmationMsg();
			if (confirm_mgs.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}else {
				logger.error("Test failed");
				logger.debug("Debug logs..");
				Assert.assertTrue(false);
			}
			//Assert.assertEquals(confirm_mgs, "Your Account Has Been Created!");
		}catch(Exception e) {
//			logger.error("Test failed");
//			logger.debug("Debug logs..");
			Assert.fail();
		}
		
		logger.info("***** Finished TC001 AccountRegistrationTest ******");
		
		
	}
	
	
	
}
