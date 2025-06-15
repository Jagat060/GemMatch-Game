import javax.swing.*;
import java.awt.*;

public class GemManagerGUI extends Board {
    JFrame frame = new JFrame("Gem Manager Game");
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JButton[][] buttons;

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
                //button.addActionListener(ActionListener);
            }
        }

        frame.add(panel1, BorderLayout.WEST);
        frame.add(panel2, BorderLayout.EAST);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void buttonDesign(int row, int col, JButton button) {
        int w = 80;
        int h = 80;
        ImageIcon Icon1 = new ImageIcon("gemstone_6057422.png");
        ImageIcon Icon2 = new ImageIcon("3132163.png");
        ImageIcon Icon3 = new ImageIcon("diamond_4743152.png");
        ImageIcon Icon4 = new ImageIcon("emerald_2504321.png");

        Image ICon1 = Icon1.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image ICon2 = Icon2.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image ICon3 = Icon3.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
        Image ICon4 = Icon4.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);

        ImageIcon icon1 = new ImageIcon(ICon1);
        ImageIcon icon2 = new ImageIcon(ICon2);
        ImageIcon icon3 = new ImageIcon(ICon3);
        ImageIcon icon4 = new ImageIcon(ICon4);
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
        checkMatch();
    }



}
