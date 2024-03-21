package HalvaotProject;


import HalvotPages.Deal.*;
import Utils.JsonUtils;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import HalvotPages.*;

import static Utils.RandomID.generateRandomID;

public class DealTest extends BaseDealTest {

    String NameOfDeal = "עסקה אוטומציה";
    String amount = "10,000";
    String path ="X:\\פריוריטי\\Or Baron\\1.docx";
    String baseName = "עסקה אוטומציה";
   String randomID = generateRandomID();
   String nameWithRandomID = baseName + randomID;
    private static String testData= "TestData.json";
    private static  String urlData = "urlData.json";
    String websiteUrl = "";
    String APIUrl = "";

    @Attachment(value = "page screenshot",type = "image/png")
    public byte[] saveScreenShot(WebDriver driver) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        return scrShot.getScreenshotAs(OutputType.BYTES);
    }

    @Test(description = "בדיקת לשונית פרטי עסקה")
    @Description("בדיקת לשונית פרטי עסקה- סכום עסקה = 10000")
    public void test01dealDetails() throws InterruptedException {
        pageLoader = new PageLoader(driver);
        DealDetailsPage deal = pageLoader.deal;
        Assert.assertTrue(deal.isPageLoaded(),"Deal Page Not Loaded");
        deal.fillingOutDealDetailsFields();
        saveScreenShot(driver);
        Assert.assertEquals(deal.getTotalAmountDeal(), amount);
    }

    @Test(description = "בדיקת לשונית לווים")
    @Description("בדיקת לשונית לווה- לווה = מיאמי בע מ")
    public void test02borrowerPage() {
        JSONObject test1Data= JsonUtils.returnJsonObject(testData, "testDeal1");
        pageLoader = new PageLoader(driver);
        DealDetailsPage deal = pageLoader.deal;
        deal.GoToBorrowerTabAndFillingOutTheFields();
        saveScreenShot(driver);
        Assert.assertEquals(deal.getMainBorrowerField(), test1Data.get("MainBorrowerField"));

    }

    @Test(description = "בדיקת לשונית נתוני הצעה")
    @Description("מעבר ללשונית נתוני הצעה והזנת הנתונים")
    public void test03offerDataPage() throws InterruptedException {
        pageLoader = new PageLoader(driver);
        JSONObject test2Data= JsonUtils.returnJsonObject(testData, "testDeal2");
        DealOfferDataPage offerData = pageLoader.OfferData;
        offerData.goToOfferDataTabAndFillingOutTheFields();
        saveScreenShot(driver);
        Assert.assertEquals(offerData.getOfferDataPageTitle(), test2Data.get("offerDataPageTitle"));

    }

    @Test(description = "בדיקת לשונית תנאים נוספים")
    @Description("הזנת נתונים בלשונית פרטים נוספים")
    public void test04extraDetailsPage() throws InterruptedException {
        JSONObject test3Data= JsonUtils.returnJsonObject(testData, "testDeal3");
        pageLoader = new PageLoader(driver);
        DealExtraDetailsPage extraDetails = pageLoader.extraDetails;
        extraDetails.goToExtraDetailsTabAndFillingOutTheFields();
        saveScreenShot(driver);
        Assert.assertEquals(extraDetails.getAfik(), test3Data.get("afik"));
    }
    @Test(description = "בדיקת לשונית תנאים נוספים")
    @Description("בדיקת כותרות וסימון השדות בלשונית םרטים נוספים")
    public void test05additionalConditionsPage(){
        JSONObject test4Data= JsonUtils.returnJsonObject(testData, "testDeal4");
        pageLoader = new PageLoader(driver);
        DealAdditionalConditionsPage additionalConditions = pageLoader.additionalConditions;
        additionalConditions.goToAdditionalConditionsAndFillingOutAllTheFields();
        saveScreenShot(driver);
        Assert.assertEquals(additionalConditions.getAdditionalConditionsTab(), test4Data.get("AdditionalConditionsTab"));
    }
    @Test(description = "בדיקת לשונית בטחונות וערבים")
    @Description("בדיקת מעבר ללשונית בטחונות וערבים והזנת השדות")
    public void test06CollateralAndGuarantorsPage(){
        JSONObject test5Data= JsonUtils.returnJsonObject(testData, "testDeal5");
        pageLoader = new PageLoader(driver);
        DealCollateralAndGuarantorsPage CollateralAndGuarantors = pageLoader.CollateralAndGuarantors;
        CollateralAndGuarantors.goToCollateralAndGuarantorsAndFillingOutTheFields();
        saveScreenShot(driver);
        Assert.assertEquals(CollateralAndGuarantors.getCollateralAndGuarantorsTab(), test5Data.get("CollateralAndGuarantorsTab"));
    }
    @Test(description = "בדיקת לשונית אנשי קשר")
    @Description("בדיקת לשונית אנשי קשר ובחירת איש קשר מהרשימה")
    public void test07contactsPage(){
        JSONObject test6Data= JsonUtils.returnJsonObject(testData, "testDeal6");
        pageLoader = new PageLoader(driver);
        DealContactsPage contacts = pageLoader.contacts;
        contacts.goToContactTabAndFillingOutTheFields();
        saveScreenShot(driver);
        Assert.assertEquals(contacts.getContactTab(), test6Data.get("contactTab"));


    }
    @Test(description = "בדיקת לשונית מסמכים")
    @Description("בדיקת העלאת מסמך בלשונית מסמכים")
    public void test08documentsPage() throws InterruptedException {
        JSONObject test7Data= JsonUtils.returnJsonObject(testData, "testDeal7");
        pageLoader = new PageLoader(driver);
        DealDocumentsPage documents = pageLoader.documents;
        documents.upLoadFileInDocumentsTab();
        saveScreenShot(driver);
        Assert.assertEquals(documents.getDocumentsTab(), test7Data.get("documentsTab"));
    }
    @Test(description = "בדיקת לשונית אירועים")
    @Description("בדיקת לשונית אירועים והקמת אירוע חדש")
    public void test09eventsPage(){
        JSONObject test8Data= JsonUtils.returnJsonObject(testData, "testDeal8");
        pageLoader = new PageLoader(driver);
        DealEventPage dealEvent = pageLoader.dealEvent;
        dealEvent.goToEventsAndOpenNewEvent();
        saveScreenShot(driver);
        Assert.assertEquals(dealEvent.getDealEventTab(), test8Data.get("dealEventTab"));
    }
    @Test(description = "בדיקת שמירה עסקה")
    @Description("בדיקת שמירה עסקה עם הודעה = נשמר בהצלחה")
    public void test10closeDeal() throws InterruptedException {
        pageLoader = new PageLoader(driver);
        DealDetailsPage deal = pageLoader.deal;
        DealTablePage table = pageLoader.table;
        deal.saveDeal();
        String expectedTitle = "נשמר בהצלחה!\n" +
                "×";
        saveScreenShot(driver);
        Assert.assertEquals(table.getAlert(), expectedTitle);
    }
    @Test(description = "פתיחה של העסקה החדשה")
    @Description("פתיחת העסקה האחרונה שנוספה לרשימת העסקאות")
    public void test11openTheDeal() throws InterruptedException {
        JSONObject test9Data= JsonUtils.returnJsonObject(testData, "testDeal9");
        pageLoader = new PageLoader(driver);
        DealTablePage table = pageLoader.table;
        DealDetailsPage deal = pageLoader.deal;
        table.openTheLestDeal();
        saveScreenShot(driver);
        Assert.assertEquals(deal.getDealTitle(), test9Data.get("dealTitle"));


    }


}












































