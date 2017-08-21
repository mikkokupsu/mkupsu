class SpaceAge {

    private static final int EARTH = 31557600;
    private static final double MERCURY = 0.2408467;
    private static final double VENUS = 0.61519726;
    private static final double MARS = 1.8808158;
    private static final double JUPITER = 11.862615;
    private static final double SATURN = 29.447498;
    private static final double URANUS = 84.016846;
    private static final double NEPTUNE = 164.79132;

    private final double seconds;

    SpaceAge(final double seconds) {
        this.seconds = seconds;
    }

    private static double toYears(final double seconds, final double divider) {
        return seconds / EARTH / divider;
    }

    double getSeconds() {
        return seconds;
    }

    double onEarth() {
        return toYears(seconds, 1);
    }

    double onMercury() {
        return toYears(seconds, MERCURY);
    }

    double onVenus() {
        return toYears(seconds, VENUS);
    }

    double onMars() {
        return toYears(seconds, MARS);
    }

    double onJupiter() {
        return toYears(seconds, JUPITER);
    }

    double onSaturn() {
        return toYears(seconds, SATURN);
    }

    double onUranus() {
        return toYears(seconds, URANUS);
    }

    double onNeptune() {
        return toYears(seconds, NEPTUNE);
    }

}
