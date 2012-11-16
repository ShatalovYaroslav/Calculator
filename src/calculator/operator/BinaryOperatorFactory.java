package calculator.operator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BinaryOperatorFactory {

    private static final Map<String, BinaryOperator> operators =
            new HashMap<String, BinaryOperator>() {{
                put("+", new PlusBinaryOperator(1));
                put("-", new MinusBinaryOperator(1));
                put("*", new MultiplyBinaryOperator(2));
                put("/", new DivideBinaryOperator(2));
                put("^", new PowerBinaryOperator(2));
            }};

    public BinaryOperator create(String operatorRepresentation) {
        final BinaryOperator binaryOperator =
                operators.get(operatorRepresentation);
        if (binaryOperator == null) {
            throw new IllegalStateException(
                    "Operator not found: " + operatorRepresentation);
        }
        return binaryOperator;
    }

    public Set<String> getOperatorRepresentations() {
        return operators.keySet();
    }
}
