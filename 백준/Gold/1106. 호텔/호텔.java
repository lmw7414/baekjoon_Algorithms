
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int C, N;
    static Node[] nodes;
    static int INF = 98765432;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken()); // 홍보 최소 인원
        N = Integer.parseInt(st.nextToken()); // 홍보할 수 있는 도시의 개수
        nodes = new Node[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(c, p);
        }
        Arrays.sort(nodes);
        //for(Node n : nodes) System.out.println(n.c + " " + n.p);
        int[][] dp = new int[N + 1][C + 1];
        for(int i = 0; i <= N; i++) Arrays.fill(dp[i], INF);
        for(int i = 1; i <= N; i++) {
            Node node = nodes[i-1];
            dp[i][0] = 0;
            for(int j = 1; j <= C; j++) {
                dp[i][j] = Math.min(dp[i-1][j], ((j-1) / node.p + 1)* node.c);
                if(j-node.p >= 0) dp[i][j] = Math.min(dp[i][j], dp[i][j-node.p] + node.c);
            }
        }

        System.out.println(dp[N][C]);
    }

    static class Node implements Comparable<Node>{
        int c, p;

        public Node(int c, int p) {
            this.c = c;
            this.p = p;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare((double) o.p / o.c, (double)this.p / this.c);
        }
    }

}