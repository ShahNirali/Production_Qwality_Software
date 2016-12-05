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
  
  public int getMove() {
    int column;
    board = game.getBoard();
    if (moveHistory.isEmpty()) {
      column = random();
    } else {
    int prevColumn = moveHistory.get(moveHistory.size() - 1).y;
    column = getTwoInARow(game.getLowestRow(prevColumn), prevColumn);
    column = (column == -1) ? prevColumn : column;
    }
    moveHistory.add(new Point(game.getLowestRow(column),column));
    return column;
  }
  
  /* Checking for horizontal and vertical line with one Disc */
  private int getTwoInARow(int row, int column) {
    int horizontalLine = checkLine(new Point(row, 0), new Point(row, game.getColumns() - 1));
    int verticalLine = checkLine(new Point(0, column), new Point(game.getRows() - 1, column)); 
    return horizontalLine >= 0 ? horizontalLine : verticalLine;
  }

  private int checkLine(Point start, Point end) {
    int winningSequence = 0;
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
      if (winningSequence == 1) {
        return (x+1 >= game.getColumns()) ? x-1 : x+1;
      }
      x = x + directionOfX;
      y = y + directionOfY;
      previous = current;
    }
    return -1;
  }
}
