package deliveroo.cron.utilities.month;

import deliveroo.cron.exception.CronParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MonthParserTest {

    MonthParser parser = new MonthParser();

    @Test
    @DisplayName("Slash testing eg. */15")
    void testMonthParserWithSlash() throws CronParseException {
        String expected = "0 3 6 9 12";
        Assertions.assertEquals(expected, parser.getIntervals("*/3"));
    }

    @Test
    @DisplayName("Hyphen testing eg. 1-10")
    void testMonthParserWithHyphen() throws CronParseException {
        String expected = "1 2 3 4 5 6 7 8 9 10";
        Assertions.assertEquals(expected,parser.getIntervals("1-10"));
    }

    @Test
    @DisplayName("Comma testing eg. 1,2,3")
    void testMonthParserWithComma() throws CronParseException {
        String expected = "1 10 11 12";
        Assertions.assertEquals(expected,parser.getIntervals("1,10,11,12"));
    }

    @Test
    @DisplayName("Comma testing with disordered intervals eg. 1,3,2,5,9")
    void testMonthParserWithCommaForJumbledIntervals() throws CronParseException {
        String expected = "1 4 5 6 11";
        Assertions.assertEquals(expected,parser.getIntervals("1,11,6,5,4"));
    }

    @Test
    @DisplayName("Asterisk testing")
    void testMonthParserWithAsterisk() throws CronParseException {
        String expected = "1 2 3 4 5 6 7 8 9 10 11 12";
        Assertions.assertEquals(expected,parser.getIntervals("*"));
    }

    @Test
    @DisplayName("Default result testing eg. 0 or 1 or 12")
    void testMonthParserWithDefault() throws CronParseException {
        String expected = "1";
        Assertions.assertEquals(expected,parser.getIntervals("1"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 0001 or 0002 or 012")
    void testMonthParserWithTrailingZeros1() throws CronParseException {
        String expected = "12";
        Assertions.assertEquals(expected,parser.getIntervals("000012"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 000000")
    void testMonthParserWithTrailingZeros2() throws CronParseException {
        String expected = "1";
        Assertions.assertEquals(expected,parser.getIntervals("000001"));
    }

    @Test
    @DisplayName("Exception thrown when wrong alpha-numeric characters exist eg. a12 or ab34 or #12 ")
    void testMonthParserValidationOne() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("a12"));
        Assertions.assertEquals("Month expression unsupported. Allowed Symbols /*-, (1-12)",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Hyphenated string has wrong start and end time ")
    void testMonthParserValidationTwo() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("12-7"));
        Assertions.assertEquals("Month expression unsupported.Correct range 1-12 and expression should be incremental",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Slash is passed with 0")
    void testMonthParserValidationThree() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("*/0"));
        Assertions.assertEquals("Month expression unsupported. Allowed Symbols /*-, (1-12)",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Slash is passed with a value outside the range")
    void testMonthParserValidationFour() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("*/67"));
        Assertions.assertEquals("Month expression unsupported. Allowed Symbols /*-, (1-12)",exception.getMessage());
    }
}
