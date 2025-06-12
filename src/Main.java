import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Board board = new Board(7, 7);
        System.out.println(board);
        while (true) {
            board.checkMatch();
            System.out.println(board);
            board.replace();
            board.checkMatch();
            board.replace();

            System.out.println(board);
            board.handleInput();
            System.out.println(board);
            board.checkMatch();
            System.out.println(board);

        }
    }
}
