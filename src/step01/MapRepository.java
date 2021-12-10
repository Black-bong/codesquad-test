package step01;

import java.util.*;

public class MapRepository {
    private static final MapRepository instance = new MapRepository();
    private static final Map<Integer, SokobanMap> maps = new HashMap<>();
    private int sequence = 0;

    public void save(SokobanMap mapData) {
        maps.put(sequence, mapData);
        mapData.setMapID(sequence);
        sequence++;
    }

    public void printerMap(int stage) {
        System.out.println("Stage " + (maps.get(stage).getMapID() + 1));
        System.out.println();
        maps.get(stage).setValue();
        maps.get(stage).printSokobanMap();
    }

    public void printMap(int stage) {
        System.out.println("Stage " + (maps.get(stage).getMapID() + 1));
        System.out.println();
        maps.get(stage).setValue();
        maps.get(stage).printSokobanMap();
        clearGame(stage);
        maps.get(stage).count();
        System.out.println();
    }

    private void clearGame(int stage) {
        if (maps.get(stage).gameClear()) {
            System.out.println("빠밤! Stage " + (stage + 1) + "클리어!");
            maps.get(stage).count();
            System.out.println();
            int nextStage = stage + 1;
            CommandController commandController = new CommandController();
            try {
                maps.get(nextStage).printSokobanMap();
                maps.get(nextStage).setValue();
            } catch (NullPointerException e) {
                System.out.println("전체 게임을 클리어하셨습니다!");
                System.out.println("축하드립니다!");
                System.exit(0);
            }
            commandController.saveCommand(nextStage);
        }
    }

    public static MapRepository getInstance() {
        return instance;
    }

    public void moveRight(int stage) {
        int playerX = maps.get(stage).getPlayerX() - 1;
        int playerY = maps.get(stage).getPlayerY() - 1;
        try {
            isRightObstacle(stage, playerX, playerY);
            printMap(stage);
        } catch (StringIndexOutOfBoundsException e) {
            printMap(stage);
        }
        System.out.println();
    }

