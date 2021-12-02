package practice;

import java.util.Random;

public class GenerateRanDomNum {

	public static void main(String[] args) {
		
		Random ran = new Random();
		int ranDomNun = ran.nextInt(10000);
System.out.println(ranDomNun);
	}

}
