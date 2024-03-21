package HalvotPages.Frame;

import HalvotPages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrameBorrowerPage extends BasePage {

    public FrameBorrowerPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "(//b[contains(text(),'לווים')])[3]")
    public WebElement borrowerTab;
    @FindBy(xpath = "(//div[@class='mt-2'])")
    public WebElement borrowerName;
    @FindBy(xpath = "//onboarding-field-readonly/*/span/*[@title='5']")
    public WebElement serialNumber;
    @FindBy(xpath = "//option[@value='0']")
    public WebElement contact;
    @FindBy(xpath = "//option[contains(text(),'ייי - 052336666 (סלולר)')]")
    public WebElement borrowerPhone;
    @FindBy(xpath = "//option[normalize-space()='miamigmail.com']")
    public WebElement borrowerEmail;

    public String getBorrowerName(){
        return borrowerName.getText();
    }
    public String getSerialNumber(){
        return serialNumber.getText();
    }
    public String getContact(){
        return contact.getText();
    }
    public String getBorrowerPhone(){
        return borrowerPhone.getText();
    }
    public String getBorrowerEmail(){
        return borrowerEmail.getText();
    }

    public void goToBorrowerTab() throws InterruptedException {
        borrowerTab.click();
        Thread.sleep(1000);
    }

}
