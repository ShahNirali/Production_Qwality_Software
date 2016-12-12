package canvas;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ShapeTest {
  
  @Test
  public void testShape_simple() {
    Shape shape = new Shape();
    shape.setX1(2);
    shape.setY1(0);
    assertEquals(2, shape.getX1());
    assertEquals(0, shape.getY1());
  }
  
  @Test
  public void testShape_withParameters() {
    Shape shape = new Shape(2, 3, 4, 5);
    assertEquals(2, shape.getX1());
    assertEquals(3, shape.getY1());
    assertEquals(4, shape.getX2());
    assertEquals(5, shape.getY2());
  }
  
  @Test
  public void testSetMethods_simple() {
    Shape shape = new Shape(2, 3, 4, 5);
    shape.setX1(123);
    shape.setY1(74);
    shape.setX2(40);
    shape.setY2(50);
    assertEquals(123, shape.getX1());
    assertEquals(74, shape.getY1());
    assertEquals(40, shape.getX2());
    assertEquals(50, shape.getY2());
  }
  
  @Test
  public void testToString() {
    Shape shape = new Shape(2, 3, 4, 5);
    assertEquals("[2, 3, 4, 5]", shape.toString());
  }
}
