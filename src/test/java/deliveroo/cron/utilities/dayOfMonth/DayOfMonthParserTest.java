package deliveroo.cron.utilities.dayOfMonth;

import deliveroo.cron.exception.CronParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayOfMonthParserTest {
    DayOfMonthParser parser = new DayOfMonthParser();

    @Test
    @DisplayName("Slash testing eg. */12")
    void testDayOfMonthsParserWithSlash() throws CronParseException {
        String expected = "0 12 24";
        Assertions.assertEquals(expected, parser.getIntervals("*/12"));
    }

    @Test
    @DisplayName("Hyphen testing eg. 1-10")
    void testDayOfMonthParserWithHyphen() throws CronParseException {
        String expected = "1 2 3 4 5 6 7 8 9 10";
        Assertions.assertEquals(expected,parser.getIntervals("1-10"));
    }

    @Test
    @DisplayName("Comma testing eg. 1,2,3")
    void testDayOfMonthParserWithComma() throws CronParseException {
        String expected = "1 3 6 9 11";
        Assertions.assertEquals(expected,parser.getIntervals("1,3,6,9,11"));
    }

    @Test
    @DisplayName("Comma testing with disordered intervals eg. 1,3,2,5,9")
    void testDayOfMonthParserWithCommaForJumbledIntervals() throws CronParseException {
        String expected = "1 3 6 9 11";
        Assertions.assertEquals(expected,parser.getIntervals("1,11,6,3,9"));
    }

    @Test
    @DisplayName("Asterisk testing")
    void testDayOfMonthParserWithAsterisk() throws CronParseException {
        String expected = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31";
        Assertions.assertEquals(expected,parser.getIntervals("*"));
    }

    @Test
    @DisplayName("Default result testing eg. 0 or 1 or 12")
    void testDayOfMonthParserWithDefault() throws CronParseException {
        String expected = "2";
        Assertions.assertEquals(expected,parser.getIntervals("2"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 0001 or 0002 or 012")
    void testDayOfMonthParserWithTrailingZeros1() throws CronParseException {
        String expected = "23";
        Assertions.assertEquals(expected,parser.getIntervals("000023"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 000000")
    void testDayOfMonthParserWithTrailingZeros2() throws CronParseException {
        String expected = "1";
        Assertions.assertEquals(expected,parser.getIntervals("000001"));
    }

    @Test
    @DisplayName("Exception thrown when wrong alpha-numeric characters exist eg. a12 or ab34 or #12 ")
    void testDayOfMonthParserValidationOne() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("a12"));
        Assertions.assertEquals("Day Of Month expression unsupported. Allowed Symbols /*-, (1-31)",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Hyphenated string has wrong start and end time ")
    void testDayOfMonthParserValidationTwo() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("23-10"));
        Assertions.assertEquals("Day Of Month expression unsupported.Correct range 1-31 and expression should be incremental",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Slash is passed with 0")
    void testDayOfMonthParserValidationThree() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("*/0"));
        Assertions.assertEquals("Day Of Month expression unsupported. Allowed Symbols /*-, (1-31)",exception.getMessage());
    }
}
