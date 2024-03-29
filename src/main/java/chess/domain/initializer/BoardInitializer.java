package chess.domain.initializer;

import chess.domain.dao.PieceDao;
import chess.domain.dto.PieceDto;

public class BoardInitializer {
    private static final long CHESS_ROOM_ID = 1;

    private BoardInitializer() {
    }

    public static void initialize(PieceDao pieceDao) {
        pieceDao.setAutoIncrementToOne();
        placeInitialPieces(pieceDao);
    }

    private static void placeInitialPieces(PieceDao pieceDao) {
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a1", "ROOK", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b1", "KNIGHT", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c1", "BISHOP", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d1", "QUEEN", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e1", "KING", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f1", "BISHOP", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g1", "KNIGHT", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h1", "ROOK", "WHITE"));

        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));

        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a8", "ROOK", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b8", "KNIGHT", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c8", "BISHOP", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d8", "QUEEN", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e8", "KING", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f8", "BISHOP", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g8", "KNIGHT", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h8", "ROOK", "BLACK"));

        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        pieceDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
    }
}
