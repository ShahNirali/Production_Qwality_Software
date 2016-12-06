package connectfour;

/**
 * A grid of Disc forms the board for the game. It stores information
 * of player, and (row, column) played by the player.
 * 
 * @author nns271
 */
public class Disc {
  Player player;
  int rowPlayed;
  int columnPlayed;
  
  /**
   * Used to form Object when player hits a button on board.
   * @param player An object of Player
   * @param rowPlayed integer value
   * @param columnPlayed integer value
   * @throws IllegalArgumentException in two cases when player is null
   *        and when rowPlayer and columnPlayed are negative values
   */
  public Disc(Player player, int rowPlayed, int columnPlayed) {
    if (player == null) {
      throw new IllegalArgumentException("Player can't be null");
    }
    if(rowPlayed < 0 || columnPlayed < 0) {
      throw new IllegalArgumentException("Disc can't have negative co-ordinates");
    }
    this.player = player;
    this.rowPlayed = rowPlayed;
    this.columnPlayed = columnPlayed;
  }
  
  /**
   * Get rowPlayed by player on the board.
   * @return rowPlayed by the player object
   */
  public int getRowPlayed() {
    return rowPlayed;
  }
  
  /**
   * Get columnPlayed by player on the board.
   * @return columnPlayed by the player object
   */
  public int getColumnPlayed() {
    return columnPlayed;
  }
  
  /**
   * Get player's information
   * @return an object of player
   */
  public Player getPlayer() {
    return player;
  }
  
  @Override
  public String toString() {
    StringBuilder discDetails = new StringBuilder();
    discDetails.append(player.getName());
    discDetails.append(": [");
    discDetails.append(rowPlayed);
    discDetails.append(", ");
    discDetails.append(columnPlayed);
    discDetails.append("]");
    return discDetails.toString();    
  }
}
