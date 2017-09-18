public class CollatzCalculator {

    public int computeStepCount(int start) {
        if (start < 1) {
            throw new IllegalArgumentException("Only natural numbers are allowed");
        }
        int steps = 0;
        while (start != 1) {
            steps++;
            if (start % 2 == 0) {
                start = start / 2;
            } else {
                start = 3 * start + 1;
            }
        }
        return steps;
    }
}
