package chess.domain.initializer;

import chess.domain.dao.ChessRoomDao;
import chess.domain.dto.ChessRoomDto;

public class ChessRoomInitializer {

    private ChessRoomInitializer() {
    }

    public static void initialize(ChessRoomDao chessRoomDao) {
        chessRoomDao.setAutoIncrementToOne();
        chessRoomDao.addChessRoom(new ChessRoomDto("WHITE"));
    }
}
