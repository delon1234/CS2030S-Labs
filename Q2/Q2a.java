import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2a {

    // 2a. Get sorted list of unique town names
    public static List<String> getAllTownNames(List<Flat> flats) {
      /*
        List<String> result = new ArrayList<>();

        for (Flat f : flats) {
            String town = f.getTown();
            if (!result.contains(town)) {
                result.add(town);
            }
        }

        Collections.sort(result);
        return result;
      */
      return flats.stream()
        .map(flat -> flat.getTown())
        .distinct()
        .sorted()
        .toList();
    }

}
