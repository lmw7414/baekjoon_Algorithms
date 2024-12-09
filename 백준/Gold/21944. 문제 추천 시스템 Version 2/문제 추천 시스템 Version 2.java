import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static TreeSet<Problem> problems = new TreeSet<>();
    static Problem[] pbs = new Problem[100001];
    static Map<Integer, TreeSet<Problem>> hm = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 1. 문제 리스트 저장
        int N = Integer.parseInt(br.readLine()); // 문제의 개수: 최대 10만
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken()); // 문제번호
            int L = Integer.parseInt(st.nextToken()); // 난이도 1~100
            int G = Integer.parseInt(st.nextToken()); // 알고리즘 분류 1~100
            Problem problem = new Problem(P, L, G);
            pbs[P] = problem;
            problems.add(problem);
            if (hm.containsKey(G)) {
                hm.get(G).add(problem);
            } else {
                hm.put(G, new TreeSet<>());
                hm.get(G).add(problem);
            }
        }

        // 2. 쿼리
        int M = Integer.parseInt(br.readLine()); // 최대 1만개
        for (int m = 0; m < M; m++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                case "recommend":
                    recommend(input);
                    break;
                case "recommend2":
                    recommend2(input);
                    break;
                case "recommend3":
                    recommend3(input);
                    break;
                case "add":
                    add(input);
                    break;
                case "solved":
                    solved(input);
                    break;
            }
        }
        System.out.print(sb.toString());
    }

    // 정렬한 상태 -> 뒤에 있는 문제가 어려운 문제 -> 첫번째로 G가 나오면
    public static void recommend(String[] input) {
        int G = Integer.parseInt(input[1]);
        TreeSet<Problem> prs = hm.get(G);
        if (input[2].equals("1")) { // 알고리즘 분류가 G인 문제 중 가장 어려운 문제 번호(같다면 문제 번호가 큰 것)
            sb.append(prs.last().P).append("\n");
        } else { // 알고리즘 분류가 G인 문제 중 가장 쉬운 문제 번호(같다면 문제 번호가 작은 것)
            sb.append(prs.first().P).append("\n");
        }
    }

    // -> 문제를 난이도 순으로 저장(같다면 문제 번호가 작은 것 부터 오름차순)
    public static void recommend2(String[] input) {
        if (input[1].equals("1")) {
            // 알고리즘 분류 없이 가장 어려운(난이도 높은) 문제 번호(같다면 문제 번호가 큰 것)
            sb.append(problems.last().P).append("\n");
        } else {
            // 알고리즘 분류 없이 가장 쉬운(난이도 낮은) 문제 번호(같다면 문제 번호 작은 것)
            sb.append(problems.first().P).append("\n");
        }
    }

    // -> 난이도 기준 이분탐색
    public static void recommend3(String[] input) {
        int L = Integer.parseInt(input[2]);
        if (input[1].equals("1")) {
            // 알고리즘 분류 없이 난이도 L보다 크거나 같은 문제 중 가장 쉬운 문제 번호(같다면 문제 번호가 작은 것)
            Problem ceil = problems.ceiling(new Problem(0, L, 0));
            if(ceil == null) sb.append(-1).append("\n");
            else sb.append(ceil.P).append("\n");
        } else {
            // 알고리즘 분류 없이 난이도 L보다 작은 문제중 가장 어려운 문제 번호(같다면 문제 번호가 큰 것)
            Problem low = problems.lower(new Problem(0, L, 0));
            if(low == null) sb.append(-1).append("\n");
            else sb.append(low.P).append("\n");
        }
    }

    public static void add(String[] input) {
        int P = Integer.parseInt(input[1]);
        int L = Integer.parseInt(input[2]);
        int G = Integer.parseInt(input[3]);
        Problem problem = new Problem(P, L, G);
        pbs[P] = problem;
        problems.add(problem);
        if (hm.containsKey(G)) {
            hm.get(G).add(problem);
        } else {
            hm.put(G, new TreeSet<>());
            hm.get(G).add(problem);
        }
    }

    public static void solved(String[] input) {
        int P = Integer.parseInt(input[1]);
        problems.remove(pbs[P]);
        hm.get(pbs[P].G).remove(pbs[P]);
        pbs[P] = null;
    }

    static class Problem implements Comparable<Problem> {
        int P, L, G;

        public Problem(int p, int l, int g) {
            P = p;
            L = l;
            G = g;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.L == o.L) return this.P - o.P;
            return this.L - o.L;
        }
    }
}