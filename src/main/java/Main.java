public class Main {
    public static void main(String[] args) {
        taskStarter();
    }

    private static void taskStarter() {
        try {
            System.out.println(new HandlerSaleResults(new String[]{
                    "report.txt",
                    "report_headerError.txt",
                    "report_errorInString.txt"}).fileReading());
        } catch (HandlerException e) {
            System.out.println(e.getMessage());
        }
    }
}
