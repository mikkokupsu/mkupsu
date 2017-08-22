import java.lang.StringBuilder;
import java.util.regex.Pattern;


class LuhnValidator {

    private static final Pattern PATTERN = Pattern.compile("[0-9]");

    boolean isValid(final String candidate) {
        int sum = 0;
        int position = 0;
        final StringBuilder builder = new StringBuilder(candidate.replaceAll(" ", "")).reverse();
        for (final char single : builder.toString().toCharArray()) {
            final String str = "" + single;
            if (!PATTERN.matcher(str).matches()) {
                return false;
            }
            int value = Integer.parseInt(str);
            if (position % 2 == 1) {
                value = 2 * value;
                if (value > 9) {
                    value -= 9;
                }
            }
            sum += value;
            position++;
        }
        return position > 1 && sum % 10 == 0;
    }
}
