import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2d {

    // 2d. Find MRT stations shared by flats with rent difference > X
    public static List<String> getMRTStationsWithLargeRentGap(List<Flat> flats, double rentThreshold) {
      /*
        List<String> result = new ArrayList<>();

        for (int i = 0; i < flats.size(); i++) {
            Flat f1 = flats.get(i);
            for (int j = i + 1; j < flats.size(); j++) {
                Flat f2 = flats.get(j);

                double rentDiff = Math.abs(f1.getRent() - f2.getRent());
                if (rentDiff > rentThreshold) {
                    for (String station : f1.getNearbyMRTStations()) {
                        if (f2.getNearbyMRTStations().contains(station) && !result.contains(station)) {
                            result.add(station);
                        }
                    }
                }
            }
        }

        Collections.sort(result);
        return result;
        */
      return flats.stream().flatMap(f1 -> 
          flats.stream()
            .filter(f2 -> f2 != f1) // Ensure not same flat
            .filter(f2 -> Math.abs(f1.getRent() - f2.getRent()) > rentThreshold)
            .flatMap(f2 -> f2.getNearbyMRTStations().stream()
              .filter(station -> f1.getNearbyMRTStations().contains(station)))
          )
        .distinct()
        .sorted()
        .toList();
    }

}
