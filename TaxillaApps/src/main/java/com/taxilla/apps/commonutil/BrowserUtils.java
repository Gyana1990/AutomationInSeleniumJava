package com.taxilla.apps.commonutil;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Driver;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.min;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.taxilla.apps.taxillautility.TaxillaUtility;

//import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
//import com.relevantcodes.extentreports.LogStatus;

public class BrowserUtils
{
	static WebDriver driver;
	Properties prop;
	Actions act;
	int RecordsCount=0;
	int retryCount = 3;
	boolean test=false;
	public String windowID=null;
	int executionCount = 0;
	String findKey="";
	By element;
	//Boolean retry = false;
	//static String screenshotFolderPath= System.getProperty("user.dir")+ "\\Screenshot\\";
	public String screenshotFolderPath= System.getProperty("user.dir")+ "\\TaxillaScreenshot\\";
	JavascriptExecutor js = null;
	//DesiredCapabilities cap;
	ChromeOptions options;
	//String downloadFilePath="E:\\WorkSpaceForSeleniumJava\\TaxillaApps\\DownloadedFiles";
	int ArraySize;
	WebDriverWait wait;
	static Wait<WebDriver> waitWeb = null;
	public  List<WebElement>Links=null; 
	TaxillaUtility taxilla=new TaxillaUtility(); 


	public WebDriver initalizeDriver(String browserName){
		switch (browserName) 
		{
		case "Firefox":
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			this.driver=new FirefoxDriver();
			break;
		case "IE":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			this.driver=new InternetExplorerDriver();
			break;
		case "Chrome":
			fileDownload();
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			this.driver=new ChromeDriver(options);
			break;
		default:
			break;
		}
		driver.manage().window().maximize();
		return driver;
	}


	public void fileDownload()
	{
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", System.getProperty("user.dir")+"\\DownloadedFiles\\");
		options = new ChromeOptions(); 
		options.setExperimentalOption("prefs", chromePrefs);

		//		  HashMap<String, Object>chromeOptionsMap = new HashMap<String, Object>();
		//		  options.setExperimentalOption("prefs", chromePrefs); cap =
		//		  DesiredCapabilities.chrome(); cap.setCapability(ChromeOptions.CAPABILITY,
		//		  chromeOptionsMap); cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		//		  cap.setCapability(ChromeOptions.CAPABILITY, options);
		//		 	 
	}

	public  void setText(String locator,String value){
		try{
			taxilla.waitForInvisibleOfSpinner();
			driver.findElement(By.xpath(locator)).sendKeys(value);
		}catch(Exception e){
			throw e;
		}
	}

	public  void submit(String key){
		try{
			driver.findElement(By.xpath(key)).submit();
		}catch(Exception e){
			throw e;
		}
	}
	public  void openUrl(String url){
		try{
			this.driver.get(url);
		}catch(Exception e){
			throw e;
		}
	}

