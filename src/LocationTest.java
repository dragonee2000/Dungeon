import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LocationTest {
    public static void main(String[] args){
        Locations main_cell = new Locations("Main cell");
        assertEquals("Main cell", main_cell.getLocation_name());
        assertEquals("", main_cell.getLocation_description());

        Locations prisoner = new Locations("Prisoner", "A room with a prisoner");
        assertEquals("Prisoner", prisoner.getLocation_name());
        assertEquals("A room with a prisoner", prisoner.getLocation_description());

        Locations vestsite = new Locations("Vestsite");
        main_cell.addConnectedLocation("e", prisoner);
        main_cell.addConnectedLocation("n", vestsite);
        prisoner.addConnectedLocation("w", main_cell);
        prisoner.addConnectedLocation("se", vestsite);
        vestsite.addConnectedLocation("s", main_cell);
        vestsite.addConnectedLocation("nw", prisoner);

        /*System.out.println("Connected rooms of: " + main_cell.getName());
        main_cell.printConnectedRooms();
        System.out.println("Connected rooms of: " + prisoner.getName());
        prisoner.printConnectedRooms();
        System.out.println("Connected rooms of: " + vestsite.getName());
        vestsite.printConnectedRooms();

        prisoner.addConnectedLocation();

        System.out.println("Connected rooms of: " + main_cell.getName());
        main_cell.printConnectedRooms();
        System.out.println("Connected rooms of: " + prisoner.getName());
        prisoner.printConnectedRooms();
        System.out.println("Connected rooms of: " + vestsite.getName());
        vestsite.printConnectedRooms();
*/
        System.out.println("Connected rooms of: " + main_cell.getLocation_name());
        Map<String, Locations> obtained =  main_cell.getConnectedLocation();
        System.out.println("Connected room at east, should be Prisoner: "+ obtained.get("e").getLocation_name());
        System.out.println("Connected room at north, should be Vestsite: "+ obtained.get("n").getLocation_name());

        Locations prisoner2 =  obtained.get("e");
        Map<String, Locations> obtained2 =  prisoner2.getConnectedLocation();
        System.out.println("Connected rooms of: " + prisoner2.getLocation_name());
        System.out.println("Connected room at west, should be main cell: "+ obtained2.get("w").getLocation_name());
        System.out.println("Connected room at south east, should be vestsite: "+ obtained2.get("se").getLocation_name());

        obtained =  vestsite.getConnectedLocation();
        System.out.println("Connected rooms of: " + vestsite.getLocation_name());
        System.out.println("Connected room at west, should be main cell: "+ obtained.get("s").getLocation_name());
        System.out.println("Connected room at south east, should be prisoner: "+ obtained.get("nw").getLocation_name());


        //System.out.println("number of connected rooms, should be 2: " + main_cell.getNumberOfConnectedRooms());
    }


}
