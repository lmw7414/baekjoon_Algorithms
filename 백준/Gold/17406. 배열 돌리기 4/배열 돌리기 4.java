import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열로 조합해야함
public class Main {

    static int[][] arr; // 입력 값 저장할 2차원 배열
    static int[][] tempArr;  // 회전시킬 때 사용할 배열, 회전 시키기 전에 arr 배열 값을 복사해서 사용
    static int N, M, K;
    static Query[] queries;  // 쿼리들을 저장할 배열
    static boolean[] visit;  // 순열 체크할 때 사용
    static int[] selectedQuery;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        queries = new Query[K];
        visit = new boolean[K];
        selectedQuery = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r, c, s;
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            queries[i] = new Query(r, c, s);
        }

        // 주어진 쿼리에서 순열로 조합
        DFS(0);
        System.out.println(answer);
    }

    // 주어진 쿼리에서 순열 만들기
    public static void DFS(int depth) {
        if (depth == K) {
            copyArr();  // 원본 arr 배열을 tempArr배열 복사 후 회전
            for (int i = 0; i < K; i++) {
                int startX = queries[selectedQuery[i]].r - queries[selectedQuery[i]].s; // 가장 왼쪽 위칸 x
                int startY = queries[selectedQuery[i]].c - queries[selectedQuery[i]].s; // 가장 왼쪽 위칸 y
                int endX = queries[selectedQuery[i]].r + queries[selectedQuery[i]].s;   // 가장 오른쪽 아래칸 x
                int endY = queries[selectedQuery[i]].c + queries[selectedQuery[i]].s;   // 가장 오른쪽 아래칸 y
                int dep = Math.min(endX - startX, endY - startY) / 2;  // 회전 깊이  depth가 s인가?? 체크해보자
                rotate(dep, startX - 1, startY - 1, endX - 1, endY - 1);
            }
            // 다 회전시킨 후 최솟값 찾기
            answer = Math.min(answer, findMin(tempArr));
            return;
        }
        for (int i = 0; i < K; i++) {
            if (visit[i]) continue;

            visit[i] = true;
            selectedQuery[depth] = i;
            DFS(depth + 1);
            visit[i] = false;
        }
    }

    // 원본 배열 복사 메서드
    public static void copyArr() {
        tempArr = new int[N][M];  //임시로 배열을 만들어 원본 배열의 값이 변하지 않도록 하기 위함
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempArr[i][j] = arr[i][j];
            }
        }
    }

    // 시계방향 회전
    public static void rotate(int depth, int startX, int startY, int endX, int endY) {

        for (int dep = 0; dep < depth; dep++) {
            int temp = tempArr[startX + dep][startY + dep];

            // 왼쪽 변 값을  x + 1 -> x로 올리기
            for (int i = startX + dep + 1; i <= endX - dep; i++) {
                tempArr[i - 1][startY + dep] = tempArr[i][startY + dep];
            }

            // 아래쪽 변을 y -> y + 1
            for (int j = startY + dep + 1; j <= endY - dep; j++) {
                tempArr[endX - dep][j - 1] = tempArr[endX - dep][j];
            }

            // 오른쪽 변을  x = x - 1
            for (int i = endX - dep - 1; i >= startX + dep; i--) {
                tempArr[i + 1][endY - dep] = tempArr[i][endY - dep];
            }

            // 위쪽 변을  y = y - 1
            for (int j = endY - dep - 1; j >= startY + dep; j--) {
                tempArr[startX + dep][j + 1] = tempArr[startX + dep][j];
            }
            tempArr[startX + dep][startY + dep + 1] = temp;
        }
    }

    public static int findMin(int[][] arr) {
        int minResult = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = 0; j < M; j++) {
                sum += arr[i][j];
            }
            minResult = Math.min(minResult, sum);
        }
        return minResult;
    }

    static class Query {
        int r;
        int c;
        int s;

        public Query(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }

}