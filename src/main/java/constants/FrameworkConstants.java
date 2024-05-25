package constants;


import enums.ConfigProperties;
import utilities.PropertyUtils;

public class FrameworkConstants {

    private FrameworkConstants() {
    }

    /**
     * Common Path for the all keep in single Place
     */
    private static final int EXPLICITWAIT = 10;
    private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
    private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
    private static final String JSONCONFIGFILEPATH = RESOURCESPATH + "/config/config.json";
    private static final String EXCELPATH = RESOURCESPATH + "/excel/testdata.xlsx";
    private static final String RUNMANGERSHEET = "RUNMANAGER";
    private static final String ITERATIONDATASHEET = "DATA";
    private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/test-reports-output/";
    private static String extentReportFilePath = "";
    public static final String EXPORTEXCELFOLDERPATH = System.getProperty("user.dir") + "/download-data/";
    public static final String exportExcelFilePath = "";

    /**
     * @return If Override reports value in the property file is no,then the
     * timestamp will be appended
     * @throws Exception
     * @author Ansuman
     */
    private static String createReportPath() {
        if (PropertyUtils.get(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
            return EXTENTREPORTFOLDERPATH + System.currentTimeMillis() + "/index.html";
        } else {
            return EXTENTREPORTFOLDERPATH + "/index.html";
        }
    }

    /*
     * @return Extent Report path where the index.html file will be generated.
     */
    public static String getExtentReportFilePath() {
        if (extentReportFilePath.isEmpty()) {
            extentReportFilePath = createReportPath();
        }
        return extentReportFilePath;
    }

    /*
     * (NeedtoDo) Lombak Plugin to remove the boilerplate code
     */
    public static int getExplicitwait() {
        return EXPLICITWAIT;
    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;
    }

    public static String getJsonconfigfilepath() {
        return JSONCONFIGFILEPATH;
    }

    public static String getExcelpath() {
        return EXCELPATH;
    }

    public static String getRunmangerDatasheet() {
        return RUNMANGERSHEET;
    }

    public static String getIterationDatasheet() {
        return ITERATIONDATASHEET;
    }
}
