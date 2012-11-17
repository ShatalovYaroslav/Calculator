package calculator.parser;

import calculator.EvaluationContext;

abstract public class AbstractParser implements MathExpressionParser {

    protected void skipTransparentCharacters(EvaluationContext context) {
        context.skipWhitespaces();
    }

    protected boolean compareRepresentation(EvaluationContext context, String representation) {
        String remainingPartOfExpression =
                context.getMathExpression().substring(
                        context.getCurrentPosition());

        if (remainingPartOfExpression.startsWith(representation)) {
            context.setCurrentPosition(context.getCurrentPosition()
                    + representation.length());
            return true;
        }
        return false;
    }

}
