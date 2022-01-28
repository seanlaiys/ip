package alfred;

public class InvalidDateException extends AlfredException {

    private static final String INVALID_DATE = "Please try again with a valid date and time DD/MM/YYYY HHMM";

    public InvalidDateException() {
        super(INVALID_DATE);
    }
}
