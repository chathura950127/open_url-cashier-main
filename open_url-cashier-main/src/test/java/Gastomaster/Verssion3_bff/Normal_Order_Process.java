package Gastomaster.Verssion3_bff;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
	
public class Normal_Order_Process{
	  private AndroidDriver driver;
	  private ExtentReports extent;
	  private ExtentTest test;
	  
	    @Before
	    
	    public void setUp() throws MalformedURLException, InterruptedException {
	    	
	    	File reportDir = new File("reports");
	    	if (!reportDir.exists()) {
	    	    reportDir.mkdir();  // Create the folder if it does not exist
	    	}
	    	 ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
	         extent = new ExtentReports();
	         extent.attachReporter(sparkReporter);	  
	         
	         // Start the test
	        test = extent.createTest("Normal_Order_Process (Postal Code)- Loging ", "Verifying Login process");
	        
	    	String systemUrl ="https://www.bff.cashier.lk";
	    	String userEmail = "chathurashamikaindrguptha@gmail.com";
	    	String userPassword ="Epit#123";
	    	
	        DesiredCapabilities capabilities = new DesiredCapabilities();
	        capabilities.setCapability("appium:udid", "RZ8TA16B23D");//change the your emulator id 
	        capabilities.setCapability("appium:automationName", "UiAutomator2");
	        capabilities.setCapability("platformName", "Android");
	        capabilities.setCapability("appium:appPackage", "com.android.chrome");
	        capabilities.setCapability("appium:appActivity", "com.google.android.apps.chrome.Main");

	        URL url = URI.create("http://127.0.0.1:4723/").toURL();
	        driver = new AndroidDriver(url, capabilities);
	        test.log(Status.INFO, "Browser opened.");
	        
	    try {  
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
	        
	        
			        // Dismiss the sign-in prompt
			        if (driver.findElements(AppiumBy.id("com.android.chrome:id/signin_fre_dismiss_button")).isEmpty()) {
			        	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/signin_fre_dismiss_button"))).click();
			        }
			        else {
			        	System.out.println("skip the Permission 01");
			        }
					        /*     
					        //Given the permission to Notification
					        if (driver.findElements(AppiumBy.id("com.android.chrome:id/positive_button")).isEmpty()) {
					        	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/positive_button"))).click();
					        }
					        else {
					        	System.out.println("skip the Permission");
					        }
					        
					        //given Notification Permission to mobile
					        if (driver.findElements(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")).isEmpty()) {
						        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button"))).click();
						    }
					        else {
					        	System.out.println("skip the Permission");
					        }
					         */ 
			        if (driver.findElements(AppiumBy.id("com.android.chrome:id/ack_button")).isEmpty()) {
				        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/ack_button"))).click();
			        }
			        else {
			        	System.out.println("skip the Permission 02");
			        }
			        
		           // Navigate to the URL
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/search_box_text"))).click();
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/url_bar"))).sendKeys(systemUrl);
	
		           // Select the first suggestion in the Omnibox dropdown
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.android.chrome:id/omnibox_suggestions_dropdown']/android.view.ViewGroup[1]"))).click();
		     
		           
		           
		           // Click "deny cookies" button
		   		        if (!driver.findElements(AppiumBy.xpath("//android.widget.Button[@text='deny cookies']")).isEmpty()) {
		   		            System.out.println("'deny cookies' button not displayed, skipping click.");
		   		        } else {
		   		        	
		   		        	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='deny cookies']"))).click();
		   		            System.out.println("System deny the cookies");
		   		        }
		   		        
		   		        
		   		  Thread.sleep(10000);
		           // Click on the ellipsis horizontal button (adjust XPath to match structure)
		   		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='ellipsis horizontal']"))).click();
		   		  
