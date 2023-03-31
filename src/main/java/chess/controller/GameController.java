package chess.controller;

import chess.db.dao.BoardDao;
import chess.db.dao.TurnDao;
import chess.domain.Board;
import chess.domain.BoardGenerator;
import chess.domain.ChessGame;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.square.Square;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static chess.controller.Command.*;
import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class GameController {

    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int MOVE_COMMAND = 0;
    private static final int FILE_COMMAND = 1;
    private static final int RANK_COMMAND = 2;

    private final InputView inputView;
    private final OutputView outputView;
    private final Map<Command, Action> commandAction = new EnumMap<>(Command.class);
    private final BoardDao boardDao = new BoardDao();
    private final TurnDao turnDao = new TurnDao();

    private Command currentStatus = READY;

    public GameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        commandAction.put(START, (chessGame, ignore) -> start(chessGame));
        commandAction.put(STATUS, (chessGame, ignore) -> status(chessGame));
        commandAction.put(MOVE, this::move);
        commandAction.put(END, (chessGame, ignore) -> end(chessGame));
    }

    public void run() {
        outputView.printGameStart();
        ChessGame chessGame = makeChessGame();
        Command command;
        do {
            List<String> gameCommand = repeatUntilValidate(() -> play(chessGame));
            command = Command.from(gameCommand.get(MOVE_COMMAND));
        } while (command != END && !chessGame.isGameEnd());
        if (chessGame.isGameEnd()) {
            status(chessGame);
            boardDao.delete();
            turnDao.delete();
        }
    }

    private ChessGame makeChessGame() {
        Map<Square, Piece> storedBoard = boardDao.select();
        Team team = turnDao.select();
        if (storedBoard.equals(Collections.emptyMap())) {
            storedBoard = BoardGenerator.init();
            boardDao.insert(storedBoard);
        }
        if (Team.EMPTY.equals(team)) {
            team = WHITE;
        }
        return new ChessGame(new Board(storedBoard), team);
    }

    public List<String> play(final ChessGame chessGame) {
        List<String> gameCommand = repeatUntilValidate(this::inputCommand);
        Command command = Command.from(gameCommand.get(MOVE_COMMAND));
        validateStatus(command);
        commandAction.get(command).execute(chessGame, gameCommand);
        currentStatus = command;
        return gameCommand;
    }

    private List<String> inputCommand() {
        List<String> gameCommand = inputView.readGameCommand();
        Command.from(gameCommand.get(MOVE_COMMAND));
        return gameCommand;
    }

    private void validateStatus(final Command command) {
        if (command == START && currentStatus != READY) {
            throw new IllegalArgumentException("이미 체스 게임이 시작되었습니다. move, status, end 중에 입력해주세요.");
        }
        if (command != START && currentStatus == READY) {
            throw new IllegalArgumentException("체스 게임이 아직 시작되지 않았습니다. start 먼저 입력해주세요.");
        }
    }

    private void start(final ChessGame chessGame) {
        outputView.printChessBoard(chessGame.getBoard());
    }

    private void status(final ChessGame chessGame) {
        outputView.printTeamScore(WHITE, chessGame.calculateTeamScore(WHITE));
        outputView.printTeamScore(BLACK, chessGame.calculateTeamScore(BLACK));
        outputView.printWinnerTeam(chessGame.calculateWinnerTeam());
    }

    private void move(final ChessGame chessGame, final List<String> gameCommand) {
        validateMoveCommandFormat(gameCommand);
        chessGame.movePiece(boardDao, gameCommand.get(FILE_COMMAND), gameCommand.get(RANK_COMMAND));
        outputView.printChessBoard(chessGame.getBoard());
        turnDao.insert(chessGame.getTeam());
    }

    private void end(final ChessGame chessGame) {
        turnDao.insert(chessGame.getTeam());
    }

    private void validateMoveCommandFormat(final List<String> gameCommand) {
        if (gameCommand.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("move source위치 target위치 형태로 입력해주세요. 예) move a2 a3");
        }
    }

    private <T> T repeatUntilValidate(final Supplier<T> supplier) {
        T input;
        do {
            input = readUserInput(supplier);
        } while (input == null);
        return input;
    }

    private <T> T readUserInput(final Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return null;
        }
    }
}