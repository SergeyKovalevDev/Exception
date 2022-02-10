import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class SaleResultsHandler {

    private String columnName = "cost";

    public String fileHandler(String filename) throws HandlerException {

        try {
            StringBuilder retString = new StringBuilder();
            Scanner scanner = new Scanner(new File(filename));
            String header = scanner.nextLine();
            String[] headers = header.split(";");
            int columnIndex;
            if ((columnIndex = getIndexOfColumn(headers, columnName)) == -1) {
                throw new HandlerException("Missing the \"" + columnName + "\" column in the file \"" + filename + "\".");
            }
            int reportSum = 0;
            while (scanner.hasNextLine()) {
                String stringOfReport = scanner.nextLine();
                String[] stringOfReportArr = stringOfReport.split(";");
                try {
                    reportSum += Integer.parseInt(stringOfReportArr[columnIndex]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new HandlerException("Missing value \"" + columnName + "\" in string \"" + stringOfReport + "\""
                            + ". The file \"" + filename + "\" has not been handled.");
                } catch (NumberFormatException e) {
                    throw new HandlerException("The value " + stringOfReportArr[columnIndex] + " is not a number.");
                }
            }
            scanner.close();
            retString.append("Report amount in file: \"")
                    .append(filename)
                    .append("\" = ")
                    .append(reportSum);
            return retString.toString();
        } catch (NullPointerException e) {
            throw new HandlerException("Missing file name.");
        } catch (FileNotFoundException e) {
            throw new HandlerException("File \"" + filename + "\" not found.");
        } catch (IllegalStateException e) {
            throw new HandlerException("File \"" + filename + "\" reading error.");
        } catch (PatternSyntaxException e) {
            throw new HandlerException("Internal file processing error.");
        } catch (NoSuchElementException e) {
            throw new HandlerException("File \"" + filename + "\" is empty.");
        }
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
