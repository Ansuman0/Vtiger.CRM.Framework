package reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentTest;

/**
 * ExtentManager
 *
 * @author Ansuman
 * @see drivers.Driver
 */
public class ExtentManager {

    /**
     * Private constructor to avoid external instantiation
     */
    private ExtentManager() {
    }

    private static final ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

    /**
     * Returns the thread safe {@link ExtentTest}
     * instance fetched from ThreadLocal variable.
     *
     * @return Thread safe {@link ExtentTest} instance.
     * @author Ansuman
     */
    static ExtentTest getExtentTest() {
        return extTest.get();
    }

    /**
     * Set the {@link ExtentTest} instance to thread
     * local variable
     *
     * @param test {@link ExtentTest} instance that
     *             needs to saved from Thread safety issues.
     *             <p>
     * @author Ansuman
     */
    static void setExtentTest(ExtentTest test) {
        if (Objects.nonNull(test)) {
            extTest.set(test);
        }
    }

    /**
     * Calling remove method on Threadlocal variable ensures to set the default
     * value to Threadlocal variable. It is much safer than assigning null value to
     * ThreadLocal variable.
     *
     * @author Ansuman
     */
    static void unload() {
        extTest.remove();
    }
}
