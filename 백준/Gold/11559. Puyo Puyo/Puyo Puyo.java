import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1. 보드 입력 받기
2. 터질 것이 있는지 아래부터 전체 탐색
3. depth가 4개 일 경우 +1 연쇄 - 여러그룹이 동시에 터져도 + 1
4. 언제까지? 최후 탐색을 돌려서 터질게 하나도 없다면 그 때 종료
 */

public class Main {
    static char[][] board = new char[12][6];
    static int[][] check = new int[12][6];  // -1 인 경우 .  0인경우 알파벳인데 방문 안한경우  1인경우 알파벳인데 방문한 경우
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 채우기
        for (int i = 0; i < 12; i++) {
            board[i] = br.readLine().toCharArray();
        }
        while (DFS());  //삭제할 것이 없을 때까지
        System.out.println(answer);
    }

    public static boolean DFS() {
        int[] dx = {-1, 1, 0, 0};  //상하좌우
        int[] dy = {0, 0, -1, 1};
        boolean oneMore = false;
        List<int[]> dels = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();

        upToDateCheck();
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] != '.') {
                    if (check[i][j] == 1) continue;
                    int depth = 1;  // depth가 4가되면 터트릴 준비를 한다
                    List<int[]> tempDels = new ArrayList<>();  // depth가 늘어날 때마다 삭제할 위치를 저장시키고 4가 넘게 되면 삭제 배열에 저장 그렇지 않으면 삭제
                    queue.add(new int[]{i, j});
                    tempDels.add(new int[]{i, j});
                    check[i][j] = 1;  // 방문체크
                    char target = board[i][j];
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = cur[0] + dx[k];
                            int ny = cur[1] + dy[k];

                            if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6 || board[nx][ny] == '.' || board[nx][ny] != target)
                                continue;
                            if (check[nx][ny] == 1) continue;  // 이미 방문한 경우
                            queue.add(new int[]{nx, ny});
                            tempDels.add(new int[]{nx, ny});
                            check[nx][ny] = 1;
                            depth++;
                        }
                    }
                    if (depth < 4) tempDels.clear();
                    else {
                        dels.addAll(tempDels);
                        oneMore = true;
                    }

                }
            }
        }
        if(dels.size() > 0) puyoPop(dels);
        return oneMore;
    }

    // 체크 배열 최신화하기
    public static void upToDateCheck() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == '.') {
                    check[i][j] = -1;
                } else {
                    check[i][j] = 0;
                }
            }
        }
    }

    // 아래로 내리기
    public static void goDown() {
        for (int i = 0; i < 6; i++) {
            String str = "";
            for (int j = 11; j >= 0; j--) {
                if (board[j][i] != '.') {
                    str += board[j][i];
                    board[j][i] = '.';
                }
            }
            for (int j = 0; j < str.length(); j++)
                board[11 - j][i] = str.charAt(j);
        }
    }

    // 터진 푸요 삭제시키기
    public static void puyoPop(List<int[]> deletes) {
        for (int i = 0; i < deletes.size(); i++) {
            int[] del = deletes.get(i);
            board[del[0]][del[1]] = '.';
        }
        answer++;
        goDown();
    }
}