package step01;

import java.util.List;

public class SokobanMap {
    private final List<List<String>> sokobanMaps;
    private final List<List<String>> saveMap;
    private int mapID;
    private int playerX;
    private int playerY;
    private int count = 0;

    public SokobanMap(List<List<String>> sokobanMap) {
        this.sokobanMaps = sokobanMap;
        this.saveMap = sokobanMap;
    }

    public List<List<String>> getSokobanMaps() {
        return sokobanMaps;
    }

    public void setMapID(int mapID) {
        this.mapID = mapID;
    }

    public int getMapID() {
        return mapID;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setValue() {
        for (int i = 0; i < sokobanMaps.size(); i++) {
            getValue(i);
        }
    }

    private void getValue(int i) {
        for (int k = 0; k < sokobanMaps.get(i).get(0).length(); k++) {
            if (sokobanMaps.get(i).get(0).charAt(k) == 'P') {
                playerX = k + 1;
                playerY = i + 1;
            }
        }
    }

    public void printSokobanMap() {
        for (List<String> sokobanMap : sokobanMaps) {
            for (String s : sokobanMap) {
                System.out.println(s);
            }
        }
        System.out.println();
    }

    public void count() {
        count++;
        System.out.println("턴수:" + count);
    }

    public boolean gameClear() {
        for (int i = 0; i < sokobanMaps.size(); i++) {
            if (gameClearCheck(i)) return false;
        }
        return true;
    }

    private boolean gameClearCheck(int i) {
        for (int j = 0; j < sokobanMaps.get(i).size(); j++) {
            if (sokobanMaps.get(i).get(j).contains("O")) {
                return true;
            }
        }
        return false;
    }

    public void clearCount() {
        this.count = 0;
    }

    public List<List<String>> resetStage() {
        System.out.println((getMapID() + 1) + " Stage초기화");
        return saveMap;
    }
}