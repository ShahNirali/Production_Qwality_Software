package canvas;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
  private List<CanvasListner> listeners = new ArrayList<CanvasListner>();
  
  public Canvas() {
  }
  
  public void addCanvasListener(CanvasListner canvasListner) {
    this.listeners.add(canvasListner);
  }

  public void removeCanvasListener(CanvasListner canvasListner) {
    this.listeners.remove(canvasListner);
  }
  
  public void startPainting() {
    fireDrawingStartEvent();
  }
  
  public void draw(Shape image) {
    fireDrawingShowEvent(image);
  }
  private void fireDrawingShowEvent(Shape drawnImage) {
    for (CanvasListner listner: listeners) {
      listner.showDrawing(drawnImage);
    }
  }

  private void fireDrawingStartEvent() {
    for (CanvasListner listner: listeners) {
      listner.startDrawing();
    }
  }
}
