package connectfour;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer extends Player {
  private ConnectFour game;
  private Disc[][] board;
  private List<Point> moveHistory;
  
  public ComputerPlayer(String name, Color color, ConnectFour game) {
    super(name, color);
    this.game = game;
    moveHistory = new ArrayList<Point>();
  }

  private int random() {
    return new Random().nextInt(game.getColumns() - 1);
  }
  
  public int getMove(int prevColumnPlayed) {
    int column;
    boolean isValidRow = false;
    board = game.getBoard();
    int row = game.getLowestRow(prevColumnPlayed) - 1;
    while(row < 0) {
      row = game.getLowestRow(random());
      isValidRow = true;
    }
    if (isValidRow) {
      return row;
    }
    column = getNextRow(row, prevColumnPlayed);
    column = (column == -1) ? random() : column;
    while(game.getLowestRow(column) < 0) {
      column = game.getLowestRow(random());
    }
    return column;
  }
  
  /* Checking for horizontal and vertical line with one Disc */
  private int getNextRow(int row, int column) {
    int horizontalLine = checkLine(new Point(row, 0), 
                                   new Point(row, game.getColumns() - 1));
    int verticalLine = checkLine(new Point(0, column), 
                                 new Point(game.getRows() - 1, column));
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
    while (x < game.getRows() && 
        ((directionOfY >= 0 && y < game.getColumns()) || directionOfY < 0 && y >= 0)) {
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
        colOfCurrentDisc = (colOfCurrentDisc >= game.getColumns()) ? 
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
