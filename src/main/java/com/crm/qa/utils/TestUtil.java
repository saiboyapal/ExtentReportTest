package com.crm.qa.utils;

import com.crm.qa.base.Testbase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestUtil extends Testbase {
    public static long PAGE_LOAD_TIMEOUT = 30;
    public static long IMPLICIT_WAIT = 20;

    public static String getScreenshotAtName(String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //String currentDir = System.getProperty("user.dir");
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileHandler.copy(scrFile, finalDestination);
        return destination;
    }

    /*public static void setExtent(ExtentReports extent){
        extent.addSystemInfo("Host Name", "Naveen Mac");
        extent.addSystemInfo("User Name", "Naveen Automation Labs");
        extent.addSystemInfo("Environment", "QA");
    }

    public static void logTestResults(ExtentTest extentTest, ITestResult result) throws IOException{
        if(result.getStatus()== ITestResult.FAILURE){
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
            extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report

            String screenshotPath = getScreenshotAtName(result.getName());
            extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
            //extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
        }
        else if(result.getStatus()==ITestResult.SKIP){
            extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
        }
        else if(result.getStatus()==ITestResult.SUCCESS){
            extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

        }
    }

    public static void clearExtent(ExtentReports extent) {
        extent.flush();
        extent.close();
    }*/

    /*public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException{
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        // after execution, you could see a folder "FailedTestsScreenshots"
        // under src folder
        String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
                + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }*/

}

