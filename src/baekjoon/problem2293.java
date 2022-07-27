package baekjoon;

import java.util.Scanner;
// 동전 1
//메모이제이션 해야함
public class problem2293 {

    static int N;  // 동전의 개수
    static int K;  // 동전의 합
    static int coins[];
    static int count[]; //경우의 수 카운트

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        coins = new int[N];
        count = new int[K + 1];
        for(int i=0; i< N; i++)
            coins[i] = sc.nextInt();

        count[0] = 1;  // 0원을 만들 수 있는 경우의 수는 무조건 존재 -> 1
        for(int i = 0; i< N; i++) {
            for(int j =1; j<=K; j++) {
                if(j >= coins[i])
                    count[j] = count[j] + count[j - coins[i]];   // i=1 (2원)인 경우에 5원을 만들 수 있으려면 1일 때 5를 만드는 값과,  2를 써야하므로 3원을 만드는 경우의 수를 더해준다.
            }
        }
        System.out.println(count[K]);

    }
}
