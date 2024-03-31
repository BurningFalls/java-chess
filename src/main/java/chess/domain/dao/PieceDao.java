package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {
    private static final String TABLE_NAME = "piece";

    private final Connection connection;

    public PieceDao(Connection connection) {
        this.connection = connection;
    }

    public void addPiece(PieceDto pieceDto, Long chess_room_id) {
        final var query =
                "INSERT IGNORE INTO " + TABLE_NAME + " (chess_room_id, position, type, team) VALUES(?, ?, ?, ?)";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chess_room_id);
            statement.setString(2, pieceDto.position());
            statement.setString(3, pieceDto.type());
            statement.setString(4, pieceDto.team());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PieceDto> findAll(Long chess_room_id) {
        final var query = "SELECT * FROM " + TABLE_NAME + " where chess_room_id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chess_room_id);

            var resultSet = statement.executeQuery();

            List<PieceDto> pieceDtos = new ArrayList<>();
            while (resultSet.next()) {
                String position = resultSet.getString("position");
                String type = resultSet.getString("type");
                String team = resultSet.getString("team");

                pieceDtos.add(new PieceDto(position, type, team));
            }
            return pieceDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll(Long chess_room_id) {
        final var query = "DELETE FROM " + TABLE_NAME + " where chess_room_id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chess_room_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
