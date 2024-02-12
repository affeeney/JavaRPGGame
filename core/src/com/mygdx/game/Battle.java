package com.mygdx.game;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Random;

public class Battle {
    
    public static boolean loop_terminate = false;
    public static boolean inCombat = false;

    public static boolean startBattle(Warrior warrior, Mage mage, Scanner scanner, ItemInventory itemInventory) {

        // CREATE ENEMY OBJECT AND MAINCHARACTER INSTANCE FROM ENEMY CLASS
        
        Enemy.setMainCharacterInstance(warrior);
        Enemy.setMainCharacterInstance(mage);
        Enemy enemy = EnemyRandomizer.randomizeEnemy(warrior.getLevel());

        
        

        // CHARACTER WITH MORE SPEED GOES FIRST

        MainCharacter currentTurnCharacter = (warrior.getSpeed() >= mage.getSpeed()) ? warrior : mage;
        
        
        boolean isFirstTurn = true;

        inCombat = true;

        while (loop_terminate == false && warrior.getHealth() > 0 && enemy.getHealth() > 0) {
            System.out.println("----------------------------------");
            System.out.println("Your party vs " + enemy.getName() + " (Level " + enemy.getLevel() + ")");
            System.out.println("----------------------------------");

            MainCharacter character;

            if (isFirstTurn) {
                character = currentTurnCharacter;
                isFirstTurn = false;
            } 
            else {
                currentTurnCharacter = (currentTurnCharacter == warrior) ? mage : warrior;
                character = currentTurnCharacter;
            }

            System.out.printf("%-25s %-25s %-25s\n",  warrior.getName(), mage.getName(), enemy.getName());
            System.out.printf("%-25s %-25s %-25s\n", "Level: " + warrior.getLevel(), "Level: " + mage.getLevel(), "Level: " + enemy.getLevel());
            System.out.printf("%-25s %-25s %-25s\n", "Health: " + warrior.getHealth(), "Health: " + mage.getHealth(), "Health: " + enemy.getHealth());
            System.out.printf("%-25s %-25s %-25s\n", "Attack: " + warrior.getAttack(), "Attack: " + mage.getAttack(), "Attack: " + enemy.getAttack());
            System.out.printf("%-25s %-25s %-25s\n", "Magical Attack: " + warrior.getMagattack(), "Magical Attack: " + mage.getMagattack(), "Magical Attack: " + enemy.getMagattack());
            System.out.printf("%-25s %-25s %-25s\n", "Defense: " + warrior.getDefense(), "Defense: " + mage.getDefense(), "Defense: " + enemy.getDefense());
            System.out.printf("%-25s %-25s %-25s\n", "Magical Defense: " + warrior.getMagdefense(), "Magical Defense: " + mage.getMagdefense(), "Magical Defense: " + enemy.getMagdefense());
            System.out.printf("%-25s %-25s %-25s\n", "Accuracy: " + warrior.getAccuracy(), "Accuracy: " + mage.getAccuracy(), "Accuracy: " + enemy.getAccuracy());
            System.out.printf("%-25s %-25s %-25s\n", "Evasion: " + warrior.getEvasion(), "Evasion: " + mage.getEvasion(), "Evasion: " + enemy.getEvasion());
            System.out.printf("%-25s %-25s %-25s\n", "Exp: " + warrior.getExp(), "Exp: " + mage.getExp(), "Speed: " + enemy.getSpeed());
            System.out.printf("%-25s %-25s %-25s\n", "Luck: " + warrior.getLuck(), "Luck: " + mage.getLuck(), "");
            System.out.printf("%-25s %-25s %-25s\n", "Speed: " + warrior.getSpeed(), "Speed: " + mage.getSpeed(), "");
            System.out.printf("%-25s %-25s %-25s\n", "Magic: " + warrior.getMagic(), "Magic: " + mage.getMagic(), "");
            System.out.println("----------------------------------");

            System.out.println("----------------------------------");
            System.out.println(character.getName() + "'s turn!");
            System.out.println("1.Attack");
            System.out.println("2.Magic");
            System.out.println("3.Defend");
            System.out.println("4.Item");
            System.out.println("5.Flee");

            //USER INPUT
            
            int choice = scanner.nextInt();

            if (enemy.getHealth() > 0) {
                
                if (character == warrior) {
                    
                    switch (choice) {
                        case 1:
                            warrior.strike(enemy);
                            break;

                        case 2:
                            warrior.cast(enemy);
                            break;

                        case 3:
                            warrior.defend();
                            break;

                        case 4:
                            System.out.println("Your items: ");
                            int index = 1;
                            for (Items item : itemInventory.getAllItems()) {
                                System.out.println(index + ". " + item.getName());
                                index++;
                            }

                            // PLAYER CHOOSES ITEM
                            System.out.println("Choose an item (enter the number):");
                            int selectedItemIndex = scanner.nextInt();

                            // CHECK IF USER SELECTION IS TRUE
                            if (selectedItemIndex >= 1 && selectedItemIndex <= itemInventory.getInventorySize()) {
                                Items selectedItem = itemInventory.getItem(selectedItemIndex - 1);
                                selectedItem.healCharacter(warrior);
                            } 
                            else {
                                System.out.println("Invalid item selection.");
                            }
                            break;

                        case 5:
                            warrior.flee();
                            return true;
                            
                        default:
                            System.out.println("Invalid command!");
                            break;
                    }
                }        
                                
                else if (character == mage) {
                    

                    switch (choice) {
                            case 1:
                                mage.strike(enemy);
                                break;

                            case 2:
                                mage.cast(enemy);
                                break;

                            case 3:
                                mage.defend();
                                break;

                            case 4:
                                System.out.println("Your items: ");
                                int index = 1;
                                for (Items item : itemInventory.getAllItems()) {
                                    System.out.println(index + ". " + item.getName());
                                    index++;
                                }

                                // PLAYER CHOOSES ITEM
                                System.out.println("Choose an item (enter the number):");
                                int selectedItemIndex = scanner.nextInt();

                                // CHECK IF USER SELECTION IS TRUE
                                if (selectedItemIndex >= 1 && selectedItemIndex <= itemInventory.getInventorySize()) {
                                    Items selectedItem = itemInventory.getItem(selectedItemIndex - 1);
                                    selectedItem.healCharacter(mage);
                                } 
                                else {
                                    System.out.println("Invalid item selection.");
                                }
                                break;

                            case 5:
                                warrior.flee();
                                return true;
                                
                            default:
                                System.out.println("Invalid command!");
                                break;
                    }
                }
            }
            
            // ENEMY TURN, RANDOMIZED
            if (loop_terminate == false && enemy.getHealth() > 0) {
                Random random = new Random();
                int randomNumber = random.nextInt(2); // Generates a random number between 0 and 1
                MainCharacter randomCharacter = (randomNumber == 0) ? warrior : mage;

                

                switch (randomNumber) {
                    case 0:
                        enemy.strike(randomCharacter);
                        break;
                    case 1:
                        enemy.cast(randomCharacter);
                        break;
                    default:
                        System.out.println("Invalid attack.");
                        break;
                }
            }
            
           
            //CHECK IF ENEMY IS DEFEATED TO DROP A RANDOM ITEM    
            if (enemy.isDefeated()) {
                enemy.giveExpReward(warrior, mage);
            
                System.out.println("----------------------------------");
                
                Items randomItem = itemInventory.generateRandomItem();
                if (randomItem != null) {
                    itemInventory.addItem(randomItem);
                    System.out.println("You obtained a random item: " + randomItem.getName());
                    

                }
                System.out.println("-----------------------------------");
                if (!isFirstTurn) {
                    currentTurnCharacter = (currentTurnCharacter == warrior) ? mage : warrior;
                }


            }
        }

        inCombat = false;

        return false;
    }        
}            
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------            
            
            //THIS CODE IS COMMENTED OUT IN CASE IF FUTURE USE IS NEEDED
            // CONDITIONAL FOR ENEMY DEFEATED
//            boolean enemyDefeated = enemy.isDefeated(); // Replace with logic for determining if the enemy is defeated or not

            
//            simulateBattle(mainCharacter, enemy, enemyDefeated, itemInventory); // Pass the defeated enemy to the simulateBattle method
            
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------            
    
      // METHOD NOT NEEDED FOR NOW, THIS CHECKS FOR ENEMY DEFEAT AS WELL AS ENSURES XP AND LEVEL
//    public static void simulateBattle(MainCharacter mainCharacter, Enemy defeatedEnemy, boolean enemyDefeated, ItemInventory itemInventory) {
//        if (enemyDefeated) {
//            
//            int expGained = defeatedEnemy.giveExpReward();
//            mainCharacter.gainExp(expGained);
//            System.out.println("You gained " + expGained + "!");

//            SAVE LEVEL TO FILE
//            mainCharacter.saveExpPoints();
//        }
//    }



    

