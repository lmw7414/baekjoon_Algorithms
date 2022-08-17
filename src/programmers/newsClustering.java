package programmers;

//뉴스 클러스터링
//https://school.programmers.co.kr/learn/courses/30/lessons/17677?language=java

import java.util.*;

public class newsClustering {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();

        System.out.println(solution(str1, str2));
    }

    static public int solution(String str1, String str2) {
        int answer = 0;
        boolean confirm = false;
        int common = 0;
        int sum = 0;
        String str;

        ArrayList arrayList1 = new ArrayList();
        ArrayList arrayList2 = new ArrayList();

        for(int i = 0; i< str1.length()-1; i++) {
            str = str1.substring(i, i+2);
            confirm = false;
            for(int j =0; j<2; j++) {
                if(str.charAt(j) >= 'a' && str.charAt(j) <= 'z' || str.charAt(j) >= 'A' && str.charAt(j) <= 'Z')
                    confirm = true;
                else {
                    confirm = false;
                    break;
                }

            }
            if(confirm) {
                str = str.toUpperCase();
                arrayList1.add(str);
            }
        }


        for(int i = 0; i< str2.length()-1; i++) {
            str = str2.substring(i, i+2);
            confirm = false;
            for(int j =0; j<2; j++) {
                if(str.charAt(j) >= 'a' && str.charAt(j) <= 'z' || str.charAt(j) >= 'A' && str.charAt(j) <= 'Z')
                    confirm = true;
                else {
                    confirm = false;
                    break;
                }
            }
            if(confirm) {
                str = str.toUpperCase();
               arrayList2.add(str);
            }
        }



        for(int i=0; i< arrayList2.size(); i++) {
            if(arrayList1.contains(arrayList2.get(i))){
                arrayList1.remove(arrayList2.get(i));
                common++;
            }
        }

        sum = arrayList1.size() + arrayList2.size();

        if(sum == 0)
            return 65536;
        double a = common*65536/sum;
        answer = (int)a;
        return answer;
    }



}
