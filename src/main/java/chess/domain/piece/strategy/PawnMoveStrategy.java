package chess.domain.piece.strategy;

import chess.domain.order.MoveRoute;
import chess.domain.piece.Color;
import chess.domain.position.Direction;
import chess.domain.position.Rank;

public class PawnMoveStrategy extends DefaultMoveStrategy {
    public static final int PAWN_MAXIMUM_MOVABLE_ROUTE_LENGTH = 3;

    private final Color color;

    public PawnMoveStrategy(Color color) {
        super(Direction.pawnDirection(color));
        this.color = color;
    }

    @Override
    public boolean canMove(MoveRoute moveRoute) {
        if (Direction.isDiagonal(moveRoute.getDirection())) {
            validateKillMove(moveRoute);
            return true;
        }

        if (moveRoute.length() > PAWN_MAXIMUM_MOVABLE_ROUTE_LENGTH) {
            throw new IllegalArgumentException("폰이 움직일 수 있는 범위를 벗어났습니다.");
        }

        if (moveRoute.length() == PAWN_MAXIMUM_MOVABLE_ROUTE_LENGTH) {
            validateFirstMove(moveRoute);
        }

        return super.canMove(moveRoute);
    }

    private void validateKillMove(MoveRoute moveRoute) {
        if (!moveRoute.hasPieceAtToPosition()) {
            throw new IllegalArgumentException("상대 말을 잡을 때에만 대각선으로 움직일 수 있습니다.");
        }
        if (moveRoute.getPieceAtToPosition().isSameColor(this.color)) {
            throw new IllegalArgumentException("아군 말이 있어 대각선으로 움직일 수 없습니다.");
        }
    }

    private void validateFirstMove(MoveRoute moveRoute) {
        if (this.color == Color.WHITE) {
            validateFirstMoveOfWhite(moveRoute);
            return;
        }
        validateFirstMoveOfBlack(moveRoute);
    }

    private void validateFirstMoveOfWhite(MoveRoute moveRoute) {
        if (!moveRoute.getFromPosition().isRankOf(Rank.TWO)) {
            throw new IllegalArgumentException("폰은 첫 행마가 아니라면 2칸 전진할 수 없습니다.");
        }
    }

    private void validateFirstMoveOfBlack(MoveRoute moveRoute) {
        if (!moveRoute.getFromPosition().isRankOf(Rank.SEVEN)) {
            throw new IllegalArgumentException("폰은 첫 행마가 아니라면 2칸 전진할 수 없습니다.");
        }
    }
}