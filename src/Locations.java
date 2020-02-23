import java.util.HashMap;
import java.util.Map;

public class Locations {
    private Map<String, Locations> connectedLocation;
    private String location_name;
    private String location_description;
    private Items location_item;
    private boolean hasItem, requireKey, visited;
    private Player area_attacker;
    private Door location_door;

    public Locations(String roomName){
        location_name = roomName;
        location_description = "";
        this.connectedLocation = new HashMap<String, Locations>();
        hasItem = false;
        visited = false;
        location_item = null;
        hasItem = true;
    }

    public Locations(String roomName, String roomDescription){
        location_name = roomName;
        location_description = roomDescription;
        this.connectedLocation = new HashMap<String, Locations>();
        hasItem = false;
        requireKey = false;
        location_item = null;
        hasItem = false;
        area_attacker = null;
        visited = false;
    }
    public Locations(String name, String doorDescription, Door door){
        location_name = name;
        location_description = doorDescription;
        this.connectedLocation = new HashMap<String, Locations>();
        location_door = door;
        requireKey = true;
        hasItem = false;
        location_item = null;
        area_attacker = null;
        visited = false;
    }
    public Locations(String roomName, String roomDescription, Items room_item){
        location_name = roomName;
        location_description = roomDescription;
        this.connectedLocation = new HashMap<String, Locations>();
        location_item = room_item;
        hasItem = true;
        requireKey = false;
        area_attacker = null;
        visited = false;
    }

    public Locations(String roomName, String roomDescription, Player attacker){
        location_name = roomName;
        location_description = roomDescription;
        this.connectedLocation = new HashMap<String, Locations>();
        area_attacker = attacker;
        location_item = null;
        requireKey = false;
        hasItem = false;
        visited = false;
    }
    public void dropItem(Items dropped){
        this.hasItem = true;
        this.location_item = dropped;
        dropped.droppedItem();
    }
    public void attackerDied(){
        area_attacker = null;
    }

    public void locationDropBag(bag drop){
        this.hasItem = true;
        this.location_item = drop;
        drop.dropBag();
    }

    public Player getAttacker(){
        return area_attacker;
    }

    public Items getItem(){
        hasItem = false;
        return location_item;
    }

    public Door getDoor(){
        return location_door;
    }

    public boolean inspectDoor(){
        return location_door.isOpen();
    }
    public void open_door(){
        location_door.openDoor();
    }

    public boolean hasAttacker(){
        if (area_attacker == null){
            return false;
        } else
            return true;
    }

    public boolean hasVisited(){
        return visited;
    }

    public void visited(){
        visited = true;
    }

    public boolean requiresKey(){
        return requireKey;
    }
    public Items inspectItem(){
        return location_item;
    }
    public boolean isHasItem(){
        return hasItem;
    }

    public String getLocation_name(){
        return location_name;
    }

    public String getLocation_description(){ return location_description; }

    public Map getConnectedLocation(){
        return this.connectedLocation;
    }

    public void addConnectedLocation(String dir, Locations connectedLocation){
        this.connectedLocation.put(dir, connectedLocation);
    }

    public int getNumberOfConnectedRooms(){
        return connectedLocation.size();
    }

    public void printConnectedRooms(){
        for (Map.Entry<String, Locations> each: connectedLocation.entrySet()){
            System.out.println(each.getKey() + ": " + each.getValue().location_name);
        }
    }
    public void printConnectedRoomsNDesc(){
        for (Map.Entry<String, Locations> each: connectedLocation.entrySet()){
            System.out.println(each.getKey() + ": " + each.getValue().location_name);
            System.out.println(each.getValue().location_description);
        }
    }


}
