package HalvotPages.Frame;

import HalvotPages.BasePage;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class FrameExtraDetailsPage extends BasePage {

    public FrameExtraDetailsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@class='formContainer']//onboarding//div[@class='d-flex flex-column position-absolute ng-star-inserted']//div//b[contains(text(),'פרטים נוספים')]")
    public WebElement extraDetailsTab;
    @FindBy(xpath = "(//a[@href='javascript:;'])[44]")
    public WebElement PropertyNumberButton;
    @FindBy(xpath = "//div[@title='77732 - בדיקת באג | 600600']")
    public WebElement PropertyNumberFromList;
    @FindBy(xpath = "//input[@title='אג\"ח להמרה לא סחיר | 2800']")
    public WebElement TatAfik;
    @FindBy(xpath = "//span[@title='אג\"ח להמרה לא סחיר']")
    public WebElement Afik;
    @FindBy(xpath = "//input[@title='איסתא ליינס חברת נסיעות בישראל בע\"מ | 1036']")
    public WebElement Manpik;
    @FindBy(xpath = "//input[@title='Energy | 42']")
    public WebElement Anaf;
    @FindBy(xpath = "//input[@title='ACADIAN']")
    public WebElement IssuingCountry;
    @FindBy(xpath = "//input[@title='אפריקה ישראל']")
    public WebElement tradingCountry;
    @FindBy(xpath = "//input[@title='אירופה']")
    public WebElement exposureState;
    @FindBy(xpath = "//option[@value='10000']")
    public WebElement GeographicArea;
    @FindBy(xpath = "//input[@title='בדיקה | 1']")
    public WebElement Borsa;
    @FindBy(xpath = "//input[@title='בזק | 10']")
    public WebElement Konzrn;
    @FindBy(xpath = "//input[@title='הלוואה לבדיקה אורי 1 | 113113']")
    public WebElement Property;
    @FindBy(xpath = "//input[@title='5']")
    public WebElement baseAssetMultiplier;
    @FindBy(xpath = "//input[@title='\"עו\"\"ש מט\"\"ח בארץ - לדוח אוצר\" | 28600000']")
    public WebElement coinExposure;
    @FindBy(xpath = "//option[@value='23']")
    public WebElement TypeForRiskManagement;
    @FindBy(xpath = "//input[contains(@title,'להמרה-אחר | 28')]")
    public WebElement AttachmentType;
    @FindBy(xpath = "//table[@class='table table-hover mb-0 ng-star-inserted']/tbody/tr/td")
    public WebElement firstObligation;

    public void goToExtraDetailsTab(){
        extraDetailsTab.click();
    }

    public void assets(String firstValue) throws InterruptedException, IOException, CsvException {
        List<WebElement> formFields = driver.findElements(By.tagName("autocomplete-field"));
        WebElement[] formArray = formFields.stream().toArray(WebElement[]::new);
        Thread.sleep(1000);
        formArray[0].findElement(By.xpath("./*/*/input")).sendKeys("נייר" + firstValue);
        clickTheFirstAsset();
        //formArray[1].findElement(By.xpath("./*/*/input")).sendKeys("נייר" + firstValue);

    }
    public void clickTheFirstAsset() {
        WebElement tableAsset = driver.findElement(By.className("table")); // Assuming this is the table containing assets
        List<WebElement> rows = tableAsset.findElements(By.tagName("tr"));
        if (!rows.isEmpty()) {
            WebElement firstRowAsset = rows.get(0); // First row
            List<WebElement> cells = firstRowAsset.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                WebElement firstClickableElement = cells.get(0); // First cell
                firstClickableElement.click();
            } else {
                System.out.println("No cells found in the first row of the asset table.");
            }
        } else {
            System.out.println("No rows found in the asset table.");
        }
    }

    public void Obligation(String firstValue) throws InterruptedException {
        List<WebElement> formFields = driver.findElements(By.tagName("autocomplete-field"));
        WebElement[] formArray = formFields.stream().toArray(WebElement[]::new);
        Thread.sleep(1000);
        formArray[1].findElement(By.xpath("./*/*/input")).sendKeys("נייר" + firstValue);
        Thread.sleep(1000);
        //firstObligation.click();
    }

    public void table() throws InterruptedException {
        List<WebElement> formFields = driver.findElements(By.tagName("table"));
        WebElement[] formArray = formFields.stream().toArray(WebElement[]::new);
        //formArray[0].findElement(By.xpath("./tbody/tr/td")).click();
        formArray[1].findElement(By.xpath("./tbody/tr/td")).click();

    }


    public void ChooseTheFirstAsset() throws InterruptedException, IOException, CsvException {
        extraDetailsTab.click();
        List<String[]> allRows = readCSV();
        if (allRows.size() >= 2) {
            String[] secondRow = allRows.get(1);
            String firstValue = secondRow[0];
            assets(firstValue);
            Thread.sleep(1000);
            allRows.remove(1);
            removeFromCSV(allRows);
        }
    }
    public void clickTheFirstObligation() throws InterruptedException, IOException {
        List<WebElement> formFields = driver.findElements(By.tagName("autocomplete-field"));
        WebElement tableObligation = formFields.get(1).findElement(By.className("table")); // Assuming this is the table containing obligations
        List<WebElement> rows = tableObligation.findElements(By.tagName("tr"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("or"));

        if (!rows.isEmpty()) {
            WebElement firstRow = rows.get(0); // First row
            List<WebElement> cells = firstRow.findElements(By.tagName("td"));
            if (!cells.isEmpty()) {
                WebElement firstClickableElement = cells.get(0);// First cell
                String x =firstClickableElement.getText();
                writer.write(x);

                writer.close();

                firstClickableElement.click();
            } else {
                System.out.println("No cells found in the first row of the obligation table.");
            }
        } else {
            System.out.println("No rows found in the obligation table.");
        }
    }



    public void ChooseTheFirstObligation() throws InterruptedException, IOException, CsvException {
        extraDetailsTab.click();
        List<String[]> allRows = readCSV();
        if (allRows.size() >= 2) {
            String[] secondRow = allRows.get(1);
            String firstValue = secondRow[0];
            Obligation(firstValue);
            clickTheFirstObligation();
            Thread.sleep(1000);
            allRows.remove(1);
            removeFromCSV(allRows);
        }
    }
        public void removeFromCSV(List<String[]> allRows) throws IOException {
            String csvFilePath = "S:\\Danel_HB_022023\\Assets_for_automation\\Assets.csv";
            CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath));
            csvWriter.writeAll(allRows);
            csvWriter.close();
        }

        public List<String[]> readCSV() throws IOException, CsvException {
            String csvFilePath = "S:\\Danel_HB_022023\\Assets_for_automation\\Assets.csv";
            CSVReader csvReader = new CSVReader(new FileReader(csvFilePath));
            List<String[]> allRows = csvReader.readAll();
            csvReader.close();
            return allRows;
        }
    public String getTatAfik(){
        return TatAfik.getAttribute("value");
    }
    public String getAfik(){
        return Afik.getText();
    }
    public String getManpik(){
        return Manpik.getAttribute("value");
    }
    public String getAnaf(){
        return Anaf.getAttribute("value");
    }
    public String getIssuingCountry(){
        return IssuingCountry.getAttribute("value");
    }
    public String getTradingCountry(){
        return tradingCountry.getAttribute("value");
    }
    public String getExposureState(){
        return exposureState.getAttribute("value");
    }
    public String getGeographicArea(){
        return GeographicArea.getText();
    }
    public String getBorsa(){
        return Borsa.getAttribute("value");
    }
    public String getKonzrn(){
        return Konzrn.getAttribute("value");
    }
    public String getProperty(){
        return Property.getAttribute("value");
    }
    public String getBaseAssetMultiplier(){
        return baseAssetMultiplier.getAttribute("value");
    }
    public String getCoinExposure(){
        return coinExposure.getAttribute("value");
    }
    public String getTypeForRiskManagement(){
        return TypeForRiskManagement.getText();
    }
    public String getAttachmentType(){
        return AttachmentType.getAttribute("value");
    }
    }

































