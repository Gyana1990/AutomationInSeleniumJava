package com.taxilla.apps.testscripts;

import java.io.File;
import java.io.FileInputStream;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.taxilla.apps.commonutil.DateUtils;
import com.taxilla.apps.commonutil.ExcelReader;
import com.taxilla.apps.commonutil.FileUtils;
import com.taxilla.apps.testbase.TestBase;

public class TC_007_ValidationCheckForError2_OutwardTransactionV3App2 extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		//	LoginToTaxilla();
	}
	@Test()
	public void verifyErrorPhase2InOutwardTransactionV3() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify the Error message phase 2 in Outward Transactions v3 asset";
		String TestCaseNum = "TC_007";
		String TestCaseTitle = "Verify whether all the phase 2 error message are displayed or not in Outward Transactions V3 asset";
		String TestStatus = "Pass";
		String StartDateTime = DateUtils.CurrentDateTime();
		String EndDateTime = DateUtils.CurrentDateTime();
		String FailMsg, PassMsg;
		String StatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
		int SlNo=(reader.getResultRecordsCount(StatusReportFileName))+1;
		int conditionPassCount=0;
		String InputFilePath=reader.getTestDataFromXLSXSheet("testData", "InputFilePath", 2, 0);
		boolean status=true;
		String DownLoadFilePathOfExcel= System.getProperty("user.dir")+"\\DownloadedFiles\\";

		String ExpectedErrorMessage17="As [Supply Type] is selected as 'Exempted' then [IGST Rate] should be zero. 2.)As [Supply Type] is selected as 'Exempted' then [IGST Amount] should be zero";
		String ExpectedErrorMessage18="As [Supply Type] is selected as 'Non GST' then [IGST Rate] should be zero. 2.)As [Supply Type] is selected as 'Non GST' then [IGST Amount] should be zero";	
		String ExpectedErrorMessage19="If 'Transaction Subtype - Sales to SEZ Unit/Sales to SEZ Developer', then 'CGST Amount and SGST / UT GST Amount' are not allowed";
		String ExpectedErrorMessage20= "if [Inter - State/Intra - State/Inter - Country Transaction] is 'Inter-State' or 'Intra - State' and [TrasactionSubtype] is 'Sales to SEZ Unit' or 'Sales to SEZ Developer' and  [With/Without payment of GST] is 'With Payment of GST' then [IGST Amount] should be greater than zero";
		String ExpectedErrorMessage21= "if [Inter - State/Intra - State/Inter - Country Transaction] is 'Inter-State' or 'Intra - State' and [TrasactionSubtype] is 'Sales to SEZ Unit' or 'Sales to SEZ Developer' and  [With/Without payment of GST] is 'With Payment of GST' then [CGST Amount]+[SGST / UT GST Amount] should be zero";
		String ExpectedErrorMessage22= "As [Customer Country Code] is 'IN' and [Transaction Subtype] is Domestic Sales then [Place of Supply] should not be 'Others'";
		String ExpectedErrorMessage23= "As [Customer Country Code] is 'IN' and [Transaction Subtype] is Domestic Sales then [Place of Supply] should not be 'Others'";
		String ExpectedErrorMessage24= "[IGST Amount]+[CGST Amount]+[SGST / UT GST Amount] should be zero for 'Exports - Without payment of GST'";
		String ExpectedErrorMessage25= "[IGST Amount]should be greater than zero when  'With Payment of GST and Inter - Country";
		String ExpectedErrorMessage26= "[CGST Amount]+[SGST / UT GST Amount] should be zero when 'With Payment of GST and Inter - Country";
		String ExpectedErrorMessage27= "As [Inter - State/Intra - State/Inter - Country Transaction] is selected as 'Inter-State' or 'Inter - Country' then [SGST / UT GST Amount] should be zero";
		String ExpectedErrorMessage28= "if [Inter - State/Intra - State/Inter - Country Transaction] is 'Inter-State' or 'Intra - State' and [TrasactionSubtype] is 'Sales to SEZ Unit' or 'Sales to SEZ Developer' and  [With/Without payment of GST] is 'With Payment of GST' then [IGST Amount] should be greater than zero";
		String ExpectedErrorMessage29= "First two Digits of [Location / Unit / Sub Org GSTIN] should be equal to its corresponding [Ship from State]";
		String ExpectedErrorMessage30="If [GST Supply Category] is 'Taxable' and [Transaction SubType] is 'Sales to SEZ Unit' and [With / Without payment of GST] is 'With Payment of GST' then [IGST Rate]+[CGST Rate]+[SGST Rate] should be greater than zero";
		String ExpectedErrorMessage31= "If [GST Supply Category] is 'Taxable' and [Transaction SubType] is 'Deemed Exports' and [With / Without payment of GST] is 'With Payment of GST' then [IGST Rate]+[CGST Rate]+[SGST Rate] should be greater than zero";


		try{

			String LatestFileName=taxillaUtil.openTheLatestFileInFolder(DownLoadFilePathOfExcel, "xlsx");
			System.out.println("LatestFileName:"+ LatestFileName);

			String ActualErrorMessage7=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 7, 57);
			
			String ActualErrorMessage8=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 8, 57);
			
			String ActualErrorMessage9=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 9, 57);
			
			String ActualErrorMessage10=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 10, 57);
			
			String ActualErrorMessage13=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 13, 57);
			
			String ActualErrorMessage14=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 14, 57);
			
			String ActualErrorMessage15=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 15, 57);
			
			String ActualErrorMessage16=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 16, 57);
			
			String ActualErrorMessage19=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 19, 57);
			
			String ActualErrorMessage21=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 21, 57);
			
			if (ActualErrorMessage7.contains(ExpectedErrorMessage17) || ActualErrorMessage8.contains(ExpectedErrorMessage18) ||
					ActualErrorMessage9.contains(ExpectedErrorMessage19) || ActualErrorMessage9.contains(ExpectedErrorMessage20) ||
					ActualErrorMessage9.contains(ExpectedErrorMessage21) || ActualErrorMessage9.contains(ExpectedErrorMessage22) ||
					ActualErrorMessage10.contains(ExpectedErrorMessage23) || ActualErrorMessage13.contains(ExpectedErrorMessage24) ||
					ActualErrorMessage14.contains(ExpectedErrorMessage25) || ActualErrorMessage14.contains(ExpectedErrorMessage26) ||
					ActualErrorMessage15.contains(ExpectedErrorMessage27) || ActualErrorMessage15.contains(ExpectedErrorMessage28) ||
					ActualErrorMessage16.contains(ExpectedErrorMessage29) || ActualErrorMessage19.contains(ExpectedErrorMessage30) ||
					ActualErrorMessage21.contains(ExpectedErrorMessage31)) 
			{
				PassMsg ="<b>Expected Error Message 17:</b>"+ExpectedErrorMessage17+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 18:</b>"+ExpectedErrorMessage18+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 19:</b>"+ExpectedErrorMessage19+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 20:</b>"+ExpectedErrorMessage20+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 21:</b>"+ExpectedErrorMessage21+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 22:</b>"+ExpectedErrorMessage22+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 23:</b>"+ExpectedErrorMessage23+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 24:</b>"+ExpectedErrorMessage24+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 25:</b>"+ExpectedErrorMessage25+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 26:</b>"+ExpectedErrorMessage26+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 27:</b>"+ExpectedErrorMessage27+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 28:</b>"+ExpectedErrorMessage28+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 29:</b>"+ExpectedErrorMessage29+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 30:</b>"+ExpectedErrorMessage30+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 31:</b>"+ExpectedErrorMessage31+"-is displayed in Outward Transaction V3 asset";

				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>Expected Error Message 17:</b>"+ExpectedErrorMessage17+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 18:</b>"+ExpectedErrorMessage18+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 19:</b>"+ExpectedErrorMessage19+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 20:</b>"+ExpectedErrorMessage20+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 21:</b>"+ExpectedErrorMessage21+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 22:</b>"+ExpectedErrorMessage22+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 23:</b>"+ExpectedErrorMessage23+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 24:</b>"+ExpectedErrorMessage24+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 25:</b>"+ExpectedErrorMessage25+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 26:</b>"+ExpectedErrorMessage26+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 27:</b>"+ExpectedErrorMessage27+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 28:</b>"+ExpectedErrorMessage28+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 29:</b>"+ExpectedErrorMessage29+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 30:</b>"+ExpectedErrorMessage30+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 31:</b>"+ExpectedErrorMessage31+"-is not displayed in Outward Transaction V3 asset";
				TestStatus = "Fail";
				EndDateTime = DateUtils.CurrentDateTime();
				SnapshotName = TestCaseNum+"_"+TestStatus+"_"+DateUtils.CurrentDateTimeMM_dd_YYYY_HH_mm_ss();
				String ScreenshotLink=browserUtil.GetSnapShot(SnapshotName);
				SnapshotLink = ScreenshotLink+SnapshotName+".png";
				//SnapshotLink=screenshot+".png";
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, FailMsg, ScreenshotLink, StartDateTime, EndDateTime);		
			}


		}

		catch (Exception e) {
			TestStatus = "Fail";
			EndDateTime = DateUtils.CurrentDateTime();
			FailMsg = "<b>Exception Occured:</b> Exception Message - " + e.getMessage();
			SnapshotName = TestCaseNum+"_"+TestStatus+"_"+DateUtils.CurrentDateTimeMM_dd_YYYY_HH_mm_ss();
			String ScreenshotLink=browserUtil.GetSnapShot(SnapshotName);
			SnapshotLink = ScreenshotLink+SnapshotName+".png";
			//SnapshotLink=screenshot+".png";
			//System.out.println("SnapshotLink::::"+SnapshotLink);
			reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, FailMsg, ScreenshotLink, StartDateTime, EndDateTime);
			//browserUtil.closeBrowser();
			throw e;
		}

	}
}
