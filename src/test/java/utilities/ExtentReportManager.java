//SESSION 52: STEP8

package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.activation.DataSource;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;
//phai hieu code no lam gi
public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	public void onStart(ITestContext testContext) {
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		//generate date in exact format
		String currentDateTimestamp = df.format(dt);
		*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		//tiện cho việc lưu trữ lịch sử mỗi lần chạy
		repName = "Test-Report-" + timeStamp + ".html";
		//specify location of the report
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		
		// Title of the report
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		// Name of the report
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		//common
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));	//current user of system
		extent.setSystemInfo("Environment", "QA");
		
		//through xml file
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includeGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includeGroups.toString());
		}
	}
	public void onTestSuccess(ITestResult result){
		test = extent.createTest(result.getTestClass().getName());
		//to display groups in method
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got successfully executed!");
	}
	
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName() + " got failed!");
		test.log(Status.INFO, result.getThrowable().getMessage());
		//viết try..catch bị lỗi :) (trong demo có try__catch)
		String imgPath = new BaseClass().captureScreen(result.getName());
		test.addScreenCaptureFromPath(imgPath);
		
		
		
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " got skipped!");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
		
		//options
		//mở báo cáo tự động
		String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" +repName;
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//tự động gửi email (tạm thời bỏ qua)
//		try {
//			URL url = new URL("file:///"+ System.getProperty("user.dir")+"\\reports\\"+ repName);
//			
//			//create the email message
//			ImageHtmlEmail email = new ImageHtmlEmail();
//			email.setDataSourceResolver(new DataSourceUrlResolver(url));
//			email.setHostName("smtp.googlemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("thuthu8a@gmail.com", "password"));
//			email.setSSLOnConnect(true);
//			email.setFrom("thuthu8a@gmail.com");	//sender
//			email.setSubject("Test Results");
//			email.setMsg("Please find Attached Report...");
//			email.addTo("thuthu8a@gmail.com");  //receiver
//			email.attach(url, "extent report", "please check report...");
//			email.send();	//send the email
//					
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
	}
	
	
}
