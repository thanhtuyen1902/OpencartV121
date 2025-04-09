package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//kế thừa class BasePage
public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="(//span[normalize-space()='My Account'])[1]")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="(//a[normalize-space()='Register'])[1]")
	WebElement lnkRegister;
	
	@FindBy(xpath="(//a[normalize-space()='Login'])[1]")	//Login link added in step5
	WebElement lnkLogin;
	
	public void clickMyAccount() {
//		lnkMyaccount.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", lnkMyaccount);
	}
	
	public void clickRegister() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", lnkRegister);
//		lnkRegister.click();
	}
	
	public void clickLogin() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", lnkLogin);
//		lnkLogin.click();
	}
	
}
