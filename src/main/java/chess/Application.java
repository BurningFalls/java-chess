package chess;

import chess.controller.ChessGameController;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        ChessGameController chessGameController = new ChessGameController();

        chessGameController.startGame();
    }
}
