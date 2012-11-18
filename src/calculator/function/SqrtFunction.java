package calculator.function;

import java.math.BigDecimal;

public class SqrtFunction extends AbstractFunction {

    public SqrtFunction(Integer minParamNumb, Integer maxParamNumb) {
        super(minParamNumb, maxParamNumb);
    }

    @Override
    public BigDecimal calculate(BigDecimal... params) {

        checkNumbOfParams(params);

        return BigDecimal.valueOf(Math.sqrt(params[0].doubleValue()));
    }
}
