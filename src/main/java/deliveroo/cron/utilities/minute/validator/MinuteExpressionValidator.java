package deliveroo.cron.utilities.minute.validator;

import deliveroo.cron.constants.ParserConstants;
import deliveroo.cron.exception.CronParseException;

public class MinuteExpressionValidator {


    final private String SLASH_PATTERN_REGEX = "^\\*\\/([1-5]?[1-9])";
    final private String SINGLE_MINUTE_REGEX= "(^0+)?([1-5]?[0-9])";
    final private String COMMA_PATTERN_REGEX = "^([0-9]|[1-5][0-9]?)(,([0-9]|[1-5]([0-9]?)))*$";
    final private String HYPHEN_PATTERN_REGEX = "^([0-9]|[1-5][0-9])?(-([0-9]|[1-5]([0-9]?))*)$";

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
                throw new CronParseException("Minute expression unsupported.Correct range 0-59 and expression should be incremental");
            }
            return;
        }
        else if(expression.matches(SINGLE_MINUTE_REGEX)){
            return;
        }
        else{
            throw new CronParseException("Minute expression unsupported. Allowed Symbols /*-, (0-59)");
        }
    }
}
