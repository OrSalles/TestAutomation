package HalvotPages.Loan;

import HalvotPages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class LoanDocumentsPage extends BasePage {
    public LoanDocumentsPage(WebDriver driver){
        super(driver);
    }
    String path = "X:\\פריוריטי\\Or Baron\\1.docx";
    String date = "10/12/2023";
    @FindBy(xpath = "//popover-form-modal/div/onboarding/div/div/onboarding-tabs/div/*/*/*/*/*/ul/li/a/span/b[contains(text(),'מסמכים')]")
    public WebElement documentsTab;
    @FindBy(xpath = "//i[@data-addtype='InGrid']")
    public WebElement addDocumentsButton;
    @FindBy(xpath = "(//input[@type='file'])[1]")
    public WebElement uploadFileButton;
    @FindBy(xpath = "//select[@aria-label='סוג מסמך']")
    public WebElement documentsType;
    @FindBy(xpath = "//select[@aria-label='תדירות תוקף']")
    public WebElement frequency;



    public String getDocumentsTab(){
        return documentsTab.getText();
    }

    public void FrameDocumentsDates(String date) {
        List<WebElement> formDates = driver.findElements(By.tagName("form-date-combo-2"));
        WebElement[] formArray = formDates.stream().toArray(WebElement[]::new);
        formArray[0].findElement(By.xpath("./div/mat-form-field/*/*/*/input")).sendKeys(date);
        formArray[1].findElement(By.xpath("./div/mat-form-field/*/*/*/input")).sendKeys(date);
        formArray[2].findElement(By.xpath("./div/mat-form-field/*/*/*/input")).sendKeys(date);

    }
    public void FrameDocumentsFields(String path) throws InterruptedException {
        Thread.sleep(2000);
        uploadFileButton.sendKeys(path);
        Thread.sleep(5000);
        Select dropdown= new Select(documentsType);
        dropdown.selectByIndex(1);
        Select dropdown1= new Select(frequency);
        dropdown1.selectByIndex(1);
    }

    public void uploadDocument() throws InterruptedException {
        Thread.sleep(1000);
        documentsTab.click();
        addDocumentsButton.click();
        FrameDocumentsFields(path);
        Thread.sleep(5000);
        FrameDocumentsDates(date);
    }
}
