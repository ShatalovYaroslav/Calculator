package calculator.parser;

import calculator.EvaluationContext;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

public class NumberParser extends AbstractParser {

    private static final NumberFormat NUMBER_PARSER =
            new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    @Override
    public boolean parse(EvaluationContext context) {

        skipTransparentCharacters(context);

        final ParsePosition parsePosition =
                new ParsePosition(context.getCurrentPosition());
        final String expression = context.getMathExpression();

        final Number number = NUMBER_PARSER.parse(expression, parsePosition);

        if (parsePosition.getErrorIndex() < 0) {
            context.addOperand(new BigDecimal(number.doubleValue()));
            context.setCurrentPosition(parsePosition.getIndex());
            return true;
        }

        return false;
    }
}
