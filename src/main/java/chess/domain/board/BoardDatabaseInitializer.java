package chess.domain.board;

import chess.domain.dao.BoardDao;
import chess.domain.dto.PieceDto;

public class BoardDatabaseInitializer {

    private BoardDatabaseInitializer() {
    }

    public static void initialize(BoardDao boardDao) {

        placeInitialPieces(boardDao);
    }

    private static void placeInitialPieces(BoardDao boardDao) {
        boardDao.addPiece(new PieceDto("a1", "ROOK", "WHITE"));
        boardDao.addPiece(new PieceDto("b1", "KNIGHT", "WHITE"));
        boardDao.addPiece(new PieceDto("c1", "BISHOP", "WHITE"));
        boardDao.addPiece(new PieceDto("d1", "QUEEN", "WHITE"));
        boardDao.addPiece(new PieceDto("e1", "KING", "WHITE"));
        boardDao.addPiece(new PieceDto("f1", "BISHOP", "WHITE"));
        boardDao.addPiece(new PieceDto("g1", "KNIGHT", "WHITE"));
        boardDao.addPiece(new PieceDto("h1", "ROOK", "WHITE"));

        boardDao.addPiece(new PieceDto("a2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto("b2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto("c2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto("d2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto("e2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto("f2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto("g2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));
        boardDao.addPiece(new PieceDto("h2", "WHITE_PAWN_FIRST_MOVE", "WHITE"));

        boardDao.addPiece(new PieceDto("a8", "ROOK", "BLACK"));
        boardDao.addPiece(new PieceDto("b8", "KNIGHT", "BLACK"));
        boardDao.addPiece(new PieceDto("c8", "BISHOP", "BLACK"));
        boardDao.addPiece(new PieceDto("d8", "QUEEN", "BLACK"));
        boardDao.addPiece(new PieceDto("e8", "KING", "BLACK"));
        boardDao.addPiece(new PieceDto("f8", "BISHOP", "BLACK"));
        boardDao.addPiece(new PieceDto("g8", "KNIGHT", "BLACK"));
        boardDao.addPiece(new PieceDto("h8", "ROOK", "BLACK"));

        boardDao.addPiece(new PieceDto("a7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto("b7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto("c7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto("d7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto("e7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto("f7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto("g7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
        boardDao.addPiece(new PieceDto("h7", "BLACK_PAWN_FIRST_MOVE", "BLACK"));
    }
}
