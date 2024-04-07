package chess.service;

public interface TransactionalOperation {
    void execute() throws Exception;
}
