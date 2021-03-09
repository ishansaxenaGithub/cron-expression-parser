package deliveroo.cron.utilities;

import deliveroo.cron.exception.CronParseException;

public interface ExpressionParser {

    public String getIntervals(final String expression) throws CronParseException;
}
