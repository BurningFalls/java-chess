package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    private static final String TABLE_NAME = "piece";
    private static final long CHESS_ROOM_ID = 1;

    private final Connection connection;

    public BoardDao(Connection connection) {
        this.connection = connection;
    }

    public void addPiece(PieceDto pieceDto) {
        final var query =
                "INSERT IGNORE INTO " + TABLE_NAME + " (chess_room_id, position, type, team) VALUES(?, ?, ?, ?)";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, CHESS_ROOM_ID);
            statement.setString(2, pieceDto.position());
            statement.setString(3, pieceDto.type());
            statement.setString(4, pieceDto.team());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PieceDto> findAll() {
        final var query = "SELECT * FROM " + TABLE_NAME;
        try (var statement = connection.prepareStatement(query)) {
            var resultSet = statement.executeQuery();

            List<PieceDto> pieceDtos = new ArrayList<>();
            while (resultSet.next()) {
                long chess_room_id = resultSet.getLong("chess_room_id");
                String position = resultSet.getString("position");
                String type = resultSet.getString("type");
                String team = resultSet.getString("team");

                pieceDtos.add(new PieceDto(chess_room_id, position, type, team));
            }
            return pieceDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final var query = "DELETE FROM " + TABLE_NAME;
        try (var statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setAutoIncrementToOne() {
        final var query = "ALTER TABLE " + TABLE_NAME + " AUTO_INCREMENT = 1";
        try (var statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
