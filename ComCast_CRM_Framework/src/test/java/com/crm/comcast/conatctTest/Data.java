package com.crm.comcast.conatctTest;

import java.util.Date;

import org.testng.annotations.Test;

public class Data {
	
	@Test
	public void getDate() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		System.out.println(systemDateAndTime);
	}

}
