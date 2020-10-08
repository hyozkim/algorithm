package programmers.level1;

import java.util.*;

/***
 * 오픈 채팅방
 * 2019 카카오 블라인드 코딩테스트
 */
public class OpenChatRoom {
    public static String[] solution(String[] records) {
        Map<String,String> map = new HashMap<>();
        ArrayList<String[]> arrayList = new ArrayList<>();

        for(String record : records) {
            String[] split = record.split(" ");

            if( split[0].equals("Enter") ) {
                map.put(split[1], split[2]);
                arrayList.add(split);
            } else if( split[0].equals("Change") ) {
                map.put(split[1], split[2]);

            } else if( split[0].equals("Leave") ) {
                arrayList.add(split);
            }
        }

        return makeLogString(arrayList, map);
    }

    private static String[] makeLogString(ArrayList<String[]> splits, Map<String,String> map) {
        String[] answer = new String[splits.size()];

        int index = 0;
        for(String[] split : splits) {
            if( split[0].equals("Enter") ) {
                answer[index] = map.get(split[1]) + "님이 들어왔습니다.";
            } else if( split[0].equals("Leave") ) {
                answer[index] = map.get(split[1]) + "님이 나갔습니다.";
            }
            index++;
        }

        return answer;
    }

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        System.out.println(Arrays.toString(solution(record)));
    }
}
