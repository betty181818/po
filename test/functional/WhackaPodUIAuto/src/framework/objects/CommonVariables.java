package framework.objects;

import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import log.LogHandle;
import org.apache.log4j.FileAppender;
import org.apache.log4j.xml.DOMConfigurator;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import framework.sharedFunctions.Finish;
import framework.sharedFunctions.Initialize;
////import bsh.This;    

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class  CommonVariables implements CommonFunctions {
	
//	public static final LtfsSrvConfig ltfssrvconf = new LtfsSrvConfig();
//	public static final LtfsProperty ltfspro = new LtfsProperty();
//	public static final Logger logger = LogManager.getLogger(This.class.getName());
	
//	public static final Logger logger = Logger.getLogger(This.class.getName());
	public static final Logger logger = Logger.getLogger(CommonVariables.class);	

//	public static final FirefoxDriver driver = new FirefoxDriver(new ProfilesIni().getProfile("default"));
	
//	public static final FirefoxDriver driver = firefoxConfigurator();
	public static final ChromeDriver driver = chromeConfigurator();
//	public static final InternetExplorerDriver driver = InternetExplorerDriverConfigurator();
	
	public static final IBrowser ibrowser = new IBrowser();
	public static boolean loggedIn = false;
//	public static boolean loggedOut = false;
	//public static final SSHCmdExecutor exec = new SSHCmdExecutor();
	
//	public static final FileAppender appender = LogHandle.createAppender();
//	public static Map<String,String> caseName = new HashMap<String,String>();
//	public static Initialize init = new Initialize();
	public static LogHandle logHandle = new LogHandle();
	public static Finish fin = new Finish();
	
	public static enum Action {
		assigndr,
		unassigndr,
		onlinedr,
		offlinedr,
		assignvol,
		unassignvol,
		onlinevol,
		offlineIdlevol,
		offlineInusevol,
		formatvol,
		unformatvol,
		delunavailvol;
	}
		
	public static enum Menu {
		DriveName, DriveModel, DriveManufacturer, DriveSerialNumber,
		PhysicalStatus, LogicalStatus, LTFSLEAssignment, DrivePath,
		HLIPhysicalAddress;
	}
	
	public static enum VolumeInExportPool {
		VolumeSerialNumber, VolumeType, CapacityAvailable, CapacityUsed, TotalCapactiy, FinalizationCompleted, PhysicalStatus, LogicalStatus;
	}
	
	public static enum VolumeInImportPool {
		VolumeSerialNumber, VolumeType, CapacityAvailable, CapacityUsed, TotalCapactiy, PhysicalStatus, LogicalStatus;
	}
	
	public static enum VolumeInDatatPool {
		VolumeSerialNumber, VolumeType, CapacityAvailable, CapacityUsed, TotalCapactiy, PhysicalStatus, LogicalStatus, ReclamationRatio;
	}
	
	public static enum VolumeInUnassignedPool {
		VolumeSerialNumber, VolumeType, CapacityAvailable, CapacityUsed, TotalCapactiy, PhysicalStatus, LogicalStatus, LTFSCompatibility, LTFSFormatted;
	}
	
	public void PropertyConfigurator(){
		
		PropertyConfigurator.configure("log4j.xml");
	}
	
	public static FirefoxDriver firefoxConfigurator(){
//		String Xport = System.getProperty("lmportal.xvfb.id", ":1");
//		final File firefoxPath = new File(System.getProperty("lmportal.deploy.firefox.path", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe"));
//		FirefoxBinary firefoxBinary = new FirefoxBinary(firefoxPath);
//		firefoxBinary.setEnvironmentProperty("DISPLAY", Xport);
		
		System.setProperty("webdriver.gecko.driver","lib/geckodriver.exe");
		
		ProfilesIni profile = new ProfilesIni();
			 
//    	FirefoxProfile myprofile = profile.getProfile("vei4rpoz.default");
		FirefoxProfile myprofile = profile.getProfile("httpsCredentials");
			 
//		WebDriver driver = new FirefoxDriver(myprofile);
		
//	    DesiredCapabilities capabilities=DesiredCapabilities.firefox();
//	    capabilities.setCapability("marionette", true);
//	    FirefoxDriver driver = new FirefoxDriver(capabilities);	
	    FirefoxDriver driver = new FirefoxDriver(myprofile);	
	    return driver;
	}
	public static ChromeDriver chromeConfigurator(){
		//This is for Windows, should enable on Windows testing
//		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--lang=en-US");
//		options.addArguments("--no-sandbox");
//		options.addArguments("--single-process");
		options.addArguments("--headless");

		//This is for Linux, should enable on Linux testing
		System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//	    System.setProperty("webdriver.chrome.driver", "C://Program Files (x86)//Google//Chrome//Application//chromedriver.exe");
		 //Solve WebDriverException: unknown error: Chrome failed to start: exited abnormally

		ChromeDriver driver = new ChromeDriver(options);
		
	    return driver;
	}

	public static InternetExplorerDriver InternetExplorerDriverConfigurator(){
		System.setProperty("webdriver.ie.driver", "lib/IEDriverServer.exe");
		InternetExplorerDriver driver = new InternetExplorerDriver();
	    return driver;
	}
	
}
