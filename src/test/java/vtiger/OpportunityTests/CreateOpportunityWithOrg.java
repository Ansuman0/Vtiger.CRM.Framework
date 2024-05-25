package vtiger.OpportunityTests;

import annotation.FrameworkAnnotation;
import enums.CategoryType;
import listeners.ListenerClass;
import objectRepository.HomePage;
import objectRepository.OpportunityPage;
import objectRepository.OrganizationsPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import randomdataUtils.RandomUtilsImplements;
import utilities.BaseClass;

import java.util.HashMap;
import java.util.Map;

@Listeners(ListenerClass.class)
public class CreateOpportunityWithOrg extends BaseClass {

    @FrameworkAnnotation(author = { "Ansuman" }, category = { CategoryType.SMOKE, CategoryType.REGRESSION })
    @Test(groups = { "SMOKE", "REGRESSION" })
    public void createOpportunityWithOrg() throws Exception {

        String opportunityName = RandomUtilsImplements.generateOpportunityName();
        String campaingName = RandomUtilsImplements.generateCampaignName();
        String description = RandomUtilsImplements.generateDescription();
        String closeDate = RandomUtilsImplements.getOpportunityCloseDate(7);

        String orgname = RandomUtilsImplements.getCompanyName();
        String industryType = "Engineering";// can use data-provide for multipule data & run.

        // Selecting Dropdown value for the organization(Accounts) /contact
        String relatedToDrp = "Accounts";// Manuly passing the data we can pass from dropdown.

        Map<String, String> opportunityDetails = new HashMap<>();
        opportunityDetails.put("opportunityname", opportunityName);
        opportunityDetails.put("commonValue", orgname);
        opportunityDetails.put("relatedDrp", relatedToDrp);
        opportunityDetails.put("closeDate", closeDate);
        opportunityDetails.put("description", description);

        // * For closed date we have to pass number of days on
        // getOpportunityCloseDate(7)

        HomePage hp = new HomePage(driver);
        hp.clickOnOrganizationsLink();

        // Create Organization
        OrganizationsPage op = new OrganizationsPage(driver);
        op.clickOnLeadLookUpImage();
        op.createOrganizationWithIndustry(driver, orgname, industryType);
        Thread.sleep(4000);


        // Navigate to Opportunity link and Create Opportunity
        hp.clickOnOpportunitiesLink();
        System.out.println("Navigate to Opportunities Link & Click on Opportunities.");

        OpportunityPage opp = new OpportunityPage(driver);
        /*
         * String opportunityPageHeader = opp.getOpportunitiesPageHeader();
         * Assert.assertTrue(opportunityPageHeader.contains(opportunityPageHeader));
         * System.out.println(opportunityPageHeader + "Its opportunityPageHeader ")
         */

        opp.clickOnOpportunityLookUpImage();
        System.out.println("Click on Opportunity image look up");
        String OpportunityFormTitel = opp.getOpportunitiesPageHeader();
        Assert.assertTrue(OpportunityFormTitel.contains(OpportunityFormTitel));
        System.out.println("Click on the Opportunity form to Create Opportunity");

        // Creating Opportunity with organization Name.
        opp.createOpportunityWithContactOrOrg(driver, opportunityDetails);
        System.out.println("Create Opportunity with Required data");
        String CretedOpportunity = opp.getCreatedOpportunityHeader();
        Assert.assertTrue(CretedOpportunity.contains(opportunityName));
        System.out.println("Creating Opportunity Name & Given Name is Same");

    }
}
