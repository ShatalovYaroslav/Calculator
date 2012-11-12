package calculator.operator;

import java.math.BigDecimal;

public class PlusBinaryOperator extends AbstractBinaryOperator {

    public PlusBinaryOperator(int priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {

        checkOperands(left, right);
        return left.add(right);
    }
}
