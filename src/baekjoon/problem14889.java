package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//스타트와 링크
public class problem14889 {
    static int N;
    static int board[][];
    static boolean visit[];

    static int teamA[];
    static int teamB[];

    static int difference = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N+1][N+1];
        visit = new boolean[N+1];

        teamA = new int[N/2];
        teamB  = new int[N/2];


        for(int i=1; i<= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0,0);
        System.out.println(difference);

    }
    // 팀 나누는 함수
    static void DFS(int level, int before) {
        if(level == N/2){
            int result = differenceOfTeam();
            if(difference > result)
                difference = result;
            if(difference == 0) {
                System.out.println(difference);
                System.exit(0);
            }
            return;
        }
        else
        {
            for(int i= 1; i<= N; i++) {
                if(visit[i] == false && before < i)
                {
                    visit[i] = true;
                    DFS(level+1, i);
                    visit[i] = false;
                }
            }
            return;
        }

    }
    // 팀 파워 계산
    static int powerOfTeam(int team[]) {
        int power = 0;
        for(int i = 0; i< N/2; i++) {
            for(int j =i+1; j< N/2; j++) {
                power += board[team[i]][team[j]] + board[team[j]][team[i]];
            }
        }
        return power;
    }

    // 팀간의 차이 계산
    static int differenceOfTeam() {

        int powerA;
        int powerB;

        int aIdx = 0;
        int bIdx = 0;

        for(int i = 1; i<= N; i++) {
            if(visit[i] == true)
                teamA[aIdx++] = i;
            else
                teamB[bIdx++] = i;
        }
        powerA = powerOfTeam(teamA);
        powerB = powerOfTeam(teamB);

        return Math.abs(powerA - powerB);
    }

}

