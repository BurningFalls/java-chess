package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private final ChessService chessService = new ChessService();
    private Board board;
    private Team turn;

    public ChessGame() {
        this.board = new Board(new HashMap<>());
        this.turn = Team.NONE;
    }

    public boolean isNotFinish() {
        return !board.isKingDead();
    }

    public void initializeData() {
        chessService.initializeChess();
    }

    public void loadData() {
        try {
            board = chessService.loadPieces();
            turn = chessService.loadTurn();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("저장된 데이터가 없습니다.");
        }
    }

    public void movePiece(Position target, Position source) {
        board.movePiece(target, source, turn);
        turn = Team.takeTurn(turn);
    }

    public Map<Team, Double> calculatePiecesScoreSum() {
        Map<Team, Double> scores = new HashMap<>();

        scores.put(Team.BLACK, board.calculatePiecesScoreSum(Team.BLACK));
        scores.put(Team.WHITE, board.calculatePiecesScoreSum(Team.WHITE));

        return scores;
    }

    public void saveData() {
        chessService.saveData(board);
        chessService.saveTurn(turn);
    }

    public String getRawTurn() {
        return turn.getRawTeam();
    }

    public Map<Position, Piece> getRawBoard() {
        return board.getBoard();
    }
}
