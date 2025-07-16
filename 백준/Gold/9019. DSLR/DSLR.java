
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < TC; tc++) {

            st = new StringTokenizer(br.readLine());
            int val = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            String CMD = bfs(val, target);
            sb.append(CMD).append("\n");
        }
        System.out.print(sb);
    }

    public static String bfs(int val, int target) {
        Set<Integer> visited = new HashSet<>();
        visited.add(val);
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node("", val));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int next = 0;
                if (i == 0) {
                    next = methodD(cur.val);
                    if (next == target) return cur.str + "D";
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    queue.add(new Node(cur.str + "D", next));
                } else if (i == 1) {
                    next = methodS(cur.val);
                    if (next == target) return cur.str + "S";
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    queue.add(new Node(cur.str + "S", next));
                } else if (i == 2) {
                    next = methodL(cur.val);
                    if (next == target) return cur.str + "L";
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    queue.add(new Node(cur.str + "L", next));
                } else {
                    next = methodR(cur.val);
                    if (next == target) return cur.str + "R";
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    queue.add(new Node(cur.str + "R", next));
                }

            }
        }
        return "";
    }

    public static int methodD(int val) {
        return val * 2 % 10000;
    }

    public static int methodS(int val) {
        return (val + 9999) % 10000;
    }

    public static int methodL(int val) {
        int tar = val / 1000;
        val %= 1000;
        val *= 10;
        val += tar;
        return val;
    }

    public static int methodR(int val) {
        int[] arr = new int[4];
        int mod = 1000;
        int copy = val;
        int idx = 1;
        while (copy >= 10) {
            arr[idx++] = copy / mod;
            copy %= mod;
            mod /= 10;
        }
        arr[0] = copy;
        return arr[0] * 1000 + arr[1] * 100 + arr[2] * 10 + arr[3];
    }

    static class Node {
        String str;
        int val;

        public Node(String str, int val) {
            this.str = str;
            this.val = val;
        }
    }
}