package calculator.function;

import java.math.BigDecimal;

public class MaxFunction extends AbstractFunction {

    public MaxFunction(Integer minParamNumb, Integer maxParamNumb) {
        super(minParamNumb, maxParamNumb);
    }

    @Override
    public BigDecimal calculate(BigDecimal... params) {

        checkNumbOfParams(params);

        BigDecimal max = params[0];
        for (BigDecimal param : params) {
            if (param.compareTo(max) > 0)
                max = param;
        }
        return max;
    }
}
