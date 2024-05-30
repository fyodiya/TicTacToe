import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) {

        //an array for board
        char[][] gameBoard = new char[3][3];
        //fill the board with '-'
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                gameBoard[i][j] = '-';
            }
        }

        //asking the names of 2 players
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player X, enter your name: ");
        String playerX = scanner.nextLine();
        System.out.println("Player O, enter your name: ");
        String playerO = scanner.nextLine();

        //whose turn it is?
        //counter, even - for X and odd - for O
        int turnCounter = 0;

        boolean gameIsWon = false;
        boolean isDraw = false;

        //loop for the turns
        while (!gameIsWon && !isDraw) {

            String currentPlayerName;
            char currentPlayer;

            if (turnCounter % 2 == 0) {
                currentPlayer = 'X';
                currentPlayerName = playerX;
            } else {
                currentPlayer = 'O';
                currentPlayerName = playerO;
            }

            System.out.println(currentPlayerName + ", make your move!");

            //turns - players are asked to choose row and column
            printBoard(gameBoard);
            int row, col;
            while (true) {
                System.out.print("Enter row (1-3): ");
                row = scanner.nextInt() - 1;
                System.out.print("Enter column (1-3): ");
                col = scanner.nextInt() - 1;

                //validity of the move - checking the slot emptiness "-"
                if (row >= 0 && row < 3 && col >= 0 && col < 3 && gameBoard[row][col] == '-') {
                    gameBoard[row][col] = currentPlayer;
                    break;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            }

            //turnCounter after a valid move
            turnCounter++;
            printBoard(gameBoard);

            gameIsWon = checkWin(gameBoard, currentPlayer);
            isDraw = isBoardFull(gameBoard);

            //end conditions
            if (gameIsWon) {
                printBoard(gameBoard);
                System.out.println("Congratulations, " + currentPlayerName + "! You have won the game!");
            } else if (isDraw) {
                printBoard(gameBoard);
                System.out.println("The game is a draw!");
            }
        }

        scanner.close();
    }

    /*
    printing the board
     */
    public static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
    /*
    checking if the winning combo is on the board
     */
    public static boolean checkWin(char[][] board, char player) {
        //rows and columns filled with the same symbol
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        //diagonals
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true;
        }
        return false;
    }

    /*
    checking if there are empty slots left on the board
     */
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}