package chess.controller;

import chess.domain.ChessGame;
import chess.view.Enter;
import chess.view.Enterable;
import chess.view.Input;
import chess.view.Output;

public class ConsoleController {

    private static final Enterable enterable = new Enter();

    public void run() {
        Output.printChessGameStart();

        ChessGame chessGame = new ChessGame();
        while (!chessGame.isEnded()) {
            command(chessGame);
        }
    }

    private void command(final ChessGame chessGame) {
        try {
            Command.execute(Input.inputCommand(enterable), chessGame);
        } catch (IllegalStateException | IllegalArgumentException exception) {
            Output.printExceptionMessage(exception.getMessage());
        }
    }
}