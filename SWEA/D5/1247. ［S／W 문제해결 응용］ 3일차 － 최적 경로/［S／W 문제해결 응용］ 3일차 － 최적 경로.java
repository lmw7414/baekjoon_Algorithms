import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static Point[] customers;
	static boolean[] visited;
	static Point[] distances;
	static int answer = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());  //테케 갯수 입력
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cx, cy, hx, hy;  // 회사 정보, 집정보
			cx = Integer.parseInt(st.nextToken());
			cy = Integer.parseInt(st.nextToken());
			hx = Integer.parseInt(st.nextToken());
			hy = Integer.parseInt(st.nextToken());
			
			Point company = new Point(cx, cy);
			Point home = new Point(hx, hy);
			
			customers = new Point[N];
			visited = new boolean[N];
			distances = new Point[N + 2];
			distances[0] = company;
			distances[N + 1] = home;
			for(int i = 0; i< N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customers[i] = new Point(x, y);
			}

			answer = Integer.MAX_VALUE;
			DFS(1);
			System.out.println("#" + tc + " " + answer);
		}

	}
	
	public static void DFS(int depth) {
		if(depth == N + 1) {
			int tmp = 0;
			for(int i = 1; i< distances.length; i++) {
				tmp += calc(distances[i], distances[i - 1]);
			}
			answer = Math.min(tmp, answer);
			return;
		}
		for(int i = 1; i<= N; i++) {
			if(!visited[i - 1]) {
				visited[i - 1] = true;
				distances[depth] = customers[i - 1];
				DFS(depth+1);
				distances[depth] = null;
				visited[i - 1] = false;
			}
		}
		
	}
	public static int calc(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}
	
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}