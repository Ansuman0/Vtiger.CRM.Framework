package importExportExcelFile;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.time.Duration;

import static constants.FrameworkConstants.EXPORTEXCELFOLDERPATH;

public class ExportExcelFilePath {
    private ExportExcelFilePath() {}

    public static void downloadFloder(String 𝐟𝐢𝐥𝐞𝐧𝐚𝐦𝐞){

        File file = new File(EXPORTEXCELFOLDERPATH);

        FluentWait<File> wait = new FluentWait<File>(file)
                .withTimeout(Duration.ofMinutes(5))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(Exception.class).withMessage("file is not downloaded");


        try {
            boolean isDownloaded = wait.until(f -> f.exists() && f.canRead());

            if (isDownloaded) {
                System.out.println("file is completely 100% downloaded");
            }

        } catch (TimeoutException e) {
            System.out.println("file is not completely  downloaded");

        }



    }



}
