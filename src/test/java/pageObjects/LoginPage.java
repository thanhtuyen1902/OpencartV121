package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}

	@FindBy(xpath="//input[@id='input-email']") WebElement txtMailAddress;
	@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
//	@FindBy(xpath="//button[normalize-space()='Login']") WebElement btnLogin;
	@FindBy(xpath="//input[@value='Login']") WebElement btnLogin;
	
	public void setMailAddress(String email) {
		txtMailAddress.sendKeys(email);
	}
	
	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btnLogin);
	}
}
