package utilities;

import drivers.Driver;
import enums.ConfigProperties;
import factories.DriverFactory;
import objectRepository.HomePage;
import objectRepository.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import reports.Loggers;

public class BaseClass {

    protected WebDriver driver = null;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite(groups = {"SMOKE", "SANITY"})
    public void bsConfig() {
        Loggers.info(BaseClass.class, () -> "====== DB Connection Successful ======");
    }

    @BeforeClass(alwaysRun = true)
    @Parameters("xmlbrowser")
    public void bcConfig(@Optional String xmlbrowser, ITestContext context) throws Exception {

        if (xmlbrowser != null) {
            driver = Driver.initDriver(xmlbrowser, DriverFactory.browserVersion);
        } else {
            String browser = PropertyUtils.get(ConfigProperties.BROWSER);
            driver = Driver.initDriver(browser, DriverFactory.browserVersion);
        }

    }

    @BeforeMethod(groups = {"SMOKE", "SANITY"})
    public void bmConfig() {
        String username = PropertyUtils.get(ConfigProperties.USERNAME);
        String password = PropertyUtils.get(ConfigProperties.PASSWORD);

        LoginPage lp = new LoginPage(driver);
        lp.loginToApp(username, password);
        Loggers.info(BaseClass.class, () -> "====== Login to App Successful ======" );

    }

    @AfterMethod(groups = {"SMOKE", "SANITY"})
    public void amConfig() {
       HomePage hp = new HomePage(driver);
        hp.logOutOfApp(driver);
        Loggers.info(BaseClass.class, () -> "====== Logout of App Successful ======" );
    }

    @AfterClass(groups = {"SMOKE", "SANITY"})
    public void acConfig() {
        // driver.quit();
        Driver.quitDriver();
        Loggers.info(BaseClass.class, () -> "====== Browser Closed ======" );
    }

    @AfterSuite(groups = {"SMOKE", "SANITY"})
    public void asConfig() {
        Loggers.info(BaseClass.class, () ->"====== DB Connection Closed ======");
    }
}
