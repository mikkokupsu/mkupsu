import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matrix {

    private final List<List<Integer>> matrix;

    public Matrix(final List<List<Integer>> matrix) {
        this.matrix = matrix;
    }

    private <T> List<T> createNullList(final int size) {
        final List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(null);
        }
        return list;
    }

    public Set<MatrixCoordinate> getSaddlePoints() {
        if (this.matrix.isEmpty()) {
            return Collections.emptySet();
        }

        final List<Integer> cols = createNullList(this.matrix.get(0).size());
        final List<Integer> rows = createNullList(this.matrix.size());

        for (int row = 0; row < this.matrix.size(); row++) {
            for (int col = 0; col < this.matrix.get(row).size(); col++) {
                final int value = this.matrix.get(row).get(col);
                if (cols.get(col) == null || cols.get(col) > value) {
                    cols.set(col, value);
                }
                if (rows.get(row) == null || rows.get(row) < value) {
                    rows.set(row, value);
                }
            }
        }

        final Set<MatrixCoordinate> saddlePoints = new HashSet<>();
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < cols.size(); j++) {
                if (rows.get(i) == cols.get(j)) {
                    saddlePoints.add(new MatrixCoordinate(i, j));
                }
            }
        }

        return saddlePoints;
    }
}
