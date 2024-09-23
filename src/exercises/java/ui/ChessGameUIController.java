package ui;

import chess.*;
import pieces.Piece;
import util.CoordinateTransformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

import static util.CoordinateTransformer.*;

public class ChessGameUIController {
    private final GameUI panel = new GameUI();
    private final JFrame frame = new JFrame("ChessGame");
    private final Board board = new Board();

    public static void main(String[] args) {
        new ChessGameUIController().show();
    }

    ChessGameUIController() {
        initialize();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void initialize() {
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);

        board.initialize();
        createListeners();
    }

    private void createListeners() {
        createButtonListeners();
    }

    private void createButtonListeners() {
        addPieceIconsToButtons();

        ActionListener buttonListener = e -> {
            JButton clickedButton = (JButton) e.getSource();

            String coordinate = clickedButton.getName();
            if (coordinate == null)
                return;

            Position position = CoordinateTransformer.stringToPosition(coordinate);
            Piece piece = board.getPiece(position.getFile(), position.getRow());

            if (piece != null)
                highlightPossibleMoves(piece.getPossibleMoves());
        };

        for (JButton button : panel.getBoardButtons())
            button.addActionListener(buttonListener);
    }

    private void addPieceIconsToButtons() {
        Piece[][] boardCells = board.getBoardCells();

        board.iterateBoard((rank, column) -> {
            Piece piece = boardCells[column][rank];
            Position position = piece.getPosition();
            if (piece != null) {
                JButton button = panel.getBoardButtonByName(positionToCoordinateString(column, rank));
                if (piece.getType() == Piece.Type.QUEEN) {
                    button.setIcon(getPieceImage(piece));
                } else {
                    button.setText(piece.getType().name());
                }
            }
        });
    }

    public ImageIcon getPieceImage(Piece piece) {
        String path = getImagePathForPiece(piece);

        Image img;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            button.setBackground(Color.GREEN);
        }
    }
}
