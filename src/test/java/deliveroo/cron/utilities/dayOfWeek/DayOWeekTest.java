package deliveroo.cron.utilities.dayOfWeek;

import deliveroo.cron.exception.CronParseException;
import deliveroo.cron.utilities.dayOfWeek.DayOfWeekParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DayOWeekTest {
    DayOfWeekParser parser = new DayOfWeekParser();

    @Test
    @DisplayName("Slash testing eg. */2")
    void testDayOfWeeksParserWithSlash() throws CronParseException {
        String expected = "0 2 4 6";
        Assertions.assertEquals(expected, parser.getIntervals("*/2"));
    }

    @Test
    @DisplayName("Hyphen testing eg. 1-7")
    void testDayOfWeekParserWithHyphen() throws CronParseException {
        String expected = "1 2 3 4 5 6 7";
        Assertions.assertEquals(expected,parser.getIntervals("1-7"));
    }

    @Test
    @DisplayName("Comma testing eg. 1,2,3")
    void testDayOfWeekParserWithComma() throws CronParseException {
        String expected = "1 3 6";
        Assertions.assertEquals(expected,parser.getIntervals("1,3,6"));
    }

    @Test
    @DisplayName("Comma testing with disordered intervals eg. 1,3,2,5,9")
    void testDayOfWeekParserWithCommaForJumbledIntervals() throws CronParseException {
        String expected = "1 3 6";
        Assertions.assertEquals(expected,parser.getIntervals("1,6,3"));
    }

    @Test
    @DisplayName("Asterisk testing")
    void testDayOfWeekParserWithAsterisk() throws CronParseException {
        String expected = "1 2 3 4 5 6 7";
        Assertions.assertEquals(expected,parser.getIntervals("*"));
    }

    @Test
    @DisplayName("Default result testing eg. 0 or 1 or 12")
    void testDayOfWeekParserWithDefault() throws CronParseException {
        String expected = "7";
        Assertions.assertEquals(expected,parser.getIntervals("7"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 0001 or 0002 or 012")
    void testDayOfWeekParserWithTrailingZeros1() throws CronParseException {
        String expected = "5";
        Assertions.assertEquals(expected,parser.getIntervals("00005"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 000000")
    void testDayOfWeekParserWithTrailingZeros2() throws CronParseException {
        String expected = "1";
        Assertions.assertEquals(expected,parser.getIntervals("00001"));
    }

    @Test
    @DisplayName("Exception thrown when wrong alpha-numeric characters exist eg. a12 or ab34 or #12 ")
    void testDayOfWeekParserValidationOne() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("a12"));
        Assertions.assertEquals("Day of the Week expression unsupported. Allowed Symbols /*-, (1-7)",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Hyphenated string has wrong start and end time ")
    void testDayOfWeekParserValidationTwo() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("5-2"));
        Assertions.assertEquals("Day of the Week expression unsupported.Correct range 1-7 and expression should be incremental",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Slash is passed with 0")
    void testDayOfWeekParserValidationThree() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("*/0"));
        Assertions.assertEquals("Day of the Week expression unsupported. Allowed Symbols /*-, (1-7)",exception.getMessage());
    }
}
