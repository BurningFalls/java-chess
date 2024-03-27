package chess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USERNAME = "root"; //  MySQL 서버 아이디
    private static final String PASSWORD = "root"; // MySQL 서버 비밀번gㅗ

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION;

        try {
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            System.out.println("DB 연결에 성공했습니다.");
        } catch (SQLException e) {
            System.out.println("DB 연결에 실패했습니다: " + e.getMessage());
        }

        return connection;
    }
}
