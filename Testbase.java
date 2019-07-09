package com.gmail.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.gmail.utils.ExcelReader;
import com.gmail.utils.TestUtil;
import com.gmail.utils.WebEventListener;

public class Testbase {

	public static WebDriver driver;
	public static Properties prop;
	public static ExcelReader excel;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public Testbase() {
		try {
			prop = new Properties();
			FileInputStream f = new FileInputStream(
					"C:\\New folder (2)\\Selenium Project\\GmailTest\\src\\main\\java\\com\\gmail\\config\\config.properties");
			prop.load(f);
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void initilization() {
		String browsername = prop.getProperty("chrome");
		if (prop.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"E:\\selenium\\firefox update driver\\geckodriver-v0.24.0-win32\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (prop.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"E:\\selenium\\chrome update driver virsion 75\\chromedriver_win32(1)\\chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Load chrome");

		}

		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLECIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}
}
