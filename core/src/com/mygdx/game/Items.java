
package com.mygdx.game;
public abstract class Items {

    private String name;
    private int heal;

    public Items(String name, int heal) {
        this.name = name;
        this.heal = heal;
    }
    
    //METHOD TO HEAL CHARACTER
    public void healCharacter(MainCharacter mainCharacter) {
        int currentHealth = mainCharacter.getHealth();
        currentHealth += heal;
        mainCharacter.setHealth(currentHealth);
        
    }

    // NAME GETTER
    public String getName() {
        return name;
    }

    // HEAL VALUE GETTER
    public int getHeal() {
        return heal;
    }
}

    

