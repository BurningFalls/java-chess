package chess.domain;

import chess.DatabaseConnection;
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
    private static final String DATABASE_NAME = "chess";
    private static final Connection connection = DatabaseConnection.getConnection(DATABASE_NAME);

    private final ChessService chessService;
    private Board board;
    private Team turn;

    public ChessGame() {
        chessService = ChessService.getInstance(new MysqlPieceDao(connection), new MysqlChessRoomDao(connection));
        this.board = new Board(new HashMap<>());
        this.turn = Team.NONE;
    }

    public boolean isNotFinish() {
        return !board.isKingDead();
    }

    public void initializeData(Long chessRoomId) {
        chessService.initializeChess(chessRoomId);

    }

    public void loadData(Long chessRoomId) {
        board = chessService.loadPieces(chessRoomId);
        turn = chessService.loadTurn(chessRoomId);
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

    public void saveData(Long chessRoomId) {
        chessService.savePieces(board, chessRoomId);
        chessService.saveTurn(turn, chessRoomId);
    }

    public void deleteData(Long chessRoomId) {
        chessService.deletePieces(chessRoomId);
    }

    public String getRawTurn() {
        return turn.getRawTeam();
    }

    public Map<Position, Piece> getRawBoard() {
        return board.getBoard();
    }
}
