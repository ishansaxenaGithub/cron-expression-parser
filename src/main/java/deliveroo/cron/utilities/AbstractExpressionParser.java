package deliveroo.cron.utilities;

import deliveroo.cron.constants.ParserConstants;
import deliveroo.cron.exception.CronParseException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractExpressionParser implements ExpressionParser {

    public String getIntervals(final String expression) throws CronParseException {

        validate(expression);
        return parseExpression(expression);
    }

    protected String parseExpression(final String expression) throws CronParseException {
        if(expression.contains(ParserConstants.SLASH)){
            return getResultantIntervalsOnSlash(expression);
        }
        else if(expression.contains(ParserConstants.HYPHEN)){
            return getResultantIntervalsOnHyphen(expression);
        }
        else if(expression.contains(ParserConstants.COMMA)){
            return getResultantIntervalsOnComma(expression);
        }
        else if(expression.equals(ParserConstants.ALL)){
            return ParserUtils.joinedAsString(IntStream.range(getStartRange(),getEndRange()+1).boxed().
                    map(e-> String.valueOf(e)).collect(Collectors.toList()));
        }
        else{
            return ParserUtils.removeLeadingZeros(expression);
        }
    }

    private String getResultantIntervalsOnComma(String minuteCronExpression) {
        String expressionArgs[] = minuteCronExpression.split(ParserConstants.COMMA);
        List<String> exp = Arrays.stream(expressionArgs).collect(Collectors.toList());
        List<String> intervals = IntStream.range(getStartRange(),getEndRange()+1).boxed().
                map(e-> String.valueOf(e)).filter(exp::contains).collect(Collectors.toList());
        return ParserUtils.joinedAsString(intervals);
    }

    private String getResultantIntervalsOnHyphen(String minuteCronExpression) throws CronParseException {
        String expressionArgs[] = minuteCronExpression.split(ParserConstants.HYPHEN);
        int from = Integer.valueOf(expressionArgs[0]);
        int to = Integer.valueOf(expressionArgs[1]);
        List<String> intervals = IntStream.range(from,to+1).boxed().map(e-> String.valueOf(e)).collect(Collectors.toList());
        return ParserUtils.joinedAsString(intervals);
    }

    private String getResultantIntervalsOnSlash(String expression) {
        String expressionArgs[] = expression.split(ParserConstants.SLASH);
        int intervalVal = Integer.valueOf(expressionArgs[1]);
        StringBuilder result = new StringBuilder();
        result.append("0");
        for(int i =intervalVal;i<getEndRange()+1;){
            result.append(" "+i);
            i +=intervalVal;
        }
        return result.toString();
    }

    protected abstract int getEndRange();

    protected abstract int getStartRange();

    public abstract void validate(final String expression) throws CronParseException;
}
