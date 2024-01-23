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
 * The StartRoom class is a subclass of the Room class. It contains a constructor for the
 * StartRoom object.
 */
public class StartRoom extends Room {
    /**
     * Constructor for the StartRoom. Initializes ID, image, and description.
     *
     * @param ID    the ID to give to this object
     * @param image the image that should be used as a background when drawing this Room.
     */
    public StartRoom(int ID, PImage image) {
        super(ID, "You find yourself in the entrance to a cave holding treasure.", image);
    }
}
