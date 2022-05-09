package com.taxilla.apps.commonutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader 
{


	public static String TestDataFilePath=System.getProperty("user.dir")+"\\TestData\\";
	public static String Configfilepath=System.getProperty("user.dir")+"\\Config\\";
	public static String ExecutionReportfilepath=System.getProperty("user.dir")+"\\Reports\\";	
	public static String Downloadedfilepath=System.getProperty("user.dir")+"\\DownloadedFiles\\";
	public static String Inputfilepath=System.getProperty("user.dir")+"\\InputFiles\\";
	public static String FilePath="";

	public static String getTestDataFromXLSXSheet(String fileName,String sheetName,int rownum,int columnnum) throws Exception
	{	

		try{
			File TestDataFile = new File(TestDataFilePath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			//Sheet Number starts from '0'
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			//rownum and columnnum started from '0'
			String value=TDFSheet.getRow(rownum).getCell(columnnum).getStringCellValue();
			TDFwb.close();
			TDFis.close();
			return value;

		}
		catch(Exception e){
			e.printStackTrace();     
		}
		return TestDataFilePath;
	}
	
	public static String getTestDataFromXLSXSheetWithoutExtension(String fileName,String sheetName,int rownum,int columnnum) throws Exception
	{	

		try{
			File TestDataFile = new File(fileName);
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			//Sheet Number starts from '0'
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			//rownum and columnnum started from '0'
			String value=TDFSheet.getRow(rownum).getCell(columnnum).getStringCellValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();     
		}
		return FilePath;
	}

	public static String getInvoiceValueFromXLSXSheet(String fileName,String sheetName,int rownum,int columnnum) throws Exception
	{	

		try{
			File TestDataFile = new File(Inputfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			//Sheet Number starts from '0'
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			//rownum and columnnum started from '0'
			String value=TDFSheet.getRow(rownum).getCell(columnnum).getStringCellValue();
			TDFwb.close();
			TDFis.close();
			return value;

		}
		catch(Exception e){
			e.printStackTrace();     
		}
		return TestDataFilePath;
	}

	public static String getDataFromConfigSheet(String fileName,int rownum,int columnnum) throws Exception
	{	

		try{
			File TestDataFile = new File(Configfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			//Sheet Number starts from '0'
			XSSFSheet TDFSheet= TDFwb.getSheet("Sheet1");
			//rownum and columnnum started from '0'
			String value=TDFSheet.getRow(rownum).getCell(columnnum).getStringCellValue();
			TDFwb.close();
			TDFis.close();
			return value;

		}
		catch(Exception e){
			e.printStackTrace();     
		}
		return Configfilepath;
	}


	public static void readAndUpdateRecordInDownloadedXLSXFile(String fileName, String sheetName,int rownum,int columnnum,String value) throws Exception
	{ 
		FileOutputStream fileOut;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;

		try{
			File TestDataFile = new File(Downloadedfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			wb = new XSSFWorkbook(TDFis);
			sheet = wb.getSheet(sheetName);
			int FirstRowNum = sheet.getFirstRowNum();
			int LastRowNum = sheet.getLastRowNum();
			row = sheet.getRow(rownum);
			cell = row.createCell(columnnum);
			cell.setCellValue(value);
			fileOut = new FileOutputStream(TestDataFile);
			wb.write(fileOut);
			wb.close();
			fileOut.flush();
			fileOut.close();

		}
		catch(Exception e){
			e.printStackTrace();             
		}
	}

	public static void writeToXLSXFile(String fileName,String sheetName,int rownum,int columnnum, String value) throws Exception {


		//File TestDataFile = new File(TestDataFilePath1+fileName+".xlsx");
		File TestDataFile = new File(TestDataFilePath+fileName+".xlsx");
		String excelFileName = TestDataFilePath + fileName + ".xlsx" ;
		FileOutputStream fileOut;
		XSSFWorkbook wb;
		XSSFSheet sheet;
		XSSFRow row;
		XSSFCell cell;

		if (TestDataFile.exists())
		{
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			wb = new XSSFWorkbook(TDFis);
			if(wb.getSheet(sheetName) == null)
			{
				sheet = wb.createSheet(sheetName) ;
			}
			else
			{
				sheet = wb.getSheet(sheetName) ;
			}
			int FirstRowNum = sheet.getFirstRowNum();
			int LastRowNum = sheet.getLastRowNum();
			row = sheet.createRow(LastRowNum+1);
			cell = row.createCell(columnnum);
			cell.setCellValue(value);
			fileOut = new FileOutputStream(excelFileName);
		}
		else
		{
			fileOut = new FileOutputStream(new File(excelFileName));
			wb = new XSSFWorkbook();
			sheet = wb.createSheet(sheetName) ;
			row = sheet.createRow(rownum);
			cell = row.createCell(columnnum);
			cell.setCellValue(value);

		}
		wb.write(fileOut);
		wb.close();
		fileOut.flush();
		fileOut.close();
	}

	public  static void writeToStatusReportXLSXFile(String fileName, int SlNo, String TestCaseNum, String TestCaseTitle, String Status, String ErrMsg, String SnapshotLink, String StartDateTime, String EndDateTime)
	{
		try{


			File TestDataFile = new File(ExecutionReportfilepath+fileName+".xlsx");
			String excelFileName = ExecutionReportfilepath + fileName + ".xlsx" ;
			FileOutputStream fileOut;
			XSSFWorkbook wb;
			XSSFSheet sheet;
			XSSFRow row;
			XSSFCell cell;

			if (TestDataFile.exists())
			{
				FileInputStream TDFis = new FileInputStream(TestDataFile);
				wb = new XSSFWorkbook(TDFis);
				if(wb.getSheet("Sheet1") == null)
				{
					sheet = wb.createSheet("Sheet1") ;
				}
				else
				{
					sheet = wb.getSheet("Sheet1") ;
				}
				int FirstRowNum = sheet.getFirstRowNum();
				int LastRowNum = sheet.getLastRowNum();
				row = sheet.createRow(LastRowNum+1);

				cell = row.createCell(0);
				cell.setCellValue(SlNo);

				cell = row.createCell(1);
				cell.setCellValue(TestCaseNum);

				cell = row.createCell(2);
				cell.setCellValue(TestCaseTitle);

				cell = row.createCell(3);
				cell.setCellValue(Status);

				cell = row.createCell(4);
				cell.setCellValue(ErrMsg);

				cell = row.createCell(5);
				cell.setCellValue(SnapshotLink);

				cell = row.createCell(6);
				cell.setCellValue(StartDateTime);

				cell = row.createCell(7);
				cell.setCellValue(EndDateTime);

				fileOut = new FileOutputStream(excelFileName);
			}
			else
			{
				fileOut = new FileOutputStream(new File(excelFileName));
				wb = new XSSFWorkbook();
				sheet = wb.createSheet("Sheet1") ;

				row=sheet.createRow(0);

				cell = row.createCell(0);
				cell.setCellValue("SlNo");

				cell = row.createCell(1);
				cell.setCellValue("TestCaseNumber");

				cell = row.createCell(2);
				cell.setCellValue("TestCaseTitle");

				cell = row.createCell(3);
				cell.setCellValue("Status");

				cell = row.createCell(4);
				cell.setCellValue("Remarks");

				cell = row.createCell(5);
				cell.setCellValue("SnapshotLink");

				cell = row.createCell(6);
				cell.setCellValue("StartDateTime");

				cell = row.createCell(7);
				cell.setCellValue("EndDateTime");

				row = sheet.createRow(1);

				cell = row.createCell(0);
				cell.setCellValue(SlNo);

				cell = row.createCell(1);
				cell.setCellValue(TestCaseNum);

				cell = row.createCell(2);
				cell.setCellValue(TestCaseTitle);

				cell = row.createCell(3);
				cell.setCellValue(Status);

				cell = row.createCell(4);
				cell.setCellValue(ErrMsg);

				cell = row.createCell(5);
				cell.setCellValue(SnapshotLink);

				cell = row.createCell(6);
				cell.setCellValue(StartDateTime);

				cell = row.createCell(7);
				cell.setCellValue(EndDateTime);

			}
			wb.write(fileOut);
			wb.close();
			fileOut.flush();
			fileOut.close();


		}catch(Exception e){

		}
	}

	public  static int getResultRecordsCount(String fileName) throws Exception {

		File TestDataFile = new File(ExecutionReportfilepath+fileName+".xlsx");
		String excelFileName = ExecutionReportfilepath + fileName + ".xlsx" ;

		XSSFWorkbook wb;
		XSSFSheet sheet;


		int FirstRowNum, LastRowNum=0;
		if (TestDataFile.exists())
		{
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			wb = new XSSFWorkbook(TDFis);
			if(wb.getSheet("Sheet1") == null)
			{
				sheet = wb.createSheet("Sheet1") ;
			}
			else
			{
				sheet = wb.getSheet("Sheet1") ;
			}
			FirstRowNum = sheet.getFirstRowNum();
			LastRowNum = sheet.getLastRowNum();

		}
		return LastRowNum;

	}


	public static String getStatusReportData(String fileName,int romnum,int columnnum) throws Exception
	{
		try{
			File StatusReportFile = new File(ExecutionReportfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(StatusReportFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet("Sheet1");
			String value=TDFSheet.getRow(romnum).getCell(columnnum).getStringCellValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return ExecutionReportfilepath;
	}

	
	public static String readDataFromInputFile(String fileName,String SheetName,int romnum,int columnnum) throws Exception
	{
		try{
			File inputFile = new File(Inputfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(inputFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet(SheetName);
			String value=TDFSheet.getRow(romnum).getCell(columnnum).getStringCellValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();	
		}
		return Inputfilepath;
	}
	public static double readDoubleValue(String fileName,String sheetName,int romnum,int columnnum) throws Exception
	{
		try{

			File TestDataFile = new File(TestDataFilePath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			double value= TDFSheet.getRow(romnum).getCell(columnnum).getNumericCellValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();      
		}
		return columnnum;


	}

	public static double readDoubleValueFromInputFile(String fileName,String sheetName,int romnum,int columnnum) throws Exception
	{
		try{

			File TestDataFile = new File(Inputfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			double value= TDFSheet.getRow(romnum).getCell(columnnum).getNumericCellValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();      
		}
		return columnnum;


	}

	public static int readIntvalue(String fileName,int romnum,int columnnum) throws Exception
	{
		try{

			File TestDataFile = new File(ExecutionReportfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet("Sheet1");
			int value= (int) TDFSheet.getRow(romnum).getCell(columnnum).getNumericCellValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();      
		}
		return columnnum;


	}

	public static String getRawDataFromXLSXSheet(String fileName,String sheetName,int rownum,int columnnum) throws Exception
	{ 
		String value=""; 
		try{
			File TestDataFile = new File(TestDataFilePath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			value=TDFSheet.getRow(rownum).getCell(columnnum).getRawValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();             
		}
		return value;
	}
	
	public static String getRawDataCellValue(String fileName,String sheetName,int rownum,int columnnum) throws Exception
	{ 
		String value=""; 
		try{
			File TestDataFile = new File(Inputfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			value=TDFSheet.getRow(rownum).getCell(columnnum).getRawValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();             
		}
		return value;
	}
	
	
	public static String getRawDataForInvoiceValueFromXLSXSheet(String fileName,String sheetName,int rownum,int columnnum) throws Exception
	{ 
		String value=""; 
		try{
			File TestDataFile = new File(Inputfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			value=TDFSheet.getRow(rownum).getCell(columnnum).getRawValue();
			TDFwb.close();
			TDFis.close();
			return value;
		}
		catch(Exception e){
			e.printStackTrace();             
		}
		return value;
	}

	public  static int getRecordsCount(String fileName,String sheetName) throws Exception {

		File TestDataFile = new File(TestDataFilePath+fileName+".xlsx");
		String excelFileName = TestDataFilePath + fileName + ".xlsx" ;
		int FirstRowNum=0;
		int LastRowNum=0;
		XSSFWorkbook wb;
		XSSFSheet sheet = null;

		//int FirstRowNum, LastRowNum=0;
		if (TestDataFile.exists())
		{
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			wb = new XSSFWorkbook(TDFis);
			if(wb.getSheet(sheetName) == null)
			{
				sheet = wb.createSheet(sheetName) ;
			}
			else
			{
				sheet = wb.getSheet(sheetName) ;
			}
			FirstRowNum = sheet.getFirstRowNum();
			LastRowNum = sheet.getLastRowNum();
		}
		return LastRowNum;

	}
	public  static int getRecordsCountFromInputFile(String fileName,String sheetName) throws Exception {

		File TestDataFile = new File(Inputfilepath+fileName+".xlsx");
		String excelFileName = Inputfilepath + fileName + ".xlsx" ;
		int FirstRowNum=0;
		int LastRowNum=0;
		XSSFWorkbook wb;
		XSSFSheet sheet = null;

		//int FirstRowNum, LastRowNum=0;
		if (TestDataFile.exists())
		{
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			wb = new XSSFWorkbook(TDFis);
			if(wb.getSheet(sheetName) == null)
			{
				sheet = wb.createSheet(sheetName) ;
			}
			else
			{
				sheet = wb.getSheet(sheetName) ;
			}
			FirstRowNum = sheet.getFirstRowNum();
			LastRowNum = sheet.getLastRowNum();
		}
		return LastRowNum;

	}
	public static Integer getIntegerValueFromXLSXSheet(String fileName,String sheetName,int rownum,int columnnum) throws Exception
	{ 
		Integer value=0; 
		try{
			File TestDataFile = new File(Inputfilepath+fileName+".xlsx");
			FileInputStream TDFis = new FileInputStream(TestDataFile);
			XSSFWorkbook TDFwb=new XSSFWorkbook(TDFis);
			XSSFSheet TDFSheet= TDFwb.getSheet(sheetName);
			value=(int) TDFSheet.getRow(rownum).getCell(columnnum).getNumericCellValue();
			TDFwb.close();
			TDFis.close();
			//         System.out.println("value:"+value);
			return value;


		}
		catch(Exception e){

			e.printStackTrace();  

		}
		return value;
	}

}

