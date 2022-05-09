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

public class TC_004_PostGSTR1V1_2Level2 extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		//LoginToTaxilla();
	}
	@Test()
	public void rquestCompletionInPostGSTR1() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify the request completion in Post GSTR-1 asset";
		String TestCaseNum = "TC_004";
		String TestCaseTitle = "Verify whether request is completed or not in Post GSTR-1 asset & User is able to download GSTR-1 Html Report";
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
			browserUtil.hardPause(1);
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("SelectingAppsDropdownIcon"), 500);
			browserUtil.clickOnButton(ORlocator.getProperty("SelectingAppsDropdownIcon"));
//			browserUtil.setText(ORlocator.getProperty("SearchApp"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 0));
//			browserUtil.explicitlyWaitForVisibilityOfElement(ORlocator.getProperty("SelectingAssetNameFromList"), 500);
//			browserUtil.clickOnButton(ORlocator.getProperty("SelectingAssetNameFromList"));

			/*
			 * Click on level 2 Post GSTR-1 V1.2 asset
			 */
			//browserUtil.hardPause(2);
			browserUtil.explicitlyWaitForVisibilityOfElementLocated(ORlocator.getProperty("Level2PostGSTR1V1_2"),500);
			//browserUtil.clickOnButton(ORlocator.getProperty("Level2PostGSTR1V1_2"));
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("Level2PostGSTR1V1_2"));
			//browserUtil.clickOnButton(ORlocator.getProperty("ThreeDotLineInPostGSTR1"));
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("AllProcessesInThreeDotLine"), 500);
			//browserUtil.hardPause(2);
			//browserUtil.clickOnButton(ORlocator.getProperty("AllProcessesInThreeDotLine"));
			//browserUtil.waitForPageLoading(50);
			//browserUtil.waitForCondition(ORlocator.getProperty("ViewIconInAllProcessPage"), 600); 
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("ViewIconInAllProcessPage"), 500);
	
			//browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("SpinningIcon"), 500);
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			browserUtil.hardPause(3);
			//browserUtil.explicitlyWaitForVisibilityOfElementLocated(ORlocator.getProperty("ViewIconInAllProcessPage"),500);
			
			//browserUtil.waitForElementPresentInDOM(ORlocator.getProperty("ViewIconInAllProcessPage"));
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("ViewIconInAllProcessPage"), 600);
			browserUtil.clickOnButton(ORlocator.getProperty("ViewIconInAllProcessPage"));
			
			//browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("ProcessErrorIcon"), 500);
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("CloaseIconInErrorWindow"), 500);
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("EditOrUpdateDataWorkFlowName"), 500);
			//browserUtil.hardPause(10);
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("CloaseIconInErrorWindow"));
			browserUtil.clickOnButton(ORlocator.getProperty("PostGetDataToFromGSTIN_Entity"));
			
			browserUtil.waitForCondition(ORlocator.getProperty("EditIconInPostGetDataToFromGSTIN_Entity"), 600); 
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(3);
			browserUtil.clickOnButton(ORlocator.getProperty("EditIconInPostGetDataToFromGSTIN_Entity"));
			browserUtil.clickOnButton(ORlocator.getProperty("WhatWouldYouLikeTodo"));
			browserUtil.hardPause(1);
			browserUtil.clickOnButton(ORlocator.getProperty("CompleteGSTR1ProcessOption"));
			browserUtil.hardPause(1);
			browserUtil.clickOnButton(ORlocator.getProperty("UpdateButtonInPostGetDataToFromGSTIN_Entity"));
			
			//browserUtil.waitForCondition(ORlocator.getProperty("ActionsIcon"), 600); 
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("PostUpdateMessage"), 500);
			taxillaUtil.waitForInvisibleOfSpinner();
			
			//browserUtil.explicitlyWaitForVisibilityOfElement(ORlocator.getProperty("ActionsIcon"), 500);
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("ActionsIcon"), 50000);
			browserUtil.hardPause(2);
			//browserUtil.waitForElementToBeInteractableTest(ORlocator.getProperty("ActionsIcon"),600);
			browserUtil.clickOnButton(ORlocator.getProperty("ActionsIcon"));
			//browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("UpdateOREditData"), 500);
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("UpdateOREditData"));
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("WorkFlowName_CustomerMail"), 500);
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(3);
			browserUtil.clickOnButton(ORlocator.getProperty("CloaseIconInErrorWindow"));
			/*
			 * Report Download
			 */
			
			browserUtil.ClickOnElementByEnterKey(ORlocator.getProperty("ReportIcon"));
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("LableNameChain4"),600);
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("LabelNameChain1"));
			browserUtil.scrollDownForEndKey();
			browserUtil.hardPause(2);
			
			String ActualLinkName=browserUtil.getvalueFromElement(ORlocator.getProperty("GSTR1ReportLink"),"href");
			System.out.println("ActualLinkName:"+ ActualLinkName);
			browserUtil.clickOnButton(ORlocator.getProperty("GSTR1HtmlReport"));
			browserUtil.hardPause(7);
		
			String ExpectedReportLink1="Report.html";
			String ExpectedReportLink2="GSTR-1";
			
			if (ActualLinkName.contains(ExpectedReportLink2) && ActualLinkName.contains(ExpectedReportLink1)) 
			{
				PassMsg = "<b>Expected Report Download Link for:</b>"+ExpectedReportLink2+" "+ExpectedReportLink1+"-is displayed in Post GSTR-1 asset & process is completed in Post GSTR-1";
				System.out.println("PassMsg"+ PassMsg);
				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>Expected Report Download Link for:</b>"+ExpectedReportLink2+" "+ExpectedReportLink1+"-is not displayed in Post GSTR-1 asset & process is not completed in Post GSTR-1";
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
