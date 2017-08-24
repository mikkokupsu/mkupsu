import java.lang.NumberFormatException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestSeriesProductCalculator {

    private final List<Long> digits;

    public LargestSeriesProductCalculator(final String digits) {
        if (digits == null) {
            throw new IllegalArgumentException("String to search must be non-null.");
        }
        this.digits = new ArrayList<>(digits.length());
        for (final char single : digits.toCharArray()) {
            try {
                this.digits.add(Long.valueOf("" + single));
            } catch (final NumberFormatException ex) {
                throw new IllegalArgumentException("String to search may only contains digits.");
            }
        }
    }

    private long product(final List<Long> list) {
        long product = 1L;
        for (final Long value : list) {
            product = product * value.longValue();
        }
        return product;
    }

    public long calculateLargestProductForSeriesLength(final int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Series length must be non-negative.");
        } else if (digits.size() < size) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }
        long max = 0L;
        for (int start = 0, end = size; end <= digits.size(); start++, end++) {
            final long value = product(digits.subList(start, end));
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}
