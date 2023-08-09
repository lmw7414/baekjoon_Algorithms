import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 1. 우선순위 큐를 사용
 * 2. Comparator 오버라이딩 후 비교 조건 정하기
 * 3. 절댓값이 같다면 작은 수를 저장
 * 4. 절댓값이 작으면 그냥 저장
 */
public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((a1, b1) -> {
			if(Math.abs(a1) == Math.abs(b1))
					return a1 - b1;
			return Math.abs(a1) - Math.abs(b1);
		});
		
		for(int i = 0; i< N; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input == 0) {
				if(pq.isEmpty()) sb.append(0).append("\n");
				else sb.append(pq.poll()).append("\n");
			} else pq.add(input);
		}
		System.out.print(sb);
	}

}