package chess.domain.initializer;

import chess.domain.dao.ChessRoomDao;
import chess.domain.dto.ChessRoomDto;
import java.sql.Connection;

public class ChessRoomInitializer {
    private static final String START_TURN = "WHITE";

    private ChessRoomInitializer() {
    }

    public static void initialize(Connection connection, ChessRoomDao chessRoomDao, Long chess_room_id) {
        chessRoomDao.addChessRoom(connection, new ChessRoomDto(chess_room_id, START_TURN));
    }
}
