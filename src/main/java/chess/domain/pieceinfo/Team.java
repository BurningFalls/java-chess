package chess.domain.pieceinfo;

public enum Team {
    BLACK, WHITE, NONE;

    public static Team takeTurn(Team team) {
        if (team == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
