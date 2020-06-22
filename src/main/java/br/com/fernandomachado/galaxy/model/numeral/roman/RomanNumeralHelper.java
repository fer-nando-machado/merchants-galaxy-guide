package br.com.fernandomachado.galaxy.model.numeral.roman;

import br.com.fernandomachado.galaxy.model.numeral.SymbolEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Helper class containing auxiliary functions for {@link RomanNumeral}s.
 */
public class RomanNumeralHelper {

    private static final Integer ROMAN_MIN = 1;
    private static final Integer ROMAN_MAX = 3999;

    // Source: https://stackoverflow.com/questions/267399/how-do-you-match-only-valid-roman-numerals-with-a-regular-expression
    private static final String ROMAN_REGEX = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    /**
     * Given a {@link String} numeral, returns its {@link Integer} decimal representation.
     * Throws a {@link NumberFormatException} in case the informed numeral is not valid.
     *
     * @param numeral
     * @return {@link Integer}
     */
    static Integer toDecimal(String numeral) {
        assertValidNumeral(numeral);

        Map<Character, Integer> map = SymbolEnum.buildCharacterDecimalMap(RomanSymbol.values());
        int decimal = 0;
        int n = numeral.length();
        for (int i = 0; i < n; i++) {
            int current = map.get(numeral.charAt(i));
            int next = (i + 1 < n) ? map.get(numeral.charAt(i+1)) : 0;
            if (current < next) {
                decimal += (next - current);
                i++; //since both current and next chars were used, we skip to the char that comes after the next one.
            } else {
                decimal += current;
            }
        }
        return decimal;
    }

    /**
     * Given a {@link Integer} decimal, returns its {@link String} numeral representation.
     * Throws a {@link NumberFormatException} in case the informed decimal is not valid.
     *
     * @param decimal
     * @return {@link Integer}
     */
    static String toNumeral(Integer decimal) {
        assertValidDecimal(decimal);

        int remainder = decimal.intValue();
        Map<Integer, Character> map = SymbolEnum.buildDecimalCharacterMap(RomanSymbol.values());
        StringBuilder roman = new StringBuilder();
        List<Integer> decimals = new ArrayList<>(map.keySet());
        decimals.sort(Collections.reverseOrder());

        // First, we treat the special case of thousands and determine how many "M"s will be appended to the string.
        if (remainder >= 1000) {
            int thousands = remainder / 1000;
            remainder = remainder % 1000;
            roman.append(repeat(map.get(1000), thousands));
        }

        // The, we iterate three times (for hundreds, tens and ones), while appending the corresponding chars as needed.
        // Since they follow the same pattern, we just need to determine how many units of each are present.
        for (int i = 0; i < decimals.size()-1; i = i+2) {
            int maxUnit = decimals.get(i);
            int midUnit = decimals.get(i+1);
            int minUnit = decimals.get(i+2);

            int units = remainder / minUnit;
            remainder = remainder % minUnit;

            if (units == 9) {
                roman.append(map.get(minUnit));
                roman.append(map.get(maxUnit));
            } else if (5 <= units && units <= 8) {
                roman.append(map.get(midUnit));
                roman.append(repeat(map.get(minUnit), units - 5));
            } else if (units == 4) {
                roman.append(map.get(minUnit));
                roman.append(map.get(midUnit));
            } else if (1 <= units && units <= 3) {
                roman.append(repeat(map.get(minUnit), units));
            }
        }

        return roman.toString();
    }

    private static void assertValidNumeral(String numeral) {
        if (!numeral.matches(ROMAN_REGEX)) {
            throw new NumberFormatException(numeral + " is not a valid Roman numeral.");
        }
    }

    private static void assertValidDecimal(int decimal) {
        if (decimal < ROMAN_MIN || decimal > ROMAN_MAX) {
            throw new NumberFormatException(decimal + " is outside the range of Roman numerals (" + ROMAN_MIN + "-" + ROMAN_MAX + ")");
        }
    }

    private static String repeat(char c, int times) {
        if (times == 0) {
            return "";
        }
        char[] repeat = new char[times];
        Arrays.fill(repeat, c);
        return new String(repeat);
    }

}
