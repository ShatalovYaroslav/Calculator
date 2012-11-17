package calculator.parser;

import calculator.EvaluationContext;
import calculator.function.FunctionFactory;

public class FunctionSeparatorParser extends AbstractParser {

    @Override
    public boolean parse(EvaluationContext context) {

        skipTransparentCharacters(context);

        final String separator = FunctionFactory.getSeparator();

        if (compareRepresentation(context, separator)) {
            context.applyParameterOfFunction();

            return true;
        }

        return false;
    }
}
