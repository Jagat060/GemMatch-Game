import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int row = 7;
        int col = row;

        Board board = new Board(row, col);
        GemManagerGUI gui = new GemManagerGUI(row,col);
        System.out.println(board);
        while (true) {
            board.checkMatch();
            System.out.println(board);
            board.handleInput();
            System.out.println(board);
        }
    }
}
