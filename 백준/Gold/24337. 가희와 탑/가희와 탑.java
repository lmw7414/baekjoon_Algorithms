import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * A가 클 경우
 * - B먼저 추가(뒤에서 부터)
 * - B-1 부터 1까지
 * - A 추가
 * - A-1부터 1까지
 * - 이후 나머지는 1크기로 채우기
 */
public class Main {
    static int N, A, B;
    static ArrayDeque<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        queue.add(Math.max(A, B));

        if (A < B) {
            addFront(A - 1, A - 1);
            if (queue.getFirst() == 1) {
                addRear(B - 1, B - 1, 0);
            } else {
                int oneCnt = N - queue.size() - (B - 1);
                addRear(B - 1, B - 1, oneCnt);
            }
            if (queue.getFirst() == 1) while (queue.size() < N) queue.addFirst(1);
            else while (queue.size() < N) queue.addLast(1);
        } else {
            addFront(A - 1, A - 1);
            addRear(B - 1, B - 1, 0);
            while (queue.size() < N) queue.addFirst(1);
        }
        if (queue.size() > N) System.out.println(-1);
        else {
            for (int num : queue) System.out.print(num + " ");
        }
    }

    public static void addFront(int size, int cnt) {
        for (int i = 0; i < cnt; i++) {
            queue.addFirst(size--);
        }
    }

    public static void addRear(int size, int cnt, int oneCnt) {
        for (int i = oneCnt; i > 0; i--)
            queue.addLast(1);
        for (int i = 0; i < cnt; i++) {
            queue.addLast(size--);
        }
    }

}