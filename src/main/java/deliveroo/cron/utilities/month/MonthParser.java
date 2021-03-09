package deliveroo.cron.utilities.month;

import deliveroo.cron.exception.CronParseException;
import deliveroo.cron.utilities.AbstractExpressionParser;
import deliveroo.cron.utilities.month.validate.MonthValidator;

public class MonthParser extends AbstractExpressionParser {

    final private Integer startRange = 1;
    final private Integer endRange = 12;
    final private MonthValidator monthValidator;


    public MonthParser(){
        monthValidator = new MonthValidator();
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
        monthValidator.validate(expression);
    }
}
