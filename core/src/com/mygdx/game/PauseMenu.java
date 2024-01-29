package com.mygdx.game;
import java.util.ArrayList;
import java.util.Scanner;


public class PauseMenu {

    private ItemInventory itemInventory;
    private PartyMembers party;
    private AvailableCharacters availableChars;
    private Warrior warrior;
    private Mage mage;
    private Knight knight;
    
    private Scanner scanner = new Scanner(System.in);

    public PauseMenu(ItemInventory itemInventory, PartyMembers party, AvailableCharacters availableChars) {
        this.itemInventory = itemInventory;
        this.party = party;
        this.availableChars = availableChars;
    }

    
    //PAUSE MENU MAIN SCREEN
    public void showMenu() {

        System.out.println("======== Menu ========");
        System.out.println("1. Item Inventory");
        System.out.println("2. Party Members");
        System.out.println("3. Equipment");
        System.out.println("4. Save");
        System.out.println("5. Load");
        System.out.println("0. Resume Game");
        System.out.println("-----------------------");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                showItemInventory(party);
                
            case 2:
                adjustPartyMembers(party, availableChars);
                
            case 3:
                equipGear(party);
                
            case 4:

            case 5:

            case 0:
                // Resume game
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    // PAUSEMENU ITEMINVENTORY SUB-SCREEN
    public void showItemInventory(PartyMembers party) {
        
        System.out.println("Your items: ");
        int index = 1;
        for (Items item : itemInventory.getAllItems()) {
            System.out.println(index + ". " + item.getName());
            index++;
        }
        // PLAYER CHOOSES ITEM
        System.out.println("Choose an item (enter the number):");
        int selectedItemIndex = scanner.nextInt();

        Items selectedItem = null; //NEED TO DECLARE selectedItem HERE

        // CHECK IF USER SELECTION IS TRUE
        if (selectedItemIndex >= 1 && selectedItemIndex <= itemInventory.getInventorySize()) {
            selectedItem = itemInventory.getItem(selectedItemIndex - 1);
            
        } 
        else {
            System.out.println("Invalid item selection.");
            //BACK TO MAINMENU
            showMenu();
        }


        System.out.println("===== Heal Menu =====");
        System.out.println("1. Heal" + party.getPartyMember(0));
        System.out.println("2. Heal" + party.getPartyMember(1));
        System.out.println("3. Heal" + party.getPartyMember(2));
        System.out.println("0. Back to Pause Menu");
        System.out.println("=====================");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                if (selectedItem != null) {                
                    selectedItem.healCharacter(party.getPartyMember(0)); 
                }
                else {
                    System.out.println("No item selected.");
                }
                break;
            case 2:
                if (selectedItem != null) {                
                    selectedItem.healCharacter(party.getPartyMember(1)); 
                }
                else {
                    System.out.println("No item selected.");
                }
                break;
            case 3:
                if (selectedItem != null) {                
                    selectedItem.healCharacter(party.getPartyMember(2)); 
                }
                else {
                    System.out.println("No item selected.");
                }
                break;
            case 0:
                // Return to the main pause menu
                break;
            default:
                System.out.println("Invalid choice.");
                showItemInventory(party);
        }
    }
    
    //PAUSE MENU SUB SCREEN TO ADJUST PARTYMEMBERS
    public void adjustPartyMembers(PartyMembers party, AvailableCharacters availableChars) {

        availableChars.displayAvailableCharacters();
        if (availableChars.isEmpty() == true) {
            System.out.println("Returning to menu");
            showMenu();
        }

        else {
            System.out.println("1. Add");
            System.out.println("2. Remove");
            System.out.println("0. Back to Menu");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (availableChars.getAvailableCharsSize() > 0 && party.getPartyMembersSize() <= 2) {
                        availableChars.displayAvailableCharacters();
                        System.out.println("Enter the number of the character to add to the party.");
                        int charNumber = scanner.nextInt();
                        
                        if (charNumber >= 1 && charNumber <= availableChars.getAvailableCharsSize()) {
                        //GET THE SELECTED CHARACTER
                            MainCharacter characterToAdd = availableChars.getAvailableMember(charNumber - 1);
                            party.addPartyMember(characterToAdd);
                        } 
                        else {
                            System.out.println("Selection error. Please select again.");
                            showMenu();
                        }
                    }
                    else {
                        System.out.println("Error. Input must be valid.");
                        break;
                    } 
                case 2:
                    if (party.getPartyMembersSize() >= 1 && party.getPartyMembersSize() <= 3) {
                        party.displayPartyMembers();
                        System.out.println("Enter the number of the character you want to remove from your party: ");
                        int charNumber = scanner.nextInt();

                        if (charNumber <= 3 && charNumber > 0) {
                            MainCharacter characterToRemove = party.getPartyMember(charNumber - 1);
                            party.removePartyMember(characterToRemove);
                        }
                        else {
                            System.out.println("Selection error. Please select again.");
                            showMenu();
                        } 
                    }
                    else {
                        System.out.println("Error. Input must be valid.");
                        break; 
                    }
                case 0:
                    showMenu(); 
                default:
                    System.out.println("Invalid choice.");
                    adjustPartyMembers(party, availableChars);          
                                              
            }          
        }                          
    }
    public void equipGear(PartyMembers party) {
    }       
}



    

