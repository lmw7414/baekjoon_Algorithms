import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		int input;
		int max = -1000000;
		int min = 1000000;
		for(int i=0; i < num; i++)
		{
			input = scan.nextInt();
			if(input > max)
				max = input;
			if(input < min)
				min = input;
			
		}
		System.out.println(min + " " + max);
	}

}
