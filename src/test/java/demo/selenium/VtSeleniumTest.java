package demo.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.thoughtworks.selenium.Selenium;

@RunWith(value = Parameterized.class)
public class VtSeleniumTest extends AbstractRemoteSeleniumTest {

  /**
   * Constructs a new instance.
   * @param browser
   */
  public VtSeleniumTest(DesiredCapabilities browser) {
    super(browser);
  }

  @Test
	public void testVtSite() throws Exception {
	  Selenium selenium = getSelenium();
	  selenium.open("/");
    assertEquals("Virginia Tech | Invent the Future", selenium.getTitle());
    assertTrue(selenium.isTextPresent("About Virginia Tech"));
    selenium.click("link=About Virginia Tech");
    selenium.waitForPageToLoad("30000");
    assertEquals("About Virginia Tech | Virginia Tech Home | Virginia Tech", selenium.getTitle());
    assertTrue(selenium.isTextPresent("About the University"));
    selenium.click("link=About the University");
    selenium.waitForPageToLoad("30000");
    assertEquals("About the University | Virginia Tech Home | Virginia Tech", selenium.getTitle());
    assertTrue(selenium.isTextPresent("Background"));
	}
	
}
