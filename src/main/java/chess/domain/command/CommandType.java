package chess.domain.command;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    LOAD("load"),
    MOVE("move"),
    STATUS("status"),
    SAVE("save"),
    END("end");

    private final String commandType;

    CommandType(String commandType) {
        this.commandType = commandType;
    }

    public String get() {
        return commandType;
    }

    public static CommandType getCommand(String command) {
        return Arrays.stream(CommandType.values())
                .filter(value -> value.get().equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 커맨드 타입입니다."));
    }
}
