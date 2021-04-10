package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import java.util.List;

public class Init extends Preparing {

    public Init(Board board) {
        super(board);
    }

    @Override
    public State start() {
        return new WhiteTurn(afterStartBoard());
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        throw new IllegalStateException("아직 체스게임이 시작되지 않았습니다.");
    }

    @Override
    public State passTurn() {
        throw new IllegalStateException("아직 체스게임이 시작되지 않았습니다.");
    }

    @Override
    public Board board() {
        return afterStartBoard();
    }

    @Override
    public String winner() {
        throw new IllegalStateException("아직 체스게임이 시작되지 않았습니다.");
    }

    @Override
    public State end() {
        return new End(afterStartBoard());
    }

    @Override
    public boolean isInit() {
        return true;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isNotEnd() {
        return true;
    }

    @Override
    public List<Position> movablePath(Position position) {
        throw new IllegalStateException("아직 체스게임이 시작되지 않았습니다.");
    }

    @Override
    public String state() {
        return "게임 시작 전";
    }


}