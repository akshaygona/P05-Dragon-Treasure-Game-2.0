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

import java.util.Random;

/**
 * The PortalRoom class is a direct known subclass of the Room class. It contains the
 * constructor of the portal room, warnings which are returned when portals are close, and some
 * other getters and setters which set images and return locations.
 */
public class PortalRoom extends Room {
    private static final String PORTAL_WARNING = "You feel a distortion in space nearby.\n";
    // message for when a portal room is adjacent to the current room
    private static final String TELEPORT_MESSAGE =
        "The space distortion teleported " + "you to another room!\n";
    // message for when the character is teleported
    private static PImage portalImage; //image of a portal to be shown in all portal rooms
    private final Random randGen; //random number generator for location picking

    /**
     * Constructor for a PortalRoom object. Initializes all instance data fields.
     *
     * @param ID          the ID that this PortalRoom should have
     * @param description the verbal description this PortalRoom should have
     * @param image       the image that should be used as a background when drawing this PortalRoom.
     */
    public PortalRoom(int ID, String description, processing.core.PImage image) {
        super(ID, description, image);
        randGen = new Random();
    }

    /**
     * Getter for PORTAL_WARNING.
     *
     * @return the string for warning about a portal being nearby.
     */
    public static String getPortalWarning() {
        return PORTAL_WARNING;
    }

    /**
     * Getter for TELEPORT_MESSAGE.
     *
     * @return the string for letting the player know they were teleported.
     */
    public static String getTeleportMessage() {
        return TELEPORT_MESSAGE;
    }

    /**
     * Sets the portal image for the PortalRoom class.
     *
     * @param portalImage1 the image to represent the portal
     */
    public static void setPortalImage(processing.core.PImage portalImage1) {
        portalImage = portalImage1;
    }

    /**
     * Picks an adjacent room at random for the player to teleport into.
     *
     * @return The room that player should immediately be moved to
     */
    public Room getTeleportLocation() {
        return super.getAdjacentRooms().get(randGen.nextInt(super.getAdjacentRooms().size()));
    }

    /**
     * Draws this PortalRoom to the window by drawing the background image, a rectangle, some text,
     * and the portal image.
     */
    public void draw() {
        super.draw();
        processing.image(portalImage, 325, 225);
    }

}
