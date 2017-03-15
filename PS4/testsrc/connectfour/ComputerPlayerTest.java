package connectfour;

import static org.junit.Assert.assertEquals;
import java.awt.Color;
import org.junit.Test;

public class ComputerPlayerTest {
  
  @Test
  public void testGetMove_simple() {
    ConnectFour connectFour= ConnectFour.getInstance();
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    computerPlayer.getMove();
  }
  
  @Test
  public void testGetMove_oneColumnIsFull() throws IllegalMoveException {
    ConnectFour connectFour= ConnectFour.getInstance();
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    for (int row = 0; row < connectFour.getRows(); row++) {
      connectFour.playDisc(5);
    }
    connectFour.playDisc(computerPlayer.getMove());
  }
  
  @Test
  public void testGetMove_withTwoComputerPlayer() throws IllegalMoveException {
    ConnectFour connectFour= ConnectFour.getInstance();
    ComputerPlayer computerPlayer1 = new ComputerPlayer("Computer1", Color.BLUE, connectFour);
    ComputerPlayer computerPlayer2 = new ComputerPlayer("Computer2", Color.BLUE, connectFour);
    connectFour.startGame(computerPlayer1, computerPlayer2);
    for (int row = 0; row < connectFour.getRows(); row++) {
      connectFour.playDisc(computerPlayer1.getMove());
      connectFour.playDisc(computerPlayer2.getMove());
    }
  }
  
  @Test
  public void testGetMove_withWinningMoveByComputer() throws IllegalMoveException {
    ConnectFour connectFour= ConnectFour.getInstance();
    ConnectFourListner listner = new ConnectFourListner() {

      @Override
      public void playerWon(Disc disc) {
        showMessage(disc.getPlayer() + " won");
      }
      
      private void showMessage(String string) {
        assertEquals("Computer won", string);
      }
      
      @Override
      public void playedDisc(Disc disc) {
      }
      
      @Override
      public void gameStarted() {
      }
      
      @Override
      public void gameEnded() {
      }
    };
    connectFour.addConnectFourListener(listner);
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    int col;
    for (col = 0; col < 3; col++) {
      for (int row = 0; row < connectFour.getRows(); row++) {
        connectFour.playDisc(col);
      }
    }
    connectFour.playDisc(3);
    connectFour.playDisc(3);
    connectFour.playDisc(3);
    connectFour.playDisc(computerPlayer.getMove());
  }
  
  @Test
  public void testGetMove_withFullBoard() throws IllegalMoveException {
    ConnectFour connectFour= ConnectFour.getInstance();
    ConnectFourListner listner = new ConnectFourListner() {
      @Override
      public void playerWon(Disc disc) {
      }
      
      @Override
      public void playedDisc(Disc disc) {
      }
      
      @Override
      public void gameStarted() {
      }
      
      @Override
      public void gameEnded() {
        showMessage("Game Over");
      }
      
      private void showMessage(String string) {
        assertEquals("Game Over", string);
      }
    };
    connectFour.addConnectFourListener(listner);
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer2", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    for (int col = 0; col < connectFour.getColumns(); col++) {
      for (int row = 0; row < connectFour.getRows(); row++) {
        connectFour.playDisc(col);
      }
    }
  }
  
  @Test
  public void testGetMove_withLastColumnFull() throws IllegalMoveException {
    ConnectFour connectFour= ConnectFour.getInstance();
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer2", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    for (int col = 0; col < 1; col++) {
      for (int row = 0; row < connectFour.getRows(); row++) {
        connectFour.playDisc(5);
      }
    }
    connectFour.playDisc(computerPlayer.getMove());
  }
  
  @Test
  public void testGetMove_withOneColumnEmpty() throws IllegalMoveException {
    ConnectFour connectFour= ConnectFour.getInstance();
    ConnectFourListner listner = new ConnectFourListner() {
      @Override
      public void playerWon(Disc disc) {
      }
      
      @Override
      public void playedDisc(Disc disc) {
      }
      
      @Override
      public void gameStarted() {
      }
      
      @Override
      public void gameEnded() {
        showMessage("Game Over");
      }
      
      private void showMessage(String string) {
      }
    };
    connectFour.addConnectFourListener(listner);
    Player player = new Player("Player", Color.BLACK);
    ComputerPlayer computerPlayer = new ComputerPlayer("Computer2", Color.BLUE, connectFour);
    connectFour.startGame(player, computerPlayer);
    for (int col = 0; col < 6; col++) {
      for (int row = 0; row < connectFour.getRows(); row++) {
        connectFour.playDisc(col);
      }
    }
    connectFour.playDisc(computerPlayer.getMove());
  }
}
