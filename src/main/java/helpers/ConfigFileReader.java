package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import enums.DriverType;

public class ConfigFileReader {

	static Properties properties;

	static {
		try {
			properties = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Properties loadProperties() throws IOException, FileNotFoundException {
		FileInputStream inputFile;

		String configFilePath = System.getProperty("user.dir") + "//src//test//resources//configurationData.properties";
		inputFile = new FileInputStream(configFilePath);

		properties = new Properties();
		try {
			properties.load(inputFile);
			inputFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	public static DriverType getBrowserType() {
		String browserName;
		if (System.getProperty("BROWSER") != null)
			browserName = System.getProperty("BROWSER");
		else
			browserName = properties.getProperty("browserType");
		if (browserName == null || browserName.equalsIgnoreCase("chrome"))
			return DriverType.CHROME;
		else if (browserName.equalsIgnoreCase("firefox"))
			return DriverType.FIREFOX;
		else
			throw new RuntimeException(
					"Browser Name Key value in Configuration.properties is not matched : " + browserName);
	}

	public static String getHomePageURL() {
		return properties.getProperty("homePageURL");
	}
}