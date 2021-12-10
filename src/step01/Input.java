package step01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Input {

    public List<String> inputString() throws IOException {
        List<String> commandList = new ArrayList<>();
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String command = buf.readLine().toLowerCase();
        splitString(command, commandList);
        return commandList;
    }

    private void splitString(String command, List<String> commandList) {
        for (int i = 0; i < command.length(); i++) {
            commandList.add(String.valueOf(command.charAt(i)));
        }
    }
}