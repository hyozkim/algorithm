package programmers.level2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// 문자열 - 방금 그곡
// Programmers Lv.2
public class ThatSong {
    // 1) 곡 제목 / 2) 전제 재생 시간  / 3) 전체 멜로디
    static class MusicInfo {
        String title;
        int length;
        String melody;

        public MusicInfo(String title, int length,String melody) {
            this.title = title;
            this.length = length;
            this.melody = melody;
        }
    }

    public static String replace(String melody) {
        return melody
                .replace("C#","c")
                .replace("D#","d")
                .replace("E#","e")
                .replace("F#","f")
                .replace("G#","g")
                .replace("A#","a");
    }

    public static int timeStampOf(String timeString) {
        return Integer.parseInt(timeString.split(":")[0]) * 60 + Integer.parseInt(timeString.split(":")[1]);
    }

    public static MusicInfo makeMusicInfo(String musicinfo) {
        String[] split = musicinfo.split(",");;

        String title = split[2]; // 1. 곡 제목

        int t_begin = timeStampOf(split[0]);
        int t_end = timeStampOf(split[1]);
        int length = (t_end-t_begin);   // 2. 전체 재생 시간

        split[3] = replace(split[3]);
        int noteLength = split[3].length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length/noteLength; i++) {
            sb.append(split[3]);
        }
        sb.append(split[3].substring(0, length % noteLength));
        String melody = sb.toString(); // 3. 전체 멜로디

        // System.out.println("title: " + title + ", length: " + length + ", note: " + melody);
        return new MusicInfo( title, length, melody );
    }

    public static String solution(String m, String[] musicinfos) {
        // 1. MusicInfo Class 객체로 생성
        List<MusicInfo> musicInfoList = new ArrayList<>();
        for( String musicinfo : musicinfos ) {
            musicInfoList.add(makeMusicInfo(musicinfo));
        }

        // 2. 멜로디 없는 곡 제외
        String M = replace(m);
        Iterator<MusicInfo> iter = musicInfoList.iterator();
        while( iter.hasNext() ) {
            MusicInfo musicInfo = iter.next();
            if( musicInfo.melody.indexOf(M) < 0 )
                iter.remove();
        }

        // 3. 재생시간이 가장 긴 음악
        musicInfoList.sort((o1,o2) -> o2.length - o1.length);

        return musicInfoList.size() > 0 ? musicInfoList.get(0).title : "(None)";
    }

    public static void main(String[] args) {
        //String m = "ABCDEFG"; String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        //String m = "CC#BCC#BCC#BCC#B"; String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
        String m = "ABC"; String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};
        //String m = "SIDFUSIDF"; String[] musicinfos = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        System.out.println(solution(m,musicinfos));
    }
}
