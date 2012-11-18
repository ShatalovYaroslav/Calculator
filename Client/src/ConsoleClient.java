import calculator.EvaluationException;
import calculator.FiniteStateMachineCalculator;
import calculator.MathExpressionCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class ConsoleClient {

    final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    final MathExpressionCalculator calculator = new FiniteStateMachineCalculator();

    public ConsoleClient() {

        System.out.println("Math Expression Calculator");
        String expression;
        try {
            while ((expression = reader.readLine()).compareTo("exit") != 0) {
                try {
                    final BigDecimal result;
                    result = calculator.evaluate(expression);
                    System.out.println("The result of evaluation: "
                            + result);
                } catch (EvaluationException e) {
                    System.err.println("The error: " +
                            e.getMessage() + " at position " + e.getErrorPosition());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
