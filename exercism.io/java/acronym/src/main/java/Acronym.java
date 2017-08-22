import java.lang.StringBuilder;
import java.util.StringTokenizer;

class Acronym {

    public static final String DEFAULT_DELIMITER = " -";

    private final String delimiter;
    private final String phrase;

    Acronym(final String phrase) {
        this(phrase, DEFAULT_DELIMITER);
    }

    Acronym(final String phrase, final String delimiter) {
        this.phrase = phrase;
        this.delimiter = delimiter;
    }

    String get() {
        final StringBuilder builder = new StringBuilder();
        final StringTokenizer tokenizer = new StringTokenizer(phrase, delimiter);
        while (tokenizer.hasMoreTokens()) {
            builder.append(tokenizer.nextToken().toUpperCase().charAt(0));
        }
        return builder.toString();
    }

}
