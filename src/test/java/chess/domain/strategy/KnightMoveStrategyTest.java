package chess.domain.strategy;

import chess.domain.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class KnightMoveStrategyTest {

    private static Stream<Arguments> knightCanMoveTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("a2"), Position.of("c3"), true),
                Arguments.of(Position.of("a2"), Position.of("b4"), true),
                Arguments.of(Position.of("a2"), Position.of("b3"), false)
        );
    }

    @DisplayName("나이트의 이동 전략은 한 번에 수직으로 2칸과 수평으로 1칸 혹은 수평으로 2칸과 수직으로 1칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("knightCanMoveTestParameters")
    void knightCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        KnightMoveStrategy knightMoveStrategy = new KnightMoveStrategy();
        boolean actualCanMove = knightMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}
