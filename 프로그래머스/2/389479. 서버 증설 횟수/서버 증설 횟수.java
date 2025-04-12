import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<Server> servers = new ArrayDeque<>();
        
        for(int i = 0; i < 24; i++) {
            int users = players[i]; // i ~ i+1 까지 접속한 유저 수
            serverCheck(servers, i);
            int cnt = users / m;//필요한 서버 대수
            if(cnt > servers.size()) {
                int need = cnt - servers.size();
                answer += need;
                for(int c = 0;  c < need; c++) servers.add(new Server(i, i + k));
            }
        }
        return answer;
    }
    
    public void serverCheck(Queue<Server> servers, int curTime) {
        while(!servers.isEmpty()) {
            Server cur = servers.peek();
            if(cur.end <= curTime) servers.poll();
            else break;
        }
    }
    
    static class Server {
        int start, end;
        public Server(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}