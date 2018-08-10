package JunitTestSuites;

/**
* Date : 2017-11-07
* Owner : lixia.yuan
* @author : lixia.yuan
*/

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import UITestCases.loghandler.FinishTest;
import UITestCases.api.ColorAPI;
import UITestCases.games.DeploytoK8s;
import UITestCases.games.WhackaPodHomePage;
import UITestCases.loghandler.FinishTest;
import UITestCases.test.Checktext;
import UITestCases.test.Getip;

@RunWith(Suite.class)
@SuiteClasses({ 
	Getip.class,
	Checktext.class,
	FinishTest.class,
	
	}
)

public class TestSuite {
	
	public  TestSuite() {
        super();	       
    }
	
}
