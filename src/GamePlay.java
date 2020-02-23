import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

public class GamePlay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        boolean running = true;
        while (running) {
            Locations currentLocation = GameMap.getStartingLocation();
            String name = printSetup();
            Player player1 = new Player(name, currentLocation, 50);
            Items roomItem = null;
            bag carrying;
            Random rand = new Random();
            int randChoice = rand.nextInt(5);
            currentLocation = GameMap.setupGame();
            System.out.println(currentLocation.getLocation_name());
            System.out.println(currentLocation.getLocation_description());
            currentLocation.visited();
            boolean alive = true, stored = false ,eat;
            String input = "";
            do {
                eat = false;
                System.out.print("> ");
                input = scanner.nextLine();
                if (input.equals("look")) {
                    System.out.println(currentLocation.getLocation_name());
                    System.out.println(currentLocation.getLocation_description());
                } else if (input.equals("inspect room") || input.equals("inspect")) {
                    if ((currentLocation.isHasItem()) && (!currentLocation.inspectItem().isDrop())) {
                        if (randChoice == 1) {
                            System.out.println("A " + currentLocation.inspectItem().getName() + " hooked on the wall.");
                        } else if (randChoice == 2) {
                            System.out.println("A " + currentLocation.inspectItem().getName() + " is on the table by the wall.");
                        } else if (randChoice == 3) {
                            System.out.println("A " + currentLocation.inspectItem().getName() + " is camouflaged by the wall.");
                        } else if (randChoice == 4) {
                            System.out.println("A " + currentLocation.inspectItem().getName() + " is under the table.");
                        } else {
                            System.out.println("A " + currentLocation.inspectItem().getName() + " hanging from the ceiling like a trap.");
                        }
                    } else if (currentLocation.isHasItem() && currentLocation.inspectItem().isDrop()) {
                        System.out.println("A " + currentLocation.inspectItem().getName() + " is on the floor");
                    } else if (currentLocation.hasAttacker()) {
                        System.out.println("A " + currentLocation.getAttacker().player_name + " is walking back and forth and is ready to engage.");
                    } else {
                        System.out.println("Nothing much to inspect");
                    }
                } else if (input.equals("connections") || input.equals("connected locations")) {
                    currentLocation.printConnectedRooms();
                } else if (input.contains("connections description") || input.equals("connected locations with description")) {
                    currentLocation.printConnectedRoomsNDesc();

                    // GET ITEMS / TAKE
                } else if (input.equals("take") || input.equals("get item")) {
                    if (currentLocation.isHasItem()) {
                        if (currentLocation.inspectItem().getName().equals("bag") && !player1.carrying_bag) {
                            roomItem = currentLocation.getItem();
                            player1.takeBag((bag) roomItem);
                            System.out.println(roomItem.getName() + " taken.");
                            // move items from one bag to the other*/
                        } else if ((currentLocation.inspectItem().getName().equals("Chest"))) {
                            Chest chest = (Chest) currentLocation.inspectItem();
                            if (chest.isopen()) {
                                chest.inspectChest();
                                System.out.print("Enter index to take: ");
                                int index = scanner.nextInt();
                                Items item = chest.chest_items.get(index - 1);
                                if (!player1.carrying_bag && item.getName().equals("bag")) {
                                    player1.takeBag((bag) item);
                                    chest.tookItem(index);
                                    input = scanner.nextLine();
                                } else {
                                    stored = player1.storeItem(item);
                                    if (stored) {
                                        chest.tookItem(index);
                                        input = scanner.nextLine();
                                    }
                                }
                            } else
                                System.out.println("Chest is still locked. Find key to open");
                        } else if (!player1.carrying_bag) {
                            System.out.println("Cannot take item. Look for a bag first.");
                        } else if (player1.carrying_bag) {
                            roomItem = currentLocation.inspectItem();
                            stored = player1.storeItem(roomItem);
                            if (stored)
                                currentLocation.getItem();
                        } else if (roomItem.getClass() == weapon.class) {
                            player1.player_weapon = (weapon) roomItem;
                        }
                    } else {
                        System.out.println("No items to be taken.");
                    }
                } else if (input.equals("inspect bag")) {
                    if (!player1.carrying_bag) {
                        System.out.println("You have no bag.");
                    } else {
                        player1.inspectBag();
                    }
                } else if (input.equals("inspect inventory") || input.equals("inventory")) {
                    carrying = (bag) player1.getBag();
                    if (!player1.carrying_bag) {
                        System.out.println("You are not carrying any item.");
                    } else {
                        System.out.println("Your bag is " + carrying.getFilledAmount() + "/" + carrying.item_size + " filled.");
                        carrying.inspectInventory();
                    }
                } else if (input.equals("open chest") || input.equals("open")) {
                    if (currentLocation.isHasItem() && currentLocation.inspectItem().getName().equals("Chest")) {
                        Chest chest = (Chest) currentLocation.inspectItem();
                        if (player1.carrying_bag) {
                            carrying = (bag) player1.getBag();
                            if (carrying.containsItem("key")) {
                                carrying.inspectInventory();
                                System.out.print("Enter index of key to use: ");
                                String input1 = scanner.nextLine();
                                while (!input1.equals("no key") && !chest.isopen()) {
                                    int key = Integer.parseInt(input1);
                                    if (carrying.bag_items.get(key - 1).getName().equals("key")) {
                                        key key_value = (key) carrying.bag_items.get(key - 1);
                                        if (key_value.getValue() == (chest.getLid().getValue())) {
                                            chest.openChest();
                                            carrying.bag_items.remove(key - 1);
                                        } else {
                                            System.out.println("Try another key");
                                        }
                                        if (chest.isopen()) {
                                            System.out.println("Chest opened");
                                            key_value.useKey();
                                            carrying.bag_items.remove(key - 1);
                                            carrying.shiftItems(key - 1);
                                        } else {
                                            System.out.print("> ");
                                            input1 = scanner.nextLine();
                                        }
                                    } else {
                                        System.out.println("Try another index.");
                                        System.out.print("> ");
                                        input1 = scanner.nextLine();
                                    }
                                }
                            } else {
                                System.out.println("No key(s). Not able to open chest.");
                            }
                        } else
                            System.out.println("No bag and key(s). Look for both before trying again.");
                    }
                    // ATTACK
                } else if (input.equals("attack")) {
                    if (!player1.carrying_bag && !(currentLocation.getAttacker() == null)) {
                        System.out.println("Do not engage! I repeat... Do not engage!");
                    } else {
                        if (!(currentLocation.getAttacker() == null)) {
                            alive = player1.attack(currentLocation.getAttacker(), currentLocation);
                        } else {
                            System.out.println("No one to attack. Try killing yourself... Just kidding, don't do it");
                        }
                    }
                }else if (input.equals("consume")){
                    carrying = (bag) player1.getBag();
                    if (carrying.containsItem("food")) {
                        carrying.inspectInventory();
                        System.out.print("Enter index of food to consume: ");
                        String input1 = scanner.nextLine();
                        while (!input1.equals("no food") && !carrying.checkEmpty() && !eat) {
                            int food = Integer.parseInt(input1);
                            System.out.println(carrying.bag_items.get(food - 1).getName().equals("food"));
                            if (carrying.bag_items.get(food - 1).getName().equals("food")) {
                                player1.consumeFood((Food)carrying.bag_items.get(food - 1));
                                carrying.bag_items.remove(food-1);
                                carrying.shiftItems(food-1);
                                eat = true;
                            } else {
                                System.out.println("Try another index.");
                                System.out.print("> ");
                                input1 = scanner.nextLine();
                            }
                        }
                    }
                    // OPEN DOOR/IN
                } else if (input.equals("in")) {
                    if (currentLocation.getDoor() == null) {
                        System.out.println("No door to enter.");
                    } else if (currentLocation.inspectDoor()) {
                        String nextRoomDir = GameMap.getUserRoomChoice(input);
                        Locations nextLoc = GameMap.getNextLoc(nextRoomDir);
                        currentLocation = nextLoc;
                    } else {
                        carrying = (bag) player1.getBag();
                        if (carrying.containsItem("key")) {
                            carrying.inspectInventory();
                            System.out.println("Enter index of key to use.");
                            System.out.print("> ");
                            String input1 = scanner.nextLine();
                            while (!input1.equals("no key") && !currentLocation.inspectDoor()) {
                                int key = Integer.parseInt(input1);
                                if (carrying.bag_items.get(key - 1).getName().equals("key")) {
                                    key key_value = (key) carrying.bag_items.get(key - 1);
                                    if (key_value.getValue() == (currentLocation.getDoor().getValue())) {
                                        currentLocation.open_door();
                                    } else {
                                        System.out.println("Try another key");
                                    }
                                    if (currentLocation.inspectDoor()) {
                                        System.out.println("Door open");
                                        key_value.useKey();
                                        carrying.bag_items.remove(key - 1);
                                        carrying.shiftItems(key - 1);
                                    } else {
                                        System.out.print("> ");
                                        input1 = scanner.nextLine();
                                    }
                                } else {
                                    System.out.println("Try another index.");
                                    System.out.print("> ");
                                    input1 = scanner.nextLine();
                                }
                            }
                        } else {
                            System.out.println("Find key(s) to enter. Go now! Go!");
                        }
                    }
                 // DROP ITEMS
                } else if (input.contains("drop")) {
                    if (input.contains("drop bag")) {
                        if (!player1.carrying_bag) {
                            System.out.println("No bag to drop");
                        } else if (input.contains("with items")) {
                            currentLocation.locationDropBag(player1.player_bag);
                            player1.dropBag();
                            System.out.println("Bag dropped");
                        }else if(player1.player_bag.getFilledAmount() > 0){
                            System.out.println("Bag contains items. \nTo drop everything enter 'drop bag with items'");
                        }else if (player1.player_bag.getFilledAmount() == 0) {
                            currentLocation.locationDropBag(player1.player_bag);
                            player1.dropBag();
                            System.out.println("Bag dropped");
                        }
                    } else if (player1.player_bag.bag_items.size() == 0) {
                        System.out.println("Bag is empty... Nothing to drop.");
                    } else if (currentLocation.isHasItem()) {
                        System.out.println("Cannot drop item here");
                    } else {
                        carrying = (bag) player1.getBag();
                        carrying.inspectInventory();
                        System.out.print("Enter index of item to drop: ");
                        int index = scanner.nextInt();
                        currentLocation.dropItem(player1.player_bag.bag_items.get(index - 1));
                        carrying.dropItems(index);
                        System.out.println("Dropped");
                        input = scanner.nextLine();
                    }
                } else if (input.equals("kill self")) {
                    alive = false;
                } else if(input.equals("help")){
                    printHelp();
                }else if (!input.equals("quit")) {
                    if (input.equals("up") && currentLocation.getLocation_name().equals("bathroom")) {
                        carrying = (bag) player1.getBag();
                        if (carrying.containsItem("key")) {
                            carrying.inspectInventory();
                            System.out.println("Enter index of key to use.");
                            System.out.print("> ");
                            String input1 = scanner.nextLine();
                            while (!input1.equals("no key") && !currentLocation.inspectDoor()) {
                                int key = Integer.parseInt(input1);
                                if (carrying.bag_items.get(key - 1).getName().equals("key")) {
                                    key key_value = (key) carrying.bag_items.get(key - 1);
                                    if (key_value.getValue() == (currentLocation.getDoor().getValue())) {
                                        currentLocation.open_door();
                                    } else {
                                        System.out.println("Try another key");
                                    }
                                    if (currentLocation.inspectDoor()) {
                                        System.out.println("Mission Accomplished. You found the exit. \nNow GO! before the other guards come looking for you.");
                                        key_value.useKey();
                                        carrying.bag_items.remove(key - 1);
                                        carrying.shiftItems(key - 1);
                                    } else {
                                        System.out.print("> ");
                                        input1 = scanner.nextLine();
                                    }
                                } else {
                                    System.out.println("Try another index.");
                                    System.out.print("> ");
                                    input1 = scanner.nextLine();
                                }
                            }
                        }
                    } else {
                        String nextRoomDir = GameMap.getUserRoomChoice(input);
                        currentLocation = GameMap.getNextLoc(nextRoomDir);
                    }
                }
            } while (alive && !input.toLowerCase().equals("quit"));
            System.out.println("Would you like to restart(y/n)?");
            System.out.print("> ");
            if (scanner.nextLine().equals("y")) {
                running = true;

            } else
                running = false;
        }
    }

    private static void printHelp(){
        System.out.println("Here is some useful informations");
        System.out.println("Directions are: north, south, east, west, northeast, northwest, southeast, southwest, in, out, up, down\n");
        System.out.println("These can be abbreviated: n, s, e, w, ne, nw, se, sw, in, out, up, down\n");
        System.out.println("To pick up or drop objects: 'take' or 'drop' respectively");
        System.out.println("Drop can only work if there is no item at the current position\n");
        System.out.println("To open a chest, enter 'open' and select key");
        System.out.println("To open a door, enter 'in' and select key\n");
        System.out.println("Enter 'connected locations or connections' for connected locations");
        System.out.println("Enter 'connections descriptions' for descriptions\n");
        System.out.println("As said the way in is not the way out, find a special exit.");
        System.out.println("If you wish to quit, enter 'quit' or 'kill self'");
    }

    private static String printSetup(){
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        Random rand = new Random();
        int random_days = rand.nextInt(500);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello Mr. " + name + "! Welcome to the Dungeon");
        System.out.println("You have been placed in this filthy, old dungeon for a life sentence.\nAs of today, you have been in this dungeon for about "
                + random_days + " days. \n\nYOU'RE MISSION IS TO ESCAPE. \n\nOne way in one way out. The way in is not the way out.");
        System.out.println("\nYour mission begins.\n");
        System.out.println("--------------------------------------------------------------------------------------------------------------------");
        System.out.print("Currently in ");
        return name;
    }
}
