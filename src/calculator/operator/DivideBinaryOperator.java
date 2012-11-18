package calculator.operator;

import java.math.BigDecimal;

public class DivideBinaryOperator extends AbstractBinaryOperator {

    public DivideBinaryOperator(int priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {

        checkOperands(left, right);
        if (BigDecimal.ZERO == right) {
            throw new IllegalArgumentException("Divide by zero");
        }
        return left.divide(right);
    }
}
