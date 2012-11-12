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
        put(START, of(NUMBER));
        put(NUMBER, of(BINARY_OPERATOR, FINISH));
        put(BINARY_OPERATOR, of(NUMBER));
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
