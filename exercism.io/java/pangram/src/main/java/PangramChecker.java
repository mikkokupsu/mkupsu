

import java.util.HashSet;
import java.util.Set;

public class PangramChecker {

    private final Set<Character> allCharsOriginal;

    public PangramChecker () {
        allCharsOriginal = new HashSet<Character>(26);
        for (final char singleChar : "abcdefghijklmnopqrstuvwxyz".toCharArray()) {
            allCharsOriginal.add(singleChar);
        }
    }

    public boolean isPangram(String input) {
        if (input == null) {
            return false;
        }
        final Set<Character> allCharactersCopy = new HashSet<Character>(allCharsOriginal);
        for (final char singleChar : input.toLowerCase().toCharArray()) {
            allCharactersCopy.remove(singleChar);
        }
        return allCharactersCopy.isEmpty();
    }

}
