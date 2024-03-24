package chess.controller;

import chess.domain.Board;
import chess.domain.Command;
import chess.domain.CommandLogger;
import chess.domain.CommandType;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.dto.BoardDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ChessGame {
    private static final int BOARD_SIZE = 8;
    private static final String EMPTY_PIECE = ".";
    private static final int INDEX_OFFSET = 1;

    private final Board board;
    private final CommandLogger commandLogger;

    public ChessGame(Board board) {
        this.board = board;
        this.commandLogger = new CommandLogger();
    }

    public void startGame() {
        while (!commandLogger.isGameEnd()) {
            playGame();
        }
    }

    private void playGame() {
        try {
            play();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void play() {
        Command command = Command.from(InputView.inputCommand());
        commandLogger.addLog(command);

        if (command.isTypeEqualTo(CommandType.MOVE)) {
            board.movePiece(command, commandLogger.whoTurn());
            commandLogger.changeTurn();
        }

        OutputView.printBoard(makeBoardDto(board.getBoard()));
    }

    private BoardDto makeBoardDto(Map<Position, Piece> board) {
        List<List<String>> rawBoard = makeRawBoard();
        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();
            PieceType pieceType = piece.getType();
            PieceInfo pieceInfo = piece.getPieceInfo();

            rawBoard.get(position.getY() - INDEX_OFFSET)
                    .set(position.getX() - INDEX_OFFSET, pieceType.getPieceLetter(pieceInfo.getTeam()));
        }
        return new BoardDto(rawBoard);
    }

    private List<List<String>> makeRawBoard() {
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(BOARD_SIZE, EMPTY_PIECE));
            rawBoard.add(row);
        }

        return rawBoard;
    }
}
