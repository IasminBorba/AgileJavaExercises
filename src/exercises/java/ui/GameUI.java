package ui;

import util.CoordinateTransformer;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class GameUI extends JPanel {
    private static final int BOARD_SIZE = 8;

    private static final JFrame frame = new JFrame();
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
        setName("GAME");
        createLayout();
    }

    private void createLayout() {
        setLayout(new BorderLayout());

        final int pad = 6;
        Border emptyBorder = BorderFactory.createEmptyBorder(pad, pad, pad, pad);
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titleBorder = BorderFactory.createTitledBorder(bevelBorder, "Chess");
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
                square.setName(CoordinateTransformer.positionToCoordinateString(column, rank));
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