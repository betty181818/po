package log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.WriterAppender;

import com.google.common.io.Files;

import framework.global.Configuration;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class LogHandle {

	
	private Calendar cal = Calendar.getInstance();
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm");
	//	Date and Time Pattern	Result 	"yyyy.MM.dd G 'at' HH:mm:ss z"
	private SimpleDateFormat datetime_sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
	private SimpleDateFormat dsdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	private SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

	private String time, day, datetime;
	private Long timeDuration;
	private Date startTime, endTime;
	private static PrintStream printStream = null;
	private static StringBuilder sb = new StringBuilder();
	private File resultFile;
	private File destFile;
	private static int num = 0, pass = 0, fail = 0;
	
	public LogHandle() {
		time = sdf.format(cal.getTime());
		datetime =datetime_sdf.format(cal.getTime());
		
		day = dsdf.format(cal.getTime());
		Configuration.init();
	}
	
	public void changeName(String fileName, String caseName) {
		File file = new File(fileName);
		
//		file.renameTo(new File("logs//" + time +"_" + caseName + ".html"));
		file.renameTo(new File("logs/Result/" + time +"_" + caseName + ".html"));		
	}
	
/*	public static FileAppender createAppender() {
		FileAppender appender = null;
		
		Layout layout = new SummaryHTMLLayout();
		
		try{
			FileOutputStream file = new FileOutputStream("logs\\summary.html");
			appender = new FileAppender(layout,"logs\\summary.html",false);
		}catch (Exception e) {
			e.printStackTrace();
		}
	//	appender.
		return appender;
	}
*/	
	public void start() throws IOException{
/*		String path = "D:\\" + time + "_result.html";
		File file = new File(path);
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
	*/	
		startTime = new Date();
		System.out.println("start time: " + startTime.getTime());
		
		sb.append("<html><head><title>Result</title>" + Layout.LINE_SEP);
	    sb.append("<style type=\"text/css\">" + Layout.LINE_SEP);
	    sb.append("<!--" + Layout.LINE_SEP);
	    sb.append("body, table {font-family: '??',arial,sans-serif; font-size: 12px;}" + Layout.LINE_SEP);
	    sb.append("th {background: #336699; color: #FFFFFF; text-align: left;}" + Layout.LINE_SEP);
	    sb.append("-->\")" + Layout.LINE_SEP);
	    sb.append("</style></head>" +
	    		"<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" +
	    				"<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" +
	    						"<tr><tr>" + Layout.LINE_SEP);
	    sb.append("<th colspan=\"4\"><font size=14>Test Result of " + datetime + "</font></th>" + Layout.LINE_SEP);

	    sb.append("<tr>" + Layout.LINE_SEP);
        sb.append("<th colspan=\"4\">Browser: " + "Chrome" + "</th>" + Layout.LINE_SEP);
        sb.append("</tr>" + Layout.LINE_SEP);
            
	    sb.append("</tr>" +
	    		"<th colspan=\"3\">Scenario</th>" +
	    		"<th>Status</th>" +
	    		"</tr><br></br>" + Layout.LINE_SEP);
		
	}
	
	public void addStatus(String caseName, boolean status) {
		if(status) {
			num++;
			pass++;
			sb.append("<tr><td colspan=\"3\"><a href=\""+Configuration.pro.getReportLinkPrefix() + time + "_" + caseName + ".html\">" + caseName + "</a></td>" 
					+ "<td><font color=\"green\"><strong>Pass</strong></font></td></tr>"+ Layout.LINE_SEP);
//			sb.append("<tr><td colspan=\"3\"><a href=\"file:" + "../Result" + time + "_" + caseName + ".html\">" + caseName + "</a></td>" 
//					+ "<td><font color=\"green\"><strong>Pass</strong></font></td></tr>"+ Layout.LINE_SEP);
//			sb.append("<tr><td colspan=\"3\"><a href=\"file:" + "..//" + time + "_" + caseName + ".html\">" + caseName + "</a></td>" 
//					+ "<td><font color=\"green\"><strong>Pass</strong></font></td></tr>"+ Layout.LINE_SEP);
		}
		else {
			num++;
			fail++;
			sb.append("<tr><td colspan=\"3\"><a href=\""+Configuration.pro.getReportLinkPrefix() + time + "_" + caseName + ".html\">" + caseName + "</a></td>"
					+ "<td><font color=\"red\"><strong>Fail</strong></font></td></tr>" + Layout.LINE_SEP);
//			sb.append("<tr><td colspan=\"3\"><a href=\"file:" + "../Reuslt" + time + "_" + caseName + ".html\">" + caseName + "</a></td>"
//					+ "<td><font color=\"red\"><strong>Fail</strong></font></td></tr>" + Layout.LINE_SEP);
//			sb.append("<tr><td colspan=\"3\"><a href=\"file:" + "..//" + time + "_" + caseName + ".html\">" + caseName + "</a></td>" 
//					+ "<td><font color=\"green\"><strong>Pass</strong></font></td></tr>"+ Layout.LINE_SEP);
		}
//		System.out.println(sb);
	}
	
	public void finish() throws IOException {
		sb.append("</table>" + Layout.LINE_SEP);
		
		DecimalFormat rateFormat = new DecimalFormat("00.00");
		
		double rate = (double)pass/num *100;
		
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
		
		endTime = new Date();

		System.out.println("end time: " + endTime.getTime());
		timeDuration = endTime.getTime() - startTime.getTime();
		
		Long day = timeDuration / nd;
		Long hour = timeDuration % nd / nh;
		Long min = timeDuration % nd % nh / nm;
		
		String timeSpend = day.toString() +" day " + hour.toString() + " hour "+ min.toString() + " min ";
		System.out.println(timeSpend);

		
		sb.append("<br><br><br><br><br><br>" + Layout.LINE_SEP);
		sb.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" +
				"<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" +
				"<tr><tr>" + Layout.LINE_SEP);
		sb.append("<th colspan=\"4\"><font size=14>Summary</font></th>" + Layout.LINE_SEP);
		sb.append("<tr><td width=\"50%\"><font color=\"black\"><strong>Total Time</strong></font></td>" +
				"<td><strong>" + timeSpend + "</strong></td></tr>" + Layout.LINE_SEP); 
		sb.append("<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td>" +
				"<td><strong>" + num + "</strong></td></tr>" + Layout.LINE_SEP); 
		sb.append("<tr><td width=\"50%\"><font color=\"green\"><strong>Pass Scenarios</strong></font></td>" +
				"<td><strong>" + pass + "</strong></td></tr>" + Layout.LINE_SEP);
		sb.append("<tr><td width=\"50%\"><font color=\"red\"><strong>Fail Scenarios</strong></font></td>" + 
				"<td><strong>" + fail + "</strong></td></tr>" + Layout.LINE_SEP);
		if(rate > 80) {
			sb.append("<tr><td width=\"50\"><font color =\"green\"><strong>Pass Rate</strong></font></td>" + 
					"<td><strong>" + rateFormat.format(rate) + "%</strong></td></tr>" + Layout.LINE_SEP);
		}
		else {
			sb.append("<tr><td width=\"50\"><font color =\"red\"><strong>Pass Rate</strong></font></td>" + 
					"<td><strong>" + rateFormat.format(rate) + "%</strong></td></tr>" + Layout.LINE_SEP);
		}
		
		
		sb.append("</table></body></html>" + Layout.LINE_SEP);
		String path = "logs/Result/" + time + "_result.html";
		resultFile = new File(path);
		if(!resultFile.exists()) {
			resultFile.createNewFile();
		}
		printStream = new PrintStream(new FileOutputStream(resultFile));
		
		printStream.print(sb.toString());
		
		String resultPath = "logs/Result/" + "result.html";
		destFile = new File(resultPath);
		
		Files.copy(resultFile, destFile);

	}
	
	public void finish2() throws IOException {
		sb.append("</table>" + Layout.LINE_SEP);
		
		DecimalFormat rateFormat = new DecimalFormat("00.00");
		
		double rate = (double)pass/num *100;
		
		sb.append("<br><br><br><br><br><br>" + Layout.LINE_SEP);
		sb.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" +
				"<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" +
				"<tr><tr>" + Layout.LINE_SEP);
		sb.append("<th colspan=\"4\"><font size=14>Summary</font></th>" + Layout.LINE_SEP);
		sb.append("<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td>" +
				"<td><strong>" + num + "</strong></td></tr>" + Layout.LINE_SEP); 
		sb.append("<tr><td width=\"50%\"><font color=\"green\"><strong>Pass Scenarios</strong></font></td>" +
				"<td><strong>" + pass + "</strong></td></tr>" + Layout.LINE_SEP);
		sb.append("<tr><td width=\"50%\"><font color=\"red\"><strong>Fail Scenarios</strong></font></td>" + 
				"<td><strong>" + fail + "</strong></td></tr>" + Layout.LINE_SEP);
		if(rate > 80) {
			sb.append("<tr><td width=\"50\"><font color =\"green\"><strong>Pass Rate</strong></font></td>" + 
					"<td><strong>" + rateFormat.format(rate) + "%</strong></td></tr>" + Layout.LINE_SEP);
		}
		else {
			sb.append("<tr><td width=\"50\"><font color =\"red\"><strong>Pass Rate</strong></font></td>" + 
					"<td><strong>" + rateFormat.format(rate) + "%</strong></td></tr>" + Layout.LINE_SEP);
		}
		
		
		sb.append("</table></body></html>" + Layout.LINE_SEP);
		String path = "logs/Result/" + time + "_result2.html";
		resultFile = new File(path);
		if(!resultFile.exists()) {
			resultFile.createNewFile();
		}
		printStream = new PrintStream(new FileOutputStream(resultFile));
		
		printStream.print(sb.toString());
		
		String resultPath = "logs/Result/" + "result2.html";
		destFile = new File(resultPath);
		
		Files.copy(resultFile, destFile);

	}
	
	public void finish3() throws IOException {
		sb.append("</table>" + Layout.LINE_SEP);
		
		DecimalFormat rateFormat = new DecimalFormat("00.00");
		
		double rate = (double)pass/num *100;
		
		sb.append("<br><br><br><br><br><br>" + Layout.LINE_SEP);
		sb.append("<body bgcolor=\"#FFFFFF\" topmargin=\"6\" leftmargin=\"6\">" +
				"<table cellspacing=\"0\" cellpadding=\"4\" border=\"1\" bordercolor=\"#224466\" width=\"100%\">" +
				"<tr><tr>" + Layout.LINE_SEP);
		sb.append("<th colspan=\"4\"><font size=14>Summary</font></th>" + Layout.LINE_SEP);
		sb.append("<tr><td width=\"50%\"><font color=\"black\"><strong>Total Scenarios</strong></font></td>" +
				"<td><strong>" + num + "</strong></td></tr>" + Layout.LINE_SEP); 
		sb.append("<tr><td width=\"50%\"><font color=\"green\"><strong>Pass Scenarios</strong></font></td>" +
				"<td><strong>" + pass + "</strong></td></tr>" + Layout.LINE_SEP);
		sb.append("<tr><td width=\"50%\"><font color=\"red\"><strong>Fail Scenarios</strong></font></td>" + 
				"<td><strong>" + fail + "</strong></td></tr>" + Layout.LINE_SEP);
		if(rate > 80) {
			sb.append("<tr><td width=\"50\"><font color =\"green\"><strong>Pass Rate</strong></font></td>" + 
					"<td><strong>" + rateFormat.format(rate) + "%</strong></td></tr>" + Layout.LINE_SEP);
		}
		else {
			sb.append("<tr><td width=\"50\"><font color =\"red\"><strong>Pass Rate</strong></font></td>" + 
					"<td><strong>" + rateFormat.format(rate) + "%</strong></td></tr>" + Layout.LINE_SEP);
		}
		
		
		sb.append("</table></body></html>" + Layout.LINE_SEP);
		String path = "logs/Result/" + time + "_result3.html";
		resultFile = new File(path);
		if(!resultFile.exists()) {
			resultFile.createNewFile();
		}
		printStream = new PrintStream(new FileOutputStream(resultFile));
		
		printStream.print(sb.toString());
		
		String resultPath = "logs/Result/" + "result3.html";
		destFile = new File(resultPath);
		
		Files.copy(resultFile, destFile);

	}
}
