/*
package vtiger.OrganizationsTests;

import annotation.FrameworkAnnotation;
import enums.CategoryType;
import listeners.ListenerClass;
import objectRepository.HomePage;
import objectRepository.OrganizationsPage;
import org.junit.jupiter.api.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import randomdataUtils.RandomUtilsImplements;
import utilities.BaseClass;
import utilities.ExcelUtils;


@Listeners(ListenerClass.class)
public class CreateMultipleOrganizationWithInd extends BaseClass {

    @FrameworkAnnotation(author = {"Ansuman"}, category = {CategoryType.REGRESSION})
    @Test(dataProvider = "getData", invocationCount = 1)
    public void createOrgWithIndTest(String industryType) throws Exception {

        String orgname = RandomUtilsImplements.getCompanyName();

        */
/*
         * String industryType = "Engineering";// can use data-provide for multipule
         * data & run.
         *//*

        HomePage hp = new HomePage(driver);
        hp.clickOnOrganizationsLink();

        OrganizationsPage op = new OrganizationsPage(driver);

        op.clickOnLeadLookUpImage();

        op.createOrganizationWithIndustry(driver, orgname, industryType);

        String orgHeader = op.getHeader();
        Assert.assertTrue(orgHeader.contains(orgname));

    }

    @DataProvider(parallel = false)
    public Object[][] getData() {
        return ExcelUtils.readMultipleData("IndustryType");
    }

}
*/
