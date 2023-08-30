import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * N개의 섬으로 이루어짐. (1~N) 2 <= N <= 123,456
 * 1번 섬에 구명보트가 있어 양들이 다른 섬에서 1번 섬으로 가야한다.
 * 하지만 도중에 늑대를 만날 경우, 늑대는 양을 최대 한마리만 잡아먹는다.
 * 얼마나 많은 양이 1번 섬에 도달할 수 있을까?
 *
 * 생각해봐야 될 부분
 *  - 늑대는 양을 한번만 잡아먹으면 끝나는지?
 *
 * [입력받을 값]
 * N : 마을의 개수
 * ti -> S or W : S 양, W 늑대
 * ai -> 살고 있는 동물의 수(양 or 늑대)
 * pi -> 연결된 섬
 *
 * [문제 해결 프로세스]
 * 1. 섬 리스트를 객체 배열 리스트로 생성 -> Village[] villages;
 *     - 자체 자식 섬 리스트를 가지고 있게함으로써 DFS로 탐색 가능하도록 함
 *     - 특정 인덱스는 부모 섬, 그안의 리스트는 해당 부모가 가지고 있는 자식 섬
 * 2. 사용할 클래스 <Village>
 *     - idx : 자신 인덱스
 *     - t : 타입
 *     - parent : 부모 idx
 *     - sheep : 양의 수
 *     - wolf : 늑대 수
 *     - childs : 자식 노드 리스트
 * 3. DFS로 돌면서 자식의 섬에서 부모섬으로 양을 보낸다.
 * 4. 양이 부모섬으로 올라갈 때
 *     - 늑대가 있을 경우
 *       - 늑대가 더 많을 경우 -> 늑대의 수를 양의 수 만큼 감소시키고, 해당 마을의 양의 수는 0이된다.
 *       - 양이 더 많을 경우 -> 늑대의 수는 0이되고, 양의 수를 늑대의 수만큼 감소시킨다. -> 양이 더 많으므로, 마을의 타입을 S로 변경
 *  - 양이 있을 경우 -> 부모마을로 보내기
 * 5. 1번 마을에 도달했으면 answer 증가
 * 6. 최종 1번 마을의 양의 수 출력
 * -> 메모리 : 77460kb, 시간 : 1380ms
 *
 * [다시 생각해보기]
 * 1. 굳이 Village 클래스가 필요할까?
 * 2. 양은 양수로, 늑대는 음수로 표현
 * 3. 인접리스트로 접근하기
 */

public class Main {

    static int N;
    static List<Integer>[] adjList;
    static long[] animals;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 마을의 수

        // 변수 초기화
        adjList = new List[N + 1];
        animals = new long[N + 1];
        for(int i = 1; i<= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 2; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char T = st.nextToken().charAt(0);              // S:양 or W:늑대
            long A = Long.parseLong(st.nextToken());        //동물의 수
            int parent = Integer.parseInt(st.nextToken());  //부모 노드
            adjList[parent].add(i);
            if(T == 'S') animals[i] = A;  // 양
            else animals[i] = -A;  // 늑대
        }
        DFS(1, 1);
        System.out.println(animals[1]);

    }

    public static void DFS(int self, int parent) {
        for(int child : adjList[self]) {
            DFS(child, self);
        }
        if(self != parent) { //self와 parent가 같다면 해당 노드는 루트 노드
            // animals[self]가 음수일 경우 이동 안함
            // animals[self]가 양수일 경우 위로 이동
            if(animals[self] > 0) {
                animals[parent] += animals[self];
            }
        }
    }
}