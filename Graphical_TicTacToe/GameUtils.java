import javax.swing.*; // This import is actually not needed for GameUtils itself. Can be removed if strict.
import java.awt.*;    // This import is actually not needed for GameUtils itself. Can be removed if strict.
import java.awt.event.*; // This import is actually not needed for GameUtils itself. Can be removed if strict.

// GameUtils.java
// Provides static utility methods for checking Tic-Tac-Toe game states
// Ensure this file ONLY contains the GameUtils class below.
// Remove any duplicate GameBoard class definition if it exists here.
public class GameUtils {

    /**
     * Checks if a player has won the game on the given board.
     * @param board The 3x3 character array representing the game board.
     * @param player The character ('X' or 'O') of the player to check for a win.
     * @return true if the player has won, false otherwise.
     */
    public static boolean hasWon(char[][] board, char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Check main diagonal (top-left to bottom-right)
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }

        // Check anti-diagonal (top-right to bottom-left)
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the game is a draw (no empty cells left and no winner).
     * @param board The 3x3 character array representing the game board.
     * @return true if the game is a draw, false otherwise.
     */
    public static boolean isDraw(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Found an empty cell, so it's not a draw yet
                }
            }
        }
        // If no empty cells and no winner (as checked by hasWon), it's a draw
        return true;
    }
}