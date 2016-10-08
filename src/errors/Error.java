package errors;

/**
 * BooXchange Project
 * Created by Al on 06-Oct-16.
 */
public class Error {

    private String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
