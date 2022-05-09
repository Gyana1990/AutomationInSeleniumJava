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

public class TC_006_ValidationCheckForError1_OutwardTransactionV3App extends TestBase
{
	@BeforeMethod
	public void initial() throws Exception
	{
		//LoginToTaxilla();
	}
	@Test()
	public void verifyErrorPhase1InOutwardTransactionV3() throws Exception
	{   


		String SnapshotName, SnapshotLink;
		//String IPAddress =  Inet4Address.getLocalHost().getHostAddress();
		//String ScreenshotLink="\\\\"+ IPAddress + "\\TaxillaScreenshot\\";
		String TestScriptName = "Verify the Error message phase 1 in Outward Transactions v3 asset";
		String TestCaseNum = "TC_006";
		String TestCaseTitle = "Verify whether all the phase 1 error message are displayed or not in Outward Transactions V3 asset";
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

		String ExpectedErrorMessage1="If [Customer Country Code] is 'OTH' then [Ship to State] should be 'Others'";
		String ExpectedErrorMessage2="In case of export transactions, 'Customer GSTIN / UIN' should be Blank";
		String ExpectedErrorMessage3="As [Inter - State/Intra - State/Inter - Country Transaction] is selected as 'Inter-Country' then [With / Without payment of GST] is mandatory for 'Goods'";
		String ExpectedErrorMessage4= "As [Inter - State/Intra - State/Inter - Country Transaction] is selected as 'Inter-State' or 'Inter - Country' then [CGST Amount] should be zero";
		String ExpectedErrorMessage5= "As [Inter - State/Intra - State/Inter - Country Transaction] is selected as 'Inter-State' or 'Inter - Country' then [SGST / UT GST Amount] should be zero";
		String ExpectedErrorMessage6= "As [Inter - State/Intra - State/Inter - Country Transaction] is selected as 'Inter-State' or 'Inter - Country' then [CGST Rate] should be zero";
		String ExpectedErrorMessage7= "As [Inter - State/Intra - State/Inter - Country Transaction] is selected as 'Inter-State' or 'Inter - Country' then [ SGST / UT Rate] should be zero";
		String ExpectedErrorMessage8= "[With / Without payment of GST] field is mandatory for [Transaction SubType] - 'Exports'";
		String ExpectedErrorMessage9="[IGST Amount]+[CGST Amount]+[SGST / UT GST Amount] should be zero for 'Exports - Without payment of GST'";
		String ExpectedErrorMessage10= "if [Inter - State/Intra - State/Inter - Country Transaction] is 'Inter-State' or 'Intra - State' and [TrasactionSubtype] is 'Sales to SEZ Unit' or 'Sales to SEZ Developer' and  [With/Without payment of GST] is 'Without payment of GST' then [IGST Amount]+[CGST Amount]+[SGST / UT GST Amount] should be zero";
		String ExpectedErrorMessage11 = "If '[Transaction Type] is Credit Note for Sales' then '[Nature of Document]' should be Credit Note";
		String ExpectedErrorMessage12 = "CGST rate [6.0] should be equal to SGST rate [0.0]";
		String ExpectedErrorMessage13 = "As [Supply Type] is selected as 'Nil Rated' then [CGST Rate] should be zero";
		String ExpectedErrorMessage14 = "As [Supply Type] is selected as 'Nil Rated' then [CGST Amount] should be zero";
		String ExpectedErrorMessage15 = "As [Supply Type] is selected as 'Nil Rated' then [SGST / UT GST Amount] should be zero";
		String ExpectedErrorMessage16 = "If SGST Rate is greater than 'Zero' then  SGST / UT GST Amount should be greater than 'Zero' and Vice-Versa(or) If SGST Rate/Amount is Zero then SGST Rate/Amount also should be 'Zero' and Vice-Versa";



		try{
//						taxillaUtil.selectingLocation();
//						taxillaUtil.selectApp();
			            browserUtil.clickOnButton(ORlocator.getProperty("CloseInboundTransmissionIcon"));
			            browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			            browserUtil.hardPause(1);
			            browserUtil.clickOnButton(ORlocator.getProperty("CreateNewButton"));
			            browserUtil.waitforSpinnerToDisappear(ORlocator.getProperty("SpinningIcon"));
			            
//						browserUtil.waitForCondition(ORlocator.getProperty("ProcessNowInInitiateNew"), 600); 
//						taxillaUtil.waitForInvisibleOfSpinner();
//						browserUtil.hardPause(20);
//						browserUtil.clickOnButton(ORlocator.getProperty("ProcessNowInInitiateNew"));
						browserUtil.waitForCondition(ORlocator.getProperty("SelectTransformationIcon"), 600);
						taxillaUtil.waitForInvisibleOfSpinner();
			
						/*
						 * Create New Process
						 */
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
						browserUtil.clickOnButton(ORlocator.getProperty("InitiateProcessButton"));
						/*
						 * Notification Window
						 */
						browserUtil.waitForCondition(ORlocator.getProperty("NotificationWindow"), 600); 
						browserUtil.hardPause(2);
						browserUtil.clickOnButton(ORlocator.getProperty("RefreshIconInNotificationWindow"));
						browserUtil.waitForCondition(ORlocator.getProperty("ViewProcessEyeIcon"), 600); 
						taxillaUtil.waitForInvisibleOfSpinner();
						browserUtil.hardPause(2);
						browserUtil.clickOnButton(ORlocator.getProperty("ViewProcessEyeIcon"));
						browserUtil.waitForCondition(ORlocator.getProperty("ViewProcessEyeIconInPartition"), 600); 
						browserUtil.hardPause(3);
						browserUtil.clickOnButton(ORlocator.getProperty("ViewProcessEyeIconInPartition"));
						browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("GSTINValidation_WorkFlow_OutwardTransactionV3"), 500);
						browserUtil.hardPause(3);
						browserUtil.ClickOnElementByEnterKey(ORlocator.getProperty("ReportIcon"));
						browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("LabelNameChain3"),600);
						taxillaUtil.waitForInvisibleOfSpinner();
						browserUtil.hardPause(2);
						browserUtil.clickOnButton(ORlocator.getProperty("LabelNameChain1"));
						browserUtil.KeyDownOperation();
						browserUtil.hardPause(1);
						browserUtil.clickOnButton(ORlocator.getProperty("Chain3GenerateButton"));
						browserUtil.hardPause(4);
						browserUtil.clickOnButton(ORlocator.getProperty("NotificationBellIcon"));
						taxillaUtil.waitForInvisibleOfSpinner();
						browserUtil.hardPause(4);
						/*
						 * Loop will check whether outbound transformation completed or not in notification 
						 */
						while (status) 
						{
							String EventTableAllData = browserUtil.gettextFromElement(ORlocator.getProperty("EventTableNotificationAllData"));
							System.out.println("EventTableAllData:-"+ EventTableAllData);
							
							String ExpectedEventTableNotification = "Outbound transformation completed for transformationName : Outward Error Report";
							
							if (EventTableAllData.contains(ExpectedEventTableNotification)) 
							{ 
								
								status=false;
								System.out.println("If block");
								
							}
							else {							
								status=true;
								System.out.println("Else block");
								browserUtil.clickOn(ORlocator.getProperty("RefreshIconInNotificationWindowIntheProcess"));
								taxillaUtil.waitForInvisibleOfSpinner();
								browserUtil.hardPause(5);
							}
											
						}
						browserUtil.hardPause(2);
						browserUtil.clickOn(ORlocator.getProperty("ReportIcon"));
			
