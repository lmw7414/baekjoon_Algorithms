import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
[문제 해결 프로세스]
1.DFS로 탐색하며 그루핑
2. 그루핑한 id를 모아서 1개면 이동 가능 | 2개 이상인 경우 지점이 끊겼다는 의미이므로 갈수 없어 NO

 */
public class Main {
    static int N, M;
    static int[] arr;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new List[N + 1];
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            String[] strs = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                if (strs[j].equals("0")) continue;
                adjList[i + 1].add(j + 1);
                adjList[j + 1].add(i + 1);
            }
        }
        String[] strs = br.readLine().split(" ");
        int idx = 1;
        Set<Integer> set = new HashSet<>();
        for (String str : strs) {
            int node = Integer.parseInt(str);
            if (arr[node] != 0) continue;
            arr[node] = idx;
            DFS(idx, node);
            set.add(idx);
            idx++;
        }
        if (set.size() == 1) System.out.println("YES");
        else System.out.println("NO");
    }

    public static void DFS(int idx, int start) {
        for (int next : adjList[start]) {
            if (arr[next] != 0) continue;
            arr[next] = idx;
            DFS(idx, next);
        }
    }

}