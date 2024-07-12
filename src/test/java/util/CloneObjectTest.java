package util;

import chess.*;
import pieces.*;
import junit.framework.TestCase;

import static util.CloneObject.shallowClone;
import static util.ObjectDumper.dumper;

public class CloneObjectTest extends TestCase {
    public void testCreate() throws Exception {
        Board board = new Board();
        board.initialize();
        Object cloneBoard = shallowClone(board);

        String boardDumper = dumper(board);
        String cloneBoardDumper = dumper(cloneBoard);
        assertEquals(boardDumper, cloneBoardDumper);


        Piece blackKing = King.create(Piece.Color.BLACK, board);
        Object clonePiece = shallowClone(blackKing);

        String stringPiece = dumper(blackKing);
        String stringClonePiece = dumper(clonePiece);
        assertEquals(stringPiece, stringClonePiece);


        ObjectDumper objectDumper = new ObjectDumper();
        Object cloneObjectDumper = shallowClone(objectDumper);

        String stringObjectDumper = dumper(objectDumper);
        String stringCloneObjectDumper = dumper(cloneObjectDumper);
        assertEquals(stringObjectDumper, stringCloneObjectDumper);
    }
}
