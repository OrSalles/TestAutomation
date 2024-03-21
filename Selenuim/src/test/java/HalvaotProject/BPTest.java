package HalvaotProject;

import HalvotPages.BP.BPPrivateDetailsPage;
import HalvotPages.PageLoader;
import Utils.JsonUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BPTest extends BaseBPTest {

    private static String testData = "TestData.json";
    private static String urlData = "urlData.json";
    String websiteUrl = "";
    String APIUrl = "";

    @Attachment(value = "page screenshot", type = "image/png")
    public byte[] saveScreenShot(WebDriver driver) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        return scrShot.getScreenshotAs(OutputType.BYTES);
    }

    @Test(description = "עמוד שותף עסקי - פרטי נפתח תקין")
    @Description("בדיקת כותרת BP פרטי = שותף עסקי - פרטי  ")
    public void test01VerifyBPPrivatePageOpen() {
        JSONObject BPPrivateTitle = JsonUtils.returnJsonObject(testData, "BPPrivateDetails");
        pageLoader = new PageLoader(driver);
        //SoftAssert softAssert = new SoftAssert();
        BPPrivateDetailsPage BPPrivateDetails = pageLoader.BPPrivateDetails;
        saveScreenShot(driver);
        Assert.assertEquals(BPPrivateDetails.getPrivateBPTitle(), BPPrivateTitle.get("PrivateBPTitle"));
        //softAssert.assertAll();
    }



}

