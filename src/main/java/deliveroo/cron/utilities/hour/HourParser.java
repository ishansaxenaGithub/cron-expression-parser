package deliveroo.cron.utilities.hour;

import deliveroo.cron.exception.CronParseException;
import deliveroo.cron.utilities.AbstractExpressionParser;
import deliveroo.cron.utilities.hour.validator.HourExpressionValidator;

public class HourParser extends AbstractExpressionParser {


    final private Integer startRange = 0;
    final private Integer endRange = 23;
    final private HourExpressionValidator hourExpressionValidator;


    public HourParser(){
        hourExpressionValidator = new HourExpressionValidator();
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
        hourExpressionValidator.validate(expression);
    }

}
