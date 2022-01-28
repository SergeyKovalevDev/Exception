import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class HandlerSaleResults {
    private final String[] filenames;

    public HandlerSaleResults(String[] filenames) {
        this.filenames = filenames;
    }

    public String  fileReading() throws HandlerException {
        StringBuilder retString = new StringBuilder();
        for (String filename : filenames) {
//        try {
            File file = null;//NullPointerException
            try {
                file = new File(filename);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }


            Scanner scanner = null;//FileNotFoundException
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            String header = null;
            try {
                header = scanner.nextLine();// NoSuchElementException – if no line was found
                                            // IllegalStateException – if this scanner is closed
            } catch (NoSuchElementException | IllegalStateException e) {
                e.printStackTrace();
            }

            String[] headers = new String[0]; // PatternSyntaxException – if the regular expression's syntax is invalid
            try {
                headers = header.split(";");
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
            }

//            if (headers.length != 3) throw new HandlerException("Error in the file header " + filenames);
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
            retString.append("Report amount in file: ")
                    .append(filename)
                    .append(" ")
                    .append(reportSum);
//        } catch (FileNotFoundException e) {
//            throw new HandlerException("File not found");
//        }
        }
        return retString.toString();
    }
}
