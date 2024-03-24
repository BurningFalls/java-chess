package chess.domain.piece;

import chess.domain.PieceInfo;
import chess.domain.Position;
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
    public boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                                 boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return true;
        }
        if (isSameTeamExist) {
            return true;
        }
        return false;
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
