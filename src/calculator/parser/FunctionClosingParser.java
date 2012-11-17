package calculator.parser;

import calculator.EvaluationContext;
import calculator.function.FunctionFactory;

public class FunctionClosingParser extends AbstractParser {

    @Override
    public boolean parse(EvaluationContext context) {

        skipTransparentCharacters(context);

        final String clBracket = FunctionFactory.getCloseBracket();

        if (compareRepresentation(context, clBracket)) {
            context.applyFunction();

            return true;
        }

        return false;
    }
}