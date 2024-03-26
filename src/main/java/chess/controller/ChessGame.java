package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.CommandLogger;
import chess.domain.command.CommandType;
import chess.domain.dto.BoardDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
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
        while (!commandLogger.isCommandEnd() && !board.isKingDead()) {
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
        } else if (command.isTypeEqualTo(CommandType.STATUS)) {
            // ...
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

            rawBoard.get(position.getRankIndex() - INDEX_OFFSET)
                    .set(position.getFileIndex() - INDEX_OFFSET, pieceType.getPieceLetter(pieceInfo.getTeam()));
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
