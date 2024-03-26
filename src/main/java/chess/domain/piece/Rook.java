package chess.domain.piece;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.strategy.MoveStrategy;

public class Rook extends ChessPiece {

    public Rook(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new Rook(newPieceInfo, moveStrategy);
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }
}
