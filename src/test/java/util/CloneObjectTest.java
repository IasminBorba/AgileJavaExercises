package util;

import chess.*;
import pieces.*;
import junit.framework.TestCase;

import static util.ObjectDumper.dumper;

public class CloneObjectTest extends TestCase {
    public void testCreate() throws IllegalAccessException {
        Board board = new Board();
        board.initialize();
        CloneObject cloneBoard = new CloneObject(board);

        String boardDumper = dumper(board);
        String cloneBoardDumper = dumper(cloneBoard);
        assertEquals(boardDumper, cloneBoardDumper);


        Piece blackKing = King.create(Piece.Color.BLACK, board);
        CloneObject clonePiece = new CloneObject(blackKing);

        String stringPiece = dumper(blackKing);
        String stringClonePiece = dumper(clonePiece);
        assertEquals(stringPiece, stringClonePiece);


        ObjectDumper objectDumper = new ObjectDumper();
        CloneObject cloneObjectDumper = new CloneObject(objectDumper);

        String stringObjectDumper = dumper(objectDumper);
        String stringCloneObjectDumper = dumper(cloneObjectDumper);
        assertEquals(stringObjectDumper, stringCloneObjectDumper);
    }
}
