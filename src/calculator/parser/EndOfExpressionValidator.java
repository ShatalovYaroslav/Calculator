package calculator.parser;

import calculator.EvaluationContext;

public class EndOfExpressionValidator extends AbstractParser {

    @Override
    public boolean parse(EvaluationContext context) {

        skipTransparentCharacters(context);

        return context.getMathExpression().length()
                == context.getCurrentPosition();
    }
}
