package framework.objects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class WindowPopupInfo extends WindowPopup {
	
	
	public WindowPopupInfo(){		
		
		
	}
	
	  public void clickPopupSingleOK(){
	    	logger.info("-------------> click windows popup Single OK button start:");
			  System.out.print("-------------> windows popup Single OK button start:");
			  driver.findElement(By.id("d1::msgDlg::cancel")).click();
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  logger.info("-------------> click windows popup Single OK button end");
		      System.out.print("-------------> click windows popup Single OK button end");
	    	
	    } 
	  
	  public void clickPopupSingleCancel(){
	    	logger.info("-------------> click windows popup Single Cancel button start:");
			  System.out.print("-------------> windows popup Single Cancel button start:");
			  driver.findElement(By.id("pt1:r1:0:pc1:d6::cancel")).click();
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  logger.info("-------------> click windows popup Single Cancel button end");
		      System.out.print("-------------> click windows Single Cancel  button end");
	    	
	    }
	
	   public void clickPopupOK(){
	    	logger.info("-------------> click windows popup OK button start:");
			  System.out.print("-------------> windows popup OK button start:");
			  driver.findElement(By.id("pt1:r1:0:pc1:d1::ok")).click();
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  logger.info("-------------> click windows popup OK button end");
		      System.out.print("-------------> click windows popup OK button end");
	    	
	    }
	    
	    public void clickPopupCancel(){
	    	logger.info("-------------> click windows popup Cancel button start:");
			  System.out.print("-------------> windows popup Cancel button start:");
			  driver.findElement(By.id("pt1:r1:0:pc1:d1::cancel")).click();
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  logger.info("-------------> click windows popup Cancel button end");
		      System.out.print("-------------> click windows Cancel OK button end");
	    	
	    }
	    
	    public void clickOK(){
	    	
			 
			  driver.findElement(By.id("pt1:r1:0:cb2")).click();
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  logger.info("-------------> click  OK button end");
		     
	    	
	    }
		
		public void clickCancel(){
			
			  logger.info("-------------> clickCancel start:");
			  System.out.print("-------------> clickCancel start:");
			  driver.findElement(By.xpath("//table[@id='pt1:r1:0:sms1::content']/tbody/tr/td[3]")).click(); 
			  driver.findElement(By.id("pt1:r1:0:cb1x")).click();
			  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  logger.info("-------------> clickCancel end");
		      System.out.print("-------------> clickCancel end");
		}
		
		public void clickYes(){}
		
		public void clickNo(){}
		
		public void ClickActionMenu() {
			   		
		}
		public void clickApply(){
			  logger.info("-------------> clickApply start:");
			    System.out.print("-------------> clickApply start:");
			    driver.findElement(By.xpath("//table[@id='pt1:r1:0:sms1::content']/tbody/tr/td[3]")).click();
			    driver.findElement(By.id("pt1:r1:0:cb3")).click();
			    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			    logger.info("-------------> clickApply end");
			    System.out.print("-------------> clickApply end");
}
		
		public void clickDriveApply(){
			 
			  
			    driver.findElement(By.id("pt1:r1:0:applyBtn")).click();
			    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			    logger.info("Click Create Library Apply button successfully.");
			    
}
		
		
		
		public void clickMemoryButtonOK(){
			 
			//baseUrl = "http://leroi3.us.oracle.com:7001/";
			//driver.get(baseUrl + "/LTFS/faces/blank.jspx?_afrWindowMode=0&_afrLoop=2608124983869&_adf.ctrl-state=1p4rp8z42_9");
		    driver.findElement(By.id("d2::ok")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    logger.info("Click Create Library Apply button successfully.");
		    
		    
}
		
		
		public void clickFormatPopButtonOK(){
			 
			//baseUrl = "http://leroi3.us.oracle.com:7001/";
			//driver.get(baseUrl + "/LTFS/faces/blank.jspx?_afrWindowMode=0&_afrLoop=2608124983869&_adf.ctrl-state=1p4rp8z42_9");
		    driver.findElement(By.id("pt1:r1:0:pc1:d9::ok")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    logger.info("Click Create Library Apply button successfully.");
		    
		    
}
		
		public void clickFormatPopButtonOK2(){
			 
			//baseUrl = "http://leroi3.us.oracle.com:7001/";
			//driver.get(baseUrl + "/LTFS/faces/blank.jspx?_afrWindowMode=0&_afrLoop=2608124983869&_adf.ctrl-state=1p4rp8z42_9");
		    driver.findElement(By.id("pt1:r1:0:pc1:d10::ok")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    logger.info("Click Create Library Apply button successfully.");
		    
		    
}
		
		public void clickSettingsVolumePoolApply(){
			 
			  
		    driver.findElement(By.id("pt1:r1:0:cb_apply")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    logger.info("Click Settings -> VolumePool Apply button successfully.");
		    
}
		
		public void clickSettingsVolumePoolPopOK(){
			 
		
		    driver.findElement(By.id("pt1:r1:0:d5::ok")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    logger.info("Click Create Library Apply button successfully.");
		    
}
		
		public void clickSettingsVolumePoolOK(){
			 
			
		    driver.findElement(By.id("pt1:r1:0:cb_ok")).click();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    logger.info("Click Create Library Apply button successfully.");
		    
}
		

}
