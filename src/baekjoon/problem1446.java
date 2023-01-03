package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

//지름길
public class problem1446 {

    static int N; // 지름길 개수
    static int D; // 고속도로 거리
    static int[] dp; // 현재까지의 최단거리를 기록
    static ArrayList<ShortCut> info = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dp = new int[D + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start, end, distance;
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            distance = Integer.parseInt(st.nextToken());
            ShortCut shortCut = new ShortCut(start, end, distance);

            if (start < 0 || start >= end || end > D || end - start < distance) continue;

            if (info.contains(shortCut)) {
                ShortCut origin = info.get(info.indexOf(shortCut));
                if (origin.distance > distance) {
                    origin.distance = distance;
                }
            } else {
                info.add(shortCut);
            }
        }


        for (int i = 0; i < dp.length - 1; i++) {
            ShortCut shortCut = null;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < info.size(); j++) {
                if (info.get(j).end == i + 1) {
                    int tmp = dp[info.get(j).start] + info.get(j).distance;
                    if (min > tmp) {
                        min = tmp;
                        shortCut = info.get(j);
                    }
                }
            }
            if (shortCut != null) {
                //System.out.println(shortCut);
                dp[i + 1] = Math.min(dp[i] + 1, dp[shortCut.start] + shortCut.distance);
            } else {
                dp[i + 1] = dp[i] + 1;
            }
        }

        System.out.println(dp[D]);
    }

    public static class ShortCut {
        int start;
        int end;
        int distance;

        public ShortCut(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShortCut shortCut = (ShortCut) o;
            return start == shortCut.start && end == shortCut.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }
}
