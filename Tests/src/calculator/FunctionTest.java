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
        final BigDecimal result = calculator.evaluate(" (((1))) ");
        assertEquals("simple brackets wasn't evaluated correctly.",
                new BigDecimal(1), result);
    }

    @Test
    public void sumAndMultiplyTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" 2 * sum (4+1, 3) ");
        assertEquals("summing function and multipling  wasn't evaluated correctly.",
                new BigDecimal(16), result);
    }

    @Test
    public void minFunctionTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" min(-7, 1,2, -10) ");
        assertEquals("min function wasn't evaluated correctly.",
                new BigDecimal(-10), result);
    }

    @Test
    public void sqrtFunctionTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" sqrt(100) ");
        assertEquals("sqrt function wasn't evaluated correctly.",
                BigDecimal.valueOf(Math.sqrt(100.0)), result);
    }

    @Test
    public void PiFunctionTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" pi() ");
        assertEquals("pi function wasn't evaluated correctly.",
                BigDecimal.valueOf(Math.PI), result);
    }

    @Test
    public void sumComplexTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" sum (4, 3-7, sum(1, 3)) - 4 ");
        assertEquals("summing nested function wasn't evaluated correctly.",
                new BigDecimal(0), result);
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
                    20, e.getErrorPosition());
        }
    }

    @Test
    public void differentFunctionsTest() throws Exception {
        final BigDecimal result = calculator.evaluate(" min( sum (4.5, 3), 10/(7+3), max(1.7, 3)) * sqrt(4) ");
        assertEquals("the case with different functions wasn't evaluated correctly.",
                BigDecimal.valueOf(2.0), result);
    }
}
