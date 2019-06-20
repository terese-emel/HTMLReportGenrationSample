package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeScreenshot {
    private String screenShotPath;

    public String captureScreenShot(WebDriver ldriver,String testName){
// Take screenshot and store as a file format
        File src=((TakesScreenshot)ldriver).getScreenshotAs(OutputType.FILE);
        try {
// now copy the  screenshot to desired location using copyFile method
            String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            screenShotPath = "E:\\IdeaProject\\TestProject\\output\\screenshots\\"+testName+"\\screenshot_on_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(src, new File(screenShotPath));
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return screenShotPath;
    }
}
