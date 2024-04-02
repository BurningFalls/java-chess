package chess.domain.initializer;

import chess.domain.dao.PieceDao;
import chess.domain.dto.PieceDto;
import java.sql.Connection;

public class BoardInitializer {

    private BoardInitializer() {
    }

    public static void initialize(Connection connection, PieceDao pieceDao, Long chess_room_id) {
        placeInitialPieces(connection, pieceDao, chess_room_id);
    }

    private static void placeInitialPieces(Connection connection, PieceDao pieceDao, Long chess_room_id) {
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("a1", "ROOK", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("b1", "KNIGHT", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("c1", "BISHOP", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("d1", "QUEEN", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("e1", "KING", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("f1", "BISHOP", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("g1", "KNIGHT", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("h1", "ROOK", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("a2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("b2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("c2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("d2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("e2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("f2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("g2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("h2", "WHITE_PAWN_FIRST_MOVE", "WHITE"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("a8", "ROOK", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("b8", "KNIGHT", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("c8", "BISHOP", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("d8", "QUEEN", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("e8", "KING", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("f8", "BISHOP", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("g8", "KNIGHT", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("h8", "ROOK", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("a7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("b7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("c7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("d7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("e7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("f7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("g7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
        pieceDao.addPieceByChessRoomId(connection, new PieceDto("h7", "BLACK_PAWN_FIRST_MOVE", "BLACK"), chess_room_id);
    }
}
