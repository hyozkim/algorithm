package programmers.level3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/***
 * 매칭 점수 - Lv3
 * 2019 카카오 블라인드 코딩테스트
 */
public class MatchingScore {
    static class PageInfo {
        String url;
        int base_score;
        ArrayList<String> links;

        public PageInfo(String url, int base_score, ArrayList<String> links) {
            this.url = url;
            this.base_score = base_score;
            this.links = links;
        }
    }

    static class Score {
        int index;
        double score;
        public Score(int index, double score) {
            this.index = index;
            this.score = score;
        }
    }

    static Comparator<Score> comp = new Comparator<Score>() {
        @Override
        public int compare(Score o1, Score o2) {
            if( o1.score == o2.score )
                return o1.index - o2.index;
            return o2.score - o1.score > 0 ? 1 : -1; // double 내림차순 정렬
        }
    };

    public static int solution(String word, String[] pages) {
        /***
         * 기본점수: 검색어(word) 등장횟수 - 대소문자 무시
         * 외부 링크 수: 다른 외부 페이지 연결 횟수
         * 링크 점수: 링크가 걸린 다른 웹페이지의 기본점수 / 다른 웹페이지의 외부 링크 수
         * 매칭 점수: 기본점수 + 링크 점수
         */
        ArrayList<PageInfo> pageInfos = new ArrayList<>();
        for(String page : pages) {
            pageInfos.add(makePageInfo(page, word));
        }

        // 매칭 점수
        ArrayList<Score> scores = new ArrayList<>();
        for (int i = 0; i < pageInfos.size(); i++) {
            PageInfo currPage = pageInfos.get(i);

            double link_score = 0;
            for (int j = 0; j < pageInfos.size(); j++) {
                if( i == j ) continue;

                PageInfo compPage = pageInfos.get(j);
                int base_score = compPage.base_score;
                int link_count = compPage.links.size();

                for (String link : compPage.links) {
                    if( currPage.url.equals(link) ) {
                        link_score += (double) base_score/link_count;
                    }
                }
            }

            scores.add(new Score(i, pageInfos.get(i).base_score+link_score));
        }

        // TODO 매칭점수가 가장 높은 웹페이지, 동일한 매칭점수 -> index 번호가 가장 작은 것
        Collections.sort(scores, comp);

        return scores.get(0).index;
    }

    private static PageInfo makePageInfo(String page, String word) {
        page = page.toLowerCase();
        word = word.toLowerCase();

        // TODO 문자열 파싱

        // 페이지 url : <head> 내 <meta> 내 <content>
        int l = 0; int r = 0; int mid = 0;
        while( mid <= l ) {
            l = page.indexOf("<meta", l+1);
            r = page.indexOf(">", l);
            mid = page.lastIndexOf("https://", r);
        }
        r = page.indexOf("\"", mid);
        String url = page.substring(mid, r);

        // 검색어 : 대소문자 무시
        int count = 0;
        l = page.indexOf("<body>", r);
        int start = l;
        while( true ) {
            start = page.indexOf(word, start+1);
            if( start == -1 ) break;
            if( !Character.isLetter(page.charAt(start-1)) && !Character.isLetter(page.charAt(start+word.length())) )
                count ++;
        }
        //System.out.println(count);

        // href : 외부링크
        ArrayList<String> links = new ArrayList<>();
        while( true ) {
            l = page.indexOf("<a href", l+1);
            r = page.indexOf(">", l);
            mid = page.lastIndexOf("https://", r);
            if( l == -1 ) break;
            r = page.indexOf("\"", mid);
            links.add(page.substring(mid,r));
            l = r;
        }

        return new PageInfo(url,count,links);
    }

    public static void main(String[] args) {
        //String word = "blind"; String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        String word = "Muzi"; String[] pages = {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"};

        System.out.println(solution(word, pages));
    }
}
