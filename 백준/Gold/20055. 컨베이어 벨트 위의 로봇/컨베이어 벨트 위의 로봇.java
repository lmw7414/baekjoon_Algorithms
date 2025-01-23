import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static List<Box> arr = new ArrayList<>();
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
            rotate();
            moveRobot();
            addRobot();
            step++;
        }
        System.out.println(step - 1);
    }

    // 컨베이어 벨트, 로봇 회전
    public static void rotate() {
        Box last = arr.remove(arr.size() - 1);
        arr.add(0, last);
        arr.get(N - 1).isHere = false;
    }

    // 로봇 이동
    public static void moveRobot() {
        for (int i = N - 2; i >= 0; i--) {
            if (!arr.get(i).isHere) continue; // 로봇이 존재하지 않는다면
            if(arr.get(i + 1).isHere) continue;
            if (arr.get(i + 1).cnt < 1) continue; // 이동할 위치의 내구도가 0이라면

            arr.get(i + 1).cnt--; // 이동할 곳의 내구도 감소
            if (arr.get(i + 1).cnt == 0) zeroCnt++;
            arr.get(i).isHere = false;  // 현재 위치
            arr.get(i + 1).isHere = true; // 다음 이동할 위치
        }
        arr.get(N - 1).isHere = false;
    }

    // 로봇 올리기(boolean)
    public static void addRobot() {
        if (arr.get(0).cnt < 1) return;
        arr.get(0).cnt--;
        if (arr.get(0).cnt == 0) zeroCnt++;
        arr.get(0).isHere = true;
    }

    public static void print() {
        System.out.println("---- ----");
        for (int j = 0; j < 2 * N; j++) {
            System.out.print(arr.get(j).cnt + "\t");
        }
        System.out.println();
        for (int j = 0; j < 2 * N; j++) {
            System.out.print(arr.get(j).isHere?1 + "\t":0 + "\t");
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