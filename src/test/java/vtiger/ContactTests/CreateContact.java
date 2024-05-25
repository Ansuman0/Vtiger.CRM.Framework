package vtiger.ContactTests;

import annotation.FrameworkAnnotation;
import enums.CategoryType;
import listeners.ListenerClass;
import objectRepository.ContactsPage;
import objectRepository.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import randomdataUtils.RandomUtilsImplements;
import reports.Loggers;
import utilities.BaseClass;

@Listeners(ListenerClass.class)
public class CreateContact extends BaseClass {

    @FrameworkAnnotation(author = {"Ansuman"}, category = {CategoryType.SMOKE, CategoryType.SANITY})
    @Test(groups = {"SMOKE", "SANITY"})
    public void createContactTest() throws Exception {

        String firstName = RandomUtilsImplements.getFirstName();
        String lastname = RandomUtilsImplements.getLastName();

        HomePage hp = new HomePage(driver);
        hp.clickOnContactsLink();

        ContactsPage cp = new ContactsPage(driver);
        cp.clickOnCreateContactLookupImage();

        cp.createContact(driver, firstName, lastname);

        System.out.println("Name : " + lastname);

        String contactHeader = cp.getContactFormHeader();
        System.out.println(contactHeader);
        Assert.assertTrue(contactHeader.contains(lastname));
        Loggers.info(CreateContact.class, () -> "Contact Header Matched : " + lastname);

    }
}
