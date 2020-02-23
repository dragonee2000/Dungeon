public class key extends Items {
    private boolean key_used;
    private boolean key_taken;
    private int key_value;

    public key(String name, String description, int value){
        super(name, description, 0);
        key_used = false;
        key_taken = false;
        key_value = value;
        super.isDrop = false;
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
    @Override
    public String getName() {
            return super.getName();
    }
    public int getValue(){
        return key_value;
    }
    public void useKey(){
        key_used = true;
    }
    public void dropkey() {
        item_taken = false;
        super.isDrop = true;
    }

}
