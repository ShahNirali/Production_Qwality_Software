package connectfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class IllegalMoveExceptionTest {
  
  @Test
  public void testIllegalMoveException_withoutMessage() {
    boolean exceptionCatch = false;
    try {
      throw new IllegalMoveException();
    } catch (IllegalMoveException catchException) {
      exceptionCatch = true;
    }
    assertTrue(exceptionCatch);
  }
  
  @Test
  public void testIllegalMoveException_withMessage() {
    String message = "Invalid move! Try again";
    try {
      throw new IllegalMoveException(message);
    } catch (IllegalMoveException invalidMove) {
      assertEquals(message, invalidMove.getMessage());
    }
  }
}
