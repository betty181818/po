package framework.objects;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class LogoutDialog extends CommonVariables{
    //define identification conditions
	public String warningDiloagId = "logout_warning_dialog";
//	public String yesButtonId = "logout_ok";
	public String yesButtonXpath = ".//*[@id='logout_ok']/button";
	public String noButtonId = "logout_cancel";
	public String messageBodyId = "logout_warning_dialog_body";
	public String messageTitleId = "logout_warning_dialog_title";

	public LogoutDialog(){		
		
	}
		
	public void clickYesButton(){
		driver.findElement(By.xpath(yesButtonXpath)).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickNoButton(){
		driver.findElement(By.id(noButtonId)).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
    public boolean isConfirmationDialogPopup(){
    	return driver.findElement(By.id(warningDiloagId)).isDisplayed();
    }
	
    public String warningDialogTitle(){
    	return driver.findElement(By.id(messageTitleId)).getText();
    }
    
    public String warningDialogMessage(){
    	return driver.findElement(By.id(messageBodyId)).getText();
    }
}
