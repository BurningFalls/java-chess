package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
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
    public boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                                 boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return true;
        }
        if (isDisturbed || isSameTeamExist) {
            return true;
        }
        return false;
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }
}
