package chess.domain;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.List;
import java.util.Map;

public class Board {
    private static final MoveStrategy EMPTY_MOVE_STRATEGY = new EmptyMoveStrategy();

    private final Map<Position, Piece> board;

    public Board() {
        this.board = BoardInitializer.initialize();
    }

    public void placePiece(Position currentPosition, Piece piece) {
        board.put(currentPosition, piece);
    }

    public void movePiece(Command command, Team turn) {
        Position source = command.getSource();
        Position target = command.getTarget();
        validatePieceExist(source);
        validateMyTurn(source, turn);

        Piece piece = board.get(source);
        Piece movedPiece = movePiece(piece, source, target);
        validateMoveSuccess(piece, movedPiece);

        renewBoard(movedPiece, source, target);
    }

    public boolean isPieceExistInPosition(Position position) {
        Piece piece = board.get(position);

        return piece.getType() != PieceType.EMPTY;
    }

    public boolean isPieceFromSameTeam(Position position, Team team) {
        Piece piece = board.get(position);

        return piece.isSameTeam(team);
    }

    private void validatePieceExist(Position position) {
        if (!isPieceExistInPosition(position)) {
            throw new IllegalArgumentException("출발지에 체스 말이 없습니다.");
        }
    }

    private void validateMyTurn(Position position, Team turn) {
        if (!isPieceFromSameTeam(position, turn)) {
            throw new IllegalArgumentException("상대 턴입니다.");
        }
    }

    private void validateMoveSuccess(Piece piece, Piece movedPiece) {
        if (piece.equals(movedPiece)) {
            throw new IllegalArgumentException("목적지로 체스 말을 이동시킬 수 없습니다.");
        }
    }

    private void renewBoard(Piece movedPiece, Position source, Position target) {
        Piece emptyPiece = new EmptyPiece(new PieceInfo(source, Team.NONE), EMPTY_MOVE_STRATEGY);

        board.put(source, emptyPiece);
        board.put(target, movedPiece);
    }

    private Piece movePiece(Piece piece, Position source, Position target) {
        return piece.move(target,
                isObstacleInRange(source, target),
                isPieceExistInPosition(target),
                isPieceFromSameTeam(target, piece.getTeam()));
    }

    public boolean isObstacleInRange(Position currentPosition, Position newPosition) {
        List<Position> internalPositions = currentPosition.getInternalPositions(newPosition);

        return internalPositions.stream()
                .map(board::get)
                .anyMatch(piece -> piece.getType() != PieceType.EMPTY);
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }
}
