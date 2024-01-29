package com.mygdx.game;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        // Create objects for user input
        PartyMembers partyMembers = new PartyMembers();
        ItemInventory itemInventory = new ItemInventory();
        Warrior warrior = new Warrior("Hero", 1);
        Mage mage = new Mage("Mage", 1);
        Scanner scanner = new Scanner(System.in);
        boolean battleEnded = false;

        
        

        

        while (Battle.loop_terminate == false && warrior.getLevel() < 10) {

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
