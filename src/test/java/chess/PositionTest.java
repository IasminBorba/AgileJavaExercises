package chess;

import junit.framework.TestCase;

public class PositionTest extends TestCase {

    public void testSetFile() {
        Position position = new Position(1,1);
        position.setFile(2);

        assertEquals(2, position.getFile());
    }

    public void testToAlgebraicNotation() {
        Position position = new Position(1,1);
        assertEquals("b2", position.toAlgebraicNotation());
    }
}
