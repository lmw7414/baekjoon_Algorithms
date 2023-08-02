import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] arr;
	static Queue<Student> queue = new LinkedList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N + 1];
		for(int i = 1; i<= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int stuCnt = Integer.parseInt(br.readLine());
		for(int i = 0; i< stuCnt; i++) {
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			queue.add(new Student(sex, value));
			
			while(!queue.isEmpty()) 
				turnSwitch(queue.poll());
		}
		
		for(int i = 1; i<= N; i++) {
			System.out.print(arr[i] + " ");
			if(i % 20 == 0)
				System.out.println();
		}
	}
	public static void turnSwitch(Student stu) {
		if(stu.sex == 1) {  //남자
			for(int i = stu.value; i <= N; i += stu.value)
				arr[i] = (arr[i] == 0) ? 1 : 0;
		} else if(stu.sex == 2) {  // 여자
			int idx = stu.value;
			int range = 0;
			while(true) {
				if(idx - range > 0 && idx + range <= N) {
					if(arr[idx - range] == arr[idx + range]) {
						arr[idx - range] = (arr[idx - range] == 0) ? 1 : 0;
						arr[idx + range] = arr[idx - range];
						range++;
					} else break;
				} else break;
			}
		}
	}
	
	static class Student {
		int sex;
		int value;
		
		public Student(int sex, int value) {
			this.sex = sex;
			this.value = value;
		}
	}
	
}