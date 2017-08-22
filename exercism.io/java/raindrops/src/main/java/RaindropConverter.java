import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

class RaindropConverter {

    String convert(final int number) {
        final StringBuilder builder = new StringBuilder();
        if (number % 3 == 0) {
            builder.append("Pling");
        }
        if (number % 5 == 0) {
            builder.append("Plang");
        }
        if (number % 7 == 0) {
            builder.append("Plong");
        }
        if (builder.length() == 0) {
            builder.append(number);
        }
        return builder.toString();
    }

}
