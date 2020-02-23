import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private static Locations main_cell, prisoner, backpack1_loc, guard_post;
    private static Locations corridor1_0, corridor1_1, corridor1_2, corridor1_3, corridor2_0, corridor3_0, corridor3_1, corridor3_2;
    private static Locations corridor4_0, corridor6_0, corridor6_1, corridor6_2, corridor6_3;
    private static Locations corridor_door1_1_0, corridor_door1_1_1, corridor_door1_1_2, corridor_door1_1_3, corridor_door1_1_4, corridor_door1_1_5;
    private static Locations fork1, fork2, fork3;
    private static Locations currentLocation, door1, stairwell2, stairwell1, bathroom, door2, Arena, Exit;
    private static Locations corridor_2_1_0, corridor_2_1_1, corridor_2_1_2, corridor_2_1_3, corridor_2_2_0, corridor_2_2_1, corridor_2_3_0, corridor_2_3_1;
    private static Items backpack1, backpack2;
    private static weapon LongSword, minecraft_wooden, LongSword2, Excalibur, dagger, katana, Dragon;
    private static Player guard, guard2, dragon, guard3, guard4;
    private static key key1, key2, key3, key4;
    private static Door Door1, Window, chest_lid, Door2;
    private static Chest LargeChest;
    private static Food apple, potion, banana;
    public static Locations setupGame(){
        Map<String, Locations> exit = new HashMap<>();
        main_cell = new Locations("Prison Cell", "Cell is empty.\nDoor left partially locked.");
        prisoner = new Locations("Prison cell", "A prisoner in a cell.");
        minecraft_wooden = new weapon("Minecraft wooden sword", "A pixelated wooden sword that only splinters.", 3, 3);
        key1 = new key("key", "Looks like a normal key.", 1);
        key2 = new key("key", "Key for a chest", 2);
        guard = new Player("Main Cell Guard", 30, minecraft_wooden, key2);
        guard_post = new Locations("Guard Post", "A fork to three passages: northeast, west and south", guard);
        corridor1_0 = new Locations("Corridor", "Dimly lit. Not able to see anything around");
        backpack1 = new bag("bag", "A messenger bag that holds 10L", 10);
        backpack1_loc = new Locations("Corridor","Dimly lit. ", backpack1);
        corridor1_1 = new Locations("Corridor", "Dimly lit. Not able to see anything around");
        corridor1_2 = new Locations("Corridor", "Dimly lit. Not able to see anything around");
        corridor1_3= new Locations("Corridor", "Dimly lit. Not able to see anything around");
        corridor2_0 = new Locations("Corridor", "Dimly lit. Not able to see anything around");
        fork1 = new Locations("Fork", "You are at a fork of four passages: north, east, south and west.");
        fork2 = new Locations("Fork", "You are at a fork of three passages: northeast, west and south.");
        fork3 = new Locations("Fork", "You are at a fork of three passages: north, southeast and southwest.");
        corridor3_0 = new Locations("Corridor", "Medieval torch on wall. Looks like a short corridor.");
        corridor3_1 = new Locations("Corridor", "Brightly lit by torch.");
        LongSword = new weapon("Long Sword", "Looks like the Excalibur, most likely not though.", 5, 8);
        corridor3_2 = new Locations("Corridor", "Looks like a corner.", LongSword);
        corridor4_0 = new Locations("Corridor", "Old, dirty walls on both sides.");
        Door1 = new Door("Plain wooden door", 1);
        door1 = new Locations("Door", "Looks like a wooden door. Key required.", Door1);
        LongSword2= new weapon("Claymore", "Definitely a claymore.",5, 9);
        katana = new weapon("Katana", "The holy grail of swords. Stamped 'Made in Japan'", 12, 25);
        dagger = new weapon("Dagger", "A really small dagger. Looks good for standby.", 3, 5);
        Excalibur = new weapon("Excalibur", "'Used by King Arthur'...", 10, 20);
        corridor_door1_1_0 = new Locations("Armoury", "A large room filled with loads of weapons.");
        corridor_door1_1_1 = new Locations("Armoury", "A Claymore hooked on the wall", LongSword2);
        corridor_door1_1_2 = new Locations("Armoury", "A Katana placed on wooden holders, hanging on the wall", katana);
        corridor_door1_1_3 = new Locations("Armoury", "A dagger hangs on the wall", dagger);
        corridor_door1_1_4 = new Locations("Armoury", "An Excalibur hooked on the wall", Excalibur);
        corridor_door1_1_5 = new Locations("Armoury", "A Minecraft Wooden Sword hooked on the wall", minecraft_wooden);
        backpack2 = new bag("bag", "A vintage tactical bag that holds 25L", 25);
        key3 = new key ("key", "A weird looking key", 3);
        key4 = new key("key", "Metal key", 4);
        chest_lid = new Door("Chest lid", 2);
        LargeChest = new Chest("Chest", "An old wooden chest with dragon carvings.", 80, chest_lid);
        corridor6_0 = new Locations("Corridor", "Looks like a corner");
        corridor6_1 = new Locations("Corridor", "Dimly lit. Not able to see anything around.");
        corridor6_2 = new Locations("Corridor", "A sign that indicates a left turn.", LargeChest);
        corridor6_3 = new Locations("Corridor", "Dimly lit.");
        stairwell1 = new Locations ("Stairwell", "There's a stairs that leads upward. Looks kinda sketchy. 'No entry'");
        stairwell2 = new Locations ("Stairwell", "There's a stairs that leads upward. Looks kinda sketchy.");
        Window = new Door("Window", 3);
        bathroom = new Locations ("Bathroom", "Showers with a cracked toilet seat.\nThere is also a metal grill up above for air circulation", Window);
        corridor_2_1_0 = new Locations("Stairwell", "Sounds of cheering. ");
        corridor_2_1_1 = new Locations("Corridor", "Sounds continue. Dimly lit.");
        apple = new Food ("food", "An apple that looks like the fruit and not the smartphone.", 3, 10);
        banana = new Food("food", "A banana a day keeps the doctors away.", 3, 15);
        potion = new Food("food", "A bottle of potion", 4, 30);
        corridor_2_1_2 = new Locations("Corridor", "Sounds continue. Dimly lit.", apple);
        guard = new Player("Ordinary Guard", 55, LongSword2);
        corridor_2_1_3 = new Locations("Corridor", "Ordinary Guard. Right turn indicated.", guard);
        corridor_2_2_0 = new Locations("Corridor","Dimly lit.");
        corridor_2_2_1 = new Locations("Corridor", "Right turn indicated.", potion);
        guard = new Player("Ordinary Guard", 55, dagger, key4);
        corridor_2_3_0 = new Locations("Corridor", "There is a metal grill ahead. The cheering gets louder.", guard);
        corridor_2_3_1 = new Locations("Corridor", "Corridor with Metal Grill. A sign 'ENTER/EXIT' above'.");
        Door2 = new Door("Metal Grill", 4);
        door2 = new Locations("Metal Grill", "Crowd roars from out and beyond.", Door2);
        Dragon = new weapon("claw", "dragon's claw", 10, 15);
        dragon = new Player("Dragon", 100, Dragon, key3);
        Arena = new Locations("Arena", "'Kill the dragon!... Kill the dragon!'", dragon);
        Exit = new Locations("Exit", "Mission Accomplished! Now go live your life.");

        // adding items into chest
        LargeChest.fillChest(key1);
        LargeChest.fillChest(backpack2);
        LargeChest.fillChest(banana);
        LargeChest.fillChest(banana);
        LargeChest.fillChest(apple);
        LargeChest.fillChest(apple);
        LargeChest.fillChest(potion);

        main_cell.addConnectedLocation("e", corridor1_0);
        main_cell.addConnectedLocation("n", corridor2_0);
        corridor2_0.addConnectedLocation("n", guard_post);
        corridor2_0.addConnectedLocation("s", main_cell);
        guard_post.addConnectedLocation("s", corridor2_0);
        guard_post.addConnectedLocation("ne", fork1);
        guard_post.addConnectedLocation("w", corridor6_0);
        corridor6_0.addConnectedLocation("e", guard_post);
        corridor6_0.addConnectedLocation("s", corridor6_1);
        corridor6_1.addConnectedLocation("s", corridor6_2);
        corridor6_1.addConnectedLocation("n", corridor6_0);
        corridor6_2.addConnectedLocation("e", corridor6_3);
        corridor6_2.addConnectedLocation("n", corridor6_1);
        corridor6_3.addConnectedLocation("w", corridor6_2);
        corridor6_3.addConnectedLocation("e", stairwell2);
        stairwell2.addConnectedLocation("w", corridor6_3);
        corridor1_0.addConnectedLocation("w", main_cell);
        corridor1_0.addConnectedLocation("e", corridor1_1);
        corridor1_1.addConnectedLocation("w", corridor1_0);
        corridor1_1.addConnectedLocation("e", backpack1_loc);
        backpack1_loc.addConnectedLocation("w", corridor1_1);
        backpack1_loc.addConnectedLocation("n", corridor1_2);
        corridor1_2.addConnectedLocation("s", backpack1_loc);
        corridor1_2.addConnectedLocation("n", corridor1_3);
        corridor1_3.addConnectedLocation("s", corridor1_2);
        corridor1_3.addConnectedLocation("n", fork1);
        fork1.addConnectedLocation("w", guard_post);
        fork1.addConnectedLocation("n", prisoner);
        fork1.addConnectedLocation("s", corridor1_3);
        prisoner.addConnectedLocation("s", fork1);
        fork1.addConnectedLocation("e", fork2);
        fork2.addConnectedLocation("w", fork1);
        fork2.addConnectedLocation("ne", fork3);
        fork2.addConnectedLocation("s", corridor3_0);
        fork3.addConnectedLocation("sw", fork2);
        fork3.addConnectedLocation("n", bathroom);
        fork3.addConnectedLocation("se", stairwell1);
        stairwell1.addConnectedLocation("nw", fork3);
        bathroom.addConnectedLocation("s", fork3);
        corridor3_0.addConnectedLocation("n", fork2);
        corridor3_0.addConnectedLocation("s", corridor3_1);
        corridor3_1.addConnectedLocation("n", corridor3_0);
        corridor3_1.addConnectedLocation("sw", corridor3_2);
        corridor3_2.addConnectedLocation("ne", corridor3_1);
        corridor3_2.addConnectedLocation("sw", corridor4_0);
        corridor4_0.addConnectedLocation("w", door1);
        corridor4_0.addConnectedLocation("ne", corridor3_2);
        door1.addConnectedLocation("e", corridor4_0);
        // after wooden door
        door1.addConnectedLocation("in", corridor_door1_1_0);
        corridor_door1_1_0.addConnectedLocation("e", door1);
        corridor_door1_1_0.addConnectedLocation("n", corridor_door1_1_1);
        corridor_door1_1_1.addConnectedLocation("w", corridor_door1_1_2);
        corridor_door1_1_1.addConnectedLocation("s", corridor_door1_1_0);
        corridor_door1_1_2.addConnectedLocation("e", corridor_door1_1_1);
        corridor_door1_1_0.addConnectedLocation("s", corridor_door1_1_3);
        corridor_door1_1_3.addConnectedLocation("n", corridor_door1_1_0);
        corridor_door1_1_3.addConnectedLocation("w", corridor_door1_1_4);
        corridor_door1_1_4.addConnectedLocation("e", corridor_door1_1_3);
        corridor_door1_1_0.addConnectedLocation("w", corridor_door1_1_5);
        corridor_door1_1_5.addConnectedLocation("e", corridor_door1_1_0);

        // second floor
        stairwell2.addConnectedLocation("up", corridor_2_1_0);
        corridor_2_1_0.addConnectedLocation("down", stairwell2);
        corridor_2_1_0.addConnectedLocation("w", corridor_2_1_1);
        corridor_2_1_1.addConnectedLocation("e", corridor_2_1_0);
        corridor_2_1_1.addConnectedLocation("w", corridor_2_1_2);
        corridor_2_1_2.addConnectedLocation("e", corridor_2_1_1);
        corridor_2_1_2.addConnectedLocation("w", corridor_2_1_3);
        corridor_2_1_3.addConnectedLocation("e", corridor_2_1_2);
        corridor_2_1_3.addConnectedLocation("n", corridor_2_2_0);
        corridor_2_2_0.addConnectedLocation("s", corridor_2_1_3);
        corridor_2_2_0.addConnectedLocation("e", corridor_2_2_1);
        corridor_2_2_1.addConnectedLocation("w", corridor_2_2_0);
        corridor_2_2_1.addConnectedLocation("s", corridor_2_3_0);
        corridor_2_3_0.addConnectedLocation("n", corridor_2_2_1);
        corridor_2_3_0.addConnectedLocation("s", corridor_2_3_1);
        corridor_2_3_1.addConnectedLocation("s", door2);
        door2.addConnectedLocation("n", corridor_2_3_1);
        door2.addConnectedLocation("in", Arena);
        Arena.addConnectedLocation("out", door2);

        currentLocation = main_cell;
        return main_cell;
    }

    public static Locations getCurrentLocation(){
        return currentLocation;
    }

    public static Locations getStartingLocation(){
        return main_cell;
    }

    /*public static Locations changePosition(Locations newroom){
        currentLocation = newroom;
    }*/

    public static Locations getNextLoc(String dir){
        Map<String, Locations> connectedLocation = currentLocation.getConnectedLocation();
        if (!connectedLocation.containsKey(dir)) {
            System.out.println("You can't go that way.");
        /*} else if (currentLocation.requiresKey() && !currentLocation.inspectDoor()){
            System.out.println("You need a key. (If you do have a key enter 'in')");*/
        } else{
            currentLocation = connectedLocation.get(dir);
            if (currentLocation.hasVisited()){
                System.out.println(currentLocation.getLocation_name());

            } else {
                System.out.println(currentLocation.getLocation_name());
                System.out.println(currentLocation.getLocation_description());
                currentLocation.visited();
                if (currentLocation.hasAttacker()) {
                    System.out.println("A " + currentLocation.getAttacker().player_name + " is walking back and forth and has yet to see you.");
                }
            }
        }
        return currentLocation;
    }

    public static String getUserRoomChoice(String choice){
        if (choice.toLowerCase().equals("north") || choice.equals("N"))
            choice = "n";
        else if (choice.toLowerCase().equals("south") || choice.equals("S"))
            choice = "s";
        else if (choice.toLowerCase().equals("west") || choice.equals("W"))
            choice = "w";
        else if (choice.toLowerCase().equals("east")|| choice.equals("E"))
            choice = "e";
        else if (choice.toLowerCase().equals("northeast") || choice.equals("NE"))
            choice = "ne";
        else if (choice.toLowerCase().equals("southeast") || choice.equals("SE"))
            choice = "se";
        else if (choice.toLowerCase().equals("northwest") || choice.equals("NW"))
            choice = "nw";
        else if (choice.toLowerCase().equals("southwest") || choice.equals("SW"))
            choice = "sw";
        else if (choice.toLowerCase().equals("down") || choice.equals("D"))
            choice = "d";
        else if (choice.toLowerCase().equals("up") || choice.equals("U"))
            choice = "up";
        else if (choice.toLowerCase().equals("in"))
            choice = "in";
        else if (choice.toLowerCase().equals("out"))
            choice = "out";
        return choice;
    }
}
