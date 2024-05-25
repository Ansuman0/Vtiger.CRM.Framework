package listeners;

import enums.ConfigProperties;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utilities.PropertyUtils;

/**
 * Implements {@link IRetryAnalyzer}. Helps in rerunning the failed tests.
 */
public class RetryAnalyzerImpl implements IRetryAnalyzer {
    private int count=0;
    private int retries = 2;
    /**
     * Return true when needs to be retried and false otherwise.
     * Maximum will retry for one time.
     * Retry will happen if user desires to and set the value in the property file
     */
    @Override
    public boolean retry(ITestResult result) {
        boolean value =false;

        if(PropertyUtils.get(ConfigProperties.RETRYFAILEDTESTS).equalsIgnoreCase("yes")) {
            value = count<=retries ;
            count++;
        }
        return value;
    }
}
