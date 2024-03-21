package HalvotPages.Frame;

import HalvotPages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrameContactsPage extends BasePage {

    public FrameContactsPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "(//b[contains(text(),'אנשי קשר')])[2]")
    public WebElement contactsTab;
    @FindBy(xpath = "//td[contains(@aria-label,'is template cell column header מספר סידורי')]")
    public WebElement serialNumber;
    @FindBy(xpath = "//div[@class='mt-2']")
    public WebElement contactName;
    @FindBy(xpath = "//option[contains(text(),'fff - 05222222 (סלולר)')]")
    public WebElement contactPhone;
    @FindBy(xpath = "//option[normalize-space()='fff@gmail.com']")
    public WebElement contactEmail;
    @FindBy(xpath = "//option[contains(text(),'מנהל כספים')]")
    public WebElement contactJob;

    public void goToContactsTab(){
        contactsTab.click();
    }

    public String getContactName(){
        return contactName.getText();
    }
    public String getSerialNumber(){
        return serialNumber.getText();
    }
    public String getContactPhone(){
        return contactPhone.getText();
    }
    public String getContactEmail(){
        return contactEmail.getText();
    }
    public String getContactJob(){
        return contactJob.getText();
    }

}
