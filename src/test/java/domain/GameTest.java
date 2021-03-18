package domain;

import domain.piece.Color;
import domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void display() {
        game.display();
    }

    @Test
    void validatePieceToMove() {
        Player whitePlayer = new Player(Color.WHITE);
        assertThatCode(() -> game.pickStartPiece(whitePlayer, Position.from("a2")))
                .doesNotThrowAnyException();
    }

    @Test
    void validatePieceToMoveError() {
        Player whitePlayer = new Player(Color.WHITE);
        assertThatThrownBy(() -> game.pickStartPiece(whitePlayer, Position.from("a7")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void pickEndPiece() {
        Player whitePlayer = new Player(Color.WHITE);
        assertThatCode(() -> game.action(whitePlayer, Position.from("a2"), Position.from("a4")))
                .doesNotThrowAnyException();
    }
}