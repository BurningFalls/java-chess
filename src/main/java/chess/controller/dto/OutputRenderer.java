package chess.controller.dto;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputRenderer {
    private static final Map<PieceType, String> PIECE_TO_STRING = new EnumMap<>(PieceType.class);

    static {
        PIECE_TO_STRING.put(PieceType.EMPTY, ".");
        PIECE_TO_STRING.put(PieceType.KING, "K");
        PIECE_TO_STRING.put(PieceType.QUEEN, "Q");
        PIECE_TO_STRING.put(PieceType.ROOK, "R");
        PIECE_TO_STRING.put(PieceType.KNIGHT, "N");
        PIECE_TO_STRING.put(PieceType.BISHOP, "B");
        PIECE_TO_STRING.put(PieceType.PAWN, "P");
    }

    public static BoardDto toBoardDto(final List<List<Piece>> board) {
        List<List<String>> boardDto = stringifyPieces(board);
        return new BoardDto(boardDto);
    }

    private static List<List<String>> stringifyPieces(final List<List<Piece>> board) {
        List<List<String>> boardDto = new ArrayList<>();
        for (List<Piece> line : board) {
            boardDto.add(stringifyLine(line));
        }
        return boardDto;
    }

    private static List<String> stringifyLine(final List<Piece> line) {
        return line.stream()
                .map(OutputRenderer::stringifySign)
                .collect(Collectors.toList());
    }

    private static String stringifySign(final Piece piece) {
        String sign = PIECE_TO_STRING.get(piece.getPieceType());
        if (piece.getTeam() == Team.WHITE) {
            sign = sign.toLowerCase();
        }
        return sign;
    }
}