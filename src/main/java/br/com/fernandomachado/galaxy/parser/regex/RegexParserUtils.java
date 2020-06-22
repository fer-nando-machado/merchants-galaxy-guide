package br.com.fernandomachado.galaxy.parser.regex;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Utilities and constants used by {@link RegexParser} and its associated classes.
 */
public class RegexParserUtils {

    public final static String IS = "is";
    public final static String SPACE = " ";
    public final static String MULTI_SPACE_REGEX = "\\s+";
    public final static String ERROR_MESSAGE = "I have no idea what you are talking about";

    private final static String DECIMAL_FORMAT = "###.##";
    final static char DECIMAL_SEPARATOR = '.';

    /**
     * Given a {@link Double} decimal, converts it to a {@link String} that follows a standard price format.
     *
     * @param d
     * @return formatted {@link String}
     */
    public static String formatPrice(Double d) {
        DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.ROOT);
        dfs.setDecimalSeparator(DECIMAL_SEPARATOR);
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT, dfs);
        return df.format(d);
    }

}
