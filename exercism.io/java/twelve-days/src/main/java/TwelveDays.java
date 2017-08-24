import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.Map;


class TwelveDays {

    private static final String VERSE_1 = "On the %s day of Christmas my true love gave to me, ";
    private static final int POS_VERSE_1 = 0;
    private static final int POS_VERSE_2 = 1;

    private static final Map<Integer, String[]> DAY_VERSES = new HashMap<>();

    static {
        DAY_VERSES.put(1, new String[] {"first", "a Partridge in a Pear Tree."});
        DAY_VERSES.put(2, new String[] {"second", "two Turtle Doves, "});
        DAY_VERSES.put(3, new String[] {"third", "three French Hens, "});
        DAY_VERSES.put(4, new String[] {"fourth", "four Calling Birds, "});
        DAY_VERSES.put(5, new String[] {"fifth", "five Gold Rings, "});
        DAY_VERSES.put(6, new String[] {"sixth", "six Geese-a-Laying, "});
        DAY_VERSES.put(7, new String[] {"seventh", "seven Swans-a-Swimming, "});
        DAY_VERSES.put(8, new String[] {"eighth", "eight Maids-a-Milking, "});
        DAY_VERSES.put(9, new String[] {"ninth", "nine Ladies Dancing, "});
        DAY_VERSES.put(10, new String[] {"tenth", "ten Lords-a-Leaping, "});
        DAY_VERSES.put(11, new String[] {"eleventh", "eleven Pipers Piping, "});
        DAY_VERSES.put(12, new String[] {"twelfth", "twelve Drummers Drumming, "});
    }

    private String startVerse(final int startVerse) {
        return String.format(VERSE_1, DAY_VERSES.get(startVerse)[POS_VERSE_1]);
    }

    private String endVerse(final int maxVerse) {
        final StringBuilder verse = new StringBuilder();
        for (int i = maxVerse; i >= 2; i--) {
            verse.append(DAY_VERSES.get(i)[POS_VERSE_2]);
        }
        if (verse.length() != 0) {
            verse.append("and ");
        }
        return verse.append(DAY_VERSES.get(1)[POS_VERSE_2]).toString();
    }

    String verse(final int verseNumber) {
        return new StringBuilder(startVerse(verseNumber))
            .append(endVerse(verseNumber))
            .append("\n")
            .toString();
    }

    String verses(final int startVerse, final int endVerse) {
        final StringBuilder builder = new StringBuilder();
        for (int i = startVerse; i <= endVerse; i++) {
            builder.append("\n").append(verse(i));
        }
        return builder.replace(0, 1, "").toString();
    }

    String sing() {
        return verses(1, 12);
    }
}
