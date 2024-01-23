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

import processing.core.PImage;

/**
 * The TreasureRoom class is a direct known subclass of the Room class. The class contains
 * a constructor for the TreasureRoom object, as well as getters and setters for warnings.
 */
public class TreasureRoom extends Room {
    private static final String TREASURE_WARNING = "You sense that there is treasure nearby.\n";
    // message for when a treasure room is adjacent to the player's room
    private static PImage treasureBackground; //the image ALWAYS used for treasure rooms

    /**
     * Constructor for a TresureRoom object and have a description of "In the back of this room,
     * you spot a treasure chest. It is locked...". Intializes all instance data fields.
     *
     * @param ID the ID to give to this object
     */
    public TreasureRoom(int ID) {
        super(ID, "In the back of this room, you spot a treasure chest. It is locked...",
            treasureBackground);
    }

    /**
     * Getter for TREASURE_WARNING.
     *
     * @return the string for warning about treasure being nearby.
     */
    public static String getTreasureWarning() {
        return TREASURE_WARNING;
    }

    /**
     * Sets the background image for the TreasureRoom class.
     *
     * @param treasureBackground1 the image to be the background
     */
    public static void setTreasureBackground(processing.core.PImage treasureBackground1) {
        treasureBackground = treasureBackground1;
    }

    /**
     * Determines whether or not the player can open the treasure chest in the room.
     *
     * @param p the Player to check if they can open the chest
     * @return true if the player has the key and is in this TreasureRoom, false otherwise
     */
    public boolean playerCanGrabTreasure(Player p) {
        return p.hasKey();
    }

}
