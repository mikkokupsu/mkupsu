class Triangle {

    private final double side1;
    private final double side2;
    private final double side3;

    Triangle(final double side1, final double side2, final double side3) throws TriangleException {
        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new TriangleException();
        } else if ((side1 + side2) <= side3 || (side2 + side3) <= side1 || (side3 + side1) <= side2) {
            throw new TriangleException();
        }
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        System.out.println("side1 " + side1 + " side2 " + side2 + " side3 " + side3);
    }

    TriangleKind getKind() {
        final int side12 = Double.compare(side1, side2);
        final int side23 = Double.compare(side2, side3);
        final int side31 = Double.compare(side3, side1);
        if (side12 == 0 && side23 == 0 && side31 == 0) {
            return TriangleKind.EQUILATERAL;
        } else if (side12 == 0 || side23 == 0 || side31 == 0) {
            return TriangleKind.ISOSCELES;
        }
        return TriangleKind.SCALENE;
    }

}
