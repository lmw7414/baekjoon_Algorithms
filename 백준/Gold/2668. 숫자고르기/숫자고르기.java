
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
1 -> 3
3 -> 5
5 -> 7
7 -> 1
 */
public class Main {

    static int N;
    static int[] arr;
    static boolean[] visit;
    static List<Integer> answerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        visit = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i <= N; i++) {
            if (visit[i]) continue;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            visit[i] = true;
            dfs(i, arr[i], set);
            if (set.isEmpty()) continue;
            for (int val : set) {
                visit[val] = true;
                answerList.add(val);
            }

        }
        Collections.sort(answerList);
        System.out.println(answerList.size());
        for (int i : answerList) System.out.println(i);
    }

    public static void dfs(int start, int cur, Set<Integer> set) {
        if (start == cur) {
            return;
        }
        if (visit[cur] || set.contains(cur)) {
            set.clear();
            return;
        }
        set.add(cur);
        dfs(start, arr[cur], set);
    }

}