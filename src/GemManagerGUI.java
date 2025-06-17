import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class GemManagerGUI extends Board {


    JFrame frame = new JFrame("Gem Manager Game");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JButton[][] buttons;
    int selectedRow = -1;
    int selectedCol = -1;

    GemManagerGUI(int w, int h) {
        super(w, h);
        buttons = new JButton[w][h];
        makeFrame();
    }

    public void makeFrame() {
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        panel1.setLayout(new GridLayout(height, width));
        panel1.setBackground(Color.MAGENTA);
        panel1.setPreferredSize(new Dimension(600, 800));
        panel2.setPreferredSize(new Dimension(200, 800));
        panel2.setBackground(Color.white);


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                JButton button = new JButton();
                //button.setOpaque(true);  //if needed background color in buttons.
                buttons[i][j] = button;
                buttonDesign(i, j, button);
                panel1.add(button);

                int I = i;
                int J = j;

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (selectedRow == -1 && selectedCol == -1) {
                            selectedRow = I;
                            selectedCol = J;
                            button.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                        } else {
                            // Attempt swap
                            if (validMove(selectedRow, selectedCol, I, J, getDirection(selectedRow, selectedCol, I, J))) {
                                swap(selectedRow, selectedCol, I, J);
                                checkMatch(); // check match after swap
                                updateGUI();
                            }
                            // Reset selection
                            buttons[selectedRow][selectedCol].setBorder(null);
                            selectedRow = -1;
                            selectedCol = -1;
                        }
                    }
                });

            }
        }
        checkMatch();
        frame.add(panel1, BorderLayout.WEST);
        frame.add(panel2, BorderLayout.EAST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void swap(int r1, int c1, int r2, int c2) {
        char temp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = temp;
    }

    public String getDirection(int r1, int c1, int r2, int c2) {
        if (Math.abs(r1 - r2) == 1 && c1 == c2) return "v";
        if (Math.abs(c1 - c2) == 1 && r1 == r2) return "h";
        return "";
    }

    public void buttonDesign(int row, int col, JButton button) {
        int w = 80;
        int h = 80;
        ImageIcon Icon1 = new ImageIcon("gemstone_6057422.png");
        ImageIcon Icon2 = new ImageIcon("3132163.png");
        ImageIcon Icon3 = new ImageIcon("diamond_4743152.png");
        ImageIcon Icon4 = new ImageIcon("emerald_2504321.png");
        ImageIcon Icon5 = new ImageIcon("tick.jpeg");

        Image ICon1 = Icon1.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image ICon2 = Icon2.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image ICon3 = Icon3.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image ICon4 = Icon4.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image ICon5 = Icon5.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

        ImageIcon icon1 = new ImageIcon(ICon1);
        ImageIcon icon2 = new ImageIcon(ICon2);
        ImageIcon icon3 = new ImageIcon(ICon3);
        ImageIcon icon4 = new ImageIcon(ICon4);
        ImageIcon icon5 = new ImageIcon(ICon5);
//        icon(row, col, button, icon1, icon2, icon3, icon4);

        if (board[row][col] == '*') {
            button.setIcon(icon1);
            button.setBackground(new Color(128, 0, 128));
        }
        if (board[row][col] == '+') {
            button.setIcon(icon2);
            button.setBackground(Color.RED);
        }
        if (board[row][col] == '#') {
            button.setIcon(icon3);
            button.setBackground(Color.yellow);
        }
        if (board[row][col] == '%') {
            button.setIcon(icon4);
            button.setBackground(Color.ORANGE);
        }
        if (board[row][col] == '-') {
            button.setIcon(icon5);
            button.setBackground(Color.ORANGE);
        }
    }

    public void updateGUI() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                buttonDesign(i, j, buttons[i][j]);
            }
        }
    }

    @Override
    public void checkMatch() {
        Set<String> positions = new HashSet<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                checkHorizontalMatch(i, j, positions);
                checkVerticalMatch(i, j, positions);
            }
        }

        if (!positions.isEmpty()) {
            remove(positions);
            updateGUI();  // Show removed matches as tick icons

            // Delay falling animation and continue matching after 1 second
            Timer timer = new Timer(3000, new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    replace();
                    updateGUI();

                    // Recursively check for new matches after replacement
                    checkMatch();  // Safe now because it’s in callback
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }


    public static void main(String[] args) {
        GemManagerGUI gui = new GemManagerGUI(7, 7);
    }
}
