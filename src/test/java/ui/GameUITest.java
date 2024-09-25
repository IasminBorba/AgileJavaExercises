package ui;

import junit.framework.TestCase;
import pieces.*;
import ui.helpers.ComponentSearch;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;


public class GameUITest extends TestCase {
    private GameUI panel;
    private JFrame frame;

    protected void setUp() throws AWTException {
        panel = new GameUI();
        frame = panel.getFrame();
    }

    public void testCreate() {
        final double tolerance = 0.05;
        assertEquals(800, frame.getSize().getHeight(), tolerance);
        assertEquals(800, frame.getSize().getWidth(), tolerance);
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertNotNull(ComponentSearch.findComponentByName(frame, GameUI.NAME));
        assertEquals("ChessGame", frame.getTitle());

        assertNotNull(panel);
    }

    public void testDisplay() {
        panel.display();
        assertTrue(frame.isVisible());
    }

    protected void tearDown() {
        panel.close();
    }

    public void testLayoutCreation() {
        assertTrue(panel.getLayout() instanceof BorderLayout);

        Component chessBoard = panel.getComponent(0);
        assertNotNull(chessBoard);
        assertTrue(chessBoard instanceof JPanel);

        Border border = panel.getBorder();
        assertNotNull(border);
        assertTrue(border instanceof CompoundBorder);

        if (border instanceof CompoundBorder compoundBorder) {
            Border insideBorder = compoundBorder.getInsideBorder();

            TitledBorder titledBorder = (TitledBorder) insideBorder;
            assertEquals("Chess", titledBorder.getTitle());
        }
    }

    public void testClearHighlights() {
        JButton buttonGreen = panel.getBoardButtonByName("a1");
        buttonGreen.setBackground(Color.GREEN);

        panel.clearHighlights();
        for (JButton button : panel.getBoardButtons())
            assertTrue(button.getBackground() != Color.GREEN);
    }

    public void testAddIconToButtons() {
        Piece queen = Queen.createPiece(Piece.Color.WHITE);
        Image image = queen.getImage();
        assertNotNull(image);

        JButton button = panel.getBoardButtonByName("d5");
        panel.addIconToButtons(button, image);

        assertNotNull(button.getIcon());
    }
}
