package connectfour;

import java.awt.Color;
import org.junit.Test;

public class ComputerPlayerTest {
  
  @Test
  public void testGetMove_simple() {
    ConnectFour connectFour= new ConnectFour();
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    computerPlayer.getMove(0);
  }
  
  @Test
  public void testGetMove_oneColumnIsFull() throws IllegalMoveException {
    ConnectFour connectFour= new ConnectFour();
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    for (int row = 0; row < connectFour.getRows(); row++) {
      connectFour.playDisc(5);
    }
    computerPlayer.getMove(5);
  }
  
  @Test
  public void testGetMove_twoColumnsAreFull() throws IllegalMoveException {
    ConnectFour connectFour= new ConnectFour();
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    int col;
    for (col = 0; col < 2; col++) {
      for (int row = 0; row < connectFour.getRows(); row++) {
        connectFour.playDisc(col);
      }
    }
    computerPlayer.getMove(col);
  }
  
  @Test
  public void testGetMove_withRandomMove() throws IllegalMoveException {
    ConnectFour connectFour= new ConnectFour();
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    connectFour.playDisc(1);
    connectFour.playDisc(computerPlayer.getMove(1));
    connectFour.playDisc(0);
    connectFour.playDisc(computerPlayer.getMove(0));
  }
  
}
