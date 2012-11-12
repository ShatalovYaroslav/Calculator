package calculator.stateMachine;

import java.util.Set;

public interface TransitionMatrix<State extends Enum> {

    State getStartState();

    State getFinishState();

    Set<State> getPossibleTransitions(State state);
}
