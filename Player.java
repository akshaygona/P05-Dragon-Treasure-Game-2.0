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


/**
 * The Player class extends the Character class and contains a constructor for the Player object,
 * as well as getters and mutators for specific attributes, such as the currentRoom and label. It
 * also contains methods relating to the treasure key and warning messages of nearby rooms/dragons.
 */
public class Player extends Character implements Moveable {
    private boolean hasKey; // stores whether the player has the key

    /**
     * Constructor for player object. The label should be "PLAYER" and not have a key by default.
     *
     * @param currentRoom the room that the player should start in
     * @throws IllegalArgumentException if the currentRoom is not a StartRoom
     */
    public Player(Room currentRoom) throws IllegalArgumentException {
        super(currentRoom, "PLAYER");
        if (!(currentRoom instanceof StartRoom)) {
            throw new IllegalArgumentException("currentRoom isn't a start room");
        }
        hasKey = false;
    }

    /**
     * Determines if the player has the key.
     *
     * @return true if the player has the key, false otherwise
     */
    public boolean hasKey() {
        return hasKey;
    }

    /**
     * Gives player the key.
     */
    public void obtainKey() {
        hasKey = true;
    }

    /**
     * Moves the Player to the destination room.
     *
     * @param destination, the Room to change it to
     * @return true if the change was successful, false otherwise
     */
    public boolean changeRoom(Room destination) {
        // first evaluates whether changing to the room is valid, then changes to that room
        if (canMoveTo(destination)) {
            setCurrentRoom(destination);
        }
        return getCurrentRoom().equals(destination);
    }

    /**
     * Checks if the player needs to teleport and move them if needed.
     *
     * @return true if a teleport occurred, false otherwise
     */
    public boolean teleport() {
        // checks whether the player is in a portal room
        if (super.getCurrentRoom() instanceof PortalRoom) {
            return this.changeRoom(((PortalRoom) this.getCurrentRoom()).getTeleportLocation());
        }
        return false;
    }

    /**
     * Checks if the player can move to the given destination. A valid move is the destination is
     * a room adjacent to the player.
     *
     * @param destination, the Room to check if it can move to
     * @return true if they can, false otherwise
     */
    public boolean canMoveTo(Room destination) {
        return super.getCurrentRoom().isAdjacent(destination);
    }

    /**
     * Determines whether or not the given dragon is nearby. A dragon is considered nearby if it is
     * in one of the adjacent rooms.
     *
     * @param d the dragon to check if nearby
     * @return true if the dragon is nearby, false otherwise
     */
    public boolean isDragonNearby(Dragon d) {
        // loops through adjacent rooms and checks if dragon is in any of them
        for (int i = 0; i < super.getCurrentRoom().getAdjacentRooms().size(); i++) {
            if (d.getCurrentRoom().equals(super.getCurrentRoom().getAdjacentRooms().get(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether or not a portal room is nearby. A portal room is considered nearby if it
     * is one of the adjacent rooms.
     *
     * @return true if a portal room is nearby, false otherwise
     */
    public boolean isPortalNearby() {
        // loops through adjacent rooms and checks if one is a portal room
        for (int i = 0; i < super.getCurrentRoom().getAdjacentRooms().size(); i++) {
            if (super.getCurrentRoom().getAdjacentRooms().get(i) instanceof PortalRoom) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines whether or not the treasure room is nearby. The treasure room is considered
     * nearby if it is one of the adjacent rooms.
     *
     * @return true if the treasure room is nearby, false otherwise
     */
    public boolean isTreasureNearby() {
        // loops through adjacent rooms and checks if one is a trasure room
        for (int i = 0; i < super.getCurrentRoom().getAdjacentRooms().size(); i++) {
            if (super.getCurrentRoom().getAdjacentRooms().get(i) instanceof TreasureRoom) {
                return true;
            }
        }
        return false;
    }


}
