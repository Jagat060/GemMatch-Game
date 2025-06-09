import java.util.ArrayList;
import java.util.Random;

public class Board {
    int noRow;
    int noCol;
    char[][] board;
    char[] gems = {'*', '+', '#'};
    Random random = new Random(20);

    Board(int noRow, int noCol){
        this.noRow = noRow;
        this.noCol = noCol;
        board = new char[noRow][noCol];
        boardInitialize();
    }

    public void boardInitialize(){
        for(int i=0; i<noRow; i++){
            for(int j=0; j<noCol; j++){
                board[i][j] = gems[random.nextInt(gems.length)];
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(char[] row:board){
            for(char gem: row){
                sb.append(gem).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}
