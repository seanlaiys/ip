package alfred;

public class EmptyInputException extends AlfredException {

    private static final String NO_DESCRIPTION = "Please try again with a description in mind ☹";

    public EmptyInputException() {
        super(NO_DESCRIPTION);
    }
}
