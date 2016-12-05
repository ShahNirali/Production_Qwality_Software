package connectfour;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConnectFourView {
  private JFrame frame = new JFrame("Connect 4");
  private JButton[][] discButton;
  private JPanel boardPanel;
  private ConnectFour connectFour;
  private int numberOfRows;
  private int numberOfColumns;
  private ComputerPlayer computerPlayer;
  
  /*
   * Form the GUI which will display board
   */
  public ConnectFourView(ConnectFour model) {
    this.connectFour = model;
    numberOfRows = model.getRows();
    numberOfColumns = model.getColumns();
    discButton = new JButton[numberOfRows][numberOfColumns];
    boardPanel = new JPanel(new GridLayout(numberOfRows, numberOfColumns));
    formBoard();
    
    //frame.getContentPane().setLayout(new GridLayout(numberOfRows, numberOfColumns));
    frame.getContentPane().add(boardPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 800);
    frame.setVisible(true);
    
    connectFour.addConnectFourListener(new ConnectFourListner() {
      
      @Override
      public void playerWon(Disc disc) {
        displayDisc(disc);
        showMessage(disc.getPlayer() + " won.");
        
      }
      
      @Override
      public void playedDisc(Disc disc) {
        displayDisc(disc);
        if(computerPlayer != null && !disc.getPlayer().equals(computerPlayer)) {
          try {
            connectFour.playDisc(computerPlayer.getMove(disc.getColumnPlayed()));
          } catch (IllegalMoveException e) {
          }
        }
      }
      
      @Override
      public void gameStarted() {
        resetDiscs();
      }
      
      @Override
      public void gameEnded() {
        showMessage("Game Over!");
      }
    });
    startNewGame("Let's play Connect4!");
  }

  protected void showMessage(String message) {
    JOptionPane.showMessageDialog(frame, message);
  }

  private void displayDisc(Disc disc) {
    int rowPlayed = numberOfRows - 1 -disc.getRowPlayed();
    int columnPlayed = disc.getColumnPlayed();
    Color discColor = disc.getPlayer().getColor();
    discButton[rowPlayed][columnPlayed].setBackground(discColor);
  }

  private void startNewGame(String message) {
    int dialogResult = JOptionPane.showConfirmDialog (frame, 
        message + "\nWould you like to start new game? ","Connect4",JOptionPane.YES_NO_OPTION);
    if (dialogResult == JOptionPane.YES_OPTION) {
      Player player = new AllPlayer("Player", Color.RED);
      computerPlayer = new ComputerPlayer("Computer", Color.BLUE, connectFour);
      connectFour.startGame(player, computerPlayer);
    } else {
      System.exit(0);
    }

  }

  private void resetDiscs() {
    for (int row = 0; row < numberOfRows; row++) {
      for (int column = 0; column < numberOfColumns; column++) {
        discButton[row][column].setBackground(Color.GRAY);
      }
    }
  }

  private void formBoard() {
    for (int row = 0; row < numberOfRows; row++) {
      for (int col = 0; col < numberOfColumns; col++) {
        JButton button = createButton(col);
        button.setBackground(Color.GRAY);
        button.setOpaque(true);
        boardPanel.add(button);
        discButton[row][col] = button;
      }
    }
  }

  private JButton createButton(final int column) {
    JButton button = new JButton();
    button.addActionListener(new ActionListener() {
      
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          connectFour.playDisc(column);
        } catch (IllegalMoveException moveInvalid) {
          JOptionPane.showMessageDialog(frame, moveInvalid.getMessage());
        }
      }
    });
    return button;
  }
}
