import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<String> pq = new PriorityQueue<>((a1, b1) -> a1.length() - b1.length()); // 문자열 길이 순 정렬
            Node head = new Node('s');
            for (int n = 0; n < N; n++) {
                pq.add(br.readLine());
            }
            boolean flag = true;
            while (!pq.isEmpty()) {
                String str = pq.poll();
                boolean result = add(head, str, 0);
                if (!result) {
                    flag = false;
                    break;
                }
            }
            if (flag) sb.append("YES\n");
        }
        System.out.print(sb);
    }

    public static boolean add(Node parent, String str, int idx) {
        if (parent.isEnd) {
            sb.append("NO\n");
            return false;
        }
        if (str.length() <= idx) {
            parent.isEnd = true;
            return true;
        }
        char key = str.charAt(idx);
        if (parent.child.containsKey(key)) {
            return add(parent.child.get(key), str, idx + 1);
        } else {
            parent.child.put(key, new Node(key));
            return add(parent.child.get(key), str, idx + 1);
        }
    }

    static class Node {
        char data;
        Map<Character, Node> child;
        boolean isEnd;

        public Node(char data) {
            this.data = data;
            child = new HashMap<>();
            isEnd = false;
        }
        
    }
}