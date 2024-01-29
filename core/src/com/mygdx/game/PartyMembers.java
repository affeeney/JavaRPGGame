package com.mygdx.game;
import java.util.ArrayList;

public class PartyMembers {

    private ArrayList<MainCharacter> party;

    //NEW EMPTY PARTY ARRAY
    public PartyMembers() {
        party = new ArrayList<>(3);
    }

    //ADD MEMBER TO PARTY
    public void addPartyMember(MainCharacter mainCharacter) {
        party.add(mainCharacter);
    }

    //REMOVE MEMBER FROM PARTY
    public void removePartyMember(MainCharacter mainCharacter) {
        party.remove(mainCharacter);
    }

    //CHECKS IF PARTY IS EMPTY
    public boolean isEmpty() {
        return party.isEmpty();
    }

    //DISPLAYS PARTY MEMBERS
    public void displayPartyMembers() {
        if (isEmpty()) {
            System.out.println("Party is empty.");
        }    
        else {
            System.out.println("Party Members: ");
            for (int i = 0; i < party.size(); i++) {
                System.out.println((i + 1) + ". " + party.get(i).getClass().getSimpleName());
            }
        }    
    }

    //OBTAINS PARTY MEMBERS
    public ArrayList<MainCharacter> getPartyMembers() {
        return party;
    }

    //RETURNS THE NUMBER OF PEOPLE IN THE PARTY
    public int getPartyMembersSize() {
        return party.size();
    }

    //OBTAINS SINGLE PARTY MEMBER. USED WITH USER INPUT
    public MainCharacter getPartyMember(int index) {
        if (index >= 0 && index < party.size()) {
            return party.get(index);
        }
        return null; //RETURN NULL FOR INVALID
    }    
}
