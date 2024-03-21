package HalvaotProject;
import HalvotPages.Deal.DealDetailsPage;
import HalvotPages.Deal.DealTablePage;
import HalvotPages.Loan.*;
import Utils.JsonUtils;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.json.simple.JSONObject;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.asserts.SoftAssert;
import HalvotPages.*;

import java.io.IOException;
import java.util.List;

public class LoanTest extends BaseLoanTest {
    String NameOfDeal = "עסקה אוטומציה";
    String amountDeal = "10,000";
    String amountFrame = "5000";
    String amountLoan = "2000";
    String explanation = "בדיקה";
    String fieldsDealOffers = "5";
    String date = "10/12/2023";
    String endDateFrame = "30/12/2023";
    String BaseAssetMultiplier = "5";
    String path = "X:\\פריוריטי\\Or Baron\\1.docx";
    String baseName = "מסגרת אוטומציה";
    private String csvFilePath = "S:\\Danel_HB_022023\\Assets_for_automation\\Assets.csv";
    String expectedAmount = "5,000";


    private CSVReader csvReader;
    private CSVWriter csvWriter;
    private static String testData = "TestData.json";
    private static String urlData = "urlData.json";
    String websiteUrl = "";
    String APIUrl = "";


    @Attachment(value = "page screenshot", type = "image/png")
    public byte[] saveScreenShot(WebDriver driver) {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        return scrShot.getScreenshotAs(OutputType.BYTES);
    }

    @Test(description = "פתיחת הלוואה חדשה")
    @Description("מסך הלוואה נפתח תקין")
    public void test01openNewLoan() {
        JSONObject tes21Data = JsonUtils.returnJsonObject(testData, "testLoan2");
        LoanDetailsPage loanDetails = pageLoader.loanDetails;
        saveScreenShot(driver);
        Assert.assertEquals(loanDetails.getLoanTitle(), tes21Data.get("TitleLoan"));
    }

    @Test(description = "בלשונית פרטי הלוואה : בחירת נייר בשדה מס' הלוואה")
    @Description("בחירת נייר שלא מקושר לישות אחרת")
    public void test02choosePaperToLoan() throws InterruptedException, IOException, CsvException {
        LoanDetailsPage loanDetails = pageLoader.loanDetails;
        loanDetails.ChoosePaper();

    }

