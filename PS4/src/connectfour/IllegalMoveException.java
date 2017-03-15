package connectfour;

/**
 * IllegalMoveException is subset of Exception used when 
 * player takes move outside the game board.
 * 
 * @author Nirali
 */
@SuppressWarnings("serial")
public class IllegalMoveException extends Exception{
  
  /**
   * Constructor of IllegalMoveException class.
   */
  public IllegalMoveException() {
    super();
  }
  
  /**
   * Constructor of IllegalMoveException class.
   * @param message String variable to display meaningful message to player
   */
  public IllegalMoveException(String message) {
    super(message);
  }
}
