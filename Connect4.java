import java.util.Scanner;

public class Connect4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int rows = 6;
        final int columns = 7;
        int[][] board = new int[rows][columns];

        boolean gameOn = true;

        int roundcount = 1;

        // gameon runs the game loop
        // odd number rounds are for red player
        // even number rounds are for yellow player
        while (gameOn) {
            int plates = 0;
            if (roundcount % 2 == 1) {
                plates = 1;
            } else if (roundcount % 2 == 0) {
                plates = 2;
            }

            if (roundcount % 2 == 1 && roundcount != 43) {
                display(board);
                System.out.println("Drop a red disk at column (0 - 6): ");
                int columnNum = scanner.nextInt();

                // loop if user input out of range number
                if (columnNum > board[0].length - 1 || columnNum < 0) {
                    System.out.println("Invalid Input! Try again.");
                    continue;
                } else {
                    // loop if the column is full
                    if (board[0][columnNum] != 0) {
                        System.out.println("The column is full! Please try again!");
                        continue;
                    } else
                        // place plates
                        for (int i = board.length - 1; i >= 0; i--) {
                            if (board[i][columnNum] == 0) {
                                board[i][columnNum] = plates;
                                if (checkResult(board, i, columnNum)) {
                                    gameOn = false;
                                    display(board);
                                    System.out.println("The red player won.");
                                }

                                break;
                            }

                        }
                    roundcount++;
                }

            } else if (roundcount % 2 == 0) {
                display(board);
                System.out.println("Drop a yellow disk at column (0 - 6): ");
                int columnNum = scanner.nextInt();
                // loop if user input out of range number
                if (columnNum > board[0].length - 1 || columnNum < 0) {
                    System.out.println("Invalid Input! Try again.");
                    continue;
                } else {
                    // loop if the column is full
                    if (board[0][columnNum] != 0) {
                        System.out.println("The column is full! Please try again!");
                        continue;
                    } else
                        for (int i = board.length - 1; i >= 0; i--) {
                            if (board[i][columnNum] == 0) {
                                board[i][columnNum] = plates;
                                if (checkResult(board, i, columnNum)) {
                                    gameOn = false;
                                    display(board);
                                    System.out.println("The yellow player won.");
                                }

                                break;
                            }
                        }
                    roundcount++;
                }
                // check for ties
            } else if (roundcount == 43) {
                gameOn = false;
                display(board);
                System.out.println("Tie!");

            }

        }
    }

    public static void display(int[][] board) {
        // display board
        for (int row = 0; row < board.length; row++) {
            System.out.print("|");
            for (int column = 0; column < board[row].length; column++) {
                char plates;
                if (board[row][column] == 1) {
                    plates = 'R';
                } else if (board[row][column] == 2) {
                    plates = 'Y';
                } else {
                    plates = ' ';
                }
                System.out.print(plates + "|");
            }
            System.out.println();
        }

        // dashes at bottom panel
        for (int i = 0; i <= board[0].length * 2; i++) {
            if (i < board[0].length * 2)
                System.out.print("-");
            if (i == board[0].length * 2) {
                System.out.println("-");
            }
        }
    }

    // if row, column, or diagonal are true, then player win, retun true boolean.
    public static boolean checkResult(int[][] board, int row, int column) {
        if (checkRow(board, row, column) == true || checkColumn(board, row, column) == true
                || checkDiagonal(board, row, column) == true)
            return true;
        else
            return false;
    }

    // check for rows
    public static boolean checkRow(int[][] board, int row, int column) {
        boolean win = false;
        for (int j = 0; j < board[0].length - 3; j++) {
            if (board[row][j] != 0 && board[row][j] == board[row][j + 1] && board[row][j + 1] == board[row][j + 2]
                    && board[row][j + 2] == board[row][j + 3]) {
                win = true;
            }
        }
        return win;
    }

    // check for columns
    public static boolean checkColumn(int[][] board, int row, int column) {
        boolean win = false;
        for (int j = 0; j < board.length - 3; j++) {
            if (board[j][column] != 0 && board[j][column] == board[j + 1][column]
                    && board[j + 1][column] == board[j + 2][column] && board[j + 2][column] == board[j + 3][column]) {
                win = true;
            }

        }
        return win;
    }

    // check for diagonal
    public static boolean checkDiagonal(int[][] board, int row, int column) {
        boolean win = false;
        int[][] startCoordinates = DiagonalCord(row, column);

        for (int i = startCoordinates[0][0]; i < 3; i++) {
            for (int j = startCoordinates[0][1]; j < 4; j++) {
                if (board[i][j] != 0 && board[i][j] == board[i + 1][j + 1] && board[i + 1][j + 1] == board[i + 2][j + 2]
                        && board[i + 2][j + 2] == board[i + 3][j + 3]) {
                    win = true;
                }
            }
        }

        for (int i = startCoordinates[1][0]; i < 3; i++) {
            for (int j = startCoordinates[1][1]; j > 2; j--) {
                if (board[i][j] != 0 && board[i][j] == board[i + 1][j - 1] && board[i + 1][j - 1] == board[i + 2][j - 2]
                        && board[i + 2][j - 2] == board[i + 3][j - 3]) {
                    win = true;
                }
            }
        }
        return win;
    }

    // get the starting diagonal cordinate, starting point.
    public static int[][] DiagonalCord(int row, int column) {
        // left cord
        int minus = Math.min(row, column);
        int[][] DiagonalCords = new int[2][2];
        DiagonalCords[0][0] = row - minus;
        DiagonalCords[0][1] = column - minus;

        // right cord
        int a = row;
        int b = column;
        minus = Math.min(a, (6 - b));
        DiagonalCords[1][0] = a - minus;
        DiagonalCords[1][1] = b + minus;

        return DiagonalCords;
    }

}
