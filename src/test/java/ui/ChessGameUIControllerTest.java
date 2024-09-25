package ui;

import chess.*;
import junit.framework.TestCase;
import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ChessGameUIControllerTest extends TestCase {
    private ChessGameUIController gameController;
    private GameUI panel;
    private Board board;

    protected void setUp() throws AWTException {
        panel = new GameUI();
        board = new Board();
        gameController = new ChessGameUIController(panel, board);
    }

    public void testClicked() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        String position = "d5";
        gameController.addPieceToGame(queen, position);

        ArrayList<String> possibleMoves = queen.getPossibleMoves();

        JButton buttonClicked = panel.getBoardButtonByName(position);
        buttonClicked.doClick();

        for (String move : possibleMoves) {
            JButton button = panel.getBoardButtonByName(move);
            assertEquals(Color.GREEN, button.getBackground());
        }
    }

    public void testClickedNull() {
        String position = "d5";

        JButton buttonClicked = panel.getBoardButtonByName(position);
        assertTrue(Objects.equals(buttonClicked.getText(), ""));

        buttonClicked.doClick();

        ArrayList<JButton> allButtons = panel.getBoardButtons();
        for (JButton button : allButtons)
            assertFalse(button.getBackground() == Color.GREEN);
    }

    public void testHighlightPossibleMoves() {
        Piece rook = Rook.createPiece(Piece.Color.WHITE);
        String position = "d5";
        gameController.addPieceToGame(rook, position);

        ArrayList<String> possibleMoves = rook.getPossibleMoves();
        gameController.highlightPossibleMoves(possibleMoves);

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

    public void testAddPiece() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        String position = "d5";

        gameController.addPieceToGame(queen, position);

        JButton button = panel.getBoardButtonByName(position);

        assertEquals("QUEEN", button.getText());
        assertNotNull(button.getIcon());
        assertTrue(button.getIcon() instanceof ImageIcon);
    }

    public void testRemovePiece() {
        String position = "d1";
        JButton button = panel.getBoardButtonByName(position);
        Piece queen = board.getPiece(0, 3);

        gameController.removePieceToGame(queen);

        assertEquals("", button.getText());
        assertNull(button.getIcon());
    }
}
