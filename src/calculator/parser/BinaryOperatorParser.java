package calculator.parser;

import calculator.EvaluationContext;
import calculator.operator.BinaryOperator;
import calculator.operator.BinaryOperatorFactory;

public class BinaryOperatorParser extends AbstractParser {

    @Override
    public boolean parse(EvaluationContext context) {

        skipTransparentCharacters(context);

        final BinaryOperatorFactory factory =
                context.getBinaryOperatorFactory();

        final String remainingPartOfExpression =
                context.getMathExpression().substring(
                        context.getCurrentPosition());

        for (String representation : factory.getOperatorRepresentations()) {
            if (remainingPartOfExpression.startsWith(representation)) {

                context.setCurrentPosition(context.getCurrentPosition() +
                        representation.length());

                final BinaryOperator binaryOperator =
                        factory.create(representation);

                context.addOperator(binaryOperator);

                return true;
            }
        }

        return false;
    }
}
