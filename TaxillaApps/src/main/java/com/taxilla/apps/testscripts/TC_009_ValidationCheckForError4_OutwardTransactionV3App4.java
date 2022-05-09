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

public class TC_009_ValidationCheckForError4_OutwardTransactionV3App4 extends TestBase
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
		String TestScriptName = "Verify the Error message phase 4 in Outward Transactions v3 asset";
		String TestCaseNum = "TC_009";
		String TestCaseTitle = "Verify whether all the phase 4 error message are displayed or not in Outward Transactions V3 asset";
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

		String ExpectedErrorMessage53="As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and customer GSTIN is not Blank and GST Supply Category is selected as 'Taxable' and Transaction Sub Type is selected as 'Domestic Sales', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage54="As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and customer GSTIN is not Blank and GST Supply Category is selected as 'Taxable' and Transaction Sub Type is selected as 'Domestic Sales', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage55= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Sales to SEZ Unit', then [Original Invoice Number] is mandatory";
		String ExpectedErrorMessage56= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Sales to SEZ Unit', then [Original Invoice Date] is mandatory";
		String ExpectedErrorMessage57= "Value[Test] selected for field[Reason for issuing Credit / Debit Note] is not a valid lookup";
		String ExpectedErrorMessage58= "If '[Transaction Type] is -Sales' then '[Reason for issuing Credit / Debit Note]' should be blank";
		String ExpectedErrorMessage59=  "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Deemed Exports', then [Original Invoice Number] is mandatory";
		String ExpectedErrorMessage60= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Deemed Exports', then [Original Invoice Date] is mandatory";
		String ExpectedErrorMessage61="As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)'and [Transaction Sub Type] is selected as 'Sales to SEZ Unit', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage62= "As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)'and [Transaction Sub Type] is selected as 'Sales to SEZ Unit', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage63="As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)'and [Transaction Sub Type] is selected as 'Sales to SEZ Unit', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage64= "As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)'and [Transaction Sub Type] is selected as 'Sales to SEZ Unit', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage65= "As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)'and [Transaction Sub Type] is selected as 'Deemed Exports', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage66= "As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)'and [Transaction Sub Type] is selected as 'Deemed Exports', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage67="As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [ship from  state] not equal to [POS] and [Original Invoice Value] greater then 250000 and [customer GSTIN] is Blank and [GST Supply Category] is selected as 'Taxable' and [Transaction Sub Type] is selected as 'Domestic Sales', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage68="As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [ship from  state] not equal to [POS] and [Original Invoice Value] greater then 250000 and [customer GSTIN] is Blank and [GST Supply Category] is selected as 'Taxable' and [Transaction Sub Type] is selected as 'Domestic Sales', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage69="As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [ship from  state] not equal to [POS] and [Original Invoice Value] greater then 250000 and [customer GSTIN] is Blank and [GST Supply Category] is selected as 'Taxable' and [Transaction Sub Type] is selected as 'Domestic Sales', then [Original Place of Supply] is mandatory";
		String ExpectedErrorMessage70= "First two Digits of [Location / Unit / Sub Org GSTIN] should be equal to its corresponding [Ship from State]";
		String ExpectedErrorMessage71= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [customer GSTIN] is Blank and [GST Supply Category] is selected as 'Taxable'and [Transaction Sub Type] is selected as 'Domestic Sales',then [Original Invoice Value] is mandatory";
		String ExpectedErrorMessage72= "If GST Rate is greater than 'Zero' and GST Amount is greater than 'Zero' Taxabale value should be greater than 'Zero'";
		
		
		try{

			String LatestFileName=taxillaUtil.openTheLatestFileInFolder(DownLoadFilePathOfExcel, "xlsx");
			System.out.println("LatestFileName:"+ LatestFileName);

			String ActualErrorMessage38=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 38, 57);
			
			String ActualErrorMessage39=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 39, 57);

			String ActualErrorMessage40=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 40, 57);

			String ActualErrorMessage42=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 42, 57);

			String ActualErrorMessage44=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 44, 57);
			
			String ActualErrorMessage45=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 45, 57);
			
			String ActualErrorMessage46=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 46, 57);
			
			String ActualErrorMessage48=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 48, 57);
			
			String ActualErrorMessage51=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 51, 57);
			
			String ActualErrorMessage52=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 52, 57);
			
			String ActualErrorMessage54=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 54, 57);
			
			if (ActualErrorMessage38.contains(ExpectedErrorMessage53) || ActualErrorMessage38.contains(ExpectedErrorMessage54) ||
					ActualErrorMessage39.contains(ExpectedErrorMessage55) || ActualErrorMessage39.contains(ExpectedErrorMessage56) ||
					ActualErrorMessage40.contains(ExpectedErrorMessage57) || ActualErrorMessage40.contains(ExpectedErrorMessage58) ||
					ActualErrorMessage42.contains(ExpectedErrorMessage59) || ActualErrorMessage42.contains(ExpectedErrorMessage60) ||
					ActualErrorMessage44.contains(ExpectedErrorMessage61) || ActualErrorMessage44.contains(ExpectedErrorMessage62) ||
					ActualErrorMessage45.contains(ExpectedErrorMessage63) || ActualErrorMessage45.contains(ExpectedErrorMessage64) ||
					ActualErrorMessage46.contains(ExpectedErrorMessage65) || ActualErrorMessage46.contains(ExpectedErrorMessage66) ||
					ActualErrorMessage48.contains(ExpectedErrorMessage67) || ActualErrorMessage48.contains(ExpectedErrorMessage68)||
					ActualErrorMessage48.contains(ExpectedErrorMessage69) || ActualErrorMessage51.contains(ExpectedErrorMessage70) ||
					ActualErrorMessage52.contains(ExpectedErrorMessage71) || ActualErrorMessage54.contains(ExpectedErrorMessage72))
			{
				PassMsg ="<b>Expected Error Message 53:</b>"+ExpectedErrorMessage53+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 54:</b>"+ExpectedErrorMessage54+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 55:</b>"+ExpectedErrorMessage55+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 56:</b>"+ExpectedErrorMessage56+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 57:</b>"+ExpectedErrorMessage57+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 58:</b>"+ExpectedErrorMessage58+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 59:</b>"+ExpectedErrorMessage59+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 60:</b>"+ExpectedErrorMessage60+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 61:</b>"+ExpectedErrorMessage61+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 62:</b>"+ExpectedErrorMessage62+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 63:</b>"+ExpectedErrorMessage63+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 64:</b>"+ExpectedErrorMessage64+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 65:</b>"+ExpectedErrorMessage65+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 66:</b>"+ExpectedErrorMessage66+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 67:</b>"+ExpectedErrorMessage67+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 68:</b>"+ExpectedErrorMessage68+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 69:</b>"+ExpectedErrorMessage69+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 70:</b>"+ExpectedErrorMessage70+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 71:</b>"+ExpectedErrorMessage71+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 72:</b>"+ExpectedErrorMessage72+"-is displayed in Outward Transaction V3 asset" + "<br>";

				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>Expected Error Message 53:</b>"+ExpectedErrorMessage53+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 54:</b>"+ExpectedErrorMessage54+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 55:</b>"+ExpectedErrorMessage55+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 56:</b>"+ExpectedErrorMessage56+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 57:</b>"+ExpectedErrorMessage57+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 58:</b>"+ExpectedErrorMessage58+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 59:</b>"+ExpectedErrorMessage59+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 60:</b>"+ExpectedErrorMessage60+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 61:</b>"+ExpectedErrorMessage61+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 62:</b>"+ExpectedErrorMessage62+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 63:</b>"+ExpectedErrorMessage63+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 64:</b>"+ExpectedErrorMessage64+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 65:</b>"+ExpectedErrorMessage65+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 66:</b>"+ExpectedErrorMessage66+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 67:</b>"+ExpectedErrorMessage67+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 68:</b>"+ExpectedErrorMessage68+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 69:</b>"+ExpectedErrorMessage69+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 70:</b>"+ExpectedErrorMessage70+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 71:</b>"+ExpectedErrorMessage71+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 72:</b>"+ExpectedErrorMessage72+"-is not displayed in Outward Transaction V3 asset" + "<br>";
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
