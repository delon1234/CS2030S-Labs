import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Q2b {

    // 2b. Get total rent for a given room type
    public static double getTotalRentByRoomType(List<Flat> flats, int roomCount) {
      /*
        double total = 0;
        for (Flat f : flats) {
            if (f.getRooms() == roomCount) {
                total += f.getRent();
            }
        }
        return total;
        */
      return flats.stream()
        .filter(x -> x.getRooms() == roomCount)
        .map(Flat::getRent)
        .reduce(0.0, (acc, x) -> acc + x);
    }

}
