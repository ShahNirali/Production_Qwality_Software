package canvas;

public class Shape {
  private int x1;
  private int y1;
  private int x2;
  private int y2;
  
  public Shape() {
  }
  
  public Shape(int x1, int y1, int x2, int y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
  }
  
  public void setX1(int x) {
    x1 = x;
  }
  
  public void setY1(int y) {
    y1 = y;
  }
  
  public void setX2(int x) {
    x2 = x;
  }
  
  public void setY2(int y) {
    y2 = y;
  }
  
  public int getX1() {
    return x1;
  }
  
  public int getY1() {
    return y1;
  }
  
  public int getX2() {
    return x2;
  }
  
  public int getY2() {
    return y2;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    sb.append(x1).append(", ").append(y1).append(", ");
    sb.append(x2).append(", ").append(y2);
    sb.append("]");
    return sb.toString();
  }
}
