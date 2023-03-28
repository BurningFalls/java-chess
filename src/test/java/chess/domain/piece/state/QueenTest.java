package chess.domain.piece.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.chessboard.Coordinate;
import chess.domain.piece.Empty;
import chess.domain.piece.PieceState;
import chess.domain.piece.Team;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class QueenTest {

    private static final String QUEEN_ERROR_MESSAGE = "Queen(은)는 해당 좌표로 이동할 수 없습니다.";

    @Test
    void 퀸은_같은_파일의_좌표로_움직일_수_있다() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate a3 = Coordinate.from("a3");

        //when & then
        assertThat(queen.findRoute(a1, a3)).containsExactly(Coordinate.from("a2"), Coordinate.from("a3"));
    }


    @Test
    void 퀸은_같은_랭크의_좌표로_움직일_수_있다() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate e1 = Coordinate.from("e1");

        //when & then
        assertThat(queen.findRoute(a1, e1)).containsExactly(Coordinate.from("b1"), Coordinate.from("c1")
                , Coordinate.from("d1"), Coordinate.from("e1"));
    }

    @Test
    void 퀸이_갈_수_없는_좌표이면_예외가_발생한다() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate b3 = Coordinate.from("b3");

        //when & then
        assertThatThrownBy(() -> queen.findRoute(a1, b3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(QUEEN_ERROR_MESSAGE);
    }

    @Test
    void 퀸은_우상향_대각선의_좌표로_움직일_수_있다() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        final Coordinate a1 = Coordinate.from("a1");
        final Coordinate c3 = Coordinate.from("c3");

        //when & then
        assertThat(queen.findRoute(a1, c3)).containsExactly(Coordinate.from("b2"), Coordinate.from("c3"));
    }


    @Test
    void 퀸은_좌상향_대각선의_좌표로_움직일_수_있다() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        final Coordinate c3 = Coordinate.from("c3");
        final Coordinate e1 = Coordinate.from("e1");

        //when & then
        assertThat(queen.findRoute(c3, e1)).containsExactly(Coordinate.from("d2"), Coordinate.from("e1"));
    }

    @Test
    void 퀸은_도착지_스퀘어에_같은_팀이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final Queen queen = new Queen(team);
        final List<PieceState> route = List.of(new Empty(), new Empty(), new Pawn(team));

        //when & then
        assertThatThrownBy(() -> queen.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(QUEEN_ERROR_MESSAGE);
    }

    @Test
    void 퀸은_도착지를_가는_중간에_다른_기물이_있으면_갈_수_없다는_예외가_발생() {
        //given
        final Team team = Team.BLACK;
        final Queen queen = new Queen(team);
        final List<PieceState> route = List.of(new Empty(), new Pawn(team), new Empty());

        //when & then
        assertThatThrownBy(() -> queen.validateRoute(route))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(QUEEN_ERROR_MESSAGE);
    }

    @Test
    void 퀸은_도착지가_비어있으면_예외가_발생하지_않는다() {
        //given
        final Team team = Team.BLACK;
        final Queen queen = new Queen(team);
        final List<PieceState> route = List.of(new Empty(), new Empty(), new Empty());

        //when & then
        assertDoesNotThrow(() -> queen.validateRoute(route));
    }

    @Test
    void 퀸은_도착지에_다른팀의_기물이_있으면_예외가_발생하지_않는다() {
        //given
        final Queen queen = new Queen(Team.BLACK);
        final List<PieceState> route = List.of(new Empty(), new Empty(),
                new Pawn(Team.WHITE));

        //when & then
        assertDoesNotThrow(() -> queen.validateRoute(route));
    }
}