import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 조합 접근
 * @author SSAFY
 *
 */
public class Main {

	static int[] arr = new int[9];
	static int[] temp = new int[7];
	static boolean[] visit = new boolean[9];
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i< 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		permutation(0, 0);
		System.out.print(sb);
	}
	
	public static void permutation(int depth, int idx) {
		if(depth == 7) {
			int sum = 0;
			for(int i = 0; i< 7; i++)
				sum += temp[i];
			if(sum == 100) {
				for(int i = 0; i < depth; i++) {
					sb.append(temp[i]).append("\n");
				}
				return;
			}
			return;
		} else {
			for(int i = idx; i < 9; i++ ) {
				if(visit[i]) continue;
				visit[i] = true;
				temp[depth] = arr[i];
				permutation(depth + 1, i + 1);
				visit[i] = false;
			}
		}
		
		
		
	}	

}