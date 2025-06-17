import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int row = 7;
        int col = row;

        Board boardLayout = new Board(row, col);
        //board.initializeBoard();
        //GemManagerGUI gui = new GemManagerGUI(row,col);
        //gui.makeFrame();
        System.out.println(boardLayout);
        while (true) {
            boardLayout.checkMatch();
            System.out.println(boardLayout);
            boardLayout.handleInput();
            System.out.println(boardLayout);
        }
    }
}
