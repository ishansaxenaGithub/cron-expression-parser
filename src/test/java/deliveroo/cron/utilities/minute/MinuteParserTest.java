package deliveroo.cron.utilities.minute;


import deliveroo.cron.exception.CronParseException;
import org.junit.jupiter.api.*;

public class MinuteParserTest{

    MinuteParser parser = new MinuteParser();

    @Test
    @DisplayName("Slash testing eg. */15")
    void testMinuteParserWithSlash() throws CronParseException {
        String expected = "0 15 30 45";
        Assertions.assertEquals(expected, parser.getIntervals("*/15"));
    }

    @Test
    @DisplayName("Hyphen testing eg. 1-10")
    void testMinuteParserWithHyphen() throws CronParseException {
        String expected = "1 2 3 4 5 6 7 8 9 10";
        Assertions.assertEquals(expected,parser.getIntervals("1-10"));
    }

    @Test
    @DisplayName("Comma testing eg. 1,2,3")
    void testMinuteParserWithComma() throws CronParseException {
        String expected = "1 10 11 12 31";
        Assertions.assertEquals(expected,parser.getIntervals("1,10,11,12,31"));
    }

    @Test
    @DisplayName("Comma testing with disordered intervals eg. 1,3,2,5,9")
    void testMinuteParserWithCommaForJumbledIntervals() throws CronParseException {
        String expected = "1 10 11 12 31 32 41";
        Assertions.assertEquals(expected,parser.getIntervals("1,10,11,12,31,41,32,31"));
    }

    @Test
    @DisplayName("Asterisk testing")
    void testMinuteParserWithAsterisk() throws CronParseException {
        String expected = "0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59";
        Assertions.assertEquals(expected,parser.getIntervals("*"));
    }

    @Test
    @DisplayName("Default result testing eg. 0 or 1 or 12")
    void testMinuteParserWithDefault() throws CronParseException {
        String expected = "0";
        Assertions.assertEquals(expected,parser.getIntervals("0"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 0001 or 0002 or 012")
    void testMinuteParserWithTrailingZeros1() throws CronParseException {
        String expected = "12";
        Assertions.assertEquals(expected,parser.getIntervals("000012"));
    }

    @Test
    @DisplayName("Remove leading zeros eg. 000000")
    void testMinuteParserWithTrailingZeros2() throws CronParseException {
        String expected = "0";
        Assertions.assertEquals(expected,parser.getIntervals("000000"));
    }

    @Test
    @DisplayName("Exception thrown when wrong alpha-numeric characters exist eg. a12 or ab34 or #12 ")
    void testMinuteParserValidationOne() throws CronParseException {
       Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("a12"));
       Assertions.assertEquals("Minute expression unsupported. Allowed Symbols /*-, (0-59)",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Hyphenated string has wrong start and end time ")
    void testMinuteParserValidationTwo() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("31-12"));
        Assertions.assertEquals("Minute expression unsupported.Correct range 0-59 and expression should be incremental",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Slash is passed with 0")
    void testMinuteParserValidationThree() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("*/0"));
        Assertions.assertEquals("Minute expression unsupported. Allowed Symbols /*-, (0-59)",exception.getMessage());
    }

    @Test
    @DisplayName("Exception thrown when Slash is passed with a value outside the range")
    void testMinuteParserValidationFour() throws CronParseException {
        Exception exception = Assertions.assertThrows(CronParseException.class, ()->parser.getIntervals("*/67"));
        Assertions.assertEquals("Minute expression unsupported. Allowed Symbols /*-, (0-59)",exception.getMessage());
    }
}
