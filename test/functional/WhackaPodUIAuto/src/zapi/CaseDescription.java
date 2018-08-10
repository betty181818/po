package zapi;

import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.csvreader.CsvReader;  

/**
* Date : 2017-11-22
* Owner : lixia.yuan
* @author lixia.yuan
*/

public class CaseDescription {
	public static String[] USED_FIELDS_LIST = {"Summary","Description","Component/s"};
	
	public void getCaseDestination(String product, String tab_list){
//		String out_file = File.absolute_path(File.join(File.dirname(""), "../related_files/"+product.gsub("/","_")+"_case_list.csv"));
//		String out_file = 
	}
	
//	  def self.translate_file_to_list product
//	    out_file = File.absolute_path(File.join(File.dirname(__FILE__), "../related_files/"+product.gsub("/","_")+"_case_list.csv"))
//	    csv_file = CSV.new(File.read(out_file), :headers => true)
//	    csv_file.to_a.map {|row| row.to_hash}
//	  end
	
	public static List<String[]> translateFileToList(String product) throws IOException{
	    String filePath = "C:\\Users\\yuhma\\Desktop\\zapi\\zapi\\related_files\\engage_case_list.csv";
//	    File csv_file = new File(out_file_path);
	    
        List<String[]> csvList = new ArrayList<String[]>();  
        if (isCsv(filePath)) {  
            CsvReader reader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));  
//            reader.readHeaders(); // skip header, if need header, don't use this sentence. 
            while (reader.readRecord()) { //read header by line
                csvList.add(reader.getValues());  
            }  
            reader.close();  
        } else {  
            System.out.println("this is not csv file");  
        }  
        
        return csvList;  
	}
	
    //tell if csv file
    public static boolean isCsv(String fileName) {  
        return fileName.matches("^.+\\.(?i)(csv)$");  
    }

}
