package connectfour;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.awt.Color;


public class DiscTest {
  @Test
  public void testDisc_withCorrectArguments() {
    Player player = new Player("John", Color.BLACK);
    Disc disc = new Disc(player, 3, 2);
    assertTrue(disc.getPlayer().equals(player));
    assertEquals(3, disc.getRowPlayed());
    assertEquals(2, disc.getColumnPlayed());
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testDisc_withNullPlayer() {
    new Disc(null, 3, 2);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testDisc_withNegativeRows() {
    new Disc(new Player("Player", Color.BLACK), -3, 2);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testDisc_withNegativeColumns() {
    new Disc(new Player("Player", Color.BLACK), 3, -2);
  }
  
  @Test
  public void testToString_simple() {
    Player player = new Player("John", Color.BLACK);
    Disc disc = new Disc(player, 3, 2);
    assertEquals("John: [3, 2]", disc.toString());
  }
  
}
