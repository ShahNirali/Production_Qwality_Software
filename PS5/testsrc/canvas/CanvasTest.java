package canvas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CanvasTest {
  
  @Test
  public void testAddListner_simple() {
    Canvas canvas = new Canvas();
    canvas.addCanvasListener(new CanvasListner() {
      
      @Override
      public void startDrawing() {
        showMessage("Canvas Started");
      }
      
      private void showMessage(String message) {
        assertTrue(message.equals("Canvas Started"));
      }

      @Override
      public void showDrawing(Shape image) {
      }
    });
    canvas.startPainting();
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAddListner_withNullListner() {
    Canvas canvas = new Canvas();
    canvas.addCanvasListener(null);
  }
  
  @Test
  public void testRemoveListner_simple() {
    Canvas canvas = new Canvas();
    CanvasListner canvasListner = new CanvasListner() {
      
      @Override
      public void startDrawing() {
      }
      
      @Override
      public void showDrawing(Shape image) {
      }
    };
    canvas.addCanvasListener(canvasListner);
    canvas.removeCanvasListener(canvasListner);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testRemoveListner_withNullListner() {
    Canvas canvas = new Canvas();
    canvas.removeCanvasListener(null);
  }
  
  @Test
  public void testStartPainting() {
    Canvas canvas = new Canvas();
    canvas.addCanvasListener(new CanvasListner() {
      
      @Override
      public void startDrawing() {
        showMessage("Canvas Started");
      }
      
      private void showMessage(String message) {
        assertTrue(message.equals("Canvas Started"));
      }

      @Override
      public void showDrawing(Shape image) {
      }
    });
    canvas.startPainting();
  }
  
  @Test
  public void testDraw_simple() {
    Canvas canvas = new Canvas();
    canvas.addCanvasListener(new CanvasListner() {
      
      @Override
      public void startDrawing() {
      }

      @Override
      public void showDrawing(Shape image) {
        assertEquals("[2, 3, 4, 5]", image.toString());
      }
    });
    canvas.startPainting();
    canvas.draw(new Pencil(2, 3, 4, 5));
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testDraw_withNullImage() {
    Canvas canvas = new Canvas();
    canvas.startPainting();
    canvas.draw(null);
  }
}
