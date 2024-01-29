package com.mygdx.game;
import java.util.Random;


public abstract class Enemy {
    
    private String name;
    private int speed;
    private int level;
    private int attack;
    private int magattack;
    private int defense;
    private int magdefense;
    private int accuracy;
    private int evasion;
    private int health;
    private boolean defeated;
    private boolean isEvenLevel = false;
    private int expReward;
   

    public Enemy(String name, int level) {
        this.name = name;
        this.speed = speed;
        this.level = level;
        this.attack = attack;
        this.magattack = magattack;
        this.defense = defense;
        this.magdefense = magdefense;
        this.accuracy = accuracy;
        this.evasion = evasion;
        this.health = health;
        this.expReward = expReward;
        initializeStats(); // Enemys stats initialized according to characters level
        this.defeated = false; // Initializes enemy as not defeated yet
        
    }
    
//-------------------------------------------------------------------------------------------------
//METHODS TO SET ENEMYS STATS DEPENDING ON WHAT LEVEL THEY WILL SPAWN AS

    private void initializeStats() {
        if (name.equals("Troll")) {
            setTrollStats();
            
        } else if (name.equals("Skeleton")) {
            setSkeletonStats();
        
        } else if (name.equals("Slime")) {
            setSlimeStats();
        }
    }

    private void setTrollStats() {
        int baseSpeed = 0;
        int baseAttack = 0;
        int baseMagAttack = 0;
        int baseDefense = 0;
        int baseMagDefense = 0;
        int baseAccuracy = 0;
        int baseEvasion = 0;
        int baseHealth = 0;
        int baseExpReward = 10;

        //Every other level progression before generating will give the enemy different stat changes
        for (int lvl = 1; lvl <= level; lvl++) {
            if (isEvenLevel) {
                baseSpeed += 1;
                baseAttack += 4;
                baseMagAttack += 1;
                baseDefense += 4;
                baseMagDefense += 1;
                baseAccuracy += 1;
                baseEvasion += 1;
                baseHealth += 4;
                
            } else {
                baseSpeed += 0;
                baseAttack += 4;
                baseMagAttack += 1;
                baseDefense += 3;
                baseMagDefense += 1;
                baseAccuracy += 1;
                baseEvasion += 1;
                baseHealth += 4;
            }
            isEvenLevel = !isEvenLevel;
        }

        speed = baseSpeed;
        attack = baseAttack;
        magattack = baseMagAttack;
        defense = baseDefense;
        magdefense = baseMagDefense;
        accuracy = baseAccuracy;
        evasion = baseEvasion;
        health = baseHealth;
        expReward = baseExpReward;
    }

    private void setSkeletonStats() {
        int baseSpeed = 0;
        int baseAttack = 0;
        int baseMagAttack = 0;
        int baseDefense = 0;
        int baseMagDefense = 0;
        int baseAccuracy = 0;
        int baseEvasion = 0;
        int baseHealth = 0;
        int baseExpReward = 10;

        for (int lvl = 1; lvl <= level; lvl++) {
            if (isEvenLevel) {
                baseSpeed += 1;
                baseAttack += 2;
                baseMagAttack += 2;
                baseDefense += 1;
                baseMagDefense += 2;
                baseAccuracy += 2;
                baseEvasion += 2;
                baseHealth += 4;
            } else {
                baseSpeed += 1;
                baseAttack += 1;
                baseMagAttack += 2;
                baseDefense += 1;
                baseMagDefense += 2;
                baseAccuracy += 2;
                baseEvasion += 2;
                baseHealth += 4;
            }
            isEvenLevel = !isEvenLevel;
        }

        speed = baseSpeed;
        attack = baseAttack;
        magattack = baseMagAttack;
        defense = baseDefense;
        magdefense = baseMagDefense;
        accuracy = baseAccuracy;
        evasion = baseEvasion;
        health = baseHealth;
        expReward = baseExpReward;
    }

    private void setSlimeStats() {
        int baseSpeed = 0;
        int baseAttack = 0;
        int baseMagAttack = 0;
        int baseDefense = 0;
        int baseMagDefense = 0;
        int baseAccuracy = 0;
        int baseEvasion = 0;
        int baseHealth = 0;
        int baseExpReward = 10;

        for (int lvl = 1; lvl <= level; lvl++) {
            if (isEvenLevel) {
                baseSpeed += 2;
                baseAttack += 1;
                baseMagAttack += 3;
                baseDefense += 2;
                baseMagDefense += 3;
                baseAccuracy += 3;
                baseEvasion += 4;
                baseHealth += 4;
            } else {
                baseSpeed += 2;
                baseAttack += 1;
                baseMagAttack += 3;
                baseDefense += 1;
                baseMagDefense += 2;
                baseAccuracy += 3;
                baseEvasion += 4;
                baseHealth += 4;
            }
            isEvenLevel = !isEvenLevel;
        }

        speed = baseSpeed;
        attack = baseAttack;
        magattack = baseMagAttack;
        defense = baseDefense;
        magdefense = baseMagDefense;
        accuracy = baseAccuracy;
        evasion = baseEvasion;
        health = baseHealth;
        expReward = baseExpReward;
    }


//-----------------------------------------------------------------------------------------------------------------------------------------------
//METHODS FOR PHYSICAL ATTACKS

