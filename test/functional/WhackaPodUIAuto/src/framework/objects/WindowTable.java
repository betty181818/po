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
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class WindowTable extends CommonVariables {

	public static String[][] data;
	public String[] line;
	public int rowCount = 0;
	public int colCount = 0;
	public WebElement Table;
	public String migrateJobsTableId = "migrate_jobs_info";
	public String MessageTableXpath = ".//*[@id='message_Table']/tbody";
	public String MysqlSourceDatabaseListId = "source_database_List";

	public WindowTable() {
		
		By by = By.id("pt1:r1:0:pc1:t1");
		// Table = driver.findElement(By.id("pt1:r1:0:pc1:t1"));
		// Table = driver.findElement(By.id("pt1:r1:0:pc1:t1::ch::t"));
		// Table = driver.findElement(By.className("xxb xy3"));
		Table = driver
				.findElement(By
						.xpath(".//*[@id='pt1:r1:0:pc1:t1::db']/table"));
		// Table = driver.findElement(By.xpath("//table[@class='xxy xy3']"));
		rowCount = this.getRowCount(by, Table);
		colCount = this.getColCount(by, Table);
		// logger.info("in super class, the rowCount is " + rowCount);
		data = new String[rowCount][colCount];
		line = new String[colCount];
	}

	// mary added. Need code review
	/*
	 * public WindowTable(String id){ By by = By.id(id); // Table =
	 * driver.findElement(By.id("pt1:r1:0:pc1:t1::db")); Table =
	 * driver.findElement(By.id("pt1:r1:0:pc1:t1")); rowCount =
	 * this.getRowCount(by, Table); colCount = this.getColCount(by, Table);
	 * data=new String[rowCount][colCount]; line=new String[colCount];
	 * 
	 * }
	 */

	/**
	 * The contruction function which has one parameter to decide which window table to build, including "Default Drive Pool",
	 * "All Tape Drives Pool".
	 * @param poolName
	 * @throws Exception
	 * @author jacky
	 */
	public WindowTable(String poolName) throws Exception {
		if (poolName.equals("MigrateJobs")) {
			// Table = driver.findElement(By.xpath("//table[class='xxy xy3']"));
			Table = driver.findElement(By.id(migrateJobsTableId));
			rowCount = this.getRowCount(Table);
			colCount = this.getColCount(Table);
			data = new String[rowCount][colCount];
			line = new String[colCount];
//			this.getTableData_new();
			// logger.info("rowCount is: " + rowCount + "\ncolCount is: " +
			// colCount);

		}
		else if (poolName.equals("MessageTable")) {
			Table = driver.findElement(By.xpath(MessageTableXpath));
			rowCount = this.getRowCount(Table);
			colCount = this.getColCount(Table);
			data = new String[rowCount][colCount];
			line = new String[colCount];
		}
		
		else if (poolName.equals("MysqlDatabaseList")) {
			Table = driver.findElement(By.id(MysqlSourceDatabaseListId));
			rowCount = this.getRowCount(Table);
			colCount = this.getColCountForMySqlDatabaseList(Table);
			data = new String[rowCount][colCount];
			line = new String[colCount];
		}
		
//		}else if (poolName.equals("DefaultDrivePool")) {
//			// Table = driver.findElement(By.xpath("//table[class='xxy xy3']"));
//			Table = driver.findElement(By.xpath(".//*[@id='pt1:r1:0:pc1:t1::db']/table"));
//			rowCount = this.getRowCount(Table);
//			colCount = this.getColCount(Table);
//			data = new String[rowCount][colCount];
//			line = new String[colCount];
//
//			// logger.info("rowCount is: " + rowCount + "\ncolCount is: " +
//			// colCount);
//
//		} else if (poolName.equals("AllTapeDrives")) {
//			Table = driver.findElement(By.xpath("/html/body/div[1]/form/div[1]/div[2]/div/div[1]"
//							+ "/div/div[5]/div/div[1]/div[2]/div/div[6]/div/div[1]/div[3]"
//							+ "/div/div[2]/div/div[2]/div/div/div/div[1]/div[6]/div/div/div"
//							+ "/div/div[1]/div[2]/div/div[2]/table"));
//			rowCount = this.getRowCount(Table);
//			colCount = this.getColCount(Table);
//			data = new String[rowCount][colCount];
//			line = new String[colCount];
//		} else if (poolName.equals("Unassigned")) {
//			Table = driver.findElement(By.xpath("/html/body/div[1]/form/div[1]/div[2]/div/div[1]"
//					+ "/div/div[5]/div/div[1]/div[2]/div/div[6]/div/div[1]/div[3]"
//					+ "/div/div[2]/div/div[2]/div/div/div/div[1]/div[6]/div/div/div"
//					+ "/div/div[1]/div[2]/div/div[2]/table"));
//					rowCount = this.getRowCount(Table);
//					colCount = this.getColCount(Table);
//					data = new String[rowCount][colCount];
//					line = new String[colCount];
//		} 
//						
//		else if (poolName.equals("DataPool") || poolName.equals("ImportPool") || poolName.equals("ExportPool") || poolName.equals("UnassignedVolumesPool") ) {
//			Table = driver.findElement(By.xpath(".//*[@id='pt1:r1:0:pc1:t3::db']/table"));
//			rowCount = this.getRowCount(Table);
//			colCount = this.getColCount(Table);
//			data = new String[rowCount][colCount];
//			line = new String[colCount];
//
//		} 
		else {

		}
	}

	public void RefreshTable() {

		// Library/Drive/Volume id is pt1:r1:0:pc1:t1::db;
		// By by = By.id("pt1:r1:0:pc1:t1::db");
		By by = By.id("pt1:r1:0:pc1:t1");

		// Table = driver.findElement(By.id("pt1:r1:0:pc1:t1::db"));
		Table = driver.findElement(By.id("pt1:r1:0:pc1:t1"));
		rowCount = this.getRowCount(by, Table);
		colCount = this.getColCount(by, Table);
		data = new String[rowCount][colCount];
		line = new String[colCount];
	}

	/**
	 * @param by
	 *            : to get the table object
	 * @param Xpath
	 *            : the table path (Library,Drive or Volume)
	 * @return array:the table contents
	 */

	public void getTableData_new() {
		List<WebElement> cellValue = Table.findElements(By.tagName("td"));
		int j = 0;
		for (int i = 0; i < rowCount; i++) {
			for (int index = 0; index < colCount; index++) {
				if (cellValue.get(j).getText().equals(" ")) {
					data[i][index] = "null";
				} else {
					data[i][index] = cellValue.get(j).getText().trim();
				}
				j++;
				if (index == colCount) {
					index = 0;
					break;
				}
			}
		}
	}
	
	public void getMysqlTableDatabaseData() {
		List<WebElement> cellValue = Table.findElements(By.tagName("td"));
		int j = 0;
		for (int i = 1; i < rowCount; i++) {
			for (int index = 0; index < colCount && j<colCount; index++) {
				if (cellValue.get(j).getText().equals("")) {
					data[i][index] = "null";
				} else {
					data[i][index] = cellValue.get(j).getText().trim();
				}
				j++;
				if (index == colCount) {
					index = 0;
					break;
				}
			}
		}
	}
	
	public void getTableData() {

		// get the all elements of td
		List<WebElement> cellValue = Table.findElements(By.tagName("td"));

		// logger.info("rowCount is: " + rowCount + "\ncolCount is: " +
		// colCount);
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount * rowCount; j++) {
				for (int index = 0; index < colCount; index++) {

					if (cellValue.get(j).getText().equals(" ")) {
						data[i][index] = "null";
					} else {
						data[i][index] = cellValue.get(j).getText().trim();
					}

					// System.out.print("data"+"["+i+"]["+index+"]:"+data[i][index]+" ");

					// handle data on the edge
					if (j > 0 && j < colCount * rowCount - 1
							&& (j + 1) % colCount == 0) {
						i++;
						j++;
						index = 0;
						// System.out.print("\n"+j+"["+i+"]["+index+"]:"+cellValue.get(j).getText());
						if (cellValue.get(j).getText().equals(" ")) {
							data[i][index] = "null";
						} else {
							data[i][index] = cellValue.get(j).getText().trim();
							// System.out.print("\n"+"data"+"["+i+"]["+index+"]:"+data[i][index]+" ");
						}
					}

					j++;
					if (j == colCount * rowCount)
						break;
				}
			}
		}

	}

	public void getlineDataBySN(String key) {

		// get the all elements of tr(get lines)
		List<WebElement> lineValues = Table.findElements(By.tagName("tr"));
		// get all the columns of a line.
		List<WebElement> colObjects;

		int i, j;
		boolean flag = false;

		for (i = 0; i < lineValues.size(); i++) {
			WebElement currentline = lineValues.get(i);
			colObjects = currentline.findElements(By.tagName("td"));

			if (colObjects.get(0).getText().trim().equals(key)) {
				flag = true;
				for (j = 0; j < colCount; j++) {
					if (colObjects.get(j).getText().equals(" ")) {
						line[j] = "null";
					} else {
						line[j] = colObjects.get(j).getText().trim();
					}

					// System.out.print("Result"+"["+j+"]:"+lineData[j]+" ");
				}
				break;
			}
		}

		if (!flag) {
			logger.info("the target key dosen't exist in current table.");
		}
	}

	// mary
	// according to the row number, get data of the whole row line
	public void getlineDataByRawNum(int rawNum) {

		// get the up-to-date data from the current table
		getTableData();
		// logger.info("test" + line.length);
		for (int i = 0; i < line.length; i++) {

			line[i] = data[rawNum][i];

		}

	}

	public void setRowCount(int row) {
		this.rowCount = row;
	}

	public int getRowCount(By by, WebElement table) {

		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int rowcount = rows.size();
		return rowcount;
	}

	public int getRowCount() {
		return this.rowCount;
	}

	public void setColCount(int col) {
		this.colCount = col;
	}

	public int getColCount(By by, WebElement table) {

		List<WebElement> columns = table.findElements(By.tagName("td"));
		int rowcount = this.getRowCount(by, table);
		int columnCount = columns.size() / rowcount;
		return columnCount;
	}

	public int getColCount() {
		return this.colCount;
	}

	public int getRowNumIn1stColByInnerText(String value) {
		getTableData();
		int i;
		for (i = 0; i < rowCount; i++) {
			if (this.data[i][0].equalsIgnoreCase(value.trim())) {
				break;
			}

		}
		return i;
	}

	// mary add
	public int getRowNumInColByInnerText(String value, int col) {

		int i;

		getTableData();
		// getTableData_new();

		for (i = 0; i < rowCount; i++) {
			if (data[i][col].equalsIgnoreCase(value.trim())) {
				break;
			}

		}

		return i;
	}

	// mary
	// //get row number by web element

	public int getRowCountbyInnerText(String value, int col) {
		int i, j = 0;

		for (i = 0; i < rowCount; i++)
			if (data[i][col - 1].equalsIgnoreCase(value.trim())) {
				j++;
			}

		return j;
	}

	// can use this
	// before this method is called, should initiate the table data
	// getTableData_new();
	public int[] getAllRowNumByInnerText(String value, int col) {

		int i, row[], k = 0, j = 0;

		k = this.getRowCountbyInnerText(value, col);
		row = new int[k];

		for (i = 0; i < rowCount; i++) {
			if (data[i][col - 1].equalsIgnoreCase(value.trim())) {
				row[j++] = i;

			}
		}

		return row;
	}

	public String getInnerTextbycell(int row, int col) {
		getTableData();
		return data[row][col - 1].trim();

	}

	// jacky
	public int getRowCount(WebElement table) {
		int rowCount = 0;
		List<WebElement> rows = new ArrayList<WebElement>();
		rows = table.findElements(By.tagName("tr"));
		rowCount = rows.size();
		// logger.info(rowCount);
		return rowCount;
	}

	// jacky
	public int getColCount(WebElement table) {
		int colCount = 0;
		List<WebElement> cols = table.findElements(By.tagName("td"));
		colCount = cols.size() / getRowCount(table);
		return colCount;
	}
	
	// Yuhong
	public int getColCountForMySqlDatabaseList(WebElement table) {
		int colCount = 0;
		List<WebElement> cols = table.findElements(By.tagName("td"));
		colCount = cols.size();
		return colCount;
	}
	

}
