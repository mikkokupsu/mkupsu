import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class HandshakeCalculator {

    private static final int WINK = 0b1; // 1
    private static final int DOUBLE_BLINK = 0b10; // 10
    private static final int CLOSE_YOUR_EYES = 0b100; // 100
    private static final int JUMP = 0b1000; // 1000;
    private static final int REVERSE = 0b10000; // 10000

    List<Signal> calculateHandshake(final int number) {
        final List<Signal> list = new ArrayList<>();
        if ((number & WINK) == WINK) {
            list.add(Signal.WINK);
        }
        if ((number & DOUBLE_BLINK) == DOUBLE_BLINK) {
            list.add(Signal.DOUBLE_BLINK);
        }
        if ((number & CLOSE_YOUR_EYES) == CLOSE_YOUR_EYES) {
            list.add(Signal.CLOSE_YOUR_EYES);
        }
        if ((number & JUMP) == JUMP) {
            list.add(Signal.JUMP);
        }
        if ((number & REVERSE) == REVERSE) {
            Collections.reverse(list);
        }
        return list;
    }

}
