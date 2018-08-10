package framework.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * This page is about the confirm action after online drives, offline drives and
 * so on. Including three button "Apply, OK, Cancel", some labels, and the
 * windowTable.
 * Date : 2017-04-27
 * Owner : yuhong.ma
 * @author yuhong.ma
*/
public class WindowConfirm extends CommonVariables {
	public WindowConfirm() {

	}
	
	public void Apply() {
		WebElement button_Apply = driver.findElement(By.xpath("//span[text()='Apply']"));
		button_Apply.click();
	}
	
	public void OK() {
		WebElement button_OK = driver.findElement(By.xpath("//span[text()='OK']"));
		button_OK.click();
	}
	
	public void Cancel() {
		WebElement button_Cancel = driver.findElement(By.xpath("//span[text()='Cancel']"));
		button_Cancel.click();
	}
}
