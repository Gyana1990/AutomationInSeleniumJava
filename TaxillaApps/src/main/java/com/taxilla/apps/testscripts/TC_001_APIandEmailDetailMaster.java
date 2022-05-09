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

public class TC_001_APIandEmailDetailMaster extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		//LoginToTaxilla();
	}

	@Test()
	public void apiAndEmailDetailMaster() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify API and Email Detail Master";
		String TestCaseNum = "TC_001";
		String TestCaseTitle = "Verify whether user is able to add record in API and Email Detail Master";
		String TestStatus = "Pass";
		String StartDateTime = DateUtils.CurrentDateTime();
		String EndDateTime = DateUtils.CurrentDateTime();
		String FailMsg, PassMsg;
		String StatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
		int SlNo=(reader.getResultRecordsCount(StatusReportFileName))+1;
		int conditionPassCount=0;
		//String InputFilePath=reader.getTestDataFromXLSXSheet("testData", "InputFilePath", 2, 0);

		try{
			//taxillaUtil.selectingLocation();
			/*
			 * Master Data Management
			 */
			
			browserUtil.clickOnButton(ORlocator.getProperty("enReportDropDown"));
			browserUtil.hardPause(2);
			browserUtil.clickOnButton(ORlocator.getProperty("enComplyIcon"));
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			
			//browserUtil.waitForCondition(ORlocator.getProperty("MyApps_ManageApp"), 700);
			browserUtil.waitForCondition(ORlocator.getProperty("DisplayOfAssetName"), 700);
			//taxillaUtil.waitForInvisibleOfSpinner();
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("MasterDatamanagemnetTab"), 500);
			
//			browserUtil.hardPause(10);
			browserUtil.clickOnButton(ORlocator.getProperty("MasterDatamanagemnetTab"));
			/*
			 * API and Email Details Master	
			 */
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			//taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(2);
			//browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("APIAndEmailDetailMaster"), 500);
			
			browserUtil.clickOnButton(ORlocator.getProperty("APIAndEmailDetailMaster"));
			browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			browserUtil.waitForCondition(ORlocator.getProperty("DataPresentInMaster"), 700);
