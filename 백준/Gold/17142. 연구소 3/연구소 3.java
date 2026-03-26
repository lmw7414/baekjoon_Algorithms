
import java.util.*;
import java.io.*;

/*
연구소 크기 N:  4 <= N <= 50
바이러스 개수 M: 1 <= M <= 10

가장 처음의 모든 바이러스는 비활성 상태
활성 상태의 바이러스는 상하좌우로 인접한 모든 빈 칸으로 동시 복제되는데 1초가 걸림
연구소의 바이러스 M개를 활성 상태로 변경하려고 함
0: 빈칸 1: 벽 2 바이러스 위치

문제 해결 프로세스
- 조합: 10Cn 최대 252개
 */
public class Main {

  static int N, M;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static List<Point> viruses = new ArrayList<>(); // 비활성 바이러스 리스트
  static Point[] targets;
  static int[][] original;
  static int ANSWER = Integer.MAX_VALUE;
  static int zeroCnt = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    original = new int[N][N];
    targets = new Point[M];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int num = Integer.parseInt(st.nextToken());
        original[i][j] = num;
        if(num == 2) {
          viruses.add(new Point(i, j));
        } else if(num == 0) {
          zeroCnt++;
        }
      }
    }
    permutation(0, 0);
    if (ANSWER == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else {
      System.out.println(ANSWER);
    }
  }

  public static void permutation(int depth, int cur) {
    if(depth == M) {
      int result = calc();
      ANSWER = Math.min(ANSWER, result);
      return;
    }

    for(int i = cur; i < viruses.size(); i++) {
      targets[depth] = viruses.get(i);
      permutation(depth + 1, i + 1);
    }
  }

  // BFS
  // 바이러스 사이즈 + 벽 사이즈 == N * N
  public static int calc() {
    Queue<Point> queue = new ArrayDeque<>();
    boolean[][] visit = new boolean[N][N];

    //2. 활성 바이러스 초기화
    for(Point target : targets) {
      visit[target.x][target.y] = true;
      queue.add(target);
    }
    int cnt = 0;
    int sec = 0;
    while(!queue.isEmpty()) {
      if(cnt == zeroCnt) return sec;
      int activeSize = queue.size();
      sec++;
      while(activeSize > 0) {
        Point cur = queue.poll();
        activeSize--;

        for(int d = 0; d < 4; d++) {
          int nx = cur.x + dx[d];
          int ny = cur.y + dy[d];
          if(OOB(nx, ny)) continue;
          if(original[nx][ny] == 1) continue; // 벽 인경우
          if(visit[nx][ny]) continue; // 이미 방문한 경우

          // 빈칸
          if(original[nx][ny] == 0) cnt++;
          visit[nx][ny] = true;
          queue.add(new Point(nx, ny));
        }
      }
    }
    return Integer.MAX_VALUE;
  }

  // true 면 경계 밖
  public static boolean OOB(int x, int y) {
    return x < 0 || y < 0 || x >= N || y >= N;
  }

  static class Point {

    int x, y;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}