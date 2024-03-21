package HalvotPages.BP;

import HalvotPages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BPTablePage extends BasePage {
    public BPTablePage(WebDriver driver){
        super(driver);
    }
    @FindBy(xpath = "(//a[normalize-space()='BP'])[1]")
    public WebElement BPButton;
    @FindBy(xpath = "//a[contains(text(),'הוספה +')]")
    public WebElement AddButton;
    @FindBy(xpath = "//div[@class='dropdown-menu show']//a[@class='dropdown-item'][contains(text(),'פרטי')]")
    public WebElement PrivateBPButton;

}
