# 구현과정 상세 설명
## 1단계
 - gist revision 번호
   - 17
 - git commit log revision 번호
   - commit 750b8aa09071d6487e3de8f64cf7f2c069e5843c
 
### 실행
- main메서드가 위치한 클래스 명
  - PrintMapData클래스

### 1단계 코딩 요구사항
- [X] 컴파일 또는 실행이 가능해야 한다. (컴파일이나 실행되지 않을 경우 감점 대상)
- [X] 자기만의 기준으로 최대한 간결하게 코드를 작성한다.
- [X] Readme.md에 풀이 과정 및 코드 설명, 실행 결과를 기술하고 코드와 같이 gist에 포함해야 한다.
- [X] 제출시 gist URL과 revision 번호를 함께 제출한다.

<details>
<summary>1단계 문제</summary>
<div markdown="1">

### 1.내용을 문자열로 넘겨서 처리하는 함수를 작성한다.
```
Stage 1
#####
#OoP#
#####
=====
Stage 2
  #######
###  O  ###
#    o    #
# Oo P oO #
###  o  ###
 #   O  # 
 ########
```
### 2.위 값을 읽어 2차원 배열로 변환 저장한다.
|기호|의미|저장값|
|------|----|------|
|#|벽(Wall)|0|
|O|구멍(Hall)|1|
|o|공(Ball)|2|
|P|플레이어(Player)|3|
|=|스테이지 구분|4|
 
### 3.출력할 내용
아래와 같은 형태로 각 스테이지 정보를 출력한다.
- 플레이어 위치는 배열 [0][0]을 기준으로 처리한다
  - 아래 출력 예시와 상관없이 기준에 맞춰서 얼마나 떨어진지 표시하면 된다
- 스테이지 구분값은 출력하지 않는다
 
```java
Stage 1

#####
#OoP#
#####

가로크기: 5
세로크기: 3
구멍의 수: 1
공의 수: 1
플레이어 위치 (2, 4)

Stage 2

  #######
###  O  ###
#    o    #
# Oo P oO #
###  o  ###
 #   O  # 
 ########

가로크기: 11
세로크기: 7
구멍의 수: 4
공의 수: 4
플레이어 위치 (4, 6)
```
</div>
</details>



<details>
<summary>1단계 구현과정</summary>
<div markdown="1">

### 1.내용을 문자열로 넘겨서 처리하는 함수를 작성한다.
 - [X] map.txt 파일에 저장되어 있는 내용을 문자열로 받는다.
 - [X] Stage의 S와 스테이지 구분 = 를 기준으로 여러 스테이지가 하나의 파일에 있어도 구분 가능
### 2.값을 읽어 2차원 배열로 변환 저장한다.
 - [X] 문자열로 받은 내용을 2차원 배열로 저장한다.
   - 2차원 배열 형태로 저장을 하려다가, 맵마다의 크기가 다른데 그럴때마다 배열을 선언해야 하는 번거로움이 있어 이중리스트로 저장
### 3.아래와 같은 형태로 각 스테이지 정보를 출력한다.
 - [X] 각 위치의 값을 받고, 비교하여 각 스테이지 정보를 출력

</div>
</details>

<details>
<summary>1단계 상세설명</summary>
<div markdown="1">

