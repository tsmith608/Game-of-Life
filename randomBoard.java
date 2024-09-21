import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class randomBoard {
    public static void main(String[] args) throws InterruptedException {
        int width, height;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        //unitTest();
        System.out.print("Height: ");
        height = sc.nextInt();
        System.out.print("Width: ");
        width = sc.nextInt();
        int[][] board = randomState(height, width);
       // int[][] deadBoard = deadState(height,width);

        while (true) {
            ConsoleClearer.clearConsole();
            render(board);
            nextBoard(board);
            try {
                Thread.sleep(500); // Adjust this value to control speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
    }
    public static int[][] deadState(int height, int width){
        Scanner sc = new Scanner(System.in);
        int[][] deadBoard = new int[height][width];
        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.print(deadBoard[i][j] + " ");

            }
        }
        return deadBoard;
    }

    public static int[][] randomState (int height, int width){
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double value = new Random().nextDouble();
                if (value < 0.5) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }

            }
        }
        return board;
    }

    public static void render(int[][] board) {
        StringBuilder sb = new StringBuilder();
        sb.append("\033[H"); // Move cursor to top-left corner

        for (int i = 0; i < board.length; i++) {
            sb.append("|");
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j] == 1 ? "o" : " ");
            }
            sb.append("|\n");
        }

        System.out.print(sb);
        System.out.flush();
    }
    public static void nextBoard(int[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int neighbors = 0;
                int rows = board.length;
                int cols = board[0].length;

                //check above
                if (i - 1 >= 0 && j - 1 >= 0 && board[i-1][j-1] == 1)
                    neighbors++;
                if (i - 1 >= 0 && board[i-1][j] == 1)
                    neighbors++;
                if (i - 1 >= 0 && j + 1 < cols && board[i-1][j+1] == 1)
                    neighbors++;

                //check same row
                if (j - 1 >= 0 && board[i][j-1] == 1)
                    neighbors++;
                if (j + 1 < cols && board[i][j+1] == 1)
                    neighbors++;

                //check below
                if (i + 1 < rows && j - 1 >= 0 &&  board[i+1][j-1] == 1)
                    neighbors++;
                if (i + 1 < rows && board[i+1][j] == 1)
                    neighbors++;
                if (i + 1 < rows && j + 1 < cols && board[i+1][j+1] == 1)
                    neighbors++;


                if (board[i][j] == 1)
                    switch (neighbors){
                    case 0:
                        board[i][j] = 0;
                        break;
                    case 1:
                        board[i][j] = 0;
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        board[i][j] = 0;
                        break;
                    default:
                        break;
                    }

                if (board[i][j] == 0 && neighbors == 3)
                    board[i][j] = 1;

            }
        }
    }
    private static void printDashes(int length) {
        for (int i = 0; i < length + 2; i++) {
            System.out.print("-"); // Print a dash
        }
    }
    private static void unitTest(){
        Scanner sc = new Scanner(System.in);
        int[][] deadBoard = new int[3][3];
        System.out.println("Dead Cells should stay dead \nStarting State: ");
        render(deadBoard);

        System.out.println("expected next state: ");
        render(deadBoard);

        System.out.println("Generated next state: ");
        nextBoard(deadBoard);
        render(deadBoard);


        System.out.println("Cells with three neighbors should come alive");
        int[][] threeBoard = new int[3][3];
        threeBoard[0] = new int[]{0, 1, 0};
        threeBoard[1] = new int[]{0, 1, 1};
        threeBoard[2] = new int[]{0, 0, 0};
        render(threeBoard);

        System.out.println("expected next state: ");
        int[][] threeBoardTest = new int[3][3];
        threeBoardTest[0] = new int[]{0, 1, 1};
        threeBoardTest[1] = new int[]{0, 1, 1};
        threeBoardTest[2] = new int[]{0, 0, 0};
        render(threeBoardTest);

        System.out.println("Generated next state: ");
        nextBoard(threeBoard);
        render(threeBoard);

        if (Arrays.deepEquals(threeBoard, threeBoardTest))
            System.out.println("Test Passed");
        else System.out.println("Test Failed");

        System.out.println("Cells with more than 3 live neighbors becomes dead");
        int[][] fourBoard = new int[3][3];
        fourBoard[0] = new int[]{0, 0, 1};
        fourBoard[1] = new int[]{1, 1, 1};
        fourBoard[2] = new int[]{1, 0, 0};
        render(fourBoard);

        System.out.println("expected next state: ");
        int[][] fourBoardTest = new int[3][3];
        fourBoardTest[0] = new int[]{0, 0, 1};
        fourBoardTest[1] = new int[]{1, 0, 0};
        fourBoardTest[2] = new int[]{0, 0, 0};
        render(fourBoardTest);

        System.out.println("Generated next state: ");
        nextBoard(fourBoard);
        render(fourBoard);

        if (Arrays.deepEquals(fourBoard, fourBoardTest))
            System.out.println("Test Passed");
        else System.out.println("Test Failed");



    }

    public class ConsoleClearer {
        public static void clearConsole() {
            try {
                final String os = System.getProperty("os.name");
                if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    Runtime.getRuntime().exec("clear");
                }
            } catch (final Exception e) {
                // If the above fails, fall back to a simple print method
                for (int i = 0; i < 50; ++i) System.out.println();
            }
            // Move cursor to top-left corner after clearing
            System.out.print("\033[H");
            System.out.flush();
        }
    }
}



