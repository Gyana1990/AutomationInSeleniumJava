package com.taxilla.apps.commonutil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.taxilla.apps.testbase.TestBase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class StatusReporting extends TestBase

{
	@Test()
	public static void StatusReporting() throws Exception
	{
		try
		{
			String Title = "Taxilla Assets";
			String Header = "Taxilla Assets Regression Test Suite";	
			String  Remarks="", SnapShotLink;
			int ResultRecordsCount=0, SlNum=0, TotalCount=0, PassCount=0, FailCount=0;
			String TestCaseNum;
			String  TestCaseTitle= "", ExecStatus = "Pass", Remarks1 = "";
			String StartDateTime, EndDateTime;
			String StatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
			//String  = reader.getDataFromConfigSheet("Config", "Sheet1", 1, 1);	
			String HTMLStatusReportFileName = reader.getDataFromConfigSheet("Config",0, 1);
			ResultRecordsCount = reader.getResultRecordsCount(StatusReportFileName);
			StartDateTime = reader.getStatusReportData(StatusReportFileName, 1, 6);
			EndDateTime = reader.getStatusReportData(StatusReportFileName, ResultRecordsCount, 7);
			String Duration = DateUtils.Duration(StartDateTime, EndDateTime);

			for(int i=1; i<=ResultRecordsCount; i++)
			{

				ExecStatus = reader.getStatusReportData(StatusReportFileName, i, 3);
				if(ExecStatus.equalsIgnoreCase("Pass"))
				{
					PassCount = PassCount +1;  
				}
				else
				{
					FailCount = FailCount+1;  
				}

			} 
			TotalCount = ResultRecordsCount;

			//define a HTML String Builder
			StringBuilder htmlStringBuilder=new StringBuilder();
			htmlStringBuilder.append("<html><head><title>" + Title + "</title></head>");
			htmlStringBuilder.append("<body bgcolor= #FCF3CF >"+"<h1><font color= #CD6155>" + Header + "</h1>");
			htmlStringBuilder.append("<table border=\"2\" bordercolor=\"#000000\">");
			htmlStringBuilder.append("<tr bgcolor= #EDBB99 > <td><b> Total Count </b></td> <td><b> Pass Count </b></td>     <td><b> Fail Count </b></td><td><b> Skipped </b></td></tr>");
			htmlStringBuilder.append("<tr> <td align=center> "+ TotalCount + "</td> <td align=center>" + PassCount + "</td>    <td align=center>" + FailCount + "</td> <td align=center> 0 </td> </tr>");
			htmlStringBuilder.append("</table></body></html>");
			htmlStringBuilder.append("<br>");
			htmlStringBuilder.append("<br>");
			htmlStringBuilder.append("<table border=\"2\" bordercolor=\"#000000\">");
			htmlStringBuilder.append("<tr bgcolor= #EDBB99> <td><b> Start Date Time </b></td> <td><b> End Date Time </b></td>     <td><b> Duration (DD:HH:MM:SS) </b></td>  <td><b> Comments </b></td>   </tr>");
			htmlStringBuilder.append("<tr> <td align=center> "+ StartDateTime + "</td> <td align=center>" + EndDateTime +" </td> " +" <td align=center> "+ Duration +" </td> <td align=center>" +"---" + "</td> </tr>");
			htmlStringBuilder.append("</table></body></html>");

			htmlStringBuilder.append("<br>");
			htmlStringBuilder.append("<br>");

			htmlStringBuilder.append("<table border=\"2\" bordercolor=\"#000000\">");
			htmlStringBuilder.append("<tr bgcolor= #EDBB99> <td><b> Sl.No. </b></td> <td><b> Test Case# </b></td> <td><b> Test Case Title         </b></td>  <td><b> Status - (Pass/Fail) </b></td>  <td><b> Remarks   </b></td> <td><b> Snapshot Link </b></td> <td><b> Start Date Time </b></td> <td><b> End Date Time </b></td> </tr>");
			//htmlStringBuilder.append("<tr> <td><b> Start_Date_Time </b></td> <td><b> End Date Time </b></td>     <td><b> Duration </b></td>  <td><b> Other Info </b></td>   </tr>");


			for(int i=1; i<=ResultRecordsCount; i++)
			{

				SlNum = reader.readIntvalue(StatusReportFileName, i, 0);
				TestCaseNum = reader.getStatusReportData(StatusReportFileName,i, 1); 
				TestCaseTitle = reader.getStatusReportData(StatusReportFileName,i, 2);
				ExecStatus = reader.getStatusReportData(StatusReportFileName,i, 3);
				Remarks = reader.getStatusReportData(StatusReportFileName,i, 4);
				SnapShotLink = reader.getStatusReportData(StatusReportFileName,i, 5);
				StartDateTime = reader.getStatusReportData(StatusReportFileName , i, 6);
				EndDateTime = reader.getStatusReportData(StatusReportFileName,i, 7);
				if(ExecStatus.equalsIgnoreCase("Pass")) 
				{
					htmlStringBuilder.append("<tr> <td align=center> "+ SlNum + "</td> <td align=center>" + TestCaseNum +" </td> " +" <td align=left> "+ TestCaseTitle + " </td> <td align=center bgcolor=#00FF00>" +ExecStatus+ "</td>   </td> <td align=left>" +Remarks+ "</td> <td align=left>" + "</td> <td align=center>" +StartDateTime + "</td> <td align=center>" +EndDateTime + "</td></tr>");
				}
				else
				{
					htmlStringBuilder.append("<tr> <td align=center> "+ SlNum + "</td> <td align=center>" + TestCaseNum +" </td> " +" <td align=left> "+ TestCaseTitle + " </td> <td align=center bgcolor=#E74C3C>" +ExecStatus+ "</td>   </td> <td align=left>" +Remarks+ "</td> <td align=left>" +"<a href=" +SnapShotLink + ">Snapshot Link </a>"+"</td> <td align=center>" +StartDateTime + "</td> <td align=center>" +EndDateTime + "</td></tr>");
				}
			} 




			htmlStringBuilder.append("</table></body></html>");
			WriteToFile(htmlStringBuilder.toString(),(HTMLStatusReportFileName+".html"));
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
	public static void WriteToFile(String fileContent, String fileName) throws IOException 
	{
		String tempFile = reader.ExecutionReportfilepath + File.separator+fileName;
		File file = new File(tempFile);
		// if file does exists, then delete and create a new file
		OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile());
		Writer writer=new OutputStreamWriter(outputStream);
		writer.write(fileContent);
		writer.close();

	}
}

