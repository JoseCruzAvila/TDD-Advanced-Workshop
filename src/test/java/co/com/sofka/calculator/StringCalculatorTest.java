package co.com.sofka.calculator;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void emptyStringGivesZeroTest() {
        String toCalculate = "";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);

        assertEquals(0, result);
    }

    @Test
    public void lonelyNumberGivesNumberTest() {
        String toCalculate = "2";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(2, result);

        toCalculate = "5";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(5, result);

        toCalculate = "20";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(20, result);
    }

    @Test
    public void twoCommaDelimitedNumbersGivesSumTest() {
        String toCalculate = "1,2";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);

        toCalculate = "0,2";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(2, result);

        toCalculate = "2,5";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(7, result);
    }

    @Test
    public void twoLineBreakDelimitedNumbersGivesSumTest() {
        String toCalculate = "1\n2";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);

        toCalculate = "2\n3";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(5, result);

        toCalculate = "20\n1";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(21, result);
    }

    @Test
    public void threeEitherWayDelimitedNumbersGivesSumTest() {
        String toCalculate = "1.2,3";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(6, result);

        toCalculate = "1\n2,1";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(4, result);

        toCalculate = "5.4~2";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(11, result);
    }

   @Test(expected = NumberFormatException.class)
    public void negativeNumbersExceptionTest() {
        String toCalculate = "-1,2";
        stringCalculator.calculateAdditionFromString(toCalculate);

       toCalculate = "-1,-2";
       stringCalculator.calculateAdditionFromString(toCalculate);

       toCalculate = "1,-2";
       stringCalculator.calculateAdditionFromString(toCalculate);
    }

    @Test
    public void greaterThan1000NumbersIgnoredTest() {
        String toCalculate = "2, 1000";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(1002, result);

        toCalculate = "2, 1001";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(2, result);
    }

    @Test
    public void defineOneCharacterDelimiterTest() {
        String toCalculate = "#2#1";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);

        toCalculate = "/2/1";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);

        toCalculate = "{2{1";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);
    }

    @Test
    public void defineMultipleCharactersDelimiterTest() {
        String toCalculate = "[###]2###1";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);

        toCalculate = "[...]2...1";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);

        toCalculate = "[,-,]2,-,1";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(3, result);
    }

    @Test
    public void defineMultipleCharactersAndDelimitersTest() {
        String toCalculate = "[#][>]2#1>3";
        Integer result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(6, result);

        toCalculate = "[,][-]2,1-3";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(6, result);

        toCalculate = "[<][*]2<1*3";
        result = stringCalculator.calculateAdditionFromString(toCalculate);
        assertEquals(6, result);
    }
}
