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
    moveHistory = new ArrayList<Point>();
  }
  
  /**
   * Get next column to play. 
   * Moves are random but takes correct move when it thinks
   * it can win. This is done by checking horizontal and vertical
   * lines that help take one step closer to victory.
   * @param prevColumnPlayed column played by his opponent
   */
  public int getMove(int prevColumnPlayed) {
    int column;
    boolean isValidRow = false;
    board = game.getBoard();
    moveHistory = game.getPlayerMoveHistory(this);
    if (moveHistory.isEmpty()) {
      column = random();
      moveHistory.add(new Point(game.getLowestRow(column), column));
      return column;
    }
    int row = game.getLowestRow(prevColumnPlayed) - 1;
    while(row < 0) {
      row = game.getLowestRow(random());
      isValidRow = true;
    }
    if (isValidRow) {
      moveHistory.add(new Point(game.getLowestRow(row), row));
      return row;
    }
    column = getNextRow(row, prevColumnPlayed);
    column = (column == -1) ? random() : column;
    while(game.getLowestRow(column) < 0) {
      column = game.getLowestRow(random());
    }
    moveHistory.add(new Point(game.getLowestRow(column), column));
    return column;
  }
  
  private int random() {
    return new Random().nextInt(COLUMNS - 1);
  }

  private int getNextRow(int row, int column) {
    int horizontalLine = checkLine(new Point(row, 0), 
                                   new Point(row, COLUMNS - 1));
    int verticalLine = checkLine(new Point(0, column), 
                                 new Point(ROWS - 1, column));
    return horizontalLine >= 0 ? horizontalLine : verticalLine;
  }

  private int checkLine(Point start, Point end) {
    int winningSequence = 0;
    int countSimilarInRow = 0;
    Disc previous1 = null;
    Disc current1 = null;
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
        ((directionOfY >= 0 && y < COLUMNS) || directionOfY < 0 && y >= 0)) {
      current = board[x][y];
      if (previous != null && current != null && current.getPlayer().equals(previous.getPlayer())) {
        winningSequence++;
      } else {
        winningSequence = 0;
      }
      if (winningSequence > 0) {
        countSimilarInRow = winningSequence;
        previous1 = previous;
        current1 = current;
      }
      x = x + directionOfX;
      y = y + directionOfY;
      previous = current;
    }
    if (countSimilarInRow > 0) {
      if (directionOfY == 1) {
        @SuppressWarnings("null")
        int colOfPreviousDisc = previous1.getColumnPlayed() - countSimilarInRow;
        colOfPreviousDisc = (colOfPreviousDisc) < 0 ? previous1.getColumnPlayed() : colOfPreviousDisc;
        int column1 = game.getLowestRow(colOfPreviousDisc);
        int colOfCurrentDisc = current1.getColumnPlayed() + 1;
        colOfCurrentDisc = (colOfCurrentDisc >= COLUMNS) ? 
          current1.getRowPlayed() : colOfCurrentDisc;
        int column2 = game.getLowestRow(colOfCurrentDisc);
        if (column1 == previous1.getRowPlayed()) {
          return colOfPreviousDisc;
        } else if (column2 == current1.getRowPlayed()) {
          return colOfCurrentDisc;
        } else {
          return -1;
        }
      }
      return current1.getColumnPlayed();
    }
    return -1;
  }
}
