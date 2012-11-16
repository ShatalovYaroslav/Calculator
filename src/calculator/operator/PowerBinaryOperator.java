package calculator.operator;

import java.math.BigDecimal;


public class PowerBinaryOperator extends AbstractBinaryOperator {

    public PowerBinaryOperator(int priority) {
        super(priority);
    }

    @Override
    public BigDecimal calculate(BigDecimal left, BigDecimal right) {

        checkOperands(left, right);
        //conversion for the construction a real power
        return BigDecimal.valueOf(Math.pow(left.doubleValue(), right.doubleValue()));
    }
}