
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashMap<Integer, List<Integer>> hm;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        hm = new HashMap<>();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (!hm.containsKey(v)) {
                hm.put(v, new ArrayList<>());
                hm.get(v).add(e);
            } else {
                hm.get(v).add(e);
            }

            if (!hm.containsKey(e)) {
                hm.put(e, new ArrayList<>());
                hm.get(e).add(v);
            } else {
                hm.get(e).add(v);
            }
        }
        System.out.println(bfs(N));
    }

    public static int bfs(int N) {
        boolean[] visited = new boolean[N + 1];

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                count++;
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while(!q.isEmpty()) {
                    int cur = q.poll();
                    if(hm.get(cur) == null)
                        break;
                    for(int node : hm.get(cur)) {
                        if(!visited[node]) {
                            visited[node] = true;
                            q.add(node);
                        }
                    }
                }
            }
        }
        return count;
    }
}
