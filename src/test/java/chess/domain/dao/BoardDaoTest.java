package chess.domain.dao;

import chess.DatabaseConnection;
import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {
    private static final String DATABASE_NAME = "chess_test";

    private BoardDao boardDao;

    @BeforeEach
    void setUp() {
        Connection connection = DatabaseConnection.getConnection(DATABASE_NAME);
        boardDao = new BoardDao(connection);

        createPiecesTable(connection);
        addInitialPiecesInTable();
    }

    private void createPiecesTable(Connection connection) {
        String dropTableSQL = "DROP TABLE IF EXISTS pieces;";
        String createTableSQL = "CREATE TABLE pieces ("
                + "position varchar(2) PRIMARY KEY,"
                + "type varchar(10),"
                + "team varchar(10)"
                + ");";

        try (var statement = connection.createStatement()) {
            statement.executeUpdate(dropTableSQL);
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addInitialPiecesInTable() {
        boardDao.addPiece(new PieceDto("a1", "rook", "white"));
        boardDao.addPiece(new PieceDto("b1", "knight", "white"));
        boardDao.addPiece(new PieceDto("c1", "bishop", "white"));
        boardDao.addPiece(new PieceDto("d1", "queen", "white"));
        boardDao.addPiece(new PieceDto("e1", "king", "white"));
        boardDao.addPiece(new PieceDto("f1", "bishop", "white"));
        boardDao.addPiece(new PieceDto("g1", "knight", "white"));
        boardDao.addPiece(new PieceDto("h1", "rook", "white"));

        boardDao.addPiece(new PieceDto("a2", "pawn", "white"));
        boardDao.addPiece(new PieceDto("b2", "pawn", "white"));
        boardDao.addPiece(new PieceDto("c2", "pawn", "white"));
        boardDao.addPiece(new PieceDto("d2", "pawn", "white"));
        boardDao.addPiece(new PieceDto("e2", "pawn", "white"));
        boardDao.addPiece(new PieceDto("f2", "pawn", "white"));
        boardDao.addPiece(new PieceDto("g2", "pawn", "white"));
        boardDao.addPiece(new PieceDto("h2", "pawn", "white"));

        boardDao.addPiece(new PieceDto("a8", "rook", "black"));
        boardDao.addPiece(new PieceDto("b8", "knight", "black"));
        boardDao.addPiece(new PieceDto("c8", "bishop", "black"));
        boardDao.addPiece(new PieceDto("d8", "queen", "black"));
        boardDao.addPiece(new PieceDto("e8", "king", "black"));
        boardDao.addPiece(new PieceDto("f8", "bishop", "black"));
        boardDao.addPiece(new PieceDto("g8", "knight", "black"));
        boardDao.addPiece(new PieceDto("h8", "rook", "black"));

        boardDao.addPiece(new PieceDto("a7", "pawn", "black"));
        boardDao.addPiece(new PieceDto("b7", "pawn", "black"));
        boardDao.addPiece(new PieceDto("c7", "pawn", "black"));
        boardDao.addPiece(new PieceDto("d7", "pawn", "black"));
        boardDao.addPiece(new PieceDto("e7", "pawn", "black"));
        boardDao.addPiece(new PieceDto("f7", "pawn", "black"));
        boardDao.addPiece(new PieceDto("g7", "pawn", "black"));
        boardDao.addPiece(new PieceDto("h7", "pawn", "black"));
    }

    @DisplayName("특정 Position 값을 갖는 Piece를 테이블에 추가할 수 있다.")
    @Test
    void addPieceTest() {
        boardDao.addPiece(new PieceDto("a3", "rook", "white"));

        PieceDto pieceDto = boardDao.findPieceByPosition("a3");

        Assertions.assertThat(pieceDto).isEqualTo(new PieceDto("a3", "rook", "white"));
    }

    @DisplayName("특정 Position 값을 갖는 Piece를 테이블에서 찾을 수 있다.")
    @Test
    void findPieceByPositionTest() {
        PieceDto pieceDto = boardDao.findPieceByPosition("a1");

        Assertions.assertThat(pieceDto).isEqualTo(new PieceDto("a1", "rook", "white"));
    }

    @DisplayName("특정 Position 값을 갖는 Piece를 테이블에서 삭제할 수 있다.")
    @Test
    void removePieceByPositionTest() {
        boardDao.removePieceByPosition("a1");

        PieceDto pieceDto = boardDao.findPieceByPosition("a1");

        Assertions.assertThat(pieceDto).isNull();
    }

    @AfterEach
    void tearDown() {
        Connection connection = DatabaseConnection.getConnection(DATABASE_NAME);
        boardDao = new BoardDao(connection);

        createPiecesTable(connection);
        addInitialPiecesInTable();
    }
}
