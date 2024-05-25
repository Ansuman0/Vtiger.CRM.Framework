package objectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import randomdataUtils.RandomUtilsImplements;
import webDrievrActions.WebDriverUtility;

import java.util.Map;


public class OpportunityPage {

    // Opportunity Information:
    @FindBy(xpath = "//img[@title='Create Opportunity...']")
    private WebElement createOpportunityLookUpImg;

    @FindBy(xpath = "//span[contains(text(), 'Creating New Opportunity')]")
    private WebElement opportunitFormHeader;

    @FindBy(xpath = "//input[@name='potentialname']")
    private WebElement opportunityNameEdt;

    @FindBy(xpath = "//select[@id='related_to_type']")
    private WebElement relatedToDrp;

    @FindBy(xpath = "//input[@name='related_to_display']/following-sibling::Img[@title='Select']")
    private WebElement relatedToDrpValueLookUpImg; // Related To values based on dropDown
    // Selection(Organiztions,Contacts)
    /*
     * This will open another windows where we can open Organization
     */
    @FindBy(name = "search_text")
    private WebElement orgSearchEdt;// common elements so used same button

    @FindBy(name = "search")
    private WebElement orgSearchBtn;// common elements so used same button

    @FindBy(xpath = "//select[@name='opportunity_type']")
    private WebElement opportunityTypeDrp;

    @FindBy(name = "leadsource")
    private WebElement leadSourceDrp;

    @FindBy(xpath = "//select[@name='assigned_user_id']") // have to select Based on Value as ts Madatoryfield**
    private WebElement assignedToDrp;

    @FindBy(xpath = "//input[@name='campaignname']/following-sibling::Img[@title='Select']")
    private WebElement campaignSourceLookUpImg; // Campaign Source

    @FindBy(name = "amount")
    private WebElement amount;

    @FindBy(name = "closingdate")
    private WebElement expectedCloseDate;

    @FindBy(name = "sales_stage")
    private WebElement salesStageDrp;

    @FindBy(xpath = "//textarea[@class='detailedViewTextBox']")
    private WebElement descriptionEdt;

    @FindBy(xpath = "//input[@title='Save [Alt+S]']")
    private WebElement saveBtn;

    @FindBy(xpath = "//span[@class='dvHeaderText']")
    private WebElement createdOpportunityHeaderTitel;

    // initialization
    public OpportunityPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickOnOpportunityLookUpImage() {
        createOpportunityLookUpImg.click();
    }

    public String getOpportunitiesPageHeader() {
        return opportunitFormHeader.getText();
    }

    public String getCreatedOpportunityHeader() {
        return createdOpportunityHeaderTitel.getText();
    }

    // WebDriverUtility

    public void createOpportunityFullDetails(WebDriver driver, Map<String, String> opportunity) throws Exception {

        String opportunityName = opportunity.get("opportunityName");
        String commonValue = opportunity.get("commonValue");
        String campaignsName = opportunity.get("campaignsName");
        String closeDate = opportunity.get("closeDate");
        String relatedToDrpvalue = opportunity.get("relatedToDrpvalue");

        opportunityNameEdt.sendKeys(opportunityName);
        
        WebDriverUtility.handleDropDown(relatedToDrp, relatedToDrpvalue);// Organizations
        if (relatedToDrpvalue.contentEquals("Accounts")) {
            relatedToDrpValueLookUpImg.click();
            WebDriverUtility.switchToWindow(driver, relatedToDrpvalue);
            orgSearchEdt.sendKeys(commonValue);// common elements so used same button
            orgSearchBtn.click();// common elements so used same button
            driver.findElement(By.xpath("//a[.='" + commonValue + "']")).click();
            WebDriverUtility.switchToWindow(driver, "Potentials");// opportunity page.
        } else {
            relatedToDrpValueLookUpImg.click();
            WebDriverUtility.switchToWindow(driver, "Contacts");
            orgSearchEdt.sendKeys(commonValue);// common elements so used same button
            orgSearchBtn.click();// common elements so used same button
            // driver.findElement(By.xpath("//a[.='" + commonValue + "']")).click();
            driver.findElement(By.xpath("//a[contains(., '" + commonValue + "')]")).click();
            WebDriverUtility.switchToWindow(driver, "Potentials");// opportunity page.
        }

        WebDriverUtility.handleDropDown(opportunityTypeDrp, "Existing Business");

        /*
         * I have Taken static Value ,If I require dynamic value can use data Provider.
         * Based on the related Dropdown value we are selecting this . This will switch
         * windows and move Campaign window and select Created Org. Base on the Created
         * Campaign we're passing this value.
         */
        WebDriverUtility.handleDropDown(leadSourceDrp, "Cold Call");
        campaignSourceLookUpImg.click();
        WebDriverUtility.switchToWindow(driver, "Campaigns");// campaigns
        orgSearchEdt.sendKeys(campaignsName);// common elements so used same button
        orgSearchBtn.click(); // common elements so used same button
        driver.findElement(By.xpath("//a[.='" + campaignsName + "']")).click();
        WebDriverUtility.switchToWindow(driver, "Potentials");// opportunity page.
        expectedCloseDate.clear();
        expectedCloseDate.sendKeys(closeDate);

        /*
         * I have Taken static Value ,If require dynamic value can use data Provider.
         */

        WebDriverUtility.handleDropDown(salesStageDrp, "Prospecting"); // If Required enable.

        saveBtn.click();
    }

    public void createOpportunityWithContactOrOrg(WebDriver driver, Map<String, String> opportunityDetails)
            throws Exception {

        String opportunityname = opportunityDetails.get("opportunityname");
        String relatedDrp = opportunityDetails.get("relatedDrp");
        String commonValue = opportunityDetails.get("commonValue");
        String closeDate = opportunityDetails.get("closeDate");
        String description = opportunityDetails.get("description");

        opportunityNameEdt.sendKeys(opportunityname);
        WebDriverUtility.handleDropDown(relatedToDrp, relatedDrp);

        if (relatedDrp.contentEquals("Accounts")) {
            relatedToDrpValueLookUpImg.click();
            WebDriverUtility.switchToWindow(driver, "Accounts");
            orgSearchEdt.sendKeys(commonValue);// common elements so used same button
            orgSearchBtn.click();// common elements so used same button
            Thread.sleep(4000);

            driver.findElement(By.xpath("//a[.='" + commonValue + "']")).click();
            WebDriverUtility.switchToWindow(driver, "Potentials");// opportunity page.
        } else {
            relatedToDrpValueLookUpImg.click();
            WebDriverUtility.switchToWindow(driver, "Contacts");
            orgSearchEdt.sendKeys(commonValue);// common elements so used same button
            orgSearchBtn.click();// common elements so used same button
            driver.findElement(By.xpath("//a[contains(., '" + commonValue + "')]")).click();
            WebDriverUtility.switchToWindow(driver, "Potentials");// opportunity page.
        }

        amount.sendKeys(RandomUtilsImplements.getPrice());
        expectedCloseDate.clear();
        expectedCloseDate.sendKeys(closeDate);
        descriptionEdt.sendKeys(description);

        saveBtn.click();
        Thread.sleep(3000);
        driver.navigate().refresh();
    }

}