//			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(3);
			String dataPresentInGrid= browserUtil.gettextFromElement(ORlocator.getProperty("DataPresentInMaster"));
			
			if (dataPresentInGrid.contains(reader.getTestDataFromXLSXSheet("testData", "FieldData", 1, 0))) {

				browserUtil.waitForCondition(ORlocator.getProperty("DeleteButtonInMaster"), 700);
				taxillaUtil.waitForInvisibleOfSpinner();
				/*
				 * Deleting Record in master
				 */
				//browserUtil.hardPause(3);
				browserUtil.clickOnButton(ORlocator.getProperty("DeleteButtonInMaster"));
				browserUtil.waitForCondition(ORlocator.getProperty("OKButtoninDeleteConfirmation"), 700);
				taxillaUtil.waitForInvisibleOfSpinner();
				browserUtil.clickOnButton(ORlocator.getProperty("OKButtoninDeleteConfirmation"));
				/*
				 * Adding the master record
				 */
				browserUtil.waitForCondition(ORlocator.getProperty("NoRecordText"), 700);
				browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("DeletedMasterRecordText"));
				//taxillaUtil.waitForInvisibleOfSpinner();
				//browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("NoRecordText"), 500);
				browserUtil.hardPause(1);
				browserUtil.clickOnButton(ORlocator.getProperty("AddIconInMaster"));
				browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("OrgOrSubOrgGSTINField"), 500);
				browserUtil.setText(ORlocator.getProperty("OrgOrSubOrgGSTINField"), reader.getTestDataFromXLSXSheet("testData", "FieldData", 1, 0));
				browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("GSTINField"), 500);
				browserUtil.setText(ORlocator.getProperty("GSTINField"),reader.getTestDataFromXLSXSheet("testData", "FieldData", 1, 1));
				/*
				 * Click on save button
				 */
				//browserUtil.hardPause(3);
				browserUtil.MultipleTab();
				//browserUtil.hardPause(3);
				browserUtil.explicitlyWaitForVisibilityOfElementLocated(ORlocator.getProperty("SaveButton"), 500);
				browserUtil.clickOnButton(ORlocator.getProperty("SaveButton"));

			} else {
				browserUtil.waitForCondition(ORlocator.getProperty("AddIconInMaster"), 700);
				taxillaUtil.waitForInvisibleOfSpinner();
				//browserUtil.hardPause(3);
				browserUtil.clickOnButton(ORlocator.getProperty("AddIconInMaster"));
				browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("OrgOrSubOrgGSTINField"), 500);
				browserUtil.setText(ORlocator.getProperty("OrgOrSubOrgGSTINField"), reader.getTestDataFromXLSXSheet("testData", "FieldData", 1, 0));
				browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("GSTINField"), 500);
				browserUtil.setText(ORlocator.getProperty("GSTINField"),reader.getTestDataFromXLSXSheet("testData", "FieldData", 1, 1));
				/*
				 * Click on save button
				 */
				browserUtil.MultipleTab();
				//browserUtil.hardPause(3);
				browserUtil.explicitlyWaitForVisibilityOfElementLocated(ORlocator.getProperty("SaveButton"), 500);
				browserUtil.clickOnButton(ORlocator.getProperty("SaveButton"));


			}
			taxillaUtil.waitForInvisibleOfSpinner();
			browserUtil.hardPause(4);
			/*browserUtil.waitForCondition(ORlocator.getProperty("DisplayOfAssetName"), 500); 
		    taxillaUtil.waitForInvisibleOfSpinner();
		   browserUtil.clickOnButton(ORlocator.getProperty("SelectingAppsDropdownIcon"));
			browserUtil.setText(ORlocator.getProperty("SearchApp"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 0));
			browserUtil.explicitlyWaitForVisibilityOfElement(ORlocator.getProperty("SelectingAssetNameFromList"), 500);
			browserUtil.clickOnIcon(ORlocator.getProperty("SelectingAssetNameFromList"));

			//Click on level 1 Outward Transaction V3 asset
			browserUtil.clickOnIcon(ORlocator.getProperty("Level1AssetName"));
			browserUtil.waitForCondition(ORlocator.getProperty("AppProcessButton"), 600); 
		    taxillaUtil.waitForInvisibleOfSpinner();
		    browserUtil.explicitlyWaitforClickableElement(ORlocator.getProperty("AppProcessButton"), 500);

		    browserUtil.clickOnButton(ORlocator.getProperty("AppProcessButton"));*/

			String ActualDataPresentInGridAfterRecordAdded = browserUtil.gettextFromElement(ORlocator.getProperty("DataPresentInMaster"));	
			String ExpectedValueAfterRecordAdded=reader.getTestDataFromXLSXSheet("testData", "FieldData", 1, 0);

			if (ExpectedValueAfterRecordAdded.contains(ExpectedValueAfterRecordAdded)) 
			{
				PassMsg = "<b>Expected Record:</b>"+ExpectedValueAfterRecordAdded+"-GSTIN record is added in API and Email Detail Master";
				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>Expected Record:</b>"+ExpectedValueAfterRecordAdded+"-GSTIN record is not added in API and Email Detail Master";
				TestStatus = "Fail";
				EndDateTime = DateUtils.CurrentDateTime();
				SnapshotName = TestCaseNum+"_"+TestStatus+"_"+DateUtils.CurrentDateTimeMM_dd_YYYY_HH_mm_ss();
				String ScreenshotLink=browserUtil.GetSnapShot(SnapshotName);
				//SnapshotLink = ScreenshotLink+SnapshotName+".png";
				//SnapshotLink=screenshot+".png";
				//System.out.println("SnapshotLink::::"+SnapshotLink);
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, FailMsg, ScreenshotLink, StartDateTime, EndDateTime);		
			}

		}

		catch (Exception e) {
			TestStatus = "Fail";
			EndDateTime = DateUtils.CurrentDateTime();
			FailMsg = "<b>Exception Occured:</b> Exception Message - " + e.getMessage();
			SnapshotName = TestCaseNum+"_"+TestStatus+"_"+DateUtils.CurrentDateTimeMM_dd_YYYY_HH_mm_ss();
			String ScreenshotLink=browserUtil.GetSnapShot(SnapshotName);
			//System.out.println("screenshot::::"+ScreenshotLink);
			//SnapshotLink = ScreenshotLink+SnapshotName+".png";
			//SnapshotLink=screenshot+".png";
			//System.out.println("SnapshotLink::::"+SnapshotLink);
			reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, FailMsg, ScreenshotLink, StartDateTime, EndDateTime);
			//browserUtil.closeBrowser();
			throw e;
		}
	}
}
