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

public class TC_003_RegisteredInvoicesForReconLevel2 extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		//LoginToTaxilla();
	}
	@Test()
	public void requestCreationIevel2() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify the request creation in Registered Invoices for Recon asset";
		String TestCaseNum = "TC_003";
		String TestCaseTitle = "Verify whether request is created or not in level 2 Registered Invoices for Recon asset";
		String TestStatus = "Pass";
		String StartDateTime = DateUtils.CurrentDateTime();
		String EndDateTime = DateUtils.CurrentDateTime();
		String FailMsg, PassMsg;
		String StatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
		int SlNo=(reader.getResultRecordsCount(StatusReportFileName))+1;
		int conditionPassCount=0;
		String InputFilePath=reader.getTestDataFromXLSXSheet("testData", "InputFilePath", 1, 0);

		try{
//			taxillaUtil.selectingLocation();
//
//			browserUtil.waitForCondition(ORlocator.getProperty("DisplayOfAssetName"), 500); 
//			taxillaUtil.waitForInvisibleOfSpinner();
//			browserUtil.clickOnButton(ORlocator.getProperty("SelectingAppsDropdownIcon"));
//			browserUtil.setText(ORlocator.getProperty("SearchApp"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 0));
//			browserUtil.explicitlyWaitForVisibilityOfElement(ORlocator.getProperty("SelectingAssetNameFromList"), 500);
//			browserUtil.clickOnButton(ORlocator.getProperty("SelectingAssetNameFromList"));
//
//			//Click on level 1 Outward Transactions-GSTR-1 Return-Sales asset
//			browserUtil.clickOnButton(ORlocator.getProperty("Level1AssetName"));
//			browserUtil.waitForCondition(ORlocator.getProperty("AppProcessButton"), 600); 
//			taxillaUtil.waitForInvisibleOfSpinner();
//			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("AppProcessButton"), 500);
//			//browserUtil.hardPause(1);
//			//browserUtil.waitForElementPresentInDOM(ORlocator.getProperty("AppProcessButton"));
//			browserUtil.hardPause(3);
//			browserUtil.clickOnButton(ORlocator.getProperty("AppProcessButton"));
//
//			browserUtil.waitForCondition(ORlocator.getProperty("ViewIconInAllProcessPage"), 600); 
//			taxillaUtil.waitForInvisibleOfSpinner();
//			browserUtil.hardPause(5);
//			//browserUtil.waitForElementPresentInDOM(ORlocator.getProperty("ViewIconInAllProcessPage"));
//			//browserUtil.explicitlyWaitForVisibilityOfElement(ORlocator.getProperty("ViewIconInAllProcessPage"), 300);
//			browserUtil.clickOnButton(ORlocator.getProperty("ViewIconInAllProcessPage"));
//			
//			browserUtil.waitForCondition(ORlocator.getProperty("CloaseIconInErrorWindow"), 600); 
//			taxillaUtil.waitForInvisibleOfSpinner();
//			//browserUtil.waitForElementPresentInDOM(ORlocator.getProperty("CloaseIconInErrorWindow"));
//			browserUtil.hardPause(5);
//			
			browserUtil.clickOnButton(ORlocator.getProperty("CloaseIconInErrorWindow"));
			//browserUtil.waitForCondition(ORlocator.getProperty("GeneralDetailsEntity_OutwardTransactionV3"), 600); 
			browserUtil.clickOnButton(ORlocator.getProperty("GeneralDetailsEntity_OutwardTransactionV3"));
			
			browserUtil.waitForCondition(ORlocator.getProperty("GeneralDetailsGRTINField"), 600); 
			taxillaUtil.waitForInvisibleOfSpinner();

			String GeneralDetailsGSTINOutward= browserUtil.gettextFromElement(ORlocator.getProperty("GeneralDetailsGRTINField"));
			System.out.println("GeneralDetailsGSTINOutward:"+GeneralDetailsGSTINOutward);
			
			String GeneralDetailsReturnYearOutward= browserUtil.gettextFromElement(ORlocator.getProperty("GeneralDetailsReturnYearField"));
			System.out.println("GeneralDetailsReturnYearOutward:"+GeneralDetailsReturnYearOutward);
			
			String GeneralDetailsReturnMonthOutward= browserUtil.gettextFromElement(ORlocator.getProperty("GeneralDetailsReturnMonthField"));
			System.out.println("GeneralDetailsReturnMonthOutward:"+GeneralDetailsReturnMonthOutward);
			
			//Click on Bridge
			//browserUtil.waitForCondition(ORlocator.getProperty("SelectingAppsDropdownIcon"), 600); 
			//taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("SelectingAppsDropdownIcon"));
			browserUtil.hardPause(2);
			//browserUtil.waitForElementPresentInDOM(ORlocator.getProperty("Level2AssetNameRegisteredInvoiceForRecon"));
			browserUtil.clickOnButton(ORlocator.getProperty("Level2AssetNameRegisteredInvoiceForRecon"));
			
			//1st row record in All process page
			browserUtil.waitForCondition(ORlocator.getProperty("ViewIconInAllProcessPage"), 600); 
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(5);
			//browserUtil.waitForElementPresentInDOM(ORlocator.getProperty("ViewIconInAllProcessPage"));
			//browserUtil.explicitlyWaitForVisibilityOfElement(ORlocator.getProperty("ViewIconInAllProcessPage"), 300);
			browserUtil.clickOnButton(ORlocator.getProperty("ViewIconInAllProcessPage"));
			
			browserUtil.waitForCondition(ORlocator.getProperty("CloaseIconInErrorWindow"), 600); 
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(5);
			
			browserUtil.clickOnButton(ORlocator.getProperty("CloaseIconInErrorWindow"));
			//browserUtil.waitForCondition(ORlocator.getProperty("GeneralDetailsEntity_OutwardTransactionV3"), 600); 
			browserUtil.clickOnButton(ORlocator.getProperty("GeneralDetailsEntity_OutwardTransactionV3"));
			
			
			String GeneralDetailsGSTINRecon= browserUtil.gettextFromElement(ORlocator.getProperty("GeneralDetailsGRTINField"));
			System.out.println("GeneralDetailsGSTINRecon:"+GeneralDetailsGSTINRecon);
			
			String GeneralDetailsYearRecon= browserUtil.gettextFromElement(ORlocator.getProperty("GeneralDetailsYearRecon"));
			System.out.println("GeneralDetailsYearRecon:"+GeneralDetailsYearRecon);
			
			String GeneralDetailsMonthRecon= browserUtil.gettextFromElement(ORlocator.getProperty("GeneralDetailsMonthRecon"));
			System.out.println("GeneralDetailsMonthRecon:"+GeneralDetailsMonthRecon);
			
			

			if (GeneralDetailsGSTINOutward.equalsIgnoreCase(GeneralDetailsGSTINRecon) && GeneralDetailsReturnYearOutward.equalsIgnoreCase(GeneralDetailsYearRecon) && GeneralDetailsReturnMonthOutward.equalsIgnoreCase(GeneralDetailsMonthRecon)) 
			{
				PassMsg = "<b>Expected Year,Month & GSTIN Record:</b>"+GeneralDetailsGSTINRecon+","+GeneralDetailsYearRecon+","+GeneralDetailsMonthRecon+"-is created automatically in Registered Invoices for Recon asset";
				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>Expected Year,Month & GSTIN Record:</b>"+GeneralDetailsGSTINRecon+","+GeneralDetailsYearRecon+","+GeneralDetailsMonthRecon+"-is not created in Registered Invoices for Recon asset";
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
