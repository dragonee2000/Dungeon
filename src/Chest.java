import java.util.HashMap;
import java.util.Map;

public class Chest extends Items{
    protected Map<Integer, Items> chest_items;
    protected Door chest_lid;

    public boolean isopen(){
        return chest_lid.isOpen();
    }

    public Door getLid(){
        return chest_lid;
    }

    public void openChest(){
        chest_lid.openDoor();
    }

    public Chest(String name, String description, int size, Door lid){
        super(name, description, size);
        chest_items = new HashMap<Integer, Items>();
        chest_lid = lid;
    }


    public void shiftItems(int index){
       for (int i = index; i < this.chest_items.size(); i++){
           this.chest_items.put(i, this.chest_items.get(i+1));
           this.chest_items.remove(i+1);
       }
    }

    public void tookItem(int index){
        this.chest_items.remove(index -1);
        this.shiftItems(index-1);
    }

    public void fillChest(Items item) { chest_items.put(chest_items.size(), item);}

    public void inspectChest(){
        for (int i = 0; i < this.chest_items.size(); i++) {
            System.out.println(i+1 + ": " + this.chest_items.get(i).getName() + " - " + this.chest_items.get(i).getDescription());
        }
    }

    @Override
    public int getSize() {
        return super.getSize();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
