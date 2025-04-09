package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import freemarker.core.JavaScriptCFormat;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	//MyAccount Page heading
//	@FindBy(xpath="(//h1[normalize-space()='My Account'])[1]") WebElement msgHeading;
	@FindBy(xpath="(//h2[normalize-space()='My Account'])[1]") WebElement msgHeading;
	//adding in step 6
	@FindBy(xpath="(//a[@class='list-group-item'][normalize-space()='Logout'])[1]") WebElement lnkLogout;
	
	
	public boolean isMyAccountPageExists() {
		try {
			return (msgHeading.isDisplayed());
		}catch(Exception e) {
			return false;
		}
		
	}

	public void clickLogout() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", lnkLogout);
	}
}
