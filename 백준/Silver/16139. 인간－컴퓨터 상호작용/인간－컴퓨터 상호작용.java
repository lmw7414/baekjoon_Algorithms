import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        HashMap<Character, List<Integer>> hm = new HashMap<>();

        String S = st.nextToken();
        for (int i = 0; i < S.length(); i++) {
            if (!hm.containsKey(S.charAt(i))) {
                hm.put(S.charAt(i), new ArrayList<>());
                hm.get(S.charAt(i)).add(i);
            } else hm.get(S.charAt(i)).add(i);
        }
        st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tc; i++) {
            st = new StringTokenizer(br.readLine());
            char A = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int answer = 0;
            if (hm.get(A) == null){
                sb.append(0);
            sb.append('\n');
            }
            else {
                for (int idx : hm.get(A)) {
                    if (idx <= end && idx >= start)
                        answer++;
                    if (idx > end)
                        break;
                }
                sb.append(answer);
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }
}