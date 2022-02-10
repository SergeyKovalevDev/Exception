public class HandlerException extends Exception {

    public HandlerException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Handler of sale results error: " + super.getMessage();
    }
}
