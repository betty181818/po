package framework.objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import framework.objects.CommonVariables.Menu;

/**
* Date : 2017-05-15
* Owner : lixia.yuan
* @author  lixia.yuan
*/

public class HtmlUnorderedList extends CommonVariables {

	public static String[][] data;
	public String[] line;
	public int rowCount = 0;
	public int colCount = 3;
	public WebElement ulli;
	
	public String ulliId="listview";


	public HtmlUnorderedList(String ListName) {
		if (ListName.equals("MigrateJobs")) {
		ulli = driver.findElement(By.id(ulliId));
		data = new String[rowCount][colCount];
		line = new String[colCount];
		}
	}

	public int getCurrentPageRowCount(By by, WebElement ulli) {

		List<WebElement> rows = ulli.findElements(By.tagName("li"));
		int rowcount = rows.size();
		return rowcount;
	}
	

	public int getRowCount() {
		return this.rowCount;
	}

//	public void setColCount(int col) {
//		this.colCount = col;
//	}

	
	public void getFirstPageUlliData() {

		List<WebElement> rows = ulli.findElements(By.tagName("li"));
		String linei;
		for (int i = 0; i < rowCount; i++) {
			linei = rows.get(i).getText().trim();
			String[] parts= linei.split("\n");
			String[] partsJobname=parts[1].split(":");
			String jobName=partsJobname[1].trim();
			line[0]=jobName; 	//jobName;
			line[1]=parts[0];	//status
			line[2]=parts[8];	//Percentage
			data[i]=line;
			System.out.print(data);
		}
				
		System.out.print(data);

	}
	

	

}
