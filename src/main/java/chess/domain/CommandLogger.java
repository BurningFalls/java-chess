package chess.domain;

import static chess.domain.CommandType.END;
import static chess.domain.CommandType.START;
import static chess.domain.Team.WHITE;
import static chess.domain.Team.takeTurn;

import java.util.ArrayList;
import java.util.List;

public class CommandLogger {
    private final List<Command> commandLog;
    private Team turn;

    public CommandLogger() {
        this.commandLog = new ArrayList<>();
        this.turn = WHITE;
    }

    public void addLog(Command command) {
        CommandType commandType = command.getCommandType();
        validateCorrectOrder(commandType);

        commandLog.add(command);
    }

    private void validateCorrectOrder(CommandType commandType) {
        validateFirstCommandStart(commandType);
        validateGameAlreadyStart(commandType);
    }

    private void validateFirstCommandStart(CommandType commandType) {
        if (commandLog.isEmpty() && commandType != START) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private void validateGameAlreadyStart(CommandType commandType) {
        if (!commandLog.isEmpty() && commandType == START) {
            throw new IllegalArgumentException("이미 게임이 진행 중입니다.");
        }
    }

    public Team whoTurn() {
        return turn;
    }

    public void changeTurn() {
        turn = takeTurn(turn);
    }

    public boolean isCommandEnd() {
        if (commandLog.isEmpty()) {
            return false;
        }
        return getLastCommandType() == END;
    }

    private CommandType getLastCommandType() {
        if (commandLog.isEmpty()) {
            throw new IllegalArgumentException("로그가 비어있습니다.");
        }

        Command lastCommand = commandLog.get(commandLog.size() - 1);
        return lastCommand.getCommandType();
    }
}
