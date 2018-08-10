package zapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Layout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import framework.global.Configuration;

public class generateTotalReport {
    public static void  main(String args[]) throws Exception{
    	String resultPath = "logs/Result/" + "result.html";
    	String resultPath2 = "logs/Result/" + "result2.html";
    	String resultPath3 = "logs/Result/" + "result3.html";

    	File inFile = new File(resultPath);
    	int lineNumber = 0;
    	if(inFile.exists()){
    		lineNumber = findInsertLine(resultPath);
    	}
    	File file2 = new File(resultPath2);
    	File file3 = new File(resultPath3);
    	if(file2.exists()){
    		String record = findRecord(resultPath2);
    		insertStringInFile(inFile, lineNumber, record);
        	totalScenariosIncreaseOne(resultPath);
        	if(findStatus(resultPath2).equalsIgnoreCase("Pass")){
        		passScenariosIncreaseOne(resultPath);
        	}
        	else
        		failScenariosIncreaseOne(resultPath);
        	calculatePassRate(resultPath);    		
    	}
    	
    	if(file3.exists()){
    		String record0 = findRecord(resultPath3);
    		insertStringInFile(inFile, lineNumber, record0);
        	totalScenariosIncreaseOne(resultPath);
        	if(findStatus(resultPath3).equalsIgnoreCase("Pass")){
        		passScenariosIncreaseOne(resultPath);
        	}
        	else
        		failScenariosIncreaseOne(resultPath);
        	calculatePassRate(resultPath);
    	}
//        	String tag = "<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td><td><strong>";
//        	deleteOneLine(tag, resultPath);
    }
    
    public void totalReport() throws Exception{
    	String resultPath = "logs/Result/" + "result.html";
    	String resultPath2 = "logs/Result/" + "result2.html";
    	String resultPath3 = "logs/Result/" + "result3.html";

    	File inFile = new File(resultPath);
    	int lineNumber = 0;
    	if(inFile.exists()){
    		lineNumber = findInsertLine(resultPath);
    	}
    	File file2 = new File(resultPath2);
    	File file3 = new File(resultPath3);
    	if(file2.exists()){
    		String record = findRecord(resultPath2);
    		insertStringInFile(inFile, lineNumber, record);
        	totalScenariosIncreaseOne(resultPath);
        	if(findStatus(resultPath2).equalsIgnoreCase("Pass")){
        		passScenariosIncreaseOne(resultPath);
        	}
        	else
        		failScenariosIncreaseOne(resultPath);
        	calculatePassRate(resultPath);    		
    	}
    	
    	if(file3.exists()){
    		String record0 = findRecord(resultPath3);
    		insertStringInFile(inFile, lineNumber, record0);
        	totalScenariosIncreaseOne(resultPath);
        	if(findStatus(resultPath3).equalsIgnoreCase("Pass")){
        		passScenariosIncreaseOne(resultPath);
        	}
        	else
        		failScenariosIncreaseOne(resultPath);
        	calculatePassRate(resultPath);
    	}
//        	String tag = "<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td><td><strong>";
//        	deleteOneLine(tag, resultPath);
    }
    
