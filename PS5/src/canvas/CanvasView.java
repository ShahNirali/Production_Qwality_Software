package canvas;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Reference: http://codereview.stackexchange.com/questions/58304/
 * implementation-of-mvc-pattern-with-paintcomponent-method
 * 
 * @author nns271
 *
 */
public class CanvasView { 
  private JFrame frame = new JFrame("Canvas");
  private JPanel panel;
  private Canvas canvas;
  private Shape drawShape;
  
  public CanvasView(Canvas canvas) {
    this.canvas = canvas;
    
    panel = new JPanel(new BorderLayout());
    formDrawPanel();
    
    frame.getContentPane().setLayout(new BorderLayout());
    frame.getContentPane().add(panel, BorderLayout.CENTER);    
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
        displayImage(image);
      }
    });
  }

  private void displayImage(Shape image) {
    Graphics g = panel.getGraphics();
    g.drawLine(image.getX1(), image.getY1(), image.getX2(), image.getY2());
    panel.paintComponents(g);
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
      }
      
      @Override
      public void mouseExited(MouseEvent e) {
        drawShape = null;
      }
      
      @Override
      public void mouseEntered(MouseEvent e) {
        drawShape = new Shape();
        drawShape.setX1(e.getX());
        drawShape.setY1(e.getY());
      }
      
      @Override
      public void mouseClicked(MouseEvent e) {
      }
    });
    
    panel.addMouseMotionListener(new MouseMotionListener() {
      
      @Override
      public void mouseMoved(MouseEvent mouseEvent) {
        if (drawShape != null) {
          drawShape.setX2(mouseEvent.getX());
          drawShape.setY2(mouseEvent.getY());
          canvas.draw(drawShape);
        }
      }
      
      @Override
      public void mouseDragged(MouseEvent mouseEvent) {
        if (drawShape != null) {
          drawShape.setX2(mouseEvent.getX());
          drawShape.setY2(mouseEvent.getY());
          canvas.draw(drawShape);
        }
      }
    });
  }
}
