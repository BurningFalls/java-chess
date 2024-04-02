package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class FakeChessRoomDao implements ChessRoomDao {
    private final Map<Long, String> turns = new HashMap<>();

    @Override
    public void addChessRoom(Connection connection, ChessRoomDto chessRoomDto) {
        turns.put(chessRoomDto.id(), chessRoomDto.turn());
    }

    @Override
    public void updateTurnById(Connection connection, String turn, long id) {
        turns.put(id, turn);
    }

    @Override
    public String findTurnById(Connection connection, long id) {
        return turns.get(id);
    }

    @Override
    public void deleteChessRoomById(Connection connection, long id) {
        turns.remove(id);
    }
}
