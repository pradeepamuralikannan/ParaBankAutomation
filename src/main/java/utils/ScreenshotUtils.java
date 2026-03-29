package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.DriverFactory;

public class ScreenshotUtils {

	public static String capture(String testName) {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String fileName = testName + "_" + timeStamp + ".png";
		String filePath = System.getProperty("user.dir") + "/test-output/Screenshots/" + fileName;

		File srcFile = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		File desFile = new File(filePath);

		try {
			FileUtils.copyDirectory(srcFile, desFile);
			System.out.println("Screenshot saved:  " + filePath);
		} catch (IOException e) {
			System.err.println("Failed to capture screenshot for: " + testName);
			e.printStackTrace();
		}

		return filePath;

	}
}

/*
 * FileUtils is a class comes from Apache Commons IO...It is not part of the Java standard library. It comes from the commons-io dependency.
 * 
 * What FileUtils.copyFile() Actually Does Here? 
 * Without it, you'd have to write raw Java I/O streams to copy the screenshot from a temp location to your target folder — about 15 lines of boilerplate. 
 * FileUtils.copyFile() does the same thing in one line, and also auto-creates any missing parent folders in the path, 
 * so test-output/screenshots/ gets created automatically on first run
 */
