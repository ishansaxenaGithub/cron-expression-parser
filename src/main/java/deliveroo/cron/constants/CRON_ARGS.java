package deliveroo.cron.constants;

import deliveroo.cron.utilities.ExpressionParser;
import deliveroo.cron.utilities.dayOfMonth.DayOfMonthParser;
import deliveroo.cron.utilities.dayOfWeek.DayOfWeekParser;
import deliveroo.cron.utilities.hour.HourParser;
import deliveroo.cron.utilities.minute.MinuteParser;
import deliveroo.cron.utilities.month.MonthParser;

public enum CRON_ARGS {
    MINUTE(0,"minute",new MinuteParser()),
    HOUR(1,"hour", new HourParser()),
    DAY_OF_MONTH(2,"day of month", new DayOfMonthParser()),
    MONTH(3,"month", new MonthParser()),
    DAY_OF_WEEK(4,"day of week", new DayOfWeekParser());

    int seq ; String label; ExpressionParser parser;
    CRON_ARGS(int seq, String value, ExpressionParser parser ) {
        this.seq = seq;
        this.label = value;
        this.parser = parser;
    }

    public ExpressionParser getParser() {
        return parser;
    }

    public int getSeq() {
        return seq;
    }

    public String getLabel() {
        return label;
    }
}
