package chess;

import chess.controller.ChessGameController;
import chess.view.OutputView;
import java.sql.Connection;

public class Application {
    private static final String DATABASE_NAME = "chess";
    private static final long CHESS_ROOM_ID = 1;

    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getConnection(DATABASE_NAME);

        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        ChessGameController chessGameController = new ChessGameController();
        chessGameController.startGame(connection, CHESS_ROOM_ID);
    }
}
