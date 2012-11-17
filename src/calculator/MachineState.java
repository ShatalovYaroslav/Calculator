package calculator;

public enum MachineState {
    START,
    NUMBER,
    BINARY_OPERATOR,
    FUNCTION_OPENING,
    FUNCTION_PARAM,
    FUNCTION_SEPARATOR,
    FUNCTION_CLOSING,
    FINISH
}
