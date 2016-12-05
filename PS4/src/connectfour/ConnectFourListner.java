package connectfour;

public interface ConnectFourListner {
  void gameStarted();
  void gameEnded();
  void playedDisc(Disc disc);
  void playerWon(Disc disc);
}
