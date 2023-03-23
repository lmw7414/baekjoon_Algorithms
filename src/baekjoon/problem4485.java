package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class problem4485 {

    static int N;
    static int INF = Integer.MAX_VALUE;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if(N == 0) break;

            board = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j< N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.println("Problem " + cnt++ +": " + dijkstra());
        }


    }

    public static int dijkstra() {
        int[][] visited = new int[N][N]; //방문할 노드 초기 빼고 다 INF
        PriorityQueue<Point> pq = new PriorityQueue<>((a1, a2) -> a1.w - a2.w);
        int[] dx = {-1, 1, 0, 0}; //상하좌우
        int[] dy = {0, 0, -1, 1};

        for(int i = 0; i < N; i++) {
            Arrays.fill(visited[i], INF);
        }
        visited[0][0] = board[0][0];

        pq.add(new Point(0,0, board[0][0]));

        while(!pq.isEmpty()) {
            Point point = pq.poll();

            for(int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                    continue;
                if(visited[nx][ny] > visited[point.x][point.y] + board[nx][ny]) {
                    visited[nx][ny] = visited[point.x][point.y] + board[nx][ny];
                    pq.add(new Point(nx, ny, board[nx][ny]));
                }
            }
        }
        return visited[N-1][N-1];
    }

    static class Point {
        int x;
        int y;
        int w;
        Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }
    }
}

/*
지금쯤이면 고민이 해결되셨을 수도 있지만 혹시나 하여 올립니다.

bfs는 시작점에서부터 동일한 거리를 짧은 순서대로 탐색합니다.

거리가 1인 곳, 거리가 2인 곳, 거리가 3인 곳 이렇게 촘촘히 검사하지요. 따라서 거리가 3인 곳을 방문할 땐 거리가 2인 곳에서 다시 거리가 1인 곳으로 가지 않습니다.

왔던 곳을 다시 가지 않는다는 말입니다.

그러나 다익스트라는 왔던 곳을 다시 가기도 합니다. 왔던 곳을 방문하는 더 빠른 최단경로가 존재한다면 말이지요.

따라서 알고리즘의 목적 자체가 다른 겁니다. bfs는 모든 곳을 둘러보는 알고리즘이며 다익스트라는 모든 곳에 대해서 방문하는 최단 경로를 알아내는 알고리즘입니다.
 */