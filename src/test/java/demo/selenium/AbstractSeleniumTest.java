package demo.selenium;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.selenium.Selenium;

public class AbstractSeleniumTest {
  
  private static final String CHROME = "chrome";
  private static final String FIREFOX = "firefox";
  private static final String IE = "ie";
  private static final String OPERA = "opera";
  private static final String SAFARI = "safari";

  private static boolean runTests = Boolean.getBoolean("selenium.runTests");
  private static String serverHost = System.getProperty("selenium.host");
  private static Integer serverPort = Integer.getInteger("selenium.port");
  private static List<DesiredCapabilities> browsers = getCapabilities();

  private String baseUrl = "http://www.vt.edu/";
  private DesiredCapabilities browser;
  private WebDriver driver;
  private Selenium selenium;
	
  @Parameters
  public static Collection<Object[]> parameters() {
    List<Object[]> invocations = new ArrayList<Object[]>(browsers.size());
    for (Object obj : browsers.toArray()) {
      invocations.add(new Object[] { obj });
    }
    return invocations;
  }
  
	/**
   * Constructs a new instance.
   */
  public AbstractSeleniumTest(DesiredCapabilities browser) {
    this.browser = browser;
  }
  
  @Before
  public void setUp() {
    Assume.assumeTrue(runTests);
    Assume.assumeNotNull(serverHost, serverPort);
    try {
      System.out.println("Connecting to http://" + serverHost + ":" + serverPort);
      driver = new RemoteWebDriver(
          new URL("http://" + serverHost + ":" + serverPort + "/wd/hub"),
          browser
      );
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      selenium = new WebDriverBackedSelenium(driver, baseUrl);
    } catch (Exception e) {
      e.printStackTrace(System.err);
    }
  }
  
  @After
  public void tearDown() {
    if (driver != null) {
      driver.quit();
    }
  }
  
	protected Selenium getSelenium() {
		return selenium;
	}
	
	private static List<DesiredCapabilities> getCapabilities() {
	  List<DesiredCapabilities> capabilities = new ArrayList<DesiredCapabilities>();
	  String[] browsersWanted = System.getProperty("selenium.browsers").split(" ");
	  for (String browser : browsersWanted) {
	    browser = browser.toLowerCase().trim();
      if (browser.equals(CHROME)) {
        capabilities.add(DesiredCapabilities.chrome());
      }
      else if (browser.equals(FIREFOX)) {
        capabilities.add(DesiredCapabilities.firefox());
      }
      else if (browser.equals(IE)) {
        DesiredCapabilities abilities = DesiredCapabilities.internetExplorer();
        abilities.setCapability(
            InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
            true);
        capabilities.add(abilities);
      }
      else if (browser.equals(OPERA)) {
        capabilities.add(DesiredCapabilities.opera());
      }
      else if (browser.equals(SAFARI)) {
        capabilities.add(DesiredCapabilities.safari());
      }
	  }
	  return capabilities;
	}
	
}
