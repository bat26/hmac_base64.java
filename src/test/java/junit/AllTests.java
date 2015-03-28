/**
 * 
 */
package junit;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;





/**
 * @author Bruce
 * 
 * @date 13 Dec 2014 BaseTest.class, HMACTest.class,
 */
@RunWith(Suite.class)
@SuiteClasses({ MainTest.class })
public class AllTests
{
	
	/**
	 * 
	 */
	public AllTests()
	{
		System.out.println("Running MainTest");
		Result result = JUnitCore.runClasses(MainTest.class);
	}
	
}