		           // Click on the login button (assuming it's in German)
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='ANMELDEN']"))).click();
		           
		           // Enter email
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id='email']"))).sendKeys(userEmail);
		           
		           // Enter password
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='main-content']/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.EditText"))).sendKeys(userPassword);
		          
		           // Click to submit
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='main-content']/android.view.View/android.view.View[2]/android.view.View/android.view.View[4]"))).click();
		           Thread.sleep(10000);
		           
		           // Verify login success by checking if the user's Dashboard is displayed (adjust ID based on your App)
		           Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=' Profil']")).isDisplayed());
		           test.log(Status.PASS, "User Login Process ");
		           
		           wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
		           Thread.sleep(5000);
		           
		    } catch (Exception error_login) {
	            test.log(Status.FAIL, "Login Funcation: " + error_login.getMessage());
	        }
	             
	    }
	    

	    
	    @Test
	    public void Normal_Order_Process_DELIVERY () throws InterruptedException {
	    //	String userEmail ="chathurashamikaindrguptha@gmail.com";
	    //	String Card_Number ="4242424242424242";
	   // 	String cardExpiry="0852";
	   // 	String cardCvc ="454";
	   // 	String billingName ="Chathura Shamika";
	    //	String PostalName = "Hessen";
	    	String PostalAddress ="Theo-Geisel-Straße 12Usingen, Germany";
	    	String StreetNumber ="12";
	    	
	    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25)); 
	    	   
	           
	         /*-----  CASH PAYMENET Process  -----*/
	    /*    try { 
	        	
	        	test = extent.createTest("Normal_Order_Process (Postal Code)- Delivery Order - Cash Payment", "  Verify The Cash Payment Process");
	    	 	System.out.println("Start -Normal_Order_Process (Postal Code)- Delivery Order - Cash Payment ");
	        	
	        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
		         System.out.println("Load the home page");
		         Thread.sleep(15000);
		         
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
		         System.out.println("select the Jetzt bestellen button");
		         Thread.sleep(3000);
		        
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
				 System.out.println("Select the delivery method and this is a postal mode");
				 Thread.sleep(2000);
						 try {
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
				         		System.out.println("system displayed the permission request message for locations");
				         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
							}
				         	catch(Exception error_of_location_access) {
				         		System.out.println("system did not display the permission request message for location pick");
				         	}
					
				 
				 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
				 System.out.println("click the Wahlen button");
				 Thread.sleep(5000);
				 
						 try {
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();
							 Thread.sleep(5000);
						 }
						 catch(Exception eroor_on_postal_code_page){
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
					         System.out.println("select the Jetzt bestellen button");
					         Thread.sleep(5000);
					        
					         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
							 System.out.println("Select the delivery method");
							 Thread.sleep(2000);
							
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
							 System.out.println("click the Wahlen button");
							 Thread.sleep(5000);
							
										try {
							         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
							         		System.out.println("system displayed the permission request message for locations");
							         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
										}
							         	catch(Exception error_of_location_access) {
							         		System.out.println("system did not display the permission request message for location pick");
							         	}
										
							         
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();		       
							 System.out.println("click the postal code input field");
							 Thread.sleep(5000);
						 };
				 
				
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).sendKeys(PostalName);
		         System.out.println("enter the postal code");
		         Thread.sleep(3000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View"))).click();
		         System.out.println("close the key board");
		         Thread.sleep(3000);
		         
		         
				         //check the system is display the branch list or not
				         	try {
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"FILIALEN\"]")));
				         		System.out.println("System displayed the branch list");
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"JETZT BESTELLEN\"])[1]"))).click();
				         		System.out.println("click the first branch for proceed the ordering function");
				         		Thread.sleep(5000);
				         	}
				         	catch(Exception error_of_branch_list) {
				         		System.out.print("System didn't display the branch list");
				         		test.log(Status.FAIL, "System didn't display the branch list for proceed the ordering process");
				         	};
		         
				         	
				         // check the favorite dishes enable or not, if it enable,System selected the first dishes.
					         try {
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Favorite\"]")));
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
						         System.out.println("Select the first item with in favorite list and open the toping section");
					        	 
					         }
					         catch(Exception Erro_of_Category) {
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
						         System.out.println("Select the first item and open the toping section");
					         }
					         
					         //check the toping and added the toping for item
				         
				         try {
				        	 	WebElement wizardButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"NEIN, ÜBERALL\"])")));
							       if(wizardButton.isDisplayed()) {
								        	       
						        	System.out.println("System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
								    test.log(Status.INFO, "System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
							       }
		
				        } catch (Exception error_of_Wizard) {
				        	  
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text=\"BBQ Chicken\"])[1]/android.widget.Image"))).click();
				        	        System.out.println("BBQ Chicken toping selected");
		
				        	        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click();
				        	        System.out.println("Item added to the cart");
				        	        Thread.sleep(2000);
				        }
				         
		
			     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
		         Thread.sleep(1000);
				 System.out.println("Open the cart page");
				 
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 15.29 €\"]"))).click(); //change the button name here
		         Thread.sleep(10000);
				         
		         
		         
				         //Check if the address section is empty; if it is empty, then add the address.
						      try {    
								 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
								
							         if (postaladdressElement.isDisplayed()) {
							        	
							        	 	//check the address is empty
									         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
									        	
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
									        	 	
									        	 			//check the street number is empty
													         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
													        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
													        	 	
												   		        } else {
												   		        	
												   		        	System.out.println("Street number is already defined.");
												   		        }
									         } else {
								   		        	
								   		        	System.out.println("Street address is already defined.");
								   		        }
									         
									         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
									         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
									         
									         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
							         }
							         else {
							        	 System.out.println("Address is already defined.");
							         }}
						      catch(Exception Error_addresscheck) {
						        		 
						        		 	System.out.println(Error_addresscheck);
						        	 }
						      
						      
						      
						   // Select if the "Cash" payment option 
					    	  try {
					    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
							         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
							         

					    		    // Check if the "Cash" payment option is displayed
					    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Cash\"]"))).click();
					    		        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Points\"]"))).click();
					    		        
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
					    		        Thread.sleep(1000);

					    		    } else {
					    		        // If the "Cash" option is already selected
					    		        System.out.println("Error on Payment selection.");
					    		    }
					    		} catch (Exception error_cash_payment_option) {
					    		    // Handle exceptions
					    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
					    		    error_cash_payment_option.printStackTrace();
					    		}
					    	  
					    	  //Verify the payment method
					    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Cash\"]")).isDisplayed()) {
					    		  test.log(Status.INFO , ("Payment Method : Cash"));
					    	  }
					    	  else {
					    		  test.log(Status.INFO , ("Invalid Payment Method"));
					    	  }

																	    	  try {
																		    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
																				      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
																		    	 }
																		    	 catch(Exception scroll_down) {
																		    		 System.out.println("An error occurred: " + scroll_down.getMessage());
																		    		 scroll_down.printStackTrace();
																		    	 }
									 	  
								//check the order process type									    	  
								try{
									WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
									String ordering_method = checkoutbutton_type.getText();
									test.log(Status.FAIL, "Your ordering process is " +ordering_method+"." );
									
									checkoutbutton_type.click();
									System.out.println("checkout button click");
								}
								catch(Exception checkou_button) {
									WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
									String ordering_method = checkoutbutton_type.getText();
									test.log(Status.FAIL, "Your ordering process is " +ordering_method+ ".  Please change the order process to General Order via admin mode");
									System.out.println("Your ordering process is " +ordering_method+".  Please change the order process to General Order via admin mode");
								};
								Thread.sleep(15000);
		       
								
		         //Check the URL is equal to /order-summery and order completed successfully 
		        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
		        	   Thread.sleep(15000);
		        	   System.out.println("Open the checkout page on final stage");
			        	    
		        	   if (urlBar.isDisplayed()) {
			        	    	
			        	        String currentUrl = urlBar.getText();
	
			        	        System.out.println(currentUrl);
			        	        
				        	        if (currentUrl.contains("order-summery")) {
		
				       	            String[] urlSegments = currentUrl.split("/");
		
				        	            String lastSegment = urlSegments[urlSegments.length - 1];
		
				        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
				        	            test.log(Status.PASS, "Normal Order Process - Delivery ( Cash ) ");
				        	            
				                } else {
				        	            System.out.println("System did not completed the order process");
				        	            test.log(Status.FAIL, "Normal Order Process - Delivery ( Cash ) ");}
				        	        
			        	    } 
			        	    else {
			        	        System.out.println("URL bar is not displayed.");
			        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
	        }
	        
		        catch (Exception error_OrderProcess) {
		            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
	    
*/

	         //-----  POINT Payment  -----
	    	   /* try { 
	     		test = extent.createTest("Normal_Order_Process (Postal Code)- Delivery Order - Point Payment", "  Verify The Point Payment Process");
		        System.out.println("Start -Normal_Order_Process (Postal Code)- Delivery Order - Point Payment ");
	        	test.log(Status.INFO, "Delivery method with Postal code mode");
	        	
	        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
		         System.out.println("Load the home page");
		         Thread.sleep(15000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
		         System.out.println("select the Jetzt bestellen button");
		         Thread.sleep(3000);
		        
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
				 System.out.println("Select the delivery method and this is a postal mode");
				 Thread.sleep(2000);
						 try {
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
				         		System.out.println("system displayed the permission request message for locations");
				         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
							}
				         	catch(Exception error_of_location_access) {
				         		System.out.println("system did not display the permission request message for location pick");
				         	}
					
				 
				 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
				 System.out.println("click the Wahlen button");
				 Thread.sleep(5000);
				 
						 try {
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();
							 Thread.sleep(5000);
						 }
						 catch(Exception eroor_on_postal_code_page){
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
					         System.out.println("select the Jetzt bestellen button");
					         Thread.sleep(5000);
					        
					         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
							 System.out.println("Select the delivery method");
							 Thread.sleep(2000);
							
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
							 System.out.println("click the Wahlen button");
							 Thread.sleep(5000);
							
										try {
							         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
							         		System.out.println("system displayed the permission request message for locations");
							         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
										}
							         	catch(Exception error_of_location_access) {
							         		System.out.println("system did not display the permission request message for location pick");
							         	}
										
							         
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();		       
							 System.out.println("click the postal code input field");
							 Thread.sleep(5000);
						 };
				 
				
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).sendKeys(PostalName);
		         System.out.println("enter the postal code");
		         Thread.sleep(3000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View"))).click();
		         System.out.println("close the key board");
		         Thread.sleep(3000);
		         
		         
				         //check the system is display the branch list or not
				         	try {
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"FILIALEN\"]")));
				         		System.out.println("System displayed the branch list");
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"JETZT BESTELLEN\"])[1]"))).click();
				         		System.out.println("click the first branch for proceed the ordering function");
				         		Thread.sleep(5000);
				         	}
				         	catch(Exception error_of_branch_list) {
				         		System.out.print("System didn't display the branch list");
				         		test.log(Status.FAIL, "System didn't display the branch list for proceed the ordering process");
				         	};
		         
				         	
				         // check the favorite dishes enable or not, if it enable,System selected the first dishes.
					         try {
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Favourite\"]")));
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
						         System.out.println("Select the first item with in favourite list and open the toping section");
					        	 
					         }
					         catch(Exception Erro_of_Category) {
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
						         System.out.println("Select the first item and open the toping section");
					         }
					         
					         //check the toping and added the toping for item
				         
				         try {
				        	 	WebElement wizardButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"NEIN, ÜBERALL\"])")));
							       if(wizardButton.isDisplayed()) {
								        	       
						        	System.out.println("System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
								    test.log(Status.INFO, "System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
							       }
		
				        } catch (Exception error_of_Wizard) {
				        	  
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text=\"BBQ Chicken\"])[1]/android.widget.Image"))).click();
				        	        System.out.println("BBQ Chicken topping selected");
		
				        	        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click();
				        	        System.out.println("Item added to the cart");
				        	        Thread.sleep(2000);
				        }
				         
		
			     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
		         Thread.sleep(1000);
				 System.out.println("Open the cart page");
				 
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 15.29 €\"]"))).click(); //change the button name here
		         Thread.sleep(10000);
				         
		         
		         
				         //Check if the address section is empty; if it is empty, then add the address.
						      try {    
								 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
								
							         if (postaladdressElement.isDisplayed()) {
							        	
							        	 	//check the address is empty
									         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
									        	
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
									        	 	
									        	 			//check the street number is empty
													         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
													        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
													        	 	
												   		        } else {
												   		        	
												   		        	System.out.println("Street number is already defined.");
												   		        }
									         } else {
								   		        	
								   		        	System.out.println("Street address is already defined.");
								   		        }
									         
									         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
									         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
									         
									         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
							         }
							         else {
							        	 System.out.println("Address is already defined.");
							         }}
						      catch(Exception Error_addresscheck) {
						        		 
						        		 	System.out.println(Error_addresscheck);
						        	 }
						      
					    
					      
						   // Select if the "Point" payment option 
					    	  try {
					    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
							         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
							         

					    		    // Check if the "Cash" payment option is displayed
					    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Points\"]"))).click();
					    		        
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
					    		        Thread.sleep(1000);

					    		    } else {
					    		        // If the "Cash" option is already selected
					    		        System.out.println("Error on Payment selection.");
					    		    }
					    		} catch (Exception error_cash_payment_option) {
					    		    // Handle exceptions
					    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
					    		    error_cash_payment_option.printStackTrace();
					    		}
					    	  
					    	  //Verify the payment method
					    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Points\"]")).isDisplayed()) {
					    		  test.log(Status.INFO , ("Payment Method : Points"));
					    	  }
					    	  else {
					    		  test.log(Status.INFO , ("Invalid Payment Method"));
					    	  }

					    	  
																	    	  try {
																		    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
																				      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
																		    	 }
																		    	 catch(Exception scroll_down) {
																		    		 System.out.println("An error occurred: " + scroll_down.getMessage());
																		    		 scroll_down.printStackTrace();
																		    	 }
									
																	    	  
								//check the order process type									    	  
								try{
									WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
									String ordering_method = checkoutbutton_type.getText();
									test.log(Status.FAIL, "Your ordering process is " +ordering_method+"." );
									
									checkoutbutton_type.click();
									System.out.println("checkout button click");
								}
								catch(Exception checkou_button) {
									WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
									String ordering_method = checkoutbutton_type.getText();
									test.log(Status.FAIL, "Your ordering process is " +ordering_method+ ".  Please change the order process to General Order via admin mode");
									System.out.println("Your ordering process is " +ordering_method+".  Please change the order process to General Order via admin mode");
								};
								Thread.sleep(15000);
		       
								
		         //Check the URL is equal to /order-summery and order completed successfully 
		        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
		        	   Thread.sleep(15000);
		        	   System.out.println("Open the checkout page on final stage");
			        	    
		        	   if (urlBar.isDisplayed()) {
			        	    	
			        	        String currentUrl = urlBar.getText();
	
			        	        System.out.println(currentUrl);
			        	        
				        	        if (currentUrl.contains("order-summery")) {
		
				       	            String[] urlSegments = currentUrl.split("/");
		
				        	            String lastSegment = urlSegments[urlSegments.length - 1];
		
				        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
				        	            test.log(Status.PASS, "Normal Order Process - Delivery ( Points ) ");
				        	            
				                } else {
				        	            System.out.println("System did not completed the order process");
				        	            test.log(Status.FAIL, "Normal Order Process - Delivery ( Points ) ");}
				        	        
			        	    } 
			        	    else {
			        	        System.out.println("URL bar is not displayed.");
			        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
	      } 
	        
		        catch (Exception error_OrderProcess) {
		            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
	       */
	   
	    
	         //PayPal payment   
	 /*
	  * 
	  *    	   
	    	   try { 
	    		   
	    		   	test = extent.createTest("Normal_Order_Process (Postal Code)- Delivery Order - PayPal Payment", "  Verify The PayPal Payment Process");
		        	System.out.println("Start -Normal_Order_Process (Postal Code)- Delivery Order - PayPal Payment ");
		        	test.log(Status.INFO, "Delivery method with Postal code mode");
		        	
		        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
			         System.out.println("Load the home page");
			         Thread.sleep(15000);
			         
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
			         System.out.println("select the Jetzt bestellen button");
			         Thread.sleep(3000);
			        
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
					 System.out.println("Select the delivery method and this is a postal mode");
					 Thread.sleep(2000);
							 try {
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
					         		System.out.println("system displayed the permission request message for locations");
					         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
								}
					         	catch(Exception error_of_location_access) {
					         		System.out.println("system did not display the permission request message for location pick");
					         	}
						
					 
					 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
					 System.out.println("click the Wahlen button");
					 Thread.sleep(5000);
					 
							 try {
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();
								 Thread.sleep(5000);
							 }
							 catch(Exception eroor_on_postal_code_page){
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
						         System.out.println("select the Jetzt bestellen button");
						         Thread.sleep(5000);
						        
						         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
								 System.out.println("Select the delivery method");
								 Thread.sleep(2000);
								
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
								 System.out.println("click the Wahlen button");
								 Thread.sleep(5000);
								
											try {
								         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
								         		System.out.println("system displayed the permission request message for locations");
								         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
											}
								         	catch(Exception error_of_location_access) {
								         		System.out.println("system did not display the permission request message for location pick");
								         	}
											
								         
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();		       
								 System.out.println("click the postal code input field");
								 Thread.sleep(5000);
							 };
					 
					
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).sendKeys(PostalName);
			         System.out.println("enter the postal code");
			         Thread.sleep(3000);
			         
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View"))).click();
			         System.out.println("close the key board");
			         Thread.sleep(3000);
			         
			         
					         //check the system is display the branch list or not
					         	try {
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"FILIALEN\"]")));
					         		System.out.println("System displayed the branch list");
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"JETZT BESTELLEN\"])[1]"))).click();
					         		System.out.println("click the first branch for proceed the ordering function");
					         		Thread.sleep(5000);
					         	}
					         	catch(Exception error_of_branch_list) {
					         		System.out.print("System didn't display the branch list");
					         		test.log(Status.FAIL, "System didn't display the branch list for proceed the ordering process");
					         	};
			         
					         	
					         // check the favorite dishes enable or not, if it enable,System selected the first dishes.
						         try {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Favourite\"]")));
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item with in favourite list and open the toping section");
						        	 
						         }
						         catch(Exception Erro_of_Category) {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item and open the toping section");
						         }
						         
						         //check the toping and added the toping for item
					         
					         try {
					        	 	WebElement wizardButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"NEIN, ÜBERALL\"])")));
								       if(wizardButton.isDisplayed()) {
									        	       
							        	System.out.println("System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
									    test.log(Status.INFO, "System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
								       }
			
					        } catch (Exception error_of_Wizard) {
					        	  
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text=\"BBQ Chicken\"])[1]/android.widget.Image"))).click();
					        	        System.out.println("BBQ Chicken topping selected");
			
					        	        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click();
					        	        System.out.println("Item added to the cart");
					        	        Thread.sleep(2000);
					        }
					         
			
				     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
			         Thread.sleep(1000);
					 System.out.println("Open the cart page");
					 
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 15.29 €\"]"))).click(); //change the button name here
			         Thread.sleep(10000);
					         
			         
			         
					         //Check if the address section is empty; if it is empty, then add the address.
							      try {    
									 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
									
								         if (postaladdressElement.isDisplayed()) {
								        	
								        	 	//check the address is empty
										         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
										        	
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
										        	 	
										        	 			//check the street number is empty
														         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
														        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
														        	 	
													   		        } else {
													   		        	
													   		        	System.out.println("Street number is already defined.");
													   		        }
										         } else {
									   		        	
									   		        	System.out.println("Street address is already defined.");
									   		        }
										         
										         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
										         
										         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
								         }
								         else {
								        	 System.out.println("Address is already defined.");
								         }}
							      catch(Exception Error_addresscheck) {
							        		 
							        		 	System.out.println(Error_addresscheck);
							        	 }
							      
						    
						      
							   // Select if the "PayPal" payment option 
						    	  try {
						    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
								         

						    		    // Check if the "Cash" payment option is displayed
						    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
						    		        Thread.sleep(1000);

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"PayPal\"]"))).click();
						    		        
						    		        Thread.sleep(1000);

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
						    		        Thread.sleep(1000);

						    		    } else {
						    		        // If the "PayPal" option is already selected
						    		        System.out.println("Error on Payment selection.");
						    		    }
						    		} catch (Exception error_cash_payment_option) {
						    		    // Handle exceptions
						    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
						    		    error_cash_payment_option.printStackTrace();
						    		}
						    	  
						    	  //Verify the payment method
						    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  PayPal\"]")).isDisplayed()) {
						    		  test.log(Status.INFO , ("Payment Method : PayPal"));
						    	  }
						    	  else {
						    		  test.log(Status.INFO , ("Invalid Payment Method"));
						    	  }

						    	  
																		    	  try {
																			    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
																					      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
																			    	 }
																			    	 catch(Exception scroll_down) {
																			    		 System.out.println("An error occurred: " + scroll_down.getMessage());
																			    		 scroll_down.printStackTrace();
																			    	 }
										
																		    	  
									//check the order process type									    	  
									try{
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+"." );
										
										checkoutbutton_type.click();
										System.out.println("checkout button click");
									}
									catch(Exception checkou_button) {
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+ ".  Please change the order process to General Order via admin mode");
										System.out.println("Your ordering process is " +ordering_method+".  Please change the order process to General Order via admin mode");
									};
									Thread.sleep(15000);
			       
									
								//adding the PayPal account details for pay	
									try {
										WebElement PayPal_Logo = driver.findElement(AppiumBy.xpath("//android.widget.Image[@text=\"PayPal Logo\"]"));
										String PayPal_Logo_Text = PayPal_Logo.getText();
										test.log(Status.FAIL, "Your Payment process is "+PayPal_Logo_Text+"." );
									
											wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"email\"]"))).click();
											wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"email\"]"))).sendKeys("sb-j59s630900969@business.example.com");										
											
											wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"btnNext\"]"))).click();
						    		        Thread.sleep(1000);
											
						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"))).click();
											wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"))).sendKeys("g*Y%vSs3");										
											
											wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"btnLogin\"]"))).click();
											Thread.sleep(1000);
											
											wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"payment-submit-btn\"]"))).click();
											Thread.sleep(2000);
						    		        
						    		        
						    		      
									}
									catch(Exception error_of_PayPal) {
										System.out.println("PayPal Payment process can't proceed..!,");
										System.out.println("Please Check payment configuration on your admin portal or sandbox option should enable for run this scenario");
										
										test.log(Status.WARNING, "Please Check payment configuration on your admin portal or sandbox option should enable for run this scenario");
										test.log(Status.FAIL, "PayPal Payment process can't proceed..!");
										}
									
									
									
									
									
									
			         //Check the URL is equal to /order-summery and order completed successfully 
			        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
			        	   Thread.sleep(15000);
			        	   System.out.println("Open the checkout page on final stage");
				        	    
			        	   if (urlBar.isDisplayed()) {
				        	    	
				        	        String currentUrl = urlBar.getText();
		
				        	        System.out.println(currentUrl);
				        	        
					        	        if (currentUrl.contains("order-summery")) {
			
					       	            String[] urlSegments = currentUrl.split("/");
			
					        	            String lastSegment = urlSegments[urlSegments.length - 1];
			
					        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
					        	            test.log(Status.PASS, "Normal Order Process - Delivery ( PayPal ) ");
					        	            
					                } else {
					        	            System.out.println("System did not completed the order process");
					        	            test.log(Status.FAIL, "Normal Order Process - Delivery ( PayPal ) ");}
					        	        
				        	    } 
				        	    else {
				        	        System.out.println("URL bar is not displayed.");
				        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
		        }
		        
			        catch (Exception error_OrderProcess) {
			            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
		      
	    */	   
	        
	        
	    // STRIPE payment
	
	   /*
	        try { 
	    		   
	    		   	test = extent.createTest("Normal_Order_Process (Postal Code)- Delivery Order - Stripe Payment", "  Verify The Stripe Payment Process");
		        	System.out.println("Start -Normal_Order_Process (Postal Code)- Delivery Order - Stripe Payment ");
		        	test.log(Status.INFO, "Delivery method with Postal code mode");
		        	
		        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
			         System.out.println("Load the home page");
			         Thread.sleep(15000);
			         
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
			         System.out.println("select the Jetzt bestellen button");
			         Thread.sleep(3000);
			        
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
					 System.out.println("Select the delivery method and this is a postal mode");
					 Thread.sleep(2000);
							 try {
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
					         		System.out.println("system displayed the permission request message for locations");
					         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
								}
					         	catch(Exception error_of_location_access) {
					         		System.out.println("system did not display the permission request message for location pick");
					         	}
						
					 
					 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
					 System.out.println("click the Wahlen button");
					 Thread.sleep(5000);
					 
							 try {
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();
								 Thread.sleep(5000);
							 }
							 catch(Exception eroor_on_postal_code_page){
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
						         System.out.println("select the Jetzt bestellen button");
						         Thread.sleep(5000);
						        
						         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Lieferung\"])"))).click();
								 System.out.println("Select the delivery method");
								 Thread.sleep(2000);
								
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
								 System.out.println("click the Wahlen button");
								 Thread.sleep(5000);
								
											try {
								         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/text\"]")));
								         		System.out.println("system displayed the permission request message for locations");
								         		test.log(Status.INFO, "System request the permission for location ,please enable the location permission with your mobile setting and run this script againa..!");
											}
								         	catch(Exception error_of_location_access) {
								         		System.out.println("system did not display the permission request message for location pick");
								         	}
											
								         
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).click();		       
								 System.out.println("click the postal code input field");
								 Thread.sleep(5000);
							 };
					 
					
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).sendKeys(PostalName);
			         System.out.println("enter the postal code");
			         Thread.sleep(3000);
			         
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View"))).click();
			         System.out.println("close the key board");
			         Thread.sleep(3000);
			         
			         
					         //check the system is display the branch list or not
					         	try {
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"FILIALEN\"]")));
					         		System.out.println("System displayed the branch list");
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"JETZT BESTELLEN\"])[1]"))).click();
					         		System.out.println("click the first branch for proceed the ordering function");
					         		Thread.sleep(5000);
					         	}
					         	catch(Exception error_of_branch_list) {
					         		System.out.print("System didn't display the branch list");
					         		test.log(Status.FAIL, "System didn't display the branch list for proceed the ordering process");
					         	};
			         
					         	
					         // check the favorite dishes enable or not, if it enable,System selected the first dishes.
						         try {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Favourite\"]")));
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item with in favourite list and open the toping section");
						        	 
						         }
						         catch(Exception Erro_of_Category) {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item and open the toping section");
						         }
						         
						         //check the toping and added the toping for item
					         
					         try {
					        	 	WebElement wizardButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"NEIN, ÜBERALL\"])")));
								       if(wizardButton.isDisplayed()) {
									        	       
							        	System.out.println("System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
									    test.log(Status.INFO, "System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
								       }
			
					        } catch (Exception error_of_Wizard) {
					        	  
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text=\"BBQ Chicken\"])[1]/android.widget.Image"))).click();
					        	        System.out.println("BBQ Chicken topping selected");
			
					        	        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click();
					        	        System.out.println("Item added to the cart");
					        	        Thread.sleep(2000);
					        }
					         
			
				     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
			         Thread.sleep(1000);
					 System.out.println("Open the cart page");
					 
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 15.29 €\"]"))).click(); //change the button name here
			         Thread.sleep(10000);
					         
			         
			         
			         
			         
					         //Check if the address section is empty; if it is empty, then add the address.
							      try {    
									 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
									
								         if (postaladdressElement.isDisplayed()) {
								        	
								        	 	//check the address is empty
										         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
										        	
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
										        	 	
										        	 			//check the street number is empty
														         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
														        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
														        	 	
													   		        } else {
													   		        	
													   		        	System.out.println("Street number is already defined.");
													   		        }
										         } else {
									   		        	
									   		        	System.out.println("Street address is already defined.");
									   		        }
										         
										         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
										         
										         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
								         }
								         else {
								        	 System.out.println("Address is already defined.");
								         }}
							      catch(Exception Error_addresscheck) {
							        		 
							        		 	System.out.println(Error_addresscheck);
							        	 }
							      
						    
						      
							   // Select if the "Point" payment option 
						    	  try {
						    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
								         

						    		    // Check if the "Cash" payment option is displayed
						    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
						    		        Thread.sleep(1000);

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Stripe\"]"))).click();
						    		        
						    		        Thread.sleep(1000);

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
						    		        Thread.sleep(1000);

						    		    } else {
						    		        // If the "PayPal" option is already selected
						    		        System.out.println("Error on Payment selection.");
						    		    }
						    		} catch (Exception error_cash_payment_option) {
						    		    // Handle exceptions
						    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
						    		    error_cash_payment_option.printStackTrace();
						    		}
						    	
						    	  
						    	  //Verify the payment method
						    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Stripe\"]")).isDisplayed()) {
						    		  test.log(Status.INFO , ("Payment Method : Stripe"));
						    	  }
						    	  else {
						    		  test.log(Status.INFO , ("Invalid Payment Method"));
						    	  }

						    	  
																		    	  try {
																			    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
																					      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
																			    	 }
																			    	 catch(Exception scroll_down) {
																			    		 System.out.println("An error occurred: " + scroll_down.getMessage());
																			    		 scroll_down.printStackTrace();
																			    	 }
										
																		    	  
									//check the order process type									    	  
									try{
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+"." );
										
										checkoutbutton_type.click();
										System.out.println("checkout button click");
									}
									catch(Exception checkou_button) {
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+ ".  Please change the order process to General Order via admin mode");
										System.out.println("Your ordering process is " +ordering_method+".  Please change the order process to General Order via admin mode");
									};
									Thread.sleep(15000);
			       
									
								//adding the Stripe account details for pay	
									try {
										
										WebElement Provied_by = driver.findElement(AppiumBy.xpath("//android.widget.Image[@text=\"Stripe\"]"));
										String Provied_by_Text = Provied_by.getText();
										test.log(Status.FAIL, "Your Payment process is "+Provied_by_Text+"." );
									
											
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"email\"]"))).click();
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"email\"]"))).sendKeys(userEmail);										
												
										
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"cardNumber\"]"))).click();
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"cardNumber\"]"))).sendKeys(Card_Number);
							    		      
												
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"cardExpiry\"]"))).click();
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"cardExpiry\"]"))).sendKeys(cardExpiry);
												
												
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"cardCvc\"]"))).click();
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"cardCvc\"]"))).sendKeys(cardCvc);
												
												
							    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"billingName\"]"))).click();
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"billingName\"]"))).sendKeys(billingName);
												
											
												wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Pay\"]"))).click();
												Thread.sleep(15000);
												
						    		        
									}
									catch(Exception error_of_stripe) {
										System.out.println("Stripe Payment process can't proceed..!,");
										System.out.println("Please Check payment configuration on your admin portal or sandbox option should enable for run this scenario");
										
										test.log(Status.WARNING, "Please Check payment configuration on your admin portal or sandbox option should enable for run this scenario");
										test.log(Status.FAIL, "Stripe Payment process can't proceed..!");
										}
									
									
									
									
			         //Check the URL is equal to /order-summery and order completed successfully 
			        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
			        	   Thread.sleep(15000);
			        	   System.out.println("Open the checkout page on final stage");
				        	    
			        	   if (urlBar.isDisplayed()) {
				        	    	
				        	        String currentUrl = urlBar.getText();
		
				        	        System.out.println(currentUrl);
				        	        
					        	        if (currentUrl.contains("order-summery")) {
			
					       	            String[] urlSegments = currentUrl.split("/");
			
					        	            String lastSegment = urlSegments[urlSegments.length - 1];
			
					        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
					        	            test.log(Status.PASS, "Normal Order Process - Delivery ( Stripe ) ");
					        	            
					                } else {
					        	            System.out.println("System did not completed the order process");
					        	            test.log(Status.FAIL, "Normal Order Process - Delivery ( Stripe ) ");}
					        	        
				        	    } 
				        	    else {
				        	        System.out.println("URL bar is not displayed.");
				        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
		        }
		        
			        catch (Exception error_OrderProcess) {
			            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
		      
	        
	        */
	    
	    // END Delivery Order Method
	    
	    
	  //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	  //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	  //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
	    
	    // Start DINE-IN Order Method
	  
	           
	         /*-----  CASH PAYMENET Process  -----*/
	/*    
	    	   try { 
		        	
		        	test = extent.createTest("Normal_Order_Process (Postal Code)- DINE_IN Order - Cash Payment", "  Verify The Cash Payment Process");
		    	 	System.out.println("Start -Normal_Order_Process (Postal Code)- DINE_IN Order - Cash Payment ");
		        	
		        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
			         System.out.println("Load the home page");
			         Thread.sleep(15000);
			         
			         
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
			         System.out.println("select the Jetzt bestellen button");
			         Thread.sleep(3000);
			        
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
					 System.out.println("Select the Dine in");
					 Thread.sleep(2000);
							
					 
					 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
					 System.out.println("click the Wahlen button");
					 Thread.sleep(5000);
					 
							 try {
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
						         System.out.println("select the Jetzt bestellen button");
						         Thread.sleep(5000);
						        
						         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
								 System.out.println("Select the Dine in");
								 Thread.sleep(2000);
										
								 
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
								 System.out.println("click the Wahlen button");
								 Thread.sleep(5000);
								 }
							 
							 catch(Exception error_of_Dine_in) {
					         		System.out.print("Dine page error:" + error_of_Dine_in);
					         	};
			         
			        
					         //check the system is display the branch list or not
					         	try {
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"FILIALEN\"]")));
					         		System.out.println("System displayed the branch list");
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"JETZT BESTELLEN\"])[1]"))).click();
					         		System.out.println("click the first branch for proceed the ordering function");
					         		Thread.sleep(5000);
					         	}
					         	catch(Exception error_of_branch_list) {
					         		System.out.print("System didn't display the branch list");
					         		test.log(Status.FAIL, "System didn't display the branch list for proceed the ordering process");
					         	};
			         
					         	
					         // check the favorite dishes enable or not, if it enable,System selected the first dishes.
						         try {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Favorite\"]")));
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item with in favorite list and open the toping section");
						        	 
						         }
						         catch(Exception Erro_of_Category) {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item and open the toping section");
						         }
						         
						         //check the toping and added the toping for item
					         
					         try {
					        	 	WebElement wizardButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"NEIN, ÜBERALL\"])")));
								       if(wizardButton.isDisplayed()) {
									        	       
							        	System.out.println("System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
									    test.log(Status.INFO, "System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
								       }
			
					        } catch (Exception error_of_Wizard) {
					        	  
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text=\"BBQ Chicken\"])[1]/android.widget.Image"))).click();
					        	        System.out.println("BBQ Chicken toping selected");
			
					        	        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click();
					        	        System.out.println("Item added to the cart");
					        	        Thread.sleep(2000);
					        }
					         
			
				     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
			         Thread.sleep(1000);
					 System.out.println("Open the cart page");
					 
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
			         Thread.sleep(10000);
					         
			         
			         
					         //Check if the address section is empty; if it is empty, then add the address.
							      try {    
									 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
									
								         if (postaladdressElement.isDisplayed()) {
								        	
								        	 	//check the address is empty
										         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
										        	
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
										        	 	
										        	 			//check the street number is empty
														         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
														        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
														        	 	
													   		        } else {
													   		        	
													   		        	System.out.println("Street number is already defined.");
													   		        }
										         } else {
									   		        	
									   		        	System.out.println("Street address is already defined.");
									   		        }
										         
										         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
										         
										         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
								         }
								         else {
								        	 System.out.println("Address is already defined.");
								         }}
							      catch(Exception Error_addresscheck) {
							        		 
							        		 	System.out.println(Error_addresscheck);
							        	 }
							      
							      
							      
							   // Select if the "Cash" payment option 
						    	  try {
						    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
								         

						    		    // Check if the "Cash" payment option is displayed
						    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
						    		        Thread.sleep(1000);

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Cash\"]"))).click();
						    		        //wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Points\"]"))).click();
						    		        
						    		        Thread.sleep(1000);

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
						    		        Thread.sleep(1000);

						    		    } else {
						    		        // If the "Cash" option is already selected
						    		        System.out.println("Error on Payment selection.");
						    		    }
						    		} catch (Exception error_cash_payment_option) {
						    		    // Handle exceptions
						    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
						    		    error_cash_payment_option.printStackTrace();
						    		}
						    	  
						    	  //Verify the payment method
						    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Cash\"]")).isDisplayed()) {
						    		  test.log(Status.INFO , ("Payment Method : Cash"));
						    	  }
						    	  else {
						    		  test.log(Status.INFO , ("Invalid Payment Method"));
						    	  }

						    	  
																		    	  try {
																			    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
																					      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
																			    	 }
																			    	 catch(Exception scroll_down) {
																			    		 System.out.println("An error occurred: " + scroll_down.getMessage());
																			    		 scroll_down.printStackTrace();
																			    	 }
										
																		    	  
									//check the order process type									    	  
									try{
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+"." );
										
										checkoutbutton_type.click();
										System.out.println("checkout button click");
									}
									catch(Exception checkou_button) {
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+ ".  Please change the order process to General Order via admin mode");
										System.out.println("Your ordering process is " +ordering_method+".  Please change the order process to General Order via admin mode");
									};
									Thread.sleep(15000);
			       
									
			         //Check the URL is equal to /order-summery and order completed successfully 
			        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
			        	   Thread.sleep(15000);
			        	   System.out.println("Open the checkout page on final stage");
				        	    
			        	   if (urlBar.isDisplayed()) {
				        	    	
				        	        String currentUrl = urlBar.getText();
		
				        	        System.out.println(currentUrl);
				        	        
					        	        if (currentUrl.contains("order-summery")) {
			
					       	            String[] urlSegments = currentUrl.split("/");
			
					        	            String lastSegment = urlSegments[urlSegments.length - 1];
			
					        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
					        	            test.log(Status.PASS, "Normal Order Process - Dine_in ( Cash ) ");
					        	            
					                } else {
					        	            System.out.println("System did not completed the order process");
					        	            test.log(Status.FAIL, "Normal Order Process - Dine_in ( Cash ) ");}
					        	        
				        	    } 
				        	    else {
				        	        System.out.println("URL bar is not displayed.");
				        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
		        }
		        
			        catch (Exception error_OrderProcess) {
			            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
		    
*/

	         //-----  POINT Payment  -----
	   /*    try { 
		        	
		        	test = extent.createTest("Normal_Order_Process (Postal Code)- DINE_IN Order - Point Payment", "  Verify The Point Payment Process");
		    	 	System.out.println("Start -Normal_Order_Process (Postal Code)- DINE_IN Order - Point Payment ");
		        	
		        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
			         System.out.println("Load the home page");
			         Thread.sleep(15000);
			         
			         
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
			         System.out.println("select the Jetzt bestellen button");
			         Thread.sleep(3000);
			        
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
					 System.out.println("Select the Dine in");
					 Thread.sleep(2000);
							
					 
					 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
					 System.out.println("click the Wahlen button");
					 Thread.sleep(5000);
					 
							 try {
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
						         System.out.println("select the Jetzt bestellen button");
						         Thread.sleep(5000);
						        
						         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
								 System.out.println("Select the Dine in");
								 Thread.sleep(2000);
										
								 
								 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
								 System.out.println("click the Wahlen button");
								 Thread.sleep(5000);
								 }
							 
							 catch(Exception error_of_Dine_in) {
					         		System.out.print("Dine page error:" + error_of_Dine_in);
					         	};
			         
			        
					         //check the system is display the branch list or not
					         	try {
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"FILIALEN\"]")));
					         		System.out.println("System displayed the branch list");
					         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"JETZT BESTELLEN\"])[1]"))).click();
					         		System.out.println("click the first branch for proceed the ordering function");
					         		Thread.sleep(5000);
					         	}
					         	catch(Exception error_of_branch_list) {
					         		System.out.print("System didn't display the branch list");
					         		test.log(Status.FAIL, "System didn't display the branch list for proceed the ordering process");
					         	};
			         
					         	
					         // check the favorite dishes enable or not, if it enable,System selected the first dishes.
						         try {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Favorite\"]")));
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item with in favorite list and open the toping section");
						        	 
						         }
						         catch(Exception Erro_of_Category) {
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
							         System.out.println("Select the first item and open the toping section");
						         }
						         
						         //check the toping and added the toping for item
					         
					         try {
					        	 	WebElement wizardButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"NEIN, ÜBERALL\"])")));
								       if(wizardButton.isDisplayed()) {
									        	       
							        	System.out.println("System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
									    test.log(Status.INFO, "System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
								       }
			
					        } catch (Exception error_of_Wizard) {
					        	  
						        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text=\"BBQ Chicken\"])[1]/android.widget.Image"))).click();
					        	        System.out.println("BBQ Chicken toping selected");
			
					        	        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click();
					        	        System.out.println("Item added to the cart");
					        	        Thread.sleep(2000);
					        }
					         
			
				     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
			         Thread.sleep(1000);
					 System.out.println("Open the cart page");
					 
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
			         Thread.sleep(10000);
					         
			         
			         
					         //Check if the address section is empty; if it is empty, then add the address.
							      try {    
									 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
									
								         if (postaladdressElement.isDisplayed()) {
								        	
								        	 	//check the address is empty
										         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
										        	
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
										        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
										        	 	
										        	 			//check the street number is empty
														         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
														        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
														        	 	
													   		        } else {
													   		        	
													   		        	System.out.println("Street number is already defined.");
													   		        }
										         } else {
									   		        	
									   		        	System.out.println("Street address is already defined.");
									   		        }
										         
										         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
										         
										         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
								         }
								         else {
								        	 System.out.println("Address is already defined.");
								         }}
							      catch(Exception Error_addresscheck) {
							        		 
							        		 	System.out.println(Error_addresscheck);
							        	 }
							      
							      
							      
							   // Select if the "Point" payment option 
						    	  try {
						    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
								         

						    		    // Check if the "Point" payment option is displayed
						    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
						    		        Thread.sleep(1000);

						    		       // wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Cash\"]"))).click();
						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Points\"]"))).click();
						    		        
						    		        Thread.sleep(1000);

						    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
						    		        Thread.sleep(1000);

						    		    } else {
						    		        // If the "Point" option is already selected
						    		        System.out.println("Error on Payment selection.");
						    		    }
						    		} catch (Exception error_Point_payment_option) {
						    		    // Handle exceptions
						    		    System.out.println("An error occurred: " + error_Point_payment_option.getMessage());
						    		    error_Point_payment_option.printStackTrace();
						    		}
						    	  
						    	  //Verify the payment method
						    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Point\"]")).isDisplayed()) {
						    		  test.log(Status.INFO , ("Payment Method : Point"));
						    	  }
						    	  else {
						    		  test.log(Status.INFO , ("Invalid Payment Method"));
						    	  }

						    	  
																		    	  try {
																			    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
																					      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
																			    	 }
																			    	 catch(Exception scroll_down) {
																			    		 System.out.println("An error occurred: " + scroll_down.getMessage());
																			    		 scroll_down.printStackTrace();
																			    	 }
															    	  
									//check the order process type									    	  
									try{
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+"." );
										
										checkoutbutton_type.click();
										System.out.println("checkout button click");
									}
									catch(Exception checkou_button) {
										WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
										String ordering_method = checkoutbutton_type.getText();
										test.log(Status.FAIL, "Your ordering process is " +ordering_method+ ".  Please change the order process to General Order via admin mode");
										System.out.println("Your ordering process is " +ordering_method+".  Please change the order process to General Order via admin mode");
									};
									Thread.sleep(15000);
			       
									
			         //Check the URL is equal to /order-summery and order completed successfully 
			        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
			        	   Thread.sleep(15000);
			        	   System.out.println("Open the checkout page on final stage");
				        	    
			        	   if (urlBar.isDisplayed()) {
				        	    	
				        	        String currentUrl = urlBar.getText();
		
				        	        System.out.println(currentUrl);
				        	        
					        	        if (currentUrl.contains("order-summery")) {
			
					       	            String[] urlSegments = currentUrl.split("/");
			
					        	            String lastSegment = urlSegments[urlSegments.length - 1];
			
					        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
					        	            test.log(Status.PASS, "Normal Order Process - Dine_in ( Point ) ");
					        	            
					                } else {
					        	            System.out.println("System did not completed the order process");
					        	            test.log(Status.FAIL, "Normal Order Process - Dine_in ( Point ) ");}
					        	        
				        	    } 
				        	    else {
				        	        System.out.println("URL bar is not displayed.");
				        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
		        }
		        
			        catch (Exception error_OrderProcess) {
			            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
	  
	     
	     /*-----  PayPal PAYMENET Process  -----*/
	       try { 
    		   
	    	   test = extent.createTest("Normal_Order_Process (Postal Code)- DINE_IN Order - PayPal Payment", "  Verify The PayPal Payment Process");
	    	 	System.out.println("Start -Normal_Order_Process (Postal Code)- DINE_IN Order - PayPal Payment ");
	        	
	        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
		         System.out.println("Load the home page");
		         Thread.sleep(15000);
		         
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
		         System.out.println("select the Jetzt bestellen button");
		         Thread.sleep(3000);
		        
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
				 System.out.println("Select the Dine in");
				 Thread.sleep(2000);
						
				 
				 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
				 System.out.println("click the Wahlen button");
				 Thread.sleep(5000);
				 
						 try {
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
					         System.out.println("select the Jetzt bestellen button");
					         Thread.sleep(5000);
					        
					         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
							 System.out.println("Select the Dine in");
							 Thread.sleep(2000);
									
							 
							 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@text=\"Wählen\"])"))).click();
							 System.out.println("click the Wahlen button");
							 Thread.sleep(5000);
							 }
						 
						 catch(Exception error_of_Dine_in) {
				         		System.out.print("Dine page error:" + error_of_Dine_in);
				         	};
		         
		        
				         //check the system is display the branch list or not
				         	try {
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"FILIALEN\"]")));
				         		System.out.println("System displayed the branch list");
				         		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"JETZT BESTELLEN\"])[1]"))).click();
				         		System.out.println("click the first branch for proceed the ordering function");
				         		Thread.sleep(5000);
				         	}
				         	catch(Exception error_of_branch_list) {
				         		System.out.print("System didn't display the branch list");
				         		test.log(Status.FAIL, "System didn't display the branch list for proceed the ordering process");
				         	};
		         
				         	
				         // check the favorite dishes enable or not, if it enable,System selected the first dishes.
					         try {
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Favorite\"]")));
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
						         System.out.println("Select the first item with in favorite list and open the toping section");
					        	 
					         }
					         catch(Exception Erro_of_Category) {
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"scroll-to-class-0\"]/android.widget.ListView[1]/android.view.View"))).click();
						         System.out.println("Select the first item and open the toping section");
					         }
					         
					         //check the toping and added the toping for item
				         
				         try {
				        	 	WebElement wizardButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.Button[@text=\"NEIN, ÜBERALL\"])")));
							       if(wizardButton.isDisplayed()) {
								        	       
						        	System.out.println("System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
								    test.log(Status.INFO, "System displayed Wizard mode. Please change the wizard mode to default mode in the admin portal settings.");
							       }
		
				        } catch (Exception error_of_Wizard) {
				        	  
					        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text=\"BBQ Chicken\"])[1]/android.widget.Image"))).click();
				        	        System.out.println("BBQ Chicken toping selected");
		
				        	        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click();
				        	        System.out.println("Item added to the cart");
				        	        Thread.sleep(2000);
				        }
				         
		
			     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
		         Thread.sleep(1000);
				 System.out.println("Open the cart page");
				 
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
		         Thread.sleep(10000);
				      
		         
								         //Check if the address section is empty; if it is empty, then add the address.
										      try {    
												 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
												
											         if (postaladdressElement.isDisplayed()) {
											        	
											        	 	//check the address is empty
													         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
													        	
													        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
													        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
													        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
													        	 	
													        	 			//check the street number is empty
																	         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
																	        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
																	        	 	
																   		        } else {
																   		        	
																   		        	System.out.println("Street number is already defined.");
																   		        }
													         } else {
												   		        	
												   		        	System.out.println("Street address is already defined.");
												   		        }
													         
													         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
													         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
													         
													         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
											         }
											         else {
											        	 System.out.println("Address is already defined.");
											         }}
										      catch(Exception Error_addresscheck) {
										        		 
										        		 	System.out.println(Error_addresscheck);
										        	 }
										      
									
						   // Select if the "PayPal" payment option 
					    	  try {
					    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
							         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
							         

					    		    // Check if the "PayPal" payment option is displayed
					    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"PayPal\"]"))).click();
					    		        
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
					    		        Thread.sleep(1000);

					    		    } else {
					    		        // If the "PayPal" option is already selected
					    		        System.out.println("Error on Payment selection.");
					    		    }
					    		} catch (Exception error_cash_payment_option) {
					    		    // Handle exceptions
					    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
					    		    error_cash_payment_option.printStackTrace();
					    		}
					    	  
					    	  //Verify the payment method
					    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  PayPal\"]")).isDisplayed()) {
					    		  test.log(Status.INFO , ("Payment Method : PayPal"));
					    	  }
					    	  else {
					    		  test.log(Status.INFO , ("Invalid Payment Method"));
					    	  }

					    	  
																	    	  try {
																		    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
																				      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
																		    	 }
																		    	 catch(Exception scroll_down) {
																		    		 System.out.println("An error occurred: " + scroll_down.getMessage());
																		    		 scroll_down.printStackTrace();
																		    	 }
									
																	    	  
								//check the order process type									    	  
								try{
									WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
									String ordering_method = checkoutbutton_type.getText();
									test.log(Status.FAIL, "Your ordering process is " +ordering_method+"." );
									
									checkoutbutton_type.click();
									System.out.println("checkout button click");
								}
								catch(Exception checkou_button) {
									WebElement checkoutbutton_type = driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"));
									String ordering_method = checkoutbutton_type.getText();
									test.log(Status.FAIL, "Your ordering process is " +ordering_method+ ".  Please change the order process to General Order via admin mode");
									System.out.println("Your ordering process is " +ordering_method+".  Please change the order process to General Order via admin mode");
								};
								Thread.sleep(15000);
		       
								
							//adding the PayPal account details for pay	
								try {
									WebElement PayPal_Logo = driver.findElement(AppiumBy.xpath("//android.widget.Image[@text=\"PayPal Logo\"]"));
									String PayPal_Logo_Text = PayPal_Logo.getText();
									test.log(Status.FAIL, "Your Payment process is "+PayPal_Logo_Text+"." );
								
										wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"email\"]"))).click();
										wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"email\"]"))).sendKeys("sb-j59s630900969@business.example.com");										
										
										wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"btnNext\"]"))).click();
					    		        Thread.sleep(1000);
										
					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"))).click();
										wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"password\"]"))).sendKeys("g*Y%vSs3");										
										
										wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"btnLogin\"]"))).click();
										Thread.sleep(1000);
										
										wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"payment-submit-btn\"]"))).click();
										Thread.sleep(2000);
					    		        
					    		        
					    		      
								}
								catch(Exception error_of_PayPal) {
									System.out.println("PayPal Payment process can't proceed..!,");
									System.out.println("Please Check payment configuration on your admin portal or sandbox option should enable for run this scenario");
									
									test.log(Status.WARNING, "Please Check payment configuration on your admin portal or sandbox option should enable for run this scenario");
									test.log(Status.FAIL, "PayPal Payment process can't proceed..!");
									}
								
								
								
		         //Check the URL is equal to /order-summery and order completed successfully 
		        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
		        	   Thread.sleep(15000);
		        	   System.out.println("Open the checkout page on final stage");
			        	    
		        	   if (urlBar.isDisplayed()) {
			        	    	
			        	        String currentUrl = urlBar.getText();
	
			        	        System.out.println(currentUrl);
			        	        
				        	        if (currentUrl.contains("order-summery")) {
		
				       	            String[] urlSegments = currentUrl.split("/");
		
				        	            String lastSegment = urlSegments[urlSegments.length - 1];
		
				        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
				        	            test.log(Status.PASS, "Normal Order Process - Dine_in ( PayPal ) ");
				        	            
				                } else {
				        	            System.out.println("System did not completed the order process");
				        	            test.log(Status.FAIL, "Normal Order Process - Dine_in ( PayPal ) ");}
				        	        
			        	    } 
			        	    else {
			        	        System.out.println("URL bar is not displayed.");
			        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
	        }
	        
		        catch (Exception error_OrderProcess) {
		            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
	      

	         //-----  Stripe Payment  -----
	    	   /*        try { 
	        	
	        	 test = extent.createTest("Normal_Order_Process (Postal Code)- DINE-IN Order - Stripe Payment", "  Verify The Stripe Payment Process");
	        	 System.out.println("Start -Normal_Order_Process (Postal Code)- DINE-IN Order - Stripe Payment ");
	        	 
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"pricetag MENÜ\"]"))).click();
		         Thread.sleep(5000);
		         
				         try {
				        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Abholung\"]"))).click();
					         Thread.sleep(1000);
					         
					         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"fast food outline   Dine in\"]"))).click();
					         Thread.sleep(1000);
				         }catch(Exception Change_the_Order_Method) {
				        	 System.out.println("Already define the dinein order method"+Change_the_Order_Method);}
				         try {
				        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Lieferung\"]"))).click();
					         Thread.sleep(1000);
					         
					         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"fast food outline   Dine in\"]"))).click();
					         Thread.sleep(1000);
				         }catch(Exception Change_the_Order_Method) {
				        	 System.out.println("Already define the dinein order method"+Change_the_Order_Method);}
		         
				 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='scroll-to-class-0']/android.widget.ListView[1]/android.view.View"))).click();
				 Thread.sleep(1000);
				         
						     if (!driver.findElements(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")).isEmpty()) {
						    	 
						    		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text='Topping'])[1]/android.widget.Image"))).click();
						    	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click(); //change the button name here
				   		        } else {
				   		        	
				   		        	System.out.println("'In den Warenkorb (13.29 €)' button not displayed, skipping click.");
				   		        }
			   
			     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
		         Thread.sleep(1000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
		         Thread.sleep(10000);
				         
				         //Check if the address section is empty; if it is empty, then add the address.
						      try {    
								 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
								
							         if (postaladdressElement.isDisplayed()) {
							        	
							        	 	//check the address is empty
									         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
									        	
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
									        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
									        	 	
									        	 			//check the street number is empty
													         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
													        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
													        	 	
												   		        } else {
												   		        	
												   		        	System.out.println("Street number is already defined.");
												   		        }
									         } else {
								   		        	
								   		        	System.out.println("Street address is already defined.");
								   		        }
									         
									         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
									         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
									         
									         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
							         }
							         else {
							        	 System.out.println("Address is already defined.");
							         }}
						      catch(Exception Error_addresscheck) {
						        		 
						        		 	System.out.println(Error_addresscheck);
						        	 }
						      
						      
						   // Select if the "POINT" payment option 
					    	  try {
					    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
							         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
							         

					    		    // Check if the "Cash" payment option is displayed
					    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Points\"]"))).click();
					    		        Thread.sleep(1000);

					    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
					    		        Thread.sleep(1000);

					    		    } else {
					    		        // If the "Cash" option is already selected
					    		        System.out.println("Error on Payment selection.");
					    		    }
					    		} catch (Exception error_cash_payment_option) {
					    		    // Handle exceptions
					    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
					    		    error_cash_payment_option.printStackTrace();
					    		}
					    	  
					    	  //Verify the payment method
					    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Points\"]")).isDisplayed()) {
					    		  test.log(Status.INFO , ("Payment Method : Stripe"));
					    	  }
					    	  else {
					    		  test.log(Status.INFO , ("Invalid Payment Method"));
					    	  }
					    	  
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
		         Thread.sleep(15000);
		         
					         try {
					         		if (driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Aufladen\"]")).isDisplayed()) {
					         			System.out.println("Insufficient Point Balance, Please recharge your point balance and Run again");
					         			test.log(Status.INFO , ("Insufficient Point Balance, Please recharge your point balance and Run again"));
					         		}
					         }
					         catch(Exception error_message_Insufficient_Stripe_balance) {
					        	 System.out.println(error_message_Insufficient_Stripe_balance);
					         }
			       
		         //Check the URL is equal to /order-summery and order completed successfully 
		        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));

			        	    if (urlBar.isDisplayed()) {
			        	        String currentUrl = urlBar.getText();
	
				        	        if (currentUrl.contains("order-summery")) {
		
				        	            String[] urlSegments = currentUrl.split("/");
		
				        	            String lastSegment = urlSegments[urlSegments.length - 1];
		
				        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
				        	            test.log(Status.PASS, "Normal Order Process - DINE-IN ( Stripe ) ");
				        	            
				        	        } else {
				        	            System.out.println("System did not completed the order process");
				        	            test.log(Status.FAIL, "Normal Order Process - DINE-IN ( Stripe ) ");}
				        	        
			        	    } 
			        	    else {
			        	        System.out.println("URL bar is not displayed.");
			        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
		*//*
	        	 test.log(Status.PASS, "Normal Order Process - DINE-IN ( Stripe ) -- -- Comming Zoon -- -- ");
	        }
	        
		        catch (Exception error_OrderProcess) {
		            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
	    
	     
	    // END Dine-IN Order Method
	    
		  //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
		  //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
		  //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
		    
	   // Start PICKUP Order Method
	        
        try { 
        	
        	test = extent.createTest("Normal_Order_Process (Postal Code)- PICKUP Order - Cash Payment", "  Verify The Cash Payment Process");
	    	 System.out.println("Start -Normal_Order_Process (Postal Code)- PICKUP Order - Cash Payment ");
	    	 
	    	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"pricetag MENÜ\"]"))).click();
	         Thread.sleep(5000);
	         
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the dinein order method"+Change_the_Order_Method);}
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Lieferung\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the PICKUP order method" +Change_the_Order_Method);}
	         
			         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='scroll-to-class-0']/android.widget.ListView[1]/android.view.View"))).click();
	         Thread.sleep(1000);
	         
			     if (!driver.findElements(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")).isEmpty()) {
			    	 
			    		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text='Topping'])[1]/android.widget.Image"))).click();
			    	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click(); //change the button name here
	   		        } else {
	   		        	
	   		        	System.out.println("'In den Warenkorb (13.29 €)' button not displayed, skipping click.");
	   		        }
		   
		     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
	         Thread.sleep(1000);
	         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
	         Thread.sleep(15000);
			         
			         //Check if the address section is empty; if it is empty, then add the address.
					      try {    
							 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
							
						         if (postaladdressElement.isDisplayed()) {
						        	
						        	 	//check the address is empty
								         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
								        	
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
								        	 	
								        	 			//check the street number is empty
												         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
												        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
												        	 	
											   		        } else {
											   		        	
											   		        	System.out.println("Street number is already defined.");
											   		        }
								         } else {
							   		        	
							   		        	System.out.println("Street address is already defined.");
							   		        }
								         
								         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
								         
								         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
						         }
						         else {
						        	 System.out.println("Address is already defined.");
						         }}
					      catch(Exception Error_addresscheck) {
					        		 
					        		 	System.out.println(Error_addresscheck);
					        	 }
					      
					      
					   // Select if the "Cash" payment option 
				    	  try {
				    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
						         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
						         

				    		    // Check if the "Cash" payment option is displayed
				    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Cash\"]"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
				    		        Thread.sleep(1000);

				    		    } else {
				    		        // If the "Cash" option is already selected
				    		        System.out.println("Error on Payment selection.");
				    		    }
				    		} catch (Exception error_cash_payment_option) {
				    		    // Handle exceptions
				    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
				    		    error_cash_payment_option.printStackTrace();
				    		}
				    	  
				    	//Verify the payment method
				    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Cash\"]")).isDisplayed()) {
				    		  test.log(Status.INFO , ("Payment Method : Cash"));
				    	  }
				    	  else {
				    		  test.log(Status.INFO , ("Invalid Payment Method"));
				    	  }
				    	  
										    	  try {
											    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
													      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
											    	 }
											    	 catch(Exception scroll_down) {
											    		 System.out.println("An error occurred: " + scroll_down.getMessage());
											    		 scroll_down.printStackTrace();
											    	 }
				    	  
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
	         Thread.sleep(15000);
	       
	         //Check the URL is equal to /order-summery and order completed successfully 
	        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));

		        	    if (urlBar.isDisplayed()) {
		        	        String currentUrl = urlBar.getText();

			        	        if (currentUrl.contains("order-summery")) {
	
			        	            String[] urlSegments = currentUrl.split("/");
	
			        	            String lastSegment = urlSegments[urlSegments.length - 1];
	
			        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
			        	            test.log(Status.PASS, "Normal Order Process - PICKUP ( Cash ) ");
			        	            
			        	        } else {
			        	            System.out.println("System did not completed the order process");
			        	            test.log(Status.FAIL, "Normal Order Process - PICKUP ( Cash ) ");}   
			        	        
		        	    } 
		        	    else {

		        	    }
        }
        
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
    


         //-----  POINT Payment  -----
        try { 
        	
        	 test = extent.createTest("Normal_Order_Process (Postal Code)- PICKUP Order - Point Payment", "  Verify The Point Payment Process");
        	 System.out.println("Start -Normal_Order_Process (Postal Code)- PICKUP Order - Point Payment ");
        	 
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"pricetag MENÜ\"]"))).click();
	         Thread.sleep(5000);
	         
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the dinein order method"+Change_the_Order_Method);}
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Lieferung\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the PICKUP order method" +Change_the_Order_Method);}
	         
			wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='scroll-to-class-0']/android.widget.ListView[1]/android.view.View"))).click();
			Thread.sleep(1000);
			         
								if (!driver.findElements(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")).isEmpty()) {
						    	 
					    		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text='Topping'])[1]/android.widget.Image"))).click();
					    	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click(); //change the button name here
						        } else {
						        	
						        	System.out.println("'In den Warenkorb (13.29 €)' button not displayed, skipping click.");
						        }
				   
			wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
			Thread.sleep(1000);
			         
			wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
			Thread.sleep(15000);
			         
			         //Check if the address section is empty; if it is empty, then add the address.
					      try {    
							 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
							
						         if (postaladdressElement.isDisplayed()) {
						        	
						        	 	//check the address is empty
								         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
								        	
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
								        	 	
								        	 			//check the street number is empty
												         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
												        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
												        	 	
											   		        } else {
											   		        	
											   		        	System.out.println("Street number is already defined.");
											   		        }
								         } else {
							   		        	
							   		        	System.out.println("Street address is already defined.");
							   		        }
								         
								         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
								         
								         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
						         }
						         else {
						        	 System.out.println("Address is already defined.");
						         }}
					      catch(Exception Error_addresscheck) {
					        		 
					        		 	System.out.println(Error_addresscheck);
					        	 }
					      
					      
					   // Select if the "POINT" payment option 
				    	  try {
				    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
						         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
						         

				    		    // Check if the "Cash" payment option is displayed
				    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Points\"]"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
				    		        Thread.sleep(1000);

				    		    } else {
				    		        // If the "Cash" option is already selected
				    		        System.out.println("Error on Payment selection.");
				    		    }
				    		} catch (Exception error_cash_payment_option) {
				    		    // Handle exceptions
				    		    System.out.println("An error occurred: " + error_cash_payment_option.getMessage());
				    		    error_cash_payment_option.printStackTrace();
				    		}
				    	  
				    	  //Verify the payment method
				    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Points\"]")).isDisplayed()) {
				    		  test.log(Status.INFO , ("Payment Method : Point"));
				    	  }
				    	  else {
				    		  test.log(Status.INFO , ("Invalid Payment Method"));
				    	  }
				    	  
													    	 try {
													    		 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
															      driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));  
													    	 }
													    	 catch(Exception scroll_down) {
													    		 System.out.println("An error occurred: " + scroll_down.getMessage());
													    		 scroll_down.printStackTrace();
													    	 }
					      
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
	         Thread.sleep(15000);
	         
				         try {
				         		if (driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Aufladen\"]")).isDisplayed()) {
				         			System.out.println("Insufficient Point Balance, Please recharges your point balance and Run again");
				         			test.log(Status.INFO , ("Insufficient Point Balance, Please recharges your point balance and Run again"));
				         		}
				         }
				         catch(Exception error_message_Insufficient_Point_balance) {
				         
				        	 System.out.println(error_message_Insufficient_Point_balance);
				         }
		       
	         //Check the URL is equal to /order-summery and order completed successfully 
	        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));

		        	    if (urlBar.isDisplayed()) {
		        	        String currentUrl = urlBar.getText();

			        	        if (currentUrl.contains("order-summery")) {
	
			        	            String[] urlSegments = currentUrl.split("/");
	
			        	            String lastSegment = urlSegments[urlSegments.length - 1];
	
			        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
			        	            test.log(Status.PASS, "Normal Order Process - PICKUP ( Point ) ");
			        	            
			        	        } else {
			        	            System.out.println("System did not completed the order process");
			        	            test.log(Status.FAIL, "Normal Order Process - PICKUP ( Point ) ");}
			        	        
		        	    } 
		        	    else {
		        	        System.out.println("URL bar is not displayed.");
		        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
        }
        
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
        
        

	   
	     //*--PayPal Payment Method--*
	       try { 
        	
        	test = extent.createTest("Normal_Order_Process (Postal Code)- PICKUP Order - PayPal Payment", "  Verify The PayPal Payment Process");
	    	 System.out.println("Start -Normal_Order_Process (Postal Code)- PICKUP Order - PayPal Payment ");
	    	 
	    	 /*	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"pricetag MENÜ\"]"))).click();
	         	Thread.sleep(5000);
	         
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the dinein order method"+Change_the_Order_Method);}
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Lieferung\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the PICKUP order method" +Change_the_Order_Method);}
	         
			         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='scroll-to-class-0']/android.widget.ListView[1]/android.view.View"))).click();
	         Thread.sleep(1000);
	         
			     if (!driver.findElements(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")).isEmpty()) {
			    	 
			    		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text='Topping'])[1]/android.widget.Image"))).click();
			    	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click(); //change the button name here
	   		        } else {
	   		        	
	   		        	System.out.println("'In den Warenkorb (13.29 €)' button not displayed, skipping click.");
	   		        }
		   
		     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
	         Thread.sleep(1000);
	         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
	         Thread.sleep(15000);
			         
			         //Check if the address section is empty; if it is empty, then add the address.
					      try {    
							 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
							
						         if (postaladdressElement.isDisplayed()) {
						        	
						        	 	//check the address is empty
								         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
								        	
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
								        	 	
								        	 			//check the street number is empty
												         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
												        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
												        	 	
											   		        } else {
											   		        	
											   		        	System.out.println("Street number is already defined.");
											   		        }
								         } else {
							   		        	
							   		        	System.out.println("Street address is already defined.");
							   		        }
								         
								         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
								         
								         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
						         }
						         else {
						        	 System.out.println("Address is already defined.");
						         }}
					      catch(Exception Error_addresscheck) {
					        		 
					        		 	System.out.println(Error_addresscheck);
					        	 }
					      
					      
					   // Select if the "Cash" payment option 
				    	  try {
				    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
						         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
						         

				    		    // Check if the "Cash" payment option is displayed
				    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Cash\"]"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
				    		        Thread.sleep(1000);

				    		    } else {
				    		        // If the "Cash" option is already selected
				    		        System.out.println("Error on Payment selection.");
				    		    }
				    		} catch (Exception error_cash_payment_option) {
				    		    // Handle exceptions
				    		    System.out.println("An error occurred: " + error_PayPal_payment_option.getMessage());
				    		    error_PayPal_payment_option.printStackTrace();
				    		}
				    	  
				    	//Verify the payment method
				    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Cash\"]")).isDisplayed()) {
				    		  test.log(Status.INFO , ("Payment Method : PayPal"));
				    	  }
				    	  else {
				    		  test.log(Status.INFO , ("Invalid Payment Method"));
				    	  }
				    	  
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
	         Thread.sleep(15000);
	       
	         //Check the URL is equal to /order-summery and order completed successfully 
	        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));

		        	    if (urlBar.isDisplayed()) {
		        	        String currentUrl = urlBar.getText();

			        	        if (currentUrl.contains("order-summery")) {
	
			        	            String[] urlSegments = currentUrl.split("/");
	
			        	            String lastSegment = urlSegments[urlSegments.length - 1];
	
			        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
			        	            test.log(Status.PASS, "Normal Order Process - PICKUP ( PayPal ) ");
			        	            
			        	        } else {
			        	            System.out.println("System did not completed the order process");
			        	            test.log(Status.FAIL, "Normal Order Process - PICKUP ( PayPal ) ");}   
			        	        
		        	    } 
		        	    else {

		        	    }
	*//*	        	    
	    	 test.log(Status.PASS, "Normal Order Process - PICKUP ( PayPal ) -- -- Comming Zoon -- -- ");
        }
        
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess.getMessage());}
    
	    

         //-----  Stripe Payment  -----
        try { 
        	
        	 test = extent.createTest("Normal_Order_Process (Postal Code)- PICKUP Order - Stripe Payment", "  Verify The Stripe Payment Process");
        	 System.out.println("Start -Normal_Order_Process (Postal Code)- PICKUP Order - Stripe Payment ");
        	 
	     /*    wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"pricetag MENÜ\"]"))).click();
	         Thread.sleep(5000);
	         
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Dine in\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the dinein order method"+Change_the_Order_Method);}
			         try {
			        	 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Lieferung\"]"))).click();
				         Thread.sleep(1000);
				         
				         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"footsteps outline   Abholung\"]"))).click();
				         Thread.sleep(1000);
			         }catch(Exception Change_the_Order_Method) {
			        	 System.out.println("Already define the PICKUP order method" +Change_the_Order_Method);}
	         
			 wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='scroll-to-class-0']/android.widget.ListView[1]/android.view.View"))).click();
			 Thread.sleep(1000);
			         
					     if (!driver.findElements(AppiumBy.xpath("//android.app.Dialog/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")).isEmpty()) {
					    	 
					    		wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.CheckBox[@text='Topping'])[1]/android.widget.Image"))).click();
					    	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"cart outline In den Warenkorb (13.29 €)\"]"))).click(); //change the button name here
			   		        } else {
			   		        	
			   		        	System.out.println("'In den Warenkorb (13.29 €)' button not displayed, skipping click.");
			   		        }
		   
		     wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.webkit.WebView[@text=\"Biber & Fieber UG\"]/android.view.View/android.view.View[2]"))).click();
	         Thread.sleep(1000);
	         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"wallet outline Bestellen 13.29 €\"]"))).click(); //change the button name here
	         Thread.sleep(10000);
			         
			         //Check if the address section is empty; if it is empty, then add the address.
					      try {    
							 WebElement postaladdressElement = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"));
							
						         if (postaladdressElement.isDisplayed()) {
						        	
						        	 	//check the address is empty
								         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]")).isEmpty()) {
								        	
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).sendKeys(PostalAddress);
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"postal-code-google\"]"))).click();
								        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Theo-Geisel-Straße 12Usingen, Germany\"]"))).click();
								        	 	
								        	 			//check the street number is empty
												         if (!driver.findElements(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]")).isEmpty()) {
												        	 	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.EditText[@resource-id=\"street_number_id\"]"))).sendKeys(StreetNumber);
												        	 	
											   		        } else {
											   		        	
											   		        	System.out.println("Street number is already defined.");
											   		        }
								         } else {
							   		        	
							   		        	System.out.println("Street address is already defined.");
							   		        }
								         
								         String scrolldowntofintsavebutton = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								         driver.findElement(AppiumBy.androidUIAutomator(scrolldowntofintsavebutton));
								         
								         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id=\"checkout_delivery_information_saveBtn\"]"))).click();
						         }
						         else {
						        	 System.out.println("Address is already defined.");
						         }}
					      catch(Exception Error_addresscheck) {
					        		 
					        		 	System.out.println(Error_addresscheck);
					        	 }
					      
					      
					   // Select if the "POINT" payment option 
				    	  try {
				    		  	String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
						         driver.findElement(AppiumBy.androidUIAutomator(scrollableElement));
						         

				    		    // Check if the "Cash" payment option is displayed
				    		    if (driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image")).isDisplayed()) {

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_method_btn\"]/android.widget.Image"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text=\"Points\"]"))).click();
				    		        Thread.sleep(1000);

				    		        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='Ok']"))).click();
				    		        Thread.sleep(1000);

				    		    } else {
				    		        // If the "Cash" option is already selected
				    		        System.out.println("Error on Payment selection.");
				    		    }
				    		} catch (Exception error_cash_payment_option) {
				    		    // Handle exceptions
				    		    System.out.println("An error occurred: " + error_Stripe_payment_option.getMessage());
				    		    error_Stripe_payment_option.printStackTrace();
				    		}
				    	  
				    	  //Verify the payment method
				    	  if (driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"  Points\"]")).isDisplayed()) {
				    		  test.log(Status.INFO , ("Payment Method : Stripe"));
				    	  }
				    	  else {
				    		  test.log(Status.INFO , ("Invalid Payment Method"));
				    	  }
				    	  
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
	         Thread.sleep(15000);
	         
				         try {
				         		if (driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Aufladen\"]")).isDisplayed()) {
				         			System.out.println("Insufficient Point Balance, Please reload your point balance and Run again");
				         			test.log(Status.INFO , ("Insufficient Point Balance, Please recharge your point balance and Run again"));
				         		}
				         }
				         catch(Exception error_message_Insufficient_Stripe_balance) {
				        	 System.out.println(error_message_Insufficient_Stripe_balance);
				         }
		       
	         //Check the URL is equal to /order-summery and order completed successfully 
	        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));

		        	    if (urlBar.isDisplayed()) {
		        	        String currentUrl = urlBar.getText();

			        	        if (currentUrl.contains("order-summery")) {
	
			        	            String[] urlSegments = currentUrl.split("/");
	
			        	            String lastSegment = urlSegments[urlSegments.length - 1];
	
			        	            test.log(Status.INFO ,("Payment ID : " + lastSegment));
			        	            test.log(Status.PASS, "Normal Order Process - PICKUP ( Stripe ) ");
			        	            
			        	        } else {
			        	            System.out.println("System did not completed the order process");
			        	            test.log(Status.FAIL, "Normal Order Process - PICKUP ( Stripe ) ");}
			        	        
		        	    } 
		        	    else {
		        	        System.out.println("URL bar is not displayed.");
		        	        test.log(Status.FAIL, "Normal Order Process - Checkout Process");}
		    
		    	 }
         
        
	        catch (Exception error_OrderProcess1) {
	            test.log(Status.FAIL, "Normal Order Process : " + error_OrderProcess1.getMessage());
	            }
	        */
	        
        //end the test script
        }
         
	    	   
        
	    @After
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            extent.flush();
	        }
	        System.out.println("Browser closed.");
	    }
	}


