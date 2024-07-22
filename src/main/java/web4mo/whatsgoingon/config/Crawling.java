package web4mo.whatsgoingon.config;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;


public class Crawling {
    public static void main(String[] args) {
        Crawling c = new Crawling();
        //String url = "https://n.news.naver.com/mnews/article/003/0012672453"; //크롤링 기사 URL
        String url = "https://n.news.naver.com/mnews/article/020/0003577439";


        try {
            System.out.println(c.makeContent(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String makeContent(String url) throws IOException {
        Connection articleConn = Jsoup.connect(url).userAgent("Mozilla/5.0"); // 네이버 뉴스 기사 페이지 요청
        Document articleDoc = articleConn.get();

        Element content = articleDoc.selectFirst("#dic_area");  // 기사 본문 추출
        if (content != null) {
            return content.text();
        } else {
            return "기사 본문을 찾을 수 없습니다.";
        }
        //여기도 주석 
    }
}

