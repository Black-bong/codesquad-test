package step01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MapController extends MapRepository {

    MapRepository mapRepository = MapRepository.getInstance();

    public void readText() {
        String filePath = "src/step01/map.txt";
        List<List<String>> mapData = new ArrayList<>();
        try {
            BufferedReader buf = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = buf.readLine()) != null) {
                String[] row = line.split("/r");
                List<String> replaceRow = Arrays.stream(row)
                        .map(s -> s.replace("#", "0"))
                        .map(s -> s.replace("O", "1"))
                        .map(s -> s.replace("o", "2"))
                        .map(s -> s.replace("P", "3"))
                        .map(s -> s.replace("=", "4"))
                        .collect(Collectors.toList());
                mapData.add(replaceRow);
            }
            saveMapData(mapData);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveMapData(List<List<String>> mapData) throws IOException {
        List<List<String>> saveMap = new ArrayList<>();
        for (List<String> mapDatum : mapData) {
            mapDatum = mapDatum.stream()
                    .map(s -> s.replace("0", "#"))
                    .map(s -> s.replace("1", "O"))
                    .map(s -> s.replace("2", "o"))
                    .map(s -> s.replace("3", "P"))
                    .map(s -> s.replace("4", "="))
                    .collect(Collectors.toList());
            for (int j = 0; j < mapDatum.size(); j++) {
                saveMap = getData(saveMap, mapDatum);
            }
        }
        SokobanMap sokobanMap = new SokobanMap(saveMap);
        mapRepository.save(sokobanMap);
        PlaySokoban playSokoban = new PlaySokoban();
        playSokoban.start();
    }

    private List<List<String>> getData(List<List<String>> saveMap, List<String> mapDatum) {
        for (int j = 0; j < mapDatum.size(); j++) {
            if (mapDatum.get(j).contains("S")) {
                continue;
            }
            if (mapDatum.get(j).contains("=")) {
                SokobanMap sokobanMap = new SokobanMap(saveMap);
                mapRepository.save(sokobanMap);
                saveMap = new ArrayList<>();
                continue;
            }
            List<String> col = mapDatum;
            saveMap.add(col);
        }
        return saveMap;
    }
}