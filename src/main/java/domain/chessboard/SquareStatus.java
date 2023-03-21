package domain.chessboard;

import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;

public interface SquareStatus {

    Type getType();

    Color getColor();

    Route findRoute(MovePosition movePosition);

}