    public void takeDamage(int damage) {
        takeDamage(damage, null, null);
    }    

    public void takeDamage(int damage, Warrior warrior, Mage mage) {
        health -= damage;
        if (health <= 0) {
            defeated = true;
            defeat();
            giveExpReward(warrior, mage);
        }
       
        

    }
    
    public boolean strike(MainCharacter mainCharacter) {
        Random random = new Random();
        int n = random.nextInt(100);
        int damage = calculateDamage(mainCharacter);

        
        if (mainCharacter.isDefending()) {
            System.out.println(mainCharacter.getName() + " blocked the attack!");
            mainCharacter.resetDefend();
            return true;
        }    
        else if (n < (double) mainCharacter.getEvasion() / (mainCharacter.getEvasion() + getAccuracy()) * 100) {
            System.out.println(mainCharacter.getName() + " evaded the attack!");
            return true;
        }       
        else if (damage > 0) {
            System.out.println(name + " Attacks for " + damage + " damage!");
            mainCharacter.takeDamage(damage);
            
            if (mainCharacter.isDefeated()) {
                mainCharacter.defeat();
                return true;
            } 
              
        } 
        else {
        System.out.println(" Attack failed!");
        }
        return false;

    }

    private int calculateDamage(MainCharacter mainCharacter) {
        int attack = getAttack();
        int defense = mainCharacter.getDefense();
        //Calculate percentage
        double mitigationPercentage = 0.05; // Adjust this value as needed
        int mitigatedDamage = (int) (defense * mitigationPercentage);

        int damage = attack - mitigatedDamage;
        damage = Math.max(damage,0);
        return damage;

    }


//-------------------------------------------------------------------------------------------------------------------------------------------------
//METHODS TO GET AND SET INSTANCES OF MAINCHARACTERS-----------------------------------------------------------------------------------------------

    private static MainCharacter mainCharacterInstance;

    private MainCharacter getCharacterInstance() {
        // Assuming you have a way to retrieve the MainCharacter instance in the Enemy class
        // Replace this with your implementation to get the MainCharacter instance
        return mainCharacterInstance;
    }

    public static void setMainCharacterInstance(MainCharacter mainCharacter) {
        mainCharacterInstance = mainCharacter; // Set the MainCharacter instance
    }

    
    
//----------------------------------------------------------------------------------------------------------------------------------------------------
//METHODS FOR MAGIC ATTACKS
    

    public boolean cast(MainCharacter mainCharacter) {
        Random random = new Random();
        int n = random.nextInt(100);
        int damage = calculateMagDamage(mainCharacter);
        
        
        if (mainCharacter.isDefending()) {
            System.out.println(mainCharacter.getName() + " blocked the attack!");
            mainCharacter.resetDefend();
            return true;
        }    
        else if (n < (double) mainCharacter.getEvasion() / (mainCharacter.getEvasion() + getAccuracy()) * 100) {
            System.out.println(mainCharacter.getName() + " evaded the attack!");
            return true;
        }    
        else if (damage > 0) {
            System.out.println(name + " attacks for " + damage + " damage!");
            mainCharacter.takeDamage(damage);
            
            if (mainCharacter.isDefeated()) {
                mainCharacter.defeat();
                return true;
            }    
        } 
        else {
        System.out.println(" Attack failed!");
        }

        return false;

    }

    private int calculateMagDamage(MainCharacter mainCharacter) {
        int magattack = getMagattack();
        int magdefense = mainCharacter.getMagdefense();
        //Calculate percentage
        double mitigationPercentage = 0.05; // Adjust this value as needed
        int mitigatedDamage = (int) (magdefense * mitigationPercentage);

        int damage = magattack - mitigatedDamage;
        damage = Math.max(damage,0);
        return damage;

    }
    




//------------------------------------------------------------------------------------------------------------------------------------------------------

    public void defend() {
        System.out.println(name + " Blocked your attack!");
    }

    public void evade() {
        System.out.println(name + " Avoids the attack!");
    }

    public void flee() {
        System.out.println(name + " Has fled!");
    }

    public void defeat() {
        System.out.println(name + " Defeated!");
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }    

    

//-------------------------------------------------------------------------------------------------------------------------------------------------
//METHODS FOR EXP AND LEVEL UP
   

    public int giveExpReward(Warrior warrior, Mage mage) {
        //ADJUST EXP RETURNED AS NEEDED HERE
        int expPoints = (int) (expReward * (1 + (getLevel() * 0.5)));
        if (warrior != null) {
            warrior.gainExp(expPoints);
            warrior.saveExpPoints();
        }
        if (mage != null) {
            mage.gainExp(expPoints);
            mage.saveExpPoints();
        }
        System.out.println(name + " gave " + expPoints + " experience points!");
        return expPoints;
    }

    public void levelUp() {
        level++;
        //adjust stat increases   
    }

//---------------------------------------------------------------------------------------------------------------------------------------------------
//BASIC SETTERS AND GETTERS FOR STATS/ATTRIBUTES

    public String getName() {
        return name;
    }


    public int getLevel() {
        return level;
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

 

}
