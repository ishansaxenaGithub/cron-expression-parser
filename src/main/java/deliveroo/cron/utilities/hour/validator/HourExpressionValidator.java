package deliveroo.cron.utilities.hour.validator;

import deliveroo.cron.constants.ParserConstants;
import deliveroo.cron.exception.CronParseException;

public class HourExpressionValidator {


    final private String SLASH_PATTERN_REGEX = "^\\*\\/([1-9]|1[0-9]|2[1-3])";
    final private String SINGLE_HOUR_REGEX= "(^0+)?([0-9]|1[0-9]|2[1-3])";
    final private String COMMA_PATTERN_REGEX = "^([0-9]|1[0-9]|2[1-3])((,([0-9]|1[0-9]|2[1-3]))?)*$";
    final private String HYPHEN_PATTERN_REGEX = "^([0-9]|1[0-9]|2[1-3])(-([0-9]|1[0-9]|2[1-3]))$";

    public void validate(final String expression) throws CronParseException {
        if(expression.equals("*")) {
            return;
        }
        if(expression.matches(SLASH_PATTERN_REGEX)){
            return;
        }
        else if(expression.matches(COMMA_PATTERN_REGEX)){
            return;
        }
        else if(expression.matches(HYPHEN_PATTERN_REGEX)){
            String expressionArgs[] = expression.split(ParserConstants.HYPHEN);
            int from = Integer.valueOf(expressionArgs[0]);
            int to = Integer.valueOf(expressionArgs[1]);
            if(from>to){
                throw new CronParseException("Hour expression unsupported.Correct range 0-23 and expression should be incremental");
            }
            return;
        }
        else if(expression.matches(SINGLE_HOUR_REGEX)){
            return;
        }
        else{
            throw new CronParseException("Hour expression unsupported. Allowed Symbols /*-, (0-23)");
        }
    }
}
