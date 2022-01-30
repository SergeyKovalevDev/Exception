import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class SaleResultsHandler {

    private String columnName = "cost";

    public static void taskStarter() throws FileNotFoundException {
        SaleResultsHandler handler = new SaleResultsHandler();
        handler.setColumnName("cost");

        for (String filename :
                new String[]{
                        "report.txt",
                        null,
                        "report_missingFile.txt",
                        "report_emptyFile.txt",
                        "report_headerError.txt",
                        "report_errorInString.txt"}) {
            System.out.println(handler.fileHandler(filename));
        }
    }

    public String fileHandler(String filename) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(filename));
        String[] headers = scanner.nextLine().split(";");
        int columnIndex = getIndexOfColumn(headers, columnName);
        int reportSum = 0;
        while (scanner.hasNextLine()) {
            String[] stringOfReportArr = scanner.nextLine().split(";");
            reportSum += Integer.parseInt(stringOfReportArr[columnIndex]);
        }
        scanner.close();
        return "Report amount in file: \"" + filename + "\" = " + reportSum;
    }

    private int getIndexOfColumn(String[] headers, String columnName) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equals(columnName)) return i;
        }
        return -1;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
}
