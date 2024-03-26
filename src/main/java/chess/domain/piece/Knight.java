package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class Knight extends ChessPiece {

    public Knight(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new Knight(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
