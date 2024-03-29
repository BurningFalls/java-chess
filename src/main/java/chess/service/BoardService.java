package chess.service;

import chess.DatabaseConnection;
import chess.domain.board.Board;
import chess.domain.board.BoardDatabaseInitializer;
import chess.domain.dao.BoardDao;
import chess.domain.dto.PieceDto;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMaker;
import chess.domain.piece.PieceType;
import chess.domain.pieceinfo.PieceInfo;
import chess.domain.pieceinfo.Position;
import chess.domain.pieceinfo.Team;
import chess.domain.strategy.EmptyMoveStrategy;
import chess.domain.strategy.MoveStrategy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardService {
    private static final String DATABASE_NAME = "chess";
    private static final int BOARD_SIZE = 8;
    private static final MoveStrategy EMPTY_MOVE_STRATEGY = new EmptyMoveStrategy();
    private static final long CHESS_ROOM_ID = 1;

    private final BoardDao boardDao;

    public BoardService() {
        this.boardDao = new BoardDao(DatabaseConnection.getConnection(DATABASE_NAME));
    }

    public void initializeBoard() {
        boardDao.removeAll();
        BoardDatabaseInitializer.initialize(boardDao);
    }

    public Board loadData() {
        List<PieceDto> pieceDtos = boardDao.findAll();

        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceDto pieceDto : pieceDtos) {
            Piece piece = changePieceDtoToPiece(pieceDto);
            Position position = piece.getPosition();

            pieces.put(position, piece);
        }
        fillEmptyPieces(pieces);

        return new Board(pieces);
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

    public void saveData(Board board) {
        Map<Position, Piece> pieces = board.getBoard();

        pieces.values().stream()
                .filter(piece -> piece.getType() != PieceType.EMPTY)
                .forEach(piece -> boardDao.addPiece(changePieceToPieceDto(piece)));
    }

    private PieceDto changePieceToPieceDto(Piece piece) {
        Position position = piece.getPosition();
        PieceType pieceType = piece.getType();
        Team team = piece.getTeam();

        return new PieceDto(
                CHESS_ROOM_ID,
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
