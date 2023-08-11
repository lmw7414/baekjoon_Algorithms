import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * N 과일의 개수
 * L 뱀의 처음 길이
 * 
 * 1. 입력받은 배열을 정렬
 * 2. 뱀의 길이보다 작거나 같은 높이에 있는 과일을 먹으며 뱀의 길이를 증가시킨다.
 * 3. 최대 거리 구하기
 *
 */
public class Main {

	static int N, L;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		for(int i = 0; i< N;  i++) {
			if(arr[i] <= L) L++;
			else break;
		}
		
		System.out.println(L);

	}

}