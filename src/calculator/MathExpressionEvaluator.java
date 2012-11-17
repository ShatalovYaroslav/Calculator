package calculator;

import calculator.parser.*;
import calculator.stateMachine.StateRecognizer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static calculator.MachineState.*;

public class MathExpressionEvaluator
        implements StateRecognizer<MachineState,
        BigDecimal,
        EvaluationException,
        EvaluationContext> {

    private static final Map<MachineState, MathExpressionParser>
            PARSERS = new HashMap<MachineState, MathExpressionParser>() {{
        put(START, new StartEvaluationValidator());
        put(NUMBER, new NumberParser());
        put(BINARY_OPERATOR, new BinaryOperatorParser());
        put(FUNCTION_OPENING, new FunctionOpeningParser());
        put(FUNCTION_SEPARATOR, new FunctionSeparatorParser());
        put(FUNCTION_CLOSING, new FunctionClosingParser());
        put(FINISH, new EndOfExpressionValidator());
    }};


    @Override
    public boolean accept(EvaluationContext context, MachineState possibleState)
            throws EvaluationException {

        final MathExpressionParser parser = PARSERS.get(possibleState);
        if (parser == null) {
            throw new IllegalStateException("Parser not found for state: " + possibleState);
        }

        return parser.parse(context);
    }
}
