package calculator;

import calculator.function.Function;
import calculator.function.FunctionFactory;
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

    private final FunctionFactory functionFactory =
            new FunctionFactory();


    private final String mathExpression;
    private int currentPosition = 0;

    private final Deque<BigDecimal> operandStack = new ArrayDeque<BigDecimal>();
    private final Deque<BinaryOperator> operatorStack = new ArrayDeque<BinaryOperator>();
    private final Deque<FunctionContext> functionContextStack = new ArrayDeque<FunctionContext>();

    private MachineState state;

    public EvaluationContext(String mathExpression) {

        if (mathExpression == null) {
            throw new NullPointerException("Math expression is null.");
        }

        this.mathExpression = mathExpression;
    }

    public BinaryOperatorFactory getBinaryOperatorFactory() {
        return binaryOperatorFactory;
    }

    public FunctionFactory getFunctionFactory() {
        return functionFactory;
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

        BigDecimal result = binaryOperator.calculate(left, right);
        addOperand(result);
    }

    public void addOperand(BigDecimal operand) {
        operandStack.push(operand);
    }

    public void addOperator(BinaryOperator operator) {

        if (!functionContextStack.isEmpty()) {
            Integer sizeOperators = functionContextStack.peek().getSizeOperatorsForParameters();
            if (sizeOperators != null) {
                while (!operatorStack.isEmpty() &&
                        sizeOperators != operatorStack.size() &&
                        operator.compareTo(operatorStack.peek()) <= 0) {
                    final BinaryOperator binaryOperator =
                            operatorStack.pop();
                    applyOperator(binaryOperator);
                }
            }
        } else {
            while (!operatorStack.isEmpty() &&
                    operator.compareTo(operatorStack.peek()) <= 0) {
                applyOperator(operatorStack.pop());
            }
        }

        operatorStack.push(operator);
    }

    public void addFunction(Function func) {

        FunctionContext functContext = new FunctionContext(func);

        functContext.setSizeOperatorsForParameters(operatorStack.size());
        functContext.setSizeOperandsForParameters(operandStack.size());

        functionContextStack.push(functContext);
    }

    public void applyParameterOfFunction() {

        if (functionContextStack.isEmpty())
            throw new IllegalStateException("The separator is used without function");

        FunctionContext functionContext = functionContextStack.peek();
        Integer sizeStackOperators = functionContext.getSizeOperatorsForParameters();

        if (sizeStackOperators == null)
            throw new IllegalStateException("The separator is used without parameter");

        while (operatorStack.size() != sizeStackOperators) {
            applyOperator(operatorStack.pop());
        }

        if (operandStack.isEmpty())
            throw new IllegalStateException("Illegal parameter of function");

        final BigDecimal res = operandStack.pop();
        functionContext.addParameterValue(res);

    }

    public void applyFunction() {

        FunctionContext funcCont = functionContextStack.peek();
        Integer operandSizeStack = funcCont.getSizeOperandsForParameters();

        if (operandSizeStack == null)
            throw new IllegalStateException("Illegal number of operands on closing function");

        if (operandSizeStack != operandStack.size())
            applyParameterOfFunction();

        BigDecimal res = funcCont.getResult();
        functionContextStack.pop();
        operandStack.push(res);
    }
}
