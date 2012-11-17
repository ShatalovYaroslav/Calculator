package calculator.function;

import java.math.BigDecimal;

abstract public class AbstractFunction
        implements Function {

    private final Integer minParamNumb;
    private final Integer maxParamNumb;

    protected AbstractFunction(Integer minParamNumb, Integer maxParamNumb) {
        this.minParamNumb = minParamNumb;
        this.maxParamNumb = maxParamNumb;
    }

    protected void checkNumbOfParams(BigDecimal... params) {

        if (params == null) {
            throw new NullPointerException("Parameters is null.");
        }

        if (params.length > maxParamNumb ||
                params.length < minParamNumb) {
            throw new IllegalArgumentException("Wrong number of function parameters");
        }
    }
}
