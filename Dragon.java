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

import java.util.Random;

/**
 * The Dragon class extends the Character class and contains a constructor for the Dragon object,
 * as well as getters and mutators for specific attributes, such as the currentRoom. It also
 * contains warnings and other messages that can be returned when necessary.
 */
public class Dragon extends Character implements Moveable {
    private static final String DRAGON_WARNING = "You hear a fire breathing nearby!\n";
    // message when a dragon is in an adjacent room
    private static final String DRAGON_ENCOUNTER =
        "Oh no! You ran into the fire breathing dragon!\n";
    // Message when you're in the same room as the dragon

    private final Random randGen; //random num generator used for moving

    /**
     * Constructor for a Dragon object. Initializes all instance fields. The label is
     * "DRAGON" by default.
     *
     * @param currentRoom the room that the Dragon starts in
     * @throws IllegalArgumentException with a descriptive message if currentRoom is not
     *                                  a TreasureRoom
     */
    public Dragon(Room currentRoom) throws IllegalArgumentException {
        super(currentRoom, "DRAGON");
        if (!(currentRoom instanceof TreasureRoom)) {
            throw new IllegalArgumentException("the room isn't a treasure room");
        }
        randGen = new Random();
    }

    /**
     * Getter for DRAGON_WARNING.
     *
     * @return the string for warning about a dragon being nearby.
     */
    public static String getDragonWarning() {
        return DRAGON_WARNING;
    }

    /**
     * Getter for DRAGON_ENCOUNTER.
     *
     * @return the string for warning about a dragon being nearby.
     */
    public static String getDragonEncounter() {
        return DRAGON_ENCOUNTER;
    }

    /**
     * Moves the Dragon to the destination room.
     *
     * @param destination, the Room to change it to
     * @return true if the change was successful, false otherwise
     */
    @Override public boolean changeRoom(Room destination) {
        // first evaluates whether the room is even possible to move to before changing the room
        if (canMoveTo(destination)) {
            super.setCurrentRoom(destination);
        }
        return super.getCurrentRoom().equals(destination);
    }

    /**
     * Checks if the dragon can move to the given destination. A valid move is the destination
     * not a PortalRoom.
     *
     * @param destination, the Room to check if it can move to
     * @return true if they can, false otherwise
     */
    @Override public boolean canMoveTo(Room destination) {
        // checks if the room isn't a portal room and is adjacent to the current room, which is
        // necessary for a valid move
        return !(destination instanceof PortalRoom) && super.getCurrentRoom()
            .isAdjacent(destination);
    }

    /**
     * Picks randomly ONCE an adjacent room to move into.
     *
     * @return the room that this Dragon should try to move into
     */
    public Room pickRoom() {
        // picks a random room and if that room can't be moved to, it generates until it finds a
        // valid move
        int size = super.getCurrentRoom().getAdjacentRooms().size();
        int random = randGen.nextInt(size);
        while (!canMoveTo(super.getCurrentRoom().getAdjacentRooms().get(random))) {
            random = randGen.nextInt(size);
        }
        return super.getCurrentRoom().getAdjacentRooms().get(random);
    }
}
