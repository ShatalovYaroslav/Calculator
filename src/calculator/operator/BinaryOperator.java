package calculator.operator;

import java.math.BigDecimal;

public interface BinaryOperator extends Comparable<BinaryOperator> {
    BigDecimal calculate(BigDecimal left, BigDecimal right);
}
