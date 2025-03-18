package com.mygdx.game;
import java.util.Scanner;
import java.util.Iterator;

public abstract class Armor {
    
    private String name;
    private int physicalDefense;
    private int magicalDefense;
    private int hp;
    private int agility;
    //private boolean equip = false;
    //private boolean remove = false;

    public Armor(String name, int physicalDefense, int magicalDefense, int hp, int agility) {
        this.name = name;
        this.physicalDefense = physicalDefense;
        this.magicalDefense = magicalDefense;
        this.hp = hp;
        this.agility = agility;
        //this.equip = equip;
        //this.remove = remove;
    }

    public void equipArmorPiece(MainCharacter mainCharacter) {
        int currentDefense = mainCharacter.getDefense();
        int currentHealth = mainCharacter.getHealth();
        int currentMagDefense = mainCharacter.getMagdefense();
        int currentAgility = mainCharacter.getSpeed();
        currentHealth += hp;
        currentDefense += physicalDefense;
        currentMagDefense += magicalDefense;
        currentAgility += agility;
        mainCharacter.setHealth(currentHealth);
        mainCharacter.setDefense(currentDefense);
        mainCharacter.setMagdefense(currentMagDefense);
        mainCharacter.setSpeed(currentAgility); 
    }
    
/*public void chooseArmorPiece(ArmorInventory armor, ) {
        System.out.println("Choose armor piece: ");
        party.displayPartyMembers();                  
        int selection = scanner.nextInt();
        party.getPartyMember(selection);
        MainCharacter selectedCharacter = party.getPartyMember(selection);
        System.out.println()
    }
*/
     
//METHODS FOR EQUIPPING GEAR-------------------------------------------------------------------------------------
    private boolean isEquipped;

    public boolean equip() {
        if (isEquipped) {
            System.out.println(getName() + " is already equipped!");
            return false;
        }

        isEquipped = true;
        System.out.println(getName() + " equipped!");
        return true;
    }

    public boolean isEquipped() {
        return isEquipped;
    }


//GETTERS AND SETTERS---------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public int getPhysicalDefense() {
        return physicalDefense;
    }

    public int getMagicalDefense() {
        return magicalDefense;
    }

    public int getHp() {
        return hp;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setPhysicalDefense(int newPhysicalDefense) {
        physicalDefense = newPhysicalDefense;
    }

    public void setMagicalDefense(int newMagicalDefense) {
        magicalDefense = newMagicalDefense;
    }

    public void setHp(int newHp) {
        hp = newHp;
    }
}
