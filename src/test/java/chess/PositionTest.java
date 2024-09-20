package chess;

import junit.framework.TestCase;

public class PositionTest extends TestCase {
    public void testCreatePosition() {
        Position position = new Position(1,1);

        assertEquals(1, position.getRow());
        assertEquals(1, position.getFile());
    }

    public void testSetRow() {
        Position position = new Position(2,2);
        position.setRow(9);

        assertEquals(9, position.getRow());
    }

    public void testSetFile() {
        Position position = new Position(68,70);
        position.setFile(99);

        assertEquals(99, position.getFile());
    }

    public void testToAlgebraicNotation() {
        Position position = new Position(1,1);
        assertEquals("b2", position.toAlgebraicNotation());
    }
}
