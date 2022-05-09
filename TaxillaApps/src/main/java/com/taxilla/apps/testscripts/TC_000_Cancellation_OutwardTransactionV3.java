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

public class TC_000_Cancellation_OutwardTransactionV3 extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		LoginToTaxilla();
	}
	@Test()
	public void cancellationOfRequestInOutwardTransactionV3() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify the request cancellation in Outward Transactions v3 asset";
		String TestCaseNum = "TC_000";
		String TestCaseTitle = "Verify whether request is cancelled or not in Outward Transactions v3 asset";
		String TestStatus = "Pass";
		String StartDateTime = DateUtils.CurrentDateTime();
		String EndDateTime = DateUtils.CurrentDateTime();
		String FailMsg, PassMsg;
		String StatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
		int SlNo=(reader.getResultRecordsCount(StatusReportFileName))+1;
		int conditionPassCount=0;
		String InputFilePath=reader.getTestDataFromXLSXSheet("testData", "InputFilePath", 1, 0);
		boolean status=true;
		try{
			taxillaUtil.selectingLocation();
			//taxillaUtil.selectApp();
			
			/*
			 * Selection of Bridge And Asset
			 */
			browserUtil.hardPause(14);
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("DisplayOfAssetName"), 400, true);
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SelectingAppsDropdownIcon"), 400, true);
			browserUtil.clickOn(ORlocator.getProperty("SelectingAppsDropdownIcon"));
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SearchApp"), 400, true);
			browserUtil.setText(ORlocator.getProperty("SearchApp"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 0));
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("SelectingAssetNameFromList"), 500); 
			//browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SelectingAssetNameFromList"), 400, true);
			browserUtil.clickOn(ORlocator.getProperty("SelectingAssetNameFromList"));
			/*
			 * Select level 1 Outward Transactions-GSTR-1 Return-Sales asset
			 */
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("Level1AssetName"), 400, true);
			browserUtil.clickOn(ORlocator.getProperty("Level1AssetName"));
			browserUtil.waitForCondition(ORlocator.getProperty("ProcessNowInInitiateNew"), 600); 
			//taxillaUtil.waitForInvisibleOfSpinner();
//			browserUtil.hardPause(60);
//			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("AllProcessesButtonInenComply"), 500,true); 	
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			browserUtil.clickOn(ORlocator.getProperty("AnayticsImage"));
			browserUtil.hardPause(2);
			browserUtil.scrollDownForEndKey();
			browserUtil.clickOn(ORlocator.getProperty("AllProcessesButtonInenComply"));
			//taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("ViewRelatedInboundTransmission"), 500); 
			//browserUtil.hardPause(5);
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));

			/*
			 * Cancellation in All Inbound Transmission Page
			 */
			browserUtil.clickOn(ORlocator.getProperty("ViewRelatedInboundTransmission"));
			//taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("AllInboundPageUserName"), 900);
			browserUtil.hardPause(2);
			String ActualAllText=browserUtil.gettextFromElement(ORlocator.getProperty("AllDataInInboundTransmission"));
			//System.out.println("Actual="+ ActualAllText);

			String ExpectedText1="Completed ";
			String ExpectedText2="In-Progress ";

			if (ActualAllText.contains(ExpectedText1) || ActualAllText.contains(ExpectedText2) ) {
				browserUtil.clickOn(ORlocator.getProperty("AllInboundCancelIcon"));
				browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("PopUpCancelInboundTransmission"), 900);
				browserUtil.clickOn(ORlocator.getProperty("SubmitButtonCancelInbound"));
				taxillaUtil.waitForInvisibleOfSpinner();
				browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("AllInboundPageUserName"), 900);
				//browserUtil.hardPause(18);
				browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
				while (status) {
					String ActualAllTextInFirstRow=browserUtil.gettextFromElement(ORlocator.getProperty("AllDataInInboundTransmissionPageFirstRow"));
					String textValue= "Completed ";	
					System.out.println("ActualAllTextInFirstRow="+ ActualAllTextInFirstRow);
					if (ActualAllTextInFirstRow.contains("Cancelled ")) 
					{ 
						status=false;
					}
					browserUtil.clickOn(ORlocator.getProperty("RefreshIconInAllInboundTransmission"));
					browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("AllInboundPageUserName"), 900);
					browserUtil.hardPause(2);
				}

				String ActualAllTextForVerificationInFirstRow=browserUtil.gettextFromElement(ORlocator.getProperty("AllDataInInboundTransmissionPageFirstRow"));
				String ExpectedTextForVerificationInFirstRow="Cancelled ";

				if (ActualAllTextForVerificationInFirstRow.contains(ExpectedTextForVerificationInFirstRow)) 
				{
					PassMsg = "<b>Request is cancelled Successfully in Outward Transaction V3 Asset</b>";
					System.out.println("PassMsg"+ PassMsg);
					TestStatus = "Pass";
					EndDateTime = DateUtils.CurrentDateTime();
					reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
					conditionPassCount=conditionPassCount+1;
				}
				else{
					FailMsg = "<b>Request is not cancelled in Outward Transaction V3 Asset</b>";
					TestStatus = "Fail";
					EndDateTime = DateUtils.CurrentDateTime();
					SnapshotName = TestCaseNum+"_"+TestStatus+"_"+DateUtils.CurrentDateTimeMM_dd_YYYY_HH_mm_ss();
					String ScreenshotLink=browserUtil.GetSnapShot(SnapshotName);
					SnapshotLink = ScreenshotLink+SnapshotName+".png";
					//SnapshotLink=screenshot+".png";
					reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, FailMsg, ScreenshotLink, StartDateTime, EndDateTime);		
				}


			} else {
				System.out.println("All processes are cancelled");
				PassMsg = "<b>All Processes are cancelled</b>";
				System.out.println("PassMsg"+ PassMsg);
				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
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
