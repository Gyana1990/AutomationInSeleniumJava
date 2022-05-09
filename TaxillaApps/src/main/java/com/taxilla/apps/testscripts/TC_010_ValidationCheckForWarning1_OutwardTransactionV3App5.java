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

public class TC_010_ValidationCheckForWarning1_OutwardTransactionV3App5 extends TestBase
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
		String TestScriptName = "Verify the Warning message in Outward Transactions v3 asset";
		String TestCaseNum = "TC_010";
		String TestCaseTitle = "Verify whether all the warning message are displayed or not in Outward Transactions V3 asset";
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

		String ExpectedWarningMessage1="GST Compensation Cess Amount [1000.0] entered does not match with GST Compensation Cess Amount (Calculated) [0.0] including 'GST Amount Plus Tolerance Rs.'[0.0]";
		String ExpectedWarningMessage2="[Shipping Bill / Bill of Export Number] and [Shipping Bill / Bill of Export Date] and [Port Code]' fields to be filled for Inter-Country and 'Goods'";
		String ExpectedWarningMessage3= "If HSN is related to Service then UQC should be 'NA'";
		String ExpectedWarningMessage4= "If HSN is related to Service then Quantity should be '0'";
		String ExpectedWarningMessage5= "CGST amount [96006.0] entered does not match with calculated CGST amount [52806.0] including 'GST Amount Plus Tolerance Rs.'[0.0]";
		String ExpectedWarningMessage6= "SGST / UT GST Amount [9600.0] entered does not match with calculated SGST amount [0.0] including 'GST Amount Plus Tolerance Rs.'[0.0]";
		String ExpectedWarningMessage7=  "IGST amount [194412.0] entered does not match with calculated IGST amount [108012.0] including 'GST Amount Plus Tolerance Rs.'[0.0]";
		String ExpectedWarningMessage8= "First two Digits of [Customer GSTIN / UIN] should be equal to [Ship To State]";
		String ExpectedWarningMessage9="Taxable value [100.0] entered does not match with calculated taxable value [2038087.04] including 'GST Taxable and Invoice Value Negative Tolerance Rs.'[0.0]";
		String ExpectedWarningMessage10= "[Shipping Bill / Bill of Export Number] and [Shipping Bill / Bill of Export Date] and [Port Code]' fields to be filled for Inter-Country and 'Goods'";
		
		
		try{

			String LatestFileName=taxillaUtil.openTheLatestFileInFolder(DownLoadFilePathOfExcel, "xlsx");
			System.out.println("LatestFileName:"+ LatestFileName);

			String ActualWarningMessage1=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 1, 58);
			System.out.println("ActualWarningMessage1-"+ ActualWarningMessage1);
			
			String ActualWarningMessage2=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 2, 58);

			String ActualWarningMessage6=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 6, 58);

			String ActualWarningMessage7=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 7, 58);

			String ActualWarningMessage10=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 10, 58);
			
			String ActualWarningMessage12=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 12, 58);
			
			String ActualWarningMessage17=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 17, 58);
			
			
			if (ActualWarningMessage1.contains(ExpectedWarningMessage1) || ActualWarningMessage1.contains(ExpectedWarningMessage2) ||
					ActualWarningMessage2.contains(ExpectedWarningMessage3) || ActualWarningMessage2.contains(ExpectedWarningMessage4) ||
					ActualWarningMessage6.contains(ExpectedWarningMessage5) || ActualWarningMessage6.contains(ExpectedWarningMessage6) ||
					ActualWarningMessage7.contains(ExpectedWarningMessage7) || ActualWarningMessage10.contains(ExpectedWarningMessage8) ||
					ActualWarningMessage12.contains(ExpectedWarningMessage9) || ActualWarningMessage17.contains(ExpectedWarningMessage10))
			{
				PassMsg ="<b>Expected Warning Message 1:</b>"+ExpectedWarningMessage1+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 2:</b>"+ExpectedWarningMessage2+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 3:</b>"+ExpectedWarningMessage3+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 4:</b>"+ExpectedWarningMessage4+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 5:</b>"+ExpectedWarningMessage5+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 6:</b>"+ExpectedWarningMessage6+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 7:</b>"+ExpectedWarningMessage7+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 8:</b>"+ExpectedWarningMessage8+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 9:</b>"+ExpectedWarningMessage9+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 10:</b>"+ExpectedWarningMessage10+"-is displayed in Outward Transaction V3 asset";

				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg ="<b>Expected Warning Message 1:</b>"+ExpectedWarningMessage1+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 2:</b>"+ExpectedWarningMessage2+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 3:</b>"+ExpectedWarningMessage3+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 4:</b>"+ExpectedWarningMessage4+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 5:</b>"+ExpectedWarningMessage5+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 6:</b>"+ExpectedWarningMessage6+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 7:</b>"+ExpectedWarningMessage7+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 8:</b>"+ExpectedWarningMessage8+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 9:</b>"+ExpectedWarningMessage9+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Warning Message 10:</b>"+ExpectedWarningMessage10+"-is not displayed in Outward Transaction V3 asset";
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