	public  void explicitlyWait(String key,int seconds){
		try{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(key)));

		}catch(Exception e){
			throw e;
		}
	}

	public  void explicitlyWaitforClickableElement(String key,int seconds) throws Exception{
		try{
			//WebDriverWait wait=new WebDriverWait(driver,seconds);
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key)));

		}catch(Exception e){
			throw e;
		}
	}

	public void waitForElementPresentInDOM(String key) throws Exception {
		try{
			WebElement element = driver.findElement(By.xpath(key));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", element);
		}catch(Exception e){
			throw e;
		}
	}
	public  void explicitlyWaitforWebElement(String key,int seconds) throws Exception{
		try{
			//WebDriverWait wait=new WebDriverWait(driver,seconds);
			WebElement element=driver.findElement(By.xpath(key));
			WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(element));


		}catch(Exception e){
			throw e;
		}
	}

	public boolean waitFor(By locator, int seconds, Boolean visibility) 
	{
		try {
			if (visibility) {
				wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); 
			} else 
			{				
				wait.until(ExpectedConditions.invisibilityOfElementLocated(locator)); 
			} 
		}
		catch (Exception e) { 
		} 
		return true;
	}
	
	public void waitforSpinnerToDisappear(String key) throws Exception {
		try {
			boolean spinner=true;
			int count=0;
			while (spinner) {
				System.out.println("while loop");
				List<WebElement>list=driver.findElements(By.xpath(key));
				System.out.println("List-"+list);
				if (list.size()!=0) {
					Thread.sleep(1000);
					count++;
					System.out.println("If block");
				} else {
					System.out.println("Else false block");
                    spinner=false;
				}				
			}
		} catch (Exception e) {
			throw e;
		}
		
	}

	public boolean waitForElementToVisibleOrInvisible(String key, int seconds, Boolean visibility) 
	{
		try {
			if (visibility) {
				element=By.xpath(key);
				wait.until(ExpectedConditions.visibilityOfElementLocated(element)); 
				
			} else 
			{
				wait.until(ExpectedConditions.invisibilityOfElementLocated(element)); 
				
			} 
		}
		catch (Exception e) { 
		} 
		return true;
	}
	
	public  void explicitlyWaitforClickableElementByKey(String key,int seconds){
		try{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key))).sendKeys(Keys.ENTER);
		}catch(Exception e){
			throw e;
		}
	}

	public  void elementToClick(String key,int seconds){
		try{
			WebElement elementToClick = driver.findElement(By.xpath(key));
			// Scroll the browser to the element's Y position
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+elementToClick.getLocation().y+")");
			// Click the element
			elementToClick.click();

		}catch(Exception e){
			throw e;
		}
	}


	public  void explicitlyWaitforSelectableElement(String key,int seconds){
		try{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeSelected(By.xpath(key)));

		}catch(Exception e){
			throw e;
		}
	}
	public  void implicitlyWait(int time){
		try{
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		}catch(Exception e){
			throw e;
		}
	}

	public void waitForPageLoading(int time) throws Exception {
		try {
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
		} catch (Exception e) {
			throw e;
		}

	}
	public  void maximizeWindow(){
		try{
			driver.manage().window().maximize();
		}catch(Exception e){
			throw e;
		}
	}
	public void hardPause(int seconds) throws Exception
	{
		try {
			seconds=seconds*1000;
			Thread.sleep(seconds);
		} catch (Exception e) {
			throw e;
		}
	}

	public  void selectDropDown(String key,String value)
	{
		try{
			WebElement wb=driver.findElement(By.xpath(key));
			Select sc=new Select(wb);
			sc.selectByVisibleText(value);
		}catch(Exception e){
			throw e;
		}
	}
	public  void mouseHover(String key)
	{
		act=new Actions(driver);
		try{

			act.moveToElement(driver.findElement(By.xpath(key))).build().perform();
		}catch(Exception e){
			throw e;
		}
	}
	public  void clickOnmouseHover(String key)
	{
		try{	
			act=new Actions(driver);
			act.moveToElement(driver.findElement(By.xpath(key))).click().build().perform();
		}catch(Exception e)
		{
			throw e;
		}
	}

	public  void doubleClickOnElement(String key)
	{
		try{
			act= new Actions(driver);
			act.doubleClick(driver.findElement(By.xpath(key))).perform();
		}catch(Exception e)
		{
			throw e;
		}
	}


	public  void closeAllBrowser(){
		try{
			this.driver.quit();
		}catch(Exception e){
			throw e;
		}
	}
	public  String gettextFromElement(String key){
		try{
			return driver.findElement(By.xpath(key)).getText();

		}catch(Exception e){
			throw e;
		}
	}
	public  String getvalueFromElement(String key,String value){
		try{
			return driver.findElement(By.xpath(key)).getAttribute(value);

		}catch(Exception e){
			throw e;
		}


	}
	/*public  void captureScreenshot(String image) 
	{   
		try{
			File fl=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(fl,new File(System.getProperty("user.dir")+"\\Screenshot\\"+image+".png"));
			System.out.println("screen shot captured");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
	public  String GetSnapShot(String testCaseName) throws Exception
	{
		FileOutputStream fos = null;
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String OutPutFileName  = screenshotFolderPath + testCaseName+".png";
		fos = new FileOutputStream(OutPutFileName);
		FileUtils.copyFile(scrFile, fos);
		fos.close();
		return OutPutFileName;
	}
	public void clickOnKeyboard(){
		act=new Actions(driver);
		act.click().keyDown(Keys.TAB).build().perform();
	}
	public void windowPopup(int x)
	{
		Set<String>st= driver.getWindowHandles();
		Iterator<String>it=st.iterator();
		while (x>0) {
			{
				windowID=it.next();
				x--;
			}
			driver.switchTo().window(windowID);
		}
	}
	public void closeBrowser(){
		try{
			driver.close();
		}catch(Exception e){
			throw e;
		}
	}
	public void clearFromTextbox(String key)
	{
		try{
			driver.findElement(By.xpath(key)).clear();
		}catch(Exception e){
			throw e;
		}
	}
	public void selectValueFromPickList(String key,String value)
	{
		try{
			driver.findElement(By.xpath(key)).click();
			driver.findElement(By.xpath(value)).click();
		}catch(Exception e){
			throw e;
		}
	}
	public  void clickOnButton(String key){
		try{
			taxilla.waitForInvisibleOfSpinner();
			driver.findElement(By.xpath(key)).click();
		}catch(Exception e){
			throw e;
		}
	}
	public  void clickOnLink(String key)
	{ try{
		taxilla.waitForInvisibleOfSpinner();
		driver.findElement(By.xpath(key)).click();
	}catch(Exception e){
		throw e;
	}
	}
	
	public  void clickOn(String key) throws Exception
	{ try{
		taxilla.waitForInvisibleOfSpinner();
		explicitlyWaitforClickableElementByXpath(key, 50);
		driver.findElement(By.xpath(key)).click();
	}catch(Exception e){
		throw e;
	}
	//return true;
	}
	
	public  void explicitlyWaitforClickableElementByXpath(String key,int seconds) throws Exception{
		try{
			//WebDriverWait wait=new WebDriverWait(driver,seconds);
			Wait<WebDriver> wait=new WebDriverWait(driver, Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(key)));

		}catch(Exception e){
			throw e;
		}
	}

	public void scrollIntoElement(String findKey) {
		WebElement element = driver.findElement(By.xpath(findKey));
		System.out.println("element-"+ element);
		js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}


	/**
	 * @author grpaul: Method that can be called to get the count of passed
	 *         Element in the WebPage.
	 *
	 * @param locator The element that needs to be counted.
	 * @return Returns 0, if the element is not available, else will return the
	 *         count.
	 * @throws Exception 
	 */
	public int countOf(By locator){
		int count = 0;
		try {
			taxilla.waitForInvisibleOfSpinner();
			count = driver.findElements(locator).size();
		} catch (Exception e) {
			throw e;
		}
		return count;
	}
	/*public  void clickOnTextField(String key)
	{ try{
		taxilla.waitForInvisibleOfSpinner();
		driver.findElement(By.xpath(key)).click();
	}catch(Exception e){
		throw e;
	}
	}
	public  void clickOnIcon(String key)
	{ try{
		taxilla.waitForInvisibleOfSpinner();
		driver.findElement(By.xpath(key)).click();
	}catch(Exception e){
		throw e;
	}
	}*/
	public void alertPopup()
	{
		try{
			Alert alt=driver.switchTo().alert();
			alt.accept();
		}catch(Exception e){
			throw e;
		}
	}
	public  String gettitleFromElement(String key){
		try{
			return driver.findElement(By.xpath(key)).getAttribute("title");
		}catch(Exception e)
		{
			throw e;
		}  
	}
	public  void pageRefresh(){
		try{
			driver.navigate().refresh();
		}catch(Exception e){
			throw e;
		}
	}

	public  int TotalRecordsCount(String key)
	{
		String text= gettextFromElement(key);
		int TotalRecordsStringLength = text.length();

		if(TotalRecordsStringLength == 20)
		{

			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-5, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}


		if(TotalRecordsStringLength == 19)
		{

			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-4, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}

		if(TotalRecordsStringLength == 18)
		{

			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-3, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}

		if(TotalRecordsStringLength == 17)
		{

			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-2, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}

		if(TotalRecordsStringLength == 16)
		{


			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-1, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}
		//         System.out.println("RecordsCount: "+ RecordsCount);
		return RecordsCount;
	}


	public  int TotalRecordsCount1(String key)
	{
		String text= gettextFromElement(key);
		int TotalRecordsStringLength = text.length();

		if(TotalRecordsStringLength == 21)
		{

			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-3, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}


		if(TotalRecordsStringLength == 20)
		{

			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-2, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}

		if(TotalRecordsStringLength == 19)
		{

			RecordsCount = Integer.parseInt(text.substring(TotalRecordsStringLength-1, TotalRecordsStringLength));
			//               System.out.println("Total Records: " + RecordsCount);
		}
		return RecordsCount;
	}

	public boolean objectIsVisible(String key)
	{
		try{
			test= driver.findElement(By.xpath(key)).isDisplayed();	
		}catch(Exception e){
			throw e;
		}
		return test;
	}


	public boolean objectIsSelected(String key)
	{
		try{
			WebElement element= driver.findElement(By.xpath(key));
			return element.isSelected();
		}catch(Exception e){
			throw e;
		}
	}



	public boolean objectIsEnabled(String key)
	{
		try{
			WebElement element= driver.findElement(By.xpath(key));
			return element.isEnabled();
		}catch(Exception e){
			throw e;
		}
	}
	public boolean objectIsDisplayed(String key)
	{
		try{
			WebElement element= driver.findElement(By.xpath(key));
			return element.isDisplayed();
		}catch(Exception e){
			throw e;
		}
	}

	public void sendKeys(String key,String value)
	{
		try{
			driver.findElement(By.xpath(key)).click();
			driver.findElement(By.xpath(key)).clear();
			driver.findElement(By.xpath(key)).sendKeys(value);
			driver.findElement(By.xpath(key)).sendKeys(Keys.ENTER); 
		}
		catch(Exception e){
			throw e;
		}
	}
	public void sendKeys1(String key,String value)
	{
		try{
			driver.findElement(By.xpath(key)).click();
			driver.findElement(By.xpath(key)).clear();
			driver.findElement(By.xpath(key)).sendKeys(value); 
		}
		catch(Exception e){
			throw e;
		}
	}

	public int getNoOfLinksInPageByPartialText(String value)
	{
		try{
			List<WebElement>Links =driver.findElements(By.partialLinkText(value)) ;
			return Links.size();
		}catch(Exception e){
			throw e;
		}
	}
	public int getNoOfLinkInPageByTagName(String key)
	{
		try{
			List<WebElement>Links =driver.findElements(By.tagName(key));
			return Links.size();
		}catch(Exception e){
			throw e;
		}
	}


	public int getNoOfElemetsInPageByXpath(String key)
	{
		try
		{
			List<WebElement>Links =driver.findElements(By.xpath(key));
			return Links.size();		
		}catch(Exception e){
			throw e;
		}
	}


	public int getNoOfLinkInPageByXpath(String key)
	{
		try{
			Links =driver.findElements(By.xpath(key));
			return Links.size();
		}catch(Exception e){
			throw e;
		}
	}

	public int getNoOfLinkInPageByXpathAndTag(String key,String tagName)
	{
		try{
			Links =driver.findElements(By.xpath(key).tagName(tagName));
			return Links.size();
		}catch(Exception e){
			throw e;
		}
	}
	public  void explicitlyWaitForVisibilityOfElement(String key,int seconds){
		try{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
			WebElement element =driver.findElement(By.xpath(key)) ;
			wait.until(ExpectedConditions.visibilityOf(element));
		}catch(Exception e){
			throw e;
		}
	}

	public  void explicitlyWaitForVisibilityOfElementLocated(String key,int seconds){
		try{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
			By element =By.xpath(key) ;
			wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		}catch(Exception e){
			throw e;
		}
	}

	public void waitForCondition(final String key,int seconds)
	{ 
		try{

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(seconds))
					.pollingEvery(Duration.ofSeconds(2))
					.ignoring(WebDriverException.class);
			wait.until(new Function<WebDriver, WebElement>() 
			{
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(key));	
				}
			});
		}catch(Exception e)
		{
			throw e;
		}
	}
	public void waitForElementToBeInteractable(final String key,int seconds)
	{ 
		try{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(seconds))
					.pollingEvery(Duration.ofSeconds(5))
					.ignoring(InvalidElementStateException.class);
			WebElement fluentwait= wait.until(new Function<WebDriver, WebElement>() 
			{
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(key));	
				}
			});
		}catch(Exception e)
		{
			throw e;
		}
	}
	public void waitForElementToBeInteractableByFluent(final String key,int seconds)
	{ 
		try{
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(seconds))
					.pollingEvery(Duration.ofSeconds(2))
					.ignoring(NoSuchElementException.class);
			WebElement fluentwait= wait.until(new Function<WebDriver, WebElement>() 
			{
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath(key));	
				}
			});
		}catch(Exception e)
		{
			throw e;
		}
	}
	public  void explicitlyWaitForInVisibilityOfElement(String key,int seconds){
		try{
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(key)));

		}catch(Exception e){
			throw e;
		}
	}
	

	public void scrollDownToBottomOfPage() {
		try{
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)");
		}catch(Exception e){
			throw e;
		}
	}
	public void scrollUpToTopOfPage() {
		try{
			((JavascriptExecutor) driver).executeScript("window.scrollBy(1000,0)");
		}catch(Exception e){
			throw e;
		}
	}
	public void scrollUpVerticallyToTopOfPage() {
		try{
			((JavascriptExecutor)driver).executeScript("window.scrollTo(document.body.scrollHeight,0)");
		}catch(Exception e){
			throw e;
		}
	}

	public void scrollDownVerticallyToBottomOfPage() {
		try{
			((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight);");
		}catch(Exception e){
			throw e;
		}
	}
	public void scrollUpToElementOfPage(String key) {
		try{
			WebElement element = driver.findElement(By.xpath(key));
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView();", element);
		}catch(Exception e){
			throw e;
		}
	}
	public void windowsFile(String path) throws Exception {
		try{
			Runtime.getRuntime().exec(path);
		}catch(Exception e){
			throw e;
		}
	}
	public void scrollDownToPageByDownKey(String key) {
		try{
			driver.findElement(By.xpath(key)).sendKeys(Keys.ARROW_DOWN); 
		}catch(Exception e){
			throw e;
		}
	}

	public void ClickOnElementByEnterKey(String key) {
		try{
			driver.findElement(By.xpath(key)).sendKeys(Keys.ENTER); 
		}catch(Exception e){
			throw e;
		}
	}

	public void scrollDownForEndKey() throws Exception {
		try{
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_END);
			robot.keyRelease(KeyEvent.VK_END);
		}catch(Exception e){
			throw e;
		}
	}

	public void uploadFile(String fileLocation) {
		try {
			//Setting clipboard with file location
			setClipboardData(fileLocation);
			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public void MultipleTab() {
		try {

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	public void KeyDownOperation() {
		try {

			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_DOWN);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	public void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		StringSelection stringSelection = new StringSelection(string);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

}