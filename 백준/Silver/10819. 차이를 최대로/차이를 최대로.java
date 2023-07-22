
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] arr;
    static int[] temp;
    static boolean[] visit;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        temp = new int[N];
        visit = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        DFS(0);
        System.out.println(answer);

    }

    public static void DFS(int depth) {
        if (depth == N) {
            int total = 0;
            for (int i = 0; i < N - 1; i++) {
                total += Math.abs(temp[i] - temp[i + 1]);
            }
            answer = Math.max(answer, total);
        } else {
            for (int i = 0; i < N; i++) {
                if (!visit[i]) {
                    visit[i] = true;
                    temp[depth] = arr[i];
                    DFS(depth + 1);
                    visit[i] = false;
                }
            }
        }
    }

}