package deliveroo.cron.utilities.dayOfWeek;

import deliveroo.cron.exception.CronParseException;
import deliveroo.cron.utilities.AbstractExpressionParser;
import deliveroo.cron.utilities.dayOfWeek.validator.DayOfWeekValidator;

public class DayOfWeekParser extends AbstractExpressionParser {
    final private Integer startRange = 1;
    final private Integer endRange = 7;
    final private DayOfWeekValidator dayOfMonthValidator;


    public DayOfWeekParser(){
        dayOfMonthValidator = new DayOfWeekValidator();
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
