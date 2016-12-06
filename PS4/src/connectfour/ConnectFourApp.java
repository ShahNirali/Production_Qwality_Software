package connectfour;

/**
 * ConnectFourApp acts like a driver class and starts the game.
 * 
 * @author nns271 
 */
public class ConnectFourApp {
  
  /**
   * Used to start the game of connect4.
   */
  public void go() {
    ConnectFour connectFour = new ConnectFour();
    new ConnectFourView(connectFour);
  }
  
  public static void main(String args[]) {
    new ConnectFourApp().go();
  }
}