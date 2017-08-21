public class Hamming {

    private final int length;
    private final String left;
    private final String right;

    Hamming(String leftStrand, String rightStrand) {
        if (leftStrand == null || rightStrand == null) {
            throw new IllegalArgumentException("Neither leftStrand nor rightStrand may not be null.");
        }
        left = leftStrand;
        right = rightStrand;
        length = left.length();
        if (length != right.length()) {
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }
    }

    int getHammingDistance() {
        int distance = 0;
        for (int i = 0; i < length; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                distance++;
            }
        }
        return distance;
    }

}
