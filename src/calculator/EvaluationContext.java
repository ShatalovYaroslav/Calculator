package calculator;

import calculator.operator.BinaryOperator;
import calculator.operator.BinaryOperatorFactory;
import calculator.stateMachine.FiniteMachineContext;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluationContext implements FiniteMachineContext<
        MachineState, BigDecimal> {

    private final BinaryOperatorFactory binaryOperatorFactory =
            new BinaryOperatorFactory();

    private final String mathExpression;
    private int currentPosition = 0;

    private final Deque<BigDecimal> operandStack = new ArrayDeque<BigDecimal>();
    private final Deque<BinaryOperator> operatorStack = new ArrayDeque<BinaryOperator>();
    private final Deque<Integer> bracketStack = new ArrayDeque<Integer>();


    private MachineState state;

    public EvaluationContext(String mathExpression) {


        // ((((((((((((((((((
        bracketStack.push(operatorStack.size());

        // ))))))))))))))))))
        final Integer requiredSize = bracketStack.pop();
        while (operatorStack.size() != requiredSize) {
            applyOperator(operatorStack.pop());
        }

        if (mathExpression == null) {
            throw new NullPointerException("Math expression is null.");
        }

        this.mathExpression = mathExpression;
    }

    public BinaryOperatorFactory getBinaryOperatorFactory() {
        return binaryOperatorFactory;
    }

    public void skipWhitespaces() {
        while (currentPosition < mathExpression.length() &&
                Character.isWhitespace(mathExpression.charAt(currentPosition))) {
            currentPosition++;
        }
    }


    public String getMathExpression() {
        return mathExpression;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public MachineState getState() {
        return state;
    }

    @Override
    public void setState(MachineState state) {
        this.state = state;
    }

    @Override
    public BigDecimal getResult() {

        while (!operatorStack.isEmpty()) {
            final BinaryOperator binaryOperator = operatorStack.pop();
            applyOperator(binaryOperator);
        }

        if (operandStack.size() != 1) {
            throw new IllegalStateException("There should only single item " +
                    "in the operand stack but: " + operandStack);
        }

        return operandStack.pop();
    }

    private void applyOperator(BinaryOperator binaryOperator) {
        final BigDecimal right = operandStack.pop();
        final BigDecimal left = operandStack.pop();

        final BigDecimal result =
                binaryOperator.calculate(left, right);

        addOperand(result);
    }

    public void addOperand(BigDecimal operand) {
        operandStack.push(operand);
    }

    public void addOperator(BinaryOperator operator) {

        while (!operatorStack.isEmpty() &&
                operator.compareTo(operatorStack.peek()) < 0) {
            final BinaryOperator binaryOperator =
                    operatorStack.pop();
            applyOperator(binaryOperator);
        }

        operatorStack.push(operator);
    }
}
