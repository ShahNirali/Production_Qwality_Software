package canvas;

import static org.junit.Assert.assertEquals;

import java.awt.Graphics;

import org.junit.Test;

public class PencilTest {
  
  @Test
  public void testPencil_simple() {
    Shape shape = new Pencil();
    shape.setX1(2);
    shape.setY1(0);
    assertEquals(2, shape.getX1());
    assertEquals(0, shape.getY1());
  }
  
  @Test
  public void testPencil_withParameters() {
    Shape shape = new Pencil(2, 3, 4, 5);
    assertEquals(2, shape.getX1());
    assertEquals(3, shape.getY1());
    assertEquals(4, shape.getX2());
    assertEquals(5, shape.getY2());
  }
  
  @Test
  public void testRenderImage() {
    
  }
}
