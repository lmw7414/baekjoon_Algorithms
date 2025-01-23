import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        return calc(begin, target, words);
    }
    
    public int calc(String start, String target, String[] words) {
        boolean[] visit = new boolean[words.length];
        boolean flag = false;
        for(String word : words) {
            if(target.equals(word)) {
                flag = true;
                break;
            }
        }
        if(!flag) return 0;
        
        
        Queue<Key> queue = new ArrayDeque<>();
        queue.add(new Key(start, 0));
        
        while(!queue.isEmpty()) {
            Key key = queue.poll();
            String cur = key.word;
            int curCnt = key.cnt;
            
            if(cur.equals(target)) return curCnt;
            
            for(int i = 0; i < cur.length(); i++) {
                for(int j = 0; j < words.length; j++) {
                    if(visit[j]) continue;
                    
                    if(words[j].startsWith(cur.substring(0, i)) && words[j].endsWith(cur.substring(i+1, words[j].length()))) {
                        visit[j] = true;
                        queue.add(new Key(words[j], curCnt + 1));
                        
                    }
                }
            }
        }
        
        return 0;
    }
    
    static class Key {
        String word;
        int cnt;
        public Key(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
}