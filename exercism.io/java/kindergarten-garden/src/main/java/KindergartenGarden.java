import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KindergartenGarden {

    private static final String[] CHILDREN = {
            "Alice", "Bob", "Charlie", "David",
            "Eve", "Fred", "Ginny", "Harriet",
            "Ileana", "Joseph", "Kincaid", "Larry"
    };

    private final Map<String, List<Plant>> childPlants = new HashMap<>();

    public KindergartenGarden(final String plants) {
        this(plants, CHILDREN);
    }

    private Plant charToPlant(final char single) {
        return Plant.getPlant(single);
    }

    public KindergartenGarden(final String plants, final String[] children) {
        Arrays.sort(children);
        final String[] plantRows = plants.split("\n");
        final int maxPlants = plantRows[0].length() / 2;
        for (int i = 0; i < children.length && i < maxPlants; i++) {
            final List<Plant> childsPlants = new ArrayList<>(4);
            for (int j = 0; j < 2; j++) {
                childsPlants.add(charToPlant(plantRows[j].charAt(2 * i)));
                childsPlants.add(charToPlant(plantRows[j].charAt(2 * i + 1)));
            }
            childPlants.put(children[i], childsPlants);
        }
    }

    public List<Plant> getPlantsOfStudent(final String student) {
        return childPlants.get(student);
    }
}
