import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 *문자	의미
 * .	평지(전차가 들어갈 수 있다.)
 * *	벽돌로 만들어진 벽
 * #	강철로 만들어진 벽
 * -	물(전차는 들어갈 수 없다.)
 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
 * 
 * 문자	동작
 * U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
 * D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
 * L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
 * R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
 * S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 * 
 * 
 * 경계체크 필요
 * 포탄을 발사하면, 벽돌로 만들어진 벽, 강철 벽에 충돌하거나 경계를 벗어날 때까지 직진
 * 벽돌에 부딪히면 벽돌은 부서진다.
 * 강철에 부딪히면 아무일도 일어나지 않는다.
 * 
 * @author SSAFY
 *
 */
public class Solution {
	static StringBuilder sb = new StringBuilder();
	static int H, W; // 높이, 너비
	static char[][] field;  // 게임 맵
	static Tank tank;
	static int[] dx = {-1, 1, 0 ,0};  // 상하좌우
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine()); // 전체 테스트 케이스
		for(int tc = 1; tc <=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			field = new char[H][W];
			tank = new Tank();
			for(int i = 0; i < H; i++) {
				field[i] = br.readLine().toCharArray();
				for(int j = 0; j < field[i].length; j++) {
					if(field[i][j] == '^') {
						tank.dir = 0;
						tank.x = i;
						tank.y = j;
					}else if(field[i][j] == 'v') {
						tank.dir = 1;
						tank.x = i;
						tank.y = j;
					}else if(field[i][j] == '<') {
						tank.dir = 2;
						tank.x = i;
						tank.y = j;
					}else if(field[i][j] == '>') {
						tank.dir = 3;
						tank.x = i;
						tank.y = j;
					}
				}
			}
			int N = Integer.parseInt(br.readLine());
			String command = br.readLine();
			
			for(int i = 0; i< command.length(); i++) {
				char c = command.charAt(i);
				if(c == 'S') {
					shoot(tank);
				} else {
					int nextDir = -1;
					if(c == 'U') nextDir = 0;
					else if(c == 'D') nextDir = 1;
					else if(c == 'L') nextDir = 2;
					else if(c == 'R') nextDir = 3;
					moveTank(tank, nextDir);
				}
			}
			printAnswer(tc);
		}
		System.out.print(sb);
		
		
	}
	
	
	// 경계체크 메서드
	public static boolean check(int x, int y) {
		return x >= 0 && y >= 0 && x < H && y < W && field[x][y] == '.';
	}
	/**
	 * 탱크 이동 메서드
	 *  ^	위쪽을 바라보는 전차(아래는 평지이다.) - 0
	 *  v	아래쪽을 바라보는 전차(아래는 평지이다.) - 1
	 *  <	왼쪽을 바라보는 전차(아래는 평지이다.) - 2
	 *  >	오른쪽을 바라보는 전차(아래는 평지이다.) - 3
	 */
	public static void moveTank(Tank tank, int nextDir) {
		// field 최신화
		field[tank.x][tank.y] = '.';
		
		// 탱크의 방향을 틀고, 이동 가능하면 한칸 이동
		tank.dir = nextDir;
		int nx = tank.x + dx[nextDir];
		int ny = tank.y + dy[nextDir];
		if(check(nx, ny)) {
			tank.x = nx;
			tank.y = ny;
		}
		
		// field 최신화
		switch(tank.dir) {
		case 0:
			field[tank.x][tank.y] = '^';
			break;
		case 1:
			field[tank.x][tank.y] = 'v';
			break;
		case 2:
			field[tank.x][tank.y] = '<';
			break;
		case 3:
			field[tank.x][tank.y] = '>';
			break;
		}
		
	}
	
	/**
	 * 포탄 발사 메서드
	 * 포탄을 발사하면, 벽돌로 만들어진 벽, 강철 벽에 충돌하거나 경계를 벗어날 때까지 직진
	 * 벽돌에 부딪히면 벽돌은 부서진다.
	 * 강철에 부딪히면 아무일도 일어나지 않는다.
	 */
	public static void shoot(Tank tank) {
		int move = 1;
		int nx = tank.x + dx[tank.dir] * move;
		int ny = tank.y + dy[tank.dir] * move;
		while(true) {
			if(!check(nx, ny)) {  // 통과하지 못했을 때
				// 경계에 닿았을 경우
				if(!(nx >= 0 && ny >= 0 && nx < H && ny < W))
					break;
				// 강철일 경우
				if(field[nx][ny] == '#')
					break;
				// 벽돌일 경우 벽돌을 평지로 만들고 break;
				if(field[nx][ny] == '*') {
					field[nx][ny] = '.';
					break;
				}
			}
			move++;
			nx = tank.x + dx[tank.dir] * move;
			ny = tank.y + dy[tank.dir] * move;
		}
	}
	
	// 출력 메서드
	public static void printAnswer(int tc) {
		sb.append("#").append(tc).append(" ");
		for(int i = 0; i < H; i++) {
			for(int j = 0; j< W; j++) {
				sb.append(field[i][j]);
			}
			sb.append("\n");
		}
	}
	
	static class Tank {
		int dir;
		int x;
		int y;
	}
}