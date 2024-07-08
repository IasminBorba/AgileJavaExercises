package util;

import junit.framework.TestCase;
import pieces.*;
import chess.*;
import static util.ObjectDumper.dumper;

public class ObjectDumperTest extends TestCase {
    public void testClass() throws IllegalAccessException{
        Board board = new Board();
        board.createBoard();
        Piece blackKing = King.create(Piece.Color.BLACK, board);
        board.addPiece(blackKing, 'b', 6);
        Piece blackRook = Rook.create(Piece.Color.BLACK, board);
        board.addPiece(blackRook, 'b', 5);
        Piece whiteKing = King.create(Piece.Color.WHITE, board);
        board.addPiece(whiteKing, 'a', 1);

        String stringDumpBoard = dumper(board);
        assertEquals(stringBoard(board), stringDumpBoard);

        String stringDumpPiece = dumper(whiteKing);
        assertEquals(stringPiece(whiteKing), stringDumpPiece);
    }

    public String stringPiece(Piece piece){
        StringBuilder builder = new StringBuilder();

        builder.append("Class ").append(piece.getClass().getSimpleName()).append("\n\n");
        builder.append("[PRIVATE, FINAL] Color - color: WHITE\n");
        builder.append("[PRIVATE, FINAL] Type - type: KING\n");
        builder.append("[PRIVATE] double - points: 0.0\n");
        builder.append("[PRIVATE, FINAL] char - representation: k\n");
        builder.append("[PROTECTED] int - column: 0\n");
        builder.append("[PROTECTED] int - rank: 1");

        return builder.toString();
    }

    public String stringBoard(Board board){
        //Listar cada nome de campo do objeto: Para cada objeto fornecido, você precisará listar todos os nomes dos campos que ele possui.
        //Exibir os valores atuais desses campos: Além de listar os nomes dos campos, você também deve exibir os valores que esses campos têm no momento.
        //Fazer isso de forma recursiva: Se algum dos campos do objeto for ele próprio um objeto, você precisará também listar os campos e valores desse objeto interno, e assim por diante, criando uma saída hierárquica.
        //Evitar classes de certos pacotes: Não percorra ou liste campos de classes que fazem parte dos pacotes java ou javax.
        //Exibir campos privados: A utilidade deve ser capaz de acessar e exibir campos privados do objeto.
        //Marcar campos estáticos: Campos estáticos devem ser identificados como tais na saída.
        //Ignorar campos de superclasses: Para simplificar, você não precisa se preocupar com os campos herdados de superclasses.

        StringBuilder builder = new StringBuilder();

        builder.append("Class ").append(board.getClass().getSimpleName()).append("\n\n");
        builder.append("[PUBLIC] Two-dimensional array of Piece - board: \n").append("Class Piece[][]\n\n");

//                        "\t[[lakj: [PRIVATE] Color - color: White\n" +
//                        "\t[PRIVATE] Type - type: King\n" +
//                        "\t[PRIVATE] double - points: 0\n" +
//                        "\t[PRIVATE] char - representation: k\n" +
//                        "\t[PROTECTED] int - column: 5\n" +
//                        "\t[PROTECTED] int - rank: 5;\n" +
//                        "\tnull], [null, asdasd]]").append("\n");
        builder.append("[PUBLIC] int - piecesWhite: ").append("1\n");
        builder.append("[PUBLIC] int - piecesBlack: ").append("2\n");
        builder.append("[PROTECTED] ArrayList of Piece - pieces: \n").append(
                        "Class King\n\n" +
                        "[PRIVATE, FINAL] Board - board: \n" +
                        "Circular reference detected\n\n" +
                        "[PRIVATE, FINAL] Color - color: \n" +
                        "Class Color\n\n" +
                        "[PRIVATE, FINAL] Type - type: \n" +
                        "Class Type\n\n" +
                        "[PRIVATE, FINAL] double - points: 0.0\n" +
                        "[PRIVATE, FINAL] char - representation: K\n" +
                        "[PRIVATE] double - points: 0.0\n" +
                        "[PRIVATE, FINAL] char - representation: K\n" +
                        "[PROTECTED] int - column: 1\n" +
                        "[PROTECTED] int - rank: 6\n" +
                        "Class Rook\n\n" +
                        "[PRIVATE, FINAL] Board - board: \n" +
                        "Circular reference detected\n\n" +
                        "[PRIVATE, FINAL] Color - color: \n" +
                        "Circular reference detected\n\n" +
                        "[PRIVATE, FINAL] Type - type: \n" +
                        "Class Type\n\n" +
                        "[PRIVATE, FINAL] double - points: 5.0\n" +
                        "[PRIVATE, FINAL] char - representation: R\n" +
                        "[PRIVATE] double - points: 5.0\n" +
                        "[PRIVATE, FINAL] char - representation: R\n" +
                        "[PROTECTED] int - column: 1\n" +
                        "[PROTECTED] int - rank: 5\n" +
                        "Class King\n\n" +
                        "[PRIVATE, FINAL] Board - board: \n" +
                        "Circular reference detected\n\n" +
                        "[PRIVATE, FINAL] Color - color: \n" +
                        "Class Color\n\n" +
                        "[PRIVATE, FINAL] Type - type: \n" +
                        "Circular reference detected\n\n" +
                        "[PRIVATE] double - points: 0.0\n" +
                        "[PRIVATE, FINAL] char - representation: k\n" +
                        "[PROTECTED] int - column: 0\n" +
                        "[PROTECTED] int - rank: 1\n\n");
        builder.append("[PUBLIC] StringBuilder - piecesOnTheBoard: ").append("\n").append(
                        "\t........\n" +
                        "\t........\n" +
                        "\t.K......\n" +
                        "\t.R......\n" +
                        "\t........\n" +
                        "\t........\n" +
                        "\t........\n" +
                        "\tk.......\n");
        builder.append("[PUBLIC] String - filename: ").append(board.filename).append("\n");
        builder.append("[PUBLIC] File - file: ").append(board.file);

        return builder.toString();
    }
}
