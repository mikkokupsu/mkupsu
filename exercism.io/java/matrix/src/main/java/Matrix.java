import java.util.ArrayList;
import java.util.StringTokenizer;

public class Matrix {

    private final int[][] matrix;

    public Matrix(final String matrixAsString) {
        matrix = parseMatrix(matrixAsString);
    }

    private int[][] parseMatrix(final String matrix) {
        final ArrayList<int[]> rows = new ArrayList<>();
        for (final String row : matrix.split("\n")) {
            rows.add(parseRow(row));
        }
        final int[][] parsed = new int[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            parsed[i] = rows.get(i);
        }
        return parsed;
    }

    private int[] parseRow(final String matrixRow) {
        final StringTokenizer tokenizer = new StringTokenizer(matrixRow);
        final ArrayList<Integer> list = new ArrayList<>();
        while (tokenizer.hasMoreElements()) {
            list.add(Integer.parseInt(tokenizer.nextToken()));
        }
        final int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i).intValue();
        }
        return array;
    }

    public int getRowsCount() {
        return matrix.length;
    }

    public int getColumnsCount() {
        return matrix[0].length;
    }

    public int[] getColumn(final int index) {
        final int[] array = new int[matrix.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = matrix[i][index];
        }
        return array;
    }

    public int[] getRow(final int index) {
        return matrix[index];
    }
}
