package connectfour;

import java.awt.Color;

/**
 * Player is used to store information about players playing the game 
 * in terms of name and color. 
 *  
 * @author Nirali
 */
public class Player {
  private final String name;
  private final Color color;
  
  /**
   * Constructor of Player.
   * @param name Name of the player
   * @param color Color of Disc when player plays his turn
   * @throws IllegalArgumentException when name or color is empty
   */
  public Player(String name, Color color) {
    if (name == null || color == null || name.trim().length() == 0) {
      throw new IllegalArgumentException("Name and Color can't be empty");
    }
    this.name = name;
    this.color = color;
  }
  
  /**
   * Used to get name of the player.
   * @return string conataing name of the player object
   */
  public String getName() {
    return name;
  }
  
  /**
   * Used to get color select by the player.
   * @return color choosen by player
   */
  public Color getColor() {
    return color;
  }
  
  @Override
  public String toString() {
    return getName();
  }
}
