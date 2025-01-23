import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static List<Box> arr = new LinkedList<>();
    static List<Integer> robots = new LinkedList<>();
    static int zeroCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N * 2; n++) arr.add(new Box(Integer.parseInt(st.nextToken())));

        // 1. 전체회전(로봇, 벨트)
        // 2. 로봇 이동(가장 먼저 올라간 로봇부터)
        // 3. 로봇 추가()
        int step = 1;
        while (zeroCnt < K) {
//            System.out.println(step);
            rotate();
//            print();
            moveRobot();
//            print();
            addRobot();
//            print();
            step++;
        }
        System.out.println(step - 1);
    }

    // 컨베이어 벨트, 로봇 회전
    public static void rotate() {
        Box last = arr.remove(arr.size() - 1);
        arr.add(0, last);

        int removeIdx = -1;
        for (int i = 0; i < robots.size(); i++) {
            robots.set(i, (robots.get(i) + 1) % (2 * N));
            if (robots.get(i) == N - 1) removeIdx = i;
        }
        if (removeIdx != -1) {
            arr.get(N - 1).isHere = false;
            robots.remove(removeIdx);
        }
    }

    // 로봇 이동
    public static void moveRobot() {
        int removeIdx = -1;
        for (int i = 0; i < robots.size(); i++) {
            int curIdx = robots.get(i);
            int nextIdx = (curIdx + 1) % (2 * N);
            if (arr.get(nextIdx).isHere || arr.get(nextIdx).cnt < 1) continue;

            arr.get(curIdx).isHere = false;
            arr.get(nextIdx).isHere = true;
            arr.get(nextIdx).cnt--;
            if (arr.get(nextIdx).cnt == 0) zeroCnt++;
            robots.set(i, (robots.get(i) + 1) % (2 * N));
            if (nextIdx == N - 1) removeIdx = i;
        }
        if (removeIdx != -1) {
            arr.get(N - 1).isHere = false;
            robots.remove(removeIdx);
        }
    }

    // 로봇 올리기(boolean)
    public static void addRobot() {
        if (arr.get(0).cnt < 1) return;
        arr.get(0).cnt--;
        if (arr.get(0).cnt == 0) zeroCnt++;
        arr.get(0).isHere = true;
        robots.add(0);
    }

    public static void print() {
        System.out.println("---- ----");
        for (int num : robots) System.out.print(num + " ");
        System.out.println();
        for (int j = 0; j < 2 * N; j++) {
            System.out.print(arr.get(j).cnt + " ");
        }

        System.out.println("\n---- ----");
    }

    static class Box {
        int cnt; // 내구도
        boolean isHere; // 로봇 여부

        public Box(int cnt) {
            this.cnt = cnt;
            isHere = false;
        }
    }
}