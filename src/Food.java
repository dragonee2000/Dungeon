public class Food extends Items {
    int health_restore;
    public Food(String name, String description, int size, int health){
        super(name, description, size);
        health_restore = health;
    }

    public int getRestored(){
        return health_restore;
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
    @Override
    public int getSize() {
        return super.getSize();
    }

}
