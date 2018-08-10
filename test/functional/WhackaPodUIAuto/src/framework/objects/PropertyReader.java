/**
 * 
 */
package framework.objects;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* Date : 2017-03-28
* Owner : lixia.yuan
* @author lixia.yuan
*/

public class PropertyReader extends CommonVariables{

	 public void getProperty() {
	        Properties prop = new Properties();
	        InputStream fis;
	        //FileInputStream fis;
			
	        try {
	            fis =  PropertyReader.class.getResourceAsStream("/Auto.properties");
	            //new FileInputStream("OMSAuto.properties");	            
	                       	            
	            prop.load(fis);	
	            fis.close();
	        }
	        catch (FileNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	
	        
	    }
	   
}