    private void isRightObstacle(int stage, int playerX, int playerY) {
        char firstChar = maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX + 1);
        char secondChar = maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX + 2);
        if (firstChar == '#') {
            throw new StringIndexOutOfBoundsException();
        }
        if (firstChar == 'o' && secondChar != '#' && secondChar != 'o') {
            moveRightBall(stage, playerX, playerY);
        }
        if (firstChar == ' ') {
            StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
            newString.setCharAt(playerX, ' ');
            newString.setCharAt(playerX + 1, 'P');
            maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(newString));
        }
        if (firstChar == '0' && secondChar != '#') {
            StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
            newString.setCharAt(playerX, ' ');
            newString.setCharAt(playerX + 1, 'P');
            newString.setCharAt(playerX + 2, 'o');
            maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(newString));
        }
    }

    private void moveRightBall(int stage, int playerX, int playerY) {
        StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
        if (maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX + 2) == 'O') {
            newString.setCharAt(playerX + 2, '0');
        }
        if (maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX + 1) == 'o'
                && maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX + 2) != 'O') {
            newString.setCharAt(playerX + 2, 'o');
        }
        newString.setCharAt(playerX, ' ');
        newString.setCharAt(playerX + 1, 'P');
        maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(newString));
    }

    public void moveLeft(int stage) {
        int playerX = maps.get(stage).getPlayerX() - 1;
        int playerY = maps.get(stage).getPlayerY() - 1;
        try {
            isLeftObstacle(stage, playerX, playerY);
            printMap(stage);
        } catch (StringIndexOutOfBoundsException e) {
            printMap(stage);
        }
        System.out.println();
    }

    private void isLeftObstacle(int stage, int playerX, int playerY) {
        char firstChar = maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX - 1);
        char secondChar = maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX - 2);
        if (firstChar == '#') {
            throw new StringIndexOutOfBoundsException();
        }
        if (firstChar == 'o' && secondChar != '#' && secondChar != 'o') {
            moveLeftBall(stage, playerX, playerY);
        }
        if (firstChar == ' ') {
            StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
            newString.setCharAt(playerX, ' ');
            newString.setCharAt(playerX - 1, 'P');
            maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(newString));
        }
        if (firstChar == '0' && secondChar != '#') {
            StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
            newString.setCharAt(playerX, ' ');
            newString.setCharAt(playerX - 1, 'P');
            newString.setCharAt(playerX - 2, 'o');
            maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(newString));
        }
    }

    private void moveLeftBall(int stage, int playerX, int playerY) {
        StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
        if (maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX - 2) == 'O') {
            newString.setCharAt(playerX - 2, '0');
        }
        if (maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX - 1) == 'o'
                && maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX - 2) != 'O') {
            newString.setCharAt(playerX - 2, 'o');
        }
        newString.setCharAt(playerX, ' ');
        newString.setCharAt(playerX - 1, 'P');
        maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(newString));
    }

    public void moveDown(int stage) {
        int playerX = maps.get(stage).getPlayerX() - 1;
        int playerY = maps.get(stage).getPlayerY() - 1;
        char firstChar = maps.get(stage).getSokobanMaps().get(playerY + 1).get(0).charAt(playerX);
        try {
            if (firstChar == '#') {
                throw new CanNotMoveException();
            }
            if (firstChar == 'o') {
                moveDownBall(stage, playerX, playerY);
            }
            StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY + 1).get(0));
            StringBuilder oldString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
            newString.setCharAt(playerX, 'P');
            oldString.setCharAt(playerX, ' ');
            maps.get(stage).getSokobanMaps().get(playerY + 1).set(0, String.valueOf(newString));
            maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(oldString));
            printMap(stage);
        } catch (CanNotMoveException e) {
            printMap(stage);
        }
        System.out.println();
    }

    private void moveDownBall(int stage, int playerX, int playerY) {
        StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY + 2).get(0));
        if (maps.get(stage).getSokobanMaps().get(playerY + 2).get(0).charAt(playerX) == 'O') {
            newString.setCharAt(playerX, '0');
        }
        if (maps.get(stage).getSokobanMaps().get(playerY + 1).get(0).charAt(playerX) == 'o'
                && maps.get(stage).getSokobanMaps().get(playerY + 2).get(0).charAt(playerX) != 'O') {
            newString.setCharAt(playerX, 'o');
        }
        maps.get(stage).getSokobanMaps().get(playerY + 2).set(0, String.valueOf(newString));
    }

    public void moveUp(int stage) {
        int playerX = maps.get(stage).getPlayerX() - 1;
        int playerY = maps.get(stage).getPlayerY() - 1;
        try {
            char firstChar = maps.get(stage).getSokobanMaps().get(playerY - 1).get(0).charAt(playerX);
            char secondChar = maps.get(stage).getSokobanMaps().get(playerY - 2).get(0).charAt(playerX);
            if (firstChar == '#') {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (firstChar == 'o' && secondChar != '#' && secondChar != 'o') {
                moveUpBall(stage, playerX, playerY);
            }
            StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY - 1).get(0));
            StringBuilder oldString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
            newString.setCharAt(playerX, 'P');
            oldString.setCharAt(playerX, ' ');
            maps.get(stage).getSokobanMaps().get(playerY - 1).set(0, String.valueOf(newString));
            maps.get(stage).getSokobanMaps().get(playerY).set(0, String.valueOf(oldString));
            printMap(stage);
        } catch (ArrayIndexOutOfBoundsException e) {
            printMap(stage);
        }
        System.out.println();
    }

    private void moveUpBall(int stage, int playerX, int playerY) {
        StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY - 2).get(0));
        if (maps.get(stage).getSokobanMaps().get(playerY - 2).get(0).charAt(playerX) == 'O') {
            newString.setCharAt(playerX, '0');
        }
        if (maps.get(stage).getSokobanMaps().get(playerY - 1).get(0).charAt(playerX) == 'o'
                && maps.get(stage).getSokobanMaps().get(playerY - 2).get(0).charAt(playerX) != 'O') {
            newString.setCharAt(playerX, 'o');
        }
        maps.get(stage).getSokobanMaps().get(playerY - 2).set(0, String.valueOf(newString));
    }

    public boolean stageCount(int stage) {
        return stage == (maps.size());
    }

    public void resetStage(int stage) {

    }
}