    @Test(description = "בדיקת שדות חישוביים של  %  הלוואה ממסגרת , יתרת מסגרת לפני , יתרת מסגרת לאחר ")
    @Description("% הלוואה ממסגרת= 40,יתרת מסגרת לפני=5,000,יתרת מסגרת לאחר=3,000 ")
    public void test03VerifyAmountsFields() throws InterruptedException {
        JSONObject tes21Data = JsonUtils.returnJsonObject(testData, "testLoan2");
        LoanDetailsPage loanDetails = pageLoader.loanDetails;
        SoftAssert softAssert = new SoftAssert();
        loanDetails.InsertStatusDatesAndAmountLoan();
        saveScreenShot(driver);
        softAssert.assertEquals(loanDetails.getLoanCoin(),tes21Data.get("LoanCoin"));
        softAssert.assertEquals(loanDetails.getLoanPercentageFromFrame(), tes21Data.get("LoanPercentageFromFrame"));
        softAssert.assertEquals(loanDetails.getRemainingFrameBefore(), tes21Data.get("RemainingFrameBefore"));
        softAssert.assertEquals(loanDetails.getRemainingFrameAfter(), tes21Data.get("RemainingFrameAfter"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת הורשה- פרטי הלוואה")
    @Description("בדיקת סטטוס עסקה= פוטנציאלית,סוג אשראי= ישיר, מטרת האשראי= רכישת שליטה, מעמדנו בעסקה= משקיע הוגן")
    public void test04OpenNewFrame() throws InterruptedException {
        JSONObject tes21Data = JsonUtils.returnJsonObject(testData, "testLoan2");
        LoanDetailsPage loanDetails = pageLoader.loanDetails;
        SoftAssert softAssert = new SoftAssert();
        saveScreenShot(driver);
        softAssert.assertEquals(loanDetails.getDealStatus(), tes21Data.get("DealStatus"));
        softAssert.assertEquals(loanDetails.getCreditType(), tes21Data.get("CreditType"));
        softAssert.assertEquals(loanDetails.getCreditPurpose(), tes21Data.get("CreditPurpose"));
        softAssert.assertEquals(loanDetails.getDealPosition(), tes21Data.get("DealPosition"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת הורשה- לווה ראשי")
    @Description("בדיקת לווה ראשי= מיאמי בעמ, זיהוי לווה= ח\"פ 12345679 - ישראל, טלפון לווה= ייי - 052336666 (סלולר), אימייל לווה= miamigmail.com ")
    public void test05VerifyBorrower() throws InterruptedException {
        JSONObject tes21Data = JsonUtils.returnJsonObject(testData, "testLoan2");
        LoanDetailsPage loanDetails = pageLoader.loanDetails;
        SoftAssert softAssert = new SoftAssert();
        saveScreenShot(driver);
        softAssert.assertEquals(loanDetails.getMainBorrower(), tes21Data.get("MainBorrower"));
        softAssert.assertEquals(loanDetails.getBorrowerID(), tes21Data.get("BorrowerID"));
        softAssert.assertEquals(loanDetails.getBorrowerPhone(), tes21Data.get("BorrowerPhone"));
        softAssert.assertEquals(loanDetails.getBorrowerEmail(), tes21Data.get("BorrowerEmail"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת הורשה- נתוני הצעה")
    @Description("בדיקת נתוני הצעה: ריבית= 5, ריבית משתנה= ליבור|215, מח מ= 5, הצמדה= נייר100|100, מרווח= 5, LTV= 5 ")
    public void test06VerifyOfferData() throws InterruptedException {
        JSONObject tes21Data = JsonUtils.returnJsonObject(testData, "testLoan2");
        LoanDetailsPage loanDetails = pageLoader.loanDetails;
        SoftAssert softAssert = new SoftAssert();
        saveScreenShot(driver);
        softAssert.assertEquals(loanDetails.getInterestField(), tes21Data.get("InterestField"));
        softAssert.assertEquals(loanDetails.getVariableInterest(), tes21Data.get("VariableInterest"));
        softAssert.assertEquals(loanDetails.getMHMField(), tes21Data.get("MHMField"));
        softAssert.assertEquals(loanDetails.getLinkage(), tes21Data.get("Linkage"));
        softAssert.assertEquals(loanDetails.getSpaciousField(), tes21Data.get("SpaciousField"));
        softAssert.assertEquals(loanDetails.getTLVField(), tes21Data.get("TLVField"));
        softAssert.assertAll();
    }

    @Test(description = "לשונית פרטים נוספים- בחירת נכס והתחייבות ובדיקת שדות הורשה מעסקה")
    @Description("בדיקת שדות תת אפיק = אג\"ח להמרה לא סחיר | 2800 , אפיק=אג\"ח להמרה לא סחיר, מנפיק=איסתא ליינס חברת נסיעות בישראל בע\"מ | 1036, ענף= 1 בנקים למשכנתאות | 2 , מדינה מנפיקה= ACADIAN , מדינה נסחרת=אפריקה ישראל, מדינת חשיפה= אירופה, אזור גאוגרפי= ישראל, בורסה= בדיקה | 1 , קונצרן= בזק | 10, נכס בסיס= נייר1000321 | 1000321, מכפיל נכס בסיס= 5 , מדד מטבע חשיפה= דולר | 20001, סוג לניהול סיכונים =אג ח להמרה, סוג הצמדה= אג ח לא סחיר להמרה- אחר")
    public void test07VerifyExtraDetailsPage() throws InterruptedException {
        JSONObject tes22Data = JsonUtils.returnJsonObject(testData, "testLoan3");
        LoanExtraDetailsPage loanExtraDetails = pageLoader.loanExtraDetails;
        SoftAssert softAssert = new SoftAssert();
        loanExtraDetails.goToExtraDetailsTab();
        saveScreenShot(driver);
        softAssert.assertEquals(loanExtraDetails.getTatAfik(), tes22Data.get("TatAfik"));
        softAssert.assertEquals(loanExtraDetails.getAfik(), tes22Data.get("Afik"));
        softAssert.assertEquals(loanExtraDetails.getManpik(), tes22Data.get("Manpik"));
        softAssert.assertEquals(loanExtraDetails.getAnaf(), tes22Data.get("Anaf"));
        softAssert.assertEquals(loanExtraDetails.getIssuingCountry(), tes22Data.get("IssuingCountry"));
        softAssert.assertEquals(loanExtraDetails.getTradingCountry(), tes22Data.get("TradingCountry"));
        softAssert.assertEquals(loanExtraDetails.getExposureState(), tes22Data.get("ExposureState"));
        softAssert.assertEquals(loanExtraDetails.getGeographicArea(), tes22Data.get("GeographicArea"));
        softAssert.assertEquals(loanExtraDetails.getBorsa(), tes22Data.get("Borsa"));
        softAssert.assertEquals(loanExtraDetails.getKonzrn(), tes22Data.get("Konzrn"));
        softAssert.assertEquals(loanExtraDetails.getProperty(), tes22Data.get("Property"));
        softAssert.assertEquals(loanExtraDetails.getBaseAssetMultiplier(), tes22Data.get("BaseAssetMultiplier"));
        softAssert.assertEquals(loanExtraDetails.getCoinExposure(), tes22Data.get("CoinExposure"));
        softAssert.assertEquals(loanExtraDetails.getTypeForRiskManagement(), tes22Data.get("TypeForRiskManagement"));
        softAssert.assertEquals(loanExtraDetails.getAttachmentType(), tes22Data.get("AttachmentType"));
        softAssert.assertAll();
    }

    @Test(description = "לשונית תנאים נוספים- בדיקת שדות הורשה")
    @Description("בדיקת צ'אקבוקסים מסומנים ושדות מלאים בהגדרות כללי, הגדרות מסגרת הגדרות ריבית")
    public void test08VerifyAdditionalConditionPage() throws InterruptedException {
        pageLoader = new PageLoader(driver);
        LoanAdditionalConditionPage loanAdditionalCondition = pageLoader.loanAdditionalCondition;
        SoftAssert softAssert = new SoftAssert();
        Thread.sleep(1000);
        loanAdditionalCondition.goToAdditionalConditionTab();
        loanAdditionalCondition.YesNoRadioButtonLoanPart1();
        loanAdditionalCondition.YesNoRadioButtonLoanPart2();
        List<WebElement> formRadioButton = driver.findElements(By.tagName("form-yes-no"));
        for (WebElement element : formRadioButton.toArray(new WebElement[0])) {
            saveScreenShot(driver);
            softAssert.assertTrue(element.isDisplayed());
            softAssert.assertAll();
        }

    }

    @Test(description = "בדיקת הורשה- לשונית לווים")
    @Description("מספר סידורי =5, בדיקת שם לווה= מיאמי בעמ, טלפון לווה= ייי - 052336666 (סלולר), אימייל לווה= miamigmail.com ")
    public void test09VerifyBorrower() throws InterruptedException {
        JSONObject tes23Data = JsonUtils.returnJsonObject(testData, "LoanTest4");
        pageLoader = new PageLoader(driver);
        SoftAssert softAssert = new SoftAssert();
        LoanBorrowerPage loanBorrower = pageLoader.loanBorrower;
        Thread.sleep(1000);
        loanBorrower.goToBorrowerTab();
        Thread.sleep(1000);
        softAssert.assertEquals(loanBorrower.getBorrowerName(), tes23Data.get("BorrowerName"));
        softAssert.assertEquals(loanBorrower.getSerialNumber(), tes23Data.get("SerialNumber"));
        softAssert.assertEquals(loanBorrower.getContact(), tes23Data.get("Contact"));
        softAssert.assertEquals(loanBorrower.getBorrowerPhone(), tes23Data.get("BorrowerPhone"));
        saveScreenShot(driver);
        softAssert.assertEquals(loanBorrower.getBorrowerEmail(), tes23Data.get("BorrowerEmail"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת הורשה- בטחונות וערבים")
    @Description("פירוט בטוחה = בדיקה , סוג בטוחה = אנרגיה ")
    public void test10VerifyCollateralAndGuarantorsPage() throws InterruptedException {
        JSONObject tes24Data = JsonUtils.returnJsonObject(testData, "LoanTest5");
        pageLoader = new PageLoader(driver);
        SoftAssert softAssert = new SoftAssert();
        LoanCollateralAndGuarantorsPage loanCollateralAndGuarantors = pageLoader.loanCollateralAndGuarantors;
        loanCollateralAndGuarantors.goToCollateralAndGuarantorsTab();
        saveScreenShot(driver);
        softAssert.assertEquals(loanCollateralAndGuarantors.getSafeDetail(), tes24Data.get("SafeDetail"));
        softAssert.assertEquals(loanCollateralAndGuarantors.getSafeType(), tes24Data.get("SafeType"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת הורשה- לשונית אנשי קשר")
    @Description("מספר סידורי =4, בדיקת שם לווה= ניקולה יוקיץ, טלפון= fff - 05222222 (סלולר), אימייל= fff@gmail.com, תפקיד= מנהל כספים")
    public void test11VerifyBorrower() throws InterruptedException {
        JSONObject tes25Data = JsonUtils.returnJsonObject(testData, "LoanTest6");
        pageLoader = new PageLoader(driver);
        SoftAssert softAssert = new SoftAssert();
        LoanContactsPage LoanContacts = pageLoader.LoanContacts;
        LoanContacts.goToContactsTab();
        saveScreenShot(driver);
        softAssert.assertEquals(LoanContacts.getContactName(), tes25Data.get("ContactName"));
        softAssert.assertEquals(LoanContacts.getSerialNumber(), tes25Data.get("SerialNumber"));
        softAssert.assertEquals(LoanContacts.getContactPhone(), tes25Data.get("ContactPhone"));
        softAssert.assertEquals(LoanContacts.getContactEmail(), tes25Data.get("ContactEmail"));
        softAssert.assertEquals(LoanContacts.getContactJob(), tes25Data.get("ContactJob"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת לשונית מסמכים")
    @Description("בדיקת לשונית מסמכים והוספת מסמך")
    public void test12VerifyDocumentsPage() throws InterruptedException {
        JSONObject tes26Data = JsonUtils.returnJsonObject(testData, "testFrame10");
        pageLoader = new PageLoader(driver);
        SoftAssert softAssert = new SoftAssert();
        LoanDocumentsPage loanDocuments = pageLoader.loanDocuments;
        loanDocuments.uploadDocument();
        saveScreenShot(driver);
        softAssert.assertEquals(loanDocuments.getDocumentsTab(), tes26Data.get("documentsTab"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת לשונית אירועים")
    @Description("בדיקת הוספת אירוע")
    public void test13VerifyEventsPage() throws InterruptedException {
        JSONObject tes27Data = JsonUtils.returnJsonObject(testData, "LoanTest8");
        pageLoader = new PageLoader(driver);
        SoftAssert softAssert = new SoftAssert();
        LoanEventPage loanEvent = pageLoader.loanEvent;
        loanEvent.openNewEvent();
        saveScreenShot(driver);
        softAssert.assertEquals(loanEvent.getDealEventTab(), tes27Data.get("dealEventTab"));
        softAssert.assertAll();
    }

    @Test(description = "בדיקת לשונית נתוני הצעה")
    @Description("בדיקת כותרת נתוני הצעה")
    public void test14VerifyOfferDataPage() throws InterruptedException {
        pageLoader = new PageLoader(driver);
        SoftAssert softAssert = new SoftAssert();
        JSONObject tes28Data = JsonUtils.returnJsonObject(testData, "LoanTest9");
        LoanOfferDataPage loanOfferData = pageLoader.loanOfferData;
        loanOfferData.goToOfferDataTab();
        saveScreenShot(driver);
        softAssert.assertEquals(loanOfferData.getOfferDataTab(), tes28Data.get("offerDataTab"));
        loanOfferData.saveLoan();
        softAssert.assertAll();
    }
    @Test(description = "שמירה מסגרת ועסקה")
    @Description("בדיקת שמירה עד הסוף (מסגרת ועסקה)")
    public void test15VerifyOfferDataPage () throws InterruptedException {
        pageLoader = new PageLoader(driver);
        SoftAssert softAssert = new SoftAssert();
        DealTablePage table = pageLoader.table;
        DealDetailsPage deal = pageLoader.deal;
        deal.saveFrameAndDeal();
        String expectedTitle = "נשמר בהצלחה!\n" +
                "×";
        saveScreenShot(driver);
        softAssert.assertEquals(table.getAlert(), expectedTitle);
        softAssert.assertAll();
    }
}
