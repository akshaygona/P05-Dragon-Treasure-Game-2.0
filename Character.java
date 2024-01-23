//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P05 Dragon Treasure Adventure 2.0
// Course:   CS 300 Fall 2022
//
// Author:   Rishit Patil
// Email:    rpatil5@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Akshay Gona
// Partner Email:   gona@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:   N/A no outside help
// Online Sources:  N/A no outside help
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * The character class provides the constructor for the character object, as well as getters and
 * setters which change the character's current room and label.
 */
public class Character extends Object {
    private final String label; // stores the naming convention for the character
    private Room currentRoom; // stores the current room of this character

    /**
     * Constructor for a Character object. Initializes all instance fields.
     *
     * @param currentRoom the room that the Character is located in
     * @param label       a descriptive label of this Character
     */
    Character(Room currentRoom, String label) throws IllegalArgumentException {
        if (currentRoom == null) {
            throw new IllegalArgumentException("currentRoom is null");
        }
        this.currentRoom = currentRoom;
        this.label = label;
    }

    /**
     * Getter for the current room of this Character.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Sets the current room to the one given.
     *
     * @param newRoom the room that should become the current room
     */
    public void setCurrentRoom(Room newRoom) {
        this.currentRoom = newRoom;
    }

    /**
     * Getter for the label of this Character.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the list of rooms adjacent to this Character.
     */
    public ArrayList<Room> getAdjacentRooms() {
        return currentRoom.getAdjacentRooms();
    }
}
