package chess;

import junit.framework.TestCase;

public class PositionTest extends TestCase {

    public void testSetColumn() {
        Position position = new Position(1,1);
        position.setColumn(2);

        assertEquals(2, position.getColumn());
    }

    public void testGetPositionRepresentation() {
        Position position = new Position(1,1);
        assertEquals("b2", position.getPositionRepresentation());
    }
}
