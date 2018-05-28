package com.zoho.util;

public class Temp {

	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader(Constants.XLS_PATH);
		int rows = xls.getRowCount("LoginTest");
		int cols = xls.getColumnCount("LoginTest");
		Object[][] testData = new Object[rows-1][cols];
		
		for(int rNum=2;rNum<=rows;rNum++) {
			
			for(int cNum=0;cNum<cols;cNum++) {
				String data = xls.getCellData("LoginTest", cNum, rNum);
				testData[rNum-2][cNum] = data;
				//00 01 02 03
				//10 11 12 13
				System.out.println(data);
			}
		}

	}

}
