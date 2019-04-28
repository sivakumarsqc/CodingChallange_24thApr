package UtilityPackage;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/*
 * Created by - SIVAKUMAR.V
 * This class is used to retry the Failed Test cases for 3 times
 */

public class RetryAnalyzer implements IRetryAnalyzer {

	int iCounter = 0;
	int iRetryCount = 3;
	
	public boolean retry(ITestResult result) {
		
		if (iCounter < iRetryCount){
			iCounter++;
			return true;
		}
		
		return false;
	}
	

}
