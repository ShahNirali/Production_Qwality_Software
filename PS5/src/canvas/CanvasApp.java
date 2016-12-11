package canvas;

public class CanvasApp {
  public void go() {
    Canvas canvas = new Canvas();
    new CanvasView(canvas);
    new CanvasView(canvas);
    new CanvasView(canvas);
    new CanvasView(canvas);
    canvas.startPainting();
  }
  
  public static void main(String args[]) {
    new CanvasApp().go();
  }
}
