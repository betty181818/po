/**
 * 
 */
package framework.objects;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import framework.global.Configuration;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class IBrowser extends CommonVariables {
	
	public String myRecentJobsPanelId = "my_recent_jobs_DS";
	public String navigation_barId = "navigation_bar";
	public String systemMonitoringPanelId = "system_monitoring_DS";
	public String loginTimePanelId="login_time_DS";
	public String loginCountPanelId="login_cnt_DS";
	public String systemAlertLogId="System_Alert_Logs_DS";
	public String keyRunningJobsPanelId="key_running_jobs_DS";
	public String myFootprintsPanelId="my_footprints_DS";
	public String demoTutorialsPanelId="demo_tutorials_DS";
		
	public String jobsStatusPanelId = "job_status_DS";
	public String service_detail_titleID = "service_detail_title";
	public String pre_migrate_title_areaID = "pre_migrate_title_area";
//	<div id="my_recent_jobs_DS" class="oj-panel flex-panel-dashboard" style="display:none">
//	<div id="system_monitoring_DS" class="oj-panel flex-panel-dashboard" style="display:block">
	

public IBrowser(){
		
	}
		
	public void Browser_Maximize(){		
		driver.manage().window().maximize();	
		
	}
	
	public void Start_Browser() throws IOException, InterruptedException {
       
		String url=Configuration.srvconf.getUIUrl();
           
        try { 
        	driver.get(url);               
        }
        catch (Exception e) {
        	Thread.sleep(3000);
            logger.warn("Error with Start_Browser with first attempt" +  "The error thrown" + e);            
            try {
               	driver.get(url);  
            }
            catch (Exception e1) {
            	logger.warn("Error with Start_Browser with second attempt" + "The error thrown" + e1);
                System.out.println(e1);
            }
        }           
//		      
        Browser_Maximize();        
    }
	
	public void Start_Browser2() throws IOException, InterruptedException {
	       
		String appurl=Configuration.srvconf.getUIAdvancedUrl();
           
        try { 
        	driver.get(appurl);               
        }
        catch (Exception e) {
        	Thread.sleep(3000);
            logger.warn("Error with Start_Browser with first attempt" +  "The error thrown" + e);            
            try {
               	driver.get(appurl);  
            }
            catch (Exception e1) {
            	logger.warn("Error with Start_Browser with second attempt" + "The error thrown" + e1);
                System.out.println(e1);
            }
        }           
//		      
        Browser_Maximize();        
    }
	

	
	public void Browser_Wait(long waittime){
		try{
			Thread.sleep(waittime);
		}
		catch(InterruptedException e){
			e.printStackTrace();
			
		}
		//driver.manage().timeouts().implicitlyWait(waittime, TimeUnit.SECONDS);
	}
	
//	public boolean isLoginTimeDisplayed(){		
//		 
//		if (driver.findElement(By.id(loginTimePanelId)).getAttribute("style").equals("display:block")){	
//			logger.info("The Login Time panel is displayed.");
//			return true;
//		}
//		else{
//			Browser_Wait(1000);
//			if (driver.findElement(By.id(loginTimePanelId)).isDisplayed()){	
//				logger.info("The Login Time panel is displayed.");
//				return true;
//			}
//			else{
//				logger.info("Time out in loading Login Time panel.");
//				return false;
//			}
//	    }	
//	}	
//	
//	public boolean isLoginCountDisplayed(){		
//		 
//		if (driver.findElement(By.id(loginCountPanelId)).getAttribute("style").equals("display:block")){	
//			logger.info("The Login Count Panel is displayed.");
//			return true;
//		}
//		else{
//			Browser_Wait(1000);
//			if (driver.findElement(By.id(loginCountPanelId)).isDisplayed()){	
//				logger.info("The Login Count Panel is displayed.");
//				return true;
//			}
//			else{
//				logger.info("Time out in loading The Login Count Panel.");
//				return false;
//			}
//	    }	
//	}	
//	
//	public boolean isSystemAlertLogDisplayed(){		
//		 
//		if (driver.findElement(By.id(systemAlertLogId)).getAttribute("style").equals("display:block")){	
//			logger.info("The System Alert Logs Panel is displayed.");
//			return true;
//		}
//		else{
//			Browser_Wait(1000);
//			if (driver.findElement(By.id(loginCountPanelId)).isDisplayed()){	
//				logger.info("The System Alert Logs Panel is displayed.");
//				return true;
//			}
//			else{
//				logger.info("Time out in loading System Alert Logs Panel.");
//				return false;
//			}
//	    }	
//	}	
//	
//	
//	public boolean isKeyRunningJobsDisplayed(){		
//		 
//		if (driver.findElement(By.id(keyRunningJobsPanelId)).getAttribute("style").equals("display:block")){	
//			logger.info("The Key Running Jobs Panel is displayed.");
//			return true;
//		}
//		else{
//			Browser_Wait(1000);
//			if (driver.findElement(By.id(keyRunningJobsPanelId)).isDisplayed()){	
//				logger.info("The Key Running Jobs Panel is displayed.");
//				return true;
//			}
//			else{
//				logger.info("Time out in loading Key Running Jobs Panel.");
//				return false;
//			}
//	    }	
//	}	
//	
//	public boolean isMyFootprintsDisplayed(){		
//		 
//		if (driver.findElement(By.id(myFootprintsPanelId)).getAttribute("style").equals("display:block")){	
//			logger.info("The My Footprints Panel is displayed.");
//			return true;
//		}
//		else{
//			Browser_Wait(1000);
//			if (driver.findElement(By.id(myFootprintsPanelId)).isDisplayed()){	
//				logger.info("The My Footprints Panel is displayed.");
//				return true;
//			}
//			else{
//				logger.info("Time out in loading My Footprints Panel.");
//				return false;
//			}
//	    }	
//	}	
//	
//	public boolean isDemoTutorialsDisplayed(){		
//		 
//		if (driver.findElement(By.id(demoTutorialsPanelId)).getAttribute("style").equals("display:block")){	
//			logger.info("The Demo Tutorials Panel is displayed.");
//			return true;
//		}
//		else{
//			Browser_Wait(1000);
//			if (driver.findElement(By.id(demoTutorialsPanelId)).isDisplayed()){	
//				logger.info("The Demo Tutorials Panel is displayed.");
//				return true;
//			}
//			else{
//				logger.info("Time out in loading Demo Tutorials Panel.");
//				return false;
//			}
//	    }	
//	}	
//	
//
//
//	
//	public boolean isVolumePageDisplayed(){		
//		
//		// check to see if "Manage Volumes" wording is displayed.
//		if (driver.findElement(By.id("pt1:r1:0:sdi1::disAcr")).getText().trim().equals("Manage Volumes")){
//			logger.info("The Volumes Page is displayed.");			
//			return true;
//		}
//		else{
//		    logger.info("Failed to display the Volumes Page.");
//			return false;
//		}	
//							
//	}	
//	
//	public boolean isDrivePageDisplayed(){		
//		 
//		// check to see if "Manage Drives" tab is displayed.		
//		if (driver.findElement(By.id("pt1:r1:0:sdi1::disAcr")).getText().trim().equals("Manage Drives")){
//			logger.info("The Drives Page is displayed.");			
//			return true;
//		}
//		else{
//			Browser_Wait(5);
//			if (driver.findElement(By.id("pt1:r1:0:sdi1::disAcr")).getText().trim().equals("Manage Drives")){	
//				logger.info("The Drives Page is displayed.");
//				return true;
//			}
//			else{
//				logger.info("Failed to display the Drives Page.");
//				return false;
//			}
//		}
//	}
//	
//	public boolean isAssignShuttlePageDisplayed() {		
//		 
//		//LeftULID="pt1:r1:0:sms1::leadUl";
//		//RightULID="pt1:r1:0:sms1::trailUl";
//		
//		if (driver.findElement(By.id("pt1:r1:0:sms1::leadUl")).isDisplayed()) {	
//			logger.info("The Assign/Unassign ShuttleBox is displayed.");
//			return true;
//		} else {			
//				return false;
//			}
//	}
	
	/**
	 * 
	 * @param Locator
	 * @return
	 */
	
	public boolean ElementExist(By Locator) {
		try {
			driver.findElement(Locator);
			return true;
		} catch (NoSuchElementException ex) {
			return false;
		}
	}
	
}
