public class weapon extends Items{
    private int item_damage;

    public weapon(String name, String description, int size, int damage){
        super(name, description, size);
        item_damage = damage;
        super.item_taken = false;
        super.isDrop = false;
    }

    public boolean isTaken(){ return item_taken; }
    public int showDamage(){ return item_damage; }
    public int getDamageGiven(){ return item_damage; }

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
    public void dropWeapon() {
        item_taken = false;
        super.isDrop = true;
    }
}
