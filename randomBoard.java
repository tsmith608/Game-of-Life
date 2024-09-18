import java.util.Random;
import java.util.Scanner;

public class randomBoard {
    public static void main(String[] args) {
        int width, height;
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Height: ");
        height = sc.nextInt();
        System.out.print("Width: ");
        width = sc.nextInt();
        int[][] board = randomState(height, width);
        render(board);
    }
    public static void deadState(int height, int width){
        Scanner sc = new Scanner(System.in);
        int[][] board = new int[height][width];
        for (int i = 0; i < height; i++) {
            System.out.println();
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");

            }
        }
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

    public static void render(int[][] board){
        printDashes(board[0].length);
        for (int i = 0; i < board.length; i++) {
            System.out.println();
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1)
                System.out.print("X");
                else if (board[i][j] == 0)
                    System.out.print(" ");
                if (j == board[i].length - 1)
                    System.out.print("|");

            }
        }
        System.out.println();
        printDashes(board[0].length);
    }
    private static void printDashes(int length) {
        for (int i = 0; i < length + 2; i++) {
            System.out.print("-"); // Print a dash
        }
    }

}
