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

        //looking for representation of function
        for (String representation : factory.getFunctionRepresentations()) {

            if (compareRepresentation(context, representation)) {

                //case when we have not simple brackets, with function
                if (!FunctionFactory.getOpenBracket().equals(representation)) {
                    skipTransparentCharacters(context);

                    if (!compareRepresentation(context,
                            FunctionFactory.getOpenBracket())) {
                        return false;
                    }
                }

                final Function func =
                        factory.create(representation);

                context.addFunction(func);

                return true;
            }
        }


        return false;
    }
}
