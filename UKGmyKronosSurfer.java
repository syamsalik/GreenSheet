package greenSheet;




import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UKGmyKronosSurfer {


    public void GetUKG() {
    	
    	
    	ToClean clean = new ToClean();
    	Resources Resources = new Resources();
    	
        ChromeOptions co = new ChromeOptions();
        co.setBrowserVersion("118");
        WebDriver driver = new ChromeDriver();
 	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 	   

        String usernameFilePath = Resources.getUserName();
        String passwordFilePath = Resources.getPassword();
        String username = readFileContent(usernameFilePath).trim();
        String password = readFileContent(passwordFilePath).trim();

        try {
            driver.get(Resources.getKronosURL());
            
            // Wait for the username input to become visible and then send the username
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idToken1"))).sendKeys(username);

            // Wait for the password input to become visible and then send the password
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("idToken2"))).sendKeys(password);

            // Wait for the login button to become visible and then click it
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginButton_0"))).click();
            
            
            WebElement scheduleButtons = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("viewMyScheduleLink-240")));
            Thread.sleep(2000);
            scheduleButtons.click();



               try { 
            	// Wait for the page to load or for the "Load more" button to become visible
                Thread.sleep(2000);
                JavascriptExecutor js = (JavascriptExecutor) driver;
                // Execute JavaScript to click the "Load more" button twice
                js.executeScript("document.querySelector('#my-schedule-list > ukg-button:nth-child(3)').click();");
                Thread.sleep(2000);  // Wait for the first click action to complete
                js.executeScript("document.querySelector('#my-schedule-list > ukg-button:nth-child(3)').click();");
               
            	   
               }
               catch (Exception e) {
            	   System.out.println("Problem when trying to load more \n ");   
               } 
               
               
               // The try block loads past events and 
               //The number n means how many past loads are needed For loading past events.
               Thread.sleep(9000);
            try {
            	
            	//FIXME number of past loads is less than one now 
            	 int n = 0;
            	 
            for (int i = 0; i < n; i++) {
            	
            	Thread.sleep(2000);
            	  
            	
            	WebElement button = driver.findElement(By.cssSelector("#my-schedule-list > ukg-button:nth-child(1)"));
            	((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
            	
            	Thread.sleep(2000);
            }
            
            } catch (Exception e) {
                System.out.println("Error clicking the button: " + e.getMessage());
                // Optional: Add logic to handle if the button isn't found or some other error occurs.
            } 
            
            
            
	    	   try {
	    	   BufferedWriter writer = new BufferedWriter(new FileWriter(Resources.getFileOutPutLocation()));
               
               try {

            	   List<WebElement> listItems = driver.findElements(By.cssSelector("#my-schedule-list ng-myschedule-day"));
   
            	   for (WebElement listItem : listItems) {
            	       String automationId = listItem.getAttribute("automation-id");

            	       clean.workTag(automationId, listItem.getText());
                       
            	       
            	      
            	    	   writer.write("\n" + clean );
            	    	   //FIXME info printed out has logic error and only doesn't print all the info 
            	    	   //System.out.println(clean);         	    	 
            	    	   //System.out.println(clean.getRawInfo(listItem.getText()));
            	    	   //System.out.println(clean.getWeekDay());

            	    
            	       try {
            	           // Search for the button within the context of the current listItem
            	           WebElement button =  wait.until(ExpectedConditions.elementToBeClickable(listItem.findElement(By.className("interactive"))));

            	           JavascriptExecutor js = (JavascriptExecutor) driver;
            	           js.executeScript("arguments[0].scrollIntoView();", button);
            	           
            	           if (button != null) {
            	        	   Thread.sleep(2000); 
            	        	   js.executeScript("arguments[0].scrollIntoView();", button);
            	               button.click();

            	               // Assuming some delay for the sidebar to appear. Better would be to use WebDriverWait.
            	                   Thread.sleep(1000);  // wait for 1 seconds

            	               // Now extract comments from the sidebar
            	               for (WebElement commentSection : driver.findElements(By.cssSelector(".comments.ng-star-inserted"))) {
            	                   WebElement titleElement = commentSection.findElement(By.cssSelector(".comment-detail"));
            	                   

            	                   // Check if there are associated notes for the title
            	                   try {
            	                       WebElement notesElement = commentSection.findElement(By.cssSelector(".notes.ng-star-inserted p"));
            	                       
            	                       //FIXME the side bar info is printed as raw data, needs processing for each event
            	                       //System.out.println(titleElement.getText() + ": " + notesElement.getText());
            	                       //System.out.println(titleElement.getText());
            	                       writer.write(titleElement.getText() + ": " + notesElement.getText()+ "|");
            	                       
            	                       
            	                   } catch (org.openqa.selenium.NoSuchElementException e) {
            	                       //System.out.println("No details for this title.");
            	                   }

            	                   
            	               }

            	               WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("ukg-button[parent-icon='close']")));
            	               closeButton.click();
            	               
            	           }

            	           Thread.sleep(1000);

            	       } catch (Exception e) {
            	           System.out.println("Error from trying to access comments");
            	         
            	       }
            	       
            	       writer.write("\n---------------------------------------" );
            	       
            	       



                   }
     
  
	    	   writer.close();}
               
	    	   catch(IOException e) {    	    		   
	    		   System.out.println("Error occured while making the file");
	    	   }   
            	   
   
               }

               
               catch(Exception e) {
            	   System.out.println("Problem while accessing informtion about date ID");
            	 
               }
                    

        } 
        
        
        catch (Exception e) {
            e.printStackTrace();

        }
        
        
        finally {
            driver.quit();
        }
    }

    private static String readFileContent(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Separate class for ToClean
    public static class ToClean {
        // Class fields
        private String date;
        private String time;
        private String subdivisions;
        private String protection;
        private String con;
        private String weekday;

        // Constructor
        public ToClean() {}

        	
        	public String getRawInfo(String info){
        		
        		return info;
        		
        	}
        	
        	public void workTag(String WorkIdInput, String content) {
        		
        		this.date = "";
        		this.time = "";
        		this.subdivisions = "";
        		this.protection = "";
        		this.con = "";
        		this.weekday = "";
        		
        		this.date = WorkIdInput.replace("myschedule-day_" , "");   
        		this.time = timeExtractor(content);
                this.subdivisions = subExtractor(content);
                this.protection = protectionExtractor(content);  
                this.con = content;
                this.con = this.con.replace("\n", "|");
                this.weekday = weekDay(content);
        	
        	}
        	
        	public String timeExtractor (String text) {
        		
        		String regex = "\\d{2}:\\d{2}-\\d{2}:\\d{2} \\[\\d+\\.\\d+\\]";
        		Pattern pattern = Pattern.compile(regex);
        		Matcher matcher = pattern.matcher(text);
        		
        			if (matcher.find())
        			{
        		        // Extract the matched string
        		        String matchedString = matcher.group();

        		        // Remove the \\[\\d+\\.\\d+\\] part
        		        String trimmedString = matchedString.replaceAll("\\[\\d+\\.\\d+\\]", "");

        		        // Replace '-' with '|'
        		        String finalString = trimmedString.replace("-", "|");

        		        return finalString;
        				
        			}
        		
        		return "";
        	}
        	
        	public String protectionExtractor (String text) {
        		
        		String[] protections = {"CN TOP", "842 Foreman", "Sub-Foreman", 
        								"Foreman Oversight", "EIC" ,
        								"Spare", "OTM" , "Nite OFF"};
        		
        		for (String protection : protections) {
        			
        			int index = text.indexOf(protection);
        			if (index != -1)
        			{
        				return protection;
        			}			
        		}	
        		return "";
        		
        	}
        	
        	
        	
        	public String weekDay (String text) {
        		
        		String[] day = {"Mon", "Tue", "Wed", 
        								"Fri", "Thu", "Sun" , "Sat"};
        		
        		for (String d : day) {
        			
        			int index = text.indexOf(d);
        			if (index != -1)
        			{
        				return d;
        			}			
        		}	
        		return "";
        		
        	}
        	
        	
        	public String getWeekDay() {	
        		
        		return this.weekday;
        		
        	}
        	

        	
        	public String subExtractor (String text) {
        		
        		String[] subs = {	"Kingston Sub","Guelph Sub", "Newmarket Sub",
        							"Bala Sub", "Uxbridge Sub", "Go Sub", "Weston Sub", 
        							"Oakville Sub", "USRC"};
        		for (String sub : subs ) {
        			
        			int index = text.indexOf(sub);
        			if (index != -1)
        				{
        				return sub;
        				}		
        		}
        		
        		return "";
        	}
        	
        	@Override
        	public String toString(){
        		
        		//if (this.protection == "Nite OFF") {
        		//	return "";	
        		//}
        		
        		//else 
        			if (this.time != "" || this.subdivisions != "") {
        			
        			return 
        					
        					this.date + "|" + this.time + "|"  
        					+ this.subdivisions + " " + this.protection + "|" 
        					+ this.weekday + "|" + "Content: " + this.con +  "|"
        					;
        			
        		}
        		
        		return 	"";
        	}
        	
        	public String getProtection() {
        		
        		return this.protection;
        		
        	}


    }
}

