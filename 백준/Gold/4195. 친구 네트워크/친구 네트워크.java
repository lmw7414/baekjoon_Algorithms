import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 해결 프로세스]
1. 이름 당 아이디 부여<HashMap 저장>
2. union-find로 노드 연결
3. 리스트로 연결
*/
public class Main {
    static StringBuilder sb = new StringBuilder();
    static int ID = 0;
    static int[] parent;
    static HashMap<String, Integer> hm;
    static int[] friends;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < TC; tc++) {
            int F = Integer.parseInt(br.readLine());
            hm = new HashMap<>();  // id 저장 map
            friends = new int[2 * F]; // 친구 목록
            parent = new int[2 * F];
            ID = 0;

            for (int f = 0; f < F; f++) {
                String[] names = br.readLine().split(" ");
                int id1 = addNewFriend(names[0]);
                int id2 = addNewFriend(names[1]);
                union(id1, id2);
                int answer = friends[find(id1)];
                sb.append(answer).append("\n");
            }
        }
        System.out.print(sb.toString());
    }

    public static int addNewFriend(String name) {
        if (!hm.containsKey(name)) { // 존재하지 않는 아이디
            parent[ID] = ID;
            friends[ID] = 1;
            hm.put(name, ID++);
            return ID - 1;
        }
        return hm.get(name);
    }

    public static boolean union(int id1, int id2) {
        // 루트 노드 검색
        int parent1 = find(id1);
        int parent2 = find(id2);

        // 동일한 루트인지 확인
        if (parent1 == parent2) return false; // 이미 동일함

        // 아니라면
        friends[parent1] += friends[parent2];
        friends[parent2] = 0;
        parent[parent2] = parent1;
        return true;
    }

    public static int find(int id) {
        if (parent[id] == id) return id;
        return parent[id] = find(parent[id]);
    }
}