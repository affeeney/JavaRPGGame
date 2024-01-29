package com.mygdx.game;
import java.io.*;
import java.nio.file.*;
import java.util.Random;


public abstract class MainCharacter {
    private String name;
    private int exp;
    private int luck;
    private int speed;
    private int level;
    private int attack;
    private int magattack;
    private int defense;
    private int magdefense;
    private int accuracy;
    private int evasion;
    private int health;
    private int magic;
    private int expThresh;
    private boolean defeated;
    

    public MainCharacter(String name, int level) {
        this.name = name;
        this.level = level;
        this.exp = 0;
        this.magic = 0;
        this.luck = 0;
        this.speed = 0;
        this.attack = 0;
        this.magattack = 0;
        this.defense = 0;
        this.magdefense = 0;
        this.accuracy = 0;
        this.evasion = 0;
        this.health = 0;
        this.expThresh = expThresh;
        this.defeated = false;
        
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------
//PHYSICAL DAMAGE METHODS


    public boolean strike(Enemy enemy) {
        Random random = new Random();
        int n = random.nextInt(100);
        int damage = calculateDamage(enemy);
        
        

        if (n < (double) enemy.getEvasion() / (enemy.getEvasion() + getAccuracy()) * 100) {
            System.out.println(enemy.getName() + " evaded the attack!");
            return true;
        
        }    
        else if (damage > 0) {
            System.out.println(name + " Attacks for " + damage + " damage!");
            enemy.takeDamage(damage);
            
            if (enemy.isDefeated()) {
                
                return true;
            }
               
        } 
        else {
        System.out.println(" Attack failed!");
        }
        return false;
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
//DAMAGE MULTIPLIERS AND FORMULAS        

    
    public void takeDamage(int damage) {
        
        health -= damage;

        if (health <= 0) {
            defeat();
        }  
        
    }    

    private int calculateDamage(Enemy enemy) {
        int attack = getAttack();
        int defense = enemy.getDefense();
        // Apply any additional calculations or modifiers to the attack or defense if needed
        double mitigationPercentage = 0.05; // Adjust this value as needed
        int mitigatedDamage = (int) (defense * mitigationPercentage);

        int damage = attack - mitigatedDamage;
        damage = Math.max(damage,0);
        return damage;
    }

//-------------------------------------------------------------------------------------------------------------------------------------------------
//MAGIC DAMAGE METHODS

    public boolean cast(Enemy enemy) {
        Random random = new Random();
        int n = random.nextInt(100);
        int damage = calculateMagDamage(enemy);
        
        

        if (n < (double) enemy.getEvasion() / (enemy.getEvasion() + getAccuracy()) * 100) {
            System.out.println(enemy.getName() + " evaded the attack!");
            return true;
        
        }    
        else if (damage > 0) {
            System.out.println(name + " Attacks for " + damage + " damage!");
            enemy.takeDamage(damage);
            
            if (enemy.isDefeated()) {
                
                return true;
            }
                
        } 
        else {
        System.out.println(" Attack failed!");
        } 
        return false;   
    }

    private int calculateMagDamage(Enemy enemy) {
        int magattack = getMagattack();
        int magdefense = enemy.getMagdefense();
        // Apply any additional calculations or modifiers to the attack or defense if needed
        double mitigationPercentage = 0.05; // Adjust this value as needed
        int mitigatedDamage = (int) (magdefense * mitigationPercentage);

        int damage = magattack - mitigatedDamage;
        damage = Math.max(damage,0);
        return damage;
    }   

//--------------------------------------------------------------------------------------------------------------------------------------------------   
 //DEFEND/BLOCK METHODS   

    private boolean isDefending;

    

    public boolean defend() {
        if (isDefending) {
            System.out.println(getName() + " is already defending!");
            return false;
        }

        isDefending = true;
        System.out.println(getName() + " is defending!");
        return true;
    }

    public boolean isDefending() {
        return isDefending;
    }

    public void resetDefend() {
        isDefending = false;
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------



    public void evade() {
        System.out.println(name + " Avoids the attack!");
           
    }

    public void flee() {
        System.out.println(name + " Has fled!");
    }

    public void defeat() {
        System.out.println(name + " Defeated! GAME OVER......");
    }

    public void steal() {
        System.out.println(name + " Stole from the enemy!");
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }    

//-----------------------------------------------------------------------------------------------------------------------------------------------
//LEVELING AND EXP METHODS

    //Gain exp points
    public void gainExp(int expPoints) {
        exp += expPoints;

        //checks exp for level-up
        int expThresh = calculateExpThresh(); // calculateExpThresh is adjustable
        while (exp >= expThresh) {
            levelUp();
            exp -= expThresh;
            expThresh = calculateExpThresh();

        }

        saveExpPoints(); // Saves exp points
    }


    //This is the numeric formula for level system
    protected int calculateExpThresh() {
        //adjust lvl system here if needed
        return 30 + (int) (level * 0.5);
    }    
    
    public void levelUp() {
        level++;
        //adjust stat increases   
    }


    public void setExpThresh(int newExpThresh) {
    // Set the new experience thresh
    // You can add any additional logic 
        expThresh = newExpThresh;
        
    }

//---------------------------------------------------------------------------------------------------------------------------------------------
//FILE SAVERS/WRITERS FOR EXP TO BE SAVED AND LOADED 

    //Save exp to file
    public void saveExpPoints() {
        try {
            // Create a FileWriter and BufferedWriter to write to a file
            FileWriter fileWriter = new FileWriter("exp_points.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the expPoints to the file
            bufferedWriter.write(String.valueOf(exp));

            // Close the writers
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving exp points: " + e.getMessage());
        }
    }

    //Load saved exp from file
    public void loadExpPoints() {
        try {
            //Create the FileReader and BufferReader to read from the file
            FileReader fileReader = new FileReader("exp_points.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Read the saved expPoints from the file
            String expString = bufferedReader.readLine();

            // Convert the string to an integer and assign it to exp
            int loadedExp = Integer.parseInt(expString);

            //Close readers
            bufferedReader.close();
            fileReader.close();

            gainExp(loadedExp);

        } 
        catch (IOException e) {
            System.out.println("Error loading exp points: " + e.getMessage());
        }
    }

//-----------------------------------------------------------------------------------------------------------------------------------
//BASIC SETTERS AND GETTERS FOR STATS/ATTRIBUTES
    
    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }

    public int getMagic() {
        return magic;
    }

    public int getLuck() {
        return luck;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttack() {
        return attack;
    }

    public int getMagattack() {
        return magattack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMagdefense() {
        return magdefense;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getEvasion() {
        return evasion;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int newHealth) {
        health = newHealth;
    }

    public void setMagic(int newMagic) {
        magic = newMagic;
    }

    public void setLuck(int newLuck) {
        luck = newLuck;
    }

    public void setExp(int newExp) {
        exp = newExp;
    }

    public void setLevel(int newLevel) {
        level = newLevel;
    }

    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }

    public void setAttack(int newAttack) {
        attack = newAttack;
    }

    public void setMagattack(int newMagattack) {
        magattack = newMagattack;
    }

    public void setDefense(int newDefense) {
        defense = newDefense;
    }

    public void setMagdefense(int newMagdefense) {
        magdefense = newMagdefense;
    }

    public void setAccuracy(int newAccuracy) {
        accuracy = newAccuracy;
    }

    public void setEvasion(int newEvasion) {
        evasion = newEvasion;
    }

    public void setName(String newName) {
        name = newName;
    }

    
}
