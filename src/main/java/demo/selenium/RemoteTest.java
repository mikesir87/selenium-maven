package demo.selenium;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteTest {

	public static void main(String[] args) {
		try {
			run(); 
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	private static void run() throws Exception {
		WebDriver driver = new RemoteWebDriver(
				new URL("http://172.31.142.1:4444/wd/hub"),
				DesiredCapabilities.firefox()
				);
		driver.get("http://www.google.com/");
		WebDriver augmentedDriver = new Augmenter().augment(driver);
        byte[] screenshot = ((TakesScreenshot)augmentedDriver).
                            getScreenshotAs(OutputType.BYTES);
        OutputStream os = new FileOutputStream("/Users/Developer/Desktop/screenshot.png");
        os.write(screenshot);
        os.close();
        driver.close();
	}
}
