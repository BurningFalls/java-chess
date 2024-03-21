package chess.controller;

import static chess.domain.CommandType.END;
import static chess.domain.CommandType.MOVE;
import static chess.domain.CommandType.START;

import chess.domain.Board;
import chess.domain.PieceInfo;
import chess.domain.Position;
import chess.domain.Team;
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
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void playGame() {
        boolean checkFirst = true;
        Team turn = Team.WHITE;
        while (true) {
            List<String> commands = InputView.inputCommand();
            String command = commands.get(0);
            if (command.equals(START.getCommandType())) {
                if (checkFirst) {
                    OutputView.printBoard(makeBoardDto(board.getBoard()));
                    checkFirst = false;
                } else if (!checkFirst) {
                    System.out.println("다시 입력하세요.");
                }
                continue;
            }
            if (command.equals(MOVE.getCommandType())) {
                if (checkFirst) {
                    System.out.println("다시 입력하세요.");
                    continue;
                }
                Position source = Position.of(commands.get(1));
                Position target = Position.of(commands.get(2));
                if (source.equals(target) || !checkTurn(source, turn)) {
                    System.out.println("잘못된 움직임입니다.");
                    continue;
                }
                movePieceAndRenewBoard(source, target);
                OutputView.printBoard(makeBoardDto(board.getBoard()));
                turn = Team.takeTurn(turn);
                continue;
            }
            if (command.equals(END.getCommandType())) {
                break;
            }
        }
    }

    public void movePieceAndRenewBoard(Position source, Position target) {
        board.movePieceAndRenewBoard(source, target);
    }

    public boolean checkTurn(Position source, Team team) {
        return board.getBoard().get(source).isSameTeam(team);
    }

    private BoardDto makeBoardDto(Map<Position, Piece> board) {
        List<List<String>> rawBoard = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(8, "."));
            rawBoard.add(row);
        }

        for (var entrySet : board.entrySet()) {
            Position position = entrySet.getKey();
            Piece piece = entrySet.getValue();

            int realYPosition = position.getY() - 1;
            int realXPosition = position.getX() - 1;
            PieceType pieceType = piece.getType();
            PieceInfo pieceInfo = piece.getPieceInfo();

            rawBoard.get(realYPosition).set(realXPosition, pieceType.getPieceLetter(pieceInfo.getTeam()));
        }
        return new BoardDto(rawBoard);
    }
}