package chess.domain.position;

import chess.domain.piece.Color;
import chess.domain.piece.movable.Direction;
import chess.domain.util.WrongPositionException;

import java.util.Objects;

public class Position {
    private static final int MIN_BOUND = 1;
    private static final int MAX_BOUND = 8;
    private static final int BLACK_PAWN_INITIAL_COLUMN = 7;
    private static final int WHITE_PAWN_INITIAL_COLUMN = 2;
    private static final int ROW_START_INDEX = 0;
    private static final int COLUMN_START_INDEX = 1;

    private final Row row;
    private final Column column;

    //package-access
    Position(String position) {
        validate(position);
        this.row = Row.of(position.substring(ROW_START_INDEX, ROW_START_INDEX + 1));
        this.column = Column.of(position.substring(COLUMN_START_INDEX, COLUMN_START_INDEX + 1));
    }

    private void validate(String position) {
        Objects.requireNonNull(position, "NULL 값을 입력하셨습니다.");
        if (position.isEmpty()) {
            throw new WrongPositionException();
        }
    }

    public Position getMovedPositionBy(Direction direction) {
        if (!checkBound(direction)) {
            return this;
        }
        Row movedRow = row.calculate(direction.getXDegree());
        Column movedColumn = column.calculate(direction.getYDegree());
        return PositionFactory.of(movedRow, movedColumn);
    }

    public boolean checkBound(Direction direction) {
        int checkingRow = row.getValue() + direction.getXDegree();
        int checkingColumn = column.getValue() + direction.getYDegree();
        return isValidBound(checkingRow) && isValidBound(checkingColumn);
    }

    public boolean isPawnInitial(Color color) {
        if (column.getValue() == WHITE_PAWN_INITIAL_COLUMN && color.isWhite()) {
            return true;
        }
        return column.getValue() == BLACK_PAWN_INITIAL_COLUMN && color.isBlack();
    }

    public boolean isSameRow(Position position) {
        return this.row.equals(position.row);
    }

    public boolean isDifferentRow(Position position) {
        return !isSameRow(position);
    }

    private static boolean isValidBound(int value) {
        return value >= MIN_BOUND && value <= MAX_BOUND;
    }

    public Row getRow() {
        return row;
    }
}