package chess.controller;

import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.command.CommandLogger;
import chess.domain.command.CommandType;
import chess.domain.dto.BoardPrintDto;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.service.BoardService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGameController {
    private static final int BOARD_SIZE = 8;
    private static final String EMPTY_PIECE = ".";
    private static final int INDEX_OFFSET = 1;

    private final BoardService boardService = new BoardService();
    private final CommandLogger commandLogger;
    private Board board;
    private Team turn;

    public ChessGameController() {
        this.board = new Board(new HashMap<>());
        this.turn = boardService.loadTurn();
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
        if (commandLogger.isGameStart()) {
            OutputView.printWhoTurn(turn.getRawTeam());
        }
        Command command = Command.from(InputView.inputCommand());
        commandLogger.checkCommandValidate(command);

        playWithCommand(command);

        commandLogger.addLog(command);
        OutputView.printBoard(makeBoardDto(board.getBoard()));
    }

    private void playWithCommand(Command command) {
        if (command.isTypeEqualTo(CommandType.START)) {
            processStartCommand();
        } else if (command.isTypeEqualTo(CommandType.LOAD)) {
            processLoadCommand();
        } else if (command.isTypeEqualTo(CommandType.MOVE)) {
            processMoveCommand(command);
        } else if (command.isTypeEqualTo(CommandType.STATUS)) {
            processStatusCommand();
        } else if (command.isTypeEqualTo(CommandType.SAVE)) {
            processSaveCommand();
        }
    }

    private void processStartCommand() {
        boardService.initializeBoard();
        board = boardService.loadData();
    }

    private void processLoadCommand() {
        board = boardService.loadData();
        turn = boardService.loadTurn();
    }

    private void processMoveCommand(Command command) {
        board.movePiece(command, turn);
        turn = Team.takeTurn(turn);
    }

    private void processStatusCommand() {
        double blackPiecesScoreSum = board.calculatePiecesScoreSum(Team.BLACK);
        double whitePiecesScoreSum = board.calculatePiecesScoreSum(Team.WHITE);
        OutputView.printScoreSum(blackPiecesScoreSum, whitePiecesScoreSum);
        OutputView.printWinnerTeam(blackPiecesScoreSum, whitePiecesScoreSum);
    }

    private void processSaveCommand() {
        boardService.saveData(board);
        boardService.saveTurn(turn);
    }

    private BoardPrintDto makeBoardDto(Map<Position, Piece> board) {
        List<List<String>> rawBoard = makeRawBoard();
        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();
            PieceType pieceType = piece.getType();
            PieceInfo pieceInfo = piece.getPieceInfo();

            rawBoard.get(position.getRankIndex() - INDEX_OFFSET)
                    .set(position.getFileIndex() - INDEX_OFFSET, pieceType.getPieceLetter(pieceInfo.getTeam()));
        }
        return new BoardPrintDto(rawBoard);
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
