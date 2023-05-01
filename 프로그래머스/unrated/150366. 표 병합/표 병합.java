import java.util.*;

class Solution {
    static Cell[][] cellTable;
    public static String[] solution(String[] commands) {
        List<String> answer = new ArrayList<>();

        // 테이블 세팅
        cellTable = new Cell[51][51];
        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                cellTable[i][j] = new Cell(i, j);
            }
        }
        for(String command : commands) {
            String[] split = command.split(" ");
            if("UPDATE".equals(split[0])){
                if(split.length == 3)
                    update(split[1], split[2]);
                else if(split.length == 4)
                    update(Integer.parseInt(split[1]), Integer.parseInt(split[2]), split[3]);
            } else if("MERGE".equals(split[0])) {
                merge(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]));
            } else if("UNMERGE".equals(split[0])) {
                unmerge(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            }else if("PRINT".equals(split[0])) {
                answer.add(print(Integer.parseInt(split[1]), Integer.parseInt(split[2])));
            }
        }
        return answer.toArray(String[]::new);
    }

    static public void update(int r, int c, String s) {
        int[] parent = new int[] {cellTable[r][c].x, cellTable[r][c].y};
        cellTable[parent[0]][parent[1]].data = s;
    }

    static public void update(String value1, String value2) {

        for(int i = 1; i <= 50; i++) {
            for(int j = 1; j <= 50; j++) {
                // 부모일 때
                if(value1.equals(cellTable[i][j].data)) {
                    if (cellTable[i][j].x == i && cellTable[i][j].y == j) {
                        if (value1.equals(cellTable[i][j].data))
                            cellTable[i][j].data = value2;
                    }
                }
            }
        }

    }

    static public void merge (int r1, int c1, int r2, int c2) {
        int[] parent1 = new int[] {cellTable[r1][c1].x, cellTable[r1][c1].y};
        int[] parent2 = new int[] {cellTable[r2][c2].x, cellTable[r2][c2].y};

        // 두 위치가 같을 때
        if(parent1[0] == parent2[0] && parent1[1] == parent2[1])
            return;

        // 두개의 셀 모두가 값을 가질 때
        if(!cellTable[parent1[0]][parent1[1]].data.equals("") && !cellTable[parent2[0]][parent2[1]].data.equals("")) {
            cellTable[parent2[0]][parent2[1]].data = "";
        } else {
            if(cellTable[parent1[0]][parent1[1]].data.equals("") && cellTable[parent2[0]][parent2[1]].data.equals("")) {   // 둘 다 빈 값일 때는 동작 x
            } else if(cellTable[parent1[0]][parent1[1]].data.equals("")) {  //첫번째가 빈 값일 때 두번째 값 대입
                cellTable[parent1[0]][parent1[1]].data = cellTable[parent2[0]][parent2[1]].data;
                cellTable[parent2[0]][parent2[1]].data = "";
            }
        }



        // 해당 부모를 갖고 있는 아이들 모두 이것으로 바꿔줘야함
        for(int i = 1; i<= 50; i++) {
            for(int j = 1; j<= 50; j++) {
                if(cellTable[i][j].x == parent2[0] && cellTable[i][j].y == parent2[1]) {
                    cellTable[i][j].x = parent1[0];
                    cellTable[i][j].y = parent1[1];
                }
            }
        }

    }
    static public void unmerge(int r, int c) {
        int[] parent = new int[] {cellTable[r][c].x, cellTable[r][c].y};
        cellTable[r][c].data = cellTable[parent[0]][parent[1]].data;
        cellTable[r][c].x = r;
        cellTable[r][c].y = c;

        for(int i = 1; i<= 50; i++) {
            for(int j = 1; j<=50; j++) {
                if(i == r && j == c)
                    continue;
                if(cellTable[i][j].x == parent[0] && cellTable[i][j].y == parent[1]) {
                    cellTable[i][j].x = i;
                    cellTable[i][j].y = j;
                    cellTable[i][j].data = "";
                }
            }
        }

    }
    static public String print(int r, int c) {
        int[] parent = new int[] {cellTable[r][c].x, cellTable[r][c].y};
        if (cellTable[parent[0]][parent[1]].data == "")
            return "EMPTY";
        else
            return cellTable[parent[0]][parent[1]].data;
    }
}
class Cell {
    String data;
    int x;
    int y;
    public Cell(int x, int y) {
        data = "";
        this.x = x;
        this.y = y;
    }
}

