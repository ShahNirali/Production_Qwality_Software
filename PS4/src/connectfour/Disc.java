package connectfour;

public class Disc {
  Player player;
  int rowPlayed;
  int columnPlayed;
  
  public Disc(Player player, int rowPlayed, int columnPlayed) {
    if (player == null) {
      throw new IllegalArgumentException("Disc can't be null");
    }
    if(rowPlayed < 0 || columnPlayed < 0) {
      throw new IllegalArgumentException("Disc can't have negative co-ordinates");
    }
    this.player = player;
    this.rowPlayed = rowPlayed;
    this.columnPlayed = columnPlayed;
  }
  
  public int getRowPlayed() {
    return rowPlayed;
  }
  
  public int getColumnPlayed() {
    return columnPlayed;
  }
  
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
