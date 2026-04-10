import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2c {

    // 2c. Get all flats in selected towns, sorted by rent (ascending)
    public static List<Flat> getFlatsInGivenTowns(List<String> towns, List<Flat> flats) {
        List<Flat> result = new ArrayList<>();
        for (String town : towns) {
            for (Flat f : flats) {
                if (f.getTown().equals(town)) {
                    result.add(f);
                }
            }
        }
        result.sort((f1, f2) -> Double.compare(f1.getRent(), f2.getRent()));
        return result;
    }

}
