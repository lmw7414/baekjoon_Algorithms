
import java.util.*;
import java.io.*;

/**
 * N명의 사람 | 최대 1천명
 * 계보 복원
 * 각자가 기억하는 조상들의 이름들은 존재
 * 기억력이 좋은 주민들은 모든 조상을 완벽하게 기억
 * 각 가문은 한명의 시조를 root로 하는 트리형태를 띔
 * 조상 : 나 보다 높은 모든 노드(같은 뿌리에 한해)
 * -> 몇 개의 가문이 존재했는지, 각 가문에 대한 정보를 출력하는 프로그램
 * [출력]
 * 1. 가문의 수(루트의 개수)
 * 2. 루트의 이름
 * 3. 이름의 사전순 -> 이름 + 자식의 수 + 자식들의 이름
 * [문제 해결 프로세스]
 * 위상 정렬
 * 부모수 카운트
 */
public class Main {
    static int N; // 석호촌에 살고 있는 사람들
    static String[] people; // 현재 살고 있는 사람들의 이름| 1~6의 소문자로 구성, 중복된 이름 없음
    static int M; // 기억하는 정보의 개수| X,Y -> X의 조상 Y

    static int[] outDeg;
    static Map<String, Integer> hm = new HashMap<>();
    static List<String>[] adjList, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new String[N];
        outDeg = new int[N]; // 자신 위의 조상 수

        // 사람 이름 입력, 정렬 후 인덱스 번호 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) people[i] = st.nextToken();
        Arrays.sort(people);
        for (int i = 0; i < N; i++) hm.put(people[i], i);

        adjList = new List[N]; // 입력값 저장 - 자식 노드 저장
        answer = new List[N]; // 정답 출력용
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
            answer[i] = new ArrayList<>();
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] str = br.readLine().split(" ");
            String child = str[0];
            String parent = str[1];
            adjList[hm.get(parent)].add(child);
            outDeg[hm.get(child)]++;
        }
        solution();
    }

    public static void solution() {
        Queue<Integer> queue = new ArrayDeque<>();
        List<String> roots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (outDeg[i] == 0) {
                queue.add(i);
                roots.add(people[i]); // 루트 저장
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 해당 부모를 자식으로 두고 있는 자식노드의 degree 제거
            for (String child : adjList[cur]) {
                if (--outDeg[hm.get(child)] == 0) {
                    queue.add(hm.get(child));
                    answer[cur].add(child);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(roots.size()).append("\n");
        for(String root : roots) sb.append(root).append(" ");
        sb.append("\n");

        for(int i = 0; i < N; i++) {
            sb.append(people[i]).append(" ");
            sb.append(answer[i].size()).append(" ");
            Collections.sort(answer[i]);
            for(String child : answer[i]) sb.append(child).append(" ");
            sb.append("\n");
        }
        System.out.print(sb);
    }

}