import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static int N;
    static String[] arr = {
            "###...#.###.###.#.#.###.###.###.###.###",
            "#.#...#...#...#.#.#.#...#.....#.#.#.#.#",
            "#.#...#.###.###.###.###.###...#.###.###",
            "#.#...#.#.....#...#...#.#.#...#.#.#...#",
            "###...#.###.###...#.###.###...#.###.###"
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Integer>[] answer = new List[N];
        for (int i = 0; i < N; i++) {
            answer[i] = new ArrayList<>();
        }

        String[] input = new String[5];
        for (int i = 0; i < 5; i++) {
            input[i] = br.readLine();
        }
        for (int n = 0; n < N; n++) {
            String[] str = new String[5];
            for (int i = 0; i < 5; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 4 * n; j < 4 * n + 3; j++) {
                    sb.append(input[i].charAt(j));
                }
                str[i] = sb.toString();
            }
            for (int i = 0; i < 10; i++) {
                if (isSame(i, str)) {
                    answer[n].add(i);
                }
            }
        }

        int avgCnt = 1;
        long result = 0;
        for (int n = 0; n < N; n++) {
            if (answer[n].isEmpty()) {
                System.out.println(-1);
                return;
            }
            avgCnt *= answer[n].size();

            int cnt = 1;
            for (int k = 0; k < N; k++) {
                if (n == k) continue;
                cnt *= answer[k].size();
            }

            for (int num : answer[n]) {
                result += (long) ((long) cnt * (num * Math.pow(10, N - n - 1)));
            }
        }
        System.out.println((double) result / avgCnt);
    }

    public static boolean isSame(int num, String[] str) {
        int ny = 4 * num;
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 3; y++) {
                if (arr[x].charAt(ny + y) == '.' && str[x].charAt(y) == '#') {
                    return false;
                }
            }
        }
        return true;
    }

}