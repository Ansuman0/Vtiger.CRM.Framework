package objectRepository;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import webDrievrActions.WebDriverUtility;

import java.io.File;
import java.time.Duration;

import static constants.FrameworkConstants.EXPORTEXCELFOLDERPATH;


public class OrganizationsPage {

    @SuppressWarnings("unused")
    private WebDriverUtility webDriverUtility;

    @FindBy(xpath = "//*[contains(@alt,'Create Organization...')]") // img[@alt='Create Organization...']
    private WebElement createOrgLookUpImg;

    @FindBy(name = "accountname")
    private WebElement orgNameEdt;

    @FindBy(xpath = "//*[contains(@name, 'industry')]")
    private WebElement industryDropDwn;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;

    // @FindBy(xpath = "//*[contains(@class,'dvHeaderText')]")
    @FindBy(css = "[class='dvHeaderText']")
    private WebElement orgFromHeaderText;

    @FindBy(xpath = "//img[@src='themes/softed/images/tbarExport.gif']")
    private WebElement exportOrgExcel;

    @FindBy(xpath = "//input[@name='Export']")
    private WebElement exportOrgExcelBtn;

    // initializations
    public OrganizationsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Business Library
    public void clickOnLeadLookUpImage() {
        createOrgLookUpImg.click();
    }

    public WebElement getCreatedOrgHeaderText() {
        return orgFromHeaderText;
    }

    public void createOrganization(WebDriver driver, String orgname) {
        orgNameEdt.sendKeys(orgname);
        saveBtn.click();
        WebDriverUtility.fluentWait(driver, orgFromHeaderText);

    }

    public void createOrganizationWithIndustry(WebDriver driver, String orgname, String industryType) {
        createOrgLookUpImg.click();
        orgNameEdt.sendKeys(orgname);
        WebDriverUtility.handleDropDown(industryDropDwn, industryType);
        saveBtn.click();
        WebDriverUtility.fluentWait(driver, orgFromHeaderText);

    }

    public String getHeader() {
        return getCreatedOrgHeaderText().getText();
    }

    public void exportOrgdateToExcel(WebDriver driver){
        WebDriverUtility.waitForElementToBeVisisble(driver,exportOrgExcel);
        exportOrgExcel.click();

        String downloadBtnName = exportOrgExcelBtn.getText();
        System.out.println(downloadBtnName);

        WebDriverUtility.waitForElementToBeVisisble(driver,exportOrgExcelBtn);
        exportOrgExcelBtn.click();

        File file = new File(EXPORTEXCELFOLDERPATH,downloadBtnName);

        FluentWait<File> wait = new FluentWait<File>(file)
                .withTimeout(Duration.ofMinutes(5))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(Exception.class).withMessage("file is not downloaded");

        try {
            boolean isDownloaded = wait.until(f -> f.exists() && f.canRead());

            if (isDownloaded) {
                System.out.println("file is completely 100% downloaded");
            }

        } catch (TimeoutException e) {
            System.out.println("file is not completely  downloaded");

        }


    }

}
