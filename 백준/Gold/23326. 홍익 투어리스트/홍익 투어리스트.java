import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static TreeSet<Integer> places = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // N개의 구역
        int Q = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            String input = st.nextToken();
            if (input.equals("1")) places.add(i);
        }
        int curPos = 1;
        for (int q = 0; q < Q; q++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                case "1":
                    int i = Integer.parseInt(input[1]);
                    if (places.contains(i)) places.remove(i);
                    else places.add(i);
                    break;
                case "2":
                    int x = Integer.parseInt(input[1]);
                    int move = x % N;
                    curPos = curPos + move > N ? curPos + move - N : curPos + move;
                    break;
                case "3":
                    Integer next = places.ceiling(curPos);
                    Integer first = places.ceiling(0);
                    if (places.isEmpty()) sb.append(-1).append("\n");
                    else if (next != null) sb.append(next - curPos).append("\n");
                    else if (first != null) sb.append(N - curPos + first).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

}