package canvas;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
  private Shape image;
  private List<CanvasListner> listeners = new ArrayList<CanvasListner>();
  
  public Canvas() {
    image = new Shape();
  }
  
  public void draw() {
    fireDrawingShowEvent(image);
  }
  private void fireDrawingShowEvent(Shape drawnImage) {
    for (CanvasListner listner: listeners) {
      listner.showDrawing(drawnImage);
    }
  }

  public void startPainting() {
    fireDrawingStartEvent();
  }

  private void fireDrawingStartEvent() {
    for (CanvasListner listner: listeners) {
      listner.startDrawing();
    }
  }
  
  public void addCanvasListener(CanvasListner canvasListner) {
    this.listeners.add(canvasListner);
  }

  public void removeCanvasListener(CanvasListner canvasListner) {
    this.listeners.remove(canvasListner);
  }
}
