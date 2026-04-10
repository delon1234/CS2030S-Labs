import java.util.List;

public class Demo {
  public static void main(String[] args) {
    List<Flat> flats = List.of(
        new Flat("Woodlands", 3, 1200.0, List.of("Woodlands MRT")),
        new Flat("Woodlands", 4, 1500.0, List.of("Admiralty MRT", "Woodlands MRT")),
        new Flat("Tampines", 3, 1300.0, List.of("Tampines MRT")),
        new Flat("Tampines", 5, 1800.0, List.of("Tampines MRT", "Tampines East MRT")),
        new Flat("Jurong", 2, 1000.0, List.of("Boon Lay MRT")),
        new Flat("Jurong", 2, 950.0, List.of("Lakeside MRT")),
        new Flat("Hougang", 3, 1250.0, List.of("Hougang MRT", "Kovan MRT"))
        );

    double budget = 1300.0;

    System.out.println("== Q2a ==");
    System.out.println("\nAll Unique Town Names (Sorted)");
    System.out.println(Q2a.getAllTownNames(flats));

    System.out.println("\n== Q2b ==");
    System.out.println("\nTotal Rent (2-room):");
    System.out.println(Q2b.getTotalRentByRoomType(flats, 2));

    System.out.println("\nTotal Rent (3-room):");
    System.out.println(Q2b.getTotalRentByRoomType(flats, 3));

    System.out.println("\n== Q2c ==");
    System.out.println("\nFlats in Selected Towns Sorted by Rent");
    List<String> selectedTowns = List.of("Tampines", "Woodlands");
    System.out.println(Q2c.getFlatsInGivenTowns(selectedTowns, flats));

    System.out.println("\n== Q2d ==");
    System.out.println("\nMRT Stations Shared by Flats with Rent Difference > 400");
    System.out.println(Q2d.getMRTStationsWithLargeRentGap(flats, 400));

    System.out.println("\nMRT Stations Shared by Flats with Rent Difference > 200");
    System.out.println(Q2d.getMRTStationsWithLargeRentGap(flats, 200));

    System.out.println("\nMRT Stations Shared by Flats with Rent Difference > 500");
    System.out.println(Q2d.getMRTStationsWithLargeRentGap(flats, 500));
  }
}
