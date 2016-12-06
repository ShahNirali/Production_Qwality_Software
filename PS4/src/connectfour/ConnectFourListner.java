package connectfour;

/**
 * Interface that contains actions performed on board. 
 * Mainly it notifies these actions to all the listners/Observers 
 * of the game. Forms integral part of Observer pattern.
 * 
 * @author nns271
 */
public interface ConnectFourListner {
  
  /**
   * This is the first action performed after the board is initialized.
   * All the Observers should know that the game has started. It is also responible
   * for resetting all disc pieces to null when game starts.
   */
  void gameStarted();
  
  /**
   * This is the last action performed thus indicating that the game is over.
   * All observers are notified of this event in two cases when a player wins and second,
   * when the board become full.
   */
  void gameEnded();
  
  /**
   * This event occurs after every move of the player. Used to display the 
   * move played by the player.
   * @param disc An object of Disc is the move played by the player
   */
  void playedDisc(Disc disc);
  
  /**
   * This event occurs when a player gets 4 consecutive disc. 
   * @param disc An object of Disc is the move played by the player
   */
  void playerWon(Disc disc);
}
