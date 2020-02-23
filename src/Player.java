import java.io.InputStreamReader;
import java.util.Scanner;

public class Player {
    protected String player_name;
    protected boolean carrying_bag, hasKey;
    protected Locations current_location;
    protected bag player_bag;
    protected int player_health;
    protected weapon player_weapon;
    protected key attacker_key;


    public Player(String name, Locations location, int health){
        player_name = name;
        carrying_bag = false;
        current_location = location;
        player_health = health;
    }

    public Player(String name, int health, weapon weapon){
        player_name = name;
        player_health = health;
        player_weapon = weapon;
        hasKey = false;
        attacker_key = null;
    }

    public Player(String name, int health, weapon weapon, key key){
        player_name = name;
        player_health = health;
        player_weapon = weapon;
        hasKey = true;
        attacker_key = key;
    }

    public boolean attack(Player attacker, Locations current_location){
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String input = "attack";

        if(player_bag.checkEmpty() == true){
            System.out.println("No weapon. Do not engage!");
        } else {
            this.player_bag.inspectInventory();
            System.out.print("Choose weapon: ");
            int index = scanner.nextInt();
            player_weapon = player_bag.getWeapon(index - 1);
        }
        if (player_weapon == null){
            System.out.println("Go in another direction");
        } else if(!player_weapon.getName().equals("Long Sword") && !player_weapon.getName().equals("Katana") && !player_weapon.getName().equals("Excalibur")
                && !player_weapon.getName().equals("Dagger") && !player_weapon.getName().equals("Minecraft Wooden Sword") && !player_weapon.getName().equals("Claymore")){
            System.out.println("Are you sure you want to die being equipped with a "+ player_weapon.getName());
            System.out.print("> ");
            String input1 = scanner.nextLine();
            if (input1.equals("no")){
                System.out.println("Continue on your journey.");
                return true;
            }
        } else {
            while (((this.player_health > 0) && (attacker.player_health > 0)) && !(input.equals("run"))){
                if (input.toLowerCase().equals("attack")) {
                    attacker.player_health -= this.player_weapon.getDamageGiven();
                    System.out.println("You used a "+this.player_weapon.getName() + " to attack.");
                    if (attacker.player_health > 0) {
                        this.player_health -= attacker.player_weapon.getDamageGiven();
                        System.out.println("The " + attacker.player_name + " used a "+attacker.player_weapon.getName() + " to attack you.");
                    }
                    if (this.player_health > 0 && attacker.player_health > 0){
                        System.out.println("\nYou have " + this.player_health + " health");
                        System.out.println("The " + attacker.player_name +" has "+ attacker.player_health + " left.");
                        System.out.println("attack or run");
                        System.out.print("> ");
                    } else if(attacker.player_health <= 0) {
                        if(attacker.hasKey()) {
                            attacker.attacker_key.droppedItem();
                            current_location.dropItem(attacker.attacker_key);
                            attacker.dropKey(attacker.attacker_key);
                            current_location.attackerDied();
                            System.out.println("The " + attacker.player_name + " is dead.");
                            System.out.println("He dropped a key.");
                            return true;
                        } else {
                            current_location.attackerDied();
                            System.out.println("The " + attacker.player_name + " is dead.");
                            return true;
                        }
                    } else {
                        System.out.println("You died. Game over");
                        return false;
                    }
                }
                input = scanner.nextLine();
            }
        }
        return true;
    }

    public void consumeFood(Food food){
        this.player_health += food.getRestored();
    }

    public boolean hasKey(){
        return hasKey;
    }

    public void dropKey(key key){
        this.attacker_key = null;
    }
    public boolean storeItem(Items item){
        return player_bag.fillBag(item);
    }
    public void dropItem(int index){
        player_bag.dropItems(index);
    }
    public void takeBag(bag item){
        carrying_bag = true;
        player_bag = item;
    }
    public void dropBag(){
        player_bag = null;
        carrying_bag = false;
    }
    public Items getBag(){
        return player_bag;
    }
    public void inspectBag(){
        System.out.println(player_bag.item_description);
    }
}
