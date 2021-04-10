package chess.dao;

import chess.dto.ChessCellDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoardDao implements ChessDao {
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost:13306";
        String database = "db_chess";
        String option = "?useSSL=false&serverTimezone=UTC";
        String userName = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    @Override
    public void addPosition(ChessCellDto eachPosition) {
        String query = "INSERT INTO chess_table (game_id, position, team_color, piece_type, alive) VALUES (1, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.setString(1, eachPosition.getPosition());
            pstmt.setString(2, eachPosition.getTeamColor());
            pstmt.setString(3, eachPosition.getPieceType());
            pstmt.setBoolean(4, eachPosition.getAlive());

            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void addPositions(List<ChessCellDto> board) {
        for (ChessCellDto eachPosition : board) {
            addPosition(eachPosition);
        }
    }

    @Override
    public List<ChessCellDto> findPositions() {
        String query = "SELECT * FROM chess_table WHERE game_id = 1";

        List<ChessCellDto> chessboard = new ArrayList<>();
        try (PreparedStatement pstmt = getConnection().prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                ChessCellDto chessboardDto = new ChessCellDto(
                        rs.getString("position"),
                        rs.getString("team_color"),
                        rs.getString("piece_type"),
                        convertBooleanType(rs.getBoolean("alive"))
                );
                chessboard.add(chessboardDto);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return chessboard;
    }

    private boolean convertBooleanType(boolean value) {
        return value;
    }

    @Override
    public void removePositions() {
        String query = "DELETE FROM chess_table WHERE game_id=1";
        try (PreparedStatement pstmt = getConnection().prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}