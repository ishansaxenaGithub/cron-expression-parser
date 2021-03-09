package deliveroo.cron.utilities.minute;

import deliveroo.cron.exception.CronParseException;
import deliveroo.cron.utilities.AbstractExpressionParser;
import deliveroo.cron.utilities.minute.validator.MinuteExpressionValidator;

public class MinuteParser extends AbstractExpressionParser {

    final private Integer startRange = 0;
    final private Integer endRange = 59;
    final private MinuteExpressionValidator minuteExpressionValidator;


    public MinuteParser(){
        minuteExpressionValidator = new MinuteExpressionValidator();
    }

    @Override
    protected int getEndRange() {
        return endRange;
    }

    @Override
    protected int getStartRange() {
        return startRange;
    }

    @Override
    public void validate(String expression) throws CronParseException {
        minuteExpressionValidator.validate(expression);
    }
}
