package canvas;

import java.awt.Graphics;

/**
 * Shape is drawn by the user when he moves his mouse. 
 * It stores information about the co-ordinates that are used by graphics
 * object to display on the canvas.
 * 
 * @author nns271
 *
 */
public class Shape {
  private int x1;
  private int y1;
  private int x2;
  private int y2;
  
  /**
   * Default Constructor of Shape.
   */
  public Shape() {
  }
  
  /**
   * Constructor to form Shape object.
   * @param x1 x co-ordinate of first point
   * @param y1 y co-ordinate of first point
   * @param x2 x co-ordinate of second point
   * @param y2 y co-ordinate of second point
   */
  public Shape(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  /**
   * Set x co-ordinate of first point
   * @param x x co-ordinate of first point
   */
  public void setX1(int x) {
    x1 = x;
  }
  
  /**
   * Set y co-ordinate of first point
   * @param y y co-ordinate of first point
   */
  public void setY1(int y) {
    y1 = y;
  }
  
  /**
   * Set x co-ordinate of second point
   * @param x x co-ordinate of second point
   */
  public void setX2(int x) {
    x2 = x;
  }
  
  /**
   * Set y co-ordinate of second point
   * @param y y co-ordinate of second point
   */
  public void setY2(int y) {
    y2 = y;
  }
  
  /**
   * Get x co-ordinate of first point
   * return integer value of x1
   */
  public int getX1() {
    return x1;
  }
  
  /**
   * Get y co-ordinate of first point
   * return integer value of y1
   */
  public int getY1() {
    return y1;
  }
  
  /**
   * Get x co-ordinate of second point
   * return integer value of x2
   */
  public int getX2() {
    return x2;
  }
  
  /**
   * Get y co-ordinate of second point
   * return integer value of y2
   */
  public int getY2() {
    return y2;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(x1).append(", ").append(y1).append(", ");
    sb.append(x2).append(", ").append(y2);
    sb.append("]");
    return sb.toString();
  }
  
  /**
   * Renders Image on the canvas based on Shape.
   * @param graphics A reference of Graphics class
   */
  public void renderImage(Graphics graphics) {
  }
}
