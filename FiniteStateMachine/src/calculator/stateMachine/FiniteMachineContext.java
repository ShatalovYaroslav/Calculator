package calculator.stateMachine;

public interface FiniteMachineContext<
        State extends Enum,
        Result> {

    State getState();

    void setState(State state);

    Result getResult();
}
