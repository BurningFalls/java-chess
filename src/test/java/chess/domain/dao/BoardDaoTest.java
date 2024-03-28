package chess.domain.dao;

import chess.DatabaseConnection;
import chess.domain.board.BoardDatabaseInitializer;
import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.assertj.core.api.Assertions;
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
        BoardDatabaseInitializer.initialize(boardDao);
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

    @DisplayName("특정 Position 값을 갖는 Piece를 테이블에 추가할 수 있다.")
    @Test
    void addPieceTest() {
        boardDao.addPiece(new PieceDto("a3", "ROOK", "WHITE"));

        PieceDto pieceDto = boardDao.findPieceByPosition("a3");

        Assertions.assertThat(pieceDto).isEqualTo(new PieceDto("a3", "ROOK", "WHITE"));
    }

    @DisplayName("특정 Position 값을 갖는 Piece를 테이블에서 찾을 수 있다.")
    @Test
    void findPieceByPositionTest() {
        PieceDto pieceDto = boardDao.findPieceByPosition("a1");

        Assertions.assertThat(pieceDto).isEqualTo(new PieceDto("a1", "ROOK", "WHITE"));
    }

    @DisplayName("모든 Piece를 테이블에서 찾을 수 있다.")
    @Test
    void findAllPieceTest() {
        List<PieceDto> pieceDtos = boardDao.findAll();

        Assertions.assertThat(pieceDtos.size()).isEqualTo(32);
    }
}
