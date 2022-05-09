package com.taxilla.apps.testbase;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;

import com.taxilla.apps.commonutil.BrowserUtils;
import com.taxilla.apps.commonutil.DateUtils;
import com.taxilla.apps.commonutil.ExcelReader;
import com.taxilla.apps.commonutil.FileUtils;
import com.taxilla.apps.taxillautility.TaxillaUtility;

public class TestBase
{
	public static WebDriver driver;
	public static BrowserUtils browserUtil;
	public static Properties ORlocator;
	public static Properties environmentLocators;
	public static Properties loginLocators;
	public FileUtils fileUtils;
	public static ExcelReader reader;
	public DateUtils dateUtils;
	public static TaxillaUtility taxillaUtil; 

	@BeforeTest
	public void init() {
		try{

			fileUtils = new FileUtils();
			environmentLocators = fileUtils.loadProperty("environment");
			loginLocators = fileUtils.loadProperty("login");
			ORlocator = new FileUtils().loadProperty("taxilla");
			reader=new ExcelReader();
			dateUtils=new DateUtils();
			browserUtil = new BrowserUtils();
			taxillaUtil= new TaxillaUtility();
		}
		catch(Exception exception){
			exception.printStackTrace();
			browserUtil.closeBrowser();
		}
	}
	public static void LoginToTaxilla() throws Exception
	{
		driver = browserUtil.initalizeDriver(environmentLocators.getProperty("browser"));
		browserUtil.openUrl(environmentLocators.getProperty("taxillaURL"));
		browserUtil.waitForElementToBeInteractableByFluent(loginLocators.getProperty("SignInButton"), 500); 
		browserUtil.hardPause(1);
		browserUtil.setText(loginLocators.getProperty("UserName"),reader.getTestDataFromXLSXSheet("loginData","Login",1,1));
		browserUtil.setText(loginLocators.getProperty("Password"),reader.getTestDataFromXLSXSheet("loginData","Login",1,2));
		browserUtil.clickOnButton(loginLocators.getProperty("SignInButton"));
		browserUtil.waitForElementToBeInteractableByFluent(loginLocators.getProperty("SecurityQuestion1"), 900);
		taxillaUtil.waitForInvisibleOfSpinner();
		browserUtil.setText(loginLocators.getProperty("SecurityQuestion1"),reader.getTestDataFromXLSXSheet("loginData", "SecuredAuth", 1, 0));
		browserUtil.setText(loginLocators.getProperty("SecurityQuestion2"),reader.getTestDataFromXLSXSheet("loginData", "SecuredAuth", 1, 1));
		browserUtil.explicitlyWaitForVisibilityOfElement(loginLocators.getProperty("SubmitButton_SecuredAuthentication"), 900);
		taxillaUtil.waitForInvisibleOfSpinner();
		browserUtil.hardPause(2);
		browserUtil.clickOnButton(loginLocators.getProperty("SubmitButton_SecuredAuthentication"));


	}
}