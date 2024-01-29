package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ItemInventory {

    private ArrayList<Items> items;

    // INITIALIZES EMPTY INVENTORY
    public ItemInventory() {
        items = new ArrayList<>();
    }

    // ADDS ITEM TO INVENTORY
    public void addItem(Items item) {
        items.add(item);
    }

    // REMOVES ITEM FROM INVENTORY
    public void removeItem(Items item) {
        items.remove(item);
    }

    // GETS NUMBER OF ITEMS IN INVENTORY
    public int getInventorySize() {
        return items.size();
    }

    // CHECKS IF INVENTORY IS EMPTY
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // DISPLAYS ITEMS
    public void displayItems() {
        if (isEmpty()) {
            System.out.println("Inventory is empty.");
        }
        else {
            System.out.println("Items in inventory:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i).getClass().getSimpleName());
            }
        }
    }
    //OBTAINS ALL ITEMS
    public ArrayList<Items> getAllItems() {
        return items;
    }

    //OBTAINS A SINGLE ITEM, USED WITH USER INPUT
    public Items getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null; // RETURNS NULL FOR INVALID INDEX
    }

    //RANDOM ITEM GENERATOR
    public Items generateRandomItem() {
        // POSSIBLE ITEMS
        List<Class<? extends Items>> itemTypes = new ArrayList<>();
        itemTypes.add(Potion.class);
        itemTypes.add(StrongPotion.class);
        // Add more item types as needed

        // GENERATES RANDOM INDEX
        Random random = new Random();
        int randomIndex = random.nextInt(itemTypes.size());

        // GET RANDOM ITEM
        Class<? extends Items> randomItemType = itemTypes.get(randomIndex);

        try {
            // INSTANCE OF RANDOM ITEM
            Items randomItem = null;
            if (randomItemType == Potion.class) {
                
                randomItem = new Potion("Potion", 10); // VALUES CAN BE ADJUSTED
            } 
            else if (randomItemType == StrongPotion.class) {
            
                randomItem = new StrongPotion("Strong Potion", 20); // VALUES CAN BE ADJUSTED
            }

            //RETURN OUR NEW RANDOM ITEM, CAN BE ADDED TO ITEMINVENTORY WITH ADDITEM METHOD
            

            return randomItem;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}            


