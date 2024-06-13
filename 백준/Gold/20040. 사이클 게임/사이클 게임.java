import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * 1. 두 명의 플레이어가 차례대로 돌아가며 진행하는 게임
 * - 선 플레이어 : 홀수번째 차례
 * - 후 플레이어 : 짝수번째 차례
 * 2. 0부터 n-1 까지 고유한 번호가 부연된 평면 상의 점 n개가 주어지며, 이 중 어느 세 점도 일직선 위에 놓이지 않음
 * 3. 매 차례마다 플레이어는 두 점을 선택해서 이를 연결하는 선분을 긋는데
 * 4. 이전에 그린 선분을 다시 그을 수는 없지만 이미 그린 다른 선분과 교차하는 것은 가능
 * 5. 게임을 진행하다가 처음으로 사이클을 완성하는 순간 게임이 종료된다.
 * 사이클 C : C에 속한 임의의 선분의 한 끝점에서 출발하여 모든 선분을 한번씩만 지나서 출발점으로 되돌아올 수 있다.
 * <p>
 * 몇번째 차례에서 사이클이 완성되었는지, 혹은 아직 게임이 진행중인지 판단해야함
 * [문제 해결 프로세스]
 * 사이클이 있는지 확인해야함
 * - DFS, UNION-FIND
 */

public class Main {
    static int[] parent;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (!union(A, B)) {
                System.out.println(i);
                System.exit(0);
            }
        }
        System.out.println(0);

    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        parent[b] = a;
        return true;
    }

    public static int find(int A) {
        if (A == parent[A]) return A;
        else return parent[A] = find(parent[A]);
    }

}