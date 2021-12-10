package step01;

public class PlaySokoban {

    public void start() {
        CommandController cc = new CommandController();
        System.out.println("소코반의 세계에 오신 것을 환영합니다!");
        System.out.println("^오^");
        System.out.println();
        cc.printerMap(0);
        cc.saveCommand(0);
    }
}