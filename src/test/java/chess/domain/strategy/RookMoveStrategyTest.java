package chess.domain.strategy;

import chess.domain.Position;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RookMoveStrategyTest {

    private static Stream<Arguments> rookMoveStrategyTestParameters() {
        return Stream.of(
                Arguments.of(Position.of("d4"), Position.of("d1"), true),
                Arguments.of(Position.of("d4"), Position.of("a4"), true),
                Arguments.of(Position.of("d4"), Position.of("d8"), true),
                Arguments.of(Position.of("d4"), Position.of("h4"), true),
                Arguments.of(Position.of("d4"), Position.of("b3"), false)
        );
    }

    @DisplayName("룩의 이동 전략은 한 번에 수직 혹은 수평으로 여러 칸 이동 가능하다.")
    @ParameterizedTest
    @MethodSource("rookMoveStrategyTestParameters")
    void rookCanMoveTest(Position currentPosition, Position newPosition, boolean expectedCanMove) {
        RookMoveStrategy rookMoveStrategy = new RookMoveStrategy();
        boolean actualCanMove = rookMoveStrategy.canMove(currentPosition, newPosition);

        Assertions.assertThat(actualCanMove).isEqualTo(expectedCanMove);
    }
}