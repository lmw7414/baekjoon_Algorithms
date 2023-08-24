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
 * N : 구역의 개수. 최대 10개
 * 둘째줄 : 1-N까지의 인구 수
 * 셋째줄 ~ N번째 각 구역과 인접한 구역의 정보
 * 각 정보의 첫번째 - 그 구역과 인접한 구역의 수
 * 이후 인접한 구역의 번호
 *
 * 원하는 결과 : 두 선거구로 나누었을 때 두 선거구의 인구 차이의 최솟값
 *
 * [문제 풀이 시나리오]
 * 1. 부분집합으로 주어진 마을에서 반으로 나눈다. 2^10 = 1024(공집합이면 안됨)
 * 2. 나누어진 부분집합 중에서 BFS로 서로 연결되어 있는지 확인
 * 3. 확인이 되었다면 둘의 마을인구의 최소 합 계산
 */
public class Main {
	static int N;
	static int[] district;
	static boolean[] subSetVisit;
	static List<Integer>[] adjList;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		// 전역 변수 초기화
		district = new int[N + 1];
		subSetVisit = new boolean[N + 1];
		adjList = new List[N + 1];
		
		for(int i = 1; i <= N; i++)
			adjList[i] = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i<= N; i++)
			district[i] = Integer.parseInt(st.nextToken());
		

		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			
			for(int j = 0; j <size; j++) {
				int input = Integer.parseInt(st.nextToken());
				adjList[i].add(input);
			}
		}
		subSet(1);
		if(answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}
	
	//부분집합 만들기
	public static void subSet(int depth) {
		if(depth == N) {
			List<Integer> A = new ArrayList<>();  // True인 값 저장
			List<Integer> B = new ArrayList<>();  // False인 값 저장
			for(int i = 1; i <= depth; i++) {
				if(subSetVisit[i]) A.add(i);
				else B.add(i);
			}
			//System.out.println(A);
			//System.out.println(B);
			// 길이 이어져 있는지 체크
			if(!isConnected(A)) return;
			if(!isConnected(B)) return;
			
			// 이어져있다면 최소 인구수 계산
			int aCnt = 0, bCnt = 0;
			for(int idx : A)
				aCnt += district[idx];
			for(int idx : B)
				bCnt += district[idx];
			
			answer = Math.min(answer, Math.abs(aCnt - bCnt));
			return;
		}
		
		subSetVisit[depth] = true;
		subSet(depth + 1);
		subSetVisit[depth] = false;
		subSet(depth + 1);
	}
	
	public static boolean isConnected(List<Integer> village) {
		if(village.size() == 0) return false; // 공집합인 경우 
		int start = village.get(0);
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		visit[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int next : adjList[cur]) {
				if(visit[next]) continue;
				if(!village.contains(next)) continue;  // 탐색하려는 노드가 부분집합에 없으면 continue
				visit[next] = true;
				queue.add(next);
			}
		}
		
		for(int idx : village) {
			if(!visit[idx]) return false;
		}
		return true;
	}

}