package calculator.parser;

import calculator.EvaluationContext;

public interface MathExpressionParser {
    boolean parse(EvaluationContext context);
}
