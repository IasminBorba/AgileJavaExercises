package ui;

import chess.Position;
import util.CoordinateTransformer;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class GameUI extends JPanel {
    public static final String NAME = "GAME";
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 80;
    private static final Color LIGHT_SQUARE_COLOR = Color.WHITE;
    private static final Color DARK_SQUARE_COLOR = Color.BLACK;

    private static final JFrame frame = new JFrame("ChessGame");
    private final ArrayList<JButton> boardButtons = new ArrayList<>();

    public static void main(String[] args) {
        GameUI game = new GameUI();
        game.display();
    }

    public void display() {
        frame.setVisible(true);
    }

    public GameUI() {
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);

        setName(NAME);
        createLayout();
    }

    public void close() {
        frame.dispose();
    }

    private void createLayout() {
        setLayout(new BorderLayout());

        final int padding = 6;
        Border paddingBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
        Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        Border titleBorder = BorderFactory.createTitledBorder(bevelBorder, "Chess");
        setBorder(BorderFactory.createCompoundBorder(paddingBorder, titleBorder));

        add(createChessBoard(), BorderLayout.CENTER);
    }

    private JPanel createChessBoard() {
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE));

        applyColorsSquares(boardPanel);
        return boardPanel;
    }

    public void applyColorsSquares(JPanel boardPanel) {
        boolean isLightSquare = true;
        for (int rank = 0; rank < BOARD_SIZE; rank++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                JButton square = createBoardSquare(rank, column, isLightSquare);
                boardPanel.add(square);
                isLightSquare = !isLightSquare;
            }
            isLightSquare = !isLightSquare;
        }
    }

    private JButton createBoardSquare(int rank, int column, boolean isLightSquare) {
        JButton square = new JButton();
        square.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
        square.setName(CoordinateTransformer.positionToCoordinateString(column, rank));
        square.setEnabled(true);
        square.setBackground(isLightSquare ? LIGHT_SQUARE_COLOR : DARK_SQUARE_COLOR);
        boardButtons.add(square);
        return square;
    }

    public JButton getBoardButtonByName(String name) {
        return (JButton) ComponentSearch.findComponentByName(this, name);
    }

    public ArrayList<JButton> getBoardButtons() {
        return boardButtons;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void highlightBoardSquare(JButton button) {
        button.setBackground(Color.GREEN);
    }

    public void clearHighlights() {
        for (JButton button : getBoardButtons())
            if (button.getBackground() == Color.GREEN) {
                String coordinate = button.getName();
                button.setBackground(calculatePosition(coordinate));
            }
    }

    public Color calculatePosition(String coordinate) {
        Position position = CoordinateTransformer.stringToPosition(coordinate);
        int invertedRank = 7 - position.getRow();
        int rowOffsetInStringBuilder = (invertedRank * 9);

        int calculate = rowOffsetInStringBuilder + position.getFile();

        if (calculate % 2 == 0)
            return DARK_SQUARE_COLOR;
        return LIGHT_SQUARE_COLOR;
    }

    public void addIconToButtons(JButton button, ImageIcon icon) {
        button.setIcon(icon);
    }
}