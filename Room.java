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

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

/**
 * The Room class contains a constructor for the room object, as well as methods which draw/create
 * GUI, frames, and contains other getters and setters for the list of adjacent rooms.
 */
public class Room {
    protected static PApplet processing; //PApplet object which the rooms will use to
    private final int ID; // a "unique" identifier for each room
    private final String description; //verbal description of the room
    private final ArrayList<Room> adjRooms; //list of all rooms directly connect
    //draw stuff to the GUI
    private final PImage image; //stores the image that corresponds to the background of a room

    /**
     * Constructor for a Room object. Initializes all instance data fields.
     *
     * @param ID          the ID that this Room should have
     * @param description the verbal description this Room should have
     * @param image       the image that should be used as a background when drawing this Room.
     */
    public Room(int ID, String description, processing.core.PImage image) {
        this.ID = ID;
        this.description = description;
        this.image = image;
        adjRooms = new ArrayList<>();
    }

    /**
     * Sets the processing for the class.
     *
     * @param processing1 the PApplet that this room will use to draw to the window
     */
    public static void setProcessing(processing.core.PApplet processing1) {
        processing = processing1;
    }

    /**
     * Getter for ID.
     *
     * @return the ID of this Room
     */
    public int getID() {
        return ID;
    }

    /**
     * Getter for description.
     *
     * @return the verbal description of this Room
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the list of adjacentRooms.
     *
     * @return the list of adjacent rooms
     */
    public ArrayList<Room> getAdjacentRooms() {
        return adjRooms;
    }

    /**
     * Adds the given room to the list of rooms adjacent to this room.
     *
     * @param toAdd the room to be added
     */
    public void addToAdjacentRooms(Room toAdd) {
        adjRooms.add(toAdd);
    }

    /**
     * Checks whether or not the given room is adjacent to this room.
     *
     * @param r the room to check for adjacency
     * @return true if it is adjacent, false otherwise
     */
    public boolean isAdjacent(Room r) {
        return adjRooms.contains(r);
    }

    /**
     * Overrides Object.equals(). Determines if two objects are equal.
     *
     * @param other the object to check against this Room
     * @return true if other is of type Room and has the same ID, false otherwise
     */
    @Override public boolean equals(Object other) {
        if (other instanceof Room) {
            Room otherRoom = (Room) other;
            return this.ID == otherRoom.ID;
        }
        return false;
    }

    /**
     * Overrides Object.toString(). Returns a string representation of a Room object.
     *
     * @return Returns a string in the form of "<ID>: <description>\n Adjacent Rooms: <r1's ID>
     * <r2's ID>" list of adjacent room IDs continues for all rooms adjacent to this Room.
     */
    @Override public String toString() {
        String s = this.ID + ": " + this.description + "\n Adjacent Rooms: ";
        for (int i = 0; i < adjRooms.size(); i++) {
            s += adjRooms.get(i).ID + " ";
        }
        return s;
    }

    /**
     * Draws this Room to the window by drawing the background image, a rectangle, and some text.
     */
    public void draw() {
        processing.image(image, 0, 0);
        processing.fill(-7028);
        processing.rect(0, 500, 800, 600);
        processing.fill(0);
        processing.text(toString(), 300, 525);
    }
}
