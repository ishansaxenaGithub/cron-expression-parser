package deliveroo.cron.utilities.hour;

import deliveroo.cron.exception.CronParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HourParserTest {

    HourParser parser = new HourParser();

    @Test
    @DisplayName("Slash testing eg. */12")
    void testHoursParserWithSlash() throws CronParseException {
        String expected = "0 12";
        Assertions.assertEquals(expected, parser.getIntervals("*/12"));
    }

    @Test
    @DisplayName("Hyphen testing eg. 1-10")
    void testHourParserWithHyphen() throws CronParseException {
        String expected = "1 2 3 4 5 6 7 8 9 10";
        Assertions.assertEquals(expected,parser.getIntervals("1-10"));
    }

    @Test
    @DisplayName("Comma testing eg. 1,2,3")
    void testHourParserWithComma() throws CronParseException {
        String expected = "1 3 6 9 11";
        Assertions.assertEquals(expected,parser.getIntervals("1,3,6,9,11"));
    }

    @Test
    @DisplayName("Comma testing with disordered intervals eg. 1,3,2,5,9")
    void testHourParserWithCommaForJumbledIntervals() throws CronParseException {
        String expected = "1 3 6 9 11";
        Assertions.assertEquals(expected,parser.getIntervals("1,11,6,3,9"));
    }

    @Test
    @DisplayName("Asterisk testing")
    void testHourParserWithAsterisk() throws CronParseException {
        String expected = "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23";
        Assertions.assertEquals(expected,parser.getIntervals("*"));
    }

    @Test
    @DisplayName("Default result testing eg. 0 or 1 or 12")
    void testHourParserWithDefault() throws CronParseException {
        String expected = "0";
        Assertions.assertEquals(expected,parser.getIntervals("0"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 0001 or 0002 or 012")
    void testHourParserWithTrailingZeros1() throws CronParseException {
        String expected = "23";
        Assertions.assertEquals(expected,parser.getIntervals("000023"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 000000")
    void testHourParserWithTrailingZeros2() throws CronParseException {
        String expected = "0";
        Assertions.assertEquals(expected,parser.getIntervals("000000"));
    }

    @Test
    @DisplayName("Exception thrown when wrong alpha-numeric characters exist eg. a12 or ab34 or #12 ")
    void testHourParserValidationOne() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("a12"));
        Assertions.assertEquals("Hour expression unsupported. Allowed Symbols /*-, (0-23)",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Hyphenated string has wrong start and end time ")
    void testHourParserValidationTwo() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("23-10"));
        Assertions.assertEquals("Hour expression unsupported.Correct range 0-23 and expression should be incremental",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Slash is passed with 0")
    void testHourParserValidationThree() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("*/0"));
        Assertions.assertEquals("Hour expression unsupported. Allowed Symbols /*-, (0-23)",exception.getMessage());
    }
}
