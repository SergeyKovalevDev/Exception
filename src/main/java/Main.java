public class Main {
    public static void main(String[] args) {
        taskStarter();
    }

    private static void taskStarter() {
        try {
            System.out.println(new HandlerSaleResults("report.txt").fileReading());
//            System.out.println(new HandlerSaleResults("report_headerError.txt").fileReading());
//            System.out.println(new HandlerSaleResults("report_errorInString.txt").fileReading());
        } catch (HandlerException e) {
            System.out.println(e.getMessage());
        }
    }
}
