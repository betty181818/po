package exception;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import util.ScenarioRunning;
import util.Screenshot;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class AssertErrorException extends Exception{
	public AssertErrorException(WebDriver driver, Logger logger, AssertionError e) {
		String snapPath = Screenshot.screenShot(driver, ScenarioRunning.runningScenario);
		
		logger.error("Errors on verification.");
		logger.fatal(snapPath);
		logger.error(e.getMessage(), e);
	}
}