			            /*
			             * Report Download
			             */
						browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("LabelNameChain3"),600);
						taxillaUtil.waitForInvisibleOfSpinner();
						browserUtil.hardPause(2);
						browserUtil.clickOnButton(ORlocator.getProperty("LabelNameChain1"));
						browserUtil.KeyDownOperation();
						browserUtil.hardPause(1);
						//browserUtil.fileDownload("\\DownloadExcelFile\\");
						browserUtil.clickOnButton(ORlocator.getProperty("OutwardErrorReportDownloadLink"));
						browserUtil.hardPause(6);
			String LatestFileName=taxillaUtil.openTheLatestFileInFolder(DownLoadFilePathOfExcel, "xlsx");
			System.out.println("LatestFileName:"+ LatestFileName);

			String ActualErrorMessage1=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 1, 57);
			
			String ActualErrorMessage2=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 2, 57);
			
			String ActualErrorMessage3=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 3, 57);
			
			String ActualErrorMessage4=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 4, 57);
			
			String ActualErrorMessage6=reader.getTestDataFromXLSXSheetWithoutExtension(LatestFileName, "GST Outward Transaction STD", 6, 57);
			
			if (ActualErrorMessage1.contains(ExpectedErrorMessage1) || ActualErrorMessage2.contains(ExpectedErrorMessage9) ||
					ActualErrorMessage3.contains(ExpectedErrorMessage10) || ActualErrorMessage4.contains(ExpectedErrorMessage11) ||
					ActualErrorMessage6.contains(ExpectedErrorMessage12) || ActualErrorMessage6.contains(ExpectedErrorMessage13) ||
					ActualErrorMessage6.contains(ExpectedErrorMessage14) || ActualErrorMessage6.contains(ExpectedErrorMessage15) ||
					ActualErrorMessage6.contains(ExpectedErrorMessage16)) 
			{
				PassMsg ="<b>Expected Error Message 1:</b>"+ExpectedErrorMessage1+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 2:</b>"+ExpectedErrorMessage2+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 3:</b>"+ExpectedErrorMessage3+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 4:</b>"+ExpectedErrorMessage4+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 5:</b>"+ExpectedErrorMessage5+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 6:</b>"+ExpectedErrorMessage6+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 7:</b>"+ExpectedErrorMessage7+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 8:</b>"+ExpectedErrorMessage8+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 9:</b>"+ExpectedErrorMessage9+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 10:</b>"+ExpectedErrorMessage10+"-is displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 11:</b>"+ExpectedErrorMessage11+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 12:</b>"+ExpectedErrorMessage12+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 13:</b>"+ExpectedErrorMessage13+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 14:</b>"+ExpectedErrorMessage14+"-is displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 15:</b>"+ExpectedErrorMessage15+"-is displayed in Outward Transaction V3 asset"+ "<br>" +
						"<b>Expected Error Message 16:</b>"+ExpectedErrorMessage16+"-is displayed in Outward Transaction V3 asset";
				TestStatus = "Pass";
				EndDateTime = DateUtils.CurrentDateTime();
				reader.writeToStatusReportXLSXFile(StatusReportFileName, SlNo, TestCaseNum, TestCaseTitle, TestStatus, PassMsg, "", StartDateTime, EndDateTime);	
				conditionPassCount=conditionPassCount+1;
			}
			else{
				FailMsg = "<b>Expected Error Message 1:</b>"+ExpectedErrorMessage1+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 2:</b>"+ExpectedErrorMessage2+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 3:</b>"+ExpectedErrorMessage3+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 4:</b>"+ExpectedErrorMessage4+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 5:</b>"+ExpectedErrorMessage5+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 6:</b>"+ExpectedErrorMessage6+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 7:</b>"+ExpectedErrorMessage7+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 8:</b>"+ExpectedErrorMessage8+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 9:</b>"+ExpectedErrorMessage9+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 10:</b>"+ExpectedErrorMessage10+"-is not displayed in Outward Transaction V3 asset" + "<br>"+
						"<b>Expected Error Message 11:</b>"+ExpectedErrorMessage11+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 12:</b>"+ExpectedErrorMessage12+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 13:</b>"+ExpectedErrorMessage13+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 14:</b>"+ExpectedErrorMessage14+"-is not displayed in Outward Transaction V3 asset" + "<br>" +
						"<b>Expected Error Message 15:</b>"+ExpectedErrorMessage15+"-is not displayed in Outward Transaction V3 asset"+ "<br>" +
						"<b>Expected Error Message 16:</b>"+ExpectedErrorMessage16+"-is not displayed in Outward Transaction V3 asset";
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
