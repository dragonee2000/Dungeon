public class Items {
    protected int item_size;
    protected String item_name;
    protected String item_description;
    protected boolean item_taken, isDrop;

    public Items(String name, String description, int size){
        item_name = name;
        item_description = description;
        item_size = size;
        item_taken = false;
        isDrop = false;
    }

    public Items getItem(){
        item_taken = true;
        return this;
    }

    public void droppedItem(){ isDrop = true; }
    public boolean isDrop(){ return isDrop; }
    public String getName(){ return item_name; }
    public String getDescription(){ return item_description; }

    public int getSize(){ return item_size; }
}
