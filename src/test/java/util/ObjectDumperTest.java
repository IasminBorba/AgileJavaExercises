package util;

import junit.framework.TestCase;
import pieces.*;
import chess.*;

import java.util.Arrays;

public class ObjectDumperTest extends TestCase {
    public void testClass(){
        Board board = new Board();
        board.initialize();
        String stringDumpBoard = objectDumper.dumper(board);


        assertEquals(stringBoard(board), stringDumpBoard);


        Piece piece = King.create(Piece.Color.WHITE, board);
        String stringDumpPiece = objectDumper.dumper(piece);
    }

    public String stringBoard(Board board){
        StringBuilder builder = new StringBuilder();

        builder.append("Class Board: ").append(board.toString()).append("\n");
        builder.append("Array Bidimensional board: ").append(Arrays.deepToString(board.board)).append("\n");
        builder.append("Integer piecesWhite: ").append(board.piecesWhite).append("\n");
        builder.append("Integer piecesBlack: ").append(board.piecesBlack).append("\n");
        builder.append("Array pieces: ").append(board.getPieces().toString()).append("\n");
        builder.append("StringBuilder piecesOnTheBoard: ").append(board.piecesOnTheBoard.toString()).append("\n");
        builder.append("String filename: ").append(board.filename).append("\n");
        builder.append("File filename: ").append(board.file.getPath()).append("\n");

        return builder.toString();
    }
}
