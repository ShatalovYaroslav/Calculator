package calculator.parser;

import calculator.EvaluationContext;

public class StartEvaluationValidator
        extends AbstractParser {

    @Override
    public boolean parse(EvaluationContext context) {
        return context.getCurrentPosition() == 0;
    }
}
