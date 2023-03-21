package domain.piece;

import domain.Section;
import domain.type.Color;
import domain.type.PieceType;

public class Queen extends SlidingPiece {

    private Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    public static Queen makeBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen makeWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Section start, final Section end) {
        return (start.isNotSameLine(end)
            && start.isNotDiagonal(end))
            || start.isSameColor(end);
    }
}