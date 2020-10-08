package programmers.level4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 호텔 방 배정
// 2019 카카오 개발자 겨울 인턴십
public class HotelRoom {
    public static void main(String[] args) {
        long k = 10; long[] room_number = {1,3,4,1,3,1};

        System.out.println(Arrays.toString(solution(k,room_number)));
    }

    static Map<Long, Long> map = new HashMap<>();
    public static long[] solution(long k, long[] room_number) {
        int len = room_number.length;
        long[] answer = new long[len];

        for (int i = 0; i < len; i++) {
            answer[i] = unionFind(room_number[i]);
        }

        return answer;
    }

    private static long unionFind(long room_number) {
        if( !map.containsKey(room_number) ) {
            map.put(room_number, room_number+1);
            return room_number;
        }

        long nextRoomNumber = map.get(room_number);
        long findEmptyRoomNumber = unionFind(nextRoomNumber);
        map.put(room_number, findEmptyRoomNumber);
        return findEmptyRoomNumber;
    }
}
