package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BrowserFactory {
	static WebDriver driver;
	static String browser;
	static String url;

	public static void readingconfig() {
		// inputStrem//BufferedReader //FileReader// Scanner

		try {
			InputStream input = new FileInputStream(
					"C:\\Users\\diyor\\eclipse-workspace\\automation project\\automationjunit\\src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// http://www.techfios.com/billing/?ng=admin/

	public static WebDriver init() {
		readingconfig();
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\diyor\\eclipse-workspace\\automation project\\automationjunit\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\\\Users\\\\diyor\\\\eclipse-workspace\\\\automation project\\\\automationjunit\\\\driver\\\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

	public static boolean existeddata(String categoryname, List<String> actulelist) {
		for (int i = 0; i < actulelist.size(); i++) {
			if (categoryname.equalsIgnoreCase(actulelist.get(i))) {
				return true;
			}
		}
		return false;
	}

	public static boolean matchdata(List<String> exp, List<String> drop1) {
		boolean datamatch = false;
		for (int n = 0; n < drop1.size(); n++) {
			if (drop1.containsAll(exp) && exp.containsAll(drop1)) {
				datamatch = true;
			}
		}

		return datamatch;
	}
}
