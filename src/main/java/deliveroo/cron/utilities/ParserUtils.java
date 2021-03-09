package deliveroo.cron.utilities;

import deliveroo.cron.constants.ParserConstants;

import java.util.List;
import java.util.stream.Collectors;

public class ParserUtils {

    public static String joinedAsString(List<String> intervals) {
        return intervals.stream().map(elem -> String.valueOf(elem)).collect(Collectors.joining(ParserConstants.SINGLE_SPACE_SPLITTER));
    }

    public static String removeLeadingZeros(String minuteCronExpression) {
        String zerosRemoved =  minuteCronExpression.replaceFirst("^0+(?!$)", "");
        return zerosRemoved.isEmpty()? "0":zerosRemoved;
    }
}
