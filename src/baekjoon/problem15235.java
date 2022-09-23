package baekjoon;

import java.util.Scanner;

public class problem15235 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N];
        int answer[] = new int[N];
        int second = 0;
        for(int i = 0; i< N; i++) {
            arr[i] = sc.nextInt();
        }
        boolean confirm = false;
        while (true) {
            for(int i =0; i< arr.length; i++) {
                if (arr[i] != 0)
                    break;
                if(i==arr.length-1 && arr[i] == 0)
                    confirm = true;
            }
            if(confirm)
                break;

            for(int i=0; i< arr.length; i++) {
                if(arr[i] !=0)
                {
                    arr[i]--;
                    second++;
                    answer[i] = second;
                }

            }

        }
        for(int i = 0; i< answer.length; i++)
            System.out.printf(answer[i] +" ");

    }
}
