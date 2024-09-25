package ui;

import chess.*;
import pieces.Piece;
import util.CoordinateTransformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import static util.CoordinateTransformer.*;

public class ChessGameUIController {
    private final GameUI panel;
    private final Board board;

    public static void main(String[] args) {
        Board board = new Board();
        board.initialize();

        ChessGameUIController game = new ChessGameUIController(new GameUI(), board);

        game.panel.display();
    }

    public ChessGameUIController(GameUI panel, Board board) {
        this.panel = panel;
        this.board = board;
        initialize();
    }

    private void initialize() {
        addPiecesToBoard();
        createListeners();
    }

    private void addPiecesToBoard() {
        Piece[][] boardCells = board.getBoardCells();
        board.iterateBoard((rank, column) -> {
            Piece piece = boardCells[rank][column];
            if (piece != null) {
                Position position = piece.getPosition();
                JButton button = panel.getBoardButtonByName(positionToCoordinateString(column, rank));
                if (piece.getType() == Piece.Type.QUEEN)
                    panel.addIconToButtons(button, getPieceImage(piece));
                button.setText(piece.getType().name());
            }
        });
    }

    private void createListeners() {
        createButtonListeners();
    }

    private void createButtonListeners() {
        ActionListener buttonListener = e -> {
            panel.clearHighlights();
            JButton clickedButton = (JButton) e.getSource();

            String coordinate = clickedButton.getName();
            Position position = CoordinateTransformer.stringToPosition(coordinate);
            Piece piece = board.getPiece(position.getRow(), position.getFile());

            if (piece != null)
                highlightPossibleMoves(piece.getPossibleMoves());
        };

        for (JButton button : panel.getBoardButtons())
            button.addActionListener(buttonListener);
    }

    public ImageIcon getPieceImage(Piece piece) {
        String path = getImagePathForPiece(piece);

        Image img;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException("Can't read input file!");
        }

        Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
    }

    public String getImagePathForPiece(Piece piece) {
        if (Objects.requireNonNull(piece.getType()) == Piece.Type.QUEEN) {
            return "src/exercises/resources/queenWhite.png";
        }
        return "";
    }

    public void highlightPossibleMoves(ArrayList<String> possibleMoves) {
        for (String coordinate : possibleMoves) {
            JButton button = panel.getBoardButtonByName(coordinate);
            panel.highlightBoardSquare(button);
        }
    }

    public void addPieceToGame(Piece piece, String position) {
        board.addPiece(piece, position);
        clearAndUpdateGame();
    }

    public void removePieceToGame(Piece piece) {
        board.removePieceFromTheBoard(piece);
        clearAndUpdateGame();
    }

    public void clearAndUpdateGame() {
        clearGame();
        addPiecesToBoard();
    }

    public void clearGame() {
        Piece[][] boardCells = board.getBoardCells();

        board.iterateBoard((rank, column) -> {
            JButton button = panel.getBoardButtonByName(positionToCoordinateString(column, rank));
            button.setText("");
            button.setIcon(null);
        });
    }
}
