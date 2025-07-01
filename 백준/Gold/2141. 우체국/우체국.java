import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Node[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new Node[N];
        long te = 0;
        for (int i = 0; i < N; i++) { // i번째 마을
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()); // 마을의 위치
            int A = Integer.parseInt(st.nextToken()); // 마을에 있는 사람 수
            arr[i] = new Node(X, A);
            te += A;
        }
        Arrays.sort(arr, (a, b) -> a.x - b.x);
        long re = 0;
        for(Node n : arr) {
            re += n.a;
            if(re >= (te + 1) / 2) {
                System.out.println(n.x);
                break;
            }
        }
    }

    static class Node {
        int x, a;

        public Node(int x, int a) {
            this.x = x;
            this.a = a;
        }
    }
}