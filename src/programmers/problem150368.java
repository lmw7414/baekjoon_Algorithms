package programmers;

import java.util.*;
public class problem150368 {

    static int[][] u;
    static int[] e;
    static int[] answer;
    public static void main(String[] args) {
        int[][] users = {{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}};
        int[] emoticons = {1300, 1500, 1600, 4900};
        solution(users, emoticons);
        System.out.println(answer[0] + " " + answer[1]);
    }

    public static int[] solution(int[][] users, int[] emoticons) {

        u = users;
        e = emoticons;
        answer = new int[2];
        for(int i = 0; i< emoticons.length; i++) {
            e[i] = emoticons[i];
        }

        dfs(new ArrayList(), emoticons.length);
        return answer;
    }

    public static void dfs(List<Integer> list, int depth) {
        int[] discount = {10, 20, 30, 40};

        if(depth == 0) {
            // 종료조건
            int[] price = new int[e.length];
            int member = 0;
            int revenue = 0;

            for(int i = 0; i < price.length; i++ ) {
                price[i] = e[i] /100 * (100 - list.get(i));
            }

            for(int i = 0; i < u.length; i++) {
                int userDiscount = u[i][0];
                int minPrice = u[i][1];
                int cost = 0;
                for(int j = 0; j < price.length; j++) {
                    if(userDiscount <= list.get(j)) {
                        cost += price[j];
                    }
                }
                if(minPrice <= cost) {
                    member++;
                } else {
                    revenue += cost;
                }

            }
            isMax(member, revenue);
        } else {
            for(int i = 0; i< discount.length; i++) {
                list.add(discount[i]);
                dfs(list, depth - 1);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void isMax(int member, int revenue) {
        if(answer[0] < member) {
            answer[0] = member;
            answer[1] = revenue;
        } else if( answer[0] == member) {
            if(answer[1] < revenue) {
                answer[1] = revenue;
            }
        }
    }
}
