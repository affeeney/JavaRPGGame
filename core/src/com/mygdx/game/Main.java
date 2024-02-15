package com.mygdx.game;
import java.util.Scanner;
import com.mygdx.game.Battle;



public class Main {
    public static void main(String[] args) {
        // CREATE INSTANCE OF CHARACTER/ITEM/INVENTORY OBJECTS.
        AvailableCharacters availableCharacters = new AvailableCharacters();
        PartyMembers partyMembers = new PartyMembers();
        ItemInventory itemInventory = new ItemInventory();
        Warrior warrior = new Warrior("Hero", 1);
        Mage mage = new Mage("Mage", 1);
        Scanner scanner = new Scanner(System.in);
        boolean battleEnded = false;
        PauseMenu pauseMenu = new PauseMenu(itemInventory, partyMembers, availableCharacters);

        
        

        
        
        while (true) {
            // CHECKS FOR USER INPUT OF "P".
            if (!Battle.inCombat && PauseMenu.isPauseMenuRequested(scanner)) {
                pauseMenu.showMenu();
            }

            // Check if it's time to end the game loop
            if (Battle.loop_terminate || warrior.getLevel() >= 10) {
                break;
            }
           
            Enemy enemy = EnemyRandomizer.randomizeEnemy(warrior.getLevel());

            battleEnded = Battle.startBattle(warrior, mage, scanner, itemInventory);

            

            if (battleEnded == true) {
                break;
            }       
            

                

            

           
        }
        scanner.close();
        System.out.println("Battles Over!....");
           
    }
}
