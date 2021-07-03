package automation.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropFileHandler {

	private static String filePath="src/test/resources/testData/Data.properties";
	public static PropFileHandler properties = new PropFileHandler();

	public String readProperty(String property) {
		Properties properties=new Properties();
		InputStream inPropFile = null;
		try {
			inPropFile = new FileInputStream(filePath);
			properties.load(inPropFile);
		} catch (IOException e) {
			System.out.println("There was Exception reading the Test data");
		}
		String value = properties.getProperty(property);
		return value;
	}

	public void writeHahMaptoDataPropertyFile(HashMap<String,String> cityInfo){
		Properties properties=new Properties();
		InputStream inPropFile=null;
		try{	   
			inPropFile = new FileInputStream(filePath);
			properties.load(inPropFile);
			inPropFile.close();
			OutputStream outPropFile = new FileOutputStream(filePath);
			properties.putAll(cityInfo);
			properties.store(outPropFile, null);
			outPropFile.close();
		}catch(Exception e){}

	}
}
