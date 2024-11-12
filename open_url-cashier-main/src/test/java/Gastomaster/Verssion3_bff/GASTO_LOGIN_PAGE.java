package Gastomaster.Verssion3_bff;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class GASTO_LOGIN_PAGE {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:udid", "RZ8TA16B23D"); // Update with your emulator/device UDID
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:appPackage", "com.android.chrome");
        capabilities.setCapability("appium:appActivity", "com.google.android.apps.chrome.Main");

        URL url = URI.create("http://127.0.0.1:4723/").toURL();
        driver = new AndroidDriver(url, capabilities);
        System.out.println("Browser opened.");
    }

    @Test
    public void USER_LOGIN_FUN() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        
        // Dismiss the sign-in prompt
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/signin_fre_dismiss_button"))).click();

        // Accept the notification permission
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/ack_button"))).click();

        // Navigate to the URL
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/search_box_text"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/url_bar"))).sendKeys("https://www.bff.cashier.lk");

        // Select the first suggestion in the Omnibox dropdown
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.android.chrome:id/omnibox_suggestions_dropdown']/android.view.ViewGroup[1]"))).click();
        Thread.sleep(15000);
        
        
        // Click "deny cookies" button
		        if (!driver.findElements(AppiumBy.xpath("//android.widget.Button[@text='deny cookies']")).isEmpty()) {
		        	
		            wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='deny cookies']"))).click();
		            System.out.println("'deny cookies' button not displayed, skipping click.");
		        } else {
		        	
		        	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='deny cookies']"))).click();
		            System.out.println("Clicked 'deny cookies' button");
		        }
		        
		        
		  Thread.sleep(10000);
        // Click on the ellipsis horizontal button (adjust XPath to match structure)
		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='ellipsis horizontal']"))).click();
		  
        // Click on the login button (assuming it's in German)
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='ANMELDEN']"))).click();
        // Enter email
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='email']"))).sendKeys("chathurashamikaindrguptha@gmail.com");
        // Enter password
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='main-content']/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.EditText"))).sendKeys("Epit#123");
        // Click to submit
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='main-content']/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]"))).click();
        Thread.sleep(5000);
        // Verify login success by checking if the user's dashboard is displayed (adjust ID based on your app)
        Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=' Profil']")).isDisplayed());
        System.out.println("Login Function - Pass");
    }

    /* Uncomment this section for user registration test
    @Test
    public void USER_REGISTER_FUN() throws InterruptedException {
        System.out.println("Register function test.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
        
        // Dismiss the sign-in prompt
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/signin_fre_dismiss_button"))).click();
        
        // Accept the notification permission
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/ack_button"))).click();
        
        // Navigate to the URL
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/search_box_text"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/url_bar"))).sendKeys("https://www.bff.cashier.lk");
        
        // Click on the first suggestion in the omnibox results
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.android.chrome:id/omnibox_suggestions_dropdown']/android.view.ViewGroup[1]"))).click();
       
        // Click the Datenshutz button for close the popup message
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='deny cookies']"))).click();
        
        // Click on the button with ion-icon "ellipsis-horizontal"
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[.//ion-icon[@name='ellipsis-horizontal']]"))).click();
        
        // Click on the login button
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='ANMELDEN']"))).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Konto erstellen']"))).click();
        
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("first-name"))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("first-name"))).sendKeys("NewUser 01");
    }
    */

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Browser closed.");
    }
}
