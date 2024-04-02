package chess.domain;

import chess.domain.board.Board;
import chess.domain.dao.MysqlChessRoomDao;
import chess.domain.dao.MysqlPieceDao;
import chess.domain.piece.Piece;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.service.ChessService;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private final ChessService chessService;
    private Board board;
    private Team turn;

    public ChessGame() {
        chessService = ChessService.getInstance(new MysqlPieceDao(), new MysqlChessRoomDao());
        this.board = new Board(new HashMap<>());
        this.turn = Team.NONE;
    }

    public boolean isNotFinish() {
        return !board.isKingDead();
    }

    public void initializeData(Connection connection, Long chessRoomId) {
        chessService.initializeChess(connection, chessRoomId);

    }

    public void loadData(Connection connection, Long chessRoomId) {
        board = chessService.loadPieces(connection, chessRoomId);
        turn = chessService.loadTurn(connection, chessRoomId);
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

    public void saveData(Connection connection, Long chessRoomId) {
        chessService.savePieces(connection, board, chessRoomId);
        chessService.saveTurn(connection, turn, chessRoomId);
    }

    public void deleteData(Connection connection, Long chessRoomId) {
        chessService.deletePieces(connection, chessRoomId);
    }

    public String getRawTurn() {
        return turn.getRawTeam();
    }

    public Map<Position, Piece> getRawBoard() {
        return board.getBoard();
    }
}
