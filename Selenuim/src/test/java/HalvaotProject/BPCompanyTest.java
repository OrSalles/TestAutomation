package HalvaotProject;

import HalvotPages.BP.BPCompanyContactInformationPage;
import HalvotPages.BP.BPCompanyContactsPage;
import HalvotPages.BP.BPCompanyDetailsPage;
import HalvotPages.PageLoader;
import Utils.JsonUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.json.JSONArray;

import java.io.IOException;



public class BPCompanyTest extends BaseBPCompanyTest {
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
    public void test01verifyBPCompanyDetailsPage() throws IOException, IllegalAccessException, InterruptedException {
        JSONObject BPCompanyInformation = JsonUtils.returnJsonObject(testData, "BPCompanyInfo");
        pageLoader = new PageLoader(driver);
        BPCompanyDetailsPage BPCompanyDetails = pageLoader.BPCompanyDetails;
        BPCompanyDetails.insertCompanyDetails();
        BPCompanyDetails.saveBPCompany();
        BPCompanyDetails.chooseLastBPCompany();
        BPCompanyDetails.assertBPCompanyDetails(BPCompanyInformation);
    }

    @Test(description = "עמוד שותף עסקי - פרטי נפתח תקין")
    @Description("בדיקת כותרת BP פרטי = שותף עסקי - פרטי  ")
    public void test02verifyBPCompanyContactInformationPage() throws IOException, IllegalAccessException, InterruptedException {
        JSONObject BPCompanyContactInfo = JsonUtils.returnJsonObject(testData, "BPCompanyContactInformation");
        pageLoader = new PageLoader(driver);
        BPCompanyContactInformationPage BPContactInfo = pageLoader.BPContactInfo;
        BPContactInfo.moveToContactTab();
        BPContactInfo.insertAddress();
        BPContactInfo.insertPhoneDetails();
        BPContactInfo.insertWebsite();
        BPContactInfo.insertEmail();
        BPContactInfo.saveBPCompany();
        BPContactInfo.chooseLastBPCompany();
        BPContactInfo.moveToContactTab();
        BPContactInfo.assertBPCompanyContactInfo(BPCompanyContactInfo);

    }

    @Test(description = "עמוד שותף עסקי - פרטי נפתח תקין")
    @Description("בדיקת כותרת BP פרטי = שותף עסקי - פרטי  ")
    public void test03verifyBPCompanyContactsPage() throws IOException, IllegalAccessException, InterruptedException {
        JSONObject BPCompanyContactDetails = JsonUtils.returnJsonObject(testData, "BPCompanyContacts");
        pageLoader = new PageLoader(driver);
        BPCompanyContactsPage BPCompanyContacts = pageLoader.BPCompanyContacts;
        BPCompanyContacts.moveToContactTab();
        BPCompanyContacts.chooseContact();
        BPCompanyContacts.saveBPCompany();
        BPCompanyContacts.chooseLastBPCompany();
        BPCompanyContacts.moveToContactTab();
        BPCompanyContacts.assertBPCompanyContact(BPCompanyContactDetails);

    }
}
