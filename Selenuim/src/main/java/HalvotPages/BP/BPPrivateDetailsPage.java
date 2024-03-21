package HalvotPages.BP;

import HalvotPages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Utils.RandomID.generateRandomID;

public class BPPrivateDetailsPage extends BasePage {
    public BPPrivateDetailsPage(WebDriver driver){
        super(driver);
    }

    String baseName = "שם פרטי";
    String randomID = generateRandomID();

    String FirstNameWithRandomID = baseName + randomID;
    @FindBy(xpath = "//div[@class='h1 container mb-1 pt-3 p-2 ng-star-inserted']")
    public WebElement PrivateBPTitle;
    @FindBy(xpath = "//*[@data-fieldid=\"3\"]//input[@class=\"form-control border border-secondary leftAlignForced rightAlignForced ng-pristine ng-valid ng-touched\"]")
    public WebElement FirsName;
    @FindBy(xpath = "//*[@data-fieldid=\"4\"]//input[@class=\"form-control border border-secondary leftAlignForced rightAlignForced ng-pristine ng-valid ng-touched\"]")
    public WebElement SecondName;
    @FindBy(xpath = "//*[@data-fieldid=\"5\"]//input[@class=\"form-control border border-secondary leftAlignForced rightAlignForced ng-pristine ng-valid ng-touched\"]")
    public WebElement LastName;
    @FindBy(xpath = "//*[@data-fieldid=\"7\"]//input[@class=\"form-control border border-secondary leftAlignForced rightAlignForced ng-pristine ng-valid ng-touched\"]")
    public WebElement NickName;
    @FindBy(xpath = "//*[@data-fieldid=\"9\"]//input[@class=\"form-control border border-secondary leftAlignForced rightAlignForced ng-pristine ng-valid ng-touched\"]")
    public WebElement FatherName;
    @FindBy(xpath = "//select[@aria-label='מין']")
    public WebElement Gender;


    public String getPrivateBPTitle(){
        return PrivateBPTitle.getText();
    }
}
