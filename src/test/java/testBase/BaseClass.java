package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
//chứa các hàm dùng lại nhiều lần
//reusable
public class BaseClass {
	//1:19:00 (session 52)***
	//để không tạo 2 driver khác nhau
	public static WebDriver driver;
	public Logger logger;	//Log4j
	public Properties p;
//	@BeforeClass(groups={"Sanity", "Regression", "Master"})
	@BeforeClass(groups={"Formal", "Master"})
	@Parameters({"os", "browser"})
	public void setup(String os, String br) throws IOException, URISyntaxException {
		//Loading config.properties file
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		
		logger = LogManager.getLogger(this.getClass()); //	log4j2
		
		//step10: (session53)
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}else {
				System.out.println("No matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {	//để đồng đều
				case "chrome": cap.setBrowserName("chrome"); break;
				case "firefox": cap.setBrowserName("MicrosoftEdge"); break;	
				case "edge": cap.setBrowserName("firefox"); break;	
				default: System.out.println("No matching browser.."); return;
			}
			
			URI uri = new URI("http://localhost:4444/wd/hub");
			URL url = uri.toURL();
			driver = new RemoteWebDriver(url, cap);
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(br.toLowerCase()) {	//để đồng đều
				case "chrome": driver = new ChromeDriver(); break;
				case "edge": driver = new EdgeDriver(); break;
				case "firefox": driver = new FirefoxDriver(); break;	
				default: System.out.println("Invalid browser name.."); return;
			}
		}
		
		
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.get("http://localhost/opencart/upload/");
		driver.get(p.getProperty("appURL2"));	//reading url from properties file
		driver.manage().window().maximize();
		
	}
	
	//@AfterClass(groups={"Sanity", "Regression", "Master", "DataDriven"})
	//bỏ DataDriven vì chạy lâu:vv (demo)
//	@AfterClass(groups={"Sanity", "Regression", "Master"})
	@AfterClass(groups={"Formal", "Master"}, alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	//các hàm dùng lại
	public String randomString(int length) {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    SecureRandom RANDOM = new SecureRandom();
	    if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
	}
	
	public String randomPassword(int length) {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		//StringBuilder giúp tối ưu hiệu suất so với String khi nối chuỗi
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	/**
        	 * Tạo số ngẫu nhiên trong khoảng từ 0 đến CHARACTERS.length() - 1.
        	 * Lấy ký tự tương ứng từ CHARACTERS và thêm vào password.
        	 */
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return password.toString();
	
	}
	//???
	//khi test fail --> chụp ảnh
	public String captureScreen(String tname){
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp;
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		//trả về vị trí ảnh đã chụp
		return targetFilePath;
		
		
	}
}
