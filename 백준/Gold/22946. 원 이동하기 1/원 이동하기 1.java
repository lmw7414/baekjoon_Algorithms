import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제 설명]
 * N 개의 원
 * 임의의 두개의 원을 선택했을 때 내접, 외접 등 교점이 존재하지 않아야 함
 * 하나의 원이 다른 원 안에 포함될 수 있음
 * 하나의 원 내부 -> 다른 원의 내부로 이동하려고 함
 * 원 내부는 단 한번만 방문할 수 있으며 두번 이상 방문 불가
 * -> 좌표 평면에서 임의의 두 원을 골랐을 때 하나의 원 내부에서 다른 원 내부로 이동할 때 방문한 원의 최대 개수
 *
 * [입력]
 * N : 원의 개수(최대 5000개)
 * x, y, R : x좌표, y 좌표, 반지름(최대 10000)
 *
 * [해결 프로세스]
 * 1. 두개의 원 선택
 *  - 조합의 경우 12,497,500(천이백만) + 정렬(25000000)
 * 2. 
 * 2. 두개의 원이 겹치는지 체크(내접, 외접, 교점)
 *
 */
public class Main {
    static int N;
    static List<Circle> circles = new ArrayList<>();
    static List<Integer>[] tree;
    static List<Integer>[] parent;
    static boolean[] visit;
    static int answer = 0;
    static int answerIdx = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visit = new boolean[N + 1];
        tree = new List[N + 1];
        parent = new List[N + 1];
        for(int i= 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
            parent[i] = new ArrayList<>();
        }
        StringTokenizer st;
        circles.add(new Circle(0, 0, 0, Integer.MAX_VALUE)); // 평면좌표
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            circles.add(new Circle(i, x, y, r));
        }
        circles.sort((a1, b1) -> b1.r - a1.r);

        addTree(0);
        Arrays.fill(visit, false);
        visit[0] = true;
        dfs(0, 0);
        Arrays.fill(visit, false);
        visit[answerIdx] = true;
        answer = 0;
        dfs(0, answerIdx);
        System.out.print(answer);

    }

    // 트리에 직속 자식 넣기
    public static void addTree(int idx) {
        Circle cur = circles.get(idx);
        for(int i = idx + 1; i <= N; i++) {
            Circle next = circles.get(i);
            if(!visit[i]) {
                double d = calcD(cur.x, cur.y, next.x, next.y);
                if(d < cur.r) {
                    tree[idx].add(i);
                    tree[i].add(idx);
                    visit[i] = true;
                    addTree(i);
                }
            }
        }
    }


    // 가장 깊은 원 찾기
    public static void dfs(int depth, int start) {
        if(depth > answer) {
            answer = depth;
            answerIdx = start;
        }
        for(int next : tree[start]) {
            if(!visit[next]) {
                visit[next] = true;
                dfs(depth + 1, next);
                visit[next] = false;
            }
        }
    }

    // 두 점 사이의 거리 구하기
    public static double calcD(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static class Circle {
        int idx;
        int x;
        int y;
        int r;

        public Circle(int idx, int x, int y, int r) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}