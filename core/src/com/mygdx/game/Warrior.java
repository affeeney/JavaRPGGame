package com.mygdx.game;
public class Warrior extends MainCharacter {
    private static final double EXP_INCREMENT = 0.5;
    private boolean isEvenLevel = false;

    public Warrior(String name, int level) {
        super(name, level);
        generateStats(); // STATS SCALE WITH LEVEL
    }

    // METHOD TO ASSIGN STATS BASED ON LEVEL
    private void generateStats() {
        // ADJUST STATS HERE FOR WARRIOR
        if (isEvenLevel) {
            setAttack(getAttack() + 3);
            setDefense(getDefense() + 4);
            setSpeed(getSpeed() + 2);
            setLuck(getLuck() + 1);
            setMagattack(getMagattack() + 2);
            setMagdefense(getMagdefense() + 3);
            setAccuracy(getAccuracy() + 2);
            setEvasion(getEvasion() + 99);
            setHealth(getHealth() + 4);
            setMagic(getMagic() + 2);
        
        } 
        else {
            setAttack(getAttack() + 4);
            setDefense(getDefense() + 3);
            setSpeed(getSpeed() + 1);
            setLuck(getLuck() + 1);
            setMagattack(getMagattack() + 1);
            setMagdefense(getMagdefense() + 2);
            setAccuracy(getAccuracy() + 2);
            setEvasion(getEvasion() + 99);
            setHealth(getHealth() + 4);
            setMagic(getMagic() + 1);
            
        }
    }

    //LEVELUP METHOD EXCLUSIVE FOR WARRIOR
    @Override
    public void levelUp() {
        super.levelUp();
        isEvenLevel = !isEvenLevel;
        generateStats();
        setExpThresh(calculateExpThresh());
    }
}



