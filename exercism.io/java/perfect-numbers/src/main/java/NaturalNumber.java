class NaturalNumber {

    private final int number;

    public NaturalNumber(final int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("You must supply a natural number (positive integer)");
        }
        this.number = number;
    }

    private int getAliquotSum() {
        int divider = 1;
        int sum = 0;
        while (divider < number) {
            if (number % divider == 0) {
                sum += divider;
            } else if (number / divider == 0) {
                /* Early break because not possible results left */
                break;
            }
            divider++;
        }
        return sum;
    }

    public Classification getClassification() {
        final int aliquotSum = getAliquotSum();
        if (aliquotSum > number) {
            return Classification.ABUNDANT;
        } else if (aliquotSum < number) {
            return Classification.DEFICIENT;
        }
        return Classification.PERFECT;
    }

}
