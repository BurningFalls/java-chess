package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class Bishop extends ChessPiece {

    public Bishop(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new Bishop(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
