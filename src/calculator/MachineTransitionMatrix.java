package calculator;

import calculator.stateMachine.TransitionMatrix;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static calculator.MachineState.*;
import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;

public class MachineTransitionMatrix implements TransitionMatrix<MachineState> {

    private static final Map<MachineState, Set<MachineState>>
            TRANSITIONS = new HashMap<MachineState, Set<MachineState>>() {{
        put(START, of(NUMBER, FUNCTION_OPENING));
        put (FUNCTION_OPENING, of(FUNCTION_PARAM, FUNCTION_CLOSING));
        put(FUNCTION_PARAM, of(FUNCTION_SEPARATOR, FUNCTION_CLOSING));
        put(FUNCTION_SEPARATOR, of(FUNCTION_PARAM));
        put (FUNCTION_CLOSING, of(BINARY_OPERATOR, FINISH));

        put(NUMBER, of(BINARY_OPERATOR, FINISH));
        put(BINARY_OPERATOR, of(NUMBER, FUNCTION_OPENING));
        put(FINISH, noneOf(MachineState.class));
    }};

    @Override
    public MachineState getStartState() {
        return START;
    }

    @Override
    public MachineState getFinishState() {
        return FINISH;
    }

    @Override
    public Set<MachineState> getPossibleTransitions(MachineState machineState) {
        return TRANSITIONS.get(machineState);
    }
}
