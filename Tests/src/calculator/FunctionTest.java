package calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class FunctionTest {

    private static final MathExpressionCalculator calculator =
            new FiniteStateMachineCalculator();

    @Test
    public void sumFunctionTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" sum(1,2) ");
        assertEquals("summing function wasn't evaluated correctly.",
                new BigDecimal(3), result);
    }

    @Test
    public void simpleBracketsTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" (1) ");
        assertEquals("summing function wasn't evaluated correctly.",
                new BigDecimal(3), result);
    }

    @Test
    public void sumAndMultiplyTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" 2 * sum (1, 3) ");
        assertEquals("summing function wasn't evaluated correctly.",
                new BigDecimal(3), result);
    }

    @Test
    public void functionParameterExceptionTest() throws Exception {

        try {
            calculator.evaluate("sum( 12, )");
            fail("Expected exception wasn't thrown.");
        } catch (EvaluationException e) {
            assertEquals("Wrong error position reported.",
                    9, e.getErrorPosition());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void wrongNumberParametersExceptionTest() throws Exception {
        calculator.evaluate(" sum(1) ");
        fail("Expected exception wasn't thrown.");
    }

    @Test
    public void complexParameterExceptionTest() throws Exception {

        try {
            calculator.evaluate("sum( 12, sum(1,3 ), ");
            fail("Expected exception wasn't thrown.");
        } catch (EvaluationException e) {
            assertEquals("Wrong error position reported.",
                    7, e.getErrorPosition());
        }
    }

}
