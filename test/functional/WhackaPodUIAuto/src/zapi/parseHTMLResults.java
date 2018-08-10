package zapi;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class parseHTMLResults {
	public HashMap<String, String> parseResults(String filePath) throws IOException{
		 File input = new File(filePath); 
		 HashMap<String, String> testResults = new HashMap<String, String>();
		 if(input.exists()){
			 Document doc = Jsoup.parse(input, "UTF-8", "http://www.oschina.net/"); 
//	     HashMap<String, String> testResults = new HashMap<String, String>();
//		 Element content = doc.getElementById("content"); 
			 Elements links = doc.getElementsByAttribute("href"); 
			 for (Element link : links) { 
				 String linkText = link.text(); 
//		  System.out.println(linkText);
				 Element statusParent = link.parent().siblingElements().get(0);
				 Element statusNode = statusParent.child(0).child(0);
				 String status = statusNode.text();
				 testResults.put(linkText, status);
			 }
		 }
		 return testResults;
	}
}
