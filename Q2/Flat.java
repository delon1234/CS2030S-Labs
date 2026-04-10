import java.util.List;

public class Flat {
    private final String town;
    private final int rooms;
    private final double rent;
    private final List<String> nearbyMRTStations;

    public Flat(String town, int rooms, double rent, List<String> nearbyMRTStations) {
        this.town = town;
        this.rooms = rooms;
        this.rent = rent;
        this.nearbyMRTStations = nearbyMRTStations;
    }

    public String getTown() {
        return town;
    }

    public int getRooms() {
        return rooms;
    }

    public double getRent() {
        return rent;
    }

    public List<String> getNearbyMRTStations() {
        return nearbyMRTStations;
    }

    @Override
    public String toString() {
        return rooms + "-room in " + town + " ($" + rent + ")";
    }
}
