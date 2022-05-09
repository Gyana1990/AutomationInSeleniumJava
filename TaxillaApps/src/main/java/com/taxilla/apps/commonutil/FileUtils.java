package com.taxilla.apps.commonutil;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {

	private Properties property;
	private FileInputStream fileInputStream;
	private final String envFilePath = System.getProperty("user.dir")+"\\resources\\environment\\";
	private final String orFilePath = System.getProperty("user.dir")+"\\resources\\objectRepository\\";


	public Properties loadProperty(String fileName) throws IOException
	{
		switch(fileName){

		case "environment":
			fileInputStream = new FileInputStream(envFilePath+fileName+".properties");
			break;	

		default:
			fileInputStream = new FileInputStream(orFilePath+fileName+".properties");
			break; 
		}
		property = new Properties();
		property.load(fileInputStream);
		return property;
	}
	
	
}
