package chess.controller;

import chess.domain.ChessGame;
import chess.domain.command.Command;
import chess.domain.command.CommandLogger;
import chess.domain.command.CommandType;
import chess.domain.dto.BoardPrintDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.view.InputView;
import chess.view.OutputView;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class ChessGameController {
    private final CommandLogger commandLogger;
    private final ChessGame chessGame;

    public ChessGameController() {
        this.commandLogger = new CommandLogger();
        this.chessGame = new ChessGame();
    }

    public void startGame(Connection connection, Long chessRoomId) {
        while (!commandLogger.isCommandEnd() && chessGame.isNotFinish()) {
            playGame(connection, chessRoomId);
        }
    }

    private void playGame(Connection connection, Long chessRoomId) {
        try {
            play(connection, chessRoomId);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void play(Connection connection, Long chessRoomId) {
        if (commandLogger.isGameStart()) {
            OutputView.printWhoTurn(chessGame.getRawTurn());
        }
        Command command = Command.from(InputView.inputCommand());
        commandLogger.checkCommandValidate(command);

        playWithCommand(connection, command, chessRoomId);

        commandLogger.addLog(command);
        OutputView.printBoard(makeBoardDto(chessGame.getRawBoard()));
    }

    private void playWithCommand(Connection connection, Command command, Long chessRoomId) {
        if (command.isTypeEqualTo(CommandType.START)) {
            processStartCommand(connection, chessRoomId);
        } else if (command.isTypeEqualTo(CommandType.LOAD)) {
            processLoadCommand(connection, chessRoomId);
        } else if (command.isTypeEqualTo(CommandType.MOVE)) {
            processMoveCommand(command);
        } else if (command.isTypeEqualTo(CommandType.STATUS)) {
            processStatusCommand();
        } else if (command.isTypeEqualTo(CommandType.SAVE)) {
            processSaveCommand(connection, chessRoomId);
        }
    }

    private void processStartCommand(Connection connection, Long chessRoomId) {
        chessGame.initializeData(connection, chessRoomId);
        chessGame.loadData(connection, chessRoomId);
    }

    private void processLoadCommand(Connection connection, Long chessRoomId) {
        chessGame.loadData(connection, chessRoomId);
    }

    private void processMoveCommand(Command command) {
        chessGame.movePiece(command.getSource(), command.getTarget());
    }

    private void processStatusCommand() {
        Map<Team, Double> scores = chessGame.calculatePiecesScoreSum();

        OutputView.printScoreSum(scores.get(Team.BLACK), scores.get(Team.WHITE));
        OutputView.printWinnerTeam(scores.get(Team.BLACK), scores.get(Team.WHITE));
    }

    private void processSaveCommand(Connection connection, Long chessRoomId) {
        chessGame.deleteData(connection, chessRoomId);
        chessGame.saveData(connection, chessRoomId);
    }

    private BoardPrintDto makeBoardDto(Map<Position, Piece> board) {
        int indexOffset = 1;
        List<List<String>> rawBoard = makeRawBoard();
        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();
            PieceType pieceType = piece.getType();
            PieceInfo pieceInfo = piece.getPieceInfo();

            rawBoard.get(position.getRankIndex() - indexOffset)
                    .set(position.getFileIndex() - indexOffset, pieceType.getPieceLetter(pieceInfo.getTeam()));
        }
        return new BoardPrintDto(rawBoard);
    }

    private List<List<String>> makeRawBoard() {
        int boardSize = 8;
        String emptyPiece = ".";
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < boardSize; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(boardSize, emptyPiece));
            rawBoard.add(row);
        }

        return rawBoard;
    }
}
