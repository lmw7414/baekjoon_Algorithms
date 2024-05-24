import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Word> words = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if (!set.contains(str)) {
                set.add(str);
                words.add(new Word(str.length(), str));
            }
        }
        Collections.sort(words, (a1, b1) -> {
            if (a1.len == b1.len) {
                for (int i = 0; i < a1.len; i++) {
                    if (a1.str.charAt(i) == b1.str.charAt(i)) continue;
                    return a1.str.charAt(i) - b1.str.charAt(i);
                }
            }
            return a1.len - b1.len;
        });
        words.stream().forEach(word -> System.out.println(word.str));
    }

    static class Word {
        int len;
        String str;

        public Word(int len, String str) {
            this.len = len;
            this.str = str;
        }
    }
}