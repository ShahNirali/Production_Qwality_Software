package connectfour;

import java.awt.Color;

/**
 * A subset of Player. AllPlayer represents any player other than computer player
 * who can play this game.
 * 
 * @author nns271
 */
public class AllPlayer extends Player{
  
  /**
   * Contructor of AllPlayer that calls constructor of superclass 
   * Player
   * @param name conataing name of the player
   * @param color Color of Disc when player plays his turn
   */
  public AllPlayer(String name, Color color) {
    super(name, color);
  }
  
}
