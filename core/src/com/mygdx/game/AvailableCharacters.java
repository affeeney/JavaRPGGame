package com.mygdx.game;
import java.util.ArrayList;

public class AvailableCharacters {

    private ArrayList<MainCharacter> availableChars;

    //NEW EMPTY ROSTER ARRAY
    public AvailableCharacters() {
        availableChars = new ArrayList<>();
    }

    //ADD MEMBER TO ROSTER
    public void addMember(MainCharacter mainCharacter) {
        availableChars.add(mainCharacter);
    }

    //REMOVE MEMBER FROM ROSTER
    public void removeMember(MainCharacter mainCharacter) {
        availableChars.remove(mainCharacter);
    }

    //CHECKS IF ROSTER IS EMPTY
    public boolean isEmpty() {
        return availableChars.isEmpty();
    }

    //GETS NUMBER OF CHARACTERS IN THE AVAILABLE ROSTER
    public int getAvailableCharsSize() {
        return availableChars.size();
    }

    //DISPLAYS ROSTER MEMBERS
    public void displayAvailableCharacters() {
        if (isEmpty()) {
            System.out.println("No available characters.");
        }    
        else {
            System.out.println("Available Characters: ");
            for (int i = 0; i < availableChars.size(); i++) {
                System.out.println((i + 1) + ". " + availableChars.get(i).getClass().getSimpleName());
            }
        }    
    }

    //OBTAINS ROSTER MEMBERS
    public ArrayList<MainCharacter> getAvailableCharacters() {
        return availableChars;
    }

    //OBTAINS SINGLE ROSTER MEMBER. USED WITH USER INPUT
    public MainCharacter getAvailableMember(int index) {
        if (index >= 0 && index < availableChars.size()) {
            return availableChars.get(index);
        }
        return null; //RETURN NULL FOR INVALID
    }    
}


    

