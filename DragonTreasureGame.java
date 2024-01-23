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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The DragonTreasureGame is a game which depends on processing libraries and a GUI to execute
 * simple algorithms which create a player and advance it towards its goal of finding treasure.
 */
public class DragonTreasureGame extends PApplet {
  private ArrayList<Room> roomList; // stores the total rooms including subclasses
  private ArrayList<Character> characters; // stores all types of characters including subclasses
  private File roomInfo; // File with loaded data from roominfo.txt
  private File mapInfo; // File with loaded data from mapinfo.txt
  private boolean isDragonTurn; // determines whether it's the dragon's turn
  private int gameState = 0; // determines whether game is ongoing(0), won(1), or lost(2)

  /**
   * Main methods runs the code/papplet.
   *
   * @param args
   */
  public static void main(String[] args) {
    PApplet.main("DragonTreasureGame");
  }

  /**
   * Overrides the settings of the Papplet class and sets size.
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Overrides the setup of the Papplet class, sets up the basic necessary elements/objects.
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Dragon Treasure Adventure"); // sets the title of the window
    this.imageMode(PApplet.CORNER); //Images are drawn using the x,y-coordinate
    //as the top-left corner
    this.rectMode(PApplet.CORNERS); //When drawing rectangles interprets args
    //as top-left corner and bottom-right corner respectively
    this.focused = true; // window will be active upon running program
    this.textAlign(CENTER); // sets the text alignment to center
    this.textSize(20); //sets the font size for the text
    roomList = new ArrayList<>();
    // loads the trasure and portal rooms with their images
    PImage treasure = loadImage("images/treasure.jpg");
    TreasureRoom.setTreasureBackground(treasure);
    PImage portal = loadImage("images/portal.png");
    PortalRoom.setPortalImage(portal);
    Room.setProcessing(this);
    // loads the files with their corresponding file paths
    roomInfo = new File("roominfo.txt");
    mapInfo = new File("map.txt");
    loadRoomInfo();
    loadMap();
    characters = new ArrayList<>();
    loadCharacters();
  }

  /**
   * Draws the game, assigns characters, and implements basic game logic.
   */
  @Override
  public void draw() {
    // finds the indexes of the keyholder, player, and dragon, which works in any order that the
    // three are inputted
    int dragonIndex = 0;
    int playerIndex = 0;
    int characterIndex = 0;
    for (int i = 0; i < characters.size(); i++) {
      if (characters.get(i) instanceof Player) {
        playerIndex = i;
      } else if (characters.get(i) instanceof Dragon) {
        dragonIndex = i;
      } else {
        characterIndex = i;
      }
    }
    // draws the current room
    characters.get(playerIndex).getCurrentRoom().draw();
    // outputs all the warning messages
    if (((Player) characters.get(playerIndex)).isPortalNearby()) {
      System.out.println(PortalRoom.getPortalWarning());
    }
    if (((Player) characters.get(playerIndex)).isTreasureNearby()) {
      System.out.println(TreasureRoom.getTreasureWarning());
    }
    if (((Player) characters.get(playerIndex)).isDragonNearby(
        (Dragon) characters.get(dragonIndex))) {
      System.out.println(Dragon.getDragonWarning());
    }
    if (characters.get(playerIndex).getCurrentRoom()
        .equals(characters.get(dragonIndex).getCurrentRoom())) {
      System.out.println(Dragon.getDragonEncounter());
    }
    if (characters.get(playerIndex).getCurrentRoom()
        .equals(characters.get(characterIndex).getCurrentRoom())) {
      ((Player) characters.get(playerIndex)).obtainKey();
      System.out.println("KEY OBTAINED");
    }
    if (((Player) characters.get(playerIndex)).teleport()) {
      System.out.println(true);
    }
    // if the dragon correctly found a room, it's the player's turn to make a move
    if (isDragonTurn) {
      if (((Dragon) characters.get(dragonIndex)).changeRoom(
          ((Dragon) characters.get(dragonIndex)).pickRoom())) {
        isDragonTurn = false;
      }
    }
    // output for if the player finds the treasure room and has a key(player wins)
    if (characters.get(playerIndex)
        .getCurrentRoom() instanceof TreasureRoom) {
      if (((TreasureRoom) characters.get(playerIndex).getCurrentRoom()).playerCanGrabTreasure(
          (Player) characters.get(playerIndex))) {
        System.out.println("You won the game!");
        gameState = 1;
      }
    }
    // output for if the player and dragon are in the same room(player loses)
    if (characters.get(playerIndex).getCurrentRoom()
        .equals(characters.get(dragonIndex).getCurrentRoom())) {
      System.out.println(Dragon.getDragonEncounter());
      gameState = 2;
    }
  }

