
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for(int i = 1; i<= N; i ++)
            parent[i] = i;
        
        for(int i = 0; i< M; i++) {
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(c == 1) {
                int rootA = find(a);
                int rootB = find(b);

                if(rootA == rootB) System.out.println("YES");
                else System.out.println("NO");
            } else {
                union(a, b);
            }
        }
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parent[rootB] = rootA;
    }

    public static int find(int a) {
        if(parent[a] == a)
            return a;
        parent[a] = find(parent[a]);
        return parent[a];
    }
}