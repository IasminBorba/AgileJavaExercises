package util;

import junit.framework.TestCase;
import pieces.*;
import chess.*;
import static util.objectDumper.dumper;

public class ObjectDumperTest extends TestCase {
    public void testClass() throws IllegalAccessException{
        Board board = new Board();
//        board.initialize();
//        String stringDumpBoard = dumper(board);
//        assertEquals(stringBoard(board), stringDumpBoard);

        Piece piece = King.create(Piece.Color.WHITE, board);
        String stringDumpPiece = dumper(piece);

//System.out.println(stringPiece(piece));
        assertEquals(stringPiece(piece), stringDumpPiece);
    }

    public String stringPiece(Piece piece){
        StringBuilder builder = new StringBuilder();

        builder.append("Class ").append(piece.getClass().getSimpleName()).append("\n\n");
        builder.append("[PRIVATE, FINAL] Color - color: White\n");
        builder.append("[PRIVATE, FINAL] Type - type: King\n");
        builder.append("[PRIVATE] double - points: 0\n");
        builder.append("[PRIVATE, FINAL] char - representation: k\n");
        builder.append("[PROTECTED] int - column: 5\n");
        builder.append("[PROTECTED] int - rank: 5;\n");

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
        builder.append("[Public] Two-dimensional array of Piece - board: \n").append(
                        "\t[[lakj: [Private] Color - color: White\n" +
                        "\t[Private] Type - type: King\n" +
                        "\t[Private] double - points: 0\n" +
                        "\t[Private] char - representation: k\n" +
                        "\t[Protected] int - column: 5\n" +
                        "\t[Protected] int - rank: 5;\n" +
                        "\tnull], [null, asdasd]]").append("\n");
        builder.append("[Public] int - piecesWhite: ").append("16\n");
        builder.append("[Public] int - piecesBlack: ").append("16\n");
        builder.append("[Protected] ArrayList of Piece - pieces: zn").append(
                        "\t[lakj: [Private] Color - color: White\n" +
                        "\t[Private] Type - type: King\n" +
                        "\t[Private] double - points: 0\n" +
                        "\t[Private] char - representation: k\n" +
                        "\t[Protected] int - column: 5\n" +
                        "\t[Protected] int - rank: 5;\n" +
                        "\tsasdsa]\n");
        builder.append("[Public] StringBuilder - piecesOnTheBoard: ").append("\n").append(
                        "\tRNBQKBNR\n" +
                        "\tPPPPPPPP\n" +
                        "\t........\n" +
                        "\t........\n" +
                        "\t........\n" +
                        "\t........\n" +
                        "\tpppppppp\n" +
                        "\trnbqkbnr\n");
        builder.append("[Public] String - filename: ").append(board.filename).append("\n");
        builder.append("[Public] File - file: ").append(board.file).append("\n");

        return builder.toString();
    }
}
