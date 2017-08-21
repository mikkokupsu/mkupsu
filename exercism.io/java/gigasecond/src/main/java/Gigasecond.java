import java.lang.Math;
import java.time.LocalDate;
import java.time.LocalDateTime;

class Gigasecond {

    private static final int GIGA = (int) Math.pow(10, 9);

    private LocalDateTime birthTime;

    Gigasecond(final LocalDate birthDate) {
        birthTime = birthDate.atStartOfDay();
    }

    Gigasecond(final LocalDateTime birthDateTime) {
        birthTime = birthDateTime;
    }

    LocalDateTime getDate() {
        return birthTime.plusSeconds(GIGA);
    }

}
