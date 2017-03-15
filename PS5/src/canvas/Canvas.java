package canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Canvas class is responsible for drawing different shapes on the 
 * drawing panel (i.e. paint application where drawing with pencil)
 * It also notifies all the observers of the canvas.
 * 
 * @author Nirali
 *
 */
public class Canvas {
  private List<CanvasListner> listeners = new ArrayList<CanvasListner>();
  
  /**
   * Constructor of Canvas. 
   * It creates object of this class.
   */
  public Canvas() {
  }
  
  /**
   * Add listener of CanvasListner interface. 
   * All the listeners are notified of the events performed.
   * @param canvasListner A refernce of CanvasListner object
   * @throws IllegalArgumentException when canvasListner passed is null
   */
  public void addCanvasListener(CanvasListner canvasListner) {
    if (canvasListner == null) {
      throw new IllegalArgumentException("Listner can't be empty");
    }
    this.listeners.add(canvasListner);
  }

  /**
   * Remove listener of CanvasListner interface.
   * @param canvasListner A refernce of CanvasListner object
   * @throws IllegalArgumentException when canvasListner passed is null
   */
  public void removeCanvasListener(CanvasListner canvasListner) {
    if (canvasListner == null) {
      throw new IllegalArgumentException("Listner can't be empty");
    }
    this.listeners.remove(canvasListner);
  }
  
  /**
   * Start drawing on the canvas. It notifies all the listners
   * of this start event.
   */
  public void startPainting() {
    fireDrawingStartEvent();
  }
  
  /**
   * Draw image on the cavas. 
   * @param image A reference of Shape object
   * @throws IllegalArgumentException when image is null
   */
  public void draw(Shape image) {
    if (image == null) {
      throw new IllegalArgumentException("Can't draw image without co-ordinates");
    }
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
