package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardDto {
    private final Map<String, PieceDto> chessBoard;

    public ChessBoardDto() {
        chessBoard = new LinkedHashMap<>();
    }

    public ChessBoardDto(Map<Position, Piece> original) {
        chessBoard = new LinkedHashMap<>();
        for (Map.Entry<Position, Piece> piece : original.entrySet()) {
            System.out.println(piece.getKey() + "," + piece.getValue());
            chessBoard.put(piece.getKey().position(), new PieceDto(piece.getValue()));
        }
    }

    public void add(String position, PieceDto pieceDto) {
        chessBoard.put(position, pieceDto);
    }

    public Map<String, PieceDto> getChessBoard() {
        return chessBoard;
    }

}