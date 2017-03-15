package connectfour;

/**
 * ConnectFourApp acts like a driver class and starts the game.
 * 
 * @author Nirali 
 */
public class ConnectFourApp {
  
  /**
   * Used to start the game of connect4.
   */
  public void go() {
    ConnectFour connectFour = ConnectFour.getInstance();
    new ConnectFourView(connectFour);
  }
  
  public static void main(String args[]) {
    new ConnectFourApp().go();
  }
}
