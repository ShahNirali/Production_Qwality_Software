package canvas;

/**
 * Interface that contains actions performed on canvas. 
 * Mainly it notifies these actions to all the listners/Observers.
 * Forms integral part of Observer pattern.
 * 
 * @author nns271
 *
 */
public interface CanvasListner {
  
  /**
   * This is the first action performed after the canvas is initialized.
   * All the Observers should know that the canvas is ready for drawing.
   * It is also responible for clearing the canvas.
   */
  void startDrawing();
  
  /**
   * This event occurs after everytime user moves his mouse to draw 
   * on canvas. Used to display the image drawn by the user.
   * @param image An object of Shape used for drawing
   */
  void showDrawing(Shape image);
}
