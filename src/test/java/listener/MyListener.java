package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static testcases.TestBase.driver;
import static util.Utility.captureScreenshot;

public class MyListener implements ITestListener {
    public void onTestStart(ITestResult result) {
        System.out.println("Now Running: " + result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        // take screenshot on test failure
        captureScreenshot(driver,"Fail_");
        System.out.println(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    public void onStart(ITestContext context) {
//        try {
//            MyScreenRecorder.startRecording(context.getName());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }

    public void onFinish(ITestContext context) {
//        try {
//            MyScreenRecorder.stopRecording();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
//    public void takeScreenshot(){
//        WebDriver driver=new ChromeDriver();
//        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//        Date currntDate=new Date();
//        String screenshotName = "Failed_" + currntDate.toString().replace(" ","-").replace(":","-");
//        try {
//            FileHandler.copy(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")
//                    + "/src/test/resources/Screenshots/"+ screenshotName + ".png"));
//        } catch (WebDriverException | IOException e) {
//            e.printStackTrace();
//        }
//    }
}
