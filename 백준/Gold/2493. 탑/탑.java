import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];
		Stack<int[]> stack = new Stack();
		Stack<int[]> temp = new Stack();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			stack.push(new int[] {i + 1, Integer.parseInt(st.nextToken())});
		
		while(!stack.isEmpty()) {
			while(!temp.isEmpty() && stack.peek()[1] >= temp.peek()[1]) {
				arr[temp.peek()[0]] = stack.size();
				temp.pop();
			}
			if(temp.isEmpty() || stack.peek()[1] < temp.peek()[1]) {
				temp.add(stack.pop());
			}
		}
		for(int i = 1; i < arr.length; i++) {
			sb.append(arr[i]).append(" ");
		}
		System.out.print(sb);
	}
}