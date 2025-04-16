import java.util.*;

// 제시되는 단어 최대 10만개
// 단어들의 길이 총합 : L 최대 100만

class Solution {
    static int answer = 0;
    public int solution(String[] words) {        
        Arrays.sort(words);
        char key = '0';
        List<String> datas = new ArrayList<>();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            if(key == '0') {
                key = word.charAt(0);
                datas = new ArrayList<>();
            }
            
            if(word.charAt(0) == key) {
                datas.add(word.substring(1, word.length()));
            } else {
                if(!datas.isEmpty()) calc(1, datas);
                key = '0';
                i--;
            }
        }
        if(!datas.isEmpty()) calc(1, datas);
        
        //for(String word : words) System.out.print(word.substring(1, word.length()) + " ");
        return answer;
    }
    
    public void calc(int depth, List<String> wordList) {
        // System.out.print("depth: " + depth + " ");
        // for(String word : wordList) System.out.print(word + " ");
        // System.out.println();
        if(wordList.size() == 1) {
            answer += depth;
            return;
        }
        char key = '0';
        List<String> datas = new ArrayList<>();
        for(int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            if(word.length() == 0) {
                answer+= depth;
                continue;
            }
            
            if(key == '0') {
                key = word.charAt(0);
                datas = new ArrayList<>();
            }
            
            if(word.charAt(0) == key) {
                datas.add(word.substring(1, word.length()));
            } else {
                if(!datas.isEmpty()) calc(depth + 1, datas);
                key = '0';
                i--;
            }   
        }
        if(!datas.isEmpty()) calc(depth + 1, datas);
        
    }
}

// depth 0
// go
// gone
// guild

// depth 1
// o
// one 
// uild

// depth 2
// ""                  + 2
// ne

// depth 3
// e                   + 3

// depth 2
// ild                 + 2