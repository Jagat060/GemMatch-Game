import java.util.Random;
import java.util.Scanner;

public class Board {
    int width;
    int height;
    char [][] board;
    Random random = new Random();
    char [] gems = {'*','+','#'};
    Scanner scan = new Scanner(System.in);
    int sR, sC, eR, eC;
    char temp;
    String direction;

    Board(int w, int h){
        this.width  = w;
        this.height = h;
        this.board = new char[w][h];
        random = new Random(20);
        initializeBoard();
    }

    public void initializeBoard(){
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                board[i][j] = gems[random.nextInt(gems.length)];
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(char[] row: board){
            for(char gem: row){
                sb.append(gem).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void handleInput(){
        System.out.println("v for vertical, h for horizontal movement of gems.");
        direction = scan.nextLine();
        if(direction.equals("v") || direction.equals("h")){
            prompt();
            temp = board [sR][sC];
            board [sR][sC] = board [eR][eC];
            board [eR][eC] = temp;
        }
        else{
            System.out.println("not a valid direction for move, enter 'v' or 'h'");
            handleInput();
        }

    }
    public void prompt(){
        System.out.println("enter positions to swap,4 ints for grid position seperatred by commas");
        String[] parts = scan.nextLine().split(" ");
        sR = Integer.parseInt(parts[0]);
        sC = Integer.parseInt(parts[1]);
        eR = Integer.parseInt(parts[2]);
        eC = Integer.parseInt(parts[3]);
        validMove();

    }
    public boolean validMove(){
        if (direction.equals("v") && Math.abs(sR-eR) ==1 && (sC-eC) == 0){
            return true;
        }
        else if (direction.equals("h") && Math.abs(sC-eC) ==1 && (sR-eR) == 0){
            return true;
        }
        else{
            System.out.println("Not a valid move");
            prompt();
            return false;
        }
    }
}