import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * [문제 설명]
 * 숲에 고슴도치 한마리가 살고 있다. 고슴도치는 비버의 굴로 가능한 빨리 도망가 홍수를 피해야 한다.
 * R행 C열로 이루어진 지도. '.'은 비어있는 곳, '*'은 물이 차 있는 곳, 'X'는 돌이 있는 곳,  'D'는 비버의 굴, 'S'는 고슴도치 위치
 * 매 분마다 고슴도치는 현재 있는 칸의 인접한 네곳으로 이동할 수 있음(상,하,좌,우)
 * 물도 매분마다 비어있는 칸으로 확장
 * 물과 고슴도치는 돌을 통과할 수 없음
 * 고슴도치는 물이 차있는 곳으로 이동 불가
 * 물도 비버의 소굴로 이동할 수 없음
 * 
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없음. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는 이동할 수 없음
 * [문제 해결 프로세스]
 * 1. 물을 먼저 이동시킴
 * 2. 다음 고슴도치 이동
 * 3. BFS 사용하여 최단 경로 체크
 */
public class Main {

	static char[][] map;
	static Point hedgehog;
	static int R, C;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				char c = str.charAt(j);
				if(c == 'S') hedgehog = new Point(i, j, 0);
				else map[i][j] = c;
			}
		}
		
		int answer = BFS();
		if(answer == -1) System.out.println("KAKTUS");
		else System.out.println(answer);
	}
	
	public static int BFS() {
		Queue<Point> queue = new ArrayDeque<>();
		Queue<Point> temp = new ArrayDeque<>();
		boolean[][] visit = new boolean[R][C];
		queue.add(hedgehog);
		visit[hedgehog.x][hedgehog.y] = true;
		
		while(!queue.isEmpty()) {
			moveWater();  // 물 이동
			
			while(!queue.isEmpty()) temp.add(queue.poll());
			
			while(!temp.isEmpty()) {
				Point cur = temp.poll();
				
				if(map[cur.x][cur.y] == 'D') return cur.time;
				
				for(int i = 0; i < 4; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= R || ny >= C || visit[nx][ny]) continue;  // 경계 체크 및  방문 체크
					if(map[nx][ny] == 'X' || map[nx][ny] == '*') continue;
					
					queue.add(new Point(nx, ny, cur.time + 1));
                    visit[nx][ny] = true;
				}
			}
		}
		
		return -1;
	}
	
	public static void moveWater() {
		Queue<int[]> queue = new ArrayDeque<>();
		List<int[]> waterList = new ArrayList<>();
		boolean[][] visit = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '*') {
					visit[i][j] = true;
					queue.add(new int[] {i, j});
				}
			}
		}
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for(int i = 0; i< 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= R || ny >= C || visit[nx][ny]) continue;  // 경계 체크 및  방문 체크
				if(map[nx][ny] == 'X' || map[nx][ny] == 'D') continue;  // 돌이 있거나 비버집인 경우
				waterList.add(new int[] {nx, ny});
			}
		}
		
		for(int[] water : waterList) {
			map[water[0]][water[1]] = '*';
		}
	}
	
	static class Point {
		int x;
		int y;
		int time;
		Point(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

}