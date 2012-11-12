package calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class BinaryOperatorTest {

    private static final MathExpressionCalculator calculator =
            new FiniteStateMachineCalculator();

    @Test
    public void priorityOperatorTest() throws Exception {

        final BigDecimal result = calculator.evaluate("2+3*4");
        assertEquals("Priority of operators isn't calculated correctly.",
                new BigDecimal(14), result);
    }

}
