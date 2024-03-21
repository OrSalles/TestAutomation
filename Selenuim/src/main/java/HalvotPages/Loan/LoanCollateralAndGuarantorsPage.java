package HalvotPages.Loan;

import HalvotPages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoanCollateralAndGuarantorsPage extends BasePage {
    public LoanCollateralAndGuarantorsPage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "//popover-form-modal/div/onboarding/div/div/onboarding-tabs/div/*/*/*/*/*/ul/li/a/span/b[contains(text(),'בטחונות וערבים')]")
    public WebElement CollateralAndGuarantorsTab;
    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[@title='בדיקה'][contains(text(),'בדיקה')]")
    public WebElement SafeDetail;
    @FindBy(xpath = "//div[@title='אנרגיה']")
    public WebElement SafeType;

    public void goToCollateralAndGuarantorsTab(){
        CollateralAndGuarantorsTab.click();
    }

    public String getSafeDetail(){
        return SafeDetail.getText();
    }
    public String getSafeType(){
        return SafeType.getText();
    }

}
