package deliveroo.cron;

import deliveroo.cron.constants.CRON_ARGS;
import deliveroo.cron.exception.CronParseException;
import org.apache.commons.lang3.StringUtils;
import deliveroo.cron.constants.ParserConstants;
import deliveroo.cron.utilities.PrintUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class CronExpressionParser {

    private final Map<String,String>  interpretedResult;
    private final String cron;

    public CronExpressionParser(final String cronExpression){
        this.interpretedResult = new LinkedHashMap<>();
        this.cron = cronExpression;
    }

    public void interpretCronExpression() throws CronParseException {
        validateExpression(this.cron);
        String[] splitExpression = cron.split(ParserConstants.SINGLE_SPACE_SPLITTER);

        for(CRON_ARGS args : CRON_ARGS.values()){
            interpretedResult.put(args.getLabel(),args.getParser().getIntervals(splitExpression[args.getSeq()]));
        }
        interpretedResult.put("command",splitExpression[splitExpression.length-1]);
        PrintUtils.printTable(interpretedResult);
    }

    private void validateExpression(String cron) throws CronParseException {
        if (StringUtils.isEmpty(cron)) {
            throw new IllegalArgumentException("Cron expression is empty");
        }

        String[] expressionParts = cron.split(" ");
        if (expressionParts.length < 6) {
            throw new CronParseException("Cron Expression should have all the arguments");
        }
        else if(expressionParts.length >6){
            throw new CronParseException("Cron Expression has unnecessary arguments. Please check for the spaces between the arguments");
        }
    }

    public static void main(String[] args) throws CronParseException {
        //String expression = args[0];
        String expression = "* * * * * command";
        CronExpressionParser obj = new CronExpressionParser(expression);
        obj.interpretCronExpression();

    }
}
