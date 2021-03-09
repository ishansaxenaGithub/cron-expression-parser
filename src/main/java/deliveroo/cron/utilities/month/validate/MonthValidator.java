package deliveroo.cron.utilities.month.validate;

import deliveroo.cron.constants.ParserConstants;
import deliveroo.cron.exception.CronParseException;

public class MonthValidator {



    final private String SLASH_PATTERN_REGEX = "^\\*\\/([1-9]|1[0-2])";
    final private String SINGLE_MONTH_REGEX= "(^0+)?([1-9]|1[0-2])";
    final private String COMMA_PATTERN_REGEX = "^([1-9]|1[0-2])((,([1-9]|1[0-2]))?)*$";
    final private String HYPHEN_PATTERN_REGEX = "^([1-9]|1[0-2])(-([1-9]|1[0-2]))$";

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
                throw new CronParseException("Month expression unsupported.Correct range 1-12 and expression should be incremental");
            }
            return;
        }
        else if(expression.matches(SINGLE_MONTH_REGEX)){
            return;
        }
        else{
            throw new CronParseException("Month expression unsupported. Allowed Symbols /*-, (1-12)");
        }
    }
}
