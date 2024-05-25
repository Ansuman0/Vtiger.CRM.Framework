package vtiger.OrganizationsTests;

import annotation.FrameworkAnnotation;
import enums.CategoryType;
import listeners.ListenerClass;
import objectRepository.HomePage;
import objectRepository.OrganizationsPage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import randomdataUtils.RandomUtilsImplements;
import utilities.BaseClass;


@Listeners(ListenerClass.class)
public class CreateOrgWithIndustry extends BaseClass {

    @FrameworkAnnotation(author = {"Ansuman",}, category = {CategoryType.MINIREGRESSION, CategoryType.SANITY})
    @Test(groups = {"SMOKE", "SANITY"})
    public void createOrgWithIndTest() throws Exception {

        String orgname = RandomUtilsImplements.getCompanyName();

        String industryType = "Engineering";// can use data-provide for multipule data & run.

        HomePage hp = new HomePage(driver);
        hp.clickOnOrganizationsLink();

        OrganizationsPage op = new OrganizationsPage(driver);

        op.clickOnLeadLookUpImage();
        op.createOrganizationWithIndustry(driver, orgname, industryType);

        String orgHeader = op.getHeader();
        Assert.assertTrue(orgHeader.contains(orgname));

    }

}