## 목차
1. [PrintMapData클래스](#PrintMapData클래스)
2. [SokobanMap클래스](#SokobanMap클래스)
3. [MapRepository클래스](#MapRepository클래스)
4. [MapController클래스](#MapController클래스)

## PrintMapData클래스
|메소드명|기능|
|------|----|
|[main](#main메소드)|프로그램의 실행|

## SokobanMap클래스
- 맵이 생성될 때 사용되며, 맵의 정보를 가지고 있다.

|메소드명|기능|
|------|----|
|[getValue](#getValue메소드)| 위치에 따른 map 정보를 가져온다. |
|[printSokobanMap](#printSokobanMap메소드)| 맵을 출력해준다. |

### getValue메소드
```java
private void getValue(int i) {
     for (int k = 0; k < sokobanMaps.get(i).get(0).length(); k++) {
         if (sokobanMaps.get(i).get(0).charAt(k) == 'O') {
             hallCount += 1;
         }
         if (sokobanMaps.get(i).get(0).charAt(k) == 'o') {
             ballCount += 1;
         }
         if (sokobanMaps.get(i).get(0).length() > width) {
             width = sokobanMaps.get(i).get(0).length();
         }
         if (sokobanMaps.get(i).get(0).charAt(k) == 'P') {
             playerX = k + 1;
             playerY = i + 1;
         }
     }
 }
```
- 맵에 위치한 정보들의 위치값을 비교하여 값을 저장한다.
### printSokobanMap메소드
```java
public void printSokobanMap() {
     for (int i = 0; i < sokobanMaps.size(); i++) {
        for (int j = 0; j < sokobanMaps.get(i).size(); j++) {
            System.out.println(sokobanMaps.get(i).get(j));
        }
     }
     System.out.println();
 }
```
- 맵을 2중 for문을 사용하여 출력해 준다.

## MapRepository클래스
- 앞으로의 단계에서 사용된 맵을 저장하는 클래스

|메소드명| 기능            |
|------|---------------|
|[save](#save메소드)| 생성된 맵을 저장하는 기능|
|[printValue](#printValue메소드)| 저장되어 있는 각각의 맵의 값을 출력해준다. |
|[printMap](#printMap메소드)| 저장되어 있는 각각의 맵을 출력해준다. |

### save메소드
```java
private static final Map<Integer, SokobanMap> maps = new HashMap<>();
public void save(SokobanMap mapData) {
     maps.put(sequence, mapData);
     mapData.setMapID(sequence);
     sequence++;
 }
```
- 생성된 맵을 key와 value값으로 저장하여, 추후에 필요할때 사용할 수 있게 만들었다.

### printValue메소드
```java
public void printMap() {
     for (SokobanMap value : maps.values()) {
        System.out.println("Stage " + value.getMapID());
        value.printSokobanMap();
    }
 }
```
- 저장되어 있는 각각의 맵을 출력해준다.
### printMap메소드
```java
public void printMap() {
     for (SokobanMap value : maps.values()) {
        System.out.println("Stage " + value.getMapID());
        value.printSokobanMap();
    }
 }
```
- 저장되어 있는 각각의 맵의 값(정보)를 출력해준다.

## MapController클래스
- 맵을 불러오고 저장하는 역할

|메소드명| 기능                  |
|------|---------------------|
|[readText](#readText메소드)| txt파일에서 데이터를 읽어 오는 기능 |
|[saveMapData](#saveMapData메소드)| sokobanMap맵을 생성하고 저장하는 기능 |
|[getData](#getData메소드)| txt파일에 저장되어있는 stage를 분리해주는 기능 |

### readText메소드
```java
public void readText() {
    String filePath = "map.txt";
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
    } catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
 }
```
- map.txt파일을 읽어서 값을 가져온뒤 저장값으로 변경하여 2차원 list에 값을 저장해준다.

### saveMapData메소드
```java
private void saveMapData(List<List<String>> mapData) {
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
     mapRepository.printValue();
 }
```
- 파일에서 읽어온 값을 받아서 출력 모양으로 변환해 준 다음 저장소에 맵을 저장해준다. 

### getData메소드
```java
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
```
- S와 =(구분값) 을 활용해서 stage를 구분하여 값을 반환해 준다.

</div>
</details>

<details>
<summary>1단계 실행화면</summary>
<div markdown="1">

- Stage 1, 2
   
   ![sokoban – MapController12](https://user-images.githubusercontent.com/78953393/144959065-0fc35600-c78d-461f-8070-e5a7a4f54bb3.png)

- Stage 3, 4

   ![sokoban – MapController34](https://user-images.githubusercontent.com/78953393/144959073-33483774-2210-4ed2-9f79-5bc4fe6e027f.png)

- Stage 5

  ![sokoban – MapController5](https://user-images.githubusercontent.com/78953393/144959081-2f20956e-47ce-40af-bd12-9afa6f978af3.png)

</div>
</details>

## 2단계

- gist revision 번호
   - 28
- git commit log revision 번호
   - commit 8207ca28a59cdf900c260f9fed51332b2d3c74d8

### 실행
- main메서드가 위치한 클래스 명
  - PrintMapData클래스

## 2단계 코딩 요구사항
- [X] 너무 크지 않은 함수 단위로 구현하고 중복된 코드를 줄이도록 노력한다
- [X] 마찬가지로 Readme.md 파일과 작성한 소스 코드를 모두 기존 secret gist에 올려야 한다
- [X] 전역변수의 사용을 자제한다
- [X] 객체 또는 배열을 적절히 활용한다 

<details>
<summary>2단계 문제</summary>
<div markdown="1">

### 1단계 스테이지 2의 지도를 읽고 사용자 입력을 받아서 캐릭터를 움직이게 하는 프로그램을 작성하시오. 

### 입력명령
```
- w: 위쪽
- a: 왼쪽
- s: 아래쪽
- d: 오른쪽
- q: 프로그램 종료
```
### 요구사항
- 처음 시작하면 스테이지 2의 지도를 출력한다.
- 간단한 프롬프트 (예: SOKOBAN> )를 표시해 준다.
- 하나 이상의 문자를 입력받은 경우 순서대로 처리해서 단계별 상태를 출력한다.
- 벽이나 공등 다른 물체에 부딪히면 해당 명령을 수행할 수 없습니다 라는 메시지를 출력하고 플레이어를 움직이지 않는다.
 
### 동작예시
```
Stage 2

  #######
###  O  ###
#    o    #
# Oo P oO #
###  o  ###
 #   O  # 
 ########

SOKOBAN> ddzw (엔터)

  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 D: 오른쪽으로 이동합니다.
 
  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 D: (경고!) 해당 명령을 수행할 수 없습니다!
 
  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 Z: (경고!) 해당 명령을 수행할 수 없습니다!
 
  #######
###  O  ###
#    o    #
# Oo  PoO #
###  o  ###
 #   O  # 
 ########
 
 W: 위로 이동합니다.
 
SOKOBAN> q
Bye~
```
 
</div>
</details>
 
 
<details>
<summary>2단계 구현과정</summary>
<div markdown="1">

### 1.처음 시작하면 스테이지 2의 지도를 출력한다.
- [X] 1단계에서 구현한 저장소를 활용해서 출력
### 2.간단한 프롬프트 (예: `SOKOBAN> `)를 표시해 준다.
- [X] System.out.println을 활용하여 명령을 받는 시점에 반복적으로 표시
### 3.하나 이상의 문자를 입력받은 경우 순서대로 처리해서 단계별 상태를 출력한다.
- [X] 사용자로부터 입력받은 명령어를 순차적으로 처리 할 수 있도록 리스트로 받아서 처리
- [X] 정해진 명령어 이외 다른 명령어가 입력 될 경우 예외 처리
### 4.벽이나 공등 다른 물체에 부딪히면 `해당 명령을 수행할 수 없습니다` 라는 메시지를 출력하고 플레이어를 움직이지 않는다.
- [X] ConNotMoveException이라는 예외 클래스를 생성하여, 다른 물체에 부딪히면 예외가 발생하도록 처리
- [X] 커스텀예외를 생성하여, 예외 상황에서 좀 더 명확한 메시지를 받을 수 있도록 처리
### 5.1단계에서 구현한 클래스와 메소드를 최대한 활용
- [X] 1단계에서 구현했던 클래스와 메소드를 최대한 활용하고자 노력하였다.

</div>
</details>

<details>
<summary>2단계 상세설명</summary>
<div markdown="1">

1. [Input클래스](#Input클래스)
2. [MapRepository클래스](#MapRepository클래스)
3. [CanNotMoveException클래스](#CanNotMoveException클래스)
4. [Command클래스](#Command클래스)
5. [CommandController클래스](#CommandController클래스)

## Input클래스
- 입력받고 처리하는 역할

|메소드명| 기능                        |
|------|---------------------------|
|[inputString메소드](#inputString메소드)| 사용자로 부터 입력받는 기능           |
|[splitString메소드](#splitString메소드)| 연결된 명령어를 하나의 명령어로 분리하는 기능 |

### inputString메소드
```java
public List<String> inputString() throws IOException {
    List<String> commandList = new ArrayList<>();
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    String command = buf.readLine().toLowerCase();
    splitString(command, commandList);
    return commandList;
}
```
- BufferedReader로 명령어를 입력 받는다.

### splitString메소드
```java
private void splitString(String command, List<String> commandList) {
    for (int i = 0; i < command.length(); i++) {
        commandList.add(String.valueOf(command.charAt(i)));
    }
}
```
- 입력받은 명령어를 하나씩 나눠 리스트에 저장해준다.

### MapRepository클래스
- 맵을 저장하는 역할

| 메소드명                            | 기능                           |
|---------------------------------|------------------------------|
| [moveRight메소드](#moveRight메소드) | Player를 오른쪽으로 이동 시킨 후 저장하는 기능|

### moveRight메소드
```java
public void moveRight(int stage) {
    int playerX = maps.get(stage).getPlayerX() - 1;
    int playerY = maps.get(stage).getPlayerY() - 1;
    try {
        if (maps.get(stage).getSokobanMaps().get(playerY).get(0).charAt(playerX + 1) != ' ') {
            throw new CanNotMoveException("D");
        }
        StringBuilder newString = new StringBuilder(maps.get(stage).getSokobanMaps().get(playerY).get(0));
        newString.setCharAt(playerX, ' ');
        newString.setCharAt(playerX + 1, 'P');
        maps.get(stage).getSokobanMaps().get(playerY).remove(0);
        maps.get(stage).getSokobanMaps().get(playerY).add(String.valueOf(newString));
        printMap(stage);
        System.out.println("D: 오른쪽으로 이동합니다.");
    } catch (CanNotMoveException e) {
        printMap(stage);
        System.out.println(e.getMessage());
    }
    System.out.println();
}
```
- 1단계에서 구현했던 Player의 좌표값을 가져와서 변수에 저장한다.
- Player가 이동할 위치에 물체가 있는지 확인 후 물체가 있으면, 예외를 발생시킨다. 없으면 오른쪽으로 움직인다.
- StringBuilder를 사용해서 Player의 기존 위치는 공백으로 이동한 위치는 P로 표시되게 만든다.
- 기존 맵에 있던 데이터를 지우고, Player가 새롭게 이동한 데이터로 맵을 변경한다.

### CanNotMoveException클래스
- 이동불가 예외처리를 위한 역할
```java
public CanNotMoveException(String command) {
    super(command + "(경고!) 해당 명령을 수행할 수 없습니다!");
}
```
- 이동불가 예외 발생 시 입력된 명령어를 매개변수로 받아서 명령 수행불가 문구 출력

### Command클래스
- 명령어를 저장하는 enum타입의 클래스
```java
UP("w", 0),
DOWN("s", 1),
LEFT("a", 2),
RIGHT("d", 3),
EXIT("q", 4);
```
|메소드명| 기능                                                           |
|------|--------------------------------------------------------------|
|[isSameCommand메소드](#isSameCommand메소드)| 입력 받은 명령어가 저장된 명령어와 같은지 구분하는 기능                              |
|[getCommandID메소드](#getCommandID메소드)| 입력 받은 명령어가 저장된 명령어와 동일 할 경우 명령어 ID반환 동일하지 않을 경우 예외를 발생시키는 기능 |

### isSameCommand메소드
```java
public boolean isSameCommand(String inputCommand) {
    return this.inputCommand.equals(inputCommand);
}
```
- 입력받은 명령어가 저장된 명령어와 일치하는지 구분해 준다.

### getCommandID메소드
```java
public static int getCommandID(String command) {
    for (Command i : Command.values()) {
        if (i.isSameCommand(command)) {
            return i.commandID;
        }
    }
    throw new IllegalArgumentException();
}
```
- isSameCommand 메소드의 결과로 반환된 값을 사용하여, true이면 명령어 ID값 반환 false이면 예외를 발생 시킨다.

### CommandController클래스
- 명령어를 처리하는 역할

| 메소드명                                                      | 기능                                  |
|-----------------------------------------------------------|-------------------------------------|
| [createCommendController메소드](#createCommendController메소드) | 명령어에 따라 메소드를 호출하기 위한 컨트롤러를 생성하는 기능  |
| [readCommend메소드](#readCommend메소드)                         | 입력된 명령어를 읽어서 해당 기능을 하는 메소드를 호출하는 기능 |

### createCommendController메소드
```java
private void createCommendController(Map<Integer, Runnable> controllerList, int stage) {
    controllerList.put(0, () -> up(stage)); // w
    controllerList.put(1, () -> down(stage)); // s
    controllerList.put(2, () -> left(stage)); // a
    controllerList.put(3, () -> right(stage)); // d
    controllerList.put(4, this::gameExit); // q
}
```
- if~else문 또는 switch문의 사용을 피하기위해 함수형 인터페이스인 Runnable을 사용
- 명령어의 ID값을 key값으로 실행 메소드를 value값으로 저장되어 있는 Map을 생 

### readCommend메소드
```java
private void readCommend(List<String> commands, Map<Integer, Runnable> controllerList, int stage) {
    for (String command : commands) {
        try {
            controllerList.get(Command.getCommandID(command)).run();
        } catch (IllegalArgumentException e) {
            printErr(stage, command.toUpperCase());
        }
    }
}
```
- 입력받은 명령어를 읽고, 명령어에 해당하는 메소드를 실행해준다.
- 입력값 오류로 인한 예외 발생 시 입력된 내용과 오류문구를 출력해준다.

</div>
</details>

<details>
<summary>2단계 실행화면</summary>
<div markdown="1">

- 실행화면 부터 명령어 z 처리

   <img width="326" alt="2단계 실행화면 z 까지" src="https://user-images.githubusercontent.com/78953393/144993690-752e1cca-20bb-41da-901a-c3d1062308e8.png">

- w와 q 명령어

   <img width="159" alt="2단계 실행화면 wq" src="https://user-images.githubusercontent.com/78953393/144993709-19a00102-f8c8-4941-b3fc-7121e119a791.png">

</div>
</details>

## 3단계
- gist revision 번호
    - 43
- git commit log revision 번호
    - commit 0ad14229b1d5c09807486961561f248be85a4619

<details>
<summary>3단계 문제</summary>
<div markdown="1">

### 소코반 게임 완성하기
> 참고사이트 https://www.cbc.ca/kids/games/play/sokoban

</div>
</details>
 
### 3단계 코딩 요구사항
- [X] 가능한 한 커밋을 자주 하고 구현의 의미가 명확하게 전달되도록 커밋 메시지를 작성한다
- [X] 함수나 메소드는 한 번에 한 가지 일을 하고 가능하면 20줄이 넘지 않도록 구현한다
- [X] 함수나 메소드의 들여쓰기를 가능하면 적게(3단계까지만) 할 수 있도록 노력한다

### 실행
- main메서드가 위치한 클래스 명
  - SokobanGame클래스

<details>
<summary>3단계 구현과정</summary>
<div markdown="1">

### 요구사항
- [X] 난이도를 고려하여 스테이지 1부터 5까지 플레이 가능한 map.txt 파일을 스스로 작성한다.
- [X] 지도 파일 map.txt를 문자열로 읽어서 처리하도록 개선한다.
  - 1단계에서 구현한 MapController 클래스를 활용하여, map.txt파일을 읽어 문자열로 처리
- [X] 처음 시작시 Stage 1의 지도와 프롬프트가 표시된다.
  - 프로그램 시작 시 start() 메소르틑 호출하여 표시해준다.
- [ ] r 명령 입력시 스테이지를 초기화 한다.
  - 아직 구현하지 못했습니다.
- [X] 모든 o를 O자리에 이동시키면 클리어 화면을 표시하고 다음 스테이지로 표시한다.
  - contains 메소드를 활용하여, 맵에 O(구멍) 이 존재하는지 확인하고, 0으로 전부 변경되어 O(구멍) 이 없어지면 스테이지 클리어 식으로 구현
- [X] 주어진 모든 스테이지를 클리어시 축하메시지를 출력하고 게임을 종료한다.
  - map.txt 파일에 존재하는 스테이지를 모두 클리어하면 게임종료.

### 참고: 플레이어 이동조건
- [X] 플레이어는 o를 밀어서 이동할 수 있지만 당길 수는 없다.
- [X] o를 O 지점에 밀어 넣으면 0으로 변경된다.
- [X] 플레이어는 O를 통과할 수 있다.
  - 통과는 가능하나, O를 통과하고 나올때 O이 없어지는 문제 아직 해결중
- [X] 플레이어는 #을 통과할 수 없다.
- [ ] 0 상태의 o를 밀어내면 다시 o와 O로 분리된다.
  - 아직 구현하지 못했습니다.
- [X] 플레이어가 움직일 때마다 턴수를 카운트한다.
- [X] 상자가 두 개 연속으로 붙어있는 경우 밀 수 없다.

</div>
</details>

<details>
<summary>3단계 상세설명</summary>
<div markdown="1">

## 목차
1. [CommandController클래스](#CommandController클래스)
2. [Input클래스](#Input클래스)
3. [MapController클래스](#MapController클래스)
4. [MapRepository클래스](#MapRepository클래스)
5. [PlaySokoban클래스](#PlaySokoban클래스)
6. [SokobanMap클래스](#SokobanMap클래스)
7. [SokobanGame클래스](#SokobanGame클래스)

## CommandController클래스
- 명령어를 관리하는 역할

|메소드명| 기능                                |
|------|-----------------------------------|
|[inputCommand](#inputCommand메소드)| 사용자로부터 입력을 받는 명령어를 컨트롤러로 보내는 기능   |
|[createCommendController](#createCommendController메소드)| 명령어에 맞는 메소드를 호출하기 위해 컨트롤러를 생성해주는 기능 |
|[gameExit](#gameExit메소드)| 게임종료 기능을 가지고 있는 메소드               |
|[readCommend](#readCommend메소드)| 입력받은 명령어를 읽어서 명령어에 맞는 메소드를 호출하는 기능|

### inputCommand메소드
```java
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
```
- Input클래스를 생성하여 반복적으로 사용자로부터 입력을 받도록 입력받는 메소드를 호출한다.
- 입력받은 명령어를 readCommend메소드로 전달해준다.

### createCommendController메소드
```java
private void createCommendController(Map<Integer, Runnable> controllerList, int stage) {
    controllerList.put(0, () -> moveUp(stage));
    controllerList.put(1, () -> moveDown(stage));
    controllerList.put(2, () -> moveLeft(stage));
    controllerList.put(3, () -> moveRight(stage));
    controllerList.put(4, this::gameExit);
    controllerList.put(5, () -> resetStage(stage));
}
```
- if~else, switch의 사용을 피하기 위해 함수형 인터페이스인 Runnable을 사용하여 Map에 저장

### gameExit메소드
```java
private void gameExit() {
      System.out.println("Bye~");
      System.exit(0);
  }
```
- Q을 입력받으면 실행
- System.exit를 사용하여 게임 종료 명령어가 입력되면 종료 문구와 함께 프로그램 종료

### readCommend메소드
```java
private void readCommend(List<String> commands, Map<Integer, Runnable> controllerList) {
      for (String command : commands) {
          try {
              controllerList.get(Command.getCommandID(command)).run();
          } catch (IllegalArgumentException e) {
              System.out.println("잘못된입력입니다.");
          }
      }
  }
```
- 입력받은 명령어를 가져와서 Command클래스어 저장되어 있는 명령어와 비교하여, 저장되어 있는 명령어와 일치하면 명령어에 해당되는 메소드를 실행해준다.
- 입력받은 명령어가 일치하지 않으면 Command클래스에서 발생된 예외를 처리하여 오입력 문구를 출력하고 다시 입력을 받는다.

## Input클래스
- 사용자로부터 입력을 받는 역할

|메소드명| 기능                                           |
|------|----------------------------------------------|
|[inputString](#inputString메소드)| 사용자로부터 입력을 받는기능                              |
|[splitString](#splitString메소드)| 사용자로부터 입력받은 문자열을 하나의 문자 단위로 잘라 리스트에 저장해주는 기능 |

### inputString메소드
```java
public List<String> inputString() throws IOException {
      List<String> commandList = new ArrayList<>();
      BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
      String command = buf.readLine().toLowerCase();
      splitString(command, commandList);
      return commandList;
  }
```
- BufferedReader를 사용해서 사용자로부터 문자열 단위로 입력을 받고 입력받은 내용을 splitString메소드로 넘겨준다.
### splitString메소드
```java
private void splitString(String command, List<String> commandList) {
      for (int i = 0; i < command.length(); i++) {
          commandList.add(String.valueOf(command.charAt(i)));
      }
  }
```
- 문자열 단위로 입력받은 내용을 문자 단위로 리스트에 저장해준다.
- 연속적인 명령어를 처리하기 위해 사용되는 메소드


## MapController클래스
- map.txt에 저장되어 있는 내용을 처리하는 역할

|메소드명| 기능                                           |
|------|----------------------------------------------|
|[readText](#readText메소드)| 사용자로부터 입력을 받는기능                              |
|[saveMapData](#saveMapData메소드)| 사용자로부터 입력받은 문자열을 하나의 문자 단위로 잘라 리스트에 저장해주는 기능 |
|[getData](#getData메소드)| 사용자로부터 입력받은 문자열을 하나의 문자 단위로 잘라 리스트에 저장해주는 기능 |

### readText메소드
```java
public void readText() {
    String filePath = "map.txt";
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
```
- map.txt 파일에 작성되어 있는 내용을 FileReader를 사용해 읽고, .map을 활용해 저장값으로 변환해준다.
### saveMapData메소드
```java
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
```
- 저장값으로 저장되어 있는 내용을 가져와서 출력값으로 변환해준뒤 SokobanMap을 생성한다.
- SokobanMap을 생성한 뒤 mapRepository에 저장해준다.

### getData메소드
```java
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
```
- map.txt에 저장되어 있는 값을 S와 =로 Stage별로 구분하여 리스트에 저장하고 반환해준다.
- contains을 활용해 해당 문자열에 S와 =가 있는지 구별한 후 각각 저장


## MapRepository클래스
- 맵을 저장하고, 저장된 맵을 활용하는 역할

|메소드명| 기능                                       |
|------|------------------------------------------|
|[save](#save메소드)| hashMap에 SokobanMap객체를 저장하는 기능           |
|[printMap](#printMap메소드)| 현재 진행중인 Stage의 맵을 출력해주는 기능               |
|[clearGame](#clearGame메소드)| 현재 진행중인 Stage의 클리어 유무를 판단해주는 기능          |
|[moveRight](#moveRight메소드)| Player를 오른쪽으로 이동시키는 메소드 (왼쪽으로 이동은 오른쪽의 반대) |
|[isRightObstacle](#isRightObstacle메소드)| Player가 오른쪽으로 이동할때 장애물 유무 판단을 해주는 기능     |
|[moveRightBall](#moveRightBall메소드)| 공을 오른쪽으로 이동시키는 기능      |
|[moveDown](#moveDown메소드)| Player를 아래쪽으로 이동시키는 기능 (위로 이동은 아래의 반대)   |
|[moveDownBall](#moveDownBall메소드)| 공을 아래쪽으로 이동시키는 기능                        |

### save메소드
```java
public void save(SokobanMap mapData) {
      maps.put(sequence, mapData);
      mapData.setMapID(sequence);
      sequence++;
  }
```
- hashMap에 int로 되어있는 키값과, SokobanMap객체로 되어있는 value값을 저장해준다.
### printMap메소드
```java
public void printMap(int stage) {
      System.out.println("Stage " + (maps.get(stage).getMapID() + 1));
      System.out.println();
      maps.get(stage).setValue();
      maps.get(stage).printSokobanMap();
      clearGame(stage);
      maps.get(stage).count();
      System.out.println();
  }
```
- 현재 진행중인 Stage의 맵을 출력해준다.
### clearGame메소드
```java
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
```
- gameClear메소드를 활용해서 게임 클리어 유무를 판단하여, 클리어시 클리어 문구를 출력해준다.
### moveRight메소드
```java
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
```
- Player의 좌표를 받아와서 Player의 위치를 오른쪽으로 이동해준다.
### isRightObstacle메소드
```java
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
```
- '#', 'o', '0' 등 장애물을 유무를 판단하여 이동, 변경, 옯기는 기능을 실행한다.
### moveRightBall메소드
```java
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
```
- Player앞에 공이 있는지 확인한 후 공을 오른쪽으로 이동시켜 준다.
### moveDown메소드
```java
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
```
- StringBuilder를 사용해 문자열에 위치한 문자를 변경해줌으로써 Player가 이동되게끔 만들어준다.
- Player의 y좌표 값을 받아와서 이동할 좌표에 존재하는 장애물 등을 파악한 후 이동한다.
### moveDownBall메소드
```java
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
```
- Player의 앞에 좌표를 받아서 공이 위치하는지 확인 후 위치하면 공을 아래쪽으로 이동시킨다.

## PlaySokoban클래스
- 게임 start 역할

|메소드명| 기능                  |
|------|---------------------|
|[start](#start메소드)| 게임 시작 첫 화면을 출력하는 기능 |

### start메소드
```java
public void start() {
    CommandController cc = new CommandController();
    System.out.println("소코반의 세계에 오신 것을 환영합니다!");
    System.out.println("^오^");
    System.out.println();
    cc.printerMap(0);
    cc.saveCommand(0);
}
```
- 첫 시작 화면을 출력해주고, 명령어를 입력받는 메소드를 호출해준다.

## SokobanMap클래스
- SokobanMap에 필요한 내용을 저장

|메소드명| 기능                             |
|------|--------------------------------|
|[restStage](#restStage메소드)| stage를 리셋해주는 기능                |
|[gameClearCheck](#gameClearCheck메소드)| stage클리어 조건을 확인하고 결과를 반환해주는 기능 |
|[printSokobanMap](#printSokobanMap메소드)| stage의 맵을 출력해주는 기능             |
|[count](#count메소드)| 현재 Stage의 턴수를 출력해주는 기능         |

### restStage메소드
```java
public List<List<String>> resetStage() {
      System.out.println((getMapID() + 1) + " Stage초기화");
      return saveMap;
  }
```
- 현재 stage를 초기화 해주는 기능
- 처음 SokobanMap이 생생될때 SokobanMap을 일반 맵과 세이브 맵으로 각각 저장해서 resetStage메소드가 호출되면 saveMap을 반환해 초기화
- 위에 방식을 생각하여, 메소드를 작성하고 실행해 봤지만 초기화가 안되서, 다른 방안을 생각중
### gameClearCheck메소드
```java
private boolean gameClearCheck(int i) {
    for (int j = 0; j < sokobanMaps.get(i).size(); j++) {
        if (sokobanMaps.get(i).get(j).contains("O")) {
            return true;
        }
    }
    return false;
}
```
- contains를 사용해서 구멍을 체크한 후 해당 맵에 구멍이 없을 경우 Stage클리어로 간주하여, Stage클리어 유무를 체크해준다.
### printSokobanMap메소드
```java
public void printSokobanMap() {
    for (List<String> sokobanMap : sokobanMaps) {
        for (String s : sokobanMap) {
            System.out.println(s);
        }
    }
    System.out.println();
}
```
- 현재 Stage의 맵 상태를 출력해준다.
### count메소드
```java
public void count() {
    count++;
    System.out.println("턴수:" + count);
}
```
- 명령어가 실행될때 마다 count메소드를 호출하고, 메소드가 호출될때 count수를 올려서 현재 Stage의 턴수를 체크하여 반환해준다.

## SokobanGame클래스
- 프로그램을 실행하는 역할

|메소드명| 기능            |
|------|---------------|
|[main](#main메소드)| 프로그램을 실행하는 기능 |

### main메소드
```java
public static void main(String[] args) {
      MapController mapController = new MapController();
      mapController.readText();
  }
```
- 소코반 게임을 위한 맵을 읽는 클래스를 생성하고 메소드를 호출해 준다.
</div>
</details>

<details>
<summary>3단계 실행화면</summary>
<div markdown="1">

## 플레이 영상

https://user-images.githubusercontent.com/78953393/145208349-75da2e7f-7966-4d97-9154-564e33a7053f.mp4

- 터미널에서 실행하면 예외발생 시 예외처리가 잘 안되는 문제점이 있는데, 왜 그러는지 아직 찾지 못했습니다.

</div>
</details>

## 이번 테스트를 진행하며
- 아쉬운점
  - 3단계를 전부 완벽하게 구현하지 못한 것이 가장 아쉬운것 같다.
  - 좀 더 클래스와 메소드를 명확하게 구분하여 기능과 역할별로 나누지 못하였고, RADME를 적으면서 그런 내용들이 더 보이게 되서 아쉬웠다.

- 좋았던점
  - 3일이라는 시간동안 진짜 정신없이 코딩을 해본것 같아 이점은 정말 재미있고 좋았다, 프로그래밍에 입문하고 처음으로 밤샘 코딩도 해보고 좋은 경험이 될것같다.
  - 2차원 리스트를 처음 써봤는데 2차원 배열과 다른점도 많았고, 배워야 할 내용은 더 많아졌지만 새로운걸 적용하고 시도해본것이 좋았다.
