package step01;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandController extends MapRepository {
    public void saveCommand(int stage) {
        inputCommand(stage);
    }

    public void inputCommand(int stage) {
        try {
            Map<Integer, Runnable> controllerList = new HashMap<>();
            createCommendController(controllerList, stage);
            Input input = new Input();
            List<String> commands;
            while (!stageCount(stage)) {
                System.out.println();
                System.out.print("SOKOBAN> ");
                commands = input.inputString();
                System.out.println();
                readCommend(commands, controllerList);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createCommendController(Map<Integer, Runnable> controllerList, int stage) {
        controllerList.put(0, () -> moveUp(stage));
        controllerList.put(1, () -> moveDown(stage));
        controllerList.put(2, () -> moveLeft(stage));
        controllerList.put(3, () -> moveRight(stage));
        controllerList.put(4, this::gameExit);
        controllerList.put(5, () -> resetStage(stage));
    }

    private void gameExit() {
        System.out.println("Bye~");
        System.exit(0);
    }

    private void readCommend(List<String> commands, Map<Integer, Runnable> controllerList) {
        for (String command : commands) {
            try {
                controllerList.get(Command.getCommandID(command)).run();
            } catch (IllegalArgumentException e) {
                System.out.println("잘못된입력입니다.");
            }
        }
    }
}