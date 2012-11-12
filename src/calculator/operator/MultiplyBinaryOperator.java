package calculator.operator;

import java.math.BigDecimal;

public class MultiplyBinaryOperator extends AbstractBinaryOperator {

    public MultiplyBinaryOperator(int priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {

        checkOperands(left, right);
        return left.multiply(right);
    }
}
