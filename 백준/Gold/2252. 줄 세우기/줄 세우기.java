
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int[] degree;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        degree = new int[N + 1];
        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            arr[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            degree[b]++;
            arr[a].add(b);
        }
        List<Integer> answer = topologicalSort(N);
        answer.stream().forEach(i -> System.out.print(i + " "));
    }

    public static List<Integer> topologicalSort(int N) {
        List<Integer> answer = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];
        while(answer.size() != N) {
            int cur = -1;
            for(int i = 1; i<=N; i++) {
                if (!visited[i] && degree[i] == 0) {
                    cur = i;
                    visited[i] = true;
                    break;
                }
            }
            if(cur != -1) {
                answer.add(cur);
                for (int next : arr[cur]) {
                    degree[next]--;
                }
            }
        }
        return answer;
    }


}