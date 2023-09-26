import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 체크 포인트
 * 1로 가거나 열쇠로 가거나
 * @author SSAFY
 *
 */
public class Main {

	static int N, M;
	static char[][] map;
	static Point user;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				if (input.charAt(j) == '0') user = new Point(i, j, 0 ,0);
				map[i][j] = input.charAt(j);
			}
			
		}
		System.out.println(BFS());

	}
	
	public static int BFS() {
		Queue<Point> queue = new ArrayDeque();
		boolean[][][] visit = new boolean[N][M][64];
		queue.add(user);
		visit[user.x][user.y][0] = true;  // 아무런 키가 없는 상태
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if(map[cur.x][cur.y] == '1') return cur.cost;
			
			for(int i = 0; i< 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(visit[nx][ny][cur.key] || map[nx][ny] == '#') continue;
				
				if('a' <= map[nx][ny] && 'f' >= map[nx][ny]) {  // 열쇠 획득!!
					int key = 1 << (map[nx][ny] - 'a');
					key = cur.key | key;  // 키 등록
					visit[nx][ny][key] = true;
					queue.add(new Point(nx, ny, cur.cost + 1, key));
				} else if ('A' <= map[nx][ny] && 'F' >= map[nx][ny]) {
					// 문을 만났다면 키가 있는지 확인
					if((cur.key & 1 << (map[nx][ny] - 'A')) == (int)Math.pow(2, map[nx][ny] - 'A')) {
						visit[nx][ny][cur.key] = true;
						queue.add(new Point(nx, ny, cur.cost + 1, cur.key));
					}
				} else {
					visit[nx][ny][cur.key] = true;
					queue.add(new Point(nx, ny, cur.cost + 1, cur.key));
				}
			}
			
		}
		return -1;
	}

	

	static class Point {
		int x;
		int y;
		int cost;
		int key;

		public Point(int x, int y, int cost, int key) {
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.key = key;
			
		}
	}

}