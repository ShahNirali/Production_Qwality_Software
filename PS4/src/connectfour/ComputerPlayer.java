package connectfour;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Computer player is subset of Player. It has the functionality
 * of playing random moves and taking a move when he thinks
 * he can win.
 * Reference: https://github.com/hanshenrik/connect4/blob/master/src/main/java/AIPlayer.java
 * 
 * @author nns271
 */
public class ComputerPlayer extends Player {
  private ConnectFour game;
  private int ROWS;
  private int COLUMNS;
  private Disc[][] board;
  private List<Point> moveHistory;
  
  /**
   * Contructor of ComputerPlayer that calls constructor of superclass 
   * Player and initializes its data members
   * @param name conataing name of the player
   * @param color Color of Disc when player plays his turn
   * @param game An object of ConnectFour
   */
  public ComputerPlayer(String name, Color color, ConnectFour game) {
    super(name, color);
    this.game = game;
    this.ROWS = game.getRows();
    this.COLUMNS = game.getColumns();
  }
  
  /**
   * Get next column to play. 
   * Moves are random but takes winning move when it thinks
   * it can win. This is done by checking horizontal, vertical, and
   * diagonal lines that help take one step closer to victory.
   * @param prevColumnPlayed column played by his opponent
   */
  public int getMove() {
    board = game.getBoard();
    moveHistory = game.getPlayerMoveHistory(this);
    int column = checkWinningMove();
    //For testing of legal move
    //column = 5;
    column = (column == -1) ? random() : column;
    while (game.getLowestRow(column) < 0) {
      column = random();
      if (game.isFullBoard()) {
        return -1;
      }
    }
    return column;
  }
  
  private int checkWinningMove() {
    for (int i = 0; i < moveHistory.size(); i++) {
      Point computerMove = moveHistory.get(i);
      int horizontalLine = checkLine(computerMove, 
          new Point(computerMove.x, COLUMNS - 1));
      int verticalLine = checkLine(computerMove, 
          new Point(ROWS - 1, computerMove.y));
      int diagonalLeft = checkLine(game.
          getStartPoint(computerMove.x, computerMove.y, Direction.RIGHT), 
          new Point(ROWS - 1, COLUMNS - 1));
      int diagonalRight = checkLine(game.
          getStartPoint(computerMove.x, computerMove.y, Direction.LEFT), 
          new Point(ROWS - 1, 0));
      if (horizontalLine != -1) {
        return horizontalLine;
      } else if (verticalLine != -1) {
        return verticalLine;
      } else if (diagonalLeft != -1) {
        return diagonalLeft;
      } else if (diagonalRight != -1) {
        return diagonalRight;
      }
    }
    return -1;
  }
  
  private int checkLine(Point start, Point end) {
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
      if (current != null && previous!= null && current.getPlayer() instanceof ComputerPlayer
          && current.getPlayer().equals(previous.getPlayer())) {
        winningDisc++;
      } else {
        winningDisc = 0;
      }
      
      if (winningDisc == 2) {
        return (y + directionOfY) > (COLUMNS - 1) ? -1 : y + directionOfY;
      }
      x = x + directionOfX;
      y = y + directionOfY;
      previous = current;
    }
    return -1;
  }
  
  private int random() {
    return new Random().nextInt(COLUMNS - 1);
  }
}
