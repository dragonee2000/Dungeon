import java.util.HashMap;
import java.util.Map;

public class bag extends Items{
    protected Map<Integer, Items> bag_items;

    public bag(String name, String description, int size){
        super(name, description, size);
        bag_items = new HashMap<Integer, Items>();
        super.item_taken = false;
    }

    public void moveItems(Items bag){
        this.bag_items = bag_items;
        bag_items = null;
    }

    public boolean checkEmpty(){
        return bag_items.isEmpty();
    }

    public boolean containsItem(String str){
        for (int i = 0; i < bag_items.size(); i++){
            if (bag_items.get(i).getName().equals(str)){
                return true;
            }
        }
        return false;
    }

    public void dropBag() {
        item_taken = false;
        super.isDrop = true;
    }
    public weapon getWeapon(int index){
        return (weapon) bag_items.get(index);
    }

    public boolean fillBag(Items item){
        if (item.getSize()+ this.getFilledAmount() > this.getSize()){
            System.out.println("Too full. Find a new bag.");
            return false;
        } else {
            bag_items.put(bag_items.size(), item);
            System.out.println(item.getName() + " taken.");
            return true;
        }
    }

    public void shiftItems(int index){
        for (int i = index; i < this.bag_items.size(); i++){
            this.bag_items.put(i, this.bag_items.get(i+1));
            this.bag_items.remove(i+1);
        }
    }

    public void dropItems(int index) {
        this.bag_items.remove(index - 1);
        this.shiftItems(index-1);
    }

    public int getFilledAmount(){
        int filled = 0;
        for(int i = 0; i < this.bag_items.size(); i++) {
            filled += this.bag_items.get(i).getSize();
        }
        return filled;
    }

    public void inspectInventory(){
        for (int i = 0; i < bag_items.size(); i++) {
            System.out.println(i+1 + ": " + bag_items.get(i).getName() + " - " + bag_items.get(i).getDescription());
        }
    }
}
