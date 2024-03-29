package chess.domain.initializer;

import chess.domain.dao.ChessRoomDao;
import chess.domain.dto.ChessRoomDto;

public class ChessRoomInitializer {
    private static final String START_TURN = "WHITE";

    private ChessRoomInitializer() {
    }

    public static void initialize(ChessRoomDao chessRoomDao) {
        chessRoomDao.setAutoIncrementToOne();
        chessRoomDao.addChessRoom(new ChessRoomDto(START_TURN));
    }
}
