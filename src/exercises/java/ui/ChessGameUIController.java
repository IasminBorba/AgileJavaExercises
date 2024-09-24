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
    private final GameUI panel = new GameUI();
    private final JFrame frame = new JFrame("ChessGame");
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
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

    void close() {
        frame.dispose();
    }

    private void initialize() {
        frame.setSize(WIDTH, HEIGHT);
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
            Position position = CoordinateTransformer.stringToPosition(coordinate);
            Piece piece = board.getPiece(position.getRow(), position.getFile());

            if (piece != null)
                highlightPossibleMoves(piece.getPossibleMoves());
        };

        for (JButton button : panel.getBoardButtons())
            button.addActionListener(buttonListener);
    }

    private void addPieceIconsToButtons() {
        Piece[][] boardCells = board.getBoardCells();

        board.iterateBoard((rank, column) -> {
            Piece piece = boardCells[rank][column];
            if (piece != null) {
                Position position = piece.getPosition();
                JButton button = panel.getBoardButtonByName(positionToCoordinateString(column, rank));
                if (piece.getType() == Piece.Type.QUEEN) {
                    button.setIcon(getPieceImage(piece));
                    button.setText(piece.getType().name());
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
            throw new RuntimeException("Can't read input file!");
        }

        Image scaledImg = img.getScaledInstance(WIDTH / 10, HEIGHT / 10, Image.SCALE_SMOOTH);
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

    public JFrame getFrame() {
        return frame;
    }

    public Board getBoard() {
        return board;
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
        addPieceIconsToButtons();
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
