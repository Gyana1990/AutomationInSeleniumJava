package com.taxilla.apps.testscripts;

import java.net.Inet4Address;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.taxilla.apps.commonutil.DateUtils;
import com.taxilla.apps.commonutil.ExcelReader;
import com.taxilla.apps.commonutil.FileUtils;
import com.taxilla.apps.testbase.TestBase;

public class TC_005_PostGSTR1V1_2HtmlReportVerification extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		//LoginToTaxilla();
	}
	@Test()
	public void postGSTR1ReportVerification() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify the GSTR-1 Html Report";
		String TestCaseNum = "TC_005";
		String TestCaseTitle = "Verify whether all the data and amounts are matching as expected in GSTR-1 Html Report";
		String TestStatus = "Pass";
		String StartDateTime = DateUtils.CurrentDateTime();
		String EndDateTime = DateUtils.CurrentDateTime();
		String FailMsg, PassMsg;
		String StatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
		int SlNo=(reader.getResultRecordsCount(StatusReportFileName))+1;
		int conditionPassCount=0;
		String InputFilePath=reader.getTestDataFromXLSXSheet("testData", "InputFilePath", 1, 0);
		String DownLoadFilePath= System.getProperty("user.dir")+"\\DownloadedFiles\\";
		String TestDataFolderPath= System.getProperty("user.dir")+"\\TestData\\";

		String ReportExt="html";

		try{

			String URL=taxillaUtil.openTheLatestFileInFolder(DownLoadFilePath, ReportExt);
			System.out.println("url-"+URL);
			driver = browserUtil.initalizeDriver(environmentLocators.getProperty("browser"));
			browserUtil.openUrl(URL);
			String ActualText=driver.getPageSource();
			//System.out.println("ActualText-"+ ActualText);

			/*
			 * Check Expected Html File
			 */

			String ExpURL=taxillaUtil.openTheLatestFileInFolder(TestDataFolderPath, ReportExt);
			System.out.println("ExpURL-"+ExpURL);
			driver = browserUtil.initalizeDriver(environmentLocators.getProperty("browser"));
			browserUtil.openUrl(ExpURL);
			String ExpectedText=driver.getPageSource();
			//System.out.println("ExpectedText-"+ ExpectedText);

			if (ActualText.equalsIgnoreCase(ExpectedText))
			{
				PassMsg = "<b>All Data and amounts are:</b>"+" "+"displayed correctly in GSTR-1 html report";
				//System.out.println("PassMsg"+ PassMsg);
				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>All Data and amounts are:</b>"+" "+"not matching as expected in GSTR-1 html report";
				//System.out.println("FailMsg"+ FailMsg);
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
