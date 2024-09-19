package ui;

import util.TransformCoordenate;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class GameUI extends JPanel {
    static final String NAME = "GAME";
    static final String COURSES_LABEL_TEXT = "Chess";
    static JFrame frame = new JFrame();

    private static final int BOARD_SIZE = 8;

    ArrayList<JButton> listButtons = new ArrayList<>();

    public static void main(String[] args) {
        show(new GameUI());
    }

    private static void show(JPanel panel) {
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public GameUI() {
        setName(NAME);
        createLayout();
    }

    private void createLayout() {
        setLayout(new BorderLayout());

        final int pad = 6;
        Border emptyBorder = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titleBorder = BorderFactory.createTitledBorder(bevelBorder, COURSES_LABEL_TEXT);
        setBorder(BorderFactory.createCompoundBorder(emptyBorder, titleBorder));

        add(createChessBoard(), BorderLayout.CENTER);
    }

    private JPanel createChessBoard() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        boolean isWhite = true;

        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                JButton square = new JButton();
                square.setPreferredSize(new Dimension(80, 80));
                square.setName(TransformCoordenate.convertCoordinate(column, rank));
                square.setEnabled(true);
                listButtons.add(square);

                if (isWhite)
                    square.setBackground(Color.WHITE);
                else
                    square.setBackground(Color.BLACK);

                boardPanel.add(square);

                isWhite = !isWhite;
            }
            isWhite = !isWhite;
        }

        return boardPanel;
    }

    public JButton getButton(String name) {
        return (JButton) Util.getComponent(this, name);
    }

    public ArrayList<JButton> getAllBoardButtons() {
        return listButtons;
    }
}