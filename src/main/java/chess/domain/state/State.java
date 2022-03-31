package chess.domain.state;

import java.util.List;

public interface State {

    boolean isStart();

    boolean isEnd();

    State run(List<String> inputs);
}