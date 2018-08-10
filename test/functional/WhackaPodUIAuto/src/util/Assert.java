package util;

import exception.AssertErrorException;
import exception.UnExpectedException;
import framework.objects.CommonVariables;
import static org.junit.Assert.*;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class Assert extends CommonVariables{
	public void verifyTrue(boolean result) throws AssertErrorException, UnExpectedException{
		try {
			assertTrue(result);
		//	return true;
		}catch (AssertionError e) {
		//	logger.info("error");
			throw new AssertErrorException(driver, logger ,e);
	//		return false;
		}catch (Exception e) {
			throw new UnExpectedException(driver, logger, e);
	//		return false;
		}
	}
	
	public void verifyFalse(boolean result) throws AssertErrorException, UnExpectedException{
		try {
			assertFalse(result);
		//	return true;
		}catch (AssertionError e) {
			throw new AssertErrorException(driver, logger, e);
		//	return false;
		}catch (Exception e) {
			throw new UnExpectedException(driver, logger, e);
		//	return false;
		}
	}
	
//	public boolean verifyEquals(expecteds, actuals)
//	public boolean verifyNull()

}
