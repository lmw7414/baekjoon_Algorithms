import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [문제 해결 프로세스]
 * 1. p q를 서로소로 만들기
 * - 최대 공약수 찾기
 * - 각각의 수를 최대 공약수로 나눠주기
 * 2. arr[a] 값과 p를 같은 수로 동기화
 */

public class Main {
    static int N;
    static int[] arr;
    static Queue<Recipe> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st;

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            // 1. 서로소 만들기
            int gcd = GCD(Math.max(p, q), Math.min(p, q));
            p /= gcd;
            q /= gcd;
            if (i == 0) {
                arr[a] = p;
                arr[b] = q;
            } else {
                queue.add(new Recipe(a, b, p, q));
            }

        }
        while (!queue.isEmpty()) {
            Recipe cur = queue.poll();
            if (arr[cur.a] == 0 && arr[cur.b] == 0) {
                queue.add(cur);
                continue;
            }
            //1. 이미 초기화된 값 찾기
            if (arr[cur.a] != 0) {
                int lcm = LCM(Math.max(arr[cur.a], cur.p), Math.min(arr[cur.a], cur.p));

                // 배율에 맞게 arr 배열도 변경시켜주기
                int mul = lcm / arr[cur.a];
                for (int j = 0; j < N; j++) {
                    if (arr[j] == 0) continue;
                    arr[j] *= mul;
                }
                // pq도 최소공배수 배율에 맞게 변경
                mul = lcm / cur.p;
                cur.p = lcm;
                cur.q *= mul;
                arr[cur.b] = cur.q;

            } else { // cur.b
                int lcm = LCM(Math.max(arr[cur.b], cur.q), Math.min(arr[cur.b], cur.q));
                int mul = lcm / arr[cur.b];
                for (int j = 0; j < N; j++) {
                    if (arr[j] == 0) continue;
                    arr[j] *= mul;
                }
                mul = lcm / cur.q;
                cur.q = lcm;
                cur.p *= mul;
                arr[cur.a] = cur.p;
            }
        }
        for (int i : arr) System.out.print(i + " ");
    }

    // a 큰 수 , b 작은 수
    public static int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }

    // a 큰 수, b 작은 수
    public static int LCM(int a, int b) {
        int s = a;
        while (a % b != 0) {
            a += s;
        }
        return a;
    }

    static class Recipe {
        int a, b, p, q;

        public Recipe(int a, int b, int p, int q) {
            this.a = a;
            this.b = b;
            this.p = p;
            this.q = q;
        }
    }
}