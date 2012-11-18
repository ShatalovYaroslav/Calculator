package calculator;

import calculator.function.Function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FunctionContext {

    private final Function func;
    private List<BigDecimal> params = new ArrayList<BigDecimal>();

    //parameter of function can include complex expression
    // for remembering start of it, is used size of operatorsStack
    private Integer sizeOperatorsForParameters;
    private Integer sizeOperandsForParameters;

    public FunctionContext(Function func) {
        this.func = func;
    }

    public void addParameterValue(BigDecimal par) {
        params.add(par);
    }

    public Integer getSizeOperatorsForParameters() {
        return sizeOperatorsForParameters;
    }

    public void setSizeOperatorsForParameters(Integer sizeOperatorsForParameters) {
        this.sizeOperatorsForParameters = sizeOperatorsForParameters;
    }

    public Integer getSizeOperandsForParameters() {
        return sizeOperandsForParameters;
    }

    public void setSizeOperandsForParameters(Integer sizeOperandsForParameters) {
        this.sizeOperandsForParameters = sizeOperandsForParameters;
    }

    public BigDecimal getResult() {

        return func.calculate(params.toArray(new BigDecimal[0]));
    }
}
