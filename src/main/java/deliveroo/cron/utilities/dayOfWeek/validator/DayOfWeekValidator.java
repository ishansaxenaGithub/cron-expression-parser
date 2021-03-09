package deliveroo.cron.utilities.dayOfWeek.validator;

import deliveroo.cron.constants.ParserConstants;
import deliveroo.cron.exception.CronParseException;

public class DayOfWeekValidator {
    final private String SLASH_PATTERN_REGEX = "^\\*\\/([1-7])";
    final private String SINGLE_DAYOFWEEK_REGEX= "(^0+)?([1-7])";
    final private String COMMA_PATTERN_REGEX = "^([1-7])((,[1-7])?)*$";
    final private String HYPHEN_PATTERN_REGEX = "^([1-7])(-[1-7])$";

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
                throw new CronParseException("Day of the Week expression unsupported.Correct range 1-7 and expression should be incremental");
            }
            return;
        }
        else if(expression.matches(SINGLE_DAYOFWEEK_REGEX)){
            return;
        }
        else{
            throw new CronParseException("Day of the Week expression unsupported. Allowed Symbols /*-, (1-7)");
        }
    }
}