  /**
   * Overrides the keyPressed method in the Papplet class and determines whether it is the player's
   * turn or dragon's turn.
   */
  @Override
  public void keyPressed() {
    // finds the indexes of the keyholder, player, and dragon, which works in any order that the
    // three are inputted
    int dragonIndex = 0;
    int playerIndex = 0;
    int characterIndex = 0;
    for (int i = 0; i < characters.size(); i++) {
      if (characters.get(i) instanceof Player) {
        playerIndex = i;
      } else if (characters.get(i) instanceof Dragon) {
        dragonIndex = i;
      } else {
        characterIndex = i;
      }
    }
    // changes the player's room to the key pressed (-48 because of ASCII conversion)
    if (gameState == 0) {
      if (((Player) characters.get(playerIndex)).changeRoom(getRoomByID(key - 48))) {
        isDragonTurn = true;
      }
      System.out.println("Pick a valid room");
    }
  }

  /**
   * Loads in room info using the file stored in roomInfo
   *
   * @author Michelle
   */
  private void loadRoomInfo() {
    System.out.println("Loading rooms...");
    Scanner fileReader = null;
    try {
      //scanner to read from file
      fileReader = new Scanner(roomInfo);

      //read line by line until none left
      while (fileReader.hasNext()) {
        String nextLine = fileReader.nextLine();

        //parse info and create new room
        String[] parts = nextLine.split(" \\| ");
        int ID = Integer.parseInt(parts[1].trim()); //get the room id
        String imageName = null;
        String description = null;
        PImage image = null;
        Room newRoom = null;

        if (parts.length >= 3) {
          imageName = parts[2].trim();
          image = this.loadImage("images" + File.separator + imageName);

        }

        if (parts.length == 4) {
          description = parts[3].trim(); //get the room description
        }

        switch (parts[0].trim()) {
          case "S":
            newRoom = new StartRoom(ID, image);
            break;
          case "R":
            newRoom = new Room(ID, description, image);
            break;
          case "P":
            newRoom = new PortalRoom(ID, description, image);
            break;
          case "T":
            newRoom = new TreasureRoom(ID);
            break;
          default:
            break;
        }

        if (newRoom != null) {
          roomList.add(newRoom);
        }
      }
    } catch (IOException e) { //handle checked exception
      e.printStackTrace();
    } finally {
      if (fileReader != null)
        fileReader.close(); //close scanner regardless of what happened for security reasons :)
    }
  }

  /**
   * Loads in room connections using the file stored in mapInfo
   *
   * @author Michelle
   */
  private void loadMap() {
    System.out.println("Loading map...");
    Scanner fileReader = null;
    try {
      //scanner to read from file
      fileReader = new Scanner(mapInfo);

      //read line by line until none left
      while (fileReader.hasNext()) {

        //parse info
        String nextLine = fileReader.nextLine();
        String[] parts = nextLine.split(" ");
        int id = Integer.parseInt(parts[0]);

        Room toEdit = getRoomByID(id); //get the room we need to update info for adjacent rooms

        //add all the rooms to the adj room list of toEdit
        for (int i = 1; i < parts.length; i++) {
          Room toAdjAdd = getRoomByID(Integer.parseInt(parts[i]));
          toEdit.addToAdjacentRooms(toAdjAdd);
        }
      }
    } catch (IOException e) { //handle checked exception
      e.printStackTrace();
    } finally {    //close scanner regardless of what happened for security reasons :)
      if (fileReader != null)
        fileReader.close();
    }
  }

  /**
   * Get the room objected associated with the given ID.
   *
   * @param id the ID of the room to retrieve
   * @return the Room that corresponds to that id
   * @author Michelle
   */
  private Room getRoomByID(int id) {
    int indexToEdit = roomList.indexOf(new Room(id, "dummy", null));
    Room toEdit = roomList.get(indexToEdit);
    return toEdit;
  }

  /**
   * Adds characters and keys.
   */
  private void loadCharacters() {
    System.out.println("Adding characters...");
    characters.add(new Character(getRoomByID(5), "KEYHOLDER"));
    characters.add(new Player(getRoomByID(1)));
    characters.add(new Dragon(getRoomByID(9)));
  }
}
