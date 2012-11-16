package calculator;

import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class BinaryOperatorTest {

    private static final MathExpressionCalculator calculator =
            new FiniteStateMachineCalculator();


    @Test
    public void simpleMultiplicationTest() throws Exception {
           final BigDecimal res = calculator.evaluate(" 7 * 5 ");
        assertEquals("Multiplication of numbers isn't calculeted correctly",
                new BigDecimal(35), res);
    }

    @Test
    public void simpleAdditionTest() throws Exception {
        final BigDecimal res = calculator.evaluate(" 10+7 ");
        assertEquals("Addition of numbers isn't calculeted correctly",
                new BigDecimal(17), res);
    }

    @Test
    public void simpleSubtractionTest() throws Exception {
        final BigDecimal res = calculator.evaluate(" 5 - 9 ");
        assertEquals("Multiplication of numbers isn't calculeted correctly",
                new BigDecimal(-4), res);
    }

    @Test
    public void multiplicationExceptionTest() throws Exception {

        try {
            calculator.evaluate("30 * ");
            fail("Expected exception wasn't thrown.");
        } catch (EvaluationException e) {
            assertEquals("Wrong error position reported.",
                    5, e.getErrorPosition());
        }
    }

    @Test
    public void priorityOperatorTest() throws Exception {

        final BigDecimal result = calculator.evaluate("2+3*4");
        assertEquals("Priority of operators isn't calculated correctly.",
                new BigDecimal(14), result);
    }

    @Test
    public void simpleDivisionTest() throws Exception {
        final BigDecimal res = calculator.evaluate(" 10 / 5 ");
        assertEquals("Division of numbers isn't calculated correctly",
                new BigDecimal(2), res);
    }

    @Test(expected = ArithmeticException.class)
    public void divisionByZeroException() throws Exception {
        calculator.evaluate(" 4 / 0 ");
        fail("Expected exception wasn't thrown.");
    }

    @Test
    public void simpleExponentiationTest() throws Exception {
        BigDecimal res = calculator.evaluate(" 10 ^ 1.5 ");
        BigDecimal expect = new BigDecimal(31.62278);
        expect = expect.setScale(5, BigDecimal.ROUND_HALF_DOWN);
        assertEquals("Exponentiation of numbers isn't calculated correctly",
                expect, res.setScale(5, BigDecimal.ROUND_HALF_DOWN));
    }

}
