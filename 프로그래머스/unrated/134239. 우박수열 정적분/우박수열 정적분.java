import java.util.*;

class Solution {
    static List<Double> width = new ArrayList<>();
    
    public double[] solution(int k, int[][] ranges) {
        int size = colatz(0, k);
        List<Double> answer = new ArrayList<>();
        
        for(int[] range : ranges) {
            int start = range[0];
            int end = size + range[1];
            if(start > end)
                answer.add(-1.0);
            else if (start == end)
                answer.add(0.0);
            else {
                double a = 0;
                for(int i = start; i< end; i++) {
                    a += width.get(i);
                }
                answer.add(a);
            }
        }
        
        return answer.stream().mapToDouble(i -> i).toArray();
    }
    
    public int colatz(int count, int num) {
        if(num == 1) {
            return count;
        }
        if(num % 2 == 0){
            calcWidth(num, num/2);
            return colatz(count + 1, num/2);
        } else {
            calcWidth(num, num * 3 + 1);
            return colatz(count + 1, num * 3 + 1);
        }
            
    }
    
    public void calcWidth(double h1, double h2) {
        width.add((h1 + h2) / 2);
    }
}