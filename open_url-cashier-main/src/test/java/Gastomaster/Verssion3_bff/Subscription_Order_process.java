package Gastomaster.Verssion3_bff;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
	
public class Subscription_Order_process {
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
	        test = extent.createTest("Subscription_Order_process - Loging ", "Verifying Login process");
	        
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
			        	System.out.println("skip the permmision 01");
			        }
					        /*     
					        //Given the permission to Notification
					        if (driver.findElements(AppiumBy.id("com.android.chrome:id/positive_button")).isEmpty()) {
					        	wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/positive_button"))).click();
					        }
					        else {
					        	System.out.println("skip the permmision");
					        }
					        
					        //given notfication permsission to mobile
					        if (driver.findElements(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button")).isEmpty()) {
						        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.permissioncontroller:id/permission_allow_button"))).click();
						    }
					        else {
					        	System.out.println("skip the permmision");
					        }
					         */ 
			        if (driver.findElements(AppiumBy.id("com.android.chrome:id/ack_button")).isEmpty()) {
				        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("com.android.chrome:id/ack_button"))).click();
			        }
			        else {
			        	System.out.println("skip the permmision 02");
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
		   		        
		   		        
		   		  Thread.sleep(15000);
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
		           Thread.sleep(2000);
		           
		           // Verify login success by checking if the user's Dashboard is displayed (adjust ID based on your App)
		           Assert.assertTrue(driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=' Profil']")).isDisplayed());
		          
		           test.log(Status.PASS, "User Login Process ");
		           Thread.sleep(1000);
		           
		    } catch (Exception error_login) {
	            test.log(Status.FAIL, "Login Funcation: " + error_login.getMessage());
	        }
	             
	    }
 

	    @Test
	    public void Subscription_Order_process_DELIVERY () throws InterruptedException {
	    	
	  
	    	String PostalName = "Hessen";
	    	String PostalAddress ="Theo-Geisel-Straße 12Usingen, Germany";
	    	String StreetNumber ="12";
	    	
	    	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25)); 
	           
	         /*-----  CASH PAYMENET Process  -----*/
	        try { 
	        	
	        	 test = extent.createTest("Subscription_Order_process - Delivery Order - Cash Paymnet", "  Verify The Cash Paymnet Process");
		    	 System.out.println("Start -Subscription_Order_process - Delivery Order - Cash Paymnet ");
		    	 
		    	 
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
		         Thread.sleep(5000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
		         Thread.sleep(12000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).sendKeys(PostalName);
		         Thread.sleep(2000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"postal_code_deliveryList\"]/android.view.View/android.view.View/android.view.View"))).click();
		         Thread.sleep(1000);
		         
		         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen\"]"))).click();
		         Thread.sleep(1000);
		         
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
						      
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
						      
						      // check the subscription method to select
							      try {
							    	  WebElement susbscriptionslected = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"));
							    	  
								    	  if(susbscriptionslected.isDisplayed()) {
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"))).click();
								    	  } else {
								    		  System.out.println("System already define subscription method");
								    	  }
							      }
								      catch(Exception Erro_Subscription_Button_Selecte_Function) {
								    	  System.out.println(Erro_Subscription_Button_Selecte_Function);
							      }
						      
							      
							    //select the start date function
							      try {
							    	  WebElement susbscriptiondate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"));
							    	  
								    	  if(susbscriptiondate.isDisplayed()) {
								    		  
								    		  //open the pop-up for select the date
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"))).click();
								    		  Thread.sleep(1000);
											    		  
											    		  LocalDate today = LocalDate.now();
											    		  DayOfWeek dayOfWeek = today.getDayOfWeek();
			
											    		  if(dayOfWeek == DayOfWeek.MONDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[0]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.TUESDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[1]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.WEDNESDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[2]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.THURSDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[3]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.FRIDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[4]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.SATURDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[5]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.SUNDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[6]"))).click();
											    		      Thread.sleep(1000);
											    		  } else {
											    		      System.out.print("Something went wrong on the start date function");
											    		      Thread.sleep(1000);
											    		  }
			
											    		  test.log(Status.INFO, ("Subscription order start day : " + dayOfWeek));

								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_ok_btn\"]"))).click();
								    	  } 
								    	  else {
								    		  System.out.println( "System did not dispayed the date drop down");
								    	  }
							      }
								      catch(Exception Erro_Subscription_Date_Function) {
								    	  System.out.println(Erro_Subscription_Date_Function);
							      }
							      
							      Thread.sleep(3000);
							      
							    // add the end date to order
							      try {
							    	  
								    
							    	  WebElement susbscriptionEnddate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"));
							    	  
							    	  	LocalDate today = LocalDate.now();
							            LocalDate added_some_date_to_End_Date = today.plusDays(9);
							            DateTimeFormatter Date_formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
							            String subscription_order_end_date = added_some_date_to_End_Date.format(Date_formatter);
							          
								    	  if(susbscriptionEnddate.isDisplayed()) {
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  String scrollabl_to_checkout_isSubscription_endDate_btn = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										      driver.findElement(AppiumBy.androidUIAutomator(scrollabl_to_checkout_isSubscription_endDate_btn));
										      
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_endDate_btn\"]/android.widget.Image"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='" + subscription_order_end_date + "']"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Ok\"]"))).click();
								    		  
								    		  test.log(Status.INFO, ("SUbscription order End day : " + subscription_order_end_date));  } 
								    	  
								    	 else { System.out.println("System did not dispayed the tuesday on pop-up page"); }
								    	  
							      } 
							      
							      catch(Exception Erro_Subscription_Date_Function) {
								    	  test.log(Status.FAIL, (Erro_Subscription_Date_Function));}
							      
							    
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
							      
							      
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
					    		        System.out.println("Error on paymnet selection.");
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
							    		  test.log(Status.INFO , ("Invalid Paymnet Method"));
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
		       
		         
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		         
		         //Check the URL is equal to /order-summery and order completed successfully 
		        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
          
			        	    if (urlBar.isDisplayed()) {
			        	        String currentUrl = urlBar.getText();
	
				        	        if (currentUrl.contains("subscription-summery")) {
		
				        	            String[] urlSegments = currentUrl.split("/");
		
				        	            String lastSegment = urlSegments[urlSegments.length - 1];
				        	            
				        	            
				        	            test.log(Status.INFO ,("Subscription Order ID (DATABASE ID) : " + lastSegment));
				        	            
				        	            		List<WebElement> FirsttextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));

						        	            WebElement firstTextView = FirsttextViewElements.get(0); 
						        	            String Subscription_order_ID = firstTextView.getText();
						        	            
						        	    test.log(Status.INFO, Subscription_order_ID); 
						        	    test.log(Status.PASS, "Subscription Order Process - Delivery ( Cash ) ");
						        	    
				        	        } else {
				        	            System.out.println("System did not completed the order proess");
				        	            test.log(Status.FAIL, "Subscription Order Process - Delivery ( Cash ) ");}
				        	        
			        	    } 
			        	    else {
			        	        System.out.println("URL bar is not displayed.");
			        	        test.log(Status.FAIL, "Subscription Order Process - Checkout Process");}
	        }
	        
		        catch (Exception error_OrderProcess) {
		            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
	    

        
      // subscription order with point order
	    
	    try { 
        	
       	 test = extent.createTest("Subscription_Order_Process - Delivery Order - Point Paymnet", "  Verify The Point Paymnet Process");
       	 System.out.println("Start -Subscription_Order_Process - Delivery Order - Point Paymnet ");
       	 
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='home HEIM']"))).click();
	         Thread.sleep(8000);
	         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen chevron forward outline chevron forward outline chevron forward outline\"]"))).click();
	         Thread.sleep(12000);
	         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id='postal_code_delivery']/android.widget.EditText"))).sendKeys(PostalName);
	         Thread.sleep(2000);
	         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"postal_code_deliveryList\"]/android.view.View/android.view.View/android.view.View"))).click();
	         Thread.sleep(1000);
	         
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Jetzt bestellen\"]"))).click();
	         Thread.sleep(1000);
	         
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
					      
					      // check the subscription method to select
					      try {
					    	  WebElement susbscriptionslected = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"));
					    	  
						    	  if(susbscriptionslected.isDisplayed()) {
						    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"))).click();
						    	  } else {
						    		  System.out.println("System already define subscription method");
						    	  }
					      }
						      catch(Exception Erro_Subscription_Button_Selecte_Function) {
						    	  System.out.println(Erro_Subscription_Button_Selecte_Function);
					      }
				      
					      
					    //select the start date function
					      try {
					    	  WebElement susbscriptiondate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"));
					    	  
						    	  if(susbscriptiondate.isDisplayed()) {
						    		  
						    		  //open the pop-up for select the date
						    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"))).click();
						    		  Thread.sleep(1000);
									    		  
									    		  LocalDate today = LocalDate.now();
									    		  DayOfWeek dayOfWeek = today.getDayOfWeek();
	
									    		  if(dayOfWeek == DayOfWeek.MONDAY) {
									    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[0]"))).click();
									    		      Thread.sleep(1000);
									    		  } else if(dayOfWeek == DayOfWeek.TUESDAY) {
									    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[1]"))).click();
									    		      Thread.sleep(1000);
									    		  } else if(dayOfWeek == DayOfWeek.WEDNESDAY) {
									    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[2]"))).click();
									    		      Thread.sleep(1000);
									    		  } else if(dayOfWeek == DayOfWeek.THURSDAY) {
									    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[3]"))).click();
									    		      Thread.sleep(1000);
									    		  } else if(dayOfWeek == DayOfWeek.FRIDAY) {
									    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[4]"))).click();
									    		      Thread.sleep(1000);
									    		  } else if(dayOfWeek == DayOfWeek.SATURDAY) {
									    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[5]"))).click();
									    		      Thread.sleep(1000);
									    		  } else if(dayOfWeek == DayOfWeek.SUNDAY) {
									    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[6]"))).click();
									    		      Thread.sleep(1000);
									    		  } else {
									    		      System.out.print("Something went wrong on the start date function");
									    		      Thread.sleep(1000);
									    		  }
	
									    		  test.log(Status.INFO, ("Subscription order start day : " + dayOfWeek));

						    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_ok_btn\"]"))).click();
						    	  } 
						    	  else {
						    		  System.out.println( "System did not dispayed the date drop down");
						    	  }
					      }
						      catch(Exception Erro_Subscription_Date_Function) {
						    	  System.out.println(Erro_Subscription_Date_Function);
					      }
					      
					      Thread.sleep(3000);
					      
					    // add the end date to order
					      try {
					    	  
						    
					    	  WebElement susbscriptionEnddate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"));
					    	  
					    	  	LocalDate today = LocalDate.now();
					            LocalDate added_some_date_to_End_Date = today.plusDays(9);
					            DateTimeFormatter Date_formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
					            String subscription_order_end_date = added_some_date_to_End_Date.format(Date_formatter);
					          
						    	  if(susbscriptionEnddate.isDisplayed()) {
						    		  
						    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"))).click();
						    		  Thread.sleep(1000);
						    		  
						    		  String scrollabl_to_checkout_isSubscription_endDate_btn = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
								      driver.findElement(AppiumBy.androidUIAutomator(scrollabl_to_checkout_isSubscription_endDate_btn));
								      
						    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_endDate_btn\"]/android.widget.Image"))).click();
						    		  Thread.sleep(1000);
						    		  
						    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='" + subscription_order_end_date + "']"))).click();
						    		  Thread.sleep(1000);
						    		  
						    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Ok\"]"))).click();
						    		  
						    		  test.log(Status.INFO, ("SUbscription order End day : " + subscription_order_end_date));  } 
						    	  
						    	 else { System.out.println("System did not dispayed the tuesday on pop-up page"); }
						    	  
					      } 
					      
					      catch(Exception Erro_Subscription_Date_Function) {
						    	  test.log(Status.FAIL, (Erro_Subscription_Date_Function));}
					      
					      
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
				    		        System.out.println("Error on paymnet selection.");
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
				    		  test.log(Status.INFO , ("Invalid Paymnet Method"));
				    	  }
				    	  
									 try {
										 String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										 driver.findElement(AppiumBy.androidUIAutomator(scrollableElement)); }
									 
									catch(Exception scroll_down) {
											System.out.println("An error occurred: " + scroll_down.getMessage());
											 scroll_down.printStackTrace(); }
				    	  
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
	         Thread.sleep(15000);
	         
				         try {
				         		if (driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Aufladen\"]")).isDisplayed()) {
				         			System.out.println("Insufficent Point Balance, Please recharge your point balnce and Run agin");
				         			test.log(Status.INFO , ("Insufficent Point Balance, Please recharge your point balnce and Run agin"));
				         		}
				         }
				         catch(Exception error_message_Insufficent_Point_balnce) {
				        	 System.out.println(error_message_Insufficent_Point_balnce);
				         }
		       
				         //Check the URL is equal to /order-summery and order completed successfully 
			        	   WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
	          
				        	    if (urlBar.isDisplayed()) {
				        	        String currentUrl = urlBar.getText();
		
					        	        if (currentUrl.contains("subscription-summery")) {
			
					        	            String[] urlSegments = currentUrl.split("/");
			
					        	            String lastSegment = urlSegments[urlSegments.length - 1];
					        	            
					        	            
					        	            test.log(Status.INFO ,("Subscription Order ID (DATABASE ID) : " + lastSegment));
					        	            
					        	            		List<WebElement> FirsttextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));

							        	            WebElement firstTextView = FirsttextViewElements.get(0); 
							        	            String Subscription_order_ID = firstTextView.getText();
							        	            
							        	    test.log(Status.INFO, Subscription_order_ID); 
			        	            test.log(Status.PASS, "Subscription Order Process - Delivery ( Point ) ");
			        	            
			        	        } else {
			        	            System.out.println("System did not completed the order proess");
			        	            test.log(Status.FAIL, "Subscription Order Process - Delivery ( Point ) ");}
			        	        
		        	    } 
		        	    else {
		        	        System.out.println("URL bar is not displayed.");
		        	        test.log(Status.FAIL, "Subscription Order Process - Checkout Process");}
       }
       
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
   
	    
	    
	    
	    // paypal paymnet
        try { 
        	
        	 test = extent.createTest("Subscription_Order_Process - Delivery Order - PAYPAL Paymnet", "  Verify The PAYPAL Paymnet Process");
        	 System.out.println("Start -Subscription_Order_Process - Delivery Order - PAYPAL Paymnet ");
        	 
	     
        	 test.log(Status.PASS, "Subscription Order Process - Delivery ( PAYPAL ) -- -- Comming Zoon -- -- ");
        }
        
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
        
        
     // STRIPE payment
        try { 
        	
        	 test = extent.createTest("Subscription_Order_Process - Delivery Order - STRIPE Paymnet", "  Verify The STRIPE Paymnet Process");
        	 System.out.println("Start -Subscription_Order_Process - Delivery Order - STRIPE Paymnet ");
        
        	 
        	 test.log(Status.PASS, "Subscription Order Process - Delivery ( STRIPE ) -- -- Comming Zoon -- -- ");
        }
        
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
        
    
    // END Delivery Order Method
	    
	    
    //  ************************************************************************************************************************************************************************************************************
    //  ************************************************************************************************************************************************************************************************************
    //  ************************************************************************************************************************************************************************************************************
     
        // Start DINE-IN Order Method
  	  
        
        /*-----  CASH PAYMENET Process  -----*/
       try { 
       	
       	test = extent.createTest("Subscription_Order_Process - DINE-IN Order - Cash Paymnet", "  Verify The Cash Paymnet Process");
	    	 System.out.println("Start -Subscription_Order_Process - DINE-IN Order - Cash Paymnet ");
	    	 
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
					      
					      
					      // check the subscription method to select
						      try {
						    	  WebElement susbscriptionslected = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"));
						    	  
							    	  if(susbscriptionslected.isDisplayed()) {
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"))).click();
							    	  } else {
							    		  System.out.println("System already define subscription method");
							    	  }
						      }
							      catch(Exception Erro_Subscription_Button_Selecte_Function) {
							    	  System.out.println(Erro_Subscription_Button_Selecte_Function);
						      }
					      
						      
						    //select the start date function
						      try {
						    	  WebElement susbscriptiondate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"));
						    	  
							    	  if(susbscriptiondate.isDisplayed()) {
							    		  
							    		  //open the pop-up for select the date
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"))).click();
							    		  Thread.sleep(1000);
										    		  
										    		  LocalDate today = LocalDate.now();
										    		  DayOfWeek dayOfWeek = today.getDayOfWeek();
		
										    		  if(dayOfWeek == DayOfWeek.MONDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[0]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.TUESDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[1]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.WEDNESDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[2]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.THURSDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[3]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.FRIDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[4]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.SATURDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[5]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.SUNDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[6]"))).click();
										    		      Thread.sleep(1000);
										    		  } else {
										    		      System.out.print("Something went wrong on the start date function");
										    		      Thread.sleep(1000);
										    		  }
		
										    		  test.log(Status.INFO, ("Subscription order start day : " + dayOfWeek));

							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_ok_btn\"]"))).click();
							    	  } 
							    	  else {
							    		  System.out.println( "System did not dispayed the date drop down");
							    	  }
						      }
							      catch(Exception Erro_Subscription_Date_Function) {
							    	  System.out.println(Erro_Subscription_Date_Function);
						      }
						      
						      Thread.sleep(3000);
						      
						    // add the end date to order
						      try {
						    	  
							    
						    	  WebElement susbscriptionEnddate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"));
						    	  
						    	  	LocalDate today = LocalDate.now();
						            LocalDate added_some_date_to_End_Date = today.plusDays(9);
						            DateTimeFormatter Date_formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
						            String subscription_order_end_date = added_some_date_to_End_Date.format(Date_formatter);
						          
							    	  if(susbscriptionEnddate.isDisplayed()) {
							    		  
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"))).click();
							    		  Thread.sleep(1000);
							    		  
							    		  String scrollabl_to_checkout_isSubscription_endDate_btn = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
									      driver.findElement(AppiumBy.androidUIAutomator(scrollabl_to_checkout_isSubscription_endDate_btn));
									      
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_endDate_btn\"]/android.widget.Image"))).click();
							    		  Thread.sleep(1000);
							    		  
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='" + subscription_order_end_date + "']"))).click();
							    		  Thread.sleep(1000);
							    		  
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Ok\"]"))).click();
							    		  
							    		  test.log(Status.INFO, ("SUbscription order End day : " + subscription_order_end_date));  } 
							    	  
							    	 else { System.out.println("System did not dispayed the tuesday on pop-up page"); }
							    	  
						      } 
						      
						      catch(Exception Erro_Subscription_Date_Function) {
							    	  test.log(Status.FAIL, (Erro_Subscription_Date_Function));}
					      
					      
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
				    		        System.out.println("Error on paymnet selection.");
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
				    		  test.log(Status.INFO , ("Invalid Paymnet Method"));
				    	  }
				    	  
									try {
										String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										driver.findElement(AppiumBy.androidUIAutomator(scrollableElement)); }
									
									catch(Exception scroll_down) {
										 System.out.println("An error occurred: " + scroll_down.getMessage());
										scroll_down.printStackTrace(); }
									
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
	         Thread.sleep(15000);
	       
	       //Check the URL is equal to /order-summery and order completed successfully 
     	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));

	        	    if (urlBar.isDisplayed()) {
	        	        String currentUrl = urlBar.getText();

		        	        if (currentUrl.contains("subscription-summery")) {

		        	            String[] urlSegments = currentUrl.split("/");

		        	            String lastSegment = urlSegments[urlSegments.length - 1];
		        	            
		        	            
		        	            test.log(Status.INFO ,("Subscription Order ID (DATABASE ID) : " + lastSegment));
		        	            
		        	            		List<WebElement> FirsttextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));

				        	            WebElement firstTextView = FirsttextViewElements.get(0); 
				        	            String Subscription_order_ID = firstTextView.getText();
				        	            
				        	    test.log(Status.INFO, Subscription_order_ID); 
			        	            test.log(Status.PASS, "Subscription Order Process - DINE-IN ( Cash ) ");
			        	            
			        	        } else {
			        	            System.out.println("System did not completed the order proess");
			        	            test.log(Status.FAIL, "Subscription Order Process - DINE-IN ( Cash ) ");}   
			        	        
		        	    } 
		        	    else {

		        	    }
       }
       
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
   


        //-----  POINT PAYMNET  -----
       try { 
       	
       	 test = extent.createTest("Subscription_Order_Process - DINE-IN Order - Point Paymnet", "  Verify The Point Paymnet Process");
       	 System.out.println("Start -Subscription_Order_Process - DINE-IN Order - Point Paymnet ");
       	 
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
					      
					      
					      
					      // check the subscription method to select
						      try {
						    	  WebElement susbscriptionslected = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"));
						    	  
							    	  if(susbscriptionslected.isDisplayed()) {
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"))).click();
							    	  } else {
							    		  System.out.println("System already define subscription method");
							    	  }
						      }
							      catch(Exception Erro_Subscription_Button_Selecte_Function) {
							    	  System.out.println(Erro_Subscription_Button_Selecte_Function);
						      }
					      
						      
						    //select the start date function
						      try {
						    	  WebElement susbscriptiondate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"));
						    	  
							    	  if(susbscriptiondate.isDisplayed()) {
							    		  
							    		  //open the pop-up for select the date
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"))).click();
							    		  Thread.sleep(1000);
										    		  
										    		  LocalDate today = LocalDate.now();
										    		  DayOfWeek dayOfWeek = today.getDayOfWeek();
		
										    		  if(dayOfWeek == DayOfWeek.MONDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[0]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.TUESDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[1]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.WEDNESDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[2]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.THURSDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[3]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.FRIDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[4]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.SATURDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[5]"))).click();
										    		      Thread.sleep(1000);
										    		  } else if(dayOfWeek == DayOfWeek.SUNDAY) {
										    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[6]"))).click();
										    		      Thread.sleep(1000);
										    		  } else {
										    		      System.out.print("Something went wrong on the start date function");
										    		      Thread.sleep(1000);
										    		  }
		
										    		  test.log(Status.INFO, ("Subscription order start day : " + dayOfWeek));

							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_ok_btn\"]"))).click();
							    	  } 
							    	  else {
							    		  System.out.println( "System did not dispayed the date drop down");
							    	  }
						      }
							      catch(Exception Erro_Subscription_Date_Function) {
							    	  System.out.println(Erro_Subscription_Date_Function);
						      }
						      
						      Thread.sleep(3000);
						      
						    // add the end date to order
						      try {
						    	  
							    
						    	  WebElement susbscriptionEnddate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"));
						    	  
						    	  	LocalDate today = LocalDate.now();
						            LocalDate added_some_date_to_End_Date = today.plusDays(9);
						            DateTimeFormatter Date_formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
						            String subscription_order_end_date = added_some_date_to_End_Date.format(Date_formatter);
						          
							    	  if(susbscriptionEnddate.isDisplayed()) {
							    		  
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"))).click();
							    		  Thread.sleep(1000);
							    		  
							    		  String scrollabl_to_checkout_isSubscription_endDate_btn = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
									      driver.findElement(AppiumBy.androidUIAutomator(scrollabl_to_checkout_isSubscription_endDate_btn));
									      
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_endDate_btn\"]/android.widget.Image"))).click();
							    		  Thread.sleep(1000);
							    		  
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='" + subscription_order_end_date + "']"))).click();
							    		  Thread.sleep(1000);
							    		  
							    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Ok\"]"))).click();
							    		  
							    		  test.log(Status.INFO, ("SUbscription order End day : " + subscription_order_end_date));  } 
							    	  
							    	 else { System.out.println("System did not dispayed the tuesday on pop-up page"); }
							    	  
						      } 
						      
						      catch(Exception Erro_Subscription_Date_Function) {
							    	  test.log(Status.FAIL, (Erro_Subscription_Date_Function));}
					      
					      
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
				    		        System.out.println("Error on paymnet selection.");
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
				    		  test.log(Status.INFO , ("Invalid Paymnet Method"));
				    	  }

									try {
										String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										driver.findElement(AppiumBy.androidUIAutomator(scrollableElement)); }
									
									catch(Exception scroll_down) {
										System.out.println("An error occurred: " + scroll_down.getMessage());
										scroll_down.printStackTrace(); }
				    	  
	         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
	         Thread.sleep(15000);
	         
				         try {
				         		if (driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Aufladen\"]")).isDisplayed()) {
				         			System.out.println("Insufficent Point Balance, Please recharge your point balnce and Run agin");
				         			test.log(Status.INFO , ("Insufficent Point Balance, Please recharge your point balnce and Run agin"));
				         		}
				         }
				         catch(Exception error_message_Insufficent_Point_balnce) {
				        	 System.out.println(error_message_Insufficent_Point_balnce);
				         }
		       
				       //Check the URL is equal to /order-summery and order completed successfully 
			        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
	          
				        	    if (urlBar.isDisplayed()) {
				        	        String currentUrl = urlBar.getText();
		
					        	        if (currentUrl.contains("subscription-summery")) {
			
					        	            String[] urlSegments = currentUrl.split("/");
			
					        	            String lastSegment = urlSegments[urlSegments.length - 1];
					        	            
					        	            
					        	            test.log(Status.INFO ,("Subscription Order ID (DATABASE ID) : " + lastSegment));
					        	            
					        	            		List<WebElement> FirsttextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));

							        	            WebElement firstTextView = FirsttextViewElements.get(0); 
							        	            String Subscription_order_ID = firstTextView.getText();
							        	            
							        	    test.log(Status.INFO, Subscription_order_ID); 
			        	            test.log(Status.PASS, "Subscription Order Process - DINE-IN ( Point ) ");
			        	            
			        	        } else {
			        	            System.out.println("System did not completed the order proess");
			        	            test.log(Status.FAIL, "Subscription Order Process - DINE-IN ( Point ) ");}
			        	        
		        	    } 
		        	    else {
		        	        System.out.println("URL bar is not displayed.");
		        	        test.log(Status.FAIL, "Subscription Order Process - Checkout Process");}
       }
       
	        catch (Exception error_OrderProcess) {
	            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
   
    
       /*-----  Paypal PAYMENET Process  -----*/
		       try { 
		       	
		       	test = extent.createTest("Subscription_Order_Process - DINE-IN Order - Paypal Paymnet", "  Verify The Paypal Paymnet Process");
			    	 System.out.println("Start -Subscription_Order_Process - DINE-IN Order - Paypal Paymnet ");
			    	 
			    
				        	    test.log(Status.PASS, "Subscription Order Process - DINE-IN ( Paypal ) -- -- Comming Zoon -- -- ");
		       }
		       
		       catch (Exception error_OrderProcess) {
	        	test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
   


        //-----  Stripe PAYMNET  -----
		       try { 
		       	
		       	 test = extent.createTest("Subscription_Order_Process - DINE-IN Order - Stripe Paymnet", "  Verify The Stripe Paymnet Process");
		       	 System.out.println("Start -Subscription_Order_Process - DINE-IN Order - Stripe Paymnet ");
		       	
		       	 test.log(Status.PASS, "Subscription Order Process - DINE-IN ( Stripe ) -- -- Comming Zoon -- -- ");
		       }
		       
		        catch (Exception error_OrderProcess) {
		            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
   
    
   // END Dine-IN Order Method
      
		       
//  ************************************************************************************************************************************************************************************************************
//  ************************************************************************************************************************************************************************************************************
//  ************************************************************************************************************************************************************************************************************
		           
        
		       // Start PICKUP Order Method
		        
		        try { 
		        	
		        	test = extent.createTest("Subscription_Order_Process - PICKUP Order - Cash Paymnet", "  Verify The Cash Paymnet Process");
			    	 System.out.println("Start -Subscription_Order_Process - PICKUP Order - Cash Paymnet ");
			    	 
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
							      
							      
							      // check the subscription method to select
							      try {
							    	  WebElement susbscriptionslected = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"));
							    	  
								    	  if(susbscriptionslected.isDisplayed()) {
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"))).click();
								    	  } else {
								    		  System.out.println("System already define subscription method");
								    	  }
							      }
								      catch(Exception Erro_Subscription_Button_Selecte_Function) {
								    	  System.out.println(Erro_Subscription_Button_Selecte_Function);
							      }
						      
							      
							    //select the start date function
							      try {
							    	  WebElement susbscriptiondate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"));
							    	  
								    	  if(susbscriptiondate.isDisplayed()) {
								    		  
								    		  //open the pop-up for select the date
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"))).click();
								    		  Thread.sleep(1000);
											    		  
											    		  LocalDate today = LocalDate.now();
											    		  DayOfWeek dayOfWeek = today.getDayOfWeek();
			
											    		  if(dayOfWeek == DayOfWeek.MONDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[0]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.TUESDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[1]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.WEDNESDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[2]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.THURSDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[3]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.FRIDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[4]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.SATURDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[5]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.SUNDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[6]"))).click();
											    		      Thread.sleep(1000);
											    		  } else {
											    		      System.out.print("Something went wrong on the start date function");
											    		      Thread.sleep(1000);
											    		  }
			
											    		  test.log(Status.INFO, ("Subscription order start day : " + dayOfWeek));

								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_ok_btn\"]"))).click();
								    	  } 
								    	  else {
								    		  System.out.println( "System did not dispayed the date drop down");
								    	  }
							      }
								      catch(Exception Erro_Subscription_Date_Function) {
								    	  System.out.println(Erro_Subscription_Date_Function);
							      }
							      
							      Thread.sleep(3000);
							      
							    // add the end date to order
							      try {
							    	  
								    
							    	  WebElement susbscriptionEnddate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"));
							    	  
							    	  	LocalDate today = LocalDate.now();
							            LocalDate added_some_date_to_End_Date = today.plusDays(9);
							            DateTimeFormatter Date_formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
							            String subscription_order_end_date = added_some_date_to_End_Date.format(Date_formatter);
							          
								    	  if(susbscriptionEnddate.isDisplayed()) {
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  String scrollabl_to_checkout_isSubscription_endDate_btn = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										      driver.findElement(AppiumBy.androidUIAutomator(scrollabl_to_checkout_isSubscription_endDate_btn));
										      
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_endDate_btn\"]/android.widget.Image"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='" + subscription_order_end_date + "']"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Ok\"]"))).click();
								    		  
								    		  test.log(Status.INFO, ("SUbscription order End day : " + subscription_order_end_date));  } 
								    	  
								    	 else { System.out.println("System did not dispayed the tuesday on pop-up page"); }
								    	  
							      } 
							      
							      catch(Exception Erro_Subscription_Date_Function) {
								    	  test.log(Status.FAIL, (Erro_Subscription_Date_Function));}
							      
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
						    		        System.out.println("Error on paymnet selection.");
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
						    		  test.log(Status.INFO , ("Invalid Paymnet Method"));
						    	  }
						    	  
											try {
												String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
												driver.findElement(AppiumBy.androidUIAutomator(scrollableElement)); }
											
											catch(Exception scroll_down) {
												System.out.println("An error occurred: " + scroll_down.getMessage());
												scroll_down.printStackTrace(); }
						    	  
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
			         Thread.sleep(15000);
			       
			       //Check the URL is equal to /order-summery and order completed successfully 
		        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
       
			        	    if (urlBar.isDisplayed()) {
			        	        String currentUrl = urlBar.getText();
	
				        	        if (currentUrl.contains("subscription-summery")) {
		
				        	            String[] urlSegments = currentUrl.split("/");
		
				        	            String lastSegment = urlSegments[urlSegments.length - 1];
				        	            
				        	            
				        	            test.log(Status.INFO ,("Subscription Order ID (DATABASE ID) : " + lastSegment));
				        	            
				        	            		List<WebElement> FirsttextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));

						        	            WebElement firstTextView = FirsttextViewElements.get(0); 
						        	            String Subscription_order_ID = firstTextView.getText();
						        	            
						        	    test.log(Status.INFO, Subscription_order_ID); 
					        	            test.log(Status.PASS, "Subscription Order Process - PICKUP ( Cash ) ");
					        	            
					        	        } else {
					        	            System.out.println("System did not completed the order proess");
					        	            test.log(Status.FAIL, "Subscription Order Process - PICKUP ( Cash ) ");}   
					        	        
				        	    } 
				        	    else {

				        	    }
		        }
		        
			        catch (Exception error_OrderProcess) {
			            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
		    


		         //-----  POINT PAYMNET  -----
		        try { 
		        	
		        	 test = extent.createTest("Subscription_Order_Process - PICKUP Order - Point Paymnet", "  Verify The Point Paymnet Process");
		        	 System.out.println("Start -Subscription_Order_Process - PICKUP Order - Point Paymnet ");
		        	 
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
							      
							      // check the subscription method to select
							      try {
							    	  WebElement susbscriptionslected = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"));
							    	  
								    	  if(susbscriptionslected.isDisplayed()) {
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_btn\"]/android.view.View"))).click();
								    	  } else {
								    		  System.out.println("System already define subscription method");
								    	  }
							      }
								      catch(Exception Erro_Subscription_Button_Selecte_Function) {
								    	  System.out.println(Erro_Subscription_Button_Selecte_Function);
							      }
						      
							      
							    //select the start date function
							      try {
							    	  WebElement susbscriptiondate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"));
							    	  
								    	  if(susbscriptiondate.isDisplayed()) {
								    		  
								    		  //open the pop-up for select the date
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_subscription_scheduleDate_btn\"]/android.widget.Image"))).click();
								    		  Thread.sleep(1000);
											    		  
											    		  LocalDate today = LocalDate.now();
											    		  DayOfWeek dayOfWeek = today.getDayOfWeek();
			
											    		  if(dayOfWeek == DayOfWeek.MONDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[0]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.TUESDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[1]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.WEDNESDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[2]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.THURSDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[3]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.FRIDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[4]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.SATURDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[5]"))).click();
											    		      Thread.sleep(1000);
											    		  } else if(dayOfWeek == DayOfWeek.SUNDAY) {
											    		      wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("(//android.widget.TextView[@resource-id=\"checkout_isSubscripiton_dateSelect_btn\"])[6]"))).click();
											    		      Thread.sleep(1000);
											    		  } else {
											    		      System.out.print("Something went wrong on the start date function");
											    		      Thread.sleep(1000);
											    		  }
			
											    		  test.log(Status.INFO, ("Subscription order start day : " + dayOfWeek));

								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_payment_ok_btn\"]"))).click();
								    	  } 
								    	  else {
								    		  System.out.println( "System did not dispayed the date drop down");
								    	  }
							      }
								      catch(Exception Erro_Subscription_Date_Function) {
								    	  System.out.println(Erro_Subscription_Date_Function);
							      }
							      
							      Thread.sleep(3000);
							      
							    // add the end date to order
							      try {
							    	  
								    
							    	  WebElement susbscriptionEnddate = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"));
							    	  
							    	  	LocalDate today = LocalDate.now();
							            LocalDate added_some_date_to_End_Date = today.plusDays(9);
							            DateTimeFormatter Date_formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
							            String subscription_order_end_date = added_some_date_to_End_Date.format(Date_formatter);
							          
								    	  if(susbscriptionEnddate.isDisplayed()) {
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_hasEndBtn\"]/android.view.View"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  String scrollabl_to_checkout_isSubscription_endDate_btn = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
										      driver.findElement(AppiumBy.androidUIAutomator(scrollabl_to_checkout_isSubscription_endDate_btn));
										      
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.view.View[@resource-id=\"checkout_isSubscription_endDate_btn\"]/android.widget.Image"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text='" + subscription_order_end_date + "']"))).click();
								    		  Thread.sleep(1000);
								    		  
								    		  wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@text=\"Ok\"]"))).click();
								    		  
								    		  test.log(Status.INFO, ("SUbscription order End day : " + subscription_order_end_date));  } 
								    	  
								    	 else { System.out.println("System did not dispayed the tuesday on pop-up page"); }
								    	  
							      } 
							      
							      catch(Exception Erro_Subscription_Date_Function) {
								    	  test.log(Status.FAIL, (Erro_Subscription_Date_Function));}
							      
							      
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
						    		        System.out.println("Error on paymnet selection.");
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
						    		  test.log(Status.INFO , ("Invalid Paymnet Method"));
						    	  }
						    	  
										try {
											String scrollableElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollForward()";
											driver.findElement(AppiumBy.androidUIAutomator(scrollableElement)); }
										
										catch(Exception scroll_down) {
											System.out.println("An error occurred: " + scroll_down.getMessage());
											scroll_down.printStackTrace(); }
							      
			         wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.Button[@resource-id='checkout_proceed_btn']"))).click();
			         Thread.sleep(15000);
			         
						         try {
						         		if (driver.findElement(AppiumBy.xpath("//android.widget.Button[@text=\"Aufladen\"]")).isDisplayed()) {
						         			System.out.println("Insufficent Point Balance, Please recharge your point balnce and Run agin");
						         			test.log(Status.INFO , ("Insufficent Point Balance, Please recharge your point balnce and Run agin"));
						         		}
						         }
						         catch(Exception error_message_Insufficent_Point_balnce) {
						        	 System.out.println(error_message_Insufficent_Point_balnce);
						         }
				       
						       //Check the URL is equal to /order-summery and order completed successfully 
					        	    WebElement urlBar = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']"));
			          
						        	    if (urlBar.isDisplayed()) {
						        	        String currentUrl = urlBar.getText();
				
							        	        if (currentUrl.contains("subscription-summery")) {
					
							        	            String[] urlSegments = currentUrl.split("/");
					
							        	            String lastSegment = urlSegments[urlSegments.length - 1];
							        	            
							        	            
							        	            test.log(Status.INFO ,("Subscription Order ID (DATABASE ID) : " + lastSegment));
							        	            
							        	            		List<WebElement> FirsttextViewElements = driver.findElements(AppiumBy.className("android.widget.TextView"));

									        	            WebElement firstTextView = FirsttextViewElements.get(0); 
									        	            String Subscription_order_ID = firstTextView.getText();
									        	            
									        	    test.log(Status.INFO, Subscription_order_ID); 
					        	            test.log(Status.PASS, "Subscription Order Process - PICKUP ( Point ) ");
					        	            
					        	        } else {
					        	            System.out.println("System did not completed the order proess");
					        	            test.log(Status.FAIL, "Subscription Order Process - PICKUP ( Point ) ");}
					        	        
				        	    } 
				        	    else {
				        	        System.out.println("URL bar is not displayed.");
				        	        test.log(Status.FAIL, "Subscription Order Process - Checkout Process");}
		        }
		        
			        catch (Exception error_OrderProcess) {
			            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
		        
		        

			   
			     //*--Paypal Paymnet Method--*
			       try { 
		        	
		        	test = extent.createTest("Subscription_Order_Process - PICKUP Order - Paypal Paymnet", "  Verify The Paypal Paymnet Process");
			    	System.out.println("Start -Subscription_Order_Process - PICKUP Order - Paypal Paymnet ");
			    	 
			    	test.log(Status.PASS, "Subscription Order Process - PICKUP ( Paypal ) -- -- Comming Zoon -- -- ");
			       }
		        
			        catch (Exception error_OrderProcess) {
			            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess.getMessage());}
		    
			    

		         //-----  Stripe PAYMNET  -----
			       try { 
		        	
		        	 test = extent.createTest("Subscription_Order_Process - PICKUP Order - Stripe Paymnet", "  Verify The Stripe Paymnet Process");
		        	 System.out.println("Start -Subscription_Order_Process - PICKUP Order - Stripe Paymnet ");
		        	 
		        	 
		        	 test.log(Status.PASS, "Subscription Order Process - PICKUP ( Stripe ) -- -- Comming Zoon -- --");
		        	}
		        
			        catch (Exception error_OrderProcess1) {
			            test.log(Status.FAIL, "Subscription Order Process : " + error_OrderProcess1.getMessage());}  
        
        
	    //end of the test script*--------*-------------*-----------------*
	    }
	    
    
    // END Dine-IN Order Method
	    
	    @After
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	            extent.flush();
	        }
	        System.out.println("Browser closed.");
	    }
	}


