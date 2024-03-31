package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import java.sql.Connection;
import java.sql.SQLException;

public class ChessRoomDao {
    private static final String TABLE_NAME = "chess_room";

    private final Connection connection;

    public ChessRoomDao(Connection connection) {
        this.connection = connection;
    }

    public void addChessRoom(ChessRoomDto chessRoomDto) {
        final var query = "INSERT INTO " + TABLE_NAME + " (id, turn) VALUES(?, ?)";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chessRoomDto.id());
            statement.setString(2, chessRoomDto.turn());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTurnById(String turn, long id) {
        final var query = "UPDATE " + TABLE_NAME + " SET turn=? WHERE id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setString(1, turn);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String findTurnById(long id) {
        final var query = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("turn");
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteChessRoomById(long id) {
        final var query = "DELETE FROM " + TABLE_NAME + " where id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
