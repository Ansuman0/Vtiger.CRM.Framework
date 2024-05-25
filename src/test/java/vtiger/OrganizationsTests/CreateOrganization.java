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
public class CreateOrganization extends BaseClass {

    @FrameworkAnnotation(author = {"Ansuman"}, category = {CategoryType.SMOKE, CategoryType.REGRESSION})
    @Test(groups = {"SMOKE", "REGRESSION"})
    public void createOrganizationTest() throws Exception {
        String organizationName = RandomUtilsImplements.getCompanyName();

        HomePage hp = new HomePage(driver);
        hp.clickOnOrganizationsLink();

        OrganizationsPage op = new OrganizationsPage(driver);

        op.clickOnLeadLookUpImage();
        op.createOrganization(driver, organizationName);

        String orgHeader = op.getHeader();
        Assert.assertTrue(orgHeader.contains(organizationName));
        System.out.println("Orgabization Name Matched : " + orgHeader);

    }

}
