package ui;

import chess.*;
import junit.framework.TestCase;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class ChessGameUIControllerTest extends TestCase {
    private ChessGameUIController game;
    private JFrame frame;
    private GameUI panel;

    protected void setUp() throws AWTException {
        game = new ChessGameUIController();
        frame = game.getFrame();
        panel = (GameUI) ComponentSearch.findComponentByName(frame, GameUI.NAME);
    }

    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(ChessGameUIController.HEIGHT, frame.getSize().getHeight(), tolerance);
        assertEquals(ChessGameUIController.WIDTH, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNotNull(ComponentSearch.findComponentByName(frame, GameUI.NAME));
        assertEquals("ChessGame", frame.getTitle());

        assertNotNull(panel);
    }

    protected void tearDown() {
        game.close();
    }

    public void testShow() {
        game.show();
        assertTrue(frame.isVisible());
    }

    public void testAddPiece() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        String position = "d5";

        game.addPieceToGame(queen, position);

        JButton button = panel.getBoardButtonByName(position);

        assertEquals("QUEEN", button.getText());
        assertNotNull(button.getIcon());
        assertTrue(button.getIcon() instanceof ImageIcon);
    }

    public void testRemovePiece() {
        Board board = game.getBoard();
        String position = "d1";
        JButton button = panel.getBoardButtonByName(position);
        Piece queen = board.getPiece(0, 3);

        game.removePieceToGame(queen);

        assertEquals("", button.getText());
        assertNull(button.getIcon());
    }

    public void testHighlightPossibleMoves() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        String position = "d5";
        game.addPieceToGame(queen, position);

        ArrayList<String> possibleMoves = queen.getPossibleMoves();
        game.highlightPossibleMoves(possibleMoves);

        for (String move : possibleMoves) {
            JButton button = panel.getBoardButtonByName(move);
            assertEquals(Color.GREEN, button.getBackground());
        }
    }

    public void testNotHighlightPossibleMoves() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        Position position = new Position(5, 5);
        queen.setPosition(position);

        ArrayList<String> possibleMoves = queen.getPossibleMoves();

        for (String move : possibleMoves) {
            JButton button = panel.getBoardButtonByName(move);
            assertFalse(button.getBackground() == Color.GREEN);
        }
    }

    public void testClicked() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        String position = "d5";
        game.addPieceToGame(queen, position);

        ArrayList<String> possibleMoves = queen.getPossibleMoves();

        JButton buttonClicked = panel.getBoardButtonByName(position);
        buttonClicked.doClick();

        for (String move : possibleMoves) {
            JButton button = panel.getBoardButtonByName(move);
            assertEquals(Color.GREEN, button.getBackground());
        }
    }

    public void testClickedNull() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        queen.setPosition(new Position(4, 3));
        ArrayList<String> possibleMoves = queen.getPossibleMoves();

        String position = "d5";

        JButton buttonClicked = panel.getBoardButtonByName(position);
        buttonClicked.doClick();

        for (String move : possibleMoves) {
            JButton button = panel.getBoardButtonByName(move);
            assertFalse(button.getBackground() == Color.GREEN);
        }
    }

    public void testGetPieceImage() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        ImageIcon icon = game.getPieceImage(queen);

        assertNotNull(icon);

        String expectedPath = "src/exercises/resources/queenWhite.png";
        File imgFile = new File(expectedPath);
        assertTrue(imgFile.exists());
    }

    public void testGetPieceImageInvalid() {
        Piece king = King.createPiece(Piece.Color.WHITE);

        try {
            ImageIcon icon = game.getPieceImage(king);
        } catch (RuntimeException e) {
            assertEquals("Can't read input file!", e.getMessage());
        }

        String expectedPath = "src/exercises/resources/kingWhite.png";
        File imgFile = new File(expectedPath);
        assertFalse(imgFile.exists());
    }
}
