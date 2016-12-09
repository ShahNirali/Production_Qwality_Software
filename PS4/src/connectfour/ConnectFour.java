package connectfour;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * ConnectFour class is responible for initializing game. This class also takes care of 
 * forming the (row,colum) pair of players move. It checks for winning sequence.
 * Reference: https://github.com/hanshenrik/connect4/blob/master/src/main/java/FPModel.java
 * 
 * @author nns271
 */
public class ConnectFour {
  private Player currentPlayer;
  private Player player1;
  private Player player2;
  private final int ROWS = 6;
  private final int COLUMNS = 7;
  private Disc[][] board;
  private List<ConnectFourListner> listners = new ArrayList<ConnectFourListner>();
  private int freeDiscs;
  private boolean winningSequence;
  private List<Point> player1Moves = new ArrayList<Point>();
  private List<Point> player2Moves = new ArrayList<Point>();
  
  /**
   * ConnectFour constructor to form object an initialse data memebers
   */
  public ConnectFour() {
    winningSequence = false;
    freeDiscs = ROWS*COLUMNS;
    initializeBoard();
  }
  
  /**
   * Add connect four listners to the list who will be notified 
   * of any even occuring in the game.
   * @param listner A refernce of ConnectFourListner object
   * @throws IllegalArgumentException when listner passed is null
   */
  public void addConnectFourListener(ConnectFourListner listner) {
    if (listner == null) {
      throw new IllegalArgumentException("Listner can't be empty");
    }
    this.listners.add(listner);
  }

  /**
   * Remove connect four listners from the list who no longer wish to 
   * get notified. 
   * @param listner A refernce of ConnectFourListner object
   * @return boolean indicating the listner was removed
   * @throws IllegalArgumentException when listner passed is null
   */
  public boolean removeConnectFourListener(ConnectFourListner listner) {
    if (listner == null) {
      throw new IllegalArgumentException("Listner can't be empty");
    }
    return this.listners.remove(listner);
  }
  
  /**
   * Remove connect four listners from the list who no longer wish to 
   * get notified. 
   * @param player1 a player object who is the first player
   * @param player2 a player object that is second player
   * @throws IllegalArgumentException when player passed is null
   *        or when both players are same
   */
  public void startGame(Player player1, Player player2) {
    if (player1 == null || player2 == null) {
      throw new IllegalArgumentException("Player can't be empty"); 
    }
    if (player1 == player2) {
      throw new IllegalArgumentException("Players can't be same"); 
    }
    this.player1 = player1;
    this.player2 = player2;
    currentPlayer = player1;
    fireGameStartedEvent();
  }
  
  /**
   * Play disc when the column is known. Perform operations like 
   * assigning disc to board, decrement moves pointer freeDiscs and it also 
   * checks for winning sequences.
   * @param player1 a player object who is the first player
   * @throws IllegalMoveException when player's move results in OutOfBoundary
   *        Discs
   */
  public void playDisc(int column) throws IllegalMoveException {
    if (column < -1 || column >= COLUMNS) {
      throw new IllegalMoveException("Player's illegal move");
    }
    int row = getLowestRow(column);
    if (row != -1 && !isFullBoard()) {
      Disc disc = new Disc(currentPlayer, row, column);
      board[row][column] = disc;
      freeDiscs--;
      formPlayerMoveHistory(row, column);
      checkWinningSequence(row, column);
      if (winningSequence) {
        firePlayerWonEvent(disc);
        return;
      }
      switchPlayer();
      firePlayedDiscEvent(disc);
    }
    if (row == -1) {
      if (isFullBoard()) {
        throw new IllegalMoveException("Board is full. Start a new Game");
      } else {
        throw new IllegalMoveException("Player's illegal move");
      }
    }
  }

  /**
   * Get the correct row given a column
   * @param column column in which row is needed
   * @return the index of row or return -1
   */
  public int getLowestRow(int column) {
    for (int i = 0; i < ROWS; i++) {
      if (board[i][column] == null) {
        return i;
      }
    }
    return -1;
  }
  
  /**
   * Get the startPoint inorder to check the diagonal.
   * @param row row for which start point is needed
   * @param column column for which start point is needed
   * @param direction the direction of diagonal
   * @return new Point object 
   */
  public Point getStartPoint(int row, int column, Direction direction) {
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
  
  /**
   * Get player moves for checking winning move
   * @param player Player whose move are needed
   * @return a List of moves
   */
  public List<Point> getPlayerMoveHistory(Player player) {
    if (player.equals(player1)) {
      return new ArrayList<>(player1Moves);
    } else {
      return new ArrayList<>(player2Moves);
    }
  }
  
  /**
   * Check if board is full
   * @return true is board is filled
   */
  public boolean isFullBoard() {
    if (freeDiscs == 0) {
      fireGameFinishEvent();
      return true;
    }
    return false;
  }
  private void initializeBoard() {
    board = new Disc[ROWS][COLUMNS];
    for (int row = 0; row < ROWS; row++) {
      for (int column = 0; column < COLUMNS; column++) {
        board[row][column] = null;
      }
    }
  }
  
  private void formPlayerMoveHistory(int row, int column) {
    if (currentPlayer.equals(player1)) {
      player1Moves.add(new Point(row, column));
    } else {
      player2Moves.add(new Point(row, column));
    }
  }
  
  private void checkWinningSequence(int row, int column) {
    checkLine(new Point(row, 0), new Point(row, COLUMNS - 1));
    checkLine(new Point(0, column), new Point(ROWS - 1, column));
    checkLine(getStartPoint(row, column, Direction.RIGHT), new Point(ROWS - 1, COLUMNS - 1));
    checkLine(getStartPoint(row, column, Direction.LEFT), new Point(ROWS - 1, 0));
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
  
  private void switchPlayer() {
    currentPlayer = (currentPlayer.equals(player1)) ? player2 : player1;
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
  
  /**
   * Get board detailes
   * @return a new clone of board
   */
  public Disc[][] getBoard() {
    return board.clone();
  }
  
  /**
   * Get numberOfRows on the board to check boundry conditions
   * @return totalNumber of rows on the board
   */
  public int getRows() {
    return ROWS;
  }
  
  /**
   * Get column count
   * @return totalNumber of cols on the board
   */
  public int getColumns() {
    return COLUMNS;
  }
}
