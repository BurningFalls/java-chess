package chess.domain.dao;

import chess.domain.dto.PieceDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {
    private final Connection connection;

    public BoardDao(Connection connection) {
        this.connection = connection;
    }

    public void addPiece(PieceDto pieceDto) {
        final var query = "INSERT IGNORE INTO pieces VALUES(?, ?, ?)";
        try (var statement = connection.prepareStatement(query)) {
            statement.setString(1, pieceDto.position());
            statement.setString(2, pieceDto.type());
            statement.setString(3, pieceDto.team());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public PieceDto findPieceByPosition(String position) {
        final var query = "SELECT * FROM pieces WHERE position=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setString(1, position);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new PieceDto(
                        resultSet.getString("position"),
                        resultSet.getString("type"),
                        resultSet.getString("team")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<PieceDto> findAll() {
        final var query = "SELECT * FROM pieces";
        try (var statement = connection.prepareStatement(query)) {
            var resultSet = statement.executeQuery();

            List<PieceDto> pieceDtos = new ArrayList<>();
            while (resultSet.next()) {
                pieceDtos.add(new PieceDto(
                        resultSet.getString("position"),
                        resultSet.getString("type"),
                        resultSet.getString("team")
                ));
            }
            return pieceDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAll() {
        final var query = "DELETE FROM pieces";
        try (var statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removePieceByPosition(String position) {
        final var query = "DELETE FROM pieces WHERE position=?";
        try (var statement = connection.prepareStatement(query)) {
            statement.setString(1, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
