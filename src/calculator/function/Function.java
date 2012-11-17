package calculator.function;

import java.math.BigDecimal;

/**
 Function encapsulates logic of work with function and brackets
 */
public interface Function {
    BigDecimal calculate(BigDecimal ... params);
}
