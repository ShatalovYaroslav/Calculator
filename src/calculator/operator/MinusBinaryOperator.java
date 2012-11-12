package calculator.operator;

import java.math.BigDecimal;

public class MinusBinaryOperator extends AbstractBinaryOperator {

    public MinusBinaryOperator(int priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {

        checkOperands(left, right);
        return left.subtract(right);
    }
}
