import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static List<Integer>[] adjList;
    static int[] degree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        degree = new int[N + 1];
        adjList = new List[N + 1];
        for (int i = 1; i <= N; i++) adjList[i] = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            adjList[a].add(b);
            adjList[b].add(a);
            degree[a]++;
            degree[b]++;
        }
        long D = 0;
        long G = 0;
        for (int i = 1; i <= N; i++) {
            if (degree[i] >= 2) {
                long val = 0;
                for (int next : adjList[i]) {
                    val += (long) (degree[i] - 1) * (degree[next] - 1);
                }
                D += val;
                if(degree[i] >= 3) { // ㅈ 추가
                    G += ((long) degree[i] * (degree[i] - 1) * (degree[i] - 2))  / 6;
                }
            }
        }
        D /= 2;
        if (D == G * 3) System.out.println("DUDUDUNGA");
        else if (D > G * 3) System.out.println("D");
        else System.out.println("G");
    }
}