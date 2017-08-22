class Scrabble {

    private final String word;

    Scrabble(final String word) {
        this.word = word;
    }

    private static int charToScore(final char singleChar) {
        switch (singleChar) {
            case 'A': // fall through
            case 'E': // fall through
            case 'I': // fall through
            case 'O': // fall through
            case 'U': // fall through
            case 'L': // fall through
            case 'N': // fall through
            case 'R': // fall through
            case 'S': // fall through
            case 'T': return 1;
            case 'D': // fall through
            case 'G': return 2;
            case 'B': // fall through
            case 'C': // fall through
            case 'M': // fall through
            case 'P': return 3;
            case 'F': // fall through
            case 'H': // fall through
            case 'V': // fall through
            case 'W': // fall through
            case 'Y': return 4;
            case 'K': return 5;
            case 'J': // fall through
            case 'X': return 8;
            case 'Q': // fall through
            case 'Z': return 10;
            default: return 0;
        }
    }

    int getScore() {
        int score = 0;
        for (final char singleChar : word.toUpperCase().toCharArray()) {
            score += charToScore(singleChar);
        }
        return score;
    }

}
