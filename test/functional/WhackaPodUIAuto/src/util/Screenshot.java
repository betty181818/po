package util;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class Screenshot {
	
	public static String path = null;
	public static String path1 = null;
	public static String name = null;
	
	private static File file;
	private static File file1;
	
	public static String screenShot(WebDriver driver, String scenarioName) {

	    String dir_name = "logs/Screenshots"; 
	    String dir_name1 = "logs/Result";  
	    
	    if (!(new File(dir_name).isDirectory())) { 
	    	
	        new File(dir_name).mkdir();  
	        
	    }
	 
	    Calendar cal = Calendar.getInstance();
	    
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
	    String time = sdf.format(cal.getTime());  
	    
	    try {
	  
	        File source_file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
	        path = dir_name + File.separator + time + "_" + scenarioName + ".png";
	        path1 = dir_name1 + File.separator + time + "_" + scenarioName + ".png";
	        file = new File(path);
	        file1 = new File(path1);
	        name = time + "_" + scenarioName + ".png";
	        FileUtils.copyFile(source_file, file);

	        FileUtils.copyFile(source_file, file1);
	        
	    } catch (IOException e) {
	//    	System.out.println("1");
	    }
	    
		return name;
	    
	}
	
}
