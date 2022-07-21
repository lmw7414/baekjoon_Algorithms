package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2469 {

    static int K;  // 사다리 타는 사람의 수; 사다리 개수
    static int N;  // 사다리의 행 수
    static int qIdx;

    static int startPeopleArr[];  // 1부터 시작
    static int endPeopleArr[];    // 1부터 시작
    static int ladder[][];  // 사다리 배열
    static int before[];
    static int after[];
    static int result[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        K = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());

        startPeopleArr = new int[K + 1];
        endPeopleArr = new int[K + 1];
        before = new int[K + 1];
        after = new int[K + 1];
        str = br.readLine();
        for (int i = 1; i <= K; i++) {
            startPeopleArr[i] = i;
            endPeopleArr[i] = str.charAt(i - 1) - 64;
        }
        result = new int[K+1];
        ladder = new int[N + 1][K+1];
        ladder[0][0] = 0;

        for (int i = 1; i <=N ; i++) {
            str = br.readLine();
            ladder[i][0] = 0;
            ladder[i][K] = 0;
            for (int j = 1; j < K; j++) {
                if(str.charAt(j-1) == '*')
                    ladder[i][j] = 0;
                else if(str.charAt(j-1) == '-')
                    ladder[i][j] = 1;
                else {
                    qIdx = i;
                    ladder[i][j] = -1;
                }
            }
        }
        //printLadder();
        for(int i=1; i<= K; i++) {
            firstCalculate(i, startPeopleArr[i]);
            secondCalculate(i, endPeopleArr[i]);
        }
        /*for(int i=1; i<= K; i++) {
            System.out.print(before[i] + " ");
        }
        System.out.println();

        for(int i=1; i<= K; i++) {
            System.out.print(after[i] + " ");
        }
        System.out.println();*/

//        for(int i =1; i < K; i++)
//            System.out.print('x');
        showResult();
    }

    static void firstCalculate(int idx, int person) {
        for(int i =1; i< qIdx; i++) {
            if(ladder[i][idx-1]  == 0 && ladder[i][idx] == 0)
                continue;
            else {
                if(ladder[i][idx-1] == 1)
                    idx -= 1;
                else if(ladder[i][idx] == 1)
                    idx +=1;
            }
        }
        before[idx] = person;
    }

    static void secondCalculate(int idx, int person) {
        for(int i =N; i> qIdx; i--) {
            if(ladder[i][idx-1]  == 0 && ladder[i][idx] == 0)
                continue;
            else {
                if(ladder[i][idx-1] == 1)
                    idx -= 1;
                else if(ladder[i][idx] == 1)
                    idx +=1;
            }
        }
        after[idx] = person;
    }

    static void showResult() {
        boolean possible = true;
        for(int i =1; i <K; i++) {
            if(before[i] == after[i])
                result[i] = 0;
            else if( before[i] == after[i+1])
                result[i] = 1;
            else if(before[i] == after[i-1])
                result[i - 1] = 1;
            else {
                possible = false;
                for(int j =1; j < K; j++)
                    System.out.print('x');
                break;
            }
        }
        if(possible) {
            for (int j = 1; j < K; j++) {
                if (result[j] == 0)
                    System.out.print('*');
                else
                    System.out.print('-');
            }
        }
    }

    static void printLadder() {
        System.out.println("\n****printLadder****");
        for(int i = 1; i<=N; i++) {
            for(int j = 1; j <K; j++) {
                if(ladder[i][j] == -1)
                    System.out.print('?');
                else if(ladder[i][j] == 0)
                    System.out.print('*');
                else
                    System.out.print('-');
            }
            System.out.println();
        }
    }
}
