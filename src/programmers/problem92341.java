package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//주차 요금 계산
public class problem92341 {

    static class Car {
        String carNumber;
        String status;
        int hour;
        int minute;
        int time;
        int fee;
    }


    public static void main(String[] args) {
        List<Car> table = new ArrayList<>();
        String carNum;
        String hour;
        String minute;
        String status;


        //int[] fees = {180, 5000, 10, 600};
        int[] fees = {1, 461, 1, 10};
        //String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        String[] records = {"00:00 1234 IN"};


        for(int i = 0; i< records.length; i++) {

            Car car = new Car();
            hour = records[i].substring(0,2);
            minute = records[i].substring(3,5);
            carNum = records[i].substring(6,10);
            status = records[i].substring(11, 13);

            Car tableCar = null;
            for(int j = 0; j<table.size(); j++) {
                if (table.get(j).carNumber.equals(carNum)) {
                    tableCar = table.get(j);
                    break;
                }
            }
            if(tableCar == null) {
                car.carNumber = carNum;
                car.hour = Integer.parseInt(hour);
                car.minute = Integer.parseInt(minute);
                car.time = 0;
                car.status = "IN";
                car.fee =0;
                table.add(car);
            }
            else {  // 들어온 적이 있을 때
                //System.out.println(tableCar);
                if(status.equals("IN")) {  //방문한적이 있지만 또 들어오는 경우
                    tableCar.hour = Integer.parseInt(hour);
                    tableCar.minute = Integer.parseInt(minute);
                    tableCar.status = "IN";
                    if(tableCar.carNumber == "5961")
                        System.out.println("hour : " + tableCar.hour + "minute : " + tableCar.minute);

                }
                else {  //출차
                    tableCar.time += (Integer.parseInt(hour) - tableCar.hour)* 60 + (Integer.parseInt(minute) - tableCar.minute);
                    tableCar.hour = 0;
                    tableCar.minute =0;
                    tableCar.status = "OU";
                    //System.out.println(tableCar.time);
                }
            }
        }




        for(int i = 0; i< table.size(); i++) {
            Car car = table.get(i);
            if(car.status == "IN") {
                car.time += (24-car.hour) *60 -car.minute-1;
                if(car.time <= fees[0])
                    car.fee = fees[1];
                else {
                    car.time -= fees[0];
                    car.fee +=fees[1];
                    car.fee += (car.time%fees[2] >0 ? car.time/fees[2]+1 : car.time/fees[2] ) * fees[3];
                }
            }
            else {
                if(car.time <= fees[0])
                    car.fee = fees[1];
                else {
                    car.time -= fees[0];
                    car.fee +=fees[1];
                    car.fee += (car.time%fees[2] >0 ? car.time/fees[2]+1 : car.time/fees[2] ) * fees[3];
                }
            }
        }

        Collections.sort(table, (a, b) -> Integer.parseInt(a.carNumber) - Integer.parseInt(b.carNumber));

        for(Car a : table)
            System.out.println(a.fee);
    }


}
