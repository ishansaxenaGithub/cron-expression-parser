package deliveroo.cron.utilities.dayOfMonth;

import deliveroo.cron.exception.CronParseException;
import deliveroo.cron.utilities.AbstractExpressionParser;
import deliveroo.cron.utilities.dayOfMonth.validator.DayOfMonthValidator;

public class DayOfMonthParser extends AbstractExpressionParser {


    final private Integer startRange = 1;
    final private Integer endRange = 31;
    final private DayOfMonthValidator dayOfMonthValidator;


    public DayOfMonthParser(){
        dayOfMonthValidator = new DayOfMonthValidator();
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
        dayOfMonthValidator.validate(expression);
    }
}
