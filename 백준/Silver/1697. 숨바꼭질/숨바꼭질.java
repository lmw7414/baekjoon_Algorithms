
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, K;
	static int answer = Integer.MAX_VALUE;
	static int arr[] = new int[100001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		System.out.println(BFS());
		
	}
	
	
	// 수빈이의 위치가 x일 때 걷는 경우 x-1, x+1
	// 순간이동 하는 경우 2*x
	public static int BFS() {
		int time = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(N);
		arr[N] = 0;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur == K)
				return arr[cur];
			
			for(int i = 0; i< 3; i++) {
				int next = 0;
				if(i == 0) {
					next = cur - 1;
				} else if(i == 1) {
					next = cur + 1;
				} else if(i == 2) {
					next = 2 * cur;
				}
				
				if(next >= 0 && next < 100001 && arr[next] == 0)
				{
					arr[next] = arr[cur] + 1;
					queue.add(next);
				}
			}

		}
		
		
		return 0;
	}

}