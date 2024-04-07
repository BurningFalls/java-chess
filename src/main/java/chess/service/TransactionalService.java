package chess.service;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class TransactionalService {

    public void executeInTransaction(Connection connection, TransactionalOperation operation) {
        try {
            connection.setAutoCommit(false);
            operation.execute();
            connection.commit();
        } catch (Exception e) {
            rollback(connection);
            throw new RuntimeException("Transaction 실패: ", e);
        } finally {
            resetAutoCommit(connection);
        }
    }

    private void rollback(Connection connection) {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Rollback 실패: ", e);
        }
    }

    private void resetAutoCommit(Connection connection) {
        try {
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Set Auto Commit 실패: ", e);
        }
    }
}
