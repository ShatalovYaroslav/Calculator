package calculator.parser;

import calculator.EvaluationContext;

abstract public class AbstractParser implements MathExpressionParser {

    protected void skipTransparentCharacters(EvaluationContext context) {
        context.skipWhitespaces();
    }

}
