package chess.domain.piece;

import chess.dto.PieceInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceInfoTest {

    @Test
    @DisplayName("검은색 폰의 형식 가져오기")
    void getBlackPawnSymbol() {
        assertThat(PieceInfo.getSymbol(new Pawn(Color.BLACK))).isEqualTo("♟");
    }

    @Test
    @DisplayName("흰색 폰의 형식 가져오기")
    void getWhitePawnSymbol() {
        assertThat(PieceInfo.getSymbol(new Pawn(Color.WHITE))).isEqualTo("♙");
    }

    @Test
    @DisplayName("검은색 룩의 형식 가져오기")
    void getBlackRookSymbol() {
        assertThat(PieceInfo.getSymbol(new Rook(Color.BLACK))).isEqualTo("♜");
    }

    @Test
    @DisplayName("흰색 룩의 형식 가져오기")
    void getWhiteRookSymbol() {
        assertThat(PieceInfo.getSymbol(new Rook(Color.WHITE))).isEqualTo("♖");
    }

    @Test
    @DisplayName("검은색 나이트의 형식 가져오기")
    void getBlackKnightSymbol() {
        assertThat(PieceInfo.getSymbol(new Knight(Color.BLACK))).isEqualTo("♞");
    }

    @Test
    @DisplayName("흰색 나이트의 형식 가져오기")
    void getWhiteKnightSymbol() {
        assertThat(PieceInfo.getSymbol(new Knight(Color.WHITE))).isEqualTo("♘");
    }

    @Test
    @DisplayName("검은색 비숍의 형식 가져오기")
    void getBlackBishopSymbol() {
        assertThat(PieceInfo.getSymbol(new Bishop(Color.BLACK))).isEqualTo("♝");
    }

    @Test
    @DisplayName("흰색 비숍의 형식 가져오기")
    void getWhiteBishopSymbol() {
        assertThat(PieceInfo.getSymbol(new Bishop(Color.WHITE))).isEqualTo("♗");
    }

    @Test
    @DisplayName("검은색 퀸의 형식 가져오기")
    void getBlackQueenSymbol() {
        assertThat(PieceInfo.getSymbol(new Queen(Color.BLACK))).isEqualTo("♛");
    }

    @Test
    @DisplayName("흰색 퀸의 형식 가져오기")
    void getWhiteQueenSymbol() {
        assertThat(PieceInfo.getSymbol(new Queen(Color.WHITE))).isEqualTo("♕");
    }

    @Test
    @DisplayName("검은색 킹의 형식 가져오기")
    void getBlackKingSymbol() {
        assertThat(PieceInfo.getSymbol(new King(Color.BLACK))).isEqualTo("♚");
    }

    @Test
    @DisplayName("흰색 킹의 형식 가져오기")
    void getWhiteKingSymbol() {
        assertThat(PieceInfo.getSymbol(new King(Color.WHITE))).isEqualTo("♔");
    }

    @Test
    @DisplayName("기물이 없는 경우")
    void getNullSymbol() {
        assertThat(PieceInfo.getSymbol(null)).isEqualTo(".");
    }
}