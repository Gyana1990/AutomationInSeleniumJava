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

public class TC_002_OutwardTransactionV3AssetInLevel1 extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		//LoginToTaxilla();
	}

	@Test()
	public void uploadInputFileInOutwardTransactionV3() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify File Upload in Outward Transaction V3 asset";
		String TestCaseNum = "TC_002";
		String TestCaseTitle = "Verify whether user is able to upload input file by selecting transformation in Outward Transaction V3 asset";
		String TestStatus = "Pass";
		String StartDateTime = DateUtils.CurrentDateTime();
		String EndDateTime = DateUtils.CurrentDateTime();
		String FailMsg, PassMsg;
		String StatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
		int SlNo=(reader.getResultRecordsCount(StatusReportFileName))+1;
		int conditionPassCount=0;
		String InputFilePath=reader.getTestDataFromXLSXSheet("testData", "InputFilePath", 1, 0);

		try{
			//taxillaUtil.selectingLocation();
           // taxillaUtil.selectApp();
			
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			browserUtil.clickOnButton(ORlocator.getProperty("SelectingAppsDropdownIcon"));
			browserUtil.hardPause(2);
			//browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("SelectingAssetNameFromList"), 500); 
			browserUtil.clickOnButton(ORlocator.getProperty("SelectingAssetNameFromList"));
			/*
			 * Select level 1 Outward Transactions-GSTR-1 Return-Sales asset
			 */
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("Level1AssetName"), 400);
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("Level1AssetName"));
//			browserUtil.waitForCondition(ORlocator.getProperty("SelectingAppsDropdownIcon"), 500); 
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
//			browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("AppProcessButton"), 500);
//			browserUtil.hardPause(1);
//			browserUtil.clickOnButton(ORlocator.getProperty("AppProcessButton"));
			browserUtil.waitForCondition(ORlocator.getProperty("ProcessNowInInitiateNew"), 600); 
// 		    taxillaUtil.waitForInvisibleOfSpinner();
// 		    browserUtil.hardPause(20);
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			browserUtil.hardPause(2);
 		    browserUtil.clickOnButton(ORlocator.getProperty("ProcessNowInInitiateNew"));
			browserUtil.waitForCondition(ORlocator.getProperty("SelectTransformationIcon"), 600);
			taxillaUtil.waitForInvisibleOfSpinner();
			
			
			/*
			 * Create New Process
			 */
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("CreateNewButton"), 500);
			//browserUtil.hardPause(3);
			//browserUtil.clickOnButton(ORlocator.getProperty("CreateNewButton"));
			//browserUtil.waitForCondition(ORlocator.getProperty("SelectTransformationIcon"), 600); 
			//taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(3);
			browserUtil.clickOnButton(ORlocator.getProperty("SelectTransformationIcon"));
			browserUtil.clickOnButton(ORlocator.getProperty("SearchTransformation"));
			browserUtil.setText(ORlocator.getProperty("SearchTransformation"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 1));
			browserUtil.clickOnButton(ORlocator.getProperty("SelectTransformationFromList"));
			/*
			 * Choose file
			 */ 
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("ChooseFile"), 300);
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("ChooseFile"));
			browserUtil.hardPause(2);
			browserUtil.uploadFile(InputFilePath);
			browserUtil.hardPause(2);
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("InitiateProcessButton"), 300);
			browserUtil.clickOnButton(ORlocator.getProperty("InitiateProcessButton"));
			/*
			 * Notification Window
			 */
			browserUtil.waitForCondition(ORlocator.getProperty("NotificationWindow"), 600); 
			taxillaUtil.waitForInvisibleOfSpinner();

			//String ActualText=browserUtil.gettextFromElement(ORlocator.getProperty("NotificationWindowAllData"));
			//System.out.println("Actual Text-"+ActualText );

			//String ExpectedText ="Inbound Transmission Done For Now";   

			//String ActualText2=browserUtil.gettextFromElement(ORlocator.getProperty("EventTypeColumnData"));
			//System.out.println("Actual text 2:"+ ActualText2);

			//int LinksPagination=browserUtil.getNoOfLinkInPageByXpathAndTag(ORlocator.getProperty("TotalLinkInNotification"), "td");
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("RefreshIconInNotificationWindow"));
			browserUtil.waitForCondition(ORlocator.getProperty("ViewProcessEyeIcon"), 600); 
			taxillaUtil.waitForInvisibleOfSpinner();

			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("ViewProcessEyeIcon"));
			browserUtil.waitForCondition(ORlocator.getProperty("ViewProcessEyeIconInPartition"), 600); 
			browserUtil.hardPause(3);
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.clickOnButton(ORlocator.getProperty("ViewProcessEyeIconInPartition"));
			
			browserUtil.waitForCondition(ORlocator.getProperty("FinalWorkflowStageName"), 600); 
			taxillaUtil.waitForInvisibleOfSpinner();
			//browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("FinalWorkflowStageName"), 500);
			browserUtil.hardPause(5);
			//browserUtil.explicitlyWaitForVisibilityOfElement(ORlocator.getProperty("FinalWorkflowStageName"), 600);
			//browserUtil.clickOnIcon(ORlocator.getProperty("CloaseIconInErrorWindow"));
			String ActualStatusWorkFlowName=browserUtil.gettextFromElement(ORlocator.getProperty("FinalWorkflowStageName"));
			System.out.println("StatusWorkFlowName:"+ ActualStatusWorkFlowName);

            String ExpectedStatusWorkFlowName= " Reports";

			if (ExpectedStatusWorkFlowName.equalsIgnoreCase(ActualStatusWorkFlowName)) 
			{
				PassMsg ="Input File uploaded successfully in Outward Transaction V3 asset and"+ "<b>Expected Workflow:</b>"+ActualStatusWorkFlowName+"-is displayed in outward Transaction V3 asset";
				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "Process is not completed in outward Transaction v3 asset and"+ "<b>Expected Workflow:</b>"+ActualStatusWorkFlowName+"-is not displayed in outward Transaction V3 asset";
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