    public static String findRecord(String filePath) throws IOException{
//    	String resultPath = "logs/Result/" + "result.html";
//    	String resultPath2 = "logs/Result/" + "result2.html";
//    	String resultPath3 = "logs/Result/" + "result3.html";
    	
    	File input = new File(filePath); 
    	String line, targetLine;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {
    	       // process the line. 
    	    	String tag = "<tr><td colspan=\"3\"><a href=";
    	        if(line.startsWith(tag)){
    	        	return line;
    	        }
    	    }
    	}
		return null;
    }
    
    public static String findStatus(String filePath) throws IOException{
//    	String resultPath = "logs/Result/" + "result.html";
//    	String resultPath2 = "logs/Result/" + "result2.html";
//    	String resultPath3 = "logs/Result/" + "result3.html";
    	
    	String pass = "Pass";
    	String fail = "Fail";
    	String line = findRecord(filePath);
        if(line.toLowerCase().contains(pass.toLowerCase())){
        	return pass;
        }
        else{
        	return fail;
        }
    }
    
    public static int findInsertLine(String targetFilePath) throws FileNotFoundException, IOException{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 1;
    	String tag = "<tr><td colspan=\"3\"><a href=";
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {
    	    	lineNumber++;
    	       // process the line.     	
    	        if(line.startsWith(tag)){
    	        	break;
    	        }
    	    }
    	}
		return lineNumber;    	
    }
    
    public static void insertStringInFile(File inFile, int lineno, String lineToBeInserted) throws Exception {
		// temp file
    	File outFile = new File("test.html");
			
		// input
    	FileInputStream fis  = new FileInputStream(inFile);
    	BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			
    	// output         
    	FileOutputStream fos = new FileOutputStream(outFile);
    	PrintWriter out = new PrintWriter(fos);
			
    	String thisLine = "";
    	int i =1;
    	while ((thisLine = in.readLine()) != null) {
    		if(i == lineno) 
    			out.println(lineToBeInserted);
    		out.println(thisLine);
    		i++;
		}
		out.flush();
		out.close();
		in.close();
			
		inFile.delete();
		outFile.renameTo(inFile);
    }
    public static void totalScenariosIncreaseOne(String targetFilePath) throws Exception{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 0;
    	String tag = "<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td><td><strong>";
    	String number = null;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {  
    	    	lineNumber++;
    	        if(line.startsWith(tag)){
    	        	int lastIndex = line.lastIndexOf("</strong>");
    	        	number = line.substring(tag.length(), lastIndex);
    	        	Integer num = Integer.parseInt(number) + 1;
    	        	number = num.toString();
    	        	break;
    	        }
    	    }
    	}
    	deleteOneLine(tag, targetFilePath);
    	String lineToBeInserted= "<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td><td><strong>" + number + "</strong></td></tr>";
    	insertStringInFile(input, lineNumber, lineToBeInserted);
    }
    
    public static int totalScenarios(String targetFilePath) throws Exception{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 0;
    	String tag = "<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td><td><strong>";
    	String number = null;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {  
    	    	lineNumber++;
    	        if(line.startsWith(tag)){
    	        	int lastIndex = line.lastIndexOf("</strong>");
    	        	number = line.substring(tag.length(), lastIndex);
    	        	Integer num = Integer.parseInt(number) ;
                    return num;
    	        }
    	    }
    	}
		return 0;
    }
    
    public static void passScenariosIncreaseOne(String targetFilePath) throws Exception{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 0;
    	String tag = "<tr><td width=\"50%\"><font color=\"green\"><strong>Pass Scenarios</strong></font></td><td><strong>";
    	String number = null;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {  
    	    	lineNumber++;
    	        if(line.startsWith(tag)){
    	        	int lastIndex = line.lastIndexOf("</strong>");
    	        	number = line.substring(tag.length(), lastIndex);
    	        	Integer num = Integer.parseInt(number) + 1;
    	        	number = num.toString();
    	        	break;
    	        }
    	    }
    	}
    	deleteOneLine(tag, targetFilePath);
    	String lineToBeInserted= "<tr><td width=\"50%\"><font color=\"green\"><strong>Pass Scenarios</strong></font></td><td><strong>" + number + "</strong></td></tr>";
    	insertStringInFile(input, lineNumber, lineToBeInserted);
    }
    
    public static int passScenarios(String targetFilePath) throws Exception{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 0;
    	String tag = "<tr><td width=\"50%\"><font color=\"green\"><strong>Pass Scenarios</strong></font></td><td><strong>";
    	String number = null;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {  
    	    	lineNumber++;
    	        if(line.startsWith(tag)){
    	        	int lastIndex = line.lastIndexOf("</strong>");
    	        	number = line.substring(tag.length(), lastIndex);
    	        	Integer num = Integer.parseInt(number);
    	        	return num;
    	        }
    	    }
    	}
		return 0;
    }
    
    public static void failScenariosIncreaseOne(String targetFilePath) throws Exception{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 0;
    	String tag = "<tr><td width=\"50%\"><font color=\"red\"><strong>Fail Scenarios</strong></font></td><td><strong>";
    	String number = null;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {  
    	    	lineNumber++;
    	        if(line.startsWith(tag)){
    	        	int lastIndex = line.lastIndexOf("</strong>");
    	        	number = line.substring(tag.length(), lastIndex);
    	        	Integer num = Integer.parseInt(number) + 1;
    	        	number = num.toString();
    	        	break;
    	        }
    	    }
    	}
    	deleteOneLine(tag, targetFilePath);
    	String lineToBeInserted= "<tr><td width=\"50%\"><font color=\"red\"><strong>Fail Scenarios</strong></font></td><td><strong>" + number + "</strong></td></tr>";
    	insertStringInFile(input, lineNumber, lineToBeInserted);
    }
    
    public static int failScenarios(String targetFilePath) throws Exception{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 0;
    	String tag = "<tr><td width=\"50%\"><font color=\"red\"><strong>Fail Scenarios</strong></font></td><td><strong>";
    	String number = null;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {  
    	    	lineNumber++;
    	        if(line.startsWith(tag)){
    	        	int lastIndex = line.lastIndexOf("</strong>");
    	        	number = line.substring(tag.length(), lastIndex);
    	        	Integer num = Integer.parseInt(number);
    	        	return num;
    	        }
    	    }
    	}
    	return 0;
    }
    
    public static void calculatePassRate(String targetFilePath) throws Exception{
    	File input = new File(targetFilePath); 
    	String line;
    	int lineNumber = 0;
    	String tag = "<tr><td width=\"50\"><font color =\"green\"><strong>Pass Rate</strong></font></td><td><strong>";
//    	String passRate = null;
    	int passScenario = passScenarios(targetFilePath);
    	int totalSecenario = passScenario + failScenarios(targetFilePath); 
    	double passrate = ((double)passScenario)/totalSecenario*100;
    	try (BufferedReader br = new BufferedReader(new FileReader(input))) {
    	    while ((line = br.readLine()) != null) {  
    	    	lineNumber++;
    	        if(line.startsWith(tag)){
    	        	break;
    	        }
    	    }
    	}
    	deleteOneLine(tag, targetFilePath);
    	String lineToBeInserted= "<tr><td width=\"50\"><font color =\"green\"><strong>Pass Rate</strong></font></td><td><strong>" + passrate + "%</strong></td></tr>";
    	insertStringInFile(input, lineNumber, lineToBeInserted);
    }
    
    public static void deleteOneLine(String deleteLinePrefix, String targetFilePath) throws IOException{
    	File inputFile = new File(targetFilePath);
    	String tempPath = "logs/Result/" + "temp.html";
    	File tempFile = new File(tempPath);
//    	File tempFile = new File("myTempFile.txt");

    	BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    	BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

//    	String lineToRemove = "bbb";
    	String currentLine;

    	while((currentLine = reader.readLine()) != null) {
    	    // trim newline when comparing with lineToRemove
    	    String trimmedLine = currentLine.trim();
    	    if(trimmedLine.startsWith(deleteLinePrefix)) continue;
    	    writer.write(currentLine + System.getProperty("line.separator"));
    	}
    	writer.close(); 
    	reader.close(); 
    	inputFile.delete();
    	boolean successful = tempFile.renameTo(new File(targetFilePath));
    }
}
