package HalvotPages.Loan;

import HalvotPages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoanExtraDetailsPage extends BasePage {
    public LoanExtraDetailsPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//popover-form-modal/div/onboarding/div/div/onboarding-tabs/div/*/*/*/*/*/ul/li/a/span/b[contains(text(),'פרטים נוספים')]")
    public WebElement ExtraDetailsTab;
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
    public WebElement TradingCountry;
    @FindBy(xpath = "//input[@title='אירופה']")
    public WebElement ExposureState;
    @FindBy(xpath = "//option[@value='10000']")
    public WebElement GeographicArea;
    @FindBy(xpath = "//input[@title='בדיקה | 1']")
    public WebElement Borsa;
    @FindBy(xpath = "//input[@title='בזק | 10']")
    public WebElement Konzrn;
    @FindBy(xpath = "//input[@title='הלוואה לבדיקה אורי 1 | 113113']")
    public WebElement Property;
    @FindBy(xpath = "//input[@title='5']")
    public WebElement BaseAssetMultiplier;
    @FindBy(xpath = "//input[@title='\"עו\"\"ש מט\"\"ח בארץ - לדוח אוצר\" | 28600000']")
    public WebElement CoinExposure;
    @FindBy(xpath = "//option[@value='23']")
    public WebElement TypeForRiskManagement;
    @FindBy(xpath = "//input[contains(@title,'להמרה-אחר | 28')]")
    public WebElement AttachmentType;

    public void goToExtraDetailsTab() throws InterruptedException {
        //tabs();
        Thread.sleep(2000);
        ExtraDetailsTab.click();
    }
    public void tabs() throws InterruptedException {
        List<WebElement> formTabs = driver.findElements(By.tagName("popover-form-modal"));
        WebElement[] formArray = formTabs.stream().toArray(WebElement[]::new);
            formArray[1].findElement(By.xpath("./div/onboarding/div/div/onboarding-tabs/div/*/*/*/*/*/ul/li/a/span/b")).click();
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
        return TradingCountry.getAttribute("value");
    }
    public String getExposureState(){
        return ExposureState.getAttribute("value");
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
        return BaseAssetMultiplier.getAttribute("value");
    }
    public String getCoinExposure(){
        return CoinExposure.getAttribute("value");
    }
    public String getTypeForRiskManagement(){
        return TypeForRiskManagement.getText();
    }
    public String getAttachmentType(){
        return AttachmentType.getAttribute("value");
    }


}
