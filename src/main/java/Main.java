public class Main {
    public static void main(String[] args) {
        taskStarter();
    }

    private static void taskStarter() {
        SaleResultsHandler handler = new SaleResultsHandler();
        handler.setColumnName("cost");

        String[] filenames = new String[] {
                "report.txt",
                null,
                "report_missingFile.txt",
                "report_emptyFile.txt",
                "report_headerError.txt",
                "report_errorInString.txt"};
        for (String filename : filenames) {
            try {
                System.out.println(handler.fileHandler(filename));
            } catch (HandlerException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
