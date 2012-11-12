package calculator.stateMachine;

public interface StateRecognizer<
        State extends Enum,
        Result,
        StateMachineError extends Exception,
        Context extends FiniteMachineContext<State, Result>> {

    boolean accept(Context context, State possibleState)
            throws StateMachineError;

}
