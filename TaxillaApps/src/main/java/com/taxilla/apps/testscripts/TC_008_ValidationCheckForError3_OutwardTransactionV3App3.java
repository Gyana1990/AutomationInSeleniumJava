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

public class TC_008_ValidationCheckForError3_OutwardTransactionV3App3 extends TestBase
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
		String TestScriptName = "Verify the Error message phase 3 in Outward Transactions v3 asset";
		String TestCaseNum = "TC_008";
		String TestCaseTitle = "Verify whether all the phase 3 error message are displayed or not in Outward Transactions V3 asset";
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

		String ExpectedErrorMessage32="If [GST Supply Category] is 'Taxable' and [Transaction SubType] is 'Sales to SEZ Developer' and [With / Without payment of GST] is 'With Payment of GST' then [IGST Rate]+[CGST Rate]+[SGST Rate] should be greater than zero";
		String ExpectedErrorMessage33="[With / Without payment of GST] field is mandatory for [Transaction SubType] - 'Sales to SEZ Unit'";	
		String ExpectedErrorMessage34="[With / Without payment of GST] field is mandatory for [Transaction SubType] - 'Sales to SEZ Developer'";
		String ExpectedErrorMessage35= "If [Transaction Subtype] is other than 'Branch Transfer' then [Location / Unit / Sub Org GSTIN] should not be equal to [Customer GSTIN / UIN]";
		String ExpectedErrorMessage36= "If [Transaction Sub Type] is 'Flag for Supply covered under sec 7 of IGST Act' then 'IGST Amount' Should be grater than Zero";
		String ExpectedErrorMessage37= "If [Transaction Sub Type] is 'Flag for Supply covered under sec 7 of IGST Act' then 'CGST Amount' Should be Zero";
		String ExpectedErrorMessage38= "If [Transaction Sub Type] is 'Flag for Supply covered under sec 7 of IGST Act' then 'SGST Amount' Should be Zero";
		String ExpectedErrorMessage39= "If '[Transaction Type] is - Sales' then '[Pre GST Regime Credit / Debit Note]' should not be Yes";
		String ExpectedErrorMessage40= "As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [ship from  state] not equal to [POS] and [Original Invoice Value] greater then 250000 and [customer GSTIN] is Blank and [GST Supply Category] is selected as 'Taxable' and [Transaction Sub Type] is selected as 'Domestic Sales', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage41= "As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [ship from  state] not equal to [POS] and [Original Invoice Value] greater then 250000 and [customer GSTIN] is Blank and [GST Supply Category] is selected as 'Taxable' and [Transaction Sub Type] is selected as 'Domestic Sales', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage42= "As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [ship from  state] not equal to [POS] and [Original Invoice Value] greater then 250000 and [customer GSTIN] is Blank and [GST Supply Category] is selected as 'Taxable' and [Transaction Sub Type] is selected as 'Domestic Sales', then [Original Place of Supply] is mandatory";
		String ExpectedErrorMessage43= "As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Exports', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage44= "As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Exports', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage45="As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Exports', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage46= "As [Transaction Type] is selected as 'Debit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [Transaction Sub Type] is selected as 'Exports', then [Original Credit/Debit Note date] is mandatory";
		String ExpectedErrorMessage47= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [customer gstin] is blank and [Transaction Sub Type] is selected as 'Exports', then [Original Invoice Date] is mandatory";
		String ExpectedErrorMessage48= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and [customer gstin] is blank and [Transaction Sub Type] is selected as 'Exports', then [Original Invoice Number] is mandatory";
		String ExpectedErrorMessage49= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and customer GSTIN is not Blank and GST Supply Category is selected as 'Taxable' and Transaction Sub Type is selected as 'Domestic Sales', then [Original Invoice Number] is mandatory";
		String ExpectedErrorMessage50= "As [Transaction Type] is selected as 'Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and customer GSTIN is not Blank and GST Supply Category is selected as 'Taxable' and Transaction Sub Type is selected as 'Domestic Sales', then [Original Invoice Date] is mandatory";
		String ExpectedErrorMessage51= "As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and customer GSTIN is not Blank and GST Supply Category is selected as 'Taxable' and Transaction Sub Type is selected as 'Domestic Sales', then [Original Credit/Debit Note Number] is mandatory";
		String ExpectedErrorMessage52= "As [Transaction Type] is selected as 'Credit Note for Sales' and [Invoice type] is selected as 'Amendments to earlier tax periods Invoice (including post supply discounts)' and customer GSTIN is not Blank and GST Supply Category is selected as 'Taxable' and Transaction Sub Type is selected as 'Domestic Sales', then [Original Credit/Debit Note date] is mandatory";


		try{

			String LatestFileName=taxillaUtil.openTheLatestFileInFolder(DownLoadFilePathOfExcel, "xlsx");
			System.out.println("LatestFileName:"+ LatestFileName);

			String ActualErrorMessage22=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 22, 57);

			String ActualErrorMessage24=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 24, 57);

			String ActualErrorMessage26=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 26, 57);

			String ActualErrorMessage27=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 27, 57);

			String ActualErrorMessage28=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 28, 57);

			String ActualErrorMessage29=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 29, 57);

			String ActualErrorMessage30=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 30, 57);

			String ActualErrorMessage32=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 32, 57);

			String ActualErrorMessage33=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 33, 57);

			String ActualErrorMessage35=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 35, 57);

			String ActualErrorMessage36=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 36, 57);

			String ActualErrorMessage37=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 37, 57);

			if (ActualErrorMessage22.contains(ExpectedErrorMessage32) || ActualErrorMessage24.contains(ExpectedErrorMessage33) ||
					ActualErrorMessage26.contains(ExpectedErrorMessage34) || ActualErrorMessage27.contains(ExpectedErrorMessage35) ||
					ActualErrorMessage28.contains(ExpectedErrorMessage36) || ActualErrorMessage28.contains(ExpectedErrorMessage37) ||
					ActualErrorMessage28.contains(ExpectedErrorMessage38) || ActualErrorMessage29.contains(ExpectedErrorMessage39) ||
					ActualErrorMessage30.contains(ExpectedErrorMessage40) || ActualErrorMessage30.contains(ExpectedErrorMessage41) ||
					ActualErrorMessage30.contains(ExpectedErrorMessage42) || ActualErrorMessage32.contains(ExpectedErrorMessage43) ||
					ActualErrorMessage32.contains(ExpectedErrorMessage44) || ActualErrorMessage33.contains(ExpectedErrorMessage45) ||
					ActualErrorMessage33.contains(ExpectedErrorMessage46) || ActualErrorMessage35.contains(ExpectedErrorMessage47)||
					ActualErrorMessage35.contains(ExpectedErrorMessage48) || ActualErrorMessage36.contains(ExpectedErrorMessage49) ||
					ActualErrorMessage36.contains(ExpectedErrorMessage50) || ActualErrorMessage37.contains(ExpectedErrorMessage51) ||
					ActualErrorMessage37.contains(ExpectedErrorMessage52)) 
			{
				PassMsg ="<b>Expected Error Message 32:</b>"+ExpectedErrorMessage32+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 33:</b>"+ExpectedErrorMessage33+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 34:</b>"+ExpectedErrorMessage34+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 35:</b>"+ExpectedErrorMessage35+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 36:</b>"+ExpectedErrorMessage36+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 37:</b>"+ExpectedErrorMessage37+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 38:</b>"+ExpectedErrorMessage38+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 39:</b>"+ExpectedErrorMessage39+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 40:</b>"+ExpectedErrorMessage40+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 41:</b>"+ExpectedErrorMessage41+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 42:</b>"+ExpectedErrorMessage42+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 43:</b>"+ExpectedErrorMessage43+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 44:</b>"+ExpectedErrorMessage44+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 45:</b>"+ExpectedErrorMessage45+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 46:</b>"+ExpectedErrorMessage46+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 47:</b>"+ExpectedErrorMessage47+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 48:</b>"+ExpectedErrorMessage48+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 49:</b>"+ExpectedErrorMessage49+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 50:</b>"+ExpectedErrorMessage50+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 51:</b>"+ExpectedErrorMessage51+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 52:</b>"+ExpectedErrorMessage52+"-is displayed in Outward Transaction V3 asset" + "<br>";

				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>Expected Error Message 32:</b>"+ExpectedErrorMessage32+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 33:</b>"+ExpectedErrorMessage33+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 34:</b>"+ExpectedErrorMessage34+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 35:</b>"+ExpectedErrorMessage35+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 36:</b>"+ExpectedErrorMessage36+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 37:</b>"+ExpectedErrorMessage37+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 38:</b>"+ExpectedErrorMessage38+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 39:</b>"+ExpectedErrorMessage39+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 40:</b>"+ExpectedErrorMessage40+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 41:</b>"+ExpectedErrorMessage41+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 42:</b>"+ExpectedErrorMessage42+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 43:</b>"+ExpectedErrorMessage43+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 44:</b>"+ExpectedErrorMessage44+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 45:</b>"+ExpectedErrorMessage45+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 46:</b>"+ExpectedErrorMessage46+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 47:</b>"+ExpectedErrorMessage47+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 48:</b>"+ExpectedErrorMessage48+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 49:</b>"+ExpectedErrorMessage49+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 50:</b>"+ExpectedErrorMessage50+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 51:</b>"+ExpectedErrorMessage51+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 52:</b>"+ExpectedErrorMessage52+"-is not displayed in Outward Transaction V3 asset" + "<br>";
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
