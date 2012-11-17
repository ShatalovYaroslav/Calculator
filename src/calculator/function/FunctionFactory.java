package calculator.function;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FunctionFactory {

    private static final Map<String, Function> functions =
            new HashMap<String, Function>() {{
                put("sum", new SummingFunction(2, Integer.MAX_VALUE));
            }};

    private static final String openBracket = new String("(");
    private static final String closeBracket = new String("(");
    private static final String separator = new String(",");

    public Function create(String functionRepresentation) {
        final Function findFunc =
                functions.get(functionRepresentation);
        if (findFunc == null)  {
            throw new IllegalStateException(
                    "Function not found: " + functionRepresentation);
            }
        return findFunc;
    }

    public Set<String> getFunctionRepresentations() {
        return functions.keySet();
    }

    public static String getCloseBracket() {
        return closeBracket;
    }

    public static String getOpenBracket() {
        return openBracket;
    }

    public static String getSeparator() {
        return separator;
    }
}
