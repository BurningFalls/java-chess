package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class BoardInitializer {

    private static final List<Piece> BASE_PIECES = new ArrayList<>();

    static {
        setUpWithOutPawn();
        setUpPawn();
    }

    private BoardInitializer() {
    }

    private static void setUpWithOutPawn() {
        setUpWithOutPawnByColor(Color.WHITE, Rank.ONE);
        setUpWithOutPawnByColor(Color.BLACK, Rank.EIGHT);
    }

    private static void setUpWithOutPawnByColor(Color color, Rank rank) {
        BASE_PIECES.add(new Rook(color, new Position(File.A, rank)));
        BASE_PIECES.add(new Knight(color, new Position(File.B, rank)));
        BASE_PIECES.add(new Bishop(color, new Position(File.C, rank)));
        BASE_PIECES.add(new Queen(color, new Position(File.D, rank)));
        BASE_PIECES.add(new King(color, new Position(File.E, rank)));
        BASE_PIECES.add(new Bishop(color, new Position(File.F, rank)));
        BASE_PIECES.add(new Knight(color, new Position(File.G, rank)));
        BASE_PIECES.add(new Rook(color, new Position(File.H, rank)));
    }

    private static void setUpPawn() {
        for (File file : File.orderedValues()) {
            BASE_PIECES.add(new Pawn(Color.WHITE, new Position(file, Rank.TWO)));
            BASE_PIECES.add(new Pawn(Color.BLACK, new Position(file, Rank.SEVEN)));
        }
    }

    public static List<Piece> init() {
        return new ArrayList<>(BASE_PIECES);
    }
}
