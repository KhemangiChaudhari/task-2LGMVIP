import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tic_Tac_Toe extends JFrame {
    private JButton[] buttons;
    private boolean playerXTurn;

    public Tic_Tac_Toe() {
        super("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        buttons = new JButton[9];
        playerXTurn = true;

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
            buttons[i].addActionListener(new ButtonListener());
            buttons[i].setForeground(Color.BLACK);
            add(buttons[i]);
        }

        setSize(300, 300);
        setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton buttonClicked = (JButton) e.getSource();
            if (playerXTurn) {
                buttonClicked.setText("X");
                buttonClicked.setEnabled(false);
                buttonClicked.setForeground(Color.BLUE);
            } else {
                buttonClicked.setText("O");
                buttonClicked.setEnabled(false);
                buttonClicked.setForeground(Color.RED);
            }

            if (checkForWin()) {
                if (playerXTurn) {
                    JOptionPane.showMessageDialog(null, "Player X won the Match!");
                } else {
                    JOptionPane.showMessageDialog(null, "Player O won the Match!");
                }
                resetGame();
            } else if (checkForDraw()) {
                JOptionPane.showMessageDialog(null, "Match draw!");
                resetGame();
            } else {
                playerXTurn = !playerXTurn;
            }
        }
    }

    private boolean checkForWin() {
        String[] board = new String[9];
        for (int i = 0; i < 9; i++) {
            board[i] = buttons[i].getText();
        }

        // Check rows
        for (int i = 0; i < 9; i += 3) {
            if (board[i].equals(board[i + 1]) && board[i].equals(board[i + 2]) && !board[i].equals("")) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[i].equals(board[i + 3]) && board[i].equals(board[i + 6]) && !board[i].equals("")) {
                return true;
            }
        }

        // Check diagonals
        if (board[0].equals(board[4]) && board[0].equals(board[8]) && !board[0].equals("")) {
            return true;
        }

        if (board[2].equals(board[4]) && board[2].equals(board[6]) && !board[2].equals("")) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 9; i++) {
            if (buttons[i].getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setForeground(Color.BLACK);
        }
        playerXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tic_Tac_Toe());
    }
}
