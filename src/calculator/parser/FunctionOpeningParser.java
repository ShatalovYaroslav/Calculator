package calculator.parser;

import calculator.EvaluationContext;
import calculator.function.Function;
import calculator.function.FunctionFactory;

public class FunctionOpeningParser extends AbstractParser {

    @Override
    public boolean parse(EvaluationContext context) {

        skipTransparentCharacters(context);

        final FunctionFactory factory =
                context.getFunctionFactory();

        final String remainingPartOfExpression =
                context.getMathExpression().substring(
                        context.getCurrentPosition());

        for (String representation : factory.getFunctionRepresentations()) {
            if (remainingPartOfExpression.startsWith(representation)) {

                context.setCurrentPosition(context.getCurrentPosition() +
                        representation.length());

                final Function func =
                        factory.create(representation);

                context.createFunctionContext(func);

                return true;
            }
        }

        return false;
    }
}
