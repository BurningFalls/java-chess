package chess.service;

import chess.domain.board.Board;
import chess.domain.dao.ChessRoomDao;
import chess.domain.dao.PieceDao;
import chess.domain.dto.PieceDto;
import chess.domain.initializer.BoardInitializer;
import chess.domain.initializer.ChessRoomInitializer;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMaker;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService extends TransactionalService {
    private static ChessService instance;
    private static final int BOARD_SIZE = 8;
    private static final MoveStrategy EMPTY_MOVE_STRATEGY = new EmptyMoveStrategy();

    private final PieceDao pieceDao;
    private final ChessRoomDao chessRoomDao;

    private ChessService(PieceDao pieceDao, ChessRoomDao chessRoomDao) {
        this.pieceDao = pieceDao;
        this.chessRoomDao = chessRoomDao;
    }

    public static synchronized ChessService getInstance(PieceDao pieceDao, ChessRoomDao chessRoomDao) {
        if (instance == null) {
            instance = new ChessService(pieceDao, chessRoomDao);
        }
        return instance;
    }

    public void initializeChess(Connection connection, Long chessRoomId) {
        executeInTransaction(connection, () -> {
            pieceDao.deleteAllByChessRoomId(connection, chessRoomId);
            chessRoomDao.deleteChessRoomById(connection, chessRoomId);

            BoardInitializer.initialize(connection, pieceDao, chessRoomId);
            ChessRoomInitializer.initialize(connection, chessRoomDao, chessRoomId);
        });
    }

    public Board loadPieces(Connection connection, Long chessRoomId) {
        List<PieceDto> pieceDtos = pieceDao.findAllByChessRoomId(connection, chessRoomId);

        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceDto pieceDto : pieceDtos) {
            Piece piece = changePieceDtoToPiece(pieceDto);
            Position position = piece.getPosition();

            pieces.put(position, piece);
        }
        fillEmptyPieces(pieces);

        return new Board(pieces);
    }

    public Team loadTurn(Connection connection, Long chessRoomId) {
        String turn = chessRoomDao.findTurnById(connection, chessRoomId);
        return Team.valueOf(turn);
    }

    private void fillEmptyPieces(Map<Position, Piece> pieces) {
        List<Position> positions = createAllPiecePositions();
        positions.stream()
                .filter(position -> !pieces.containsKey(position))
                .forEach(position -> pieces.put(position,
                        new EmptyPiece(new PieceInfo(position, Team.NONE), EMPTY_MOVE_STRATEGY)));
    }

    private static List<Position> createAllPiecePositions() {
        List<Position> positions = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            positions.add(Position.of("" + (char) (i / BOARD_SIZE + 'a') + (char) (i % BOARD_SIZE + '1')));
        }
        return positions;
    }

    public void savePiecesAndTurn(Connection connection, Board board, Team turn, Long chessRoomId) {
        executeInTransaction(connection, () -> {
            savePieces(connection, board, chessRoomId);
            saveTurn(connection, turn, chessRoomId);
        });
    }

    private void savePieces(Connection connection, Board board, Long chessRoomId) {
        Map<Position, Piece> pieces = board.getBoard();

        pieces.values().stream()
                .filter(piece -> piece.getType() != PieceType.EMPTY)
                .forEach(
                        piece -> pieceDao.addPieceByChessRoomId(connection, changePieceToPieceDto(piece), chessRoomId));
    }

    private void saveTurn(Connection connection, Team turn, Long chessRoomId) {
        chessRoomDao.updateTurnById(connection, turn.getRawTeam(), chessRoomId);
    }

    public void deletePieces(Connection connection, Long chessRoomId) {
        pieceDao.deleteAllByChessRoomId(connection, chessRoomId);
    }

    private PieceDto changePieceToPieceDto(Piece piece) {
        Position position = piece.getPosition();
        PieceType pieceType = piece.getType();
        Team team = piece.getTeam();

        return new PieceDto(
                createRawPosition(position),
                pieceType.getRawPieceType(),
                team.getRawTeam()
        );
    }

    private Piece changePieceDtoToPiece(PieceDto pieceDto) {
        Position position = Position.of(pieceDto.position());
        Team team = Team.valueOf(pieceDto.team());
        PieceInfo pieceInfo = new PieceInfo(position, team);

        PieceMaker pieceMaker = PieceMaker.valueOf(pieceDto.type());
        return pieceMaker.createPiece(pieceInfo);
    }

    private String createRawPosition(Position position) {
        return position.getRawFile() + position.getRawRank();
    }
}
