package com.mygdx.game;
import java.util.Random;



public class EnemyRandomizer {
    private static final int MAX_ENEMY_TYPES = 3; 

    //METHOD TO GENERATE ENEMY SCALES WITH CHARACTER LEVEL
    public static Enemy randomizeEnemy(int mainCharacterLevel) {
        Random random = new Random();
        int enemyType = random.nextInt(MAX_ENEMY_TYPES) + 1; //This will generate a random enemy

        


        // ENEMY RANDOM SELECTION, STILL APART OF OUR GENERATING METHOD
        switch (enemyType) {
            case 1:
                return scaleEnemyLevel(new Troll(mainCharacterLevel), mainCharacterLevel);
            case 2:
                return scaleEnemyLevel(new Skeleton(mainCharacterLevel), mainCharacterLevel);
            case 3:
                return scaleEnemyLevel(new Slime(mainCharacterLevel), mainCharacterLevel);
            default:
                return null;
        }
    }

    //CALES ENEMY TO CHARACTERS LEVEL.
    private static Enemy scaleEnemyLevel(Enemy enemy, int mainCharacterLevel) {
        int enemyLevel = enemy.getLevel();
        while (enemyLevel < mainCharacterLevel) {
            enemy.levelUp(); // Run the leveling system for each level until it matches the main character's level
            enemyLevel++;
        }
        //RETURN OUR SCALED ENEMY
        return enemy;
    }
}
    

