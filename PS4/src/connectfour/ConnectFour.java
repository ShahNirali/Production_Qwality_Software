package connectfour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/*
 * Reference: https://github.com/hanshenrik/connect4/blob/master/src/main/java/FPModel.java
 */
public class ConnectFour {
  private enum Direction {
    LEFT,
    RIGHT,
  }
  
  private Player currentPlayer;
  private Player player1;
  private Player player2;
  private final int ROWS = 6;
  private final int COLUMNS = 7;
  private Disc[][] board;
  private List<ConnectFourListner> listners = new ArrayList<ConnectFourListner>();
  private boolean isGameOver;
  private int freeDiscs;
  private boolean winningSequence;
  
  public ConnectFour() {
    isGameOver = false;
    winningSequence = false;
    freeDiscs = ROWS*COLUMNS;
    initializeBoard();
  }
  
  private void initializeBoard() {
    board = new Disc[ROWS][COLUMNS];
    for (int row = 0; row < ROWS; row++) {
      for (int column = 0; column < COLUMNS; column++) {
        board[row][column] = null;
      }
    }
  }
  
  public void addConnectFourListener(ConnectFourListner listner) {
    this.listners.add(listner);
  }

  public void removeConnectFourListener(ConnectFourListner listner) {
    this.listners.remove(listner);
  }
  
  public void startGame(Player player1, Player player2) {
    this.player1 = player1;
    this.player2 = player2;
    currentPlayer = player1;
    fireGameStartedEvent();
  }
  
  public void playDisc(int column) {
    int row = getLowestRow(column);
    if (row != -1 && !isGameFinish()) {
      Disc disc = new Disc(currentPlayer, row, column);
      board[row][column] = disc;
      freeDiscs--;
      checkWinningSequence(row, column);
      if (winningSequence) {
        firePlayerWonEvent(disc);
        return;
      }
      switchPlayer();
      firePlayedDiscEvent(disc);
    }
  }
  
  private void checkWinningSequence(int row, int column) {
    checkLine(new Point(row, 0), new Point(row, COLUMNS - 1));
    checkLine(new Point(0, column), new Point(ROWS - 1, column));
    checkLine(getStartPoint(row, column, Direction.RIGHT), new Point(ROWS - 1, COLUMNS - 1));
    checkLine(getStartPoint(row, column, Direction.LEFT), new Point(ROWS - 1, 0));
  }

  private Point getStartPoint(int row, int column, Direction direction) {
    while (row != 0 && row != (ROWS - 1) && column != 0 && column != (COLUMNS-1)) {
      row--;
      if (direction == Direction.RIGHT) {
        column--;
      } else {
        column++;
      }
    }
    return new Point(row, column);
  }

  private void checkLine(Point start, Point end) {
    int winningDisc = 0;
    Disc previous = null;
    Disc current = null;
    int directionOfX = 0;
    int directionOfY = 0;
    if (start.x < end.x) {
      directionOfX = 1;
    }
    if (start.y < end.y) {
      directionOfY = 1;
    } else if (start.y > end.y) {
      directionOfY = -1;
    }
    int x = start.x;
    int y = start.y;
    
    while (x < ROWS && 
        ((directionOfY >= 0 && y < COLUMNS) || (directionOfY < 0 && y >= 0))) {
      current = board[x][y];
      if (current != null && previous!= null && 
          current.getPlayer().equals(previous.getPlayer())) {
        winningDisc++;
      } else {
        winningDisc = 0;
      }
      
      if (winningDisc == 3) {
        winningSequence = true;
        return;
      }
      x = x + directionOfX;
      y = y + directionOfY;
      previous = current;
    }
  }

  public int getLowestRow(int column) {
    for (int i = 0; i < ROWS; i++) {
      if (board[i][column] == null) {
        return i;
      }
    }
    return -1;
  }
  
  private void switchPlayer() {
    currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
  }

  private boolean isGameFinish() {
    if (isGameOver || isFullBoard()) {
      fireGameFinishEvent();
      return true;
    }
    return false;
  }

  private boolean isFullBoard() {
    if (freeDiscs == 0) {
      return true;
    }
    return false;
  }
  
  private void fireGameStartedEvent() {
    for (ConnectFourListner listner: listners) {
      listner.gameStarted();
    }
  }

  private void firePlayedDiscEvent(Disc player) {
    for (ConnectFourListner listner: listners) {
      listner.playedDisc(player);
    }
  }
  
  private void firePlayerWonEvent(Disc player) {
    for (ConnectFourListner listner: listners) {
      listner.playerWon(player);
    }
  }
  
  private void fireGameFinishEvent() {
    for (ConnectFourListner listner: listners) {
      listner.gameEnded();
    }
  }
  
  public Disc[][] getBoard() {
    return board.clone();
  }
  
  public int getRows() {
    return ROWS;
  }
  
  public int getColumns() {
    return COLUMNS;
  }
}
