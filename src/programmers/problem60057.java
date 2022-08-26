package programmers;

import java.util.List;

// 문자열 압축
public class problem60057 {

    static List<String> s = List.of("aabbaccc", "ababcdcdababcdcd", "abcabcdede", "abcabcabcabcdededededede", "xababcdcdababcdcd", "aaaaaaaaaab", "a");

    public static void main(String[] args) {

        for (int i = 0; i < s.size(); i++)
            System.out.println(solution(s.get(i)));
    }

    static public int solution(String s) {
        StringBuilder sb = new StringBuilder();  //단축시킨 문자열 저장 공간
        int min = Integer.MAX_VALUE;  // 초기 최소값은 MAX_VALUE로 설정
        int size = s.length();
        int count = 0;  // 반복된 문자열의 수
        String partStr = new String(); //비교할 문자열

        if (s.length() == 1)  // 주어진 S의 문자열이 1개일 경우
            return 1;

        for (int i = 1; i <= size / 2; i++) {  // 전체 문자열의 길이 중 반절의 크기까지만 비교하면 된다.
            count = 1;
            for (int j = 0; j < size; j += i) {
                if (i + j <= size && count == 1)  // 다음에 비교할 문자열이 인덱스를 넘어가는지 확인하고 count는 1일 경우 비교할 문자열 세팅
                    partStr = s.substring(j, i + j);

                if (j + 2 * i <= size && partStr.equals(s.substring(j + i, j + 2 * i))) { //문자열 비교해서 맞으면 count 증가
                    count++;
                } else {
                    if (count == 1)  //count가 1이면 반복되는 문자열 구간이 없음
                        sb.append(partStr);
                    else
                        sb.append(count + partStr);

                    if (partStr.length() > s.substring(i + j).length()) {  // 비교할 문자열들의 길이가 안맞으면 S의 마지막 부분이라 파악하고 마지막 부분만 sb에 추가 후 종료
                        sb.append(s.substring(i + j));
                        break;
                    }

                    count = 1;
                }
            }
            if (sb.length() != 0 && min > sb.length())  // 최종 길이 비교
                min = sb.length();
            sb.delete(0, sb.length());  // sb 초기화
        }

        return min;  // 최소값 리턴
    }
}
