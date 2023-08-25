import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static String correct = "123456780";
    static Map<String, Integer> map = new HashMap<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String start = "";
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int input = Integer.parseInt(st.nextToken());
                start += input;
            }
        }
        map.put(start, 0);
        System.out.println(bfs(start));
    }

    static int bfs(String start) {
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            String pos = queue.poll();
            int zeroLoc = pos.indexOf("0");
            int x = zeroLoc / 3;
            int y = zeroLoc % 3;
            int move = map.get(pos);

            if(pos.equals(correct))
                return move;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nPos = nx * 3 + ny;

                if(nx < 0 || ny < 0 || nx >= 3 || ny >= 3)
                    continue;

                char temp = pos.charAt(nPos);
                String next = pos.replace(temp, 't');
                next = next.replace('0', temp);
                next = next.replace('t', '0');

                if(!map.containsKey(next)) {
                    queue.add(next);
                    map.put(next, move + 1);
                }
            }
        }
        return -1;
    }
}
