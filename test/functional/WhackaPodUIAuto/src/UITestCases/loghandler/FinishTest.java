package UITestCases.loghandler;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import UITestCases.BaseTestCase;
import framework.objects.CommonVariables;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class FinishTest extends BaseTestCase{

	@Test
	public void test() throws IOException {
		logHandle.finish();
		try {
			driver.close();   
//			Thread.sleep(5000);
//			driver.quit();
		} catch (Exception e) {
		}
	}
}
