package calculator.parser;

import calculator.EvaluationContext;
import calculator.FunctionContext;

public class FunctionParamParser {

    public class FunctionOpeningParser extends AbstractParser {

        @Override
        public boolean parse(EvaluationContext context) {

            skipTransparentCharacters(context);

            FunctionContext funcContext = context.getFunctionContext();




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

}
