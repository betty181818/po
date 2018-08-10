package framework.sharedFunctions;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.openqa.selenium.WebDriver;

import util.ScenarioRunning;
import log.LogHandle;

/**
* Date : 2017-04-27
* Owner : yuhong.ma
* @author yuhong.ma
*/

public class Finish {
	public void finishWork(LogHandle logHandle, WebDriver driver) throws IOException {
		logHandle.finish();
		driver.quit();
	}
	
	public void fail(LogHandle logHandle) {
		logHandle.addStatus(ScenarioRunning.runningScenario, false);
		LogManager.shutdown();
//		logHandle.changeName("logs\\logfile.html", ScenarioRunning.runningScenario);
		logHandle.changeName("logs/Result/logfile.html", ScenarioRunning.runningScenario);
		assertTrue(false);
	}
	
	public void success(LogHandle logHandle) {
		logHandle.addStatus(ScenarioRunning.runningScenario, true);
		LogManager.shutdown();
//		logHandle.changeName("logs\\logfile.html", ScenarioRunning.runningScenario);
		logHandle.changeName("logs/Result/logfile.html", ScenarioRunning.runningScenario);
	}
	
	public void fail1(LogHandle logHandle) {
		logHandle.addStatus(ScenarioRunning.runningScenario, false);
		LogManager.shutdown();
//		logHandle.changeName("logs\\logfile.html", ScenarioRunning.runningScenario);
		logHandle.changeName("logs/Result/logfile1.html", ScenarioRunning.runningScenario);
		assertTrue(false);
	}
	
	public void success1(LogHandle logHandle) {
		logHandle.addStatus(ScenarioRunning.runningScenario, true);
		LogManager.shutdown();
//		logHandle.changeName("logs\\logfile.html", ScenarioRunning.runningScenario);
		logHandle.changeName("logs/Result/logfile1.html", ScenarioRunning.runningScenario);
	}
}
