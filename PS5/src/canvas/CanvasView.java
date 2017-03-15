package canvas;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * SwingGUI used to initialse the canvas for all the users. It fires 
 * events when mouse is moved by the user. 
 * Reference: http://codereview.stackexchange.com/questions/58304/
 * implementation-of-mvc-pattern-with-paintcomponent-method
 * 
 * @author Nirali
 *
 */
public class CanvasView { 
  private JFrame frame = new JFrame("Canvas");
  private JPanel panel;
  private Canvas canvas;
  private Shape drawShape;
  
  /**
   * Used to form the initial outline of canvas. 
   * Initialise drawing panel, add action events when mouse moves,
   * and setting up canvas.
   * @param canvas an object of Canvas
   */
  public CanvasView(Canvas canvas) {
    this.canvas = canvas;
    
    panel = new JPanel(new BorderLayout());
    formDrawPanel();
    
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(200, 200);
    frame.setVisible(true);
    this.canvas.addCanvasListener(new CanvasListner() {
      
      @Override
      public void startDrawing() {
        clearCanvas();
      }
      
      @Override
      public void showDrawing(Shape image) {
        image.renderImage(panel.getGraphics());
      }
    });
  }

  private void clearCanvas() {
    panel.repaint();
    panel.removeAll();
  }

  private void formDrawPanel() {
    panel.addMouseListener(new MouseListener() {
      
      @Override
      public void mouseReleased(MouseEvent e) {  
        drawShape = null;
      }
      
      @Override
      public void mousePressed(MouseEvent e) {
        drawShape = new Pencil();
        drawShape.setX1(e.getX());
        drawShape.setY1(e.getY());
      }
      
      @Override
      public void mouseExited(MouseEvent e) {
        drawShape = null;
      }
      
      @Override
      public void mouseEntered(MouseEvent e) {
        drawShape = new Pencil();
        drawShape.setX1(e.getX());
        drawShape.setY1(e.getY());
      }
      
      @Override
      public void mouseClicked(MouseEvent e) {
        drawShape = new Pencil();
        drawShape.setX1(e.getX());
        drawShape.setY1(e.getY());
      }
    });
    
    panel.addMouseMotionListener(new MouseMotionListener() {
      
      @Override
      public void mouseMoved(MouseEvent mouseEvent) {
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        if (drawShape != null) {
          drawShape.setX2(x);
          drawShape.setY2(y);
          canvas.draw(drawShape);
          drawShape.setX1(x);
          drawShape.setY1(y);
        }
      }
      
      @Override
      public void mouseDragged(MouseEvent mouseEvent) {
      }
    });
  }
}
