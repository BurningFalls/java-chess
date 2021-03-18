package chess.domain.piece.condition;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public abstract class MoveCondition {
    public abstract boolean isSatisfyBy(Board board, Piece source, Position target);

    protected boolean validateChessPieceOutOfBoard(Board board, Position target) {
        return 0 <= target.getRow() && target.getRow() < board.getRow() &&
                0 <= target.getColumn() && target.getColumn() < board.getColumn();
    }
}
