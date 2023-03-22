package chess;

import chess.piece.ChessPiece;
import chess.piece.Side;
import chess.position.MovablePosition;
import chess.position.Position;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessController {
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final String CANNOT_MOVE_POSITION_ERROR = "[ERROR] 해당 위치로 움직일 수 없습니다.";
    private static final String PICK_OTHER_PEICE_ERROR = "[ERROR] 자신 편의 기물을 움직여 주세요.";

    private final ChessBoard chessBoard;
    private final ChessGame chessGame;

    public ChessController(ChessBoard chessBoard, ChessGame chessGame) {
        this.chessBoard = chessBoard;
        this.chessGame = chessGame;
    }

    public void startPhase() {
        try {
            InputView.printGameStartMessage();
            OutputView.printChessBoard(chessBoard.getChessBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            startPhase();
        }
    }

    public void commandPhase() {
        while (true) {
            if (!command(Side.WHITE)) {
                break;
            }
            if (!command(Side.BLACK)) {
                break;
            }
        }
    }

    private boolean command(Side side) {
        try {
            List<String> command = InputView.readPlayGameCommand();
            if (command.size() == MOVE_COMMAND_SIZE) {
                moveChessPieceByCondition(command, side);
                OutputView.printChessBoard(chessBoard.getChessBoard());
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            command(side);
            return true;
        }
    }

    public void moveChessPieceByCondition(List<String> moveCommand, Side side) {
        Position sourcePosition = Position.of(moveCommand.get(SOURCE_POSITION_INDEX));
        if (!chessBoard.getChessPieceByPosition(sourcePosition).getSide().equals(side)) {
            throw new IllegalArgumentException(PICK_OTHER_PEICE_ERROR);
        }
        Position targetPosition = Position.of(moveCommand.get(TARGET_POSITION_INDEX));
        ChessPiece sourcePiece = chessBoard.getChessPieceByPosition(sourcePosition);
        MovablePosition movableRoute = sourcePiece.findMovableRoute(chessBoard, sourcePosition);
        if (chessGame.validateMovablePosition(targetPosition, movableRoute)) {
            chessGame.moveChessPiece(sourcePosition, targetPosition);
            return;
        }
        throw new IllegalArgumentException(CANNOT_MOVE_POSITION_ERROR);
    }
}