import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, N;
    static List<Universe>[] universes;
    static HashMap<String, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        universes = new List[M];
        for (int i = 0; i < M; i++) universes[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int id = j;
                int size = Integer.parseInt(st.nextToken());
                if(j > 0 && size == universes[i].get(j - 1).size) id = universes[i].get(j - 1).id;
                universes[i].add(new Universe(id, size));
            }
            Collections.sort(universes[i]);
        }
        for (int i = 0; i < M; i++) {
            String str = parseToString(universes[i]);
            if (hm.containsKey(str)) {
                hm.replace(str, hm.get(str) + 1);
            } else hm.put(str, 1);
        }

        int answer = 0;
        for(int value : hm.values()) {
            if(value > 1) {
                answer += (value*(value - 1)) / 2;
            }
        }
        System.out.println(answer);

    }

    public static String parseToString(List<Universe> list) {
        StringBuilder sb = new StringBuilder();
        for (Universe u : list) {
            sb.append(u.id);
        }
        return sb.toString();
    }

    static class Universe implements Comparable<Universe> {
        int id;
        int size;

        public Universe(int id, int size) {
            this.id = id;
            this.size = size;
        }

        @Override
        public int compareTo(Universe o) {
            return this.size - o.size;
        }
    }

}