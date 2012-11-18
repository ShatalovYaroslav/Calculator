package calculator.function;

import java.math.BigDecimal;

public class MinFunction extends AbstractFunction {

    public MinFunction(Integer minParamNumb, Integer maxParamNumb) {
        super(minParamNumb, maxParamNumb);
    }

    @Override
    public BigDecimal calculate(BigDecimal... params) {

        checkNumbOfParams(params);

        BigDecimal min = params[0];
        for (BigDecimal param : params) {
            if (param.compareTo(min) < 0)
                min = param;
        }
        return min;
    }
}
