package chess.domain.piece;

import static chess.domain.pieceinfo.PieceScore.PAWN_SCORE;

import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.domain.strategy.BlackPawnNotFirstMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.WhitePawnNotFirstMoveStrategy;

public class Pawn extends ChessPiece {
    private static final WhitePawnNotFirstMoveStrategy whitePawnNotFirstMoveStrategy = new WhitePawnNotFirstMoveStrategy();
    private static final BlackPawnNotFirstMoveStrategy blackPawnNotFirstMoveStrategy = new BlackPawnNotFirstMoveStrategy();

    public Pawn(PieceInfo pieceInfo, MoveStrategy moveStrategy) {
        super(pieceInfo, moveStrategy);
    }

    @Override
    public boolean isMoveInvalid(Position newPosition, boolean isDisturbed, boolean isOtherPieceExist,
                                 boolean isSameTeamExist) {
        Position currentPosition = pieceInfo.getPosition();
        int diffX = Math.abs(currentPosition.getFileIndex() - newPosition.getFileIndex());

        boolean isInvalidVerticalMove = (diffX == 0) && isOtherPieceExist;
        boolean isInvalidDiagonalMove = (diffX == 1) && (!isOtherPieceExist || isSameTeamExist);

        if (!moveStrategy.canMove(currentPosition, newPosition)) {
            return true;
        }
        return isDisturbed || isInvalidVerticalMove || isInvalidDiagonalMove;
    }

    @Override
    public ChessPiece createNewPiece(PieceInfo newPieceInfo) {
        return new Pawn(newPieceInfo, changeMovedStrategy());
    }

    private MoveStrategy changeMovedStrategy() {
        if (pieceInfo.getTeam() == Team.WHITE) {
            return whitePawnNotFirstMoveStrategy;
        }
        return blackPawnNotFirstMoveStrategy;
    }

    @Override
    public PieceType getType() {
        Position position = pieceInfo.getPosition();
        Team team = pieceInfo.getTeam();
        if (team == Team.BLACK && position.isFileTwoOrSeven()) {
            return PieceType.BLACK_PAWN_FIRST_MOVE;
        } else if (team == Team.BLACK && !position.isFileTwoOrSeven()) {
            return PieceType.BLACK_PAWN_NOT_FIRST_MOVE;
        } else if (team == Team.WHITE && position.isFileTwoOrSeven()) {
            return PieceType.WHITE_PAWN_FIRST_MOVE;
        } else if (team == Team.WHITE && !position.isFileTwoOrSeven()) {
            return PieceType.BLACK_PAWN_NOT_FIRST_MOVE;
        }
        return null;
    }

    @Override
    public double getScore() {
        return PAWN_SCORE.get();
    }
}
