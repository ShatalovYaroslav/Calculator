package calculator.function;

import java.math.BigDecimal;

public class SummingFunction extends AbstractFunction{

    public SummingFunction(Integer minParamNumb, Integer maxParamNumb) {
        super(minParamNumb, maxParamNumb);
    }

    @Override
    public BigDecimal calculate(BigDecimal... params) {

        checkNumbOfParams(params);

        BigDecimal sum = new BigDecimal(0);
        for (BigDecimal param : params) {
            sum = sum.add(param);
        }
        return sum;
    }
}
