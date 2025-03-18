
package com.mygdx.game;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class ArmorInventory {

    private ArrayList<Armor> armor;

    //NEW EMPTY ARRAY
    public ArmorInventory() {
        armor = new ArrayList<>();
    }

    //ADD ARMOR TO INVENTORY
    public void addArmor(Armor newArmor) {
        armor.add(newArmor);
    }

    //REMOVE ARMOR FROM INVENTORY
    public void removeArmor(Armor newArmor) {
        armor.remove(newArmor);
    }

    //RETURNS NUMBER OF ARMOR PIECES IN ARRAY
    public int getArmorInventorySize() {
        return armor.size();
    }

    public boolean isEmpty() {
        return armor.isEmpty();
    }

    //DISPLAYS ARMOR INVENTORY
    public void displayArmorInventory() {
        if (isEmpty()) {
            System.out.println("Inventory is empty.");
        }
        else {
            System.out.println("Items in your inventory: ");
            for (int i = 0; i < armor.size(); i++) {
                System.out.println((i + 1) + ". " + armor.get(i).getClass().getSimpleName());
            }
        }
    }

    //OBTAINS ALL GEAR PIECES
    public ArrayList<Armor> getAllPieces() {
        return armor;
    }

    //OBTAINS A SINGLE PIECE OF ARMOR
    public Armor getArmorPiece(int index) {
        if (index >=0 && index < armor.size()) {
            return armor.get(index);
        }
        return null;//INVALID, DOESN'T EXIST
    } 
}
 

    //public void chooseArmorPiece(ArmorInventory armor, ) {
        //System.out.println("Choose armor piece: ");
       // armor.displayArmorInventory();                  
       // int selection = scanner.nextInt();
       // armor.getArmorPiece(selection);
        //MainCharacter selectedArmorPiece = party.getArmorPiece(selection);        
    //}




    
