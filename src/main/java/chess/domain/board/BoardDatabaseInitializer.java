package chess.domain.board;

import chess.domain.dao.BoardDao;
import chess.domain.dto.PieceDto;

public class BoardDatabaseInitializer {
    private static final long CHESS_ROOM_ID = 1;

    private BoardDatabaseInitializer() {
    }

    public static void initialize(BoardDao boardDao) {

        placeInitialPieces(boardDao);
    }

    private static void placeInitialPieces(BoardDao boardDao) {
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a1", "ROOK", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b1", "KNIGHT", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c1", "BISHOP", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d1", "QUEEN", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e1", "KING", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f1", "BISHOP", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g1", "KNIGHT", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h1", "ROOK", "WHITE"));

        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));

        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a8", "ROOK", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b8", "KNIGHT", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c8", "BISHOP", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d8", "QUEEN", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e8", "KING", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f8", "BISHOP", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g8", "KNIGHT", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h8", "ROOK", "BLACK"));

        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "a7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "b7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "c7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "d7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "e7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "f7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "g7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto(CHESS_ROOM_ID, "h7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
    }
}
