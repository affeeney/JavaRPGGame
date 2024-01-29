
package com.mygdx.game;
public class Knight extends MainCharacter {
    private boolean isEvenLevel = false;

    public Knight(String name, int level) {
        super(name, level);
        generateStats(); //STAT SCALE WITH LEVEL
    }

    //METHOD TO ASSIGN STATS BASED ON LEVEL
    private void generateStats() {
        //ADJUST STATS AS NEEDED
        if (isEvenLevel) {
            setAttack(getAttack() + 1);
            setDefense(getDefense() + 4);
            setSpeed(getSpeed() + 1);
            setLuck(getLuck() + 1);
            setMagattack(getMagattack() + 1);
            setMagdefense(getMagdefense() + 4);
            setAccuracy(getAccuracy() + 1);
            setEvasion(getEvasion() + 99);
            setHealth(getHealth() + 4);
            setMagic(getMagic() + 2);
        
        } 
        else {
            setAttack(getAttack() + 2);
            setDefense(getDefense() + 3);
            setSpeed(getSpeed() + 1);
            setLuck(getLuck() + 1);
            setMagattack(getMagattack() + 1);
            setMagdefense(getMagdefense() + 3);
            setAccuracy(getAccuracy() + 2);
            setEvasion(getEvasion() + 99);
            setHealth(getHealth() + 4);
            setMagic(getMagic() + 1);
            
        }
    }

    //LEVELUP METHOD EXCLUSIVE FOR KNIGHT
    @Override
    public void levelUp() {
        super.levelUp();
        isEvenLevel = !isEvenLevel;
        generateStats();
        setExpThresh(calculateExpThresh());
    }
}

    

    

