package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    private Position(int x, int y) {
        validateRange(x, y);
        this.x = x;
        this.y = y;
    }

    public static Position of(String position) {
        validatePositionForm(position);

        int x = position.charAt(0) - 'a' + 1;
        int y = position.charAt(1) - '1' + 1;

        return new Position(x, y);
    }

    public static void validatePositionForm(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("좌표는 두 글자 형식이어야 합니다.");
        }
        if (position.charAt(0) < 'a' || position.charAt(0) > 'z') {
            throw new IllegalArgumentException("좌표의 첫 번째 글자는 a~z 사이의 알파벳이어야 합니다.");
        }
        if (position.charAt(1) < '1' || position.charAt(1) > '8') {
            throw new IllegalArgumentException("좌표의 두 번째 글자는 1~8 사이의 숫자여야 합니다.");
        }
    }

    public void validateRange(int x, int y) {
        if (isNotInRange(x) || isNotInRange(y)) {
            throw new IllegalArgumentException("보드의 범위를 벗어난 좌표입니다.");
        }
    }

    private boolean isNotInRange(int coordinate) {
        return coordinate < 1 || coordinate > 8;
    }

    public PositionDifference calculateDifference(Position otherPosition) {
        int xDifference = this.x - otherPosition.x;
        int yDifference = this.y - otherPosition.y;

        return new PositionDifference(xDifference, yDifference);
    }

    public List<Position> getInternalPositions(Position otherPosition) {
        List<Position> internalPositions = new ArrayList<>();
        int deltaX = otherPosition.x - this.x;
        int deltaY = otherPosition.y - this.y;
        int max = Math.max(Math.abs(deltaX), Math.abs(deltaY));

        for (int step = 1; step < max; step++) {
            internalPositions.add(new Position(this.x + (deltaX / max) * step, this.y + (deltaY / max) * step));
        }

        return internalPositions;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
