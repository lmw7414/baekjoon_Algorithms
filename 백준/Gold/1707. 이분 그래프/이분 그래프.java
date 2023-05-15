
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] A;
    static boolean[] visited;
    static int[] bipartite;
    static boolean isEven;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());

        for (int i = 0; i < tc; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            A = new ArrayList[v + 1];
            visited = new boolean[v + 1];
            bipartite = new int[v + 1];
            isEven = true;

            for(int k = 1; k <=v; k++)
                A[k] = new ArrayList<>();

            for (int j = 0; j < e; j++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                A[v1].add(v2);
                A[v2].add(v1);
            }

            for(int j = 1; j <= v; j++) {
                if(isEven) DFS(j);
                else break;
            }
            if(isEven) System.out.println("YES");
            else System.out.println("NO");

        }

    }

    public static void DFS(int node) {
        visited[node] = true;

        for(int n : A[node]) {
            if(visited[n]) {
                if(bipartite[node] == bipartite[n]) {
                    isEven = false;
                }
            } else {
                bipartite[n] = (bipartite[node] == 0 ? 1 : 0);
                DFS(n);
            }
        }
    }

}