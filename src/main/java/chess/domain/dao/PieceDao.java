package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.util.List;

public interface PieceDao {
    void addPieceByChessRoomId(Connection connection, PieceDto pieceDto, Long chess_room_id);

    List<PieceDto> findAllByChessRoomId(Connection connection, Long chess_room_id);

    void deleteAllByChessRoomId(Connection connection, Long chess_room_id);
}
