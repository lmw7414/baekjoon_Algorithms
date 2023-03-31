package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem147354 {

    public static void main(String[] args) {
        int[][] data = {{2,2,6},{1,5,10},{4,2,9},{3,8,3}};
        System.out.println(solution(data,2,2,3));
    }

    public static int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        List<Data> datas = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            int pk = data[i][0];
            int ak = data[i][col-1];
            Data d = new Data(pk, ak);
            for (int j = 0; j < data[0].length; j++) {
                d.add(data[i][j]);
            }
            datas.add(d);
        }

        Collections.sort(datas, (
                (a1, a2) ->
                {
                    if (a1.ak == a2.ak)
                        return a2.pk - a1.pk;
                    else
                        return a1.ak - a2.ak;
                })
        );
        for (int i = row_begin-1; i < row_end; i++) {
            Data d = datas.get(i);
            for (int a : d.arr)
                d.si += a % (i + 1);
            answer ^= d.si;
        }


        return answer;
    }

    static class Data {
        int pk;
        int ak;
        int si;
        List<Integer> arr;

        public Data(int pk, int ak) {
            this.pk = pk;
            this.ak = ak;
            this.si = 0;
            arr = new ArrayList<>();
        }

        public void add(int data) {
            arr.add(data);
        }
    }
}
