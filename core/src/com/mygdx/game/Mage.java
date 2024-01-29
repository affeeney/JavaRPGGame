

package com.mygdx.game;
public class Mage extends MainCharacter {

    private static final double EXP_INCREMENT = 0.5;
    private boolean isEvenLevel = false;

    public Mage(String name, int level) {
        super(name, level);
        generateStats(); // STATS SCALE WITH LEVEL
    }

    // METHOD TO ASSIGN STATS BASED ON LEVEL
    private void generateStats() {
        // ADJUST STATS HERE FOR MAGE
        if (isEvenLevel) {
            setAttack(getAttack() + 1);
            setDefense(getDefense() + 3);
            setSpeed(getSpeed() + 2);
            setLuck(getLuck() + 1);
            setMagattack(getMagattack() + 3);
            setMagdefense(getMagdefense() + 4);
            setAccuracy(getAccuracy() + 2);
            setEvasion(getEvasion() + 99);
            setHealth(getHealth() + 4);
            setMagic(getMagic() + 2);
        
        } 
        else {
            setAttack(getAttack() + 1);
            setDefense(getDefense() + 2);
            setSpeed(getSpeed() + 3);
            setLuck(getLuck() + 1);
            setMagattack(getMagattack() + 2);
            setMagdefense(getMagdefense() + 2);
            setAccuracy(getAccuracy() + 2);
            setEvasion(getEvasion() + 99);
            setHealth(getHealth() + 4);
            setMagic(getMagic() + 1);
            
        }
    }

    //LEVELUP METHOD EXCLUSIVE FOR MAGE
    @Override
    public void levelUp() {
        super.levelUp();
        isEvenLevel = !isEvenLevel;
        generateStats();
        setExpThresh(calculateExpThresh());
    }
}


    

