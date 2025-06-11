import javax.swing.text.Position;
import java.util.*;

public class Board {
    int width;
    int height;
    char[][] board;
    Random random;
    char[] gems = {'*', '+', '#', '%'};
    Scanner scan = new Scanner(System.in);

    Board(int w, int h) {
        this.width = w;
        this.height = h;
        this.board = new char[w][h];
        random = new Random(20);
        initializeBoard();
    }

    //board for gems collection is initialized
    public void initializeBoard() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = gems[random.nextInt(gems.length)];
            }
        }
    }

    // toString method overrided to print board state
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] row : board) {
            for (char gem : row) {
                sb.append(gem).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    // console input fron user is handled
    public void handleInput() {
        System.out.println("v for vertical, h for horizontal movement of gems.");
        String direction = scan.nextLine();
        if (direction.equals("v") || direction.equals("h")) {
            prompt(direction);

        } else {
            System.out.println("not a valid direction for move, enter 'v' or 'h'");
            handleInput();
        }
    }

    // user prompt to receive gems to switch
    public void prompt(String D) {
        try {
            System.out.println("enter positions to swap,4 ints for grid position seperatred by spaces");
            String[] parts = scan.nextLine().split(" ");
            int sR = Integer.parseInt(parts[0]);
            int sC = Integer.parseInt(parts[1]);
            int eR = Integer.parseInt(parts[2]);
            int eC = Integer.parseInt(parts[3]);
            if (validMove(sR, sC, eR, eC, D)) {
                char temp = board[sR][sC];
                board[sR][sC] = board[eR][eC];
                board[eR][eC] = temp;
            } else {
                System.out.println("Not a valid move, please try again.");
                prompt(D);
            }
        } catch (Exception e) {
            System.out.println("Not a valid Input");
            prompt(D);
        }
    }

    //checking if the attempted move is valid for the selected direction
    public boolean validMove(int sR, int sC, int eR, int eC, String direction) {
        if (sR < 0 || sR >= height || sC < 0 || sC >= width ||
                eR < 0 || eR >= height || eC < 0 || eC >= width) {
            System.out.println("Coordinates out of bounds.");
            return false; //to avoid out of bounds.
        }
        if (direction.equals("v") && Math.abs(sR - eR) == 1 && (sC - eC) == 0) {
            return true;
        } else if (direction.equals("h") && Math.abs(sC - eC) == 1 && (sR - eR) == 0) {
            return true;
        }
        System.out.println("Not a valid move");
        return false;
    }

    public void checkMatch() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checkHorizontalMatch(i, j);
                checkVerticalMatch(i, j);
            }
        }
    }

    public void checkHorizontalMatch(int i, int j) {
        Set<String> positions = new HashSet<>();
        int count = 1;
        int maxCount = 1;
//        if (j + 2 < height && board[i][j] == board[i][j + 1] && board[i][j] == board[i][j + 2]) {
//            System.out.println("Horizontal match found at: (" + i + "," + j + ")");
//        }
        for (int col = j+1; col < width; col++) {
            if (board[i][col] == board[i][col-1]) {
                positions.add(i+","+(col-1));
                positions.add(i+","+(col));
                count++;
                maxCount = Math.max(maxCount, count);
//                for(String position: positions){
//                    System.out.println(position+"      debug");
//                }
            } else {
                if(maxCount>=3){
                    int startCol = col - maxCount;
                    System.out.println(maxCount + " Horizontal match found at: (" + i + "," + startCol + ")"+ board[i][startCol]);
                    for(String position: positions){
                        System.out.println(position);
                    }
                    remove(positions);
                }
                count = 1;
                maxCount = 1;
                positions.clear();
            }
        }

    }

    public void checkVerticalMatch(int i, int j) {
        Set<String> positions = new HashSet<>();
        int count = 1;
        int maxCount = 1;
//        if (i + 2 < width && board[i][j] == board[i + 1][j] && board[i][j] == board[i + 2][j]) {
//            System.out.println("Vertical match found at: (" + i + "," + j + ")");
//        }
        for (int row = i+1; row < width; row++) {
            if (board[row][j] == board[row-1][j]) {
                positions.add((row-1)+","+(j));
                positions.add((row)+","+(j));
                count++;
                maxCount = Math.max(maxCount, count);
            } else {
                if(maxCount>=3){
                    int startRow = row-maxCount;
                    System.out.println(maxCount + " Vertical match found at: (" + startRow + "," + j + ")"+ + board[startRow][j]);
                    for(String position: positions){
                        System.out.println(position);
                    }
                    remove(positions);
                }
                count = 1;
                maxCount = 1;
            }
        }
    }



    public void remove(Set<String> position) {
        int row;
        int col;
        for(String location: position){
            String[] parts = location.split(",");
            row = Integer.parseInt(parts[0]);
            col = Integer.parseInt(parts[1]);
            board[row][col] = ' ';
        }
    }

}