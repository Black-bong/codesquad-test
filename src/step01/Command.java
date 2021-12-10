package step01;

public enum Command {
    UP("w", 0),
    DOWN("s", 1),
    LEFT("a", 2),
    RIGHT("d", 3),
    EXIT("q", 4),
    RESET_STAGE("r", 5);

    private final String inputCommand;
    private final int commandID;

    Command(String inputCommand, int commandID) {
        this.inputCommand = inputCommand;
        this.commandID = commandID;
    }

    public boolean isSameCommand(String inputCommand) {
        return this.inputCommand.equals(inputCommand);
    }

    public static int getCommandID(String command) {
        for (Command i : Command.values()) {
            if (i.isSameCommand(command)) {
                return i.commandID;
            }
        }
        throw new IllegalArgumentException();
    }
}