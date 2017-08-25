public class RotationalCipher {

    private static final int POSITION_A = (int) 'a';
    private static final int POSITION_Z = (int) 'z';
    private static final int SHIFT = POSITION_Z - POSITION_A + 1;
    private static final int POSITION_CAPITAL_A = (int) 'A';
    private static final int POSITION_CAPITAL_Z = (int) 'Z';
    private static final int CAPITAL_SHIFT = POSITION_CAPITAL_Z - POSITION_CAPITAL_A + 1;

    private final int seed;

    public RotationalCipher(final int seed) {
        this.seed = seed;
    }

    private boolean isValid(final char single) {
        final int value = (int) single;
        return (value >= POSITION_A && value <= POSITION_Z)
                || (value >= POSITION_CAPITAL_A && value <= POSITION_CAPITAL_Z);
    }

    private boolean isCapital(final char single) {
        final int value = (int) single;
        return value >= POSITION_CAPITAL_A && value <= POSITION_CAPITAL_Z;
    }

    private char rotateSingle(final char single) {
        /* Skip all but characters */
        if (!isValid(single)) {
            return single;
        }
        int value = (int) single + seed;
        /* Possibly shift character back to valid range */
        if (isCapital(single)) {
            if (value > POSITION_CAPITAL_Z) {
                value -= CAPITAL_SHIFT;
            }
        } else {
            if (value > POSITION_Z) {
                value -= SHIFT;
            }
        }
        return (char) value;
    }

    public String rotate(final String cipher) {
        final StringBuilder builder = new StringBuilder();
        for (final char single : cipher.toCharArray()) {
            builder.append(rotateSingle(single));
        }
        return builder.toString();
    }
}
