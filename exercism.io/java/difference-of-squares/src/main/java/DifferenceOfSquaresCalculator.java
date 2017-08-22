import java.lang.Math;

public class DifferenceOfSquaresCalculator {

    public DifferenceOfSquaresCalculator() {
        // NO OP
    }

    public int computeSquareOfSumTo(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number;
            number--;
        }
        return (int) Math.pow(sum, 2);
    }

    public int computeSumOfSquaresTo(int number) {
        int sum = 0;
        while (number > 0) {
            sum += Math.pow(number, 2);
            number--;
        }
        return sum;
    }

    public int computeDifferenceOfSquares(final int number) {
        return computeSquareOfSumTo(number) - computeSumOfSquaresTo(number);
    }

}
