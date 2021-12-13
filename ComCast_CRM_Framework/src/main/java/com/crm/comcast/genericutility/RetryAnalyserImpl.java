package com.crm.comcast.genericutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyserImpl implements IRetryAnalyzer {
             int count = 0;
             int retry=5;
	public boolean retry(ITestResult result) {
		while(count<retry)
		{
			count++;
			return true;
		}
	
		return false;

	}
}
