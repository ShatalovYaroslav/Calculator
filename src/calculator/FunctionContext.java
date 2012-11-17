package calculator;

import calculator.function.Function;
import calculator.stateMachine.FiniteMachineContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FunctionContext implements FiniteMachineContext<
        MachineState, BigDecimal> {

    private final Function func;
    private List<BigDecimal> params = new ArrayList<BigDecimal>();

    private MachineState state;

    public FunctionContext(Function func) {
        this.func = func;
    }

    @Override
    public MachineState getState() {
        return state;
    }

    @Override
    public void setState(MachineState state) {
        this.state = state;
    }

    public void addParams(BigDecimal par){
        params.add(par);
    }

    @Override
    public BigDecimal getResult() {

        BigDecimal res =
                func.calculate((BigDecimal[]) params.toArray());
        return res;
    }
}
