package framework.objects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class UserMenu extends WindowMenu{
	//define identification 
	public String userMenuButton = ".//*[@id='userMenu']";
	public String signOutMenu = ".//*[@id='out']/a";
	public String preferenceMenu = ".//*[@id='pref']/a";
	public String helpMenu = ".//*[@id='help']/a";
	public String aboutMenu = ".//*[@id='about']/a";
	
	
	//Click user menu button
	public void clickUserMenu() throws InterruptedException{
		ActionMenu = driver.findElement(By.xpath(userMenuButton));
		
		if (ActionMenu.isEnabled()){
			ActionMenu.click();	
		}
		Thread.sleep(5000);
	}
	
	
	public void clickAbout() throws InterruptedException{
		ActionMenu = driver.findElement(By.xpath(aboutMenu));
		
		if (ActionMenu.isEnabled()){
			ActionMenu.click();	
		}
		Thread.sleep(5000);
	}
	
	
	//Click Sign Out menu
	public void clickSignOut() throws InterruptedException {				
		
//		this.clickUserMenu();
		
		Actions actions = new Actions(driver);
		
		WebElement sign_out_menu = driver.findElement(By.xpath(signOutMenu));
	    
	    actions.moveToElement(sign_out_menu).build().perform();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    actions.click().build().perform();

	    		
//		if (driver.findElement(By.xpath(signOutMenu)).isEnabled())
//		{
//			driver.findElement(By.xpath(signOutMenu)).click();
//		  	logger.info("Click Sign Out menu");		
//		}else{
//		  	logger.info("Sign Out menu is not avaible");
////		  	System.out.println("Create Library Button is Disabled");
//		}
	  	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
}
