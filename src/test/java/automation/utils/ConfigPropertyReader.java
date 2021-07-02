package automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


public class ConfigPropertyReader {
	private static String defaultConfigFile = "Config.properties";


	public ConfigPropertyReader() {
	}


	public static String getProperty(String Property)  {
		File file = new File(defaultConfigFile);       
		Properties property = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(file);
			property.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return property.getProperty(Property);

	}
}
