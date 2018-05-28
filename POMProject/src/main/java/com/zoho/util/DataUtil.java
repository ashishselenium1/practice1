package com.zoho.util;

public class DataUtil {

	public static Object[][] getData(String testName,Xls_Reader xls){
		int rows = xls.getRowCount(testName);
		int cols = xls.getColumnCount(testName);
		Object[][] testData = new Object[rows-1][cols];
		
		for(int rNum=2;rNum<=rows;rNum++) {
			
			for(int cNum=0;cNum<cols;cNum++) {
				String data = xls.getCellData(testName, cNum, rNum);
				testData[rNum-2][cNum] = data;
				//00 01 02 03
				//10 11 12 13
				System.out.println(data);
			}
		}
		return testData;
	}
	// Y - true
	// N - false
	public static boolean isRunnable(String testName,Xls_Reader xls) {
		int rows = xls.getRowCount("TestCases");
		for(int rNum=2;rNum<=rows;rNum++) {
			String data = xls.getCellData("TestCases", "TCID", rNum);
			if(data.equals(testName)) {
				String runmode = xls.getCellData("TestCases", "Runmode", rNum);
				if(runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		
		return false;// default

	}
}
