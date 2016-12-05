package connectfour;

public class ConnectFourApp {
  public void go() {
    ConnectFour connectFour = new ConnectFour();
    new ConnectFourView(connectFour);
  }
  
  public static void main(String args[]) {
    new ConnectFourApp().go();
  }
}
