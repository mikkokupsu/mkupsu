public class RnaTranscription {
    public String transcribe(String dnaStrand) {
        if (dnaStrand == null) {
            return null;
        }
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < dnaStrand.length(); i++) {
            builder.append(toRna(dnaStrand.charAt(i)));
        }
        return builder.toString();
    }

    private String toRna(final char dna) {
        switch (dna) {
            case 'G': return "C";
            case 'C': return "G";
            case 'T': return "A";
            case 'A': return "U";
            default: throw new IllegalArgumentException(
                String.format("Unable to transform %s to RNA", dna));
        }
    }
}
