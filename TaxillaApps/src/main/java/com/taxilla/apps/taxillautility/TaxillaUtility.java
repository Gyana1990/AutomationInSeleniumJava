package com.taxilla.apps.taxillautility;

import java.io.File;
import java.io.FileFilter;
import java.net.Inet4Address;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.taxilla.apps.commonutil.BrowserUtils;
import com.taxilla.apps.commonutil.FileUtils;
import com.taxilla.apps.testbase.TestBase;

public class TaxillaUtility extends TestBase 
{
	boolean spinnerIcon=true;
	public void selectingLocation() throws Exception {

		try {
			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("MyApps_ManageApp"), 900);
			browserUtil.hardPause(5);
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("UdyogOrgDropDownIcon"), 500, true);
			browserUtil.clickOnButton(ORlocator.getProperty("UdyogOrgDropDownIcon"));
			/*
			 * Selecting Org
			 */

			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SearchOrg"), 500, true);
			browserUtil.setText(ORlocator.getProperty("SearchOrg"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 4));
			browserUtil.hardPause(1);
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("OrgNameInSearchOrganization")+ORlocator.getProperty("OrgNameSeach"), 500, true);
			browserUtil.clickOnButton(ORlocator.getProperty("OrgNameInSearchOrganization")+ORlocator.getProperty("OrgNameSeach"));


			/*
			 * Search location
			 */
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("LocationSearchField"), 500, true);
			browserUtil.setText(ORlocator.getProperty("LocationSearchField"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 5));
			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SelectingLocation")+ORlocator.getProperty("SelectingLocation")+ORlocator.getProperty("Selectingocation1"), 500, true);
			browserUtil.clickOnButton(ORlocator.getProperty("SelectingLocation")+ORlocator.getProperty("Selectingocation1"));


		} catch (Exception e) {
			throw e;
		}
	}


//	public void selectApp() throws Exception {
//		try {
//			browserUtil.hardPause(14);
//			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("DisplayOfAssetName"), 400, true);
//			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SelectingAppsDropdownIcon"), 400, true);
//			browserUtil.clickOn(ORlocator.getProperty("SelectingAppsDropdownIcon"));
//			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SearchApp"), 400, true);
//			browserUtil.setText(ORlocator.getProperty("SearchApp"), reader.getTestDataFromXLSXSheet("testData", "AssetDetails", 1, 0));
//			browserUtil.waitForElementToBeInteractableByFluent(ORlocator.getProperty("SelectingAssetNameFromList"), 500); 
//			//browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("SelectingAssetNameFromList"), 400, true);
//			browserUtil.clickOn(ORlocator.getProperty("SelectingAssetNameFromList"));
//			/*
//			 * Select level 1 Outward Transactions-GSTR-1 Return-Sales asset
//			 */
//			browserUtil.waitForElementToVisibleOrInvisible(ORlocator.getProperty("Level1AssetName"), 400, true);
//			browserUtil.clickOn(ORlocator.getProperty("Level1AssetName"));
//		} catch (Exception e) {
//			throw e;
//		}
//	}

	public void waitForInvisibleOfSpinner() 
	{
		By spinner = By.xpath("//img[@alt='Loading...']");
		try {
			browserUtil.waitFor(spinner, 500, false);
		} catch (Exception e) {
		}
	}
	
	
	/*public void waitForInvisibleOfSpinnerTesting()
	{
		
		while (spinnerIcon) {
			String attributeValue= driver.findElement(By.xpath(ORlocator.getProperty("SpinningIcon"))).getAttribute("class");	
			System.out.println("attributeValue="+ attributeValue);
			
			String attributeValue1= driver.findElement(By.xpath(ORlocator.getProperty("LoaderImage"))).getAttribute("class");	
			System.out.println("attributeValue1="+ attributeValue1);
			
			if (attributeValue.contains("ng-star-inserted")) 
			{ 
				spinnerIcon=false;
			}
			else if (attributeValue1.contains("loaderImageContainer ng-star-inserted")) {
				spinnerIcon=false;
			}
		}
	}*/

	public static String openTheLatestFileInFolder(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		String LatestFile = null;
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
			LatestFile=theNewestFile.getAbsolutePath();
			//System.out.println("New file-"+LatestFile);
		}

		return LatestFile;
	}

}

