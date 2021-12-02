package practice;

import java.util.Date;

public class GetCurrentDate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Date dateObj = new Date();
				
		
		int date = dateObj.getDate();
		int month = dateObj.getMonth()+1;
		String year = dateObj.toString().split(" ")[5];
		
		String formate = year + "-" + month + "-" + date;
		
		System.out.println(formate);

	}

}
