package canvas;

import java.awt.Graphics;

/**
 * A subset of Shape. Pencil class is responsible for drawing
 * random lines as the mouse moves
 * 
 * @author Nirali
 */
public class Pencil extends Shape{
  
  /**
   * Default Constructor
   */
  public Pencil() {
  }
  
  /**
   * Constructor holding values of the co-ordinates.
   * @param x1 x co-ordinate of first point
   * @param y1 y co-ordinate of first point
   * @param x2 x co-ordinate of second point
   * @param y2 y co-ordinate of second point
   */
  public Pencil(int x1, int y1, int x2, int y2) {
    super(x1, y1, x2, y2);
  }
  
  @Override
  public void renderImage(Graphics g) {
    int x1 = super.getX1();
    int y1 = super.getY1();
    int x2 = super.getX2();
    int y2 = super.getY2();
    g.drawLine(x1, y1, x2, y2);
  }
}
