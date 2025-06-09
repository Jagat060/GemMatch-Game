public class Main {
    public static void main(String[] args) {
        run();
//        while (!gameOver()) {
//            handleInput();
//        }
    }
    public static void run() {
        Board board = new Board(4,4);
        System.out.println(board);
    }
}