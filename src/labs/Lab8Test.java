package labs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Lab8Test {
    // Tests for divideNumbers
    @Test
    public void divideNumbers_normalPositive() {
        assertEquals(0.5, Lab8.divideNumbers(1, 2), 0.0001);
    }

    @Test
    public void divideNumbers_normalNegative() {
        assertEquals(-2.0, Lab8.divideNumbers(-4, 2), 0.0001);
    }

    @Test
    public void divideNumbers_largeNumbers() {
        assertEquals(1000.0, Lab8.divideNumbers(10000, 10), 0.0001);
    }

    @Test
    public void divideNumbers_smallNumbers() {
        assertEquals(0.25, Lab8.divideNumbers(1, 4), 0.0001);
    }

    @Test
    public void divideNumbers_numeratorZero() {
        assertEquals(0.0, Lab8.divideNumbers(0, 5), 0.0001);
    }

    @Test
    public void divideNumbers_negativeDenominator() {
        assertEquals(-0.5, Lab8.divideNumbers(1, -2), 0.0001);
    }

    @Test
    public void divideNumbers_maxIntNumerator() {
        assertEquals(2147483647.0, Lab8.divideNumbers(Integer.MAX_VALUE, 1), 0.0001);
    }

    @Test
    public void divideNumbers_minIntDenominator() {
        assertEquals(-0.0, Lab8.divideNumbers(0, Integer.MIN_VALUE), 0.0001);
    }

    @Test
    public void divideNumbers_bothNegative() {
        assertEquals(2.0, Lab8.divideNumbers(-4, -2), 0.0001);
    }

    @Test
    public void divideNumbers_denominatorZero_throwsArithmeticException() {
        assertThrows(ArithmeticException.class, () -> Lab8.divideNumbers(5, 0));
    }

    @Test
    public void divideNumbers_bothZero_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> Lab8.divideNumbers(0, 0));
    }

    // Tests for convertCelsiusToFahrenheit
    @Test
    public void convertCelsiusToFahrenheit_zero() {
        assertEquals(32.0, Lab8.convertCelsiusToFahrenheit("0"), 0.0001);
    }

    @Test
    public void convertCelsiusToFahrenheit_positive() {
        assertEquals(98.6, Lab8.convertCelsiusToFahrenheit("37"), 0.0001);
    }

    @Test
    public void convertCelsiusToFahrenheit_negative() {
        assertEquals(-40.0, Lab8.convertCelsiusToFahrenheit("-40"), 0.0001);
    }

    @Test
    public void convertCelsiusToFahrenheit_decimal() {
        assertEquals(33.8, Lab8.convertCelsiusToFahrenheit("1.0"), 0.0001);
    }

    @Test
    public void convertCelsiusToFahrenheit_largePositive() {
        assertEquals(212.0, Lab8.convertCelsiusToFahrenheit("100"), 0.0001);
    }

    @Test
    public void convertCelsiusToFahrenheit_largeNegative() {
        assertEquals(-459.67, Lab8.convertCelsiusToFahrenheit("-273.15"), 0.0001);
    }

    @Test
    public void convertCelsiusToFahrenheit_emptyString_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Lab8.convertCelsiusToFahrenheit(""));
    }

    @Test
    public void convertCelsiusToFahrenheit_nonNumeric_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Lab8.convertCelsiusToFahrenheit("abc"));
    }

    @Test
    public void convertCelsiusToFahrenheit_null_throwsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> Lab8.convertCelsiusToFahrenheit(null));
    }

    @Test
    public void convertCelsiusToFahrenheit_belowAbsoluteZero_throwsInvalidTemperatureException() {
        assertThrows(Lab8.InvalidTemperatureException.class, () -> Lab8.convertCelsiusToFahrenheit("-273.16"));
    }

    @Test
    public void convertCelsiusToFahrenheit_scientificNotation() {
        assertEquals(212.0, Lab8.convertCelsiusToFahrenheit("1e2"), 0.0001);
    }

    // Tests for file_slice
    @Test
    public void fileSlice_normalRange() {
        String result = Lab8.file_slice("test.txt", 0, 2);
        assertEquals("hello\nworld\nhow", result);
    }

    @Test
    public void fileSlice_singleLine() {
        String result = Lab8.file_slice("test.txt", 0, 0);
        assertEquals("hello", result);
    }

    @Test
    public void fileSlice_lastBeyondEnd() {
        String result = Lab8.file_slice("test.txt", 0, 10);
        assertEquals("hello\nworld\nhow\nare\nyou\ntoday?", result);
    }

    @Test
    public void fileSlice_firstEqualsLast() {
        String result = Lab8.file_slice("test.txt", 1, 1);
        assertEquals("world", result);
    }

    @Test
    public void fileSlice_middleRange() {
        String result = Lab8.file_slice("test.txt", 2, 4);
        assertEquals("how\nare\nyou", result);
    }

    @Test
    public void fileSlice_lastLine() {
        String result = Lab8.file_slice("test.txt", 5, 5);
        assertEquals("today?", result);
    }

    @Test
    public void fileSlice_fullFile() {
        String result = Lab8.file_slice("test.txt", 0, 5);
        assertEquals("hello\nworld\nhow\nare\nyou\ntoday?", result);
    }

    @Test
    public void fileSlice_negativeFirst_throwsNegativeArraySizeException() {
        assertThrows(NegativeArraySizeException.class, () -> Lab8.file_slice("test.txt", -1, 2));
    }

    @Test
    public void fileSlice_negativeLast_throwsNegativeArraySizeException() {
        assertThrows(NegativeArraySizeException.class, () -> Lab8.file_slice("test.txt", 0, -1));
    }

    @Test
    public void fileSlice_lastLessThanFirst_throwsNegativeArraySizeException() {
        assertThrows(NegativeArraySizeException.class, () -> Lab8.file_slice("test.txt", 2, 1));
    }

    @Test
    public void fileSlice_firstBeyondEnd_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> Lab8.file_slice("test.txt", 6, 6));
    }

    @Test
    public void fileSlice_firstAtEnd_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> Lab8.file_slice("test.txt", 6, 7));
    }

    @Test
    public void fileSlice_lastAtEnd() {
        String result = Lab8.file_slice("test.txt", 4, 5);
        assertEquals("you\ntoday?", result);
    }

    // Tests for rev_rev_file
    @Test
    public void revRevFile_normalFile() {
        String result = Lab8.rev_rev_file("test.txt", "output.txt");
        String expected = "?yadot\nuoy\nera\nwoh\ndlrow\nolleh";
        assertEquals(expected, result);
    }

    @Test
    public void revRevFile_nonExistentInput() {
        String result = Lab8.rev_rev_file("nonexistent.txt", "output.txt");
        assertEquals("ERROR: NO INPUT", result);
    }

    @Test
    public void revRevFile_nullInputFile() {
        String result = Lab8.rev_rev_file(null, "output.txt");
        assertEquals("ERROR: NO INPUT", result);
    }

    @Test
    public void revRevFile_emptyInputFileName() {
        String result = Lab8.rev_rev_file("", "output.txt");
        assertEquals("ERROR: NO INPUT", result);
    }

    @Test
    public void revRevFile_nullOutputFile() {
        String result = Lab8.rev_rev_file("test.txt", null);
        String expected = "?yadot\nuoy\nera\nwoh\ndlrow\nolleh";
        assertEquals(expected, result);
    }

    @Test
    public void revRevFile_emptyOutputFileName() {
        String result = Lab8.rev_rev_file("test.txt", "");
        String expected = "?yadot\nuoy\nera\nwoh\ndlrow\nolleh";
        assertEquals(expected, result);
    }

    @Test
    public void revRevFile_unwritableOutput() {
        String result = Lab8.rev_rev_file("test.txt", "/invalid/path/output.txt");
        String expected = "?yadot\nuoy\nera\nwoh\ndlrow\nolleh";
        assertEquals(expected, result);
    }

    @Test
    public void revRevFile_bothNamesEmpty() {
        String result = Lab8.rev_rev_file("", "");
        assertEquals("ERROR: NO INPUT", result);
    }

    @Test
    public void revRevFile_bothNamesNull() {
        String result = Lab8.rev_rev_file(null, null);
        assertEquals("ERROR: NO INPUT", result);
    }

    @Test
    public void revRevFile_singleLineFile() {
        String result = Lab8.rev_rev_file("test3.txt", "output.txt");
        assertTrue(result.contains("?yadot"));
    }

    @Test
    public void revRevFile_specialCharacters() {
        String result = Lab8.rev_rev_file("test2.txt", "output.txt");
        assertTrue(result.startsWith("?yadot"));
    }

}
