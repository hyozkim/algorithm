package programmers.level2;

import java.util.*;
public class Tuple {
    private static ArrayList<ArrayList<Integer>> parseStr(String s) {
        char[] toChar = s.toCharArray();

        Stack<Character> stack = new Stack<>();
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        ArrayList<Integer> each = new ArrayList<>();
        String number = "";
        for (int i = 1; i < toChar.length-1; i++) {
            if( Character.isDigit(toChar[i]) ) {
                number += toChar[i];

            } else if ( toChar[i] == '{' )  {
                stack.push(toChar[i]);
                each = new ArrayList<>();
                number = "";

            } else if( toChar[i] == '}' && stack.peek() == '{' ) {
                each.add( Integer.parseInt(number) );
                ret.add(each);
                stack.pop();

            } else if( toChar[i] == ',' && Character.isDigit(toChar[i+1])) {
                each.add( Integer.parseInt(number) );
                number = "";

            }
        }

        return ret;
    }

    static ArrayList<Integer> answerList = new ArrayList<>();
    private static void dfs(ArrayList<ArrayList<Integer>> tuples, int index, ArrayList<Integer> list) {
        if( tuples.size() <= index ) {
            answerList = list;
            return;
        }

        ArrayList<Integer> tuple = tuples.get(index);
        for (int i = 0; i < tuple.size(); i++) {
            if( !list.contains(tuple.get(i)) ) {
                list.add(tuple.get(i));
                dfs(tuples,index+1, list);
            }
        }

    }

    private static int[] getArrayList2Arrray(ArrayList<Integer> list) {
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public static int[] solution(String s) {
        ArrayList<ArrayList<Integer>> tuples = parseStr(s);
        Collections.sort(tuples, (a1,a2) -> a1.size() - a2.size());
        //for(ArrayList<Integer> arr : tuples ) System.out.println(arr);

        dfs(tuples,0,new ArrayList<Integer>());

        //return getArrayList2Arrray(answerList);
        return answerList.stream().mapToInt(x->x).toArray();
    }

    public static void main(String[] args) {
        //String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
        //String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
        //String s = "{{20,111},{111}}";
        //String s = "{{123}}";
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";

        System.out.println(Arrays.toString(solution(s)));
    }

}