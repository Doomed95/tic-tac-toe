import java.util.Scanner;

public class Main {
    private static char[][] board;
    private static char currentPlayer;
    private static final int BOARD_SIZE = 3;

    public static void main(String[] args) {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 'X';
        initializeBoard();
        playGame();
    }

    // Method to initialize the 3x3 board with empty spaces
    public static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Method to display the board
    public static void displayBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.println("-------------");
            System.out.print("| ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    private static void playGame() {
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            displayBoard();
            System.out.println("Player " + currentPlayer + " enter your move (1-9): ");

            if (!scanner.hasNextInt()) { // Check if input is an integer
                System.out.println("Invalid input, please enter a number between 1 and 9.");
                scanner.next(); // Consume the invalid input
                continue;
            }

            int move = scanner.nextInt();

            // Check if input is valid and map it to row and column
            // Check for move between 1 and 9
            if (move < 1 || move > 9) {
                System.out.println("Invalid move, please enter a number between 1 and 9");
            } else {
                // Map row and columns
                int row = (move - 1) / 3;
                int col = (move - 1) % 3;

                // Check if cell is occupied
                if (board[row][col] != ' ') {
                    System.out.println("This cell is already occupied, please choose another one");
                } else {
                    // Place the move
                    board[row][col] = currentPlayer;

                    // Check if current player has won
                    if (checkWin()) {
                        displayBoard();
                        System.out.println("Player " + currentPlayer + " won!");
                        gameOver = true;
                    } else if (checkTie()) {
                        displayBoard();
                        System.out.println("Game is a tie!");
                        gameOver = true;
                    } else {
                        // Switch player turn
                        switchPlayer();
                    }
                }
            }
        }
    }

    // Switch player
    private static void switchPlayer() {
        currentPlayer = (currentPlayer == 'X' ? 'O' : 'X');
    }

    // Check if current player has won
    private static boolean checkWin() {
        // Check rows, columns and diagonals
        for (int i = 0; i < BOARD_SIZE; i++) {
            // Check rows
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
            // Check columns
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        // Check diagonals
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }
        return false;
    }

    // Check if the game is a tie
    private static boolean checkTie() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}



