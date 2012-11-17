package calculator;

import calculator.function.Function;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FunctionContext {

    private final Function func;
    private List<BigDecimal> params = new ArrayList<BigDecimal>();
    private final Deque<Integer> parametersStack = new ArrayDeque<Integer>();

    public FunctionContext(Function func) {
        this.func = func;
    }

    public void addParameterValue(BigDecimal par) {
        params.add(par);
    }

    public Deque<Integer> getParametersStack() {
        return parametersStack;
    }

    public BigDecimal getResult() {

        return func.calculate((BigDecimal[]) params.toArray());
    }
}
