package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import java.sql.Connection;

public interface ChessRoomDao {
    void addChessRoom(Connection connection, ChessRoomDto chessRoomDto);

    void updateTurnById(Connection connection, String turn, long id);

    String findTurnById(Connection connection, long id);

    void deleteChessRoomById(Connection connection, long id);
}
