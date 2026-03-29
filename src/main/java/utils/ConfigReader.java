package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Reads the property file (config.properties)
public class ConfigReader {

	private static Properties prop = new Properties();

	// static {} block runs once when the class is first loaded..it opens
	// config.properties and loads all key-value pairs into memory.
	// After that, any class in the framework can call ConfigReader.get("someKey") to pull a value
	static {
		try {
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/config.properties");
			prop.load(fis);
			fis.close();
		} catch (IOException e) {
			throw new RuntimeException("config.properties file not found", e);
		}
	}

	public static String get(String key) {
		String value = prop.getProperty(key);
		if (value == null) {
			throw new RuntimeException("Key" + key + " is not found in config.properties file");
		}

		return value.trim();
	}

}
