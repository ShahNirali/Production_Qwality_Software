package connectfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.Point;
import java.util.List;

import org.junit.Test;

public class ConnectFourTest {
  
  @Test 
  public void testInitialiseBorard_simple() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    int ROWS = connectfour.getRows();
    int COLUMNS = connectfour.getColumns();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    Disc[][] board = connectfour.getBoard();
    for (int row = 0; row < ROWS; row++) {
      for (int column = 0; column < COLUMNS; column++) {
        assertEquals(null, board[row][column]);
      }
    }
  }
  
  @Test
  public void testAddListnerRemoveListner_withCorrectArguments() {
    ConnectFour connectfour = new ConnectFour();
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
      }
    };
    connectfour.addConnectFourListener(listner);
    assertTrue(connectfour.removeConnectFourListener(listner));
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAddListner_withNullValues() {
    ConnectFour connectfour = new ConnectFour();
    ConnectFourListner listner = null;
    connectfour.addConnectFourListener(listner);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveListner_withNullArgument() {
    ConnectFour connectfour = new ConnectFour();
    ConnectFourListner listner = null;
    connectfour.removeConnectFourListener(listner);
  }
  
  @Test
  public void testStartGame_simple() {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
  }
  
  @Test (expected = IllegalArgumentException.class) 
  public void testStartGame_withSamePlayers() {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    connectfour.startGame(player1, player1);
  }
  
  @Test (expected = IllegalArgumentException.class) 
  public void testStartGame_withNullPlayers() {
    ConnectFour connectfour = new ConnectFour();
    connectfour.startGame(null, null);
  }
  
  @Test
  public void testPlayDisc_simple() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    connectfour.playDisc(5);
  }
  
  @Test (expected = IllegalMoveException.class)
  public void testPlayDisc_withInvalidMove() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    connectfour.playDisc(8);
  }
  
  @Test
  public void testPlayDisc_withTwoColumnsFull() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    for (int columns = 0; columns < 2; columns++) {
      for (int row = 0; row < connectfour.getRows(); row++) {
        connectfour.playDisc(columns);
      }
    }
  }
  
  @Test (expected = IllegalMoveException.class)
  public void testPlayDisc_withMoveInFullColumn() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    for (int row = 0; row < connectfour.getRows(); row++) {
      connectfour.playDisc(0);
    }
    connectfour.playDisc(0);
  }
  
  @Test
  public void testPlayDisc_withFullBoard() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    for (int col = 0; col < connectfour.getColumns(); col++) {
      for (int row = 0; row < connectfour.getRows(); row++) {
        connectfour.playDisc(col);
      }
    }   
  }
  
  @Test (expected = IllegalMoveException.class)
  public void testPlayDisc_withFullBoardAndExtraMove() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
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
      }
    };
    connectfour.addConnectFourListener(listner);
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    for (int col = 0; col < connectfour.getColumns(); col++) {
      for (int row = 0; row < connectfour.getRows(); row++) {
        connectfour.playDisc(col);
      }
    }
    connectfour.playDisc(4);
  }
  
  @Test
  public void testPlayDisc_withWinnngSequence() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    for (int row = 0; row < 4; row++) {
        connectfour.playDisc(0);
        connectfour.playDisc(1);
    }
  }
  
  @Test
  public void testGetPlayerMoveHistory_forPlayer() throws IllegalMoveException {
    ConnectFour connectfour = new ConnectFour();
    Player player1 = new Player("Player1", Color.BLACK);
    Player player2 = new Player("Player2", Color.GRAY);
    connectfour.startGame(player1, player2);
    connectfour.playDisc(0);
    List<Point> player1History = connectfour.getPlayerMoveHistory(player1);
    List<Point> player2History = connectfour.getPlayerMoveHistory(player2);
    assertEquals(new Point(0,0), player1History.get(0));
    assertTrue(player2History.isEmpty());
  }
}
