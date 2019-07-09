package com.gmail.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;

import com.gmail.base.Testbase;

public class TestUtil extends Testbase {

	public static long PAGE_LOAD_TIMEOUT = 120;
	public static long IMPLECIT_WAIT = 120;

	public static String TESTDATA_SHEET_PATH ="C:\\New folder (2)\\Selenium Project\\GmailTest\\src\\main\\java\\com\\gmail\\testdata\\New Microsoft Office Excel Worksheet (2).xlsx";

	static Workbook book;
	static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		System.out.println("row" + sheet.getLastRowNum());
		System.out.println("cell" + sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}

	public static void TakeScreenshot() throws IOException {

		File srcfile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String CurrentDir = System.getProperty("user.dir");
		FileHandler.copy(srcfile, new File(CurrentDir + "/Screenphoto/" + System.currentTimeMillis() + ".png"));

	}

}
