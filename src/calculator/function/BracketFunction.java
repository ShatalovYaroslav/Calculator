package calculator.function;

import java.math.BigDecimal;

public class BracketFunction extends AbstractFunction {

    public BracketFunction(Integer minParamNumb, Integer maxParamNumb) {
        super(minParamNumb, maxParamNumb);
    }

    @Override
    public BigDecimal calculate(BigDecimal... params) {

        checkNumbOfParams(params);

        return params[0];
    }
}