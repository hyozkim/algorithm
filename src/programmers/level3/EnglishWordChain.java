package programmers.level3;

import java.util.*;

/***
 * 영어끝말잇기
 * Summer/Winter Coding (~2018)
 */
public class EnglishWordChain {
    public static void main(String[] args) {
        int n = 3; String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
        //int n = 5; String[] words = {"hello", "observe", "effect", "take", "either", "recognize", "encourage", "ensure", "establish", "hang", "gather", "refer", "reference", "estimate", "executive"};
        //int n = 2; String[] words = {"hello", "one", "even", "never", "now", "world", "draw"};

        System.out.println(Arrays.toString(solution(n,words)));
    }

    public static int[] solution(int n, String[] words) {
        int[] answer = {0,0};

        Map<String,Integer> map = new HashMap<>();
        String last_word = words[0].substring(words[0].length()-1);
        map.put(words[0], 1);
        int index = 1;
        boolean isChanged = false;
        for ( ; index < words.length; index++) {
            // TODO 끝말잇기가 되지 않으면 -> 탈락
            if( !words[index].startsWith(last_word) ) {
                isChanged = true;
                break;
            }

            // TODO 이전에 이미 나온 단어 -> 탈락
            if( map.containsKey(words[index]) ) {
                isChanged = true;
                break;
            }

            map.put(words[index], 1);
            last_word = words[index].substring(words[index].length()-1);
        }

        // System.out.println(index);
        answer[0] = (index % n) + 1;
        answer[1] = (index / n) + 1;
        return isChanged ? answer : new int[]{0,0};
    }
}
