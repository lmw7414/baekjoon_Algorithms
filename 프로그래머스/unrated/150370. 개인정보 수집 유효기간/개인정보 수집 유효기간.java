import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        List<Integer> ans = new ArrayList<>(); 
        HashMap<String, Integer> hm = new HashMap<>();

        String[] todays = today.split("\\.");
        Date now = new Date(Integer.parseInt(todays[0]), Integer.parseInt(todays[1]), Integer.parseInt(todays[2]));
        
        for(String str : terms) {
            String[] splits = str.split(" ");
            hm.put(splits[0], Integer.parseInt(splits[1]));
        }
        int idx = 1;
        for(String str : privacies) {
            String[] splits = str.split(" ");
            
            String[] pdate = splits[0].split("\\.");
            Date date = new Date(Integer.parseInt(pdate[0]), Integer.parseInt(pdate[1]), Integer.parseInt(pdate[2]));
            
            int term = hm.get(splits[1]);
            date.addMonth(term);
            if(Integer.parseInt(now.YMDFormat()) - Integer.parseInt(date.YMDFormat()) >= 0)
                ans.add(idx);
            idx++;
        }
        
        return ans.stream().mapToInt(i -> i).toArray();
    }
    
}

class Date {
    int year;
    int month;
    int day;
    
    Date(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public void addMonth(int month) {
        int mon = this.month + month;
        if(mon > 12) {
            if(mon % 12 != 0) {
                this.year += mon / 12;
                mon %= 12;
                this.month = mon;
            } else {
                this.year += mon / 12;
                this.year--;
                this.month = 12;
            }
                
        } else
            this.month = mon;
    }
    public String YMDFormat() {
        String str ="";
        str += year;
        if(month < 10) {
            str += 0;
            str += month;
        } else
            str += month;
        if(day < 10) {
            str += 0;
            str += day;
        } else
            str += day;
        return str;
    }
}