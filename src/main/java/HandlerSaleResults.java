import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HandlerSaleResults {
    private final String filename;

    public HandlerSaleResults(String filename) {
        this.filename = filename;
    }

    public String  fileReading() throws HandlerException {
        try {
            Scanner scanner = new Scanner(new File(filename));
            String[] headers = scanner.nextLine().split(";");
            if (headers.length != 3) throw new HandlerException("Error in the file header " + filename);
            int reportSum = 0;
            while (scanner.hasNextLine()) {
                String reportString = scanner.nextLine();
                String[] array = reportString.split(";");
                try {
                    reportSum += Integer.parseInt(array[2]);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    throw new HandlerException("The string " + reportString +
                            " is not recognized. The string is not included in the report!");
                }
            }
            scanner.close();
            return "Report amount: " + reportSum;
        } catch (FileNotFoundException e) {
            throw new HandlerException("File not found");
        }
    }
}
