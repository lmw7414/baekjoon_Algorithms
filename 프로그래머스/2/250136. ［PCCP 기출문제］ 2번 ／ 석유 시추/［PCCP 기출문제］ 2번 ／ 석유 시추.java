import java.util.*;
/*
땅의 최대 길이 500
땅으 최대 깊이 500

이미 방문한 곳이라면?
- 방문 배열 생성 -> int 타입
- 그룹별 ID 부여
- 해시맵으로 ID에 해당하는 너비 값 저장
*/
class Solution {
    static int N, M;
    static Map<Integer, Integer> hm = new HashMap<>();
    static int[][] visit;
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        visit = new int[N][M];
        findOil(land);
        // printMap();
        int answer = 0;
        
        for(int j = 0; j < M; j++) {
            Set<Integer> set = new HashSet<>();
            int result = 0;
            for(int i = 0; i < N; i++) {
                if(land[i][j] == 0) continue;
                set.add(visit[i][j]);
            }
            for(int id : set) {
                result += hm.get(id);
            }
            answer = Math.max(answer, result);
        }
        
        
        return answer;
    }
    
    
    
    public void findOil(int[][] land) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int id = 1;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(land[i][j] == 0) continue;
                if(visit[i][j] > 0) continue; // 이미 아이디 부여함
                
                Queue<int[]> queue = new ArrayDeque<>();
                visit[i][j] = id;
                queue.add(new int[]{i, j});
                int cnt = 1;
                while(!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    
                    for(int d = 0; d < 4; d++) {
                        int nx = cur[0] + dx[d];
                        int ny = cur[1] + dy[d];
                        if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                        if(land[nx][ny] == 0) continue;
                        if(visit[nx][ny] > 0) continue;
                        
                        visit[nx][ny] = id;
                        cnt++;
                        queue.add(new int[] {nx, ny});
                    }
                }
                hm.put(id++, cnt);
            }
        }
    }
    
    public void printMap() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(visit[i][j] + " ");
            }
            System.out.println();
        }
    }
}