package com.mygdx.game;
import java.util.Scanner;
import com.mygdx.game.Battle;



public class Main {
    public static void main(String[] args) {
        // Create objects for user input
        PartyMembers partyMembers = new PartyMembers();
        ItemInventory itemInventory = new ItemInventory();
        Warrior warrior = new Warrior("Hero", 1);
        Mage mage = new Mage("Mage", 1);
        Scanner scanner = new Scanner(System.in);
        boolean battleEnded = false;
        PauseMenu pauseMenu = new PauseMenu(itemInventory, partyMembers, null);

        
        

        
        
        while (true) {
            // Check for 'P' key press to access the pause menu
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
