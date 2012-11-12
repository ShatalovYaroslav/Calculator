package calculator;

public class EvaluationException extends Exception {

    final private int errorPosition;

    public EvaluationException(String message, int errorPosition) {
        super(message);
        this.errorPosition = errorPosition;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public int getErrorPosition() {
        return errorPosition;
    }
}


