package chess.domain.dao;

import chess.domain.dto.ChessRoomDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class MysqlChessRoomDao implements ChessRoomDao {
    private static MysqlChessRoomDao instance;
    private static final String TABLE_NAME = "chess_room";

    public static synchronized MysqlChessRoomDao getInstance() {
        if (instance == null) {
            instance = new MysqlChessRoomDao();
        }
        return instance;
    }

    @Override
    public void addChessRoom(Connection connection, ChessRoomDto chessRoomDto) {
        final var query = "INSERT INTO " + TABLE_NAME + " (id, turn) VALUES(?, ?)";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, chessRoomDto.id());
            statement.setString(2, chessRoomDto.turn());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTurnById(Connection connection, String turn, long id) {
        final var query = "UPDATE " + TABLE_NAME + " SET turn=? WHERE id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setString(1, turn);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findTurnById(Connection connection, long id) {
        final var query = "SELECT * FROM " + TABLE_NAME + " WHERE id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("turn");
            }
            throw new NoSuchElementException("ID에 해당하는 턴을 찾을 수 없습니다: " + id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteChessRoomById(Connection connection, long id) {
        final var query = "DELETE FROM " + TABLE_NAME + " where id=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
