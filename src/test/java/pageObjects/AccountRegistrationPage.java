package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTele;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPwd;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPwd;
	
	@FindBy(xpath="//input[@id='input-newsletter']")
	WebElement switchNewsletter;
	
	@FindBy(xpath="//input[@value='0']")
	WebElement radiobtnNewsletter;
	
	@FindBy(xpath="(//input[@name='agree'])[1]")
	WebElement switchPolicy;
	
//	@FindBy(xpath="//button[normalize-space()='Continue']")
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	//do some validation of this message
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement mgsConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTele(String tele) {
		txtTele.sendKeys(tele);
	}
	public void setPassword(String pwd) {
		txtPwd.sendKeys(pwd);
	}
	public void setCfPassword(String cfpwd) {
		txtConfirmPwd.sendKeys(cfpwd);
	}
	public void setPrivacyPolicy() {
		//gặp lỗi:((
//		switchPolicy.click();
//		Actions act = new Actions(driver);
//		act.moveToElement(switchPolicy).click().perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", switchPolicy);
	}
	public void setNewsletter() {
//		switchNewsletter.click();
		radiobtnNewsletter.click();
	}
	public void clickContinue() {
		//sol1
//		btnContinue.click();
		
//		//sol2; if sol1 is not working
//		btnContinue.submit();
//		
//		//sol3
//		Actions act = new Actions(driver);
//		act.moveToElement(btnContinue).click().perform();
//		
//		//sol4-recommended
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", btnContinue);
//		
//		//sol5?? keyboard action -- tìm hiểu thêm
//		btnContinue.sendKeys(Keys.RETURN); 
//		
//		//sol6
//		WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();;
	}
	
	
	public String getConfirmationMsg() {
		try {
			return (mgsConfirmation.getText());
		}catch(Exception e) {
			return (e.getMessage());
		}
		
	}
	
}
