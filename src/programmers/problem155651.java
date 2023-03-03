package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class problem155651 {

    public static void main(String[] args) {
        //String[][] b1 = {{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}};
        //String[][] b2 = {{"09:10", "10:10"}, {"10:20", "12:20"}};
        String[][] b3 = {{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}};
        System.out.println(solution(b3));
    }

    public static int solution(String[][] book_time) {
        List<Room> roomList = new ArrayList<>();

        LinkedList<BT> table = new LinkedList<>();

        for (int i = 0; i < book_time.length; i++) {
            int sh, sm, eh, em;
            String[] time1 = book_time[i][0].split(":");
            String[] time2 = book_time[i][1].split(":");
            sh = Integer.parseInt(time1[0]);
            sm = Integer.parseInt(time1[1]);
            eh = Integer.parseInt(time2[0]);
            em = Integer.parseInt(time2[1]);
            table.add(new BT(sh, sm, eh, em));
        }

        for (int i = 0; i < table.size(); i++) {
            if (roomList.isEmpty()) {
                roomList.add(new Room(table.get(i)));
            } else {
                boolean flag = false;
                for (int j = 0; j < roomList.size(); j++) {
                    if (roomList.get(j).isAvailable(table.get(i))) {
                        roomList.get(j).room.add(table.get(i));
                        flag = true;
                        break;
                    }
                }
                if(!flag)
                    roomList.add(new Room(table.get(i)));
            }
        }
        return roomList.size();
    }
}

class Room {
    List<BT> room; // 하루의 시간을 가진 룸

    public Room() {
        room = new ArrayList<>();
    }

    public Room(BT bt) {
        room = new ArrayList<>();
        room.add(bt);
    }

    public boolean isAvailable(BT bt) {
        for (int i = 0; i < room.size(); i++) {
            if (room.get(i).isInclude(bt)) {
                return false;
            }
        }
        return true;
    }
}

class BT {
    int S_H;  //시작 시간
    int S_M;  //시작 분
    int E_H;  //끝나는 시간
    int E_M;  // 끝나는 분

    public BT(int sh, int sm, int eh, int em) {
        S_H = sh;
        S_M = sm;
        E_H = eh;
        E_M = em + 10;

        if (E_M >= 60) {
            E_H++;
            E_M -= 60;
        }
    }

    public String toZero(int num) {
        if(num == 0)
            return "00";
        return Integer.toString(num);
    }
    public boolean isInclude(BT bt) {
        int startTime1 = Integer.parseInt(Integer.toString(S_H) + toZero(S_M));
        int endTime1 = Integer.parseInt(Integer.toString(E_H) + toZero(E_M));

        int startTime2 = Integer.parseInt(Integer.toString(bt.S_H) + toZero(bt.S_M));
        int endTime2 = Integer.parseInt(Integer.toString(bt.E_H) + toZero(bt.E_M));

        if((startTime1 <= startTime2 && endTime1 > startTime2) || (startTime1 <= endTime2 && endTime1 > endTime2))
            return true;
        return false;
    }

}
