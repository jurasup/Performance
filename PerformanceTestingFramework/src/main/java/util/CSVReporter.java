package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Provides functionality for tests reporting in CSV file.
 * @author Yury_Suponeu
 */
public class CSVReporter extends Reporter {
    public static final String SEPARATOR = ",";
    private static final String WRITER_ERROR = "Error: Can't report test log!";
    private static final String DATE_TIME_FORMAT = "yyyyMMdd_HHmmss";
    private static final String HEADER = "INDEX,BROWSER,VERSION,MIN(s),AVERAGE(s),90% (s)" + System.lineSeparator();
    private static final String REPORT_FOLDER = "Reports";

    @Override
    public void report(List<String> log) throws IOException {
        BufferedWriter writer = null;
        try {
            File reportDir = new File(REPORT_FOLDER);
            if (!reportDir.exists()){
                reportDir.mkdir();
            }
            String timeLog = new SimpleDateFormat(DATE_TIME_FORMAT).format(Calendar.getInstance().getTime());
            File logFile = new File(REPORT_FOLDER + "/" + timeLog + ".csv");
            writer = new BufferedWriter(new FileWriter(logFile));
            writer.write(HEADER);
            for (String line : log){
                writer.write(line);
            }
        } catch (Exception e) {
            throw new IOException(WRITER_ERROR);
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
            }
        }
    }
}
