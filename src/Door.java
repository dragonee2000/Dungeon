import java.util.HashMap;
import java.util.Map;

public class Door {
    protected String door_description;
    private Map<String, Locations> door_connection;
    private boolean door_open;
    int door_value;

    Door(String description){
        door_description = description;
        door_open = false;
        door_connection = new HashMap<String, Locations>();
    }
    Door(String description, int value){
        door_description = description;
        door_open = false;
        door_connection = new HashMap<String, Locations>();
        door_value = value;
    }
    public boolean isOpen(){
        return door_open;
    }
    public void openDoor(){
        door_open = true;
    }
    public int getValue(){
        return door_value;
    }

}
