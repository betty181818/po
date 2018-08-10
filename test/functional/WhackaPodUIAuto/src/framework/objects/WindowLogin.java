package framework.objects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class WindowLogin extends CommonVariables{
    //define identification conditions
	public String UserNameInputId = "input_user_name";
	public String PasswordInputId = "input_password";
	//multi language support lixia 2017/05/15
	//public static String LoginButton = "//input[@value='Login']";
	public static String LoginButton = "//*[@id='login_button']";
	//multi language support lixia 2017/05/15
	public String RememberUserNameRadioButtonId = "forget_radio";
	public static String WrongCredentialsMessageId = "error_message";

	public WindowLogin(){		
		
	}
		
	public String getUserName(){
		
//		String username = driver.findElement(By.xpath(UserNameInput)).getText();
		String username = driver.findElement(By.id(UserNameInputId)).getAttribute("value");
		return username;
		
	}
	
	public void setUserName(String username){
		driver.findElement(By.id(UserNameInputId)).clear();		
		driver.findElement(By.id(UserNameInputId)).sendKeys(username);				

	}
	public String getPwd(){
		String pwd = driver.findElement(By.id(PasswordInputId)).getText();
		return pwd;
	}
	
	public void setPwd(String pwd){
		driver.findElement(By.id(PasswordInputId)).clear();
		driver.findElement(By.id(PasswordInputId)).sendKeys(pwd);		
	}

   public void clickLoginButton(){  	 	
		
  	driver.findElement(By.xpath(LoginButton)).click();
  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  	
  	
  	//click memory info button 
  	//if(driver.findElement(By.id("d2::ok")).isEnabled()){
  		//driver.findElement(By.id("d2::ok")).click();}
  	
  	//driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   }
   
   public void checkRememberUserNameRadioButton(){
	   driver.findElement(By.id(RememberUserNameRadioButtonId)).click();
	   driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   }
   
   
   public void clickMemoryInfButton(){  	 	
	  	
	  	
	  	//click memory info button 
	    if(driver.findElement(By.id("pt1:d213::ok")).isDisplayed()){
	  		driver.findElement(By.id("pt1:d213::ok")).click();}
	    else{
	    	
	    	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    }
	  	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  	
   }
   public static boolean isLoginButtonDisplayed() throws InterruptedException{		
		 
		if (driver.findElement(By.xpath(LoginButton)).isDisplayed()){	
			logger.info("The Login Button is displayed.");
			return true;
		}
		else{
			Thread.sleep(10000);
			if (driver.findElement(By.xpath(LoginButton)).isDisplayed()){	
				logger.info("The Login Button is displayed.");
				return true;
			}
			else{
				logger.warn("Time out in switching to Login page.");
				return false;
			}
					
	    }	
	}	
   
   public static boolean isWrongCredentialsMessageDisplayed() throws InterruptedException{		
		 
		if (driver.findElement(By.id(WrongCredentialsMessageId)).getText().equals("Invalid Username or Password!")){	
			logger.info("The Wrong Credentials Message is displayed.");
			return true;
		}
		else{
			Thread.sleep(10000);
			if (driver.findElement(By.xpath(LoginButton)).isDisplayed()){	
				logger.info("The Wrong Credentials Message is displayed.");
				return true;
			}
			else{
				logger.warn("The Wrong Credentials Message isn't displayed.");
				return false;
			}
					
	    }	
	}
	
}
