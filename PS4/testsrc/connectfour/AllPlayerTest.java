package connectfour;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import org.junit.Test;

public class AllPlayerTest {
  @Test
  public void testAllPlayer_simple() {
    new AllPlayer("Player", Color.BLACK);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAllPlayer_withNullName() {
    new AllPlayer(null, Color.BLACK);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAllPlayer_withNullColor() {
    new AllPlayer("Player", null);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testAllPlayer_withSpaces() {
    new AllPlayer("\t   \n", Color.BLACK);
  }
  
  @Test
  public void testGetNameAndColor() {
    Player player = new AllPlayer("Player", Color.BLACK);
    assertEquals("Player", player.getName());
    assertEquals(Color.BLACK, player.getColor());
  }
  
  @Test
  public void testToString() {
    Player player = new AllPlayer("Player", Color.BLACK);
    assertEquals("Player", player.toString());
  }
  
}